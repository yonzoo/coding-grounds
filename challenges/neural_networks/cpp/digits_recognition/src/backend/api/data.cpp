#include "data.hpp"

Data::Data(const int INPUTS, const int OUTPUTS) {
    data = new double[INPUTS];
    targets = new double[OUTPUTS];
    for (int i = 0; i < INPUTS; i++) {
      data[i] = -1;
    }
    for (int i = 0; i < OUTPUTS; i++) {
      targets[i] = 0;
    }
}

Data::~Data() {
  delete data;
  delete targets;
}

double * Data::getData() {
  return data;
}

double * Data::getTargets() {
  return targets;
}

void Data::setTrainData(const int i, const double value) {
  data[i] = value;
}

void Data::setTrainTargets(const int i, const double value) {
  targets[i] = value;
}