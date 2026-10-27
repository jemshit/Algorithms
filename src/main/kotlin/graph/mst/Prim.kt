package graph.mst

import graph.representation.Graph
import java.util.PriorityQueue

/** Prim's MST algorithm in O(E log V) with an adjacency list and binary heap. */
fun <V> primMinimumSpanningTree(
    graph: Graph<V>,
    start: V
): MinimumSpanningTree<V> {
    require(!graph.directed) { "Prim's algorithm requires an undirected graph" }
    require(start in graph.vertices()) { "Unknown start vertex: $start" }

    val tree = mutableListOf<MstEdge<V>>()
    val visited = mutableSetOf(start)
    val candidates = PriorityQueue<MstEdge<V>>(compareBy { it.weight })
    var totalWeight = 0

    fun addCandidates(from: V) {
        for (edge in graph.outgoingEdges(from)) {
            if (edge.to !in visited) candidates.add(MstEdge(from, edge.to, edge.weight))
        }
    }

    addCandidates(start)
    while (candidates.isNotEmpty() && tree.size < graph.vertexCount - 1) {
        val edge = candidates.poll()
        if (!visited.add(edge.to)) continue

        tree.add(edge)
        totalWeight += edge.weight
        addCandidates(edge.to)
    }

    require(tree.size == maxOf(0, graph.vertexCount - 1)) {
        "Prim's algorithm requires a connected graph"
    }
    return MinimumSpanningTree(tree, totalWeight)
}
