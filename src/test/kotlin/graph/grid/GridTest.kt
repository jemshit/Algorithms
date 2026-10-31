package graph.grid

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class GridTest {

    @Test
    fun `test neighbour not-diagonal`() {
        val grid = Grid(
            arrayOf(
                charArrayOf('.', '.', '#', '.', '.'),
                charArrayOf('.', '.', '.', '#', '.'),
                charArrayOf('.', '#', '.', '.', '.'),
                charArrayOf('#', '.', '#', '.', '.')
            ),
            diagonalMoveAllowed = false,
            blockedChar = '#'
        )
        assertEquals(4, grid.rowCount)
        assertEquals(5, grid.columnCount)

        val neighbours00 = grid.getNeighbours(Coordinate(row = 0, column = 0))
        assertEquals(2, neighbours00.size)
        assertTrue(neighbours00.contains(Coordinate(row = 0, column = 1)))
        assertTrue(neighbours00.contains(Coordinate(row = 1, column = 0)))

        val neighbours01 = grid.getNeighbours(Coordinate(row = 0, column = 1))
        assertEquals(2, neighbours01.size)
        assertTrue(neighbours01.contains(Coordinate(row = 0, column = 0)))
        assertTrue(neighbours01.contains(Coordinate(row = 1, column = 1)))

        val neighbours11 = grid.getNeighbours(Coordinate(row = 1, column = 1))
        assertEquals(3, neighbours11.size)
        assertTrue(neighbours11.contains(Coordinate(row = 0, column = 1)))
        assertTrue(neighbours11.contains(Coordinate(row = 1, column = 0)))
        assertTrue(neighbours11.contains(Coordinate(row = 1, column = 2)))
    }

    @Test
    fun `test neighbour diagonal`() {
        val grid = Grid(
            arrayOf(
                charArrayOf('.', '.', '#', '.', '.'),
                charArrayOf('.', '.', '.', '#', '.'),
                charArrayOf('.', '#', '.', '.', '.'),
                charArrayOf('#', '.', '#', '.', '.')
            ),
            diagonalMoveAllowed = true,
            blockedChar = '#'
        )
        assertEquals(4, grid.rowCount)
        assertEquals(5, grid.columnCount)

        val neighbours00 = grid.getNeighbours(Coordinate(row = 0, column = 0))
        assertEquals(3, neighbours00.size)
        assertTrue(neighbours00.contains(Coordinate(row = 0, column = 1)))
        assertTrue(neighbours00.contains(Coordinate(row = 1, column = 0)))
        assertTrue(neighbours00.contains(Coordinate(row = 1, column = 1)))

        val neighbours01 = grid.getNeighbours(Coordinate(row = 0, column = 1))
        assertEquals(4, neighbours01.size)
        assertTrue(neighbours01.contains(Coordinate(row = 0, column = 0)))
        assertTrue(neighbours01.contains(Coordinate(row = 1, column = 1)))
        assertTrue(neighbours01.contains(Coordinate(row = 1, column = 0)))
        assertTrue(neighbours01.contains(Coordinate(row = 1, column = 2)))

        val neighbours11 = grid.getNeighbours(Coordinate(row = 1, column = 1))
        assertEquals(6, neighbours11.size)
        assertTrue(neighbours11.contains(Coordinate(row = 0, column = 0)))
        assertTrue(neighbours11.contains(Coordinate(row = 2, column = 0)))
        assertTrue(neighbours11.contains(Coordinate(row = 0, column = 1)))
        assertTrue(neighbours11.contains(Coordinate(row = 1, column = 0)))
        assertTrue(neighbours11.contains(Coordinate(row = 1, column = 2)))
        assertTrue(neighbours11.contains(Coordinate(row = 2, column = 2)))
    }

}
