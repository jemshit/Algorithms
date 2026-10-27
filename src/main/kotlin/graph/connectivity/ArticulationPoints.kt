package graph.connectivity

import graph.representation.Graph

fun <V> findArticulationPoints(graph: Graph<V>): Set<V> {
    require(!graph.directed) { "Articulation points are defined here for undirected graphs" }

    val discovery = mutableMapOf<V, Int>()
    val low = mutableMapOf<V, Int>()
    val result = linkedSetOf<V>()
    var time = 0

    fun visit(vertex: V, parent: V?) {
        discovery[vertex] = time
        low[vertex] = time++
        var childCount = 0

        for (edge in graph.outgoingEdges(vertex)) {
            val neighbor = edge.to
            if (neighbor == parent) continue

            if (neighbor !in discovery) {
                childCount++
                visit(neighbor, vertex)
                low[vertex] = minOf(low.getValue(vertex), low.getValue(neighbor))

                if (parent != null && low.getValue(neighbor) >= discovery.getValue(vertex)) {
                    result.add(vertex)
                }
            } else {
                low[vertex] = minOf(low.getValue(vertex), discovery.getValue(neighbor))
            }
        }

        if (parent == null && childCount > 1) result.add(vertex)
    }

    for (vertex in graph.vertices()) {
        if (vertex !in discovery) visit(vertex, null)
    }
    return result
}
