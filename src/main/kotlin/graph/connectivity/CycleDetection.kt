package graph.connectivity

import graph.representation.Graph

private enum class VisitState { VISITING, FINISHED }

fun <V> hasUndirectedCycle(graph: Graph<V>): Boolean {
    require(!graph.directed) { "Use directed cycle detection for a directed graph" }
    val visited = mutableSetOf<V>()

    fun visit(vertex: V, parent: V?): Boolean {
        visited.add(vertex)
        for (edge in graph.outgoingEdges(vertex)) {
            if (edge.to !in visited) {
                if (visit(edge.to, vertex)) return true
            } else if (edge.to != parent) {
                return true
            }
        }
        return false
    }

    return graph.vertices().any { it !in visited && visit(it, null) }
}

fun <V> hasDirectedCycle(graph: Graph<V>): Boolean {
    require(graph.directed) { "Use undirected cycle detection for an undirected graph" }

    val state = mutableMapOf<V, VisitState>()

    fun visit(vertex: V): Boolean {
        state[vertex] = VisitState.VISITING
        for (edge in graph.outgoingEdges(vertex)) {
            when (state[edge.to]) {
                VisitState.VISITING -> return true
                VisitState.FINISHED -> Unit
                null -> if (visit(edge.to)) return true
            }
        }
        state[vertex] = VisitState.FINISHED
        return false
    }

    return graph.vertices().any { it !in state && visit(it) }
}
