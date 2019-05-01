import models.Queen

class QueenModelTest extends BaseTestingClass {

  var queen: Queen = _

  before {
    queen = new Queen()
  }

  test("3x3 board with index = 1 (0,1) - Queen.getAffectedPositionInBoard ") {
    val height: Int = 3
    val width: Int = 3
    val index: Int = 1
    val expectedResult: List[Int] = List(0, 2, 3, 4, 5, 7)
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    val affectedPositions: List[Int] = queen.getAffectedPositionInBoard(index, mappingArray, boardDimensions)
    val diff1 = expectedResult.diff(affectedPositions)
    val diff2 = affectedPositions.diff(expectedResult)
    assert(diff1.isEmpty && diff2.isEmpty)
  }

  test("3x3 board with index = 4 (1,1) - Queen.getAffectedPositionInBoard ") {
    val height: Int = 3
    val width: Int = 3
    val index: Int = 4
    val expectedResult: List[Int] = List(0, 1, 2, 3, 5, 6, 7, 8)
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    val affectedPositions: List[Int] = queen.getAffectedPositionInBoard(index, mappingArray, boardDimensions)
    val diff1 = affectedPositions.diff(expectedResult)
    val diff2 = expectedResult.diff(affectedPositions)
    assert(diff1.isEmpty && diff2.isEmpty)
  }

  test("4x4 board with index = 0 (0,0) - Queen.getAffectedPositionInBoard ") {
    val height: Int = 4
    val width: Int = 4
    val index: Int = 0
    val expectedResult: List[Int] = List(1, 2, 3, 4, 8, 12, 5, 10, 15)
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    val affectedPositions: List[Int] = queen.getAffectedPositionInBoard(index, mappingArray, boardDimensions)
    val diff1 = affectedPositions.diff(expectedResult)
    val diff2 = expectedResult.diff(affectedPositions)
    assert(diff1.isEmpty && diff2.isEmpty)
  }

  test("Queen.signature is Q") {
    assert(queen.signature == "Q")
  }

  test("Queen.checkPosition is true") {
    var board = initBoard(3, 3)
    var result = queen.checkPosition(1, board)
    assert(result)
  }

  test("Queen.checkPosition is false") {
    var board = initBoard(3, 3)
    board += (2 -> "Q")
    var result = queen.checkPosition(2, board)
    assert(!result)
  }

  test("Queen.validate is true") {
    val height: Int = 4
    val width: Int = 4
    val index: Int = 0
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    var board = initBoard(height, width)
    board += (6 -> "K")

    val result = queen.validate(board, index, boardDimensions, mappingArray)
    assert(result)
  }

  test("Queen.validate is false") {
    val height: Int = 4
    val width: Int = 4
    val index: Int = 0
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    var board = initBoard(height, width)
    board += (4 -> "K")

    val result = queen.validate(board, index, boardDimensions, mappingArray)
    assert(!result)
  }

  test("Queen.updateBoard") {
    val height: Int = 4
    val width: Int = 4
    val index: Int = 0
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    var board = initBoard(height, width)

    val result = queen.updateBoard(board, index, boardDimensions, mappingArray)

    var expectedResult: Map[Int, String] = board

    expectedResult += (0 -> "Q")
    expectedResult += (1 -> null)
    expectedResult += (2 -> null)
    expectedResult += (3 -> null)
    expectedResult += (4 -> null)
    expectedResult += (8 -> null)
    expectedResult += (12 -> null)
    expectedResult += (5 -> null)
    expectedResult += (10 -> null)
    expectedResult += (15 -> null)

    assert(result == expectedResult)
  }


}