package utils

import vegas._

object Drawer extends App {

  def draw(s: Seq[Map[String, Int]], keyX: String, keyY: String): Unit = {
    Vegas("Per base sequence quality",  width=1200, height=400).
      withData(s).
      encodeX(keyX, Ordinal).
      encodeY(keyY, Quantitative).
      mark(Line).
      show
  }
}