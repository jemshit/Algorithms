package graph

fun GraphUsingAdjacencyList.shortestPathDAG(): Array<Int> {
    val shortestPaths = Array<Int>(nodeCount) { 0 }
    val topologicalOrdering = this.topologicalOrdering()
    shortestPaths[0] = 0
    for (node in topologicalOrdering) {
        val edges = getEdges(node)
        for (edge in edges) {
            val neighbour = edge.end
            val distanceToNeighbour = shortestPaths[node] + (edge.weight ?: 0)
            if (distanceToNeighbour < shortestPaths[neighbour]) {
                shortestPaths[neighbour] = distanceToNeighbour
            }
        }
    }
    return shortestPaths
}