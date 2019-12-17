#include "neural_network.hpp"
#include <cmath>    
#include <vector>
#include <iostream>

double sigmoid(double x) {
  return 1 / (1 + exp(-x));
}

double dsigmoid(double y) {
  return y * (1 - y);
}

NeuralNetwork::NeuralNetwork(std::vector<int> nodesPerLayer) {
size = nodesPerLayer.size() - 1;
layers = new LayerPtr[size];
for (int i = 0; i < size; i++) {
  layers[i] = new Layer(nodesPerLayer[i], nodesPerLayer[i + 1]);
}
}

NeuralNetwork::~NeuralNetwork() {
  delete layers;
}

Matrix *NeuralNetwork::guess(double inputs_array[], int inputs_size) {
  Matrix *inputs = Matrix::fromArray(inputs_array, inputs_size);
  for (int i = 0; i < size; i++) {
    Matrix *layer_outputs = Matrix::multiply(layers[i]->getInputsWeights(), inputs);
    layer_outputs->add(layers[i]->getInputsBias());
    layer_outputs->map(sigmoid);
    layers[i]->setOutputs(layer_outputs);
    inputs = layer_outputs;
  }
  return inputs;
}

void NeuralNetwork::train(double inputs_array[], double targets_array[], int inputs_size, int targets_size) {
  Matrix *inputs = Matrix::fromArray(inputs_array, inputs_size);
  Matrix *outputs = guess(inputs_array, inputs_size);
  Matrix *convertedTargets = Matrix::fromArray(targets_array, targets_size);
  Matrix *outputErrors = Matrix::substract(convertedTargets, outputs);
  Matrix *gradients;
  for  (int i = size - 1; i >= 0; i--) {
    gradients = Matrix::map(outputs, dsigmoid);
    gradients->multiply(outputErrors);
    gradients->multiply(learningRate);
    Matrix *transposed = (i > 0) ? Matrix::transpose(layers[i - 1]->getOutputs()) : Matrix::transpose(inputs);
    Matrix *weightDeltas = Matrix::multiply(gradients, transposed);
    layers[i]->adjustWeights(weightDeltas);
    layers[i]->adjustBias(gradients);
    if (i > 0) {
      Matrix *wT = Matrix::transpose(layers[i]->getInputsWeights());
      outputErrors = Matrix::multiply(wT, outputErrors);
      outputs = layers[i - 1]->getOutputs();
    }
  }
}