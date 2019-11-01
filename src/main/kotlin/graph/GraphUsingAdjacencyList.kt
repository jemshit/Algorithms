package graph

import java.util.*

class GraphUsingAdjacencyList {

    data class Edge(
        val start: Int,
        val end: Int,
        var weight: Int? = null
    )

    private val directed: Boolean
    private val graph: Array<MutableList<Edge>>
    private var edgeCount: Int = 0
    private val nodeCount: Int

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

    // O(V+E) same edge visited from both nodes
    fun depthFirstTraversal(startNode: Int)
            : List<Int> {
        if (startNode >= nodeCount) throw IllegalArgumentException()

        val nodes = mutableListOf<Int>()
        val visited = Array<Boolean>(nodeCount) { false }
        val stack = Stack<Int>()
        stack.push(startNode)
        visited[startNode] = true

        while (stack.isNotEmpty()) {
            val node = stack.pop()
            nodes.add(node)

            val edges = getEdges(node)
            for (index in edges.size - 1 downTo 0) {
                if (!visited[edges[index].end]) {
                    visited[edges[index].end] = true
                    stack.push(edges[index].end)
                }
            }
        }

        return nodes
    }

    // O(V+E)
    fun breadthFirstTraversal(startNode: Int)
            : List<Int> {
        if (startNode >= nodeCount) throw IllegalArgumentException()

        val nodes = mutableListOf<Int>()
        val visited = Array<Boolean>(nodeCount) { false }
        val queue: Queue<Int> = LinkedList<Int>()
        queue.offer(startNode)
        visited[startNode] = true

        while (queue.isNotEmpty()) {
            val node = queue.poll()
            nodes.add(node)
            for (edge in getEdges(node)) {
                if (!visited[edge.end]) {
                    visited[edge.end] = true
                    queue.offer(edge.end)
                }
            }
        }

        return nodes
    }

    fun breadthFirstPathFind(startNode: Int, findNode: Int)
            : List<Int> {
        return pathFind(startNode, findNode, isBfs = true)
    }

    fun depthFirstPathFind(startNode: Int, findNode: Int)
            : List<Int> {
        return pathFind(startNode, findNode, isBfs = false)
    }

    private fun pathFind(startNode: Int, findNode: Int, isBfs: Boolean)
            : List<Int> {
        val prevNodes = if (isBfs)
            breadthFirstSearchPrevNodes(startNode, findNode)
        else
            depthFirstSearchPrevNodes(startNode, findNode)

        val path = mutableListOf<Int>()
        var node = findNode
        while (node != prevNodes[node]) {
            path.add(node)
            node = prevNodes[node]
        }
        path.add(node)

        return path.reversed()
    }

    private fun breadthFirstSearchPrevNodes(startNode: Int, findNode: Int)
            : List<Int> {
        if (startNode >= nodeCount || findNode >= nodeCount) throw IllegalArgumentException()

        // BFS Traverse, find previous nodes of each node
        val prevNodes = arrayOfNulls<Int>(nodeCount)
        val visited = Array<Boolean>(nodeCount) { false }
        val queue: Queue<Int> = LinkedList<Int>()
        queue.offer(startNode)
        visited[startNode] = true
        prevNodes[startNode] = startNode

        while (queue.isNotEmpty()) {
            val node = queue.poll()
            if (node == findNode) break
            for (edge in getEdges(node)) {
                if (!visited[edge.end]) {
                    visited[edge.end] = true
                    queue.offer(edge.end)
                    prevNodes[edge.end] = node
                }
            }
        }

        return prevNodes.toList() as List<Int>
    }

    private fun depthFirstSearchPrevNodes(startNode: Int, findNode: Int)
            : List<Int> {
        if (startNode >= nodeCount || findNode >= nodeCount) throw IllegalArgumentException()

        val prevNodes = arrayOfNulls<Int>(nodeCount)
        val visited = Array<Boolean>(nodeCount) { false }
        val stack = Stack<Int>()
        stack.push(startNode)
        visited[startNode] = true
        prevNodes[startNode] = startNode

        while (stack.isNotEmpty()) {
            val node = stack.pop()
            if (node == findNode) break
            val edges = getEdges(node)
            for (index in edges.size - 1 downTo 0) {
                if (!visited[edges[index].end]) {
                    visited[edges[index].end] = true
                    stack.push(edges[index].end)
                    prevNodes[edges[index].end] = node
                }
            }
        }

        return prevNodes.toList() as List<Int>
    }

}