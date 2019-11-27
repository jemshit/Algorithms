package graph

import java.util.*

fun GraphUsingAdjacencyList.isBipartite(): Boolean {
    val color = Array<Int>(this.nodeCount) { -1 } // -1 no color, 0 red, 1 black

    fun bfs(source: Int): Boolean {
        color[source] = 1
        val queue: Queue<Int> = LinkedList()
        queue.add(source)

        while (queue.isNotEmpty()) {
            val node = queue.poll()

            for (edge in getEdges(node)) {
                val neighbor = edge.end
                if (node == neighbor)
                    return false // self-loop

                if (color[neighbor] == -1) {  // not colored yet
                    color[neighbor] = 1 - color[node]
                    queue.add(neighbor)
                } else if (color[neighbor] == color[node]) { // same color
                    return false
                } else {
                    // noOp for different color neighbor
                }
            }
        }

        return true
    }

    // across all components
    for (node in 0 until nodeCount) {
        if (color[node] == -1) { // if not colored yet
            val bipartite = bfs(node)
            if (!bipartite) return false
        }
    }

    return true
}