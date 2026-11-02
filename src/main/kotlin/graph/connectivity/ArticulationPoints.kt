package graph.connectivity

import graph.representation.Graph

/** Finds all articulation points using DFS in O(V+E). */
fun <V> findArticulationPoints(graph: Graph<V>): Set<V> {
    require(!graph.directed) { "Articulation points are defined here for undirected graphs" }

    val visitOrder = mutableMapOf<V, Int>()
    // updated based on min(node, neighbour)
    val earliestReachableVisitOrder = mutableMapOf<V, Int>()
    val articulationPoints = linkedSetOf<V>()
    var nextVisitOrder = 0

    fun dfsVisit(vertex: V, parent: V?) {
        visitOrder[vertex] = nextVisitOrder
        earliestReachableVisitOrder[vertex] = nextVisitOrder
        nextVisitOrder++
        var dfsChildCount = 0

        for (edge in graph.outgoingEdges(vertex)) {
            val neighbor = edge.to
            if (neighbor == parent) continue

            if (neighbor !in visitOrder) {
                dfsChildCount++
                dfsVisit(neighbor, vertex)
                // updated [visitOrder, earliestReachableVisitOrder] for neighbor

                // Include any earlier vertex the neighbor's subtree can reach.
                earliestReachableVisitOrder[vertex] = minOf(
                    earliestReachableVisitOrder.getValue(vertex),
                    earliestReachableVisitOrder.getValue(neighbor)
                )

                // The neighbor's subtree cannot reach an earlier vertex without this vertex.
                if (parent != null &&
                    earliestReachableVisitOrder.getValue(neighbor) >= visitOrder.getValue(vertex)
                ) {
                    articulationPoints.add(vertex)
                }
            } else {
                // A back edge provides an alternative path to an earlier visited vertex.
                earliestReachableVisitOrder[vertex] = minOf(
                    earliestReachableVisitOrder.getValue(vertex),
                    visitOrder.getValue(neighbor)
                )
            }
        }

        // A DFS root is an articulation point when it has multiple independent child subtrees.
        if (parent == null && dfsChildCount > 1) {
            articulationPoints.add(vertex)
        }
    }

    // start a new DFS for each disconnected component.
    for (vertex in graph.vertices()) {
        if (vertex !in visitOrder) {
            dfsVisit(vertex, null)
        }
    }

    return articulationPoints
}
