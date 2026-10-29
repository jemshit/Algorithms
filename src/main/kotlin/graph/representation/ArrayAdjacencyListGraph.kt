package graph.representation

/** Adjacency list for vertices numbered from 0 until [vertexCount]. */
class ArrayAdjacencyListGraph(
    override val directed: Boolean,
    override val vertexCount: Int
) : Graph<Int> {

    private val adjacency: Array<MutableList<Edge<Int>>>

    override var edgeCount: Int = 0
        private set

    init {
        require(vertexCount >= 0) { "vertexCount must not be negative" }
        adjacency = Array(vertexCount) {
            // each vertex has empty edge list
            mutableListOf()
        }
    }

    override fun vertices(): Iterable<Int> = 0 until vertexCount

    override fun addEdge(from: Int, to: Int, weight: Int) {
        requireVertex(from)
        requireVertex(to)
        require(from != to) { "A simple graph does not allow self-loops" }

        val existingToIndex = adjacency[from].indexOfFirst { it.to == to }
        if (existingToIndex >= 0) {
            adjacency[from][existingToIndex] = Edge(to, weight)
            if (!directed) {
                val reverseIndex = adjacency[to].indexOfFirst { it.to == from }
                check(reverseIndex >= 0) { "Undirected edge is missing its reverse entry" }
                adjacency[to][reverseIndex] = Edge(from, weight)
            }
            return
        }

        // not existing, add new
        adjacency[from].add(Edge(to, weight))
        if (!directed) {
            adjacency[to].add(Edge(from, weight))
        }
        edgeCount++
    }

    override fun removeEdge(from: Int, to: Int): Boolean {
        requireVertex(from)
        requireVertex(to)

        val removed = adjacency[from].removeAll { it.to == to }
        if (!removed) return false

        if (!directed) {
            check(adjacency[to].removeAll { it.to == from }) {
                "Undirected edge is missing its reverse entry"
            }
        }
        edgeCount--
        return true
    }

    override fun hasEdge(from: Int, to: Int): Boolean {
        requireVertex(from)
        requireVertex(to)
        return adjacency[from].any { it.to == to }
    }

    override fun outgoingEdges(from: Int): List<Edge<Int>> {
        requireVertex(from)
        return adjacency[from].toList()
    }

    private fun requireVertex(vertex: Int) {
        require(vertex in 0 until vertexCount) { "Unknown vertex: $vertex" }
    }
}
