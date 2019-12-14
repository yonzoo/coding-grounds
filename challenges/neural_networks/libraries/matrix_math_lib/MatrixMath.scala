package libraries.matrix_math_lib

import matrix_math_lib.exceptions.{RowsAndColumnsAreNotCorresponding, RowsAndColumnsAreNotEqual}

object MatrixMath {
  def multiply(m: Matrix, n: Double): Matrix = {
    val result = new Matrix(m.rows, m.cols)
    for {
      i <- 0 until m.rows
      j <- 0 until m.cols
    } {
      result.data(i)(j) = m.data(i)(j) * n
    }
    result
  }

  @throws(classOf[RowsAndColumnsAreNotEqual])
  def multiply(fstMtx: Matrix, scnMtx: Matrix): Matrix = {
    if ((fstMtx.rows == scnMtx.rows) && (fstMtx.cols == scnMtx.cols)) {
      val result = new Matrix(fstMtx.rows, fstMtx.cols)
      for {
        i <- 0 until fstMtx.rows
        j <- 0 until fstMtx.cols
      } {
        result.data(i)(j) = fstMtx.data(i)(j) * scnMtx.data(i)(j)
      }
      result
    }
    else {
      throw new RowsAndColumnsAreNotEqual("Rows and columns must be equal\nFirst matrix is " + fstMtx.rows + " x " + fstMtx.cols
        + " but second matrix is " + scnMtx.rows + " x " + scnMtx.cols)
    }
  }

  @throws(classOf[RowsAndColumnsAreNotCorresponding])
  def makeMatrixProduct(fstMtx: Matrix, scnMtx: Matrix): Matrix = {
    if (fstMtx.cols == scnMtx.rows) {
      val result = new Matrix(fstMtx.rows, scnMtx.cols)
      for {
        i <- 0 until fstMtx.rows
        j <- 0 until scnMtx.cols
      } {
        result.data(i)(j) = fstMtx.data(i).zip(scnMtx.data.map(_(j))).map { case (a, b) => a * b }.sum
      }
      result
    }
    else {
      throw new RowsAndColumnsAreNotCorresponding("First matrix is " + fstMtx.rows + " x " + fstMtx.cols
        + " but second matrix is " + scnMtx.rows + " x " + scnMtx.cols + "\n"
        + "Number of columns in the first matrix must be equal to the number of rows in the second")
    }
  }

  def add(m: Matrix, n: Int): Matrix = {
    val result = new Matrix(m.rows, m.cols)
    for {
      i <- 0 until m.rows
      j <- 0 until m.cols
    } {
      result.data(i)(j) += n
    }
    result
  }

  @throws(classOf[RowsAndColumnsAreNotEqual])
  def add(fstMtx: Matrix, scnMtx: Matrix): Matrix = {
    if ((fstMtx.rows == scnMtx.rows) && (fstMtx.cols == scnMtx.cols)) {
      val result = new Matrix(fstMtx.rows, fstMtx.cols)
      for {
        i <- 0 until fstMtx.rows
        j <- 0 until fstMtx.cols
      } {
        result.data(i)(j) = fstMtx.data(i)(j) + scnMtx.data(i)(j)
      }
      result
    }
    else {
      throw new RowsAndColumnsAreNotEqual("Rows and columns must be equal\nFirst matrix is " + fstMtx.rows + " x " + fstMtx.cols
        + " but second matrix is " + scnMtx.rows + " x " + scnMtx.cols)
    }
  }

  @throws(classOf[RowsAndColumnsAreNotEqual])
  def substract(fstMtx: Matrix, scnMtx: Matrix): Matrix = {
    if ((fstMtx.rows == scnMtx.rows) && (fstMtx.cols == scnMtx.cols)) {
      val result = new Matrix(fstMtx.rows, fstMtx.cols)
      for {
        i <- 0 until result.rows
        j <- 0 until result.cols
      } {
        result.data(i)(j) = fstMtx.data(i)(j) - scnMtx.data(i)(j)
      }
      result
    }
    else {
      throw new RowsAndColumnsAreNotEqual("Rows and columns must be equal\nFirst matrix is " + fstMtx.rows + " x " + fstMtx.cols
        + " but second matrix is " + scnMtx.rows + " x " + scnMtx.cols)
    }
  }

  def T(m: Matrix): Matrix = {
    val result = new Matrix(m.cols, m.rows)
    for {
      i <- 0 until m.rows
      j <- 0 until m.cols
    } {
      result.data(j)(i) = m.data(i)(j)
    }
    result
  }

  def fromArray(arr: Array[Double]): Matrix = {
    val newMatrix = new Matrix(arr.length, 1)
    for (i <- arr.indices) {
      newMatrix.data(i)(0) = arr(i)
    }
    newMatrix
  }

  def map(matrix: Matrix, func: Double => Double): Matrix = {
    val result = new Matrix(matrix.rows, matrix.cols)
    for {
      i <- 0 until result.rows
      j <- 0 until result.cols
    } {
      val value = matrix.data(i)(j)
      result.data(i)(j) = func(value)
    }
    result
  }
}
