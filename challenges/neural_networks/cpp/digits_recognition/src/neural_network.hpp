#ifndef NEURAL_NETWORK
#define NEURAL_NETWORK
#include "layer.hpp"
#include <vector>

class NeuralNetwork
{
    typedef Layer * LayerPtr;

    public:
        NeuralNetwork(std::vector<int>);
        Matrix * guess(double[], int size);
        void train(double[], double[], int, int);
        ~NeuralNetwork();
        
    private:
        LayerPtr * layers;
        int size;
        double learningRate = 0.1;
};

#endif // NEURAL_NETWORK