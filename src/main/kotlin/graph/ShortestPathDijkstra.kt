package graph

import java.util.*

private data class NodeDistancePair(val node: Int, val distance: Int) : Comparable<NodeDistancePair> {
    override fun compareTo(other: NodeDistancePair): Int {
        return distance.compareTo(other.distance)
    }
}

fun GraphUsingAdjacencyList.shortestPathDijkstra(): Array<Int> {
    val shortestPaths = arrayOfNulls<Int>(nodeCount)
    shortestPaths[0] = 0
    val priorityQueue = PriorityQueue<NodeDistancePair>() // node, distance
    priorityQueue.add(NodeDistancePair(0, 0))
    val visited: Array<Boolean> = Array<Boolean>(nodeCount) { false }

    while (priorityQueue.isNotEmpty()) {
        val (node, distance) = priorityQueue.poll()
        visited[node] = true

        if (shortestPaths[node] != null && shortestPaths[node]!! < distance)
            continue

        shortestPaths[node] = distance
        for (edge in getEdges(node)) {
            val neighbour = edge.end
            // You can't get shorter path if it is visited before
            if (!visited[neighbour]) {
                val neighbourDistance = shortestPaths[node]!! + (edge.weight ?: 0)
                shortestPaths[neighbour] = neighbourDistance
                priorityQueue.add(NodeDistancePair(neighbour, neighbourDistance))
            }
        }
    }
    return shortestPaths as Array<Int>
}