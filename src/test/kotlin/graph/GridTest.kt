package graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class GridTest {

    @Test
    fun `test neighbour not-diagonal`() {
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

        val neighbours00 = grid.getNeighbours(0, 0)
        assertEquals(2, neighbours00.size)
        assertTrue(neighbours00.contains(Grid.Coordinate(0, 1)))
        assertTrue(neighbours00.contains(Grid.Coordinate(1, 0)))

        val neighbours01 = grid.getNeighbours(0, 1)
        assertEquals(2, neighbours01.size)
        assertTrue(neighbours01.contains(Grid.Coordinate(0, 0)))
        assertTrue(neighbours01.contains(Grid.Coordinate(1, 1)))

        val neighbours11 = grid.getNeighbours(1, 1)
        assertEquals(3, neighbours11.size)
        assertTrue(neighbours11.contains(Grid.Coordinate(0, 1)))
        assertTrue(neighbours11.contains(Grid.Coordinate(1, 0)))
        assertTrue(neighbours11.contains(Grid.Coordinate(1, 2)))
    }

    @Test
    fun `test neighbour diagonal`() {
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

        val neighbours00 = grid.getNeighbours(0, 0)
        assertEquals(3, neighbours00.size)
        assertTrue(neighbours00.contains(Grid.Coordinate(0, 1)))
        assertTrue(neighbours00.contains(Grid.Coordinate(1, 0)))
        assertTrue(neighbours00.contains(Grid.Coordinate(1, 1)))

        val neighbours01 = grid.getNeighbours(0, 1)
        assertEquals(4, neighbours01.size)
        assertTrue(neighbours01.contains(Grid.Coordinate(0, 0)))
        assertTrue(neighbours01.contains(Grid.Coordinate(1, 1)))
        assertTrue(neighbours01.contains(Grid.Coordinate(1, 0)))
        assertTrue(neighbours01.contains(Grid.Coordinate(1, 2)))

        val neighbours11 = grid.getNeighbours(1, 1)
        assertEquals(6, neighbours11.size)
        assertTrue(neighbours11.contains(Grid.Coordinate(0, 0)))
        assertTrue(neighbours11.contains(Grid.Coordinate(2, 0)))
        assertTrue(neighbours11.contains(Grid.Coordinate(0, 1)))
        assertTrue(neighbours11.contains(Grid.Coordinate(1, 0)))
        assertTrue(neighbours11.contains(Grid.Coordinate(1, 2)))
        assertTrue(neighbours11.contains(Grid.Coordinate(2, 2)))
    }

}