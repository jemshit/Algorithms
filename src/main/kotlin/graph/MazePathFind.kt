package graph

import java.util.*

fun Grid.findMazePathBfs(
    start: Grid.Coordinate,
    end: Grid.Coordinate
): List<Grid.Coordinate> {
    if (start.x >= this.rowCount || end.x >= this.rowCount) throw IllegalArgumentException()
    if (start.y >= this.columnCount || end.y >= this.columnCount) throw IllegalArgumentException()

    val pathToEnd = mutableListOf<Grid.Coordinate>()
    val prevCells = bfsExplore(start, end)
    if (prevCells.isEmpty()) return listOf()
    var cell = end
    while (!(cell.x == prevCells[cell.x][cell.y].x && cell.y == prevCells[cell.x][cell.y].y)) {
        pathToEnd.add(cell)
        cell = prevCells[cell.x][cell.y]
    }
    pathToEnd.add(cell)

    return pathToEnd.reversed()
}

private fun Grid.bfsExplore(
    start: Grid.Coordinate,
    end: Grid.Coordinate
): Array<Array<Grid.Coordinate>> {

    var foundEnd = false
    val prevCells: Array<Array<Grid.Coordinate>> = Array<Array<Grid.Coordinate>>(size = this.rowCount) {
        Array<Grid.Coordinate>(size = this.columnCount) { Grid.Coordinate(-1, -1) }
    }
    val queue: Queue<Grid.Coordinate> = LinkedList<Grid.Coordinate>()
    val visited: Array<Array<Boolean>> = Array<Array<Boolean>>(size = this.rowCount) {
        Array<Boolean>(size = this.columnCount) { false }
    }

    queue.offer(start)
    visited[start.x][start.y] = true
    prevCells[start.x][start.y] = start

    while (queue.isNotEmpty()) {
        val cell = queue.poll()
        if (cell.x == end.x && cell.y == end.y) {
            foundEnd = true
            break
        }

        val neighbours = this.getNeighbours(cell.x, cell.y)
        for (neighbourCell in neighbours) {
            if (!visited[neighbourCell.x][neighbourCell.y]) {
                queue.offer(neighbourCell)
                visited[neighbourCell.x][neighbourCell.y] = true
                prevCells[neighbourCell.x][neighbourCell.y] = cell
            }
        }
    }

    return if (foundEnd) prevCells else arrayOf()
}
