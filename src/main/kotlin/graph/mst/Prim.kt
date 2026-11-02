package graph.mst

import graph.representation.Edge
import graph.representation.Graph
import java.util.PriorityQueue

/** Lazy Prim's MST algorithm in O(E log V) with an adjacency list and binary heap. */
fun <V> primMst(
    graph: Graph<V>,
    start: V
): MinimumSpanningTree<V> {
    require(!graph.directed) { "Prim's algorithm requires an undirected graph" }
    require(start in graph.vertices()) { "Unknown start vertex: $start" }

    val mst = mutableListOf<Edge<V>>()
    val visited = mutableSetOf(start)
    val candidates = PriorityQueue<Edge<V>>(compareBy { it.weight })
    var totalWeight = 0

    fun addEdges(vertex: V) {
        for (edge in graph.outgoingEdges(vertex)) {
            if (edge.to !in visited) {
                candidates.add(edge)
            }
        }
    }

    addEdges(start)
    while (candidates.isNotEmpty() && mst.size < graph.vertexCount - 1) {
        // get the next promising edge: the lightest candidate.
        val edge = candidates.poll()

        // skip it if its destination is already in the MST.
        if (edge.to in visited)
            continue

        visited.add(edge.to)
        mst.add(edge)
        totalWeight += edge.weight

        addEdges(edge.to)
    }

    require(mst.size == maxOf(0, graph.vertexCount - 1)) {
        "Prim's algorithm requires a connected graph"
    }
    return MinimumSpanningTree(mst, totalWeight)
}
