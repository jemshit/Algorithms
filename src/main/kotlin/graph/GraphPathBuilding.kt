package graph

import java.util.*


fun GraphUsingAdjacencyList.breadthFirstPathFind(startNode: Int, findNode: Int): List<Int> =
    pathFind(startNode, findNode, isBfs = true)

fun GraphUsingAdjacencyList.depthFirstPathFind(startNode: Int, findNode: Int): List<Int> =
    pathFind(startNode, findNode, isBfs = false)

private fun GraphUsingAdjacencyList.pathFind(startNode: Int, findNode: Int, isBfs: Boolean)
        : List<Int> {
    val prevNodes = if (isBfs)
        breadthFirstSearchPrevNodes(startNode, findNode)
    else
        depthFirstSearchPrevNodes(startNode, findNode)

    val path = mutableListOf<Int>()
    var node = findNode
    while (node != prevNodes[node]) {
        path.add(node)
        node = prevNodes[node]
    }
    path.add(node)

    return path.reversed()
}

private fun GraphUsingAdjacencyList.breadthFirstSearchPrevNodes(startNode: Int, findNode: Int)
        : List<Int> {
    if (startNode >= nodeCount || findNode >= nodeCount) throw IllegalArgumentException()

    // BFS Traverse, find previous nodes of each node
    val prevNodes = arrayOfNulls<Int>(nodeCount)
    val visited = Array<Boolean>(nodeCount) { false }
    val queue: Queue<Int> = LinkedList<Int>()
    queue.offer(startNode)
    visited[startNode] = true
    prevNodes[startNode] = startNode

    while (queue.isNotEmpty()) {
        val node = queue.poll()
        if (node == findNode) break
        for (edge in getEdges(node)) {
            if (!visited[edge.end]) {
                visited[edge.end] = true
                queue.offer(edge.end)
                prevNodes[edge.end] = node
            }
        }
    }

    return prevNodes.toList() as List<Int>
}

private fun GraphUsingAdjacencyList.depthFirstSearchPrevNodes(startNode: Int, findNode: Int)
        : List<Int> {
    if (startNode >= nodeCount || findNode >= nodeCount) throw IllegalArgumentException()

    val prevNodes = arrayOfNulls<Int>(nodeCount)
    val visited = Array<Boolean>(nodeCount) { false }
    val stack = Stack<Int>()
    stack.push(startNode)
    visited[startNode] = true
    prevNodes[startNode] = startNode

    while (stack.isNotEmpty()) {
        val node = stack.pop()
        if (node == findNode) break
        val edges = getEdges(node)
        for (index in edges.size - 1 downTo 0) {
            if (!visited[edges[index].end]) {
                visited[edges[index].end] = true
                stack.push(edges[index].end)
                prevNodes[edges[index].end] = node
            }
        }
    }

    return prevNodes.toList() as List<Int>
}