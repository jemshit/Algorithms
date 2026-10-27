package graph.dag

import graph.representation.Graph
import java.util.ArrayDeque

/** Kahn's O(V + E) topological sort using in-degrees instead of DFS recursion. */
fun <V> kahnTopologicalSort(graph: Graph<V>): List<V> {
    require(graph.directed) { "Topological sorting requires a directed graph" }

    val vertices = graph.vertices().toList()
    val inDegree = vertices.associateWith { 0 }.toMutableMap()

    for (vertex in vertices) {
        for (edge in graph.outgoingEdges(vertex)) {
            inDegree[edge.to] = inDegree.getValue(edge.to) + 1
        }
    }

    val ready = ArrayDeque<V>()
    for (vertex in vertices) {
        if (inDegree.getValue(vertex) == 0) ready.addLast(vertex)
    }

    val order = mutableListOf<V>()
    while (ready.isNotEmpty()) {
        val vertex = ready.removeFirst()
        order.add(vertex)

        for (edge in graph.outgoingEdges(vertex)) {
            val remaining = inDegree.getValue(edge.to) - 1
            inDegree[edge.to] = remaining
            if (remaining == 0) ready.addLast(edge.to)
        }
    }

    require(order.size == graph.vertexCount) { "Graph contains a directed cycle" }
    return order
}
