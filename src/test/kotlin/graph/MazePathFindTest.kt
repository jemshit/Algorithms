package graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MazePathFindTest {

    @Test
    fun `maze path find non-diagonal`() {
        val grid = Grid(
            arrayOf(
                arrayOf(".", ".", "#", ".", "."),
                arrayOf(".", ".", ".", "#", "."),
                arrayOf(".", "#", ".", ".", "."),
                arrayOf("#", ".", "#", ".", ".")
            ),
            diagonalMoveAllowed = false,
            blockedChar = "#"
        )
        assertEquals(4, grid.rowCount)
        assertEquals(5, grid.columnCount)

        val path = grid.findMazePathBfs(start = Grid.Coordinate(0, 0), end = Grid.Coordinate(3, 3))
        assertEquals(7, path.size)
        for (index in path.indices)
            print("${path[index]}->")
    }

    @Test
    fun `maze path find non-diagonal 2`() {
        val grid = Grid(
            arrayOf(
                arrayOf(".", ".", "#", ".", "."),
                arrayOf(".", ".", ".", "#", "."),
                arrayOf(".", "#", ".", ".", "."),
                arrayOf("#", ".", "#", ".", ".")
            ),
            diagonalMoveAllowed = false,
            blockedChar = "#"
        )
        assertEquals(4, grid.rowCount)
        assertEquals(5, grid.columnCount)

        val path = grid.findMazePathBfs(start = Grid.Coordinate(0, 0), end = Grid.Coordinate(0, 3))
        assertEquals(10, path.size)
        for (index in path.indices)
            print("${path[index]}->")
    }

    @Test
    fun `maze path find diagonal`() {
        val grid = Grid(
            arrayOf(
                arrayOf(".", ".", "#", ".", "."),
                arrayOf(".", ".", ".", "#", "."),
                arrayOf(".", "#", ".", ".", "."),
                arrayOf("#", ".", "#", ".", ".")
            ),
            diagonalMoveAllowed = true,
            blockedChar = "#"
        )
        assertEquals(4, grid.rowCount)
        assertEquals(5, grid.columnCount)

        val path = grid.findMazePathBfs(start = Grid.Coordinate(0, 0), end = Grid.Coordinate(3, 3))
        assertEquals(4, path.size)
        for (index in path.indices)
            print("${path[index]}->")
    }

    @Test
    fun `maze path find diagonal 2`() {
        val grid = Grid(
            arrayOf(
                arrayOf(".", ".", "#", ".", "#"),
                arrayOf(".", ".", ".", "#", "."),
                arrayOf(".", "#", ".", ".", "."),
                arrayOf("#", ".", "#", ".", ".")
            ),
            diagonalMoveAllowed = true,
            blockedChar = "#"
        )
        assertEquals(4, grid.rowCount)
        assertEquals(5, grid.columnCount)

        val path = grid.findMazePathBfs(start = Grid.Coordinate(0, 0), end = Grid.Coordinate(0, 3))
        assertEquals(4, path.size)
    }

    @Test
    fun `maze path cant find`() {
        val grid = Grid(
            arrayOf(
                arrayOf(".", ".", "#", ".", "#"),
                arrayOf(".", ".", ".", "#", "."),
                arrayOf(".", "#", ".", ".", "."),
                arrayOf("#", ".", "#", ".", ".")
            ),
            diagonalMoveAllowed = false,
            blockedChar = "#"
        )
        assertEquals(4, grid.rowCount)
        assertEquals(5, grid.columnCount)

        val path = grid.findMazePathBfs(start = Grid.Coordinate(0, 0), end = Grid.Coordinate(0, 3))
        assertEquals(0, path.size)
    }

}