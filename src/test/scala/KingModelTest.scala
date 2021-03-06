import models.King

class KingModelTest extends BaseTestingClass {

  var king: King = _

  before {
    king = new King()
  }

  test("3x3 board with index = 1 (0,1) - King.getAffectedPositionInBoard ") {
    val height: Int = 3
    val width: Int = 3
    val index: Int = 1
    val expectedResult: List[Int] = List(0, 2, 3, 4, 5)
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    val affectedPositions: List[Int] = king.getAffectedPositionInBoard(index, mappingArray, boardDimensions)
    val diff1 = affectedPositions.diff(expectedResult)
    val diff2 = expectedResult.diff(affectedPositions)
    assert(diff1.isEmpty && diff2.isEmpty)
  }

  test("3x3 board with index = 4 (1,1) - King.getAffectedPositionInBoard ") {
    val height: Int = 3
    val width: Int = 3
    val index: Int = 4
    val expectedResult: List[Int] = List(0, 1, 2, 3, 5, 6, 7, 8)
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    val affectedPositions: List[Int] = king.getAffectedPositionInBoard(index, mappingArray, boardDimensions)
    val diff1 = affectedPositions.diff(expectedResult)
    val diff2 = expectedResult.diff(affectedPositions)
    assert(diff1.isEmpty && diff2.isEmpty)
  }

  test("4x4 board with index = 0 (0,0) - King.getAffectedPositionInBoard ") {
    val height: Int = 4
    val width: Int = 4
    val index: Int = 0
    val expectedResult: List[Int] = List(1, 4, 5)
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    val affectedPositions: List[Int] = king.getAffectedPositionInBoard(index, mappingArray, boardDimensions)
    val diff1 = affectedPositions.diff(expectedResult)
    val diff2 = expectedResult.diff(affectedPositions)
    assert(diff1.isEmpty && diff2.isEmpty)
  }

  test("king.signature is K") {
    assert(king.signature == "K")
  }

  test("king.checkPosition is true") {
    var board = initBoard(3, 3)
    var result = king.checkPosition(1, board)
    assert(result)
  }

  test("king.checkPosition is false") {
    var board = initBoard(3, 3)
    board += (2 -> "Q")
    var result = king.checkPosition(2, board)
    assert(!result)
  }

  test("king.validate is true") {
    val height: Int = 4
    val width: Int = 4
    val index: Int = 0
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    var board = initBoard(height, width)
    board += (3 -> "K")

    val result = king.validate(board, index, boardDimensions, mappingArray)
    assert(result)
  }

  test("king.validate is false") {
    val height: Int = 4
    val width: Int = 4
    val index: Int = 0
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    var board = initBoard(height, width)
    board += (4 -> "K")

    val result = king.validate(board, index, boardDimensions, mappingArray)
    assert(!result)
  }

  test("king.updateBoard") {
    val height: Int = 4
    val width: Int = 4
    val index: Int = 0
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    var board = initBoard(height, width)

    val result = king.updateBoard(board, index, boardDimensions, mappingArray)

    var expectedResult: Map[Int, String] = board

    expectedResult += (0 -> "K")
    expectedResult += (1 -> null)
    expectedResult += (4 -> null)
    expectedResult += (5 -> null)

    assert(result == expectedResult)
  }


}
