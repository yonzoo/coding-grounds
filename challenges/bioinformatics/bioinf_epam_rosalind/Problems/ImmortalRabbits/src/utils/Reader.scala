package src.utils

import scala.io.Source

object Reader {
  def read(): List[Int] = {
    val lines = Source.fromFile("res/dataset.txt").getLines.toList
    lines.head.split(" ").map(_.toInt).toList
  }
}
