import utils.Reader
import utils.Drawer
import utils.Constants

object Controller extends App {
  val (qualities, sequences) = Reader.read("res/dataset.fastq")
  val qualitiesPerBase = Processor.getQualitiesPerBase(qualities).zipWithIndex.map {
    case (score, index) => Map(Constants.POSITION_IN_READ -> (index + 1), Constants.PHRED_SCORE -> score)
  }
  val qualitiesPerSequence = Processor.getQualitiesPerSequence(qualities).map {
    case (scores, times) => Map(Constants.PHRED_SCORE -> scores, Constants.NUMBER_OF_SEQUENCES -> times)
  }.toSeq
  val gcContents = Processor.getGcContents(sequences).zipWithIndex.map {
    case (number, gcContent) => Map(Constants.NUMBER_OF_SEQUENCES -> number, Constants.GC_CONTENT -> (gcContent + 1))
  }
  Drawer.draw(qualitiesPerBase, Constants.POSITION_IN_READ, Constants.PHRED_SCORE)
  Drawer.draw(qualitiesPerSequence, Constants.PHRED_SCORE, Constants.NUMBER_OF_SEQUENCES)
  Drawer.draw(gcContents, Constants.GC_CONTENT, Constants.NUMBER_OF_SEQUENCES)
}
