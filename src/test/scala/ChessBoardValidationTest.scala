import models.Rook
import services.ChessBoardValidation

class ChessBoardValidationTest extends BaseTestingClass {

  var rook: Rook = _

  before {
    rook = new Rook()
  }

  test("ChessBoardValidation.willBoardBeSafe is true") {
    val height: Int = 3
    val width: Int = 3
    val index: Int = 0
    val pieceSignature: String = "K"
    val boardDimensions = (height, width)
    val mappingArray = initMappingArray(height, width)
    var board = initBoard(height, width)

    val result = ChessBoardValidation.willBoardBeSafe(board, index, pieceSignature, boardDimensions, mappingArray)

    assert(result)

  }

  test("ChessBoardValidation.willBoardBeSafe is false") {
    val height: Int = 3
    val width: Int = 3
    val index: Int = 0
    val pieceSignature: String = "K"
    val boardDimensions = (height, width)
    val mappingArray = initMappingArray(height, width)
    var board = initBoard(height, width)
    board += (0 -> "K")

    val result = ChessBoardValidation.willBoardBeSafe(board, index, pieceSignature, boardDimensions, mappingArray)

    assert(!result)

  }

  test("ChessBoardValidation.updateBoard") {
    val height: Int = 3
    val width: Int = 3
    val index: Int = 0
    val pieceSignature: String = "K"
    val boardDimensions = (height, width)
    val mappingArray = initMappingArray(height, width)
    var board = initBoard(height, width)


    val result = ChessBoardValidation.updateBoard(board, index, pieceSignature, boardDimensions, mappingArray)

    board += (0 -> "K")
    board += (1 -> null)
    board += (3 -> null)
    board += (4 -> null)

    assert(result == board)

  }


}