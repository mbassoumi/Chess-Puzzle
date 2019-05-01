import models.Knight

class KnightModelTest extends BaseTestingClass {

  var knight: Knight = _

  before {
    knight = new Knight()
  }

  test("3x3 board with index = 1 (0,1) - Knight.getAffectedPositionInBoard ") {
    val height: Int = 3
    val width: Int = 3
    val index: Int = 1
    val expectedResult: List[Int] = List(6, 8)
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    val affectedPositions: List[Int] = knight.getAffectedPositionInBoard(index, mappingArray, boardDimensions)
    val diff1 = expectedResult.diff(affectedPositions)
    val diff2 = affectedPositions.diff(expectedResult)
    assert(diff1.isEmpty && diff2.isEmpty)
  }

  test("3x3 board with index = 4 (1,1) - Knight.getAffectedPositionInBoard ") {
    val height: Int = 3
    val width: Int = 3
    val index: Int = 4
    val expectedResult: List[Int] = List()
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    val affectedPositions: List[Int] = knight.getAffectedPositionInBoard(index, mappingArray, boardDimensions)
    val diff1 = affectedPositions.diff(expectedResult)
    val diff2 = expectedResult.diff(affectedPositions)
    assert(diff1.isEmpty && diff2.isEmpty)
  }

  test("4x4 board with index = 0 (0,0) - Knight.getAffectedPositionInBoard ") {
    val height: Int = 4
    val width: Int = 4
    val index: Int = 0
    val expectedResult: List[Int] = List(9, 6)
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    val affectedPositions: List[Int] = knight.getAffectedPositionInBoard(index, mappingArray, boardDimensions)
    val diff1 = affectedPositions.diff(expectedResult)
    val diff2 = expectedResult.diff(affectedPositions)
    assert(diff1.isEmpty && diff2.isEmpty)
  }

  test("Knight.signature is N") {
    assert(knight.signature == "N")
  }

  test("Knight.checkPosition is true") {
    var board = initBoard(3, 3)
    var result = knight.checkPosition(1, board)
    assert(result)
  }

  test("Knight.checkPosition is false") {
    var board = initBoard(3, 3)
    board += (2 -> null)
    var result = knight.checkPosition(2, board)
    assert(!result)
  }

  test("Knight.validate is true") {
    val height: Int = 4
    val width: Int = 4
    val index: Int = 0
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    var board = initBoard(height, width)
    board += (4 -> "K")

    val result = knight.validate(board, index, boardDimensions, mappingArray)
    assert(result)
  }

  test("Knight.validate is false") {
    val height: Int = 4
    val width: Int = 4
    val index: Int = 0
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    var board = initBoard(height, width)
    board += (6 -> "K")

    val result = knight.validate(board, index, boardDimensions, mappingArray)
    assert(!result)
  }

  test("Knight.updateBoard") {
    val height: Int = 4
    val width: Int = 4
    val index: Int = 0
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(height, width)
    val boardDimensions: (Int, Int) = (height, width)
    var board = initBoard(height, width)

    val result = knight.updateBoard(board, index, boardDimensions, mappingArray)

    var expectedResult: Map[Int, String] = board

    expectedResult += (0 -> "N")
    expectedResult += (6 -> null)
    expectedResult += (9 -> null)

    assert(result == expectedResult)
  }


}