package src

import src.utils.{Reader, Writer}

object Solution extends App {
  def gcContent(s: String): Double = {
    (s.filter(c => c == 'G' || c == 'C').length.toDouble / s.length.toDouble) * 100
  }

  def getTopGcContentSequence(fastaStrings: List[(String, String)]): (String, Double) = {
    fastaStrings.map({
      case (seqId, sequence) => (seqId, gcContent(sequence))
    }).maxBy(_._2)
  }

  val (sequence, gcContent) = getTopGcContentSequence(Reader.read())
  Writer.write(sequence, gcContent)
}

