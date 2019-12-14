package libraries.matrix_math_lib.exceptions

class RowsAndColumnsAreNotCorresponding(s: String) extends Exception(s) {
  System.err.println(s)
}
