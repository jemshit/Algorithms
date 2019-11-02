package graph

import java.util.*

// O(V+E) same edge visited from both nodes
fun GraphUsingAdjacencyList.depthFirstTraversal(startNode: Int): List<Int> {
    if (startNode >= nodeCount) throw IllegalArgumentException()

    val nodes = mutableListOf<Int>()
    val visited = Array<Boolean>(nodeCount) { false }
    val stack = Stack<Int>()
    stack.push(startNode)
    visited[startNode] = true

    while (stack.isNotEmpty()) {
        val node = stack.pop()
        nodes.add(node)

        val edges = getEdges(node)
        for (edge in edges.reversed()) {
            if (!visited[edge.end]) {
                visited[edge.end] = true
                stack.push(edge.end)
            }
        }
    }

    return nodes
}

fun GraphUsingAdjacencyList.depthFirstTraversalRecursive(startNode: Int)
        : List<Int> {

    val nodes = mutableListOf<Int>()
    val visited = Array<Boolean>(nodeCount) { false }
    depthFirstTraversalRecursive(visited, nodes, startNode)

    return nodes
}

private fun GraphUsingAdjacencyList.depthFirstTraversalRecursive(
    visited: Array<Boolean>,
    result: MutableList<Int>,
    startNode: Int
) {
    visited[startNode] = true
    result.add(startNode)

    val edges = getEdges(startNode)
    for (edge in edges) {
        if (!visited[edge.end])
            depthFirstTraversalRecursive(visited, result, edge.end)
    }
}