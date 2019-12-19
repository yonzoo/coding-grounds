#ifndef NEURAL_NETWORK
#define NEURAL_NETWORK
#include <vector>
#include "layer.hpp"

class NeuralNetwork
{
    typedef Layer * LayerPtr;

    public:
        NeuralNetwork(std::vector<int>);
        Matrix * guess(double[], int size);
        void setLearningRate(const double);
        void train(double[], double[], int, int);
        ~NeuralNetwork();
        
    private:
        LayerPtr * layers;
        int size;
        double learningRate;
};

#endif // NEURAL_NETWORK