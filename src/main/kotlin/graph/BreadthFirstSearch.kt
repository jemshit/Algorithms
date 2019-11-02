package graph

import java.util.*

// O(V+E)
fun GraphUsingAdjacencyList.breadthFirstTraversal(startNode: Int): List<Int> {
    if (startNode >= this.nodeCount) throw IllegalArgumentException()

    val nodes = mutableListOf<Int>()
    val visited = Array<Boolean>(this.nodeCount) { false }
    val queue: Queue<Int> = LinkedList<Int>()
    queue.offer(startNode)
    visited[startNode] = true

    while (queue.isNotEmpty()) {
        val node = queue.poll()
        nodes.add(node)
        for (edge in getEdges(node)) {
            if (!visited[edge.end]) {
                visited[edge.end] = true
                queue.offer(edge.end)
            }
        }
    }

    return nodes
}