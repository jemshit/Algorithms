package graph.mst

import graph.representation.Graph

/**
 * Kruskal's MST algorithm.
 *
 * Collecting edges takes O(V + E) from an adjacency list or O(V²) from an adjacency matrix.
 * Sorting takes O(E log E), which dominates when the graph uses an adjacency list.
 */
fun <V> kruskalMinimumSpanningTree(graph: Graph<V>): MinimumSpanningTree<V> {
    require(!graph.directed) { "Kruskal's algorithm requires an undirected graph" }

    val vertices = graph.vertices().toList()
    val indexOf = vertices.withIndex().associate { (index, vertex) -> vertex to index }
    val processed = mutableSetOf<V>()
    val edges = mutableListOf<MstEdge<V>>()

    // An undirected edge appears in both adjacency lists; collect it only once.
    for (from in vertices) {
        for (edge in graph.outgoingEdges(from)) {
            if (edge.to !in processed) edges.add(MstEdge(from, edge.to, edge.weight))
        }
        processed.add(from)
    }

    val unionFind = UnionFind(vertices.size)
    val tree = mutableListOf<MstEdge<V>>()
    var totalWeight = 0

    for (edge in edges.sortedBy { it.weight }) {
        if (!unionFind.union(indexOf.getValue(edge.from), indexOf.getValue(edge.to))) continue
        tree.add(edge)
        totalWeight += edge.weight
        if (tree.size == vertices.size - 1) break
    }

    require(tree.size == maxOf(0, vertices.size - 1)) {
        "Kruskal's algorithm requires a connected graph"
    }
    return MinimumSpanningTree(tree, totalWeight)
}
