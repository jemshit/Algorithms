package graph.shortestpath

import graph.representation.Graph

/**
 * Finds shortest distances from [source] in O(VE).
 * Negative edge weights are allowed; unreachable vertices keep [Int.MAX_VALUE].
 * Vertices reachable from a negative cycle are marked with [Int.MIN_VALUE].
 */
fun <V> bellmanFord(graph: Graph<V>, source: V): Map<V, Int> {
    val vertices = graph.vertices().toList()
    require(source in vertices) { "Unknown source vertex: $source" }

    // all edges
    val edges = vertices.flatMap { graph.outgoingEdges(it) }

    val distances = mutableMapOf<V, Int>()
    for (vertex in vertices) {
        distances[vertex] = Int.MAX_VALUE
    }
    distances[source] = 0

    // for each node
    for (pass in 0 until vertices.size - 1) {
        var distanceChanged = false

        // check all edges
        for (edge in edges) {
            val fromDistance = distances.getValue(edge.from)

            // Skip this edge when its starting vertex is unreachable from the source.
            if (fromDistance == Int.MAX_VALUE)
                continue

            val candidate = fromDistance + edge.weight
            if (candidate < distances.getValue(edge.to)) {
                distances[edge.to] = candidate
                distanceChanged = true
            }
        }

        // No distance changed, so later passes cannot improve anything.
        if (!distanceChanged) break
    }

    // Any further improvement proves that a reachable negative cycle exists.
    // Repeated scans propagate MIN_VALUE to every vertex reachable from that cycle.
    for (pass in 0 until vertices.size) {
        for (edge in edges) {
            val fromDistance = distances.getValue(edge.from)

            // Skip this edge when its starting vertex is unreachable from the source.
            if (fromDistance == Int.MAX_VALUE)
                continue

            // Mark vertices on or reachable from a negative cycle
            if (fromDistance == Int.MIN_VALUE
                || fromDistance + edge.weight < distances.getValue(edge.to)) {
                distances[edge.to] = Int.MIN_VALUE
            }
        }
    }

    return distances
}
