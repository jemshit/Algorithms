package data_structures

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

internal class SnakeMoveTest {

    private fun snake(vararg points: Point): LinkedList<Point> = LinkedList(points.toList())

    /** Food far away from any cell the tests use, so it is never eaten by mistake. */
    private val farAwayFood = Point(Int.MAX_VALUE, Int.MAX_VALUE)

    // ------------------------------------------------------------------
    // Challenge examples
    // ------------------------------------------------------------------

    @Test
    fun `given snake along x axis when move left then head shifts and body follows`() {
        // given
        val s = snake(Point(0, 0), Point(1, 0), Point(2, 0))
        val food = Point(4, 5)

        // when
        move(s, Direction.LEFT, food)

        // then
        assertEquals(
            listOf(Point(-1, 0), Point(0, 0), Point(1, 0)),
            s
        )
    }

    @Test
    fun `given snake along y axis when move down then head shifts and body follows`() {
        // given
        val s = snake(Point(0, 0), Point(0, 1), Point(1, 1))
        val food = Point(4, 5)

        // when
        move(s, Direction.DOWN, food)

        // then
        assertEquals(
            listOf(Point(0, -1), Point(0, 0), Point(0, 1)),
            s
        )
    }

    // ------------------------------------------------------------------
    // Basic moves, all four directions, no food eaten
    // ------------------------------------------------------------------

    @Test
    fun `given snake along x axis when move right then tail drops`() {
        // given
        val s = snake(Point(2, 3), Point(1, 3), Point(0, 3))

        // when
        move(s, Direction.RIGHT, farAwayFood)

        // then
        assertEquals(
            listOf(Point(3, 3), Point(2, 3), Point(1, 3)),
            s
        )
    }

    @Test
    fun `given snake along x axis when move up then tail drops`() {
        // given
        val s = snake(Point(2, 3), Point(1, 3), Point(0, 3))

        // when
        move(s, Direction.UP, farAwayFood)

        // then
        assertEquals(
            listOf(Point(2, 4), Point(2, 3), Point(1, 3)),
            s
        )
    }

    @Test
    fun `given single segment snake when move left then head moves one cell`() {
        // given
        val s = snake(Point(5, 5))

        // when
        move(s, Direction.LEFT, farAwayFood)

        // then
        assertEquals(listOf(Point(4, 5)), s)
    }

    @Test
    fun `given single segment snake when move in each direction then head moves one cell`() {
        // given: the expected new head per direction
        val newHeads = mapOf(
            Direction.LEFT to Point(-1, 0),
            Direction.RIGHT to Point(1, 0),
            Direction.UP to Point(0, 1),
            Direction.DOWN to Point(0, -1)
        )

        for ((direction, expectedHead) in newHeads) {
            // given
            val s = snake(Point(0, 0))

            // when
            move(s, direction, farAwayFood)

            // then
            assertEquals(1, s.size) { "size changed for $direction" }
            assertEquals(expectedHead, s[0], "wrong head for $direction")
        }
    }

    @Test
    fun `given five segment snake when move without food then size stays constant`() {
        // given
        val s = snake(Point(0, 0), Point(1, 0), Point(2, 0), Point(3, 0), Point(4, 0))

        // when
        move(s, Direction.UP, farAwayFood)

        // then
        assertEquals(5, s.size)
    }

    @Test
    fun `given bent snake when move up then body order is preserved`() {
        // given
        val s = snake(
            Point(0, 0), Point(1, 0), Point(2, 0),
            Point(2, 1), Point(2, 2), Point(3, 2)
        )

        // when
        move(s, Direction.UP, farAwayFood)

        // then
        assertEquals(
            listOf(
                Point(0, 1), Point(0, 0), Point(1, 0),
                Point(2, 0), Point(2, 1), Point(2, 2)
            ),
            s
        )
    }

    // ------------------------------------------------------------------
    // Growing on food
    // ------------------------------------------------------------------

    @Test
    fun `given food left of head when move left onto food then snake grows`() {
        // given
        val s = snake(Point(0, 0), Point(1, 0), Point(2, 0))
        val food = Point(-1, 0)

        // when
        move(s, Direction.LEFT, food)

        // then
        assertEquals(
            listOf(Point(-1, 0), Point(0, 0), Point(1, 0), Point(2, 0)),
            s
        )
        assertEquals(4, s.size)
    }

    @Test
    fun `given food right of head when move right onto food then snake grows`() {
        // given
        val s = snake(Point(0, 0), Point(0, 1))
        val food = Point(1, 0)

        // when
        move(s, Direction.RIGHT, food)

        // then
        assertEquals(
            listOf(Point(1, 0), Point(0, 0), Point(0, 1)),
            s
        )
    }

    @Test
    fun `given food above head when move up onto food then snake grows`() {
        // given
        val s = snake(Point(0, 0), Point(1, 0))
        val food = Point(0, 1)

        // when
        move(s, Direction.UP, food)

        // then
        assertEquals(
            listOf(Point(0, 1), Point(0, 0), Point(1, 0)),
            s
        )
    }

    @Test
    fun `given food below head when move down onto food then snake grows`() {
        // given
        val s = snake(Point(0, 0), Point(1, 0))
        val food = Point(0, -1)

        // when
        move(s, Direction.DOWN, food)

        // then
        assertEquals(
            listOf(Point(0, -1), Point(0, 0), Point(1, 0)),
            s
        )
    }

    @Test
    fun `given single segment snake when move onto food then snake grows to two`() {
        // given
        val s = snake(Point(5, 5))
        val food = Point(5, 4)

        // when
        move(s, Direction.DOWN, food)

        // then
        assertEquals(
            listOf(Point(5, 4), Point(5, 5)),
            s
        )
    }

    @Test
    fun `given food under body segment when move up then food is ignored and tail drops`() {
        // given: food sits on a body segment, but the new head moves to a different cell
        val s = snake(Point(0, 0), Point(1, 0), Point(2, 0))
        val food = Point(1, 0)

        // when
        move(s, Direction.UP, food)

        // then
        assertEquals(
            listOf(Point(0, 1), Point(0, 0), Point(1, 0)),
            s
        )
        assertEquals(3, s.size) { "must not grow, food is not on the new head cell" }
    }

    @Test
    fun `given food at old tail cell when move up then food is ignored and tail drops`() {
        // given
        val s = snake(Point(0, 0), Point(1, 0), Point(2, 0))
        val food = Point(2, 0)

        // when
        move(s, Direction.UP, food)

        // then
        assertEquals(3, s.size) { "must not grow" }
        assertEquals(Point(0, 1), s[0])
    }

    @Test
    fun `given food ahead of head twice when move left twice then snake grows twice`() {
        // given
        val s = snake(Point(0, 0), Point(1, 0), Point(2, 0))

        // when
        move(s, Direction.LEFT, Point(-1, 0))
        move(s, Direction.LEFT, Point(-2, 0))

        // then
        assertEquals(
            listOf(Point(-2, 0), Point(-1, 0), Point(0, 0), Point(1, 0), Point(2, 0)),
            s
        )
        assertEquals(5, s.size)
    }

    // ------------------------------------------------------------------
    // LinkedList in-place updates
    // ------------------------------------------------------------------

    @Test
    fun `given two segment snake when move without food then tail is dropped in place`() {
        // given
        val s = LinkedList(listOf(Point(0, 0), Point(1, 0)))

        // when
        move(s, Direction.DOWN, farAwayFood)

        // then
        assertEquals(
            listOf(Point(0, -1), Point(0, 0)),
            s
        )
    }

    @Test
    fun `given two segment snake when move onto food then snake grows in place`() {
        // given
        val s = LinkedList(listOf(Point(0, -1), Point(0, 0)))
        val food = Point(0, -2)

        // when
        move(s, Direction.DOWN, food)

        // then
        assertEquals(
            listOf(Point(0, -2), Point(0, -1), Point(0, 0)),
            s
        )
    }

    // ------------------------------------------------------------------
    // Multi-step walks
    // ------------------------------------------------------------------

    @Test
    fun `given three segment snake when walk left twice then down twice then path is followed`() {
        // given
        val s = snake(Point(0, 0), Point(1, 0), Point(2, 0))

        // when
        move(s, Direction.LEFT, farAwayFood)
        move(s, Direction.LEFT, farAwayFood)
        move(s, Direction.DOWN, farAwayFood)
        move(s, Direction.DOWN, farAwayFood)

        // then
        assertEquals(
            listOf(Point(-2, -2), Point(-2, -1), Point(-2, 0)),
            s
        )
    }

    @Test
    fun `given two segment snake when walk over food and empty cells then size grows only on eating`() {
        // given
        val s = snake(Point(0, 0), Point(1, 0))

        // when
        move(s, Direction.LEFT, farAwayFood)      // empty cell
        move(s, Direction.UP, Point(-1, 1))        // food
        move(s, Direction.RIGHT, farAwayFood)      // empty cell
        move(s, Direction.DOWN, Point(0, 0))       // food

        // then
        assertEquals(4, s.size)
        assertEquals(
            listOf(Point(0, 0), Point(0, 1), Point(-1, 1), Point(-1, 0)),
            s
        )
    }

    @Test
    fun `given hundred segment snake when move left ten times then head and tail shift ten cells`() {
        // given: 100 segments laid out horizontally, head at (0, 0), tail at (99, 0)
        val s = LinkedList((0..99).map { Point(it, 0) })
        val expectedSize = s.size

        // when
        repeat(10) { move(s, Direction.LEFT, farAwayFood) }

        // then
        assertEquals(expectedSize, s.size)
        assertEquals(Point(-10, 0), s[0])
        assertEquals(Point(89, 0), s.last())
    }

    @Test
    fun `given five segment snake when move eat and move again then growth and movement combine`() {
        // given: head at (0,0), tail at (4,0)
        val s = LinkedList((0..4).map { Point(it, 0) })

        // when
        move(s, Direction.LEFT, Point(-1, 0))     // grow to 6
        move(s, Direction.DOWN, farAwayFood)      // stay 6
        move(s, Direction.RIGHT, Point(0, -1))    // grow to 7

        // then
        assertEquals(7, s.size)
        assertEquals(Point(0, -1), s[0])
        assertEquals(Point(3, 0), s.last())
        assertEquals(
            listOf(
                Point(0, -1), Point(-1, -1), Point(-1, 0),
                Point(0, 0), Point(1, 0), Point(2, 0), Point(3, 0)
            ),
            s
        )
    }
}
