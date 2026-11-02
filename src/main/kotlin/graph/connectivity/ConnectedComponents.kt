package graph.connectivity

import graph.representation.Graph

data class ConnectedComponents<V>(
    val componentOf: Map<V, Int>,
    val count: Int
)

/** Finds connected components using DFS in O(V+E). */
fun <V> findConnectedComponents(graph: Graph<V>): ConnectedComponents<V> {
    require(!graph.directed) { "Connected components require an undirected graph" }

    val componentOf = mutableMapOf<V, Int>()
    var componentCount = 0

    fun dfsVisit(vertex: V, componentId: Int) {
        // Assigning a component also marks the vertex as visited.
        componentOf[vertex] = componentId

        for (edge in graph.outgoingEdges(vertex)) {
            val neighbor = edge.to
            if (neighbor !in componentOf) {
                dfsVisit(neighbor, componentId)
            }
        }
    }

    // Every unassigned vertex starts a new connected component.
    for (vertex in graph.vertices()) {
        if (vertex !in componentOf) {
            dfsVisit(vertex, componentCount)
            componentCount++
        }
    }

    return ConnectedComponents(
        componentOf = componentOf,
        count = componentCount
    )
}
