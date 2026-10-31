package graph.dag

import graph.representation.Graph

// To determine Cycle:
// A Missing means unvisited; VISITING means on the current DFS path.
private enum class VisitState { VISITING, FINISHED }

/** DFS topological sort: add each vertex after its descendants, then reverse the result. */
fun <V> topologicalSortDfs(graph: Graph<V>): List<V> {
    require(graph.directed) { "Topological sorting requires a directed graph" }

    val visitState = mutableMapOf<V, VisitState>()
    val postorder = mutableListOf<V>()

    fun dfsVisit(vertex: V) {
        visitState[vertex] = VisitState.VISITING

        for (edge in graph.outgoingEdges(vertex)) {
            when (visitState[edge.to]) {
                // Reaching a vertex on the current DFS path means there is a cycle.
                VisitState.VISITING -> throw IllegalArgumentException("Graph contains a directed cycle")
                VisitState.FINISHED -> Unit
                null -> dfsVisit(edge.to)
            }
        }

        visitState[vertex] = VisitState.FINISHED
        postorder.add(vertex)
    }

    // for disconnected graph
    for (vertex in graph.vertices()) {
        if (vertex !in visitState) {
            dfsVisit(vertex)
        }
    }

    postorder.reverse()
    return postorder
}

/** Kahn's topological sort: repeatedly remove a vertex with no incoming edges. */
fun <V> topologicalSortKahn(graph: Graph<V>): List<V> {
    require(graph.directed) { "Topological sorting requires a directed graph" }

    val vertices = graph.vertices().toList()
    val inDegree = vertices.associateWith { 0 }.toMutableMap()

    // Count the incoming edges of every vertex.
    for (vertex in vertices) {
        for (edge in graph.outgoingEdges(vertex)) {
            inDegree[edge.to] = inDegree.getValue(edge.to) + 1
        }
    }

    // Vertices with no unmet dependencies are ready to process
    //   starting vertex with no incoming edges
    val queue = ArrayDeque<V>()
    for (vertex in vertices) {
        if (inDegree.getValue(vertex) == 0) {
            queue.addLast(vertex)
        }
    }

    val result = mutableListOf<V>()
    while (queue.isNotEmpty()) {
        val vertex = queue.removeFirst()
        result.add(vertex)

        // Processing vertex removes its outgoing edges from consideration.
        for (edge in graph.outgoingEdges(vertex)) {
            val remaining = inDegree.getValue(edge.to) - 1
            inDegree[edge.to] = remaining
            // The neighbor is ready once all its incoming edges are removed.
            if (remaining == 0) {
                queue.addLast(edge.to)
            }
        }
    }

    // A cycle leaves some vertices with an in-degree greater than zero.
    require(result.size == graph.vertexCount) { "Graph contains a directed cycle" }
    return result
}
