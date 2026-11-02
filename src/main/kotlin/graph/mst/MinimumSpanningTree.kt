package graph.mst

import graph.representation.Edge

data class MinimumSpanningTree<V>(
    val edges: List<Edge<V>>,
    val totalWeight: Int
)
