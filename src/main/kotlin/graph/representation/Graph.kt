package graph.representation

data class Edge<V>(
    val to: V,
    val weight: Int = 1
)

/** A mutable, weighted simple graph with a fixed set of vertices. */
interface Graph<V> {
    val directed: Boolean
    val vertexCount: Int
    val edgeCount: Int

    fun vertices(): Iterable<V>

    /** Adds a new edge, or updates its weight if it already exists. */
    fun addEdge(from: V, to: V, weight: Int = 1)

    fun removeEdge(from: V, to: V): Boolean

    fun hasEdge(from: V, to: V): Boolean

    /** Returns a snapshot of the edges leaving [from]. */
    fun outgoingEdges(from: V): List<Edge<V>>
}
