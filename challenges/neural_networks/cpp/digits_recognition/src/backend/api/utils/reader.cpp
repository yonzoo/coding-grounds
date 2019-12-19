#include <iostream>
#include <fstream>
#include "reader.hpp"
#include "../data.cpp"
#include <string>

Data ** Reader::readTrainData(const int INPUTS, const int OUTPUTS, const int TOTAL_TRAINING_AMOUNT) {
  Data **trainDataset = new Data*[TOTAL_TRAINING_AMOUNT];
  for (int i = 0; i < TOTAL_TRAINING_AMOUNT; i++) {
    trainDataset[i] = new Data(INPUTS, OUTPUTS);
  }

  std::ifstream inFile;
  inFile.open("../../../res/digits.txt");
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
            trainDataset[index]->setTrainTargets(i, 1);
          }
        }
        int m = 0;
        while ((trainDataset[k]->getData()[m] != -1) && (m < INPUTS - 1)) {
          ++m;
        }
        for (int i = 0; i < 5; i++) {
          trainDataset[k]->setTrainData(m, str[i] - '0');
          ++m;
        }
      }
    }
  }
  inFile.close();
  return trainDataset;
}

Data ** Reader::readTestData(const int INPUTS, const int OUTPUTS, const int TOTAL_TESTING_AMOUNT)  {
  Data **testDataset = new Data*[TOTAL_TESTING_AMOUNT];
  for (int i = 0; i < TOTAL_TESTING_AMOUNT; i++) {
    testDataset[i] = new Data(INPUTS, OUTPUTS);
  }
  std::ifstream inFile;
  std::string str;
  inFile.open("../../../res/output.txt");
  if (!inFile) {
    std::cerr << "Unable to open file output.txt";
    exit(1);
  }
  int k = -1;
  while (std::getline(inFile, str)) {
    if (str[0] == '@') {
      k+=1;
    }
    else {
      int m = 0;
      while ((testDataset[k]->getData()[m] != -1) && (m < INPUTS - 1)) {
        ++m;
      }
      for (int j = 0; j < 5; j++) {
        testDataset[k]->setTrainData(m, str[j] - '0');
        ++m;
      }
    }
  }
  inFile.close();
  return testDataset;
}
