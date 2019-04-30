import models.King
import services.ChessHelpers._
import services.Helpers._

object Driver {
  def main(args: Array[String]): Unit = {

    val inputPieces: List[String] = List("K", "K", "R")
    val height: Int = 2
    val width: Int = 2
    val boardDimensions: (Int, Int) = (height, width)
    val length: Int = height * width

    val mappingArray = getMappingArray(height, width)

    val pieces = getPiecesWithItsCount(inputPieces)

    var safeBoards = time {
      getSafeBoards(length, pieces, boardDimensions, mappingArray)
    }

    //    var cc = majd()
    //println(cc)
    println(safeBoards.length)
    //    safeBoards.foreach(board => {
    //      println(board)
    //    })
  }

  def majd(): Int = {
    for (i <- 7 to 10) {
      return i
    }
    4
  }

}