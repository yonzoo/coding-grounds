#include "layer.hpp"

Layer::Layer(const int input_nodes, const int nodes) {
  this->input_nodes = input_nodes;
  this->nodes = nodes;

  input_weights = new Matrix(nodes, input_nodes);
  input_weights->randomize();

  input_bias = new Matrix(nodes, 1);
  input_bias->randomize();
}

Layer::~Layer() {
  delete outputs;
  delete input_weights;
  delete input_bias;
}

Matrix * Layer::getInputsWeights() {
  return input_weights;
}

Matrix *  Layer::getInputsBias() {
  return input_bias;
}


Matrix * Layer::getOutputs() {
  return outputs;
}

void Layer::adjustWeights(Matrix *deltas) {
  input_weights->add(deltas);
}

void Layer::adjustBias(Matrix *deltas) {
  input_bias->add(deltas);
}

void Layer::setOutputs(Matrix *m) {
  outputs = m;
}
