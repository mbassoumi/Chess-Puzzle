package traits

import services.Helpers._

trait Piece {

  def getAffectedPositionInBoard(index: Int, mappingArray: (Map[String, Int], Map[Int, String]), boardDimensions: (Int, Int)): List[Int]

  def signature: String

  def checkPosition(checkedIndex: Int, board: Map[Int, String]): Boolean = {
    var isPositionSafe: Boolean = true

    val boardPositionValue: String = board(checkedIndex)
    if (boardPositionValue != "-" && !isNull(boardPositionValue)) {
      isPositionSafe = false
    }

    isPositionSafe
  }

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

  def updateBoard(board: Map[Int, String], index: Int, boardDimensions: (Int, Int), mappingArray: (Map[String, Int], Map[Int, String])): Map[Int, String] = {
    var affectedIndexes: List[Int] = getAffectedPositionInBoard(index, mappingArray, boardDimensions)
    var updatedBoard: Map[Int, String] = board

    affectedIndexes.foreach(affectedIndex => {
      updatedBoard = updatedBoard + (affectedIndex -> null)
    })

    updatedBoard = updatedBoard + (index -> signature)

    updatedBoard
  }


  def getPieceXY(index: String): (Int, Int) = {
    val XY: Array[String] = index.split("-")
    val x: Int = XY(0).toInt
    val y: Int = XY(1).toInt

    (x, y)
  }
}


/*

  def checkPosition(checkedIndex: String, pieceIndex: Int, mappingArray: (Map[String, Int], Map[Int, String]), board: Map[Int, String]): Boolean = {
    var isPositionSafe: Boolean = true

    val (reverseMapping: Map[String, Int], straightforwardMapping: Map[Int, String]) = mappingArray

    val pieceIndexKey: String = straightforwardMapping(pieceIndex)

    if (checkedIndex != pieceIndexKey && reverseMapping.isDefinedAt(checkedIndex)) {
      val boardPositionValue: String = board(reverseMapping(checkedIndex))
      if (boardPositionValue != "-" && !isNull(boardPositionValue)) {
        isPositionSafe = false
      }
    }

    isPositionSafe
  }
 */