#include <vector>
#include <iostream>
#include <fstream>
#include "matrix.cpp"
#include "layer.cpp"
#include "neural_network.cpp"

double inc(double s) {
  return ++s;
}
double randMToN(int M, int N)
{
    return M + (rand() / ( RAND_MAX / (N-M) ) ) ;  
}

int main() {
  const int INPUTS = 25;
  const int OUTPUTS = 10;
  //Holds information about each layer of the neural network,
  //first element always equals number of input nodes and the last layer is number of output nodes
  //all the layers between hold information about hidden layers
  std::vector<int> layers_information = {INPUTS, 125, OUTPUTS};
  double **train_data;
  double **train_targets;
  train_data = new double*[OUTPUTS];
  train_targets = new double*[OUTPUTS];
  for (int i = 0; i < OUTPUTS; i++) {
    train_data[i] = new double[INPUTS];
    train_targets[i] = new double[OUTPUTS];
  }

  for (int i = 0; i < OUTPUTS; i++) {
    for (int j = 0; j < INPUTS; j++) {
      train_data[i][j] = -1;
    }
  }

  std::ifstream inFile;
  inFile.open("../res/digits.txt");
  if (!inFile) {
    std::cerr << "Unable to open file digits.txt";
    exit(1);
  }
  std::string str;
  int index;
  int k = -1;
  while (std::getline(inFile, str)) {
    if (str[0] == '@') {
      index = str[1] - '0';
      k+=1;
    }
    else {
      if (k > -1) {
        for (int i = 0; i < OUTPUTS; i++) {
          if (i == index) {
            train_targets[index][i] = 1;
          }
          else {
            train_targets[index][i] = 0;
          }
        }
        int m = 0;
        while ((train_data[k][m] != -1) && (m < INPUTS - 1)) {
          ++m;
        }
        for (int i = 0; i < 5; i++) {
          train_data[k][m] = str[i] - '0';
          ++m;
        }
      }
    }
  }
  inFile.close();

  srand (time(0));
  NeuralNetwork *nn = new NeuralNetwork(layers_information);
  double *test_data = new double[INPUTS];
  for (int j = 0; j < INPUTS; j++) {
    test_data[j] = -1;
  }
  inFile.open("../res/test.txt");
  while (std::getline(inFile, str)) {
    int m = 0;
    while ((test_data[m] != -1) && (m < INPUTS - 1)) {
      ++m;
    }
    for (int j = 0; j < 5; j++) {
      test_data[m] = str[j] - '0';
      ++m;
    }
  }
  inFile.close();

  for (int i = 0; i < 10000; i++) {
    int randomIndex = rand() % 10;
    nn->train(train_data[randomIndex], train_targets[randomIndex], INPUTS, OUTPUTS);
  }
  double *result = nn->guess(test_data, INPUTS)->toArray();
  int max = 0;
  for (int i = 0; i < OUTPUTS; i++) {
    if (result[i] > result[max]) max = i;
  }
  std::cout << "I think your digit is " << max << "\n";
  return 0;
}