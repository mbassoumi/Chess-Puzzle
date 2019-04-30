package services

import models._
import traits.Piece

object Helpers {

  /**
    * calculate the execution time for a function and print it
    *
    * @param block
    * @tparam R
    * @return
    */
  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) + "ns")
    result
  }

  /**
    * get the chess piece full name by its signature
    *
    * @param signature [piece signature (K, Q, N, R, B)]
    * @return [String: piece full name]
    */
  def getPieceName(signature: String): String = {

    val piece: String = signature match {
      case "K" => "King"
      case "R" => "Rook"
      case "N" => "Knight"
      case "Q" => "Queen"
      case "B" => "Bishop"
      case _ => null
    }

    piece
  }


  /**
    * get object of piece model by its signature
    *
    * @param signature [piece signature (K, Q, N, R, B)]
    * @return [piece model object]
    */
  def getPieceModel(signature: String): Piece = {

    val piece: Piece = signature match {
      case "K" => new King()
      case "R" => new Rook()
      case "N" => new Knight()
      case "Q" => new Queen()
      case "B" => new Bishop()
      case _ => null
    }
    piece
  }

  def isNull(variable: Any): Boolean = {
    var isNull: Boolean = false
    if (variable == null) {
      isNull = true
    }
    isNull
  }
}
