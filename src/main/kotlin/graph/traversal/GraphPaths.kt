package graph.traversal

import graph.representation.Graph

fun <V> breadthFirstPath(graph: Graph<V>, start: V, target: V): List<V> =
    findPath(graph, start, target, breadthFirst = true)

fun <V> depthFirstPath(graph: Graph<V>, start: V, target: V): List<V> =
    findPath(graph, start, target, breadthFirst = false)

private fun <V> findPath(
    graph: Graph<V>,
    start: V,
    target: V,
    breadthFirst: Boolean
): List<V> {
    val vertices = graph.vertices().toSet()
    require(start in vertices) { "Unknown start vertex: $start" }
    require(target in vertices) { "Unknown target vertex: $target" }

    val previous = mutableMapOf<V, V>()
    val visited = mutableSetOf(start)
    val frontier = ArrayDeque<V>()
    frontier.addLast(start)

    while (frontier.isNotEmpty()) {
        val vertex = if (breadthFirst) frontier.removeFirst() else frontier.removeLast()
        if (vertex == target) break

        val edges = graph.outgoingEdges(vertex)
        val traversalEdges = if (breadthFirst) edges else edges.asReversed()
        for (edge in traversalEdges) {
            if (visited.add(edge.to)) {
                previous[edge.to] = vertex
                frontier.addLast(edge.to)
            }
        }
    }

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
