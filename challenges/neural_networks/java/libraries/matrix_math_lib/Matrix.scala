package libraries.matrix_math_lib

import matrix_math_lib.exceptions.RowsAndColumnsAreNotEqual

class Matrix(var rows: Int, var cols: Int) {
  var data: Array[Array[Double]] = Array.ofDim[Double](rows, cols)
  for {
    i <- 0 until rows
    j <- 0 until cols
  } {
    data(i)(j) = 0
  }

  def randomize() = {
    for {
      i <- 0 until rows
      j <- 0 until cols
    } {
      data(i)(j) = Math.random() * 2 - 1
    }
  }

  def multiply(n: Double): Unit = {
    for {
      i <- 0 until rows
      j <- 0 until cols
    } {
      data(i)(j) = data(i)(j) * n
    }
  }

  @throws(classOf[RowsAndColumnsAreNotEqual])
  def multiply(m: Matrix): Unit = {
    if ((rows == m.rows) && (cols == m.cols)) {
      for {
        i <- 0 until rows
        j <- 0 until cols
      } {
        data(i)(j) *= m.data(i)(j)
      }
    }
    else {
      throw new RowsAndColumnsAreNotEqual("Rows and columns must be equal\nFirst matrix is " + rows + " x " + cols
        + " but second matrix is " + m.rows + " x " + m.cols)
    }
  }

  def add(n: Int): Unit = {
    for {
      i <- 0 until rows
      j <- 0 until cols
    } {
      data(i)(j) += n
    }
  }

  @throws(classOf[RowsAndColumnsAreNotEqual])
  def add(m: Matrix): Unit = {
    if ((rows == m.rows) && (cols == m.cols)) {
      for {
        i <- 0 until rows
        j <- 0 until cols
      } {
        data(i)(j) += m.data(i)(j)
      }
    }
    else {
      throw new RowsAndColumnsAreNotEqual("Rows and columns must be equal\nFirst matrix is " + rows + " x " + cols
        + " but second matrix is " + m.rows + " x " + m.cols)
    }
  }

  def toArray(): Array[Double] = {
    val arr: Array[Double] = Array(rows * cols)
    for {
      i <- 0 until rows
      j <- 0 until cols
      k <- 0 until rows * cols
    } {
      arr(k) = data(i)(j)
    }
    arr
  }


  def printMatrix(): Unit = {
    data.foreach(row => {
      row.foreach(elem => print(" " + elem + " "))
      println()
    })
  }

  def map(func: Double => Double): Unit = {
    for {
      i <- 0 until rows
      j <- 0 until cols
    } {
      val value = data(i)(j)
      data(i)(j) = func(value)
    }
  }
}
