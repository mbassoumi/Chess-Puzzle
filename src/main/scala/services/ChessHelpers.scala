package services

import services.ChessBoardValidation._
import services.Helpers._

object ChessHelpers {

  def getMappingArray(height: Int, width: Int): (Map[String, Int], Map[Int, String]) = {

    var reverseMapping: Map[String, Int] = Map()
    var straightforwardMapping: Map[Int, String] = Map()
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
                    boardDimensions: (Int, Int),
                    mappingArray: (Map[String, Int], Map[Int, String]),
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


    var recursivePiecesElements: List[String] = List()

    if (isFirstElement) {
      board = getEmptyChessBoard(length)
      initialBoard = board
    }
    for (i <- startIndex until length) {

      if (willBoardBeSafe(board, i, piece, boardDimensions, mappingArray)) {
        //        board = board + (i -> piece)
        board = updateBoard(board, i, piece, boardDimensions, mappingArray)
        if (newPiecesCount(piece) == 0) {
          recursivePiecesElements = piecesElements.filter(_ != piece)
          newStartIndex = 0
        } else {
          recursivePiecesElements = piecesElements
          newStartIndex = i + 1
        }

        if (recursivePiecesElements.nonEmpty) {
          boardResults = getSafeBoards(length, (recursivePiecesElements, newPiecesCount), boardDimensions, mappingArray, newStartIndex, board, boardResults, false)
        } else {
          boardResults = board :: boardResults
        }

        board = initialBoard
      }
    }

    boardResults
  }

  def printSolutions(safeBoards: List[Map[Int, String]], boardDimensions: (Int, Int), length: Int): Unit = {
    val (height, width) = boardDimensions
    var positionValue: String = null

    safeBoards.foreach(board => {
      for (i <- 0 until length) {
        if (i % width == 0) {
          println("")
        }
        positionValue = board(i)
        if (isNull(positionValue)) {
          positionValue = "-"
        }
        print(s"  $positionValue  ")
      }

      println("")
      println("")
      println("---------------------------------------------------------")
    })
  }
}
