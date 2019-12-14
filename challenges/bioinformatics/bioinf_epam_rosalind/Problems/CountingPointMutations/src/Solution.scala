package src

import src.utils.{Reader, Writer}

object Solution extends App {
  def getPointMutationCount(src: String, mutant: String): Int = {
    (src zip mutant).count(el => el._1 != el._2)
  }

  val (src, mutant) = Reader.read()
  Writer.write(getPointMutationCount(src, mutant).toString)
}
