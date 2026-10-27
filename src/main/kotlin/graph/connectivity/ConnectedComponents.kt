package graph.connectivity

import graph.representation.Graph

data class ConnectedComponents<V>(
    val componentOf: Map<V, Int>,
    val count: Int
)

fun <V> findConnectedComponents(graph: Graph<V>): ConnectedComponents<V> {
    require(!graph.directed) { "Connected components require an undirected graph" }

    val componentOf = mutableMapOf<V, Int>()
    var component = 0

    fun visit(vertex: V) {
        componentOf[vertex] = component
        for (edge in graph.outgoingEdges(vertex)) {
            if (edge.to !in componentOf) visit(edge.to)
        }
    }

    for (vertex in graph.vertices()) {
        if (vertex !in componentOf) {
            visit(vertex)
            component++
        }
    }
    return ConnectedComponents(componentOf, component)
}
