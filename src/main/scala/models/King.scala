package models

import traits.Piece

class King extends Piece {
  private val _signature: String = "K"

  def signature: String = _signature


  def getAffectedPositionInBoard(index: Int, mappingArray: (Map[String, Int], Map[Int, String]), boardDimensions: (Int, Int)): List[Int] = {
    var affectedPositions: List[Int] = List()

    val (reverseMapping: Map[String, Int], straightforwardMapping: Map[Int, String]) = mappingArray

    val boardIndexKey: String = straightforwardMapping(index)

    val (iBoardIndex: Int, jBoardIndex: Int) = getPieceXY(boardIndexKey)

    var checkedIndex: String = ""

    for (i <- -1 to 1) {
      for (j <- -1 to 1) {
        checkedIndex = (iBoardIndex + i) + "-" + (jBoardIndex + j)
        if (reverseMapping.isDefinedAt(checkedIndex) && checkedIndex != boardIndexKey) {
          affectedPositions = reverseMapping(checkedIndex) :: affectedPositions
        }
      }
    }
    affectedPositions
  }

  //  def updateBoard(index: Int, board: Map[Int, String], mappingArray: (Map[String, Int], Map[Int, String])): Map[Int, String] = {
  //    var updatedBoard: Map[Int, String] = board
  //    val (reverseMapping: Map[String, Int], straightforwardMapping: Map[Int, String]) = mappingArray
  //
  //    var checkedIndex: String = ""
  //    for (i <- -1 to 1) {
  //      for (j <- -1 to 1) {
  //        checkedIndex = (iBoardIndex + i) + "-" + (jBoardIndex + j)
  //        isSafe = checkPosition(checkedIndex, index, mappingArray, board)
  //        if (!isSafe) {
  //          return isSafe
  //        }
  //      }
  //    }
  //  }
}
