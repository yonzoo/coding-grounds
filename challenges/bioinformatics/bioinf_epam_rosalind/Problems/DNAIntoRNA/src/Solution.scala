package src

import src.utils.{Reader, Writer}

object Solution extends App {
  def transformDnaToRna = (s: String) => s.replaceAll("T", "U")

  Writer.write(transformDnaToRna(Reader.read()))
}
