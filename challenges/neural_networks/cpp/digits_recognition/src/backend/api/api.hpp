#ifndef API_HPP
#define API_HPP
#include "data.hpp"
#include "../neural_network_lib/neural_network.hpp"

class Api
{
    public:
      Api();
      ~Api();
      void setup(const int, const int, const int, const int, const int);
      void trainNeuralNetwork(const int);
      void setLearningRate(const double);
      int* predict();
      
    private:
        NeuralNetwork *nn;
        Data **trainDataset;
        Data **testDataset;
        int INPUTS;
        int OUTPUTS;
        int HIDDEN;
        int TOTAL_TRAINING_AMOUNT;
        int TOTAL_TESTING_AMOUNT;
};

#endif // API_HPP