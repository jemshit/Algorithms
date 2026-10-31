package graph.traversal

import graph.representation.Graph

// O(V + E) time, O(V) extra space.
fun <V> bfs(graph: Graph<V>, start: V): List<V> {
    require(start in graph.vertices()) { "Unknown start vertex: $start" }

    val result = mutableListOf<V>()
    val visited = mutableSetOf(start)
    val queue = ArrayDeque<V>()
    queue.addLast(start)

    while (queue.isNotEmpty()) {
        val vertex = queue.removeFirst()
        result.add(vertex)

        for (edge in graph.outgoingEdges(vertex)) {
            if (edge.to !in visited) {
                visited.add(edge.to)
                queue.addLast(edge.to)
            }
        }
    }

    return result
}
