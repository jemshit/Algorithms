package graph

class GraphUsingAdjacencyList {

    class Edge(val start: Int, val end: Int, var weight: Int? = null) : Comparable<Edge> {
        override fun compareTo(other: Edge): Int {
            if (start == other.start) {
                return end.compareTo(other.end)
            } else
                return start.compareTo(other.start)
        }

        override fun equals(other: Any?): Boolean {
            val other = other as Edge
            return start == other.start && end == other.end && weight == other.weight
        }
    }

    private val directed: Boolean
    private val graph: Array<MutableList<Edge>>
    private var edgeCount: Int = 0
    val nodeCount: Int

    constructor(directed: Boolean, nodeCount: Int) {
        if (nodeCount <= 0) throw IllegalArgumentException()

        this.nodeCount = nodeCount
        this.directed = directed
        this.graph = Array<MutableList<Edge>>(nodeCount) {
            mutableListOf<Edge>()
        }
    }

    fun edgeCount(): Int = edgeCount

    // O(1)
    fun addEdge(start: Int, end: Int, weight: Int? = null) {
        // add edge 'start->end'
        graph[start].add(Edge(start, end, weight))
        edgeCount += 1

        // add edge 'end-start' if undirected graph
        if (!directed) {
            graph[end].add(Edge(end, start, weight))
            edgeCount += 1
        }
    }

    // O(E)
    fun removeEdge(start: Int, end: Int): Boolean {
        // remove edge 'start-end'
        val edgesOfStart = graph.get(start)
        val indexStart = edgesOfStart.indexOfFirst { edge -> edge.start == start && edge.end == end }
        if (indexStart < 0) return false
        edgesOfStart.removeAt(indexStart)
        edgeCount -= 1

        // remove edge 'end-start' if undirected graph
        if (!directed) {
            val edgesOfEnd = graph.get(end)!!
            val indexEnd = edgesOfEnd.indexOfFirst { edge -> edge.start == end && edge.end == start }
            if (indexEnd < 0) throw IllegalAccessException("edge between endKey-startKey does not exist for undirected graph")
            edgesOfEnd.removeAt(indexEnd)
            edgeCount -= 1
        }

        return true
    }

    // O(1)
    fun getEdges(nodeKey: Int): List<Edge> {
        return graph[nodeKey]
    }

    // O(V+E) ? O(E)?
    fun getAllEdges(): List<Edge> {
        if (edgeCount == 0) return emptyList()
        val allEdges = mutableListOf<Edge>()
        for (edgeList in graph) {
            allEdges.addAll(edgeList)
        }

        return allEdges
    }

}