package traits

import services.Helpers._

/**
  * Piece trait is extended by all piece models, it contains a shared functions between all of them
  */
trait Piece {


  /**
    * get all affected (attacked) positions in the chess board when trying to add Bishop piece (B)
    * check https://www.ichess.net/blog/chess-pieces-moves/ to know piece movements guide
    *
    * it dose'nt have a default implementation, as it is differ from piece to piece. Its implementation is inside each model
    *
    * @param index           [index where the chess piece will be inserted at]
    * @param mappingArray    [tuple with two maps to help in converting one dimensional array to two dimensional array and vice versa]
    * @param boardDimensions [tuple contains chess board dimensions (height, width)]
    * @return [list of integers contains the square positions will be attacked by the piece if it is inserted]
    */
  def getAffectedPositionInBoard(index: Int, mappingArray: (Map[String, Int], Map[Int, String]), boardDimensions: (Int, Int)): List[Int]


  /**
    * shared getter for all models
    *
    * @return _signature value
    */
  def signature: String

  /**
    * check if position in the chess board has a chess piece or attacked by other piece
    *
    * @param checkedIndex [index of the position to be checked (for the one dimensional array)]
    * @param board        [chess board to be checked]
    * @return [boolean]
    */
  def checkPosition(checkedIndex: Int, board: Map[Int, String]): Boolean = {
    var isPositionSafe: Boolean = true

    val boardPositionValue: String = board(checkedIndex)
    if (boardPositionValue != "-" && !isNull(boardPositionValue)) {
      isPositionSafe = false
    }

    isPositionSafe
  }

  /**
    * get all positions which will be attacked when insert the chess piece at its position and
    * then for each of these positions, check if it has other chess piece
    * if it has a chess piece, so you can't insert your chess piece at this index and the function returns false
    * otherwise the function return true and so you can insert your chess piece at this index
    *
    * @param board           [chess board to be checked]
    * @param index           [index where the piece will be inserted at]
    * @param mappingArray    [tuple with two maps to help in converting one dimensional array to two dimensional array and vice versa]
    * @param boardDimensions [tuple contains chess board dimensions (height, width)]
    * @return [boolean]
    */
  def validate(board: Map[Int, String], index: Int, boardDimensions: (Int, Int), mappingArray: (Map[String, Int], Map[Int, String])): Boolean = {

    var affectedIndexes: List[Int] = getAffectedPositionInBoard(index, mappingArray, boardDimensions)

    var isSafe: Boolean = true

    affectedIndexes.foreach(checkedIndex => {
      isSafe = checkPosition(checkedIndex, board)
      if (!isSafe) {
        return isSafe
      }
    })
    isSafe
  }


  /**
    * update the board, insert the piece at its index and fill the attacked positions from
    * this piece with null value
    *
    * @param board           [chess board to be updated]
    * @param index           [index where the piece will be inserted at]
    * @param boardDimensions [tuple contains chess board dimensions (height, width)]
    * @param mappingArray    [tuple with two maps to help in converting one dimensional array to two dimensional array and vice versa]
    * @return
    */
  def updateBoard(board: Map[Int, String], index: Int, boardDimensions: (Int, Int), mappingArray: (Map[String, Int], Map[Int, String])): Map[Int, String] = {
    var affectedIndexes: List[Int] = getAffectedPositionInBoard(index, mappingArray, boardDimensions)
    var updatedBoard: Map[Int, String] = board

    affectedIndexes.foreach(affectedIndex => {
      updatedBoard = updatedBoard + (affectedIndex -> null)
    })

    updatedBoard = updatedBoard + (index -> signature)

    updatedBoard
  }


  /**
    * a helper function just to split the position key into its dimensions
    *
    * @param index [string in format (x-y) dash separated]
    * @return [tuple with x and y (i,j) in the two dimensional array]
    */
  def getPieceXY(index: String): (Int, Int) = {
    val XY: Array[String] = index.split("-")
    val x: Int = XY(0).toInt
    val y: Int = XY(1).toInt

    (x, y)
  }
}