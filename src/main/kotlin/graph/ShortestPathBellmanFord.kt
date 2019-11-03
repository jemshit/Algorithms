package graph

fun GraphUsingAdjacencyList.shortestPathBellmanFord(): Array<Int> {
    val shortestPaths = Array<Int>(nodeCount) { Int.MAX_VALUE }
    shortestPaths[0] = 0

    for (node in 0 until nodeCount) {
        for (edge in getAllEdges()) {
            if (shortestPaths[edge.start] + (edge.weight ?: 0) < shortestPaths[edge.end])
                shortestPaths[edge.end] = shortestPaths[edge.start] + (edge.weight ?: 0)
        }
    }

    // same loop: if we can find smaller distance, then there is negative cycle
    for (node in 0 until nodeCount) {
        for (edge in getAllEdges()) {
            if (shortestPaths[edge.start] + (edge.weight ?: 0) < shortestPaths[edge.end])
                shortestPaths[edge.end] = Int.MIN_VALUE
        }
    }

    return shortestPaths as Array<Int>
}