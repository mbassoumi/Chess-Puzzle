import models._
import services.ChessHelpers._
import services.Helpers._

object Driver {
  def main(args: Array[String]): Unit = {

//    val inputPieces: List[String] = List("K", "K", "R")
//    val inputPieces: List[String] = List("R", "R", "N", "N", "N", "N")
    val inputPieces: List[String] = List("K", "K", "Q", "Q", "B", "B", "N")
//    val height: Int = 3
//    val width: Int = 3
//    val height: Int = 4
//    val width: Int = 4

    //3063828 in 59137804468ns
    val height: Int = 6
    val width: Int = 6
    val boardDimensions: (Int, Int) = (height, width)
    val length: Int = height * width

    val mappingArray = getMappingArray(height, width)

    val pieces = getPiecesWithItsCount(inputPieces)

    var safeBoards = time {
      getSafeBoards(length, pieces, boardDimensions, mappingArray)
    }
//    printSolutions(safeBoards, boardDimensions, length)

//    safeBoards.foreach(board => {
//      println(board)
//    })
////
//    var king: King = new King()
//    var rook: Rook = new Rook()
//    var knight: Knight = new Knight()
//    var queen: Queen = new Queen()
//    var bishp: Bishop = new Bishop()
//    var board = getEmptyChessBoard(length)
//
//    var updatedBoard = king.updateBoard(board, 2, mappingArray, boardDimensions)
//
//    updatedBoard.foreach(key => {
//      println(s"$key")
//    })

    //    var affectedIndexes: List[Int] = bishp.getAffectedPositionInBoard(1, mappingArray, boardDimensions)
    //    var affectedIndexes: List[Int] = queen.getAffectedPositionInBoard(4, mappingArray, boardDimensions)
    //    var affectedIndexes: List[Int] = knight.getAffectedPositionInBoard(2, mappingArray, boardDimensions)
    //    var affectedIndexes: List[Int] = rook.getAffectedPositionInBoard(4, mappingArray, boardDimensions)
    //    var affectedIndexes: List[Int] = king.getAffectedPositionInBoard(1, mappingArray, boardDimensions)
    //board: Map[Int, String], index: Int, boardDimensions: (Int, Int), mappingArray: (Map[String, Int], Map[Int, String])): List[Int] = {


    //    var affectedIndexes: List[Int] = king.validate22(board,1,boardDimensions,mappingArray)

    //    affectedIndexes.foreach(index => {
    //      println(s"index = $index")
    //    })

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