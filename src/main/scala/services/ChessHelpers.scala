package services

import services.ChessBoardValidation._
import services.Helpers._

object ChessHelpers {

  /**
    * function which return tuple with two maps to help you with converting the one dimensional array
    * to two dimensional array and vice versa
    * the first map is called reverse which keyed by two dimensional array key and valued by one dimensional array key
    * for example
    * reverse("1-2") = 5 when height = 3 and width = 3
    * the second map is called straightforward which keyed by one dimensional array key and valued by two dimensional array key
    * for example
    * straightforward(5) = "1-2" when height = 3 and width = 3
    *
    * @param height [chess board height]
    * @param width  [chess board width]
    * @return [tuple with two maps]
    */
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


  /**
    * function that return list of distinct pieces signatures
    * and map keyed by piece signature and valued by number of this piece in the origin input
    *
    * @param inputPieces [list of signatures (input list)]
    * @return [tuple (distinct pieces signatures, count of each signature)]
    */
  def getPiecesWithItsCount(inputPieces: List[String]): (List[String], Map[String, Int]) = {
    var piecesCount: Map[String, Int] = Map()
    inputPieces.foreach(item => {
      if (piecesCount.isDefinedAt(item)) {
        piecesCount = piecesCount + (item -> (piecesCount(item) + 1))
      } else {
        piecesCount = piecesCount + (item -> 1)
      }
    })

    val pieces: List[String] = orderChessPieces(piecesCount)


    (pieces, piecesCount)
  }

  /**
    * order the chess pieces to reduce the execution time while generate the solution
    * by ordering the chess piece based on its range in attacking others
    * so queen is in the first element as it attack wide area, and king is in the last element as it attack tiny area
    *
    * @param piecesCount [map with pieces signatures as key]
    * @return [ordered chess pieces]
    */
  def orderChessPieces(piecesCount: Map[String, Int]): List[String] = {
    var orderedChessPieces: List[String] = List()

    if (piecesCount.isDefinedAt("K") && piecesCount("K") != 0) {
      orderedChessPieces = "K" :: orderedChessPieces
    }
    if (piecesCount.isDefinedAt("N") && piecesCount("N") != 0) {
      orderedChessPieces = "N" :: orderedChessPieces
    }
    if (piecesCount.isDefinedAt("R") && piecesCount("R") != 0) {
      orderedChessPieces = "R" :: orderedChessPieces
    }
    if (piecesCount.isDefinedAt("B") && piecesCount("B") != 0) {
      orderedChessPieces = "B" :: orderedChessPieces
    }
    if (piecesCount.isDefinedAt("Q") && piecesCount("Q") != 0) {
      orderedChessPieces = "Q" :: orderedChessPieces
    }

    orderedChessPieces
  }


  /**
    * initialize the chess board by filling it with dashes
    *
    * @param length [chess board height x chess board width]
    * @return [initialized chess board filled with dashes only]
    */
  def getEmptyChessBoard(length: Int): Map[Int, String] = {
    var board: Map[Int, String] = Map()

    for (i <- 0 until length) {
      board = board + (i -> "-")
    }
    board
  }


  /**
    * generate the solutions (safe boards) by a recursion function
    * the idea of the function is as following
    * 1- consider the first element of the pieces is the root
    * 2- place the piece in each position in the board
    * 3- for every placement call the recursion function again with new set of pieces remaining after drop the first piece from it
    * 4- check board safety at every insertion, and if it is not safe then skip it and all its children
    *
    * the trick to reduce its execution time is by using two array for pieces one that have the distinct pieces and the other contains count of each piece
    * by this, we avoid repeat the solution twice
    *
    * another trick is sorting the pieces based on their attacking wide area to reduce the possible positions for the next piece
    *
    * @param length          [chess board height * chess board width]
    * @param pieces          [tuple (list of distinct pieces, count of each piece in the origin input)]
    * @param boardDimensions [tuple contains chess board dimensions (height, width)]
    * @param mappingArray    [tuple with two maps to help in converting one dimensional array to two dimensional array and vice versa]
    * @param startIndex      [to brunning the search]
    * @param inputBoard      [chess board]
    * @param results         [list of solutions (safe chess boards)]
    * @param isFirstElement  [to initialize the chess board on search]
    * @return
    */
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

  /**
    * print the solutions (safe chess boards) in the console
    *
    * @param safeBoards      [list of solutions]
    * @param boardDimensions [dimensions of chess board (height and width)]
    * @param length          [height * width]
    */
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
