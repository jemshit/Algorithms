package graph

fun GraphUsingAdjacencyList.longestPathDAG(): Array<Int> {
    val shortestPaths = arrayOfNulls<Int>(nodeCount)
    val topologicalOrdering = this.topologicalOrdering()
    shortestPaths[0] = 0
    for (node in topologicalOrdering) {
        val edges = getEdges(node)
        for (edge in edges) {
            val neighbour = edge.end
            val distanceToNeighbour = (shortestPaths[node] ?: 0) + (edge.weight ?: 0) * -1
            if (shortestPaths[neighbour] == null || distanceToNeighbour < shortestPaths[neighbour]!!) {
                shortestPaths[neighbour] = distanceToNeighbour
            }
        }
    }

    for (index in shortestPaths.indices)
        shortestPaths[index] = shortestPaths[index]!! * -1
    return shortestPaths as Array<Int>
}