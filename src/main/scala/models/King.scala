package models

import traits.Piece

class King extends Piece {
  private val _signature: String = "K"

  def signature: String = _signature

  def validate(board: Map[Int, String], index: Int, boardDimensions: (Int, Int), mappingArray: (Map[String, Int], Map[Int, String])): Boolean = {
    var isSafe: Boolean = true
    val (reverseMapping: Map[String, Int], straightforwardMapping: Map[Int, String]) = mappingArray
    val boardIndexKey: String = straightforwardMapping(index)
    val (iBoardIndex: Int, jBoardIndex: Int) = getPieceXY(boardIndexKey)

    var checkedIndex: String = ""
    for (i <- -1 to 1) {
      for (j <- -1 to 1) {
        checkedIndex = (iBoardIndex + i) + "-" + (jBoardIndex + j)
        isSafe = checkPosition(checkedIndex, index, mappingArray, board)
        if (!isSafe) {
          return isSafe
        }
      }
    }
    isSafe
  }

  def updateBoard(index: Int, board: Map[Int, String], mappingArray: (Map[String, Int], Map[Int, String])): Map[Int, String] = {
    var updatedBoard: Map[Int, String] = board
    val (reverseMapping: Map[String, Int], straightforwardMapping: Map[Int, String]) = mappingArray

    var checkedIndex: String = ""
    for (i <- -1 to 1) {
      for (j <- -1 to 1) {
        checkedIndex = (iBoardIndex + i) + "-" + (jBoardIndex + j)
        isSafe = checkPosition(checkedIndex, index, mappingArray, board)
        if (!isSafe) {
          return isSafe
        }
      }
    }
  }
}
