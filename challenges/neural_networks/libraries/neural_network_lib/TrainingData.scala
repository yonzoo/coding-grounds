package libraries.neural_network_lib

class TrainingData(val inputs: Array[Double], val targets: Array[Double]) {
  def getInputs(): Array[Double] = {
    inputs
  }
  def getTargets(): Array[Double] = {
    targets
  }
}
