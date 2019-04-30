package services

object ChessHelpers {

  def getMappingArray(height: Int, width: Int): (Map[Any, Any], Map[Any, Any]) = {

    var reverseMapping: Map[Any, Any] = Map()
    var straightforwardMapping: Map[Any, Any] = Map()
    var index: Int = 0
    for (i <- 0 until height) {
      for (j <- 0 until width) {
        reverseMapping = reverseMapping + (s"$i-$j" -> index)
        straightforwardMapping = straightforwardMapping + (index -> s"$i-$j")
        index = index + 1
      }
    }

    (reverseMapping, straightforwardMapping)
  }


  def getPiecesWithItsCount(inputPieces: List[String]): (List[String], Map[String, Int]) = {
    var piecesCount: Map[String, Int] = Map()
    var pieces: List[String] = List()
    inputPieces.foreach(item => {
      if (piecesCount.isDefinedAt(item)) {
        piecesCount = piecesCount + (item -> (piecesCount(item) + 1))
      } else {
        pieces = item :: pieces
        piecesCount = piecesCount + (item -> 1)
      }
    })
    (pieces, piecesCount)
  }


  def getEmptyChessBoard(length: Int): Map[Int, String] = {
    var board: Map[Int, String] = Map()

    for (i <- 0 until length) {
      board = board + (i -> "-")
    }
    board
  }


  def getSafeBoards(length: Int,
                    pieces: (List[String], Map[String, Int]),
                    height: Int,
                    width: Int,
                    mappingArray: (Map[Any, Any], Map[Any, Any]),
                    startIndex: Int = 0,
                    inputBoard: Map[Int, String] = Map(),
                    results: List[Map[Int, String]] = List(),
                    isFirstElement: Boolean = true
                   ): List[Map[Int, String]] = {

    var boardResults: List[Map[Int, String]] = results
    var (piecesElements, piecesCount) = pieces

    var board: Map[Int, String] = inputBoard
    var initialBoard: Map[Int, String] = board

    val piece: String = piecesElements.head

    var newStartIndex = startIndex
    var newPiecesCount: Map[String, Int] = piecesCount + (piece -> (piecesCount(piece) - 1))

    var isBoardSafe: Boolean = true

    var recursivePiecesElements: List[String] = List()

    if (isFirstElement) {
      board = getEmptyChessBoard(length)
      initialBoard = board
    }
    for (i <- startIndex until length) {

      if (board(i) == "-") {
        board = board + (i -> piece)
        if (newPiecesCount(piece) == 0) {
          recursivePiecesElements = piecesElements.filter(_ != piece)
          newStartIndex = 0
        } else {
          recursivePiecesElements = piecesElements
          newStartIndex = i + 1
        }

        if (recursivePiecesElements.nonEmpty) {
          boardResults = getSafeBoards(length, (recursivePiecesElements, newPiecesCount), height, width, mappingArray, newStartIndex, board, boardResults, false)
        } else {
          boardResults = board :: boardResults
        }

        board = initialBoard
      }
    }

    boardResults
  }
}
