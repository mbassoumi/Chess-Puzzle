package services

import models.{Bishop, King, Knight, Queen, Rook}
import services.ChessHelpers.getEmptyChessBoard

object ChessBoardValidation {

  val king: King = new King()
  val rook: Rook = new Rook()
  val knight: Knight = new Knight()
  val queen: Queen = new Queen()
  val bishp: Bishop = new Bishop()

  def willBoardBeSafe(board: Map[Int, String], index: Int, pieceSignature: String, boardDimensions: (Int, Int), mappingArray: (Map[String, Int], Map[Int, String])): Boolean = {

    var isSafe: Boolean = false

    if (board.isDefinedAt(index) && board(index) == "-") {
      isSafe = pieceSignature match {
        case "B" => bishp.validate(board, index, boardDimensions, mappingArray)
        case "K" => king.validate(board, index, boardDimensions, mappingArray)
        case "N" => knight.validate(board, index, boardDimensions, mappingArray)
        case "Q" => queen.validate(board, index, boardDimensions, mappingArray)
        case "R" => rook.validate(board, index, boardDimensions, mappingArray)
        case _ => false
      }
    }
    isSafe
  }

  def updateBoard(board: Map[Int, String], index: Int, pieceSignature: String, boardDimensions: (Int, Int), mappingArray: (Map[String, Int], Map[Int, String])): Map[Int, String] = {
    val updatedBoard = pieceSignature match {
      case "B" => bishp.updateBoard(board, index, boardDimensions, mappingArray)
      case "K" => king.updateBoard(board, index, boardDimensions, mappingArray)
      case "N" => knight.updateBoard(board, index, boardDimensions, mappingArray)
      case "Q" => queen.updateBoard(board, index, boardDimensions, mappingArray)
      case "R" => rook.updateBoard(board, index, boardDimensions, mappingArray)
      case _ => board
    }
    updatedBoard
  }

}
