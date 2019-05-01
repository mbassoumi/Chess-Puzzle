import models.Bishop

class BishopModelTest extends BaseTestingClass {

  var bishop: Bishop = _

  before {
    bishop = new Bishop()
  }

  test("3x3 board with index = 1 (0,1) - Bishop.getAffectedPositionInBoard ") {
    val height: Int = 3
    val width: Int = 3
    val index: Int = 1
    val expectedResult: List[Int] = List(3, 5)
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    val affectedPositions: List[Int] = bishop.getAffectedPositionInBoard(index, mappingArray, boardDimensions)
    val diff1 = expectedResult.diff(affectedPositions)
    val diff2 = affectedPositions.diff(expectedResult)
    assert(diff1.isEmpty && diff2.isEmpty)
  }

  test("3x3 board with index = 4 (1,1) - Bishop.getAffectedPositionInBoard ") {
    val height: Int = 3
    val width: Int = 3
    val index: Int = 4
    val expectedResult: List[Int] = List(0, 2, 6, 8)
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    val affectedPositions: List[Int] = bishop.getAffectedPositionInBoard(index, mappingArray, boardDimensions)
    val diff1 = affectedPositions.diff(expectedResult)
    val diff2 = expectedResult.diff(affectedPositions)
    assert(diff1.isEmpty && diff2.isEmpty)
  }

  test("4x4 board with index = 0 (0,0) - Bishop.getAffectedPositionInBoard ") {
    val height: Int = 4
    val width: Int = 4
    val index: Int = 0
    val expectedResult: List[Int] = List(5, 10, 15)
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    val affectedPositions: List[Int] = bishop.getAffectedPositionInBoard(index, mappingArray, boardDimensions)
    val diff1 = affectedPositions.diff(expectedResult)
    val diff2 = expectedResult.diff(affectedPositions)
    assert(diff1.isEmpty && diff2.isEmpty)
  }

  test("Bishop.signature is B") {
    assert(bishop.signature == "B")
  }

  test("Bishop.checkPosition is true") {
    var board = initBoard(3, 3)
    var result = bishop.checkPosition(1, board)
    assert(result)
  }

  test("Bishop.checkPosition is false") {
    var board = initBoard(3, 3)
    board += (2 -> "Q")
    var result = bishop.checkPosition(2, board)
    assert(!result)
  }

  test("Bishop.validate is true") {
    val height: Int = 4
    val width: Int = 4
    val index: Int = 0
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    var board = initBoard(height, width)
    board += (6 -> "K")

    val result = bishop.validate(board, index, boardDimensions, mappingArray)
    assert(result)
  }

  test("Bishop.validate is false") {
    val height: Int = 4
    val width: Int = 4
    val index: Int = 0
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    var board = initBoard(height, width)
    board += (15 -> "K")

    val result = bishop.validate(board, index, boardDimensions, mappingArray)
    assert(!result)
  }

  test("Bishop.updateBoard") {
    val height: Int = 4
    val width: Int = 4
    val index: Int = 0
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    var board = initBoard(height, width)

    val result = bishop.updateBoard(board, index, boardDimensions, mappingArray)

    var expectedResult: Map[Int, String] = board

    expectedResult += (0 -> "B")
    expectedResult += (5 -> null)
    expectedResult += (10 -> null)
    expectedResult += (15 -> null)

    assert(result == expectedResult)
  }


}