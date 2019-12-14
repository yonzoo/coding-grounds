package src

import scala.math.BigDecimal.RoundingMode
import src.utils.{Reader, Writer}

object Solution extends App {
  def dominantProbability(dom: Double, het: Double, rec: Double): Double = {
    val total = dom + het + rec
    val hhp = (het / total) * ((het - 1) / (total - 1))
    val hrp = (het / total) * (rec / (total - 1)) + (rec / total) * (het / (total - 1))
    val rrp = (rec / total) * ((rec - 1) / (total - 1))
    val rptotal = rrp + hhp * 1 / 4 + hrp * 1 / 2
    1 - rptotal
  }

  val dom :: het :: rec :: _ = Reader.read()
  Writer.write(dominantProbability(dom, het, rec))
}