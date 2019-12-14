package src.utils

import java.io.{BufferedWriter, File, FileWriter}

import scala.math.BigDecimal.RoundingMode

object Writer {
  def write(s: String): Unit = {
    val file = new File("res/result.txt")
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(s)
    bw.close()
  }
}
