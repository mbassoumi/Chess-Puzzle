package models

import traits.Piece

class Knight extends Piece{
  private val _signature: String = "N"

  def signature: String = _signature

  def validate(board: Map[Int, String], index: Int, boardDimensions: (Int, Int), mappingArray: (Map[String, Int], Map[Int, String])): Boolean = {
    var isSafe: Boolean = true

    isSafe
  }
}