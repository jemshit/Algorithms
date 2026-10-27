package graph.dag

import graph.representation.Graph

private enum class VisitState { VISITING, FINISHED }

fun <V> topologicalSort(graph: Graph<V>): List<V> {
    require(graph.directed) { "Topological sorting requires a directed graph" }

    val state = mutableMapOf<V, VisitState>()
    val reverseOrder = mutableListOf<V>()

    fun visit(vertex: V) {
        state[vertex] = VisitState.VISITING

        for (edge in graph.outgoingEdges(vertex)) {
            when (state[edge.to]) {
                VisitState.VISITING -> throw IllegalArgumentException("Graph contains a directed cycle")
                VisitState.FINISHED -> Unit
                null -> visit(edge.to)
            }
        }

        state[vertex] = VisitState.FINISHED
        reverseOrder.add(vertex)
    }

    for (vertex in graph.vertices()) {
        if (vertex !in state) visit(vertex)
    }

    reverseOrder.reverse()
    return reverseOrder
}
