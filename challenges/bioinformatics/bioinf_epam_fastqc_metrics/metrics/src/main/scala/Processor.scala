import scala.collection.mutable.ListBuffer

object Processor {
  val ACSII_TO_SCORE_DIFFERENCE = 33
  val MAX_SCORE = 93
  val MAX_GC_CONTENT = 100

  def computeContentIn(dna: String): Int = {
    val number: Double = dna.count(char => (char == 'G') || (char == 'C'))
    val percent = number / dna.length * MAX_GC_CONTENT
    Math.round(percent).toInt
  }

  def computeAverageReadScore(str: String): Int = Math.round(str.map(charToScore).sum / str.length)

  def charToScore(c: Char): Int = {
    c.toInt - ACSII_TO_SCORE_DIFFERENCE
  }

  def getQualitiesPerBase(qualities: List[String]): List[Int] = {
    val maxLength = qualities.maxBy(_.length).length
    val averagesPerBase: Array[Int] = Array.fill(maxLength)(0)
    val elementsPerBase: Array[Int] = Array.fill(maxLength)(0)
    qualities.foreach(quality => {
      for (j <- quality.indices) {
        averagesPerBase(j) += charToScore(quality(j))
        elementsPerBase(j) += 1
      }
    })
    for (i <- averagesPerBase.indices) {
      if (elementsPerBase(i) > 0)
        averagesPerBase(i) /= elementsPerBase(i)
    }
    averagesPerBase.toList
  }

  def getQualitiesPerSequence(qualities: List[String]): Map[Int, Int] = {
    qualities.map(computeAverageReadScore).groupBy(identity).mapValues(_.size)
  }

  def getGcContents(sequences: List[String]): List[Int] = {
    val gcContents = Array.fill(MAX_GC_CONTENT)(0)
    sequences.foreach(seq => {
      gcContents(computeContentIn(seq)) += 1
    })
    gcContents.toList
  }
}
