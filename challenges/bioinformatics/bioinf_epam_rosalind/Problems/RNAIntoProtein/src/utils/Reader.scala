package src.utils

import scala.io.Source

object Reader {
  def read(): String = {
    val data = Source.fromFile("res/dataset.txt")
    data.getLines().toList.head
  }
}
