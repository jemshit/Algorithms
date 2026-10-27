package graph.mst

data class MstEdge<V>(
    val from: V,
    val to: V,
    val weight: Int
)

data class MinimumSpanningTree<V>(
    val edges: List<MstEdge<V>>,
    val totalWeight: Int
)
