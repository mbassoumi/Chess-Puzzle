package services

import models.King

object Helpers {

  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) + "ns")
    result
  }

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

  def getPieceModel(signature: String): Any = {

    val piece: King = signature match {
      case "K" => new King()
      case "R" => new King()
      case "N" => new King()
      case "Q" => new King()
      case "B" => new King()
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
