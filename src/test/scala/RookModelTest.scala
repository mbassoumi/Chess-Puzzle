import models.Rook

class RookModelTest extends BaseTestingClass {

  var rook: Rook = _

  before {
    rook = new Rook()
  }

  test("3x3 board with index = 1 (0,1) - Rook.getAffectedPositionInBoard ") {
    val height: Int = 3
    val width: Int = 3
    val index: Int = 1
    val expectedResult: List[Int] = List(0, 2, 4, 7)
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    val affectedPositions: List[Int] = rook.getAffectedPositionInBoard(index, mappingArray, boardDimensions)
    val diff1 = expectedResult.diff(affectedPositions)
    val diff2 = affectedPositions.diff(expectedResult)
    assert(diff1.isEmpty && diff2.isEmpty)
  }

  test("3x3 board with index = 4 (1,1) - Rook.getAffectedPositionInBoard ") {
    val height: Int = 3
    val width: Int = 3
    val index: Int = 4
    val expectedResult: List[Int] = List(1, 3, 5, 7)
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    val affectedPositions: List[Int] = rook.getAffectedPositionInBoard(index, mappingArray, boardDimensions)
    val diff1 = affectedPositions.diff(expectedResult)
    val diff2 = expectedResult.diff(affectedPositions)
    assert(diff1.isEmpty && diff2.isEmpty)
  }

  test("4x4 board with index = 0 (0,0) - Rook.getAffectedPositionInBoard ") {
    val height: Int = 4
    val width: Int = 4
    val index: Int = 0
    val expectedResult: List[Int] = List(1, 2, 3, 4, 8, 12)
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    val affectedPositions: List[Int] = rook.getAffectedPositionInBoard(index, mappingArray, boardDimensions)
    val diff1 = affectedPositions.diff(expectedResult)
    val diff2 = expectedResult.diff(affectedPositions)
    assert(diff1.isEmpty && diff2.isEmpty)
  }

  test("Rook.signature is R") {
    assert(rook.signature == "R")
  }

  test("Rook.checkPosition is true") {
    var board = initBoard(3, 3)
    var result = rook.checkPosition(1, board)
    assert(result)
  }

  test("Rook.checkPosition is false") {
    var board = initBoard(3, 3)
    board += (2 -> "Q")
    var result = rook.checkPosition(2, board)
    assert(!result)
  }

  test("Rook.validate is true") {
    val height: Int = 4
    val width: Int = 4
    val index: Int = 0
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    var board = initBoard(height, width)
    board += (6 -> "K")

    val result = rook.validate(board, index, boardDimensions, mappingArray)
    assert(result)
  }

  test("Rook.validate is false") {
    val height: Int = 4
    val width: Int = 4
    val index: Int = 0
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    var board = initBoard(height, width)
    board += (4 -> "K")

    val result = rook.validate(board, index, boardDimensions, mappingArray)
    assert(!result)
  }

  test("Rook.updateBoard") {
    val height: Int = 4
    val width: Int = 4
    val index: Int = 0
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    var board = initBoard(height, width)

    val result = rook.updateBoard(board, index, boardDimensions, mappingArray)

    var expectedResult: Map[Int, String] = board

    expectedResult += (0 -> "R")
    expectedResult += (1 -> null)
    expectedResult += (2 -> null)
    expectedResult += (3 -> null)
    expectedResult += (4 -> null)
    expectedResult += (8 -> null)
    expectedResult += (12 -> null)

    assert(result == expectedResult)
  }


}