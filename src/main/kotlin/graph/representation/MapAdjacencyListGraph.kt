package graph.representation

/** Adjacency list for arbitrary vertex identifiers. */
class MapAdjacencyListGraph<V : Any>(
    override val directed: Boolean,
    vertices: Collection<V>
) : Graph<V> {

    private val adjacency = LinkedHashMap<V, MutableList<Edge<V>>>()

    override val vertexCount: Int
        get() = adjacency.size

    override var edgeCount: Int = 0
        private set

    init {
        for (vertex in vertices) adjacency.putIfAbsent(vertex, mutableListOf())
    }

    override fun vertices(): Iterable<V> = adjacency.keys

    override fun addEdge(from: V, to: V, weight: Int) {
        val fromEdges = edgesOf(from)
        edgesOf(to)
        require(from != to) { "A simple graph does not allow self-loops" }

        val existingIndex = fromEdges.indexOfFirst { it.to == to }
        if (existingIndex >= 0) {
            fromEdges[existingIndex] = Edge(to, weight)
            if (!directed) {
                val reverseEdges = edgesOf(to)
                val reverseIndex = reverseEdges.indexOfFirst { it.to == from }
                check(reverseIndex >= 0) { "Undirected edge is missing its reverse entry" }
                reverseEdges[reverseIndex] = Edge(from, weight)
            }
            return
        }

        fromEdges.add(Edge(to, weight))
        if (!directed) edgesOf(to).add(Edge(from, weight))
        edgeCount++
    }

    override fun removeEdge(from: V, to: V): Boolean {
        val removed = edgesOf(from).removeAll { it.to == to }
        edgesOf(to)
        if (!removed) return false

        if (!directed) {
            check(edgesOf(to).removeAll { it.to == from }) {
                "Undirected edge is missing its reverse entry"
            }
        }
        edgeCount--
        return true
    }

    override fun hasEdge(from: V, to: V): Boolean {
        edgesOf(to)
        return edgesOf(from).any { it.to == to }
    }

    override fun outgoingEdges(from: V): List<Edge<V>> = edgesOf(from).toList()

    private fun edgesOf(vertex: V): MutableList<Edge<V>> =
        adjacency[vertex] ?: throw IllegalArgumentException("Unknown vertex: $vertex")
}
