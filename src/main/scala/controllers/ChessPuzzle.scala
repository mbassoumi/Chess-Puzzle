package controllers

import services.ChessHelpers.{getMappingArray, getPiecesWithItsCount, getSafeBoards}
import services.Helpers._
import services.ChessHelpers._

import Console.{GREEN, RED, RESET, UNDERLINED, YELLOW_B}

object ChessPuzzle {


  /**
    * read input from console
    * inputs are number of each chess piece and board height and width
    *
    * @return [Map keyed by input key and valued by input value]
    */
  def readInput(): Map[String, Int] = {
    val pieces: List[String] = List("K", "Q", "R", "B", "N", "height", "width")
    var inputs: Map[String, Int] = Map()
    var piece: String = null
    var answer: Int = -1
    pieces.foreach(signature => {
      piece = getPieceName(signature)
      while (answer < 0) {
        if (signature == "width" || signature == "height") {
          Console.print(s"${RESET}${GREEN}Board $signature :${RESET}")
        } else {
          Console.print(s"${RESET}${GREEN}Number of $piece :${RESET}")
        }
        try {
          answer = io.StdIn.readInt()
          Console.println("")
          if (answer < 0) {
            Console.println("")
            Console.err.println(s"${RESET}${YELLOW_B}${RED}${UNDERLINED}cant be in minus!${RESET}")
            Console.println("")
          } else {
            inputs = inputs + (signature -> answer)
          }
        } catch {
          case e: Exception => {
            Console.println("")
            Console.err.println(s"${RESET}${YELLOW_B}${RED}${UNDERLINED}invalid input${RESET}")
            Console.println("")
          }
        }
      }
      answer = -1
    })

    inputs
  }


  /**
    * run method which run the task
    */
  def run(): Unit = {

    val inputs: Map[String, Int] = readInput()

    val signatures: List[String] = List("K", "Q", "R", "B", "N")
    var piecesCount: Map[String, Int] = Map()
    signatures.foreach(signature => {
      piecesCount = piecesCount + (signature -> inputs(signature))
    })

    val inputPieces = orderChessPieces(piecesCount)

    val pieces = (inputPieces, piecesCount)

    val height: Int = inputs("height")
    val width: Int = inputs("width")


    val boardDimensions: (Int, Int) = (height, width)

    val length: Int = height * width

    val mappingArray = getMappingArray(height, width)

    val safeBoards = time {
      getSafeBoards(length, pieces, boardDimensions, mappingArray)
    }

    printSolutions(safeBoards, boardDimensions, length)

    val numberOfSolutions: Int = safeBoards.length

    Console.println(s"${GREEN}number of solutions is $numberOfSolutions ${GREEN}")
  }

  /**
    * run the special series
    * a 7×7 board with 2 Kings, 2 Queens, 2 Bishops and 1 Knight.
    *
    * time is 38.481887419 seconds
    * total number of solutions is 3063828
    */
  def runSpecialSeries(): Unit = {
    val inputPieces: List[String] = List("K", "K", "Q", "Q", "B", "B", "N")
    val height: Int = 7
    val width: Int = 7
    val boardDimensions: (Int, Int) = (height, width)
    val length: Int = height * width

    val mappingArray = getMappingArray(height, width)

    val pieces = getPiecesWithItsCount(inputPieces)

    val safeBoards = time {
      getSafeBoards(length, pieces, boardDimensions, mappingArray)
    }

    val numberOfSolutions: Int = safeBoards.length

    Console.println(s"${GREEN}number of solutions is $numberOfSolutions ${GREEN}")

  }

}
