package graph.dag

import graph.representation.Graph

fun <V> dagLongestPaths(graph: Graph<V>, source: V): Map<V, Int> {
    val vertices = graph.vertices().toList()
    require(source in vertices) { "Unknown source vertex: $source" }

    val distance = vertices.associateWith { Int.MIN_VALUE }.toMutableMap()
    distance[source] = 0

    for (vertex in topologicalSort(graph)) {
        val currentDistance = distance.getValue(vertex)
        if (currentDistance == Int.MIN_VALUE) continue

        for (edge in graph.outgoingEdges(vertex)) {
            val candidate = currentDistance + edge.weight
            if (candidate > distance.getValue(edge.to)) distance[edge.to] = candidate
        }
    }
    return distance
}
