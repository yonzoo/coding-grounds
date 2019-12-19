#ifndef LAYER_HPP
#define LAYER_HPP
#include "../matrix_lib/matrix.hpp"

class Layer
{
    public:
        Layer(const int, const int);
        Matrix * getInputsWeights();
        Matrix * getInputsBias();
        Matrix * getOutputs();
        void adjustWeights(Matrix *);
        void adjustBias(Matrix *);
        void setOutputs(Matrix *);
        ~Layer();
        
    private:
        Matrix *outputs;
        int input_nodes;
        int nodes;
        Matrix *input_weights;
        Matrix *input_bias;
};

#endif // LAYER_HPP