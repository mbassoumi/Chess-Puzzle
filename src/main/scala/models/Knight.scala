package models

import traits.Piece

class Knight extends Piece {
  private val _signature: String = "N"

  def signature: String = _signature

  def getAffectedPositionInBoard(index: Int, mappingArray: (Map[String, Int], Map[Int, String]), boardDimensions: (Int, Int)): List[Int] = {
    var affectedPositions: List[Int] = List()

    val (reverseMapping: Map[String, Int], straightforwardMapping: Map[Int, String]) = mappingArray

    val boardIndexKey: String = straightforwardMapping(index)

    val (iBoardIndex: Int, jBoardIndex: Int) = getPieceXY(boardIndexKey)

    var checkedIndex: String = ""

    var j: Int = 0

    for (i <- -2 to 2) {
      if (i != 0) {
        j = 3 - Math.abs(i)
        checkedIndex = (iBoardIndex + i) + "-" + (jBoardIndex + j)
        if (reverseMapping.isDefinedAt(checkedIndex) && checkedIndex != boardIndexKey) {
          affectedPositions = reverseMapping(checkedIndex) :: affectedPositions
        }
        checkedIndex = (iBoardIndex + i) + "-" + (jBoardIndex - j)
        if (reverseMapping.isDefinedAt(checkedIndex) && checkedIndex != boardIndexKey) {
          affectedPositions = reverseMapping(checkedIndex) :: affectedPositions
        }

      }
    }

    affectedPositions
  }
}
