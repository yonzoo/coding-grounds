package src

import src.utils.{Reader, Writer}

object Solution extends App {
  def getAllIndexes(str: String, substr: String, startIndex: Int = 0): List[Int] = str.indexOf(substr, startIndex) match {
    case -1 => List()
    case targetIndex => List(targetIndex + 1) ++ getAllIndexes(str, substr, targetIndex + 1)
  }

  val (str, substr) = Reader.read()
  Writer.write(getAllIndexes(str, substr).mkString(" "))
}
