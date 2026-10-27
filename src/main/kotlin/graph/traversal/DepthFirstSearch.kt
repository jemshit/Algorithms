package graph.traversal

import graph.representation.Graph

// O(V + E) time, O(V) extra space.
fun <V> depthFirstTraversal(graph: Graph<V>, start: V): List<V> {
    require(start in graph.vertices()) { "Unknown start vertex: $start" }

    val order = mutableListOf<V>()
    val visited = mutableSetOf(start)
    val stack = ArrayDeque<V>()
    stack.addLast(start)

    while (stack.isNotEmpty()) {
        val vertex = stack.removeLast()
        order.add(vertex)

        for (edge in graph.outgoingEdges(vertex).asReversed()) {
            if (visited.add(edge.to)) stack.addLast(edge.to)
        }
    }

    return order
}

fun <V> depthFirstTraversalRecursive(graph: Graph<V>, start: V): List<V> {
    require(start in graph.vertices()) { "Unknown start vertex: $start" }

    val order = mutableListOf<V>()
    val visited = mutableSetOf<V>()

    fun visit(vertex: V) {
        visited.add(vertex)
        order.add(vertex)

        for (edge in graph.outgoingEdges(vertex)) {
            if (edge.to !in visited) visit(edge.to)
        }
    }

    visit(start)
    return order
}
