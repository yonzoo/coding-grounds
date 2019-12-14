package src.utils

import scala.io.Source

object Reader {
  def read(): String = {
    val lines = Source.fromFile("res/dataset.txt").getLines.toList
    lines.head
  }
}
