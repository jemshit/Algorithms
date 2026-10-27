package graph.shortestpath

import graph.representation.Graph
import java.util.PriorityQueue

private data class QueueEntry<V>(val vertex: V, val distance: Int)

fun <V> dijkstra(graph: Graph<V>, source: V): Map<V, Int> {
    val vertices = graph.vertices().toList()
    require(source in vertices) { "Unknown source vertex: $source" }
    require(vertices.all { vertex -> graph.outgoingEdges(vertex).all { it.weight >= 0 } }) {
        "Dijkstra's algorithm requires non-negative edge weights"
    }

    val distance = vertices.associateWith { Int.MAX_VALUE }.toMutableMap()
    val queue = PriorityQueue<QueueEntry<V>>(compareBy { it.distance })
    distance[source] = 0
    queue.add(QueueEntry(source, 0))

    while (queue.isNotEmpty()) {
        val current = queue.poll()
        if (current.distance != distance.getValue(current.vertex)) continue

        for (edge in graph.outgoingEdges(current.vertex)) {
            val candidate = current.distance + edge.weight
            if (candidate < distance.getValue(edge.to)) {
                distance[edge.to] = candidate
                queue.add(QueueEntry(edge.to, candidate))
            }
        }
    }
    return distance
}
