package graph

fun GraphUsingAdjacencyList.isCycleExistUndirected(): Boolean {
    val visited = Array<Boolean>(this.nodeCount) { false }

    fun dfs(node: Int, parent: Int): Boolean {
        visited[node] = true

        for (edge in getEdges(node)) {
            val neighbor = edge.end

            if (!visited[neighbor]) {
                val exist = dfs(neighbor, node)
                if (exist) return true
            } else if (neighbor != parent) {
                return true
            }
        }

        return false
    }

    // across all components
    for (node in 0 until nodeCount) {
        if (!visited[node]) {
            val exist = dfs(node, -1)
            if (exist) return true
        }
    }

    return false
}

fun GraphUsingAdjacencyList.isCycleExistDirected(): Boolean {
    val visited = Array<Boolean>(this.nodeCount) { false }
    val visitedInCurrentDepth = Array<Boolean>(this.nodeCount) { false }

    fun dfs(node: Int): Boolean {
        visited[node] = true
        visitedInCurrentDepth[node] = true

        for (edge in getEdges(node)) {
            val neighbor = edge.end

            if (!visited[neighbor]) {
                val exist = dfs(neighbor)
                if (exist) return true
            } else if (visitedInCurrentDepth[neighbor]) {
                return true
            }
        }

        visitedInCurrentDepth[node] = false
        return false
    }

    // across all components
    for (node in 0 until nodeCount) {
        if (!visited[node]) {
            val exist = dfs(node)
            if (exist) return true
        }
    }

    return false
}