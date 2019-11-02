package graph

class Grid {

    data class Coordinate(val x: Int, val y: Int) {
        override fun toString(): String = "($x,$y)"
    }

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
        if (x >= rowCount || y >= columnCount) throw IllegalArgumentException()

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
            val nextX = x + direction.x
            val nextY = y + direction.y

            if (nextX < 0 || nextY < 0) continue
            if (nextX >= rowCount || nextY >= columnCount) continue

            if (!grid[nextX][nextY].equals(blockedChar, ignoreCase = false))
                neighbours.add(Coordinate(nextX, nextY))
        }
        return neighbours
    }

}