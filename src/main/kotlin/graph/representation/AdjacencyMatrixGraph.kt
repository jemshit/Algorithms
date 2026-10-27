package graph.representation

/**
 * Adjacency matrix for vertices numbered from 0 until [vertexCount].
 * Uses O(V²) space, provides O(1) edge lookup, and scans O(V) cells for neighbors.
 */
class AdjacencyMatrixGraph(
    override val directed: Boolean,
    override val vertexCount: Int
) : Graph<Int> {

    private val present: Array<BooleanArray>
    private val weights: Array<IntArray>

    override var edgeCount: Int = 0
        private set

    init {
        require(vertexCount >= 0) { "vertexCount must not be negative" }
        present = Array(vertexCount) { BooleanArray(vertexCount) }
        weights = Array(vertexCount) { IntArray(vertexCount) }
    }

    override fun vertices(): Iterable<Int> = 0 until vertexCount

    override fun addEdge(from: Int, to: Int, weight: Int) {
        requireVertex(from)
        requireVertex(to)
        require(from != to) { "A simple graph does not allow self-loops" }

        if (!present[from][to]) edgeCount++
        present[from][to] = true
        weights[from][to] = weight

        if (!directed) {
            present[to][from] = true
            weights[to][from] = weight
        }
    }

    override fun removeEdge(from: Int, to: Int): Boolean {
        requireVertex(from)
        requireVertex(to)
        if (!present[from][to]) return false

        present[from][to] = false
        if (!directed) present[to][from] = false
        edgeCount--
        return true
    }

    override fun hasEdge(from: Int, to: Int): Boolean {
        requireVertex(from)
        requireVertex(to)
        return present[from][to]
    }

    override fun outgoingEdges(from: Int): List<Edge<Int>> {
        requireVertex(from)
        return buildList {
            for (to in 0 until vertexCount) {
                if (present[from][to]) add(Edge(to, weights[from][to]))
            }
        }
    }

    private fun requireVertex(vertex: Int) {
        require(vertex in 0 until vertexCount) { "Unknown vertex: $vertex" }
    }
}
