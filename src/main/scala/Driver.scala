import services.ChessHelpers._

object Driver {
  def main(args: Array[String]): Unit = {

    val inputPieces: List[String] = List("K", "K", "R")
    val height: Int = 2
    val width: Int = 2
    val length: Int = height * width

    val mappingArray = getMappingArray(height, width)

    val pieces = getPiecesWithItsCount(inputPieces)

//    var board = getEmptyChessBoard(length)

//    var list = inputPieces

    var safeBoards = getSafeBoards(length,pieces,height,width,mappingArray)

    println(safeBoards.length)
    safeBoards.foreach(board => {
      println(board)
    })
//    println(safeBoards.length)
//    println(list.filter(_ != "R"))
  }
}