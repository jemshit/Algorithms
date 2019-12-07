package graph

import kotlin.math.min

fun GraphUsingAdjacencyList.findArticulationPoints(): List<Int> {
    val artPoints = Array<Boolean>(this.nodeCount) { false }
    val visited = Array<Boolean>(this.nodeCount) { false }
    var order = -1
    val visitOrder = Array<Int>(this.nodeCount) { -1 }
    val earlyVisitOrder = Array<Int>(this.nodeCount) { -1 }

    // Similar to Articulation problem solving
    fun dfs(node: Int, parent: Int) {
        visited[node] = true
        order += 1
        visitOrder[node] = order
        earlyVisitOrder[node] = order
        var outEdgeCount = 0

        for (edge in getEdges(node)) {
            val neighbor = edge.end
            if (neighbor == parent) continue

            if (!visited[neighbor]) {
                outEdgeCount += 1
                dfs(neighbor, node) // updated visitOrder[], earlyVisitOrder[]

                earlyVisitOrder[node] = min(earlyVisitOrder[node], earlyVisitOrder[neighbor])

                // found via bridge
                if (visitOrder[node] < earlyVisitOrder[neighbor])
                    artPoints[node] = true
                // found via cycle
                if (visitOrder[node] == earlyVisitOrder[neighbor])
                    artPoints[node] = true
            } else {
                earlyVisitOrder[node] = min(earlyVisitOrder[node], visitOrder[neighbor])
            }
        }

        if (parent == -1) {
            // start node can be artPoint only if it has multiple outward links (when in cycle)
            artPoints[node] = if (outEdgeCount > 1) artPoints[node] else false
        }
    }

    // Finds all bridges in the graph across various connected components.
    for (node in 0 until nodeCount)
        if (!visited[node])
            dfs(node, -1)

    return artPoints
        .mapIndexed { index, item ->
            if (item) index else -1
        }
        .filter { it != -1 }
}