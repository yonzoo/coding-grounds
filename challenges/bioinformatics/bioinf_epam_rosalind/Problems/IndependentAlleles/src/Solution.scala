package src

import src.utils.{Reader, Writer}
import scala.math.pow

object Solution extends App {
  def factorial(n: Int): Double = {
    var fact = 1.0
    if (n > 1) {
      for (k <- 2 to n) {
        fact *= k
      }
    }
    fact
  }

  def countBinominal(n: Int, k: Int): Double = {
    factorial(n) / (factorial(k) * factorial(n - k))
  }

  def probability(n: Int, k: Int): Double = {
    countBinominal(pow(2, k).toInt, n) * pow(0.25, n) * pow(0.75, pow(2, k) - n)
  }

  def mendelSecondLaw(k: Int, N: Int): Double = {
    val result = 1 - (0 until N).map(n => probability(n, k)).sum
    BigDecimal.valueOf(result).setScale(3, BigDecimal.RoundingMode.HALF_DOWN).doubleValue
  }

  val (n, k) = Reader.read()
  Writer.write(mendelSecondLaw(n, k).toString)
}
