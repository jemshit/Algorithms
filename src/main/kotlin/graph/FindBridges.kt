package graph

import kotlin.math.min

fun GraphUsingAdjacencyList.findBridges(): List<GraphUsingAdjacencyList.Edge> {
    val bridges = mutableListOf<GraphUsingAdjacencyList.Edge>()
    val visited = Array<Boolean>(this.nodeCount) { false }
    var order = 0
    val visitOrder = Array<Int>(this.nodeCount) { -1 }
    val earlyVisitOrder = Array<Int>(this.nodeCount) { -1 }

    // similar to articulation problem solution
    fun dfs(node: Int, parent: Int) {
        visited[node] = true
        order += 1
        visitOrder[node] = order
        earlyVisitOrder[node] = order

        for (edge in getEdges(node)) {
            val neighbor = edge.end
            if (neighbor == parent) continue

            if (!visited[neighbor]) {
                dfs(neighbor, node) // updated visitOrder[], earlyVisitOrder[]

                earlyVisitOrder[node] = min(earlyVisitOrder[node], earlyVisitOrder[neighbor])
                if (visitOrder[node] < earlyVisitOrder[neighbor])
                    bridges.add(edge)
            } else {
                earlyVisitOrder[node] = min(earlyVisitOrder[node], visitOrder[neighbor])
            }
        }
    }

    /*
     earlyVisitOrder[v] = earlyVisitOrder[u] means there is other path also from u to v. Which means v can be reached
     from u via some other path as well even if the bridge between u and v is removed (because both share the same ancestor)
     However, when earlyVisitOrder[v] > earlyVisitOrder[u], it means that v can be reached from u only and only when bridge u-v exists.
    */
    fun dfsSimpleForBridge(node: Int, parent: Int) {
        visited[node] = true
        order += 1
        visitOrder[node] = order
        earlyVisitOrder[node] = order

        for (edge in getEdges(node)) {
            val neighbor = edge.end
            if (neighbor == parent) continue

            if (!visited[neighbor])
                dfs(neighbor, node) // updated visitOrder[], earlyVisitOrder[]

            earlyVisitOrder[node] = min(earlyVisitOrder[node], earlyVisitOrder[neighbor])
            if (earlyVisitOrder[node] != earlyVisitOrder[neighbor])
                bridges.add(edge)
        }
    }

    // Finds all bridges in the graph across various connected components.
    for (node in 0 until nodeCount)
        if (!visited[node])
            dfsSimpleForBridge(node, -1)

    return bridges
}
