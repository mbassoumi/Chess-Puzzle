import org.scalatest.{BeforeAndAfter, FunSuite}
import services.ChessHelpers
import services.ChessHelpers.getMappingArray

class BaseTestingClass extends FunSuite with BeforeAndAfter {

  def initMappingArray(height: Int, width: Int): (Map[String, Int], Map[Int, String]) = {
    val mappingArray: (Map[String, Int], Map[Int, String]) = getMappingArray(height, width)
    mappingArray
  }

  def initBoard(height: Int, width: Int): Map[Int, String] = {
    var board = ChessHelpers.getEmptyChessBoard(height * width)
    board
  }
}
