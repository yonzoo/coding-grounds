package src.utils

import scala.io.Source

object Reader {
  def read(): (Int, Int) = {
    val data = Source.fromFile("res/dataset.txt")
    val lines = data.getLines().toList
    val params = lines.head.split(" ").map(_.toInt).toList
    (params.head, params(1))
  }
}
