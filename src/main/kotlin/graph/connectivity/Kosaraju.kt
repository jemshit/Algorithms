package graph.connectivity

import graph.representation.Graph

/** Finds strongly connected components with Kosaraju's two DFS passes in O(V + E). */
fun <V> kosarajuScc(graph: Graph<V>): List<Set<V>> {
    require(graph.directed) { "Strongly connected components require a directed graph" }

    val vertices = graph.vertices().toList()
    val visited = mutableSetOf<V>()
    val finishOrder = mutableListOf<V>()

    fun dfsFinish(vertex: V) {
        visited.add(vertex)

        for (edge in graph.outgoingEdges(vertex)) {
            val neighbor = edge.to
            if (neighbor !in visited) {
                dfsFinish(neighbor)
            }
        }

        // Add the vertex after all outgoing paths have been explored.
        finishOrder.add(vertex)
    }

    // First DFS: record every vertex in finishing order.
    for (vertex in vertices) {
        if (vertex !in visited) {
            dfsFinish(vertex)
        }
    }

    // Reverse every edge: vertex -> neighbor becomes neighbor -> vertex.
    val incomingNeighbors = vertices.associateWith { mutableListOf<V>() }
    for (vertex in vertices) {
        for (edge in graph.outgoingEdges(vertex)) {
            incomingNeighbors.getValue(edge.to).add(vertex)
        }
    }

    visited.clear()
    val components = mutableListOf<Set<V>>()

    fun dfsCollect(vertex: V, component: MutableSet<V>) {
        visited.add(vertex)
        component.add(vertex)

        for (neighbor in incomingNeighbors.getValue(vertex)) {
            if (neighbor !in visited) {
                dfsCollect(neighbor, component)
            }
        }
    }

    // Second DFS: collect one SCC at a time, from latest to earliest finish time.
    for (vertex in finishOrder.asReversed()) {
        if (vertex in visited) continue

        val component = linkedSetOf<V>()
        dfsCollect(vertex, component)
        components.add(component)
    }

    return components
}
