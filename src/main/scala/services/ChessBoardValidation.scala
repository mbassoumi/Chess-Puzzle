package services

import models.{Bishop, King, Knight, Queen, Rook}
import services.Helpers._

object ChessBoardValidation {

  def isBoardSafe(board: Map[Int, String], index: Int, pieceSignature: String, boardDimensions: (Int, Int), mappingArray: (Map[String, Int], Map[Int, String])): Boolean = {

    var isSafe: Boolean = false

    if (board.isDefinedAt(index) && board(index) == "-") {
      isSafe = pieceSignature match {
        case "B" => new Bishop().validate(board, index, boardDimensions, mappingArray)
        case "K" => new King().validate(board, index, boardDimensions, mappingArray)
        case "N" => new Knight().validate(board, index, boardDimensions, mappingArray)
        case "Q" => new Queen().validate(board, index, boardDimensions, mappingArray)
        case "R" => new Rook().validate(board, index, boardDimensions, mappingArray)
        case _ => false
      }
    }
    isSafe
  }

}
