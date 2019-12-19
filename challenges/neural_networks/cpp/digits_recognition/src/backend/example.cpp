#include "api/api.cpp"

int main() {
  const int INPUTS = 25; //number of inputs nodes
  const int HIDDEN = 64; //number of hidden nodes
  const int OUTPUTS = 10; //number of outputs nodes
  const int TOTAL_TRAINING_AMOUNT = 10; //number of training examples
  const int TOTAL_TESTING_AMOUNT = 1; //number of testing examples
  //create api instance
  Api *api = new Api();
  //api's parameters are 1: number of input nodes, 2: number of hidden nodes, 3: number of output nodes
  //4: number of training examples, 5: number of testing examples
  api->setup(INPUTS, HIDDEN, OUTPUTS, TOTAL_TRAINING_AMOUNT, TOTAL_TESTING_AMOUNT); //this architecture works well for digits recognition(25 inputs, 64 hidden, 10 outputs)
  api->setLearningRate(0.01); //sets learning rate to 0.01, default is 0.1
  api->trainNeuralNetwork(10000); //train neural network 10000 times
  // function below returns array of results corresponding to testing examples
  // for example if there are 2 testing examples, function will return array of 2 elements where values are guesses
  int *result = api->predict();
  for (int i = 0; i < TOTAL_TESTING_AMOUNT; i++) {
    std::cout << result[i] << "\n";
  }
  return 0;
}