package problems.snake

import java.util.*

/**
 * A coordinate in the grid. x is the column, y is the row.
 */
data class Point(
    val x: Int,
    val y: Int
)

/**
 * The four directions the snake head can move.
 * See [Point] for the axis conventions.
 */
enum class Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN
}

/**
 * Snake game movement logic.
 *
 * Per the problem statement: [snake] holds the positions of all snake cells in a
 * doubly linked list ([LinkedList]), with `snake.first` as the snake's head and
 * `snake.last` as the snake's tail. The list must be updated in place.
 *
 * Contract:
 * - The body follows the head: each move, a new head is prepended in the given
 *   [direction] and the old tail is removed — unless the new head lands on
 *   [foodPosition], in which case the tail is kept and the snake grows by one segment.
 * - The grid bounds and self-collision checks happen outside [move], so no game
 *   over logic is needed here.
 * - [snake] must be modified in place; [move] returns [Unit] and callers observe
 *   the new state through the very same [LinkedList] instance they passed in.
 *
 * You can use [LinkedList.addFirst], [LinkedList.removeLast]
 *
 * All ends operations on [LinkedList] are O(1) doubly linked list operations,
 * so [move] is O(1).
 */
fun move(snake: LinkedList<Point>, direction: Direction, foodPosition: Point) {
    // 1. calculate the new coords of Head
    // 2. attach the new Head
    // 3. if didn't eat food, remove the tail, this way every segment shifts one cell towards head
    // 4. if ate food, don't remove tail because it grows

    // 1
    val currentHead = snake.first()
    val newHead = when (direction) {
        Direction.LEFT -> currentHead.copy(x = currentHead.x - 1)
        Direction.RIGHT -> currentHead.copy(x = currentHead.x + 1)
        Direction.UP -> currentHead.copy(y = currentHead.y + 1)
        Direction.DOWN -> currentHead.copy(y = currentHead.y - 1)
    }

    // 2
    snake.addFirst(newHead)

    // 3
    if (newHead != foodPosition) {
        snake.removeLast()
    }
}
