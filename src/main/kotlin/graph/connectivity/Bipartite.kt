package graph.connectivity

import graph.representation.Graph

/** Checks whether an undirected graph is bipartite using BFS two-coloring in O(V+E). */
fun <V> isBipartite(graph: Graph<V>): Boolean {
    require(!graph.directed) { "Bipartite checking requires an undirected graph" }

    // The two Boolean values represent the two partitions.
    // A vertex in this map has already been visited.
    val colors = mutableMapOf<V, Boolean>()

    // Check every component because the graph may be disconnected.
    for (start in graph.vertices()) {
        if (start in colors) continue

        // BFS
        colors[start] = false
        val queue = ArrayDeque<V>()
        queue.addLast(start)

        while (queue.isNotEmpty()) {
            val vertex = queue.removeFirst()
            val vertexColor = colors.getValue(vertex)

            for (edge in graph.outgoingEdges(vertex)) {
                val neighbor = edge.to

                // A self-loop connects a vertex to the same color.
                if (neighbor == vertex) return false

                val neighborColor = colors[neighbor]
                if (neighborColor == null) {
                    // Adjacent vertices must belong to opposite partitions.
                    colors[neighbor] = !vertexColor
                    queue.addLast(neighbor)
                } else if (neighborColor == vertexColor) {
                    // An edge within one partition means the graph is not bipartite.
                    return false
                }
            }
        }
    }
    return true
}
