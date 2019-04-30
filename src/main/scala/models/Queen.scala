package models

import traits.Piece

class Queen extends Piece {
  private val _signature: String = "Q"

  def signature: String = _signature


  def getAffectedPositionInBoard(index: Int, mappingArray: (Map[String, Int], Map[Int, String]), boardDimensions: (Int, Int)): List[Int] = {
    var affectedPositions: List[Int] = List()

    val (reverseMapping: Map[String, Int], straightforwardMapping: Map[Int, String]) = mappingArray

    val boardIndexKey: String = straightforwardMapping(index)

    val (height: Int, width: Int) = boardDimensions

    val (iBoardIndex: Int, jBoardIndex: Int) = getPieceXY(boardIndexKey)

    var checkedIndex: String = ""

    var multiplier: Int = Math.max(height, width)

    for (m <- 1 until multiplier) {
      for (i <- -1 to 1) {
        for (j <- -1 to 1) {
          checkedIndex = (iBoardIndex + (m * i)) + "-" + (jBoardIndex + (m * j))
          if (reverseMapping.isDefinedAt(checkedIndex) && checkedIndex != boardIndexKey) {
            affectedPositions = reverseMapping(checkedIndex) :: affectedPositions
          }
        }
      }
    }
    affectedPositions
  }
}
