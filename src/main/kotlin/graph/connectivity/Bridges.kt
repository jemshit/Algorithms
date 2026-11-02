package graph.connectivity

import graph.representation.Graph

/** Finds all bridges using DFS in O(V+E). */
fun <V> findBridges(graph: Graph<V>): List<Pair<V, V>> {
    require(!graph.directed) { "Bridges are defined here for undirected graphs" }

    val visitOrder = mutableMapOf<V, Int>()
    // updated based on min(node, neighbour)
    val earliestReachableVisitOrder = mutableMapOf<V, Int>()
    val bridges = mutableListOf<Pair<V, V>>()
    var nextVisitOrder = 0

    fun dfsVisit(vertex: V, parent: V?) {
        visitOrder[vertex] = nextVisitOrder
        earliestReachableVisitOrder[vertex] = nextVisitOrder
        nextVisitOrder++

        for (edge in graph.outgoingEdges(vertex)) {
            val neighbor = edge.to
            if (neighbor == parent) continue

            if (neighbor !in visitOrder) {
                dfsVisit(neighbor, vertex)
                // updated [visitOrder, earliestReachableVisitOrder] for neighbor

                // Include any earlier vertex the neighbor's subtree can reach.
                earliestReachableVisitOrder[vertex] = minOf(
                    earliestReachableVisitOrder.getValue(vertex),
                    earliestReachableVisitOrder.getValue(neighbor)
                )

                // The neighbor has no alternative path to this vertex or any earlier vertex.
                if (earliestReachableVisitOrder.getValue(neighbor) > visitOrder.getValue(vertex)) {
                    bridges.add(vertex to neighbor)
                }
            } else {
                // A back edge provides an alternative path to an earlier visited vertex.
                earliestReachableVisitOrder[vertex] = minOf(
                    earliestReachableVisitOrder.getValue(vertex),
                    visitOrder.getValue(neighbor)
                )
            }
        }
    }

    // start a new DFS for each disconnected component.
    for (vertex in graph.vertices()) {
        if (vertex !in visitOrder) {
            dfsVisit(vertex, null)
        }
    }

    return bridges
}
