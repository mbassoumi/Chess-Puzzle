package models

import traits.Piece

class Rook extends Piece {
  private val _signature: String = "R"

  def signature: String = _signature


  def getAffectedPositionInBoard(index: Int, mappingArray: (Map[String, Int], Map[Int, String]), boardDimensions: (Int, Int)): List[Int] = {
    var affectedPositions: List[Int] = List()

    val (reverseMapping: Map[String, Int], straightforwardMapping: Map[Int, String]) = mappingArray

    val boardIndexKey: String = straightforwardMapping(index)

    val (iBoardIndex: Int, jBoardIndex: Int) = getPieceXY(boardIndexKey)

    val (height: Int, width: Int) = boardDimensions

    var checkedIndex: String = ""

    for (i <- -jBoardIndex until (width - jBoardIndex)) {
      checkedIndex = iBoardIndex + "-" + (jBoardIndex + i)
      if (reverseMapping.isDefinedAt(checkedIndex) && checkedIndex != boardIndexKey) {
        affectedPositions = reverseMapping(checkedIndex) :: affectedPositions
      }
    }

    for (i <- -iBoardIndex until (height - iBoardIndex)) {
      checkedIndex = (iBoardIndex + i) + "-" + jBoardIndex
      if (reverseMapping.isDefinedAt(checkedIndex) && checkedIndex != boardIndexKey) {
        affectedPositions = reverseMapping(checkedIndex) :: affectedPositions
      }
    }


    affectedPositions
  }
}
