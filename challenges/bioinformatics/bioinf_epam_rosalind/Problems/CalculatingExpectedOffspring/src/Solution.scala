package src

import src.utils.{Reader, Writer}

object Solution extends App {
  def getAverage(src: String): Double = {
    val arr = src.split(" ").toList
    (arr.slice(0, 3).map(2 * _.toDouble) :+ 1.5 * arr(3).toDouble :+ arr(4).toDouble).sum
  }

  Writer.write(getAverage(Reader.read()).toString)
}
