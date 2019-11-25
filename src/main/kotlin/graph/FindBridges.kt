package graph

import kotlin.math.min

fun GraphUsingAdjacencyList.findBridges(): List<GraphUsingAdjacencyList.Edge> {
    val bridges = mutableListOf<GraphUsingAdjacencyList.Edge>()
    val visited = Array<Boolean>(this.nodeCount) { false }
    var discoverTime = 0
    val discoveredAt = Array<Int>(this.nodeCount) { -1 }
    val earliestLink = Array<Int>(this.nodeCount) { -1 }

    // Similar to Articulation problem solving
    fun dfsRecursive(node: Int, parent: Int) {
        visited[node] = true
        discoverTime += 1
        discoveredAt[node] = discoverTime
        earliestLink[node] = discoverTime

        for (edge in getEdges(node)) {
            if (edge.end == parent) continue

            if (!visited[edge.end]) {
                dfsRecursive(edge.end, node) // update visited[], ids[], earliestDiscoveredLink[]

                earliestLink[edge.start] = min(earliestLink[edge.start], earliestLink[edge.end])
                if (discoveredAt[edge.start] < earliestLink[edge.end])
                    bridges.add(edge)
            } else {
                earliestLink[edge.start] = min(earliestLink[edge.start], discoveredAt[edge.end])
            }
        }
    }

    /*
       earliestLink[v] = earliestLink[u] means there is other path also from u to v. Which means v can be reached
       from u via some other path as well even if the bridge between u and v is removed (because both share the same ancestor)
       However, when earliestLink[v] > earliestLink[u], it means that v can be reached from u only and only when bridge u-v exists.
    */
    fun dfsRecursiveSimpleForBridge(node: Int, parent: Int) {
        visited[node] = true
        discoverTime += 1
        discoveredAt[node] = discoverTime
        earliestLink[node] = discoverTime

        for (edge in getEdges(node)) {
            if (edge.end == parent) continue

            if (!visited[edge.end])
                dfsRecursive(edge.end, node) // update visited[], ids[], earliestLink[]

            earliestLink[edge.start] = min(earliestLink[edge.start], earliestLink[edge.end])
            if (earliestLink[edge.start] != earliestLink[edge.end])
                bridges.add(edge)
        }
    }

    // Finds all bridges in the graph across various connected components.
    for (node in 0 until nodeCount)
        if (!visited[node])
            dfsRecursiveSimpleForBridge(node, -1)

    return bridges
}
