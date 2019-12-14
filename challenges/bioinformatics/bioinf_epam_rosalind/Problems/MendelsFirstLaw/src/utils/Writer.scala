package src.utils

import java.io.{BufferedWriter, File, FileWriter}

import scala.math.BigDecimal.RoundingMode

object Writer {
  def write(result: Double): Unit = {
    val file = new File("res/result.txt")
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(BigDecimal.valueOf(result).setScale(5, RoundingMode.HALF_UP).doubleValue.toString)
    bw.close()
  }
}