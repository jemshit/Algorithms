package graph

class GraphUsingAdjacencyList {

    data class Edge(
        val start: Int,
        val end: Int,
        var weight: Int
    )

    private val directed: Boolean
    private val graph: Array<MutableList<Edge>>
    private var edgeCount: Int = 0

    constructor(directed: Boolean, nodeCount: Int) {
        if (nodeCount <= 0) throw IllegalArgumentException()

        this.directed = directed
        this.graph = Array<MutableList<Edge>>(nodeCount) {
            mutableListOf<Edge>()
        }
    }

    fun edgeCount(): Int = edgeCount

    fun addEdge(
        start: Int,
        end: Int,
        weight: Int
    ) {
        // add edge 'start->end'
        graph[start].add(Edge(start, end, weight))
        edgeCount += 1

        // add edge 'end-start' if undirected graph
        if (!directed) {
            graph[end].add(Edge(end, start, weight))
            edgeCount += 1
        }
    }

    fun removeEdge(
        start: Int,
        end: Int
    ): Boolean {
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

    fun getEdges(nodeKey: Int): List<Edge> {
        return graph[nodeKey]
    }

    fun getAllEdges(): List<Edge> {
        if (edgeCount == 0) return emptyList()
        val allEdges = mutableListOf<Edge>()
        for (edgeList in graph) {
            allEdges.addAll(edgeList)
        }

        return allEdges
    }

}