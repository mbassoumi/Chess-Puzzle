package traits

import services.Helpers._

trait Piece {

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


  def getPieceXY(index: String): (Int, Int) = {
    val XY: Array[String] = index.split("-")
    val x: Int = XY(0).toInt
    val y: Int = XY(1).toInt

    (x, y)
  }
}
