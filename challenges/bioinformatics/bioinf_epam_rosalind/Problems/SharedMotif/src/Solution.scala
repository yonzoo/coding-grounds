package src

import src.utils.{Reader, Writer}

object Solution extends App {
  def findSharedMotif(src: List[String]): String = {
    val subs = src.head.inits.flatMap(_.tails).toSet
    subs.filter(el => src.count(s => s.contains(el)) == src.length).maxBy(_.length)
  }

  Writer.write(findSharedMotif(Reader.read()))
}