package graph.shortestpath

import graph.representation.Graph
import java.util.PriorityQueue

private data class VertexDistance<V>(
    val vertex: V,
    val distance: Int
)

/**
 * Finds shortest distances from [source] in O((V + E) log V) with an adjacency list and binary heap.
 * Edge weights must be non-negative; unreachable vertices keep a distance of [Int.MAX_VALUE].
 */
fun <V> dijkstra(graph: Graph<V>, source: V): Map<V, Int> {
    val vertices = graph.vertices().toList()
    require(source in vertices) { "Unknown source vertex: $source" }
    require(vertices.all { vertex -> graph.outgoingEdges(vertex).all { it.weight >= 0 } }) {
        "Dijkstra's algorithm requires non-negative edge weights"
    }

    val distances = mutableMapOf<V, Int>()
    for (vertex in vertices) {
        distances[vertex] = Int.MAX_VALUE
    }
    distances[source] = 0

    val priorityQueue = PriorityQueue<VertexDistance<V>>(compareBy { it.distance })
    priorityQueue.add(VertexDistance(source, 0))

    while (priorityQueue.isNotEmpty()) {
        // next promising node
        val (vertex, currentDistance) = priorityQueue.poll()

        // Skip a stale entry if a shorter path to this vertex was found after it was queued
        //   we don't use visited array
        if (currentDistance > distances.getValue(vertex))
            continue

        // For a single-target version, we can return here
        //   target's distance is final once its non-stale entry is removed from the priority queue.
        // if (vertex == target) return currentDistance

        for (edge in graph.outgoingEdges(vertex)) {
            val candidate = currentDistance + edge.weight
            if (candidate < distances.getValue(edge.to)) {
                distances[edge.to] = candidate
                priorityQueue.add(VertexDistance(edge.to, candidate))
            }
        }
    }

    return distances
}
