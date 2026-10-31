package graph.traversal

import graph.representation.Graph

// O(V + E) time, O(V) extra space.
fun <V> dfs(graph: Graph<V>, start: V): List<V> {
    require(start in graph.vertices()) { "Unknown start vertex: $start" }

    val result = mutableListOf<V>()
    val visited = mutableSetOf(start)
    val stack = ArrayDeque<V>()
    stack.addLast(start)

    while (stack.isNotEmpty()) {
        val vertex = stack.removeLast()
        result.add(vertex)

        // asReversed() is to match recursive order as stack is LIFO
        for (edge in graph.outgoingEdges(vertex).asReversed()) {
            if (edge.to !in visited) {
                visited.add(edge.to)
                stack.addLast(edge.to)
            }
        }
    }

    return result
}

fun <V> dfsRecursive(graph: Graph<V>, start: V): List<V> {
    require(start in graph.vertices()) { "Unknown start vertex: $start" }

    val result = mutableListOf<V>()
    val visited = mutableSetOf<V>()

    fun visit(vertex: V) {
        // mark as visited while adding in recursive
        visited.add(vertex)
        result.add(vertex)

        for (edge in graph.outgoingEdges(vertex)) {
            if (edge.to !in visited) {
                visit(edge.to)
            }
        }
    }

    visit(start)
    return result
}
