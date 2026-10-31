package graph.dag

import graph.representation.Graph

/**
 * Finds shortest distances from [source] in O(V + E), including with negative edge weights.
 * Unreachable vertices keep a distance of [Int.MAX_VALUE].
 */
fun <V> dagShortestPaths(graph: Graph<V>, source: V): Map<V, Int> {
    val vertices = graph.vertices().toList()
    require(source in vertices) { "Unknown source vertex: $source" }

    // shortest source -> node
    val distances = mutableMapOf<V, Int>()
    for (vertex in vertices) {
        distances[vertex] = Int.MAX_VALUE
    }
    distances[source] = 0

    // Topological order processes every predecessor before its dependent vertices.
    for (vertex in topologicalSortDfs(graph)) {
        val currentDistance = distances.getValue(vertex)

        // An unreachable vertex cannot improve any of its neighbors.
        if (currentDistance == Int.MAX_VALUE) continue

        // Relax every outgoing edge.
        for (edge in graph.outgoingEdges(vertex)) {
            val candidate = currentDistance + edge.weight
            if (candidate < distances.getValue(edge.to)) {
                distances[edge.to] = candidate
            }
        }
    }

    return distances
}
