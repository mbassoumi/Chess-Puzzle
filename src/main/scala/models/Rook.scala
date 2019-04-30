package models

import traits.Piece

class Rook extends Piece {

  private val _signature: String = "R"

  /**
    * getter for _signature variable
    *
    * @return [this._signature value]
    */
  def signature: String = _signature


  /**
    * get all affected (attacked) positions in the chess board when trying to add Rook piece (R)
    * check https://www.ichess.net/blog/chess-pieces-moves/ to know piece movements guide
    *
    * @param index           [index where the chess piece will be inserted at]
    * @param mappingArray    [tuple with two maps to help in converting one dimensional array to two dimensional array and vice versa]
    * @param boardDimensions [tuple contains chess board dimensions (height, width)]
    * @return [list of integers contains the square positions will be attacked by the piece if it is inserted]
    */
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
