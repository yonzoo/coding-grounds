package src.utils

import scala.collection.mutable
import scala.io.Source

object Reader {
  def read(): List[String] = {
    val data = Source.fromFile("res/dataset.txt")
    val lines = data.getLines().toList
    lines.map(line => if (line.startsWith(">")) ">" else line).mkString("").split(">").filter(_.length > 0).toList
  }
}