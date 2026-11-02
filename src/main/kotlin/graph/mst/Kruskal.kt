package graph.mst

import graph.representation.Edge
import graph.representation.Graph

/**
 * Kruskal's MST algorithm.
 *
 * Collecting edges takes O(V + E) from an adjacency list or O(V²) from an adjacency matrix.
 * Sorting takes O(E log E), which dominates when the graph uses an adjacency list.
 */
fun <V> kruskalMst(graph: Graph<V>): MinimumSpanningTree<V> {
    require(!graph.directed) { "Kruskal's algorithm requires an undirected graph" }

    val vertices = graph.vertices().toList()
    // Union-Find uses integer indices, so map each generic graph vertex to an index.
    val vertexToIndex = vertices.withIndex().associate { (index, vertex) -> vertex to index }
    val edges = collectUndirectedEdges(graph)

    val unionFind = UnionFind(vertices.size)
    val mst = mutableListOf<Edge<V>>()
    var totalWeight = 0

    val sortedEdges = edges.sortedBy { it.weight }
    for (edge in sortedEdges) {
        val fromIndex = vertexToIndex.getValue(edge.from)
        val toIndex = vertexToIndex.getValue(edge.to)

        // Skip the edge if its endpoints are already connected; it would create a cycle.
        if (unionFind.connected(fromIndex, toIndex))
            continue

        // Connect the components and add the edge to the MST.
        unionFind.union(fromIndex, toIndex)
        mst.add(edge)
        totalWeight += edge.weight

        if (mst.size == vertices.size - 1)
            break
    }

    require(mst.size == maxOf(0, vertices.size - 1)) {
        "Kruskal's algorithm requires a connected graph"
    }
    return MinimumSpanningTree(mst, totalWeight)
}

private fun <V> collectUndirectedEdges(graph: Graph<V>): List<Edge<V>> {
    val edges = mutableListOf<Edge<V>>()
    val processedVertices = mutableSetOf<V>()

    // An undirected edge appears in both adjacency lists; collect it only once.
    for (vertex in graph.vertices()) {
        for (edge in graph.outgoingEdges(vertex)) {
            if (edge.to !in processedVertices) {
                edges.add(edge)
            }
        }
        processedVertices.add(vertex)
    }

    return edges
}
