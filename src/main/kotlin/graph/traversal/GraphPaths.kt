package graph.traversal

import graph.representation.Graph

// BFS returns a shortest path measured by number of edges.
fun <V> bfsPath(
    graph: Graph<V>,
    start: V,
    target: V
): List<V> {
    val vertices = graph.vertices().toSet()
    require(start in vertices) { "Unknown start vertex: $start" }
    require(target in vertices) { "Unknown target vertex: $target" }

    val previous = mutableMapOf<V, V>()
    val visited = mutableSetOf(start)
    val queue = ArrayDeque<V>()
    queue.addLast(start)

    while (queue.isNotEmpty()) {
        val vertex = queue.removeFirst()
        if (vertex == target) break

        for (edge in graph.outgoingEdges(vertex)) {
            if (edge.to !in visited) {
                visited.add(edge.to)
                previous[edge.to] = vertex
                queue.addLast(edge.to)
            }
        }
    }

    // couldn't find
    if (target !in visited) return emptyList()

    val path = mutableListOf(target)
    var current = target
    while (current != start) {
        current = previous.getValue(current)
        path.add(current)
    }
    path.reverse()
    return path
}

// DFS returns a path, but not necessarily a shortest path.
fun <V> dfsPath(
    graph: Graph<V>,
    start: V,
    target: V
): List<V> {
    val vertices = graph.vertices().toSet()
    require(start in vertices) { "Unknown start vertex: $start" }
    require(target in vertices) { "Unknown target vertex: $target" }

    val previous = mutableMapOf<V, V>()
    val visited = mutableSetOf(start)
    val stack = ArrayDeque<V>()
    stack.addLast(start)

    while (stack.isNotEmpty()) {
        val vertex = stack.removeLast()
        if (vertex == target) break

        // Reverse neighbor order because the stack processes the last item first.
        for (edge in graph.outgoingEdges(vertex).asReversed()) {
            if (edge.to !in visited) {
                visited.add(edge.to)
                previous[edge.to] = vertex
                stack.addLast(edge.to)
            }
        }
    }

    // couldn't find
    if (target !in visited) return emptyList()

    val path = mutableListOf(target)
    var current = target
    while (current != start) {
        current = previous.getValue(current)
        path.add(current)
    }
    path.reverse()
    return path
}
