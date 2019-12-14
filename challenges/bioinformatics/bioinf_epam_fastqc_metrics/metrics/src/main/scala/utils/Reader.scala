package utils

import scala.collection.mutable.ListBuffer
import scala.io.Source

object Reader {
  def read(path: String): (List[String], List[String]) = {
    //function returns a tuple where the first element is the list of qualities sequences and the second is the list of sequences themselves
    val sequencesBuff = new ListBuffer[String]
    val qualitiesBuff = new ListBuffer[String]
    val bufferedSource = Source.fromFile(path)
    val lines = bufferedSource.getLines()
    lines.filter(line => (!line.startsWith("@")) && (!line.startsWith("+"))).zipWithIndex.foreach {
      case (line, index) => {
        if (index % 2 == 0) sequencesBuff += line
        else qualitiesBuff += line
      }
    }
    bufferedSource.close
    (qualitiesBuff.toList, sequencesBuff.toList)
  }
}
