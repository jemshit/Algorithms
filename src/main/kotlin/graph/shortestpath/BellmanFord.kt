package graph.shortestpath

import graph.representation.Graph

private data class Arc<V>(val from: V, val to: V, val weight: Int)

fun <V> bellmanFord(graph: Graph<V>, source: V): Map<V, Int> {
    val vertices = graph.vertices().toList()
    require(source in vertices) { "Unknown source vertex: $source" }

    val arcs = vertices.flatMap { from ->
        graph.outgoingEdges(from).map { edge -> Arc(from, edge.to, edge.weight) }
    }
    val distance = vertices.associateWith { Int.MAX_VALUE }.toMutableMap()
    distance[source] = 0

    for (pass in 1 until graph.vertexCount) {
        var changed = false
        for (arc in arcs) {
            val fromDistance = distance.getValue(arc.from)
            if (fromDistance == Int.MAX_VALUE || fromDistance == Int.MIN_VALUE) continue

            val candidate = fromDistance + arc.weight
            if (candidate < distance.getValue(arc.to)) {
                distance[arc.to] = candidate
                changed = true
            }
        }
        if (!changed) break
    }

    // A vertex marked MIN_VALUE is reachable from a negative cycle.
    repeat(graph.vertexCount) {
        for (arc in arcs) {
            val fromDistance = distance.getValue(arc.from)
            if (fromDistance == Int.MAX_VALUE) continue

            if (fromDistance == Int.MIN_VALUE || fromDistance + arc.weight < distance.getValue(arc.to)) {
                distance[arc.to] = Int.MIN_VALUE
            }
        }
    }
    return distance
}
