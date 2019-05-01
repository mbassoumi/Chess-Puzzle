import services.ChessHelpers

class YourCasesTest extends BaseTestingClass {

  test("3×3 board containing 2 Kings and 1 Rook") {
    val length: Int = 9
    val pieces: (List[String], Map[String, Int]) = (List("K", "R"), Map("K" -> 2, "R" -> 1))
    val boardDimensions: (Int, Int) = (3, 3)
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(3, 3)

    val safeBoards = ChessHelpers.getSafeBoards(length, pieces, boardDimensions, mappingArray)

    assert(safeBoards.length == 4)
  }

  test("4×4 board containing 2 Rooks and 4 Knights") {
    val length: Int = 16
    val pieces: (List[String], Map[String, Int]) = (List("N", "R"), Map("N" -> 4, "R" -> 2))
    val boardDimensions: (Int, Int) = (4, 4)
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(4, 4)

    val safeBoards = ChessHelpers.getSafeBoards(length, pieces, boardDimensions, mappingArray)

    assert(safeBoards.length == 8)
  }


  test("7×7 board with 2 Kings, 2 Queens, 2 Bishops and 1 Knight") {
    val length: Int = 49
    val pieces: (List[String], Map[String, Int]) = (List("K", "Q", "B", "N"), Map("K" -> 2, "Q" -> 2, "B" -> 2, "N" -> 1))
    val boardDimensions: (Int, Int) = (7, 7)
    val mappingArray: (Map[String, Int], Map[Int, String]) = initMappingArray(7, 7)

    val safeBoards = ChessHelpers.getSafeBoards(length, pieces, boardDimensions, mappingArray)

    assert(safeBoards.length == 3063828)
  }


}