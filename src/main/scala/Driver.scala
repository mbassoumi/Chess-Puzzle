import models._
import services.ChessHelpers._
import services.Helpers._

object Driver {
  def main(args: Array[String]): Unit = {

    val inputPieces: List[String] = List("K", "K", "R")
    //    val inputPieces: List[String] = List("R", "R", "N", "N", "N", "N")
    //    val inputPieces: List[String] = List("K", "K", "Q", "Q", "B", "B", "N")
    val height: Int = 3
    val width: Int = 3
    //    val height: Int = 4
    //    val width: Int = 4

    //3063828 in 38481887419ns
    //    val height: Int = 7
    //    val width: Int = 7
    val boardDimensions: (Int, Int) = (height, width)
    val length: Int = height * width

    val mappingArray = getMappingArray(height, width)

    val pieces = getPiecesWithItsCount(inputPieces)

    var safeBoards = time {
      getSafeBoards(length, pieces, boardDimensions, mappingArray)
    }
    //    printSolutions(safeBoards, boardDimensions, length)

    println(safeBoards.length)
  }

}