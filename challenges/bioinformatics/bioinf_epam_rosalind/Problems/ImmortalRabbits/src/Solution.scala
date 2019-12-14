package src

import src.utils.{Reader, Writer}

object Solution extends App {
  def wascallyWabbits(n: Int, k: Int): Int = {
    if (n == 1 || n == 2) 1 else wascallyWabbits(n - 1, k) + k * wascallyWabbits(n - 2, k)
  }

  val params = Reader.read()
  Writer.write(wascallyWabbits(params.head, params(1)).toString)
}
