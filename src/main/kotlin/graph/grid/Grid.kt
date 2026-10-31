package graph.grid

data class Coordinate(val row: Int, val column: Int) {
    override fun toString(): String = "($row,$column)"
}

private val FOUR_DIRECTIONS = listOf(
    Coordinate(row = -1, column = 0), // Up
    Coordinate(row = 0, column = -1), // Left
    Coordinate(row = 1, column = 0), // Down
    Coordinate(row = 0, column = 1) // Right
)

private val EIGHT_DIRECTIONS = FOUR_DIRECTIONS + listOf(
    Coordinate(row = -1, column = -1), // Up-left
    Coordinate(row = 1, column = 1), // Down-right
    Coordinate(row = -1, column = 1), // Up-right
    Coordinate(row = 1, column = -1) // Down-left
)

class Grid(
    val cells: Array<CharArray>,
    val diagonalMoveAllowed: Boolean = false,
    val blockedChar: Char = '#'
) {
    val rowCount: Int
        get() = cells.size

    val columnCount: Int
        get() = cells[0].size

    init {
        require(cells.isNotEmpty()) { "Grid must contain at least one row" }
        require(cells[0].isNotEmpty()) { "Grid must contain at least one column" }
        require(cells.all { it.size == columnCount }) { "Grid rows must have equal lengths" }
    }

    fun getNeighbours(cell: Coordinate): List<Coordinate> {
        require(cell.row in 0 until rowCount && cell.column in 0 until columnCount) {
            "Coordinate is outside the grid: $cell"
        }

        val directions = if (diagonalMoveAllowed) EIGHT_DIRECTIONS else FOUR_DIRECTIONS

        val neighbours = mutableListOf<Coordinate>()
        for (direction in directions) {
            val nextRow = cell.row + direction.row
            val nextColumn = cell.column + direction.column

            // ignore outside the bounds
            if (nextRow !in 0 until rowCount || nextColumn !in 0 until columnCount) {
                continue
            }

            if (cells[nextRow][nextColumn] != blockedChar) {
                neighbours.add(Coordinate(row = nextRow, column = nextColumn))
            }
        }
        return neighbours
    }
}
