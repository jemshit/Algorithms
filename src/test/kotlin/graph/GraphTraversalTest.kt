package graph

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class GraphTraversalTest {

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
        Assertions.assertEquals(18, graph.edgeCount())

        val bfsNodes = graph.breadthFirstTraversal(0)
        Assertions.assertEquals(nodeCount, bfsNodes.size)
        for (index in 0 until nodeCount) {
            Assertions.assertEquals(index, bfsNodes[index])
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
        Assertions.assertEquals(18, graph.edgeCount())

        val dfsNodes = graph.depthFirstTraversal(0)
        Assertions.assertEquals(nodeCount, dfsNodes.size)
        Assertions.assertEquals(0, dfsNodes[0])
        Assertions.assertEquals(1, dfsNodes[1])
        Assertions.assertEquals(4, dfsNodes[2])
        Assertions.assertEquals(5, dfsNodes[3])
        Assertions.assertEquals(2, dfsNodes[4])
        Assertions.assertEquals(6, dfsNodes[5])
        Assertions.assertEquals(3, dfsNodes[6])
        Assertions.assertEquals(7, dfsNodes[7])
    }

    @Test
    fun `dfs recursive test`() {
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
        Assertions.assertEquals(18, graph.edgeCount())

        val dfsNodes = graph.depthFirstTraversalRecursive(0)
        Assertions.assertEquals(nodeCount, dfsNodes.size)
        Assertions.assertEquals(0, dfsNodes[0])
        Assertions.assertEquals(1, dfsNodes[1])
        Assertions.assertEquals(4, dfsNodes[2])
        Assertions.assertEquals(5, dfsNodes[3])
        Assertions.assertEquals(2, dfsNodes[4])
        Assertions.assertEquals(6, dfsNodes[5])
        Assertions.assertEquals(3, dfsNodes[6])
        Assertions.assertEquals(7, dfsNodes[7])
    }

}