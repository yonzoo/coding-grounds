package src

import src.utils.{Reader, Writer}

object Solution extends App {
  def reverseComplement(s: String) = {
    s.map {
        case 'A' => 'T'
        case 'T' => 'A'
        case 'C' => 'G'
        case 'G' => 'C'
    }.reverse
  }

  Writer.write(reverseComplement(Reader.read()))
}
