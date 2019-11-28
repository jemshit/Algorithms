package graph

import java.util.*

data class NodeDistance(val node: Int, val distance: Int) : Comparable<NodeDistance> {
    override fun compareTo(other: NodeDistance): Int {
        return distance.compareTo(other.distance)
    }
}

fun GraphUsingAdjacencyList.shortestPathDijkstra(): Array<Int> {
    val shortest = Array<Int>(nodeCount) { Int.MAX_VALUE }
    val priorityQueue = PriorityQueue<NodeDistance>()
    val visited: Array<Boolean> = Array<Boolean>(nodeCount) { false }

    shortest[0] = 0
    priorityQueue.add(NodeDistance(0, 0))

    while (priorityQueue.isNotEmpty()) {
        val (node, distance) = priorityQueue.poll()
        visited[node] = true

        if (shortest[node] < distance)
            continue

        shortest[node] = distance
        for (edge in getEdges(node)) {
            val neighbour = edge.end
            // Dijkstra's algorithm processes each next promising node in order.
            // If destination node has been visited, its shortest distance will not change
            if (!visited[neighbour]) {
                val neighbourDistance = shortest[node] + (edge.weight ?: 0)
                shortest[neighbour] = neighbourDistance
                priorityQueue.add(NodeDistance(neighbour, neighbourDistance))
            }
        }
    }
    return shortest
}