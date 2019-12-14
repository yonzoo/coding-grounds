package src.utils

import scala.io.Source

object Reader {
  def read(): (Int, Int) = {
    val lines = Source.fromFile("res/dataset.txt")
    val res = lines.getLines().toList.head.split(" ")
    (res.head.toInt, res(1).toInt)
  }
}