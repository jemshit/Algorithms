package graph.grid

// Finds a shortest path when every grid move has the same cost.
fun bfsPath(
    grid: Grid,
    start: Coordinate,
    end: Coordinate
): List<Coordinate> {
    require(start.row in 0 until grid.rowCount && start.column in 0 until grid.columnCount) {
        "Start coordinate is outside the grid: $start"
    }
    require(end.row in 0 until grid.rowCount && end.column in 0 until grid.columnCount) {
        "End coordinate is outside the grid: $end"
    }

    // previous[cell] records which cell first reached it.
    val previous = mutableMapOf(start to start)
    val visited = mutableSetOf(start)
    val queue = ArrayDeque<Coordinate>()
    queue.addLast(start)

    while (queue.isNotEmpty()) {
        val cell = queue.removeFirst()
        if (cell == end) break

        for (neighbor in grid.getNeighbours(cell)) {
            if (neighbor !in visited) {
                // Mark when enqueued so the same cell is never queued twice.
                visited.add(neighbor)
                previous[neighbor] = cell
                queue.addLast(neighbor)
            }
        }
    }

    // couldn't find (maybe obstacle)
    if (end !in previous) return emptyList()

    // Follow predecessors from end to start, then reverse the path.
    val path = mutableListOf<Coordinate>()
    var cell = end
    while (cell != start) {
        path.add(cell)
        cell = previous.getValue(cell)
    }
    path.add(start)

    return path.reversed()
}
