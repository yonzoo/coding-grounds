package src.utils

import scala.io.Source

object Reader {
  def read(): List[(String, String)] = {
    val lines = Source.fromFile("res/dataset.txt").getLines().toList
    val keys = lines.filter(_.startsWith(">")).map(el => el.slice(1, el.length))
    val values = lines.map(line => if (line.startsWith(">")) ">" else line).mkString("").split(">").filter(_.length > 0)
    keys.zip(values)
  }
}
