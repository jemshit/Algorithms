package graph.representation

private const val INF = Int.MAX_VALUE

/**
 * Adjacency matrix for vertices numbered from 0 until [vertexCount].
 * Uses O(V²) space, provides O(1) edge lookup, and scans O(V) cells for neighbors.
 */
class AdjacencyMatrixGraph(
    override val directed: Boolean,
    override val vertexCount: Int
) : Graph<Int> {

    private val adjacency: Array<IntArray>

    override var edgeCount: Int = 0
        private set

    init {
        require(vertexCount >= 0) { "vertexCount must not be negative" }
        adjacency = Array(vertexCount) {
            IntArray(vertexCount) { INF }
        }
    }

    override fun vertices(): Iterable<Int> = 0 until vertexCount

    override fun addEdge(from: Int, to: Int, weight: Int) {
        requireVertex(from)
        requireVertex(to)
        require(from != to) { "A simple graph does not allow self-loops" }
        require(weight != INF) { "Int.MAX_VALUE is reserved to mean no edge" }

        if (adjacency[from][to] == INF) {
            // if new edge
            edgeCount++
        }
        adjacency[from][to] = weight

        if (!directed) {
            adjacency[to][from] = weight
        }
    }

    override fun removeEdge(from: Int, to: Int): Boolean {
        requireVertex(from)
        requireVertex(to)
        if (adjacency[from][to] == INF) return false

        adjacency[from][to] = INF
        if (!directed) adjacency[to][from] = INF
        edgeCount--
        return true
    }

    override fun hasEdge(from: Int, to: Int): Boolean {
        requireVertex(from)
        requireVertex(to)
        return adjacency[from][to] != INF
    }

    override fun outgoingEdges(from: Int): List<Edge<Int>> {
        requireVertex(from)
        return buildList {
            for (to in 0 until vertexCount) {
                val weight = adjacency[from][to]
                if (weight != INF) add(Edge(to, weight))
            }
        }
    }

    private fun requireVertex(vertex: Int) {
        require(vertex in 0 until vertexCount) { "Unknown vertex: $vertex" }
    }
}
