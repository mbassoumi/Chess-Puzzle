package models

import traits.Piece

class Bishop extends Piece {
  private val _signature: String = "B"

  def signature: String = _signature

  def getAffectedPositionInBoard(index: Int, mappingArray: (Map[String, Int], Map[Int, String]), boardDimensions: (Int, Int)): List[Int] = {
    var affectedPositions: List[Int] = List()

    val (reverseMapping: Map[String, Int], straightforwardMapping: Map[Int, String]) = mappingArray

    val (height: Int, width: Int) = boardDimensions

    val boardIndexKey: String = straightforwardMapping(index)

    val (iBoardIndex: Int, jBoardIndex: Int) = getPieceXY(boardIndexKey)

    var checkedIndex: String = ""

    var multiplier: Int = Math.max(height, width)

    for (i <- -(multiplier - 1) until multiplier) {
      checkedIndex = (iBoardIndex + i) + "-" + (jBoardIndex + i)
      if (reverseMapping.isDefinedAt(checkedIndex) && checkedIndex != boardIndexKey) {
        affectedPositions = reverseMapping(checkedIndex) :: affectedPositions
      }
      checkedIndex = (iBoardIndex + i) + "-" + (jBoardIndex - i)
      if (reverseMapping.isDefinedAt(checkedIndex) && checkedIndex != boardIndexKey) {
        affectedPositions = reverseMapping(checkedIndex) :: affectedPositions
      }
    }
    affectedPositions
  }
}
