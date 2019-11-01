package graph

// Uses AdjacencyList, but HashTable<KEY, List<Edge>> instead of Array<List<Edge>> to support non-integer KEY
class GraphUsingMapOfList<KEY : Any, DATA : Any?>(private val directed: Boolean) {

    data class Node<KEY : Any, DATA : Any?>(
        val key: KEY,
        val data: DATA? = null
    )

    data class Edge<KEY : Any, DATA : Any?>(
        val start: Node<KEY, DATA>,
        val end: Node<KEY, DATA>,
        var weight: Int
    )

    private val graph: MutableMap<KEY, MutableList<Edge<KEY, DATA>>> = mutableMapOf()
    private var edgeCount: Int = 0

    fun edgeCount(): Int = edgeCount

    // O(E) because of update weight if edge exists
    fun addOrUpdateEdge(
        start: Node<KEY, DATA>,
        end: Node<KEY, DATA>,
        weight: Int
    ) {
        // update/insert edge 'start->end'
        val edgesOfStart = graph.get(start.key) ?: mutableListOf()
        var updatedStart = false
        for (edge in edgesOfStart) {
            if (edge.start.key == start.key && edge.end.key == end.key) {
                edge.weight = weight
                updatedStart = true
                break
            }
        }
        if (!updatedStart) {
            edgesOfStart.add(Edge(start, end, weight))
            edgeCount += 1
        }
        graph.put(start.key, edgesOfStart)

        // update/insert edge 'end-start' if undirected graph
        if (!directed) {
            val edgesOfEnd = graph.get(end.key) ?: mutableListOf()
            var updatedEnd = false
            for (edge in edgesOfEnd) {
                if (edge.start.key == end.key && edge.end.key == start.key) {
                    edge.weight = weight
                    updatedEnd = true
                    break
                }
            }
            if (!updatedEnd) {
                edgesOfEnd.add(Edge(end, start, weight))
                edgeCount += 1
            }
            graph.put(end.key, edgesOfEnd)
        }
    }

    // O(E)
    fun removeEdge(
        startKey: KEY,
        endKey: KEY
    ): Boolean {
        // remove edge 'startKey-endKey'
        val edgesOfStart = graph.get(startKey) ?: return false
        val indexStart = edgesOfStart.indexOfFirst { edge -> edge.start.key == startKey && edge.end.key == endKey }
        if (indexStart < 0) return false
        edgesOfStart.removeAt(indexStart)
        edgeCount -= 1
        graph.put(startKey, edgesOfStart)

        // remove edge 'endKey-startKey' if undirected graph
        if (!directed) {
            val edgesOfEnd = graph.get(endKey)!!
            val indexEnd = edgesOfEnd.indexOfFirst { edge -> edge.start.key == endKey && edge.end.key == startKey }
            if (indexEnd < 0) throw IllegalAccessException("edge between endKey-startKey does not exist for undirected graph")
            edgesOfEnd.removeAt(indexEnd)
            edgeCount -= 1
            graph.put(endKey, edgesOfEnd)
        }

        return true
    }

    fun getEdges(nodeKey: KEY): List<Edge<KEY, DATA>> {
        return graph.get(nodeKey) ?: emptyList()
    }

    fun getAllEdges(): List<Edge<KEY, DATA>> {
        if (edgeCount == 0) return emptyList()
        val allEdges = mutableListOf<Edge<KEY, DATA>>()
        for (key in graph.keys) {
            allEdges.addAll(graph.get(key) ?: emptyList())
        }

        return allEdges
    }

}