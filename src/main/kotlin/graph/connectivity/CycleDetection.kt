package graph.connectivity

import graph.representation.Graph

// Missing means unvisited; VISITING means on the current DFS path;
// FINISHED means all outgoing paths have been explored.
private enum class VisitState { VISITING, FINISHED }

/** Detects cycles in an undirected graph using DFS in O(V+E). */
fun <V> hasUndirectedCycle(graph: Graph<V>): Boolean {
    require(!graph.directed) { "Use directed cycle detection for a directed graph" }
    val visited = mutableSetOf<V>()

    fun dfsVisit(vertex: V, parent: V?): Boolean {
        visited.add(vertex)

        for (edge in graph.outgoingEdges(vertex)) {
            val neighbor = edge.to

            if (neighbor !in visited) {
                if (dfsVisit(neighbor, vertex)){
                    return true
                }
            } else if (neighbor != parent) {
                // A visited neighbor other than the parent leads back into the DFS tree.
                return true
            }
        }
        return false
    }

    // Start a new DFS for each disconnected component.
    for (vertex in graph.vertices()) {
        if (vertex !in visited && dfsVisit(vertex, null)) {
            return true
        }
    }

    return false
}

/** Detects cycles in a directed graph using DFS states in O(V+E). */
fun <V> hasDirectedCycle(graph: Graph<V>): Boolean {
    require(graph.directed) { "Use undirected cycle detection for an undirected graph" }

    val visitState = mutableMapOf<V, VisitState>()

    fun dfsVisit(vertex: V): Boolean {
        visitState[vertex] = VisitState.VISITING

        for (edge in graph.outgoingEdges(vertex)) {
            val neighbor = edge.to

            when (visitState[neighbor]) {
                // Reaching a vertex on the current DFS path means there is a cycle.
                VisitState.VISITING -> return true

                // This vertex and all paths from it were already checked.
                VisitState.FINISHED -> Unit

                null -> {
                    if (dfsVisit(neighbor)) return true
                }
            }
        }

        visitState[vertex] = VisitState.FINISHED
        return false
    }

    // Start a new DFS for each disconnected component.
    for (vertex in graph.vertices()) {
        if (vertex !in visitState && dfsVisit(vertex)) {
            return true
        }
    }

    return false
}
