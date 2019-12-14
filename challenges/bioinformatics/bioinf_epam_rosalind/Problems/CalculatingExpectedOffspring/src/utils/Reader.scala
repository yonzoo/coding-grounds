package src.utils

import scala.collection.mutable
import scala.io.Source

object Reader {
  def read(): String = {
    val data = Source.fromFile("res/dataset.txt")
    val lines = data.getLines().toList
    lines.head
  }
}
