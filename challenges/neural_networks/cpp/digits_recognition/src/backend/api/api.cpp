#include <vector>
#include <iostream>
#include "api.hpp"
#include "data.hpp"
#include "./utils/reader.cpp"
#include "../matrix_lib/matrix.cpp"
#include "../neural_network_lib/neural_network.cpp"

Api::Api() 
{
}

Api::~Api() {
  for (int i = 0; i < TOTAL_TRAINING_AMOUNT; i++) {
    delete trainDataset[i];
  }
  delete[] trainDataset;
  for (int i = 0; i < TOTAL_TESTING_AMOUNT; i++) {
    delete testDataset[i];
  }
  delete[] testDataset;
  delete nn;
}

void Api::setup(const int inputs, const int hidden, const int outputs, const int total_training, const int total_testing) {
  INPUTS = inputs;
  OUTPUTS = outputs;
  HIDDEN = hidden;
  TOTAL_TRAINING_AMOUNT = total_training;
  TOTAL_TESTING_AMOUNT = total_testing;
  //Holds information about number of nodes at each layer of the neural network
  std::vector<int> layers_information = {INPUTS, HIDDEN, OUTPUTS};
  trainDataset = Reader::readTrainData(INPUTS, OUTPUTS, TOTAL_TRAINING_AMOUNT);
  testDataset = Reader::readTestData(INPUTS, OUTPUTS, TOTAL_TESTING_AMOUNT);
  nn = new NeuralNetwork(layers_information);
}

void Api::trainNeuralNetwork(const int epoch) {
  for (int i = 0; i < epoch; i++) {
    int randomIndex = rand() % TOTAL_TRAINING_AMOUNT;
    nn->train(trainDataset[randomIndex]->getData(), trainDataset[randomIndex]->getTargets(), INPUTS, OUTPUTS);
  }
}

void Api::setLearningRate(const double learningRate) {
  nn->setLearningRate(learningRate);
} 

int* Api::predict() {
  int *res = new int[TOTAL_TESTING_AMOUNT];
  for (int i = 0; i < TOTAL_TESTING_AMOUNT; i++) {
    double *result = nn->guess(testDataset[i]->getData(), INPUTS)->toArray();
    int max = 0;
    for (int j = 0; j < OUTPUTS; j++) {
      if (result[j] > result[max]) max = j;
    }
    res[i] = max;
  }
  return res;
}