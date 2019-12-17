package libraries.neural_network_lib

object ActivationFunction {
  def sigmoid(x: Double): Double = {
    1 / (1 + Math.exp(-x))
  }
}
