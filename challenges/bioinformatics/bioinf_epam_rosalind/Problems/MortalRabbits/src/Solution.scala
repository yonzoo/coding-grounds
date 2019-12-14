package src

import src.utils.{Reader, Writer}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class Queue(var list: mutable.Buffer[BigInt]) {
  def update(m: BigInt) = {
    for (i <- 0 to list.length - 2) list(i) = list(i + 1)
    list(list.length - 1) = m
  }

  def pop(): BigInt = {
    list.head
  }
}

object Solution extends App {
  def mortalFibonacciRabbits(n: Int, k: Int): BigInt = {
    val queue = new Queue(ArrayBuffer.fill(k + 1)(0))
    var (prv, beforeprv, current) = (BigInt(0), BigInt(0), BigInt(0))
    for (i <- 0 to n) {
      if (i == 0 || i == 1 || i == 2) current = 1
      else current = prv + beforeprv - queue.pop()
      beforeprv = prv
      prv = current
      queue.update(current)
    }
    current
  }

  val (n, k) = Reader.read()
  Writer.write(mortalFibonacciRabbits(n, k).toString)
}
