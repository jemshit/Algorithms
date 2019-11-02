package graph

private var orderingAdded = 0

fun GraphUsingAdjacencyList.topologicalOrdering(): List<Int> {
    orderingAdded = 0
    val visited: Array<Boolean> = Array<Boolean>(nodeCount) { false }
    val ordering: Array<Int> = Array<Int>(nodeCount) { -1 }
    for (node in 0 until nodeCount) {
        if (!visited[node])
            this.dfsRecursive(node, visited, ordering)
    }

    return ordering.toList()
}

private fun GraphUsingAdjacencyList.dfsRecursive(
    node: Int,
    visited: Array<Boolean>,
    ordering: Array<Int>
) {
    visited[node] = true

    val edges = this.getEdges(node)
    for (edge in edges) {
        if (!visited[edge.end])
            this.dfsRecursive(edge.end, visited, ordering)
    }

    ordering[this.nodeCount - 1 - orderingAdded] = node
    orderingAdded += 1
}