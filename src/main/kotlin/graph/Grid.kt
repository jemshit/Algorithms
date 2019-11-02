package graph

class Grid {

    data class Coordinate(val x: Int, val y: Int)

    val grid: Array<Array<String>>
    val diagonalMoveAllowed: Boolean
    val rowCount: Int
    val columnCount: Int
    val blockedChar: String

    constructor(grid: Array<Array<String>>, diagonalMoveAllowed: Boolean, blockedChar: String) {
        if (grid.isEmpty()) throw IllegalArgumentException()

        this.grid = grid
        this.rowCount = grid.size
        this.columnCount = grid[0].size
        this.diagonalMoveAllowed = diagonalMoveAllowed
        this.blockedChar = blockedChar
    }

    fun getNeighbours(x: Int, y: Int): List<Coordinate> {
        if (x >= columnCount || y >= rowCount) throw IllegalArgumentException()

        val directions: List<Coordinate> = if (diagonalMoveAllowed)
            listOf(
                Coordinate(-1, 0),
                Coordinate(0, -1),
                Coordinate(1, 0),
                Coordinate(0, 1),
                Coordinate(-1, -1),
                Coordinate(1, 1),
                Coordinate(-1, 1),
                Coordinate(1, -1)
            )
        else
            listOf(
                Coordinate(-1, 0),
                Coordinate(0, -1),
                Coordinate(1, 0),
                Coordinate(0, 1)
            )

        val neighbours = mutableListOf<Coordinate>()
        for (direction in directions) {
            val nextRow = y + direction.y
            val nextColumn = x + direction.x

            if (nextColumn < 0 || nextRow < 0) continue
            if (nextColumn >= columnCount || nextRow >= rowCount) continue

            if (!grid[nextColumn][nextRow].equals(blockedChar, ignoreCase = false))
                neighbours.add(Coordinate(nextColumn, nextRow))
        }
        return neighbours
    }

}