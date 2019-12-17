package libraries.neural_network_lib

import matrix_math_lib.Matrix
import matrix_math_lib.MatrixMath

class NeuralNetwork(val input_nodes: Int, val hidden_nodes: Int, val output_nodes: Int) {
  var weights_ih = new Matrix(hidden_nodes, input_nodes)
  var weights_ho = new Matrix(output_nodes, hidden_nodes)
  weights_ih.randomize()
  weights_ho.randomize()

  var bias_h = new Matrix(hidden_nodes, 1)
  var bias_o = new Matrix(output_nodes, 1)
  bias_h.randomize()
  bias_o.randomize()

  var learningRate = 0.1

  def dsigmoid(y: Double): Double = {
    y * (1 - y)
  }

  def setLearningRate(lr: Double): Unit = {
    if (lr < 1 && lr > 0) learningRate = lr
    else System.err.println("Learning rate is out of bounds. Value must be from 0.0 to 1.0")
  }

  def predict(inputs_array: Array[Double]): Array[Double] = {
    val inputs = MatrixMath.fromArray(inputs_array)
    val hidden = MatrixMath.makeMatrixProduct(weights_ih, inputs)
    hidden.add(bias_h)
    hidden.map(ActivationFunction.sigmoid)

    //Generating the hidden output's output
    val outputs = MatrixMath.makeMatrixProduct(weights_ho, hidden)
    outputs.add(bias_o)
    outputs.map(ActivationFunction.sigmoid)

    outputs.toArray();
  }

  def train(inputs_array: Array[Double], targets_array: Array[Double]): Unit = {
    //Generating the hidden outputs
    val inputs = MatrixMath.fromArray(inputs_array)
    val hidden = MatrixMath.makeMatrixProduct(weights_ih, inputs)
    hidden.add(bias_h)
    hidden.map(ActivationFunction.sigmoid)

    //Generating the hidden output's output
    val outputs = MatrixMath.makeMatrixProduct(weights_ho, hidden)
    outputs.add(bias_o)
    outputs.map(ActivationFunction.sigmoid)

    //Convert array of targets to Matrix object
    val convertedTargets = MatrixMath.fromArray(targets_array)

    //Caclculate the error
    val outputErrors = MatrixMath.substract(convertedTargets, outputs)

    //Calculate gradient
    val gradients = MatrixMath.map(outputs, dsigmoid)
    gradients.multiply(outputErrors)
    gradients.multiply(learningRate)

    //Calculate deltas
    val hiddenT = MatrixMath.T(hidden)
    val weightsHoDeltas = MatrixMath.makeMatrixProduct(gradients, hiddenT)

    //Adjust the weights by deltas
    weights_ho.add(weightsHoDeltas)
    //Adjust the bias by its deltas(which are the gradients)
    bias_o.add(gradients)

    //Calculate the hidden layer errors
    val whoT = MatrixMath.T(weights_ho)
    val hiddenErrors = MatrixMath.makeMatrixProduct(whoT, outputErrors)
    val hiddenGradients = MatrixMath.map(hidden, dsigmoid)

    //Calculate hidden gradient
    hiddenGradients.multiply(hiddenErrors)
    hiddenGradients.multiply(learningRate)

    //Calculate input->hidden deltas
    val inputsT = MatrixMath.T(inputs)
    val weightsIhDeltas = MatrixMath.makeMatrixProduct(hiddenGradients, inputsT)

    //Adjust the weights by deltas
    weights_ih.add(weightsIhDeltas)
    //Adjust the bias by its deltas(which are the gradients)
    bias_h.add(hiddenGradients)
  }
}
