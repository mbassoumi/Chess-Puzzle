import services.ChessHelpers

class ChessHelpersTest extends BaseTestingClass {


  test("ChessHelpers.getMappingArray") {
    val height: Int = 2
    val width: Int = 2
    val mappingArray = ChessHelpers.getMappingArray(height, width)

    val (expectedReverseMapping, expectedStraightforwardMapping) = mappingArray

    var reverseMapping: Map[String, Int] = Map()
    var straightforwardMapping: Map[Int, String] = Map()

    reverseMapping += ("0-0" -> 0)
    reverseMapping += ("0-1" -> 1)
    reverseMapping += ("1-0" -> 2)
    reverseMapping += ("1-1" -> 3)

    straightforwardMapping += (0 -> "0-0")
    straightforwardMapping += (1 -> "0-1")
    straightforwardMapping += (2 -> "1-0")
    straightforwardMapping += (3 -> "1-1")

    assert(reverseMapping == expectedReverseMapping && straightforwardMapping == expectedStraightforwardMapping)

  }

  test("ChessHelpers.getPiecesWithItsCount") {
    val inputPieces: List[String] = List("R", "Q", "B", "K", "N", "Q")
    val expectedPieces: List[String] = List("Q", "B", "R", "N", "K")
    var expectedPiecesCount: Map[String, Int] = Map("K" -> 1, "Q" -> 2, "R" -> 1, "N" -> 1, "B" -> 1)

    var (pieces, piecesCount) = ChessHelpers.getPiecesWithItsCount(inputPieces)

    assert(pieces == expectedPieces && piecesCount == expectedPiecesCount)
  }

  test("ChessHelpers.orderChessPieces") {
    val piecesCount: Map[String, Int] = Map("K" -> 1, "Q" -> 2, "R" -> 1, "N" -> 1, "B" -> 1)
    val expectedPieces: List[String] = List("Q", "B", "R", "N", "K")
    val pieces = ChessHelpers.orderChessPieces(piecesCount)
    assert(pieces == expectedPieces)

  }
  test("ChessHelpers.getEmptyChessBoard") {

    val emptyBoard = ChessHelpers.getEmptyChessBoard(9)
    var expectedBoard: Map[Int, String] = Map()
    for (i <- 0 to 8) {
      expectedBoard += (i -> "-")
    }
    assert(emptyBoard == expectedBoard)
  }

}