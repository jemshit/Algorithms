package graph.connectivity

import graph.representation.Graph

fun <V> findBridges(graph: Graph<V>): List<Pair<V, V>> {
    require(!graph.directed) { "Bridges are defined here for undirected graphs" }

    val discovery = mutableMapOf<V, Int>()
    val low = mutableMapOf<V, Int>()
    val bridges = mutableListOf<Pair<V, V>>()
    var time = 0

    fun visit(vertex: V, parent: V?) {
        discovery[vertex] = time
        low[vertex] = time++

        for (edge in graph.outgoingEdges(vertex)) {
            val neighbor = edge.to
            if (neighbor == parent) continue

            if (neighbor !in discovery) {
                visit(neighbor, vertex)
                low[vertex] = minOf(low.getValue(vertex), low.getValue(neighbor))

                if (low.getValue(neighbor) > discovery.getValue(vertex)) {
                    bridges.add(vertex to neighbor)
                }
            } else {
                low[vertex] = minOf(low.getValue(vertex), discovery.getValue(neighbor))
            }
        }
    }

    for (vertex in graph.vertices()) {
        if (vertex !in discovery) visit(vertex, null)
    }
    return bridges
}
