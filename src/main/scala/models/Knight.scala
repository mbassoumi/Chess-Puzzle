package models

import traits.Piece

class Knight extends Piece {

  private val _signature: String = "N"

  /**
    * getter for _signature variable
    *
    * @return [this._signature value]
    */
  def signature: String = _signature


  /**
    * get all affected (attacked) positions in the chess board when trying to add Knight piece (N)
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
