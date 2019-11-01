package graph

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GraphUsingAdjacencyListTest {

    @Test
    fun `directed, add-get edge int edge`() {
        val graph = GraphUsingAdjacencyList(directed = true, nodeCount = 3)
        assertEquals(0, graph.edgeCount())

        graph.addEdge(start = 0, end = 1, weight = 1)
        graph.addEdge(start = 1, end = 2, weight = 2)
        assertEquals(2, graph.edgeCount())

        val edgesOfOne = graph.getEdges(0)
        assertEquals(1, edgesOfOne.size)
        assertEquals(0, edgesOfOne[0].start)
        assertEquals(1, edgesOfOne[0].end)
        assertEquals(1, edgesOfOne[0].weight)

        val edgesOfTwo = graph.getEdges(1)
        assertEquals(1, edgesOfTwo.size)
        assertEquals(1, edgesOfTwo[0].start)
        assertEquals(2, edgesOfTwo[0].end)
        assertEquals(2, edgesOfTwo[0].weight)
    }

    @Test
    fun `directed, remove int edge`() {
        val graph = GraphUsingAdjacencyList(directed = true, nodeCount = 3)
        assertEquals(0, graph.edgeCount())

        // Insert
        graph.addEdge(start = 0, end = 1, weight = 1)
        graph.addEdge(start = 1, end = 2, weight = 2)
        assertEquals(2, graph.edgeCount())

        // Update
        assertTrue(graph.removeEdge(start = 0, end = 1))
        assertEquals(1, graph.edgeCount())
        assertEquals(0, graph.getEdges(0).size)

        assertFalse(graph.removeEdge(start = 0, end = 2))

        assertTrue(graph.removeEdge(start = 1, end = 2))
        assertEquals(0, graph.edgeCount())
        assertEquals(0, graph.getEdges(1).size)
    }

    @Test
    fun `directed, get all edges`() {
        val graph = GraphUsingAdjacencyList(directed = true, nodeCount = 3)
        assertEquals(0, graph.edgeCount())

        // Insert
        graph.addEdge(start = 0, end = 1, weight = 1)
        graph.addEdge(start = 1, end = 2, weight = 2)
        graph.addEdge(start = 0, end = 2, weight = 3)
        assertEquals(3, graph.edgeCount())

        // Get all edges
        val allEdges = graph.getAllEdges()
        assertEquals(3, allEdges.size)
    }

    @Test
    fun `undirected, add-get edge int edge`() {
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 3)
        assertEquals(0, graph.edgeCount())

        graph.addEdge(start = 0, end = 1, weight = 1)
        graph.addEdge(start = 1, end = 2, weight = 2)
        assertEquals(4, graph.edgeCount())

        val edgesOfOne = graph.getEdges(0)
        assertEquals(1, edgesOfOne.size)
        assertEquals(0, edgesOfOne[0].start)
        assertEquals(1, edgesOfOne[0].end)
        assertEquals(1, edgesOfOne[0].weight)

        val edgesOfTwo = graph.getEdges(1)
        assertEquals(2, edgesOfTwo.size)

        val edgesOfThree = graph.getEdges(2)
        assertEquals(1, edgesOfThree.size)
        assertEquals(2, edgesOfThree[0].start)
        assertEquals(1, edgesOfThree[0].end)
        assertEquals(2, edgesOfThree[0].weight)
    }

    @Test
    fun `undirected, remove int edge`() {
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 3)
        assertEquals(0, graph.edgeCount())

        // Insert
        graph.addEdge(start = 0, end = 1, weight = 1)
        graph.addEdge(start = 1, end = 2, weight = 2)
        assertEquals(4, graph.edgeCount())

        // Update
        assertTrue(graph.removeEdge(start = 0, end = 1))
        assertEquals(2, graph.edgeCount())
        assertEquals(0, graph.getEdges(0).size)
        assertEquals(1, graph.getEdges(1).size)
        assertEquals(1, graph.getEdges(2).size)

        assertFalse(graph.removeEdge(start = 0, end = 2))

        assertTrue(graph.removeEdge(start = 1, end = 2))
        assertEquals(0, graph.edgeCount())
        assertEquals(0, graph.getEdges(0).size)
        assertEquals(0, graph.getEdges(1).size)
        assertEquals(0, graph.getEdges(2).size)
    }

    @Test
    fun `undirected, get all edges`() {
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 3)
        assertEquals(0, graph.edgeCount())

        // Insert
        graph.addEdge(start = 0, end = 1, weight = 1)
        graph.addEdge(start = 1, end = 2, weight = 2)
        graph.addEdge(start = 0, end = 2, weight = 3)
        assertEquals(6, graph.edgeCount())

        // Get all edges
        val allEdges = graph.getAllEdges()
        assertEquals(6, allEdges.size)
    }

}

internal class GraphUsingAdjacencyListTraversalTest {

    @Test
    fun `bfs test`() {
        val nodeCount = 8
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = nodeCount)
        graph.addEdge(start = 0, end = 1)
        graph.addEdge(start = 0, end = 2)
        graph.addEdge(start = 0, end = 3)
        graph.addEdge(start = 1, end = 4)
        graph.addEdge(start = 1, end = 5)
        graph.addEdge(start = 2, end = 6)
        graph.addEdge(start = 3, end = 7)
        // cycle
        graph.addEdge(start = 1, end = 2)
        graph.addEdge(start = 2, end = 3)
        assertEquals(18, graph.edgeCount())

        val bfsNodes = graph.breadthFirstTraversal(0)
        assertEquals(nodeCount, bfsNodes.size)
        for (index in 0 until nodeCount) {
            assertEquals(index, bfsNodes[index])
        }
    }

    @Test
    fun `dfs test`() {
        val nodeCount = 8
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = nodeCount)
        graph.addEdge(start = 0, end = 1)
        graph.addEdge(start = 0, end = 2)
        graph.addEdge(start = 0, end = 3)
        graph.addEdge(start = 1, end = 4)
        graph.addEdge(start = 1, end = 5)
        graph.addEdge(start = 2, end = 6)
        graph.addEdge(start = 3, end = 7)
        // cycle
        graph.addEdge(start = 1, end = 2)
        graph.addEdge(start = 2, end = 3)
        assertEquals(18, graph.edgeCount())

        val dfsNodes = graph.depthFirstTraversal(0)
        assertEquals(nodeCount, dfsNodes.size)
        assertEquals(0, dfsNodes[0])
        assertEquals(1, dfsNodes[1])
        assertEquals(4, dfsNodes[2])
        assertEquals(5, dfsNodes[3])
        assertEquals(2, dfsNodes[4])
        assertEquals(6, dfsNodes[5])
        assertEquals(3, dfsNodes[6])
        assertEquals(7, dfsNodes[7])
    }

}