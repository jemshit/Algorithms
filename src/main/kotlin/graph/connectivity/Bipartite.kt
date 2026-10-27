package graph.connectivity

import graph.representation.Graph

fun <V> isBipartite(graph: Graph<V>): Boolean {
    require(!graph.directed) { "Bipartite checking requires an undirected graph" }

    val color = mutableMapOf<V, Boolean>()

    for (source in graph.vertices()) {
        if (source in color) continue

        color[source] = false
        val queue = ArrayDeque<V>()
        queue.addLast(source)

        while (queue.isNotEmpty()) {
            val vertex = queue.removeFirst()
            for (edge in graph.outgoingEdges(vertex)) {
                if (edge.to == vertex) return false

                val neighborColor = color[edge.to]
                if (neighborColor == null) {
                    color[edge.to] = !color.getValue(vertex)
                    queue.addLast(edge.to)
                } else if (neighborColor == color.getValue(vertex)) {
                    return false
                }
            }
        }
    }
    return true
}
