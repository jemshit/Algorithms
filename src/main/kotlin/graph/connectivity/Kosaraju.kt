package graph.connectivity

import graph.representation.Graph

/** Finds strongly connected components with Kosaraju's two DFS passes in O(V + E). */
fun <V> findStronglyConnectedComponents(graph: Graph<V>): List<Set<V>> {
    require(graph.directed) { "Strongly connected components require a directed graph" }

    val vertices = graph.vertices().toList()
    val visited = mutableSetOf<V>()
    val finishOrder = mutableListOf<V>()

    fun visit(vertex: V) {
        if (!visited.add(vertex)) return
        for (edge in graph.outgoingEdges(vertex)) visit(edge.to)
        finishOrder.add(vertex)
    }

    for (vertex in vertices) visit(vertex)

    val incoming = vertices.associateWith { mutableListOf<V>() }
    for (from in vertices) {
        for (edge in graph.outgoingEdges(from)) incoming.getValue(edge.to).add(from)
    }

    visited.clear()
    val components = mutableListOf<Set<V>>()

    fun collect(vertex: V, component: MutableSet<V>) {
        if (!visited.add(vertex)) return
        component.add(vertex)
        for (previous in incoming.getValue(vertex)) collect(previous, component)
    }

    for (vertex in finishOrder.asReversed()) {
        if (vertex in visited) continue
        val component = linkedSetOf<V>()
        collect(vertex, component)
        components.add(component)
    }

    return components
}
