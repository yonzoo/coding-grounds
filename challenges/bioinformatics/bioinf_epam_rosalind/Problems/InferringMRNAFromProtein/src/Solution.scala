package src

import src.utils.{Reader, Writer}

object Solution extends App {
  def inferringMrna(src: String): Int = {
    val MOD_FACTOR = 1000000
    val subs = Map(
      "F" -> 2,
      "L" -> 6,
      "S" -> 6,
      "Y" -> 2,
      "C" -> 2,
      "W" -> 1,
      "P" -> 4,
      "H" -> 2,
      "Q" -> 2,
      "R" -> 6,
      "I" -> 3,
      "M" -> 1,
      "T" -> 4,
      "N" -> 2,
      "K" -> 2,
      "V" -> 4,
      "A" -> 4,
      "D" -> 2,
      "E" -> 2,
      "G" -> 4
    )
    (src.foldLeft(1)((m: Int, n: Char) => m * subs.getOrElse(n.toString, 1) % MOD_FACTOR) * 3) % MOD_FACTOR
  }

  Writer.write(inferringMrna(Reader.read()).toString)
}
