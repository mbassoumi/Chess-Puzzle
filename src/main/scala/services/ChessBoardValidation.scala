package services

import models.{Bishop, King, Knight, Queen, Rook}
import traits.Piece
import services.Helpers._

object ChessBoardValidation {

  val king: King = new King()
  val rook: Rook = new Rook()
  val knight: Knight = new Knight()
  val queen: Queen = new Queen()
  val bishp: Bishop = new Bishop()

  /**
    * check if board will stay safe if you insert a chess piece into it at the given index
    * by call the validate method from the piece model
    * the function return true if the chess board will stay safe and false otherwise
    *
    * @param board           [chess board to be checked]
    * @param index           [the index of the chess piece which you want to insert it]
    * @param pieceSignature  [piece signature (K, Q, N, R, B)]
    * @param boardDimensions [tuple contains chess board dimensions (height, width)]
    * @param mappingArray    [tuple with two maps to help in converting one dimensional array to two dimensional array and vice versa]
    * @return [boolean]
    */
  def willBoardBeSafe(board: Map[Int, String], index: Int, pieceSignature: String, boardDimensions: (Int, Int), mappingArray: (Map[String, Int], Map[Int, String])): Boolean = {

    var isSafe: Boolean = false


    val piece: Piece = getPieceModel(pieceSignature)
    if (board.isDefinedAt(index) && board(index) == "-") {
      isSafe = piece.validate(board, index, boardDimensions, mappingArray)
    }
    isSafe
  }


  /**
    * update the chess board by inserting the chess piece into it at the given index
    * and fill the attacked positions with null value
    * by call the updateBoard method from the piece model
    * the function return the updated board
    *
    * @param board           [chess board to be updated]
    * @param index           [the index of the chess piece which you want to insert it]
    * @param pieceSignature  [piece signature (K, Q, N, R, B)]
    * @param boardDimensions [tuple contains chess board dimensions (height, width)]
    * @param mappingArray    [tuple with two maps to help in converting one dimensional array to two dimensional array and vice versa]
    * @return [updated board]
    */
  def updateBoard(board: Map[Int, String], index: Int, pieceSignature: String, boardDimensions: (Int, Int), mappingArray: (Map[String, Int], Map[Int, String])): Map[Int, String] = {

    val piece: Piece = getPieceModel(pieceSignature)

    val updatedBoard = piece.updateBoard(board, index, boardDimensions, mappingArray)

    updatedBoard
  }

}
