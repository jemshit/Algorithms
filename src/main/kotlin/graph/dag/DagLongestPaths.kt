package graph.dag

import graph.representation.Graph

/**
 * Finds longest distances from [source] in O(V + E), including with negative edge weights.
 * Unreachable vertices keep a distance of [Int.MIN_VALUE].
 */
fun <V> dagLongestPaths(graph: Graph<V>, source: V): Map<V, Int> {
    val vertices = graph.vertices().toList()
    require(source in vertices) { "Unknown source vertex: $source" }

    // longest source -> node
    val distances = mutableMapOf<V, Int>()
    for (vertex in vertices) {
        distances[vertex] = Int.MIN_VALUE
    }
    distances[source] = 0

    // Topological order processes every predecessor before its dependent vertices.
    for (vertex in topologicalSortDfs(graph)) {
        val currentDistance = distances.getValue(vertex)

        // An unreachable vertex cannot improve any of its neighbors.
        if (currentDistance == Int.MIN_VALUE) continue

        // Relax every outgoing edge, keeping the greatest distance found so far.
        for (edge in graph.outgoingEdges(vertex)) {
            val candidate = currentDistance + edge.weight
            if (candidate > distances.getValue(edge.to)) {
                distances[edge.to] = candidate
            }
        }
    }

    return distances
}
