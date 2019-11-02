package graph

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class GraphPathBuildTest {

    @Test
    fun `bfs path build`() {
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

        val path = graph.breadthFirstPathFind(0, 6)
        Assertions.assertEquals(0, path[0])
        Assertions.assertEquals(2, path[1])
        Assertions.assertEquals(6, path[2])
    }

    @Test
    fun `bfs path build 2`() {
        val nodeCount = 13
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = nodeCount)
        graph.addEdge(0, 7)
        graph.addEdge(0, 9)
        graph.addEdge(0, 11)
        graph.addEdge(7, 11)
        graph.addEdge(7, 6)
        graph.addEdge(7, 3)
        graph.addEdge(6, 5)
        graph.addEdge(3, 4)
        graph.addEdge(2, 3)
        graph.addEdge(2, 12)
        graph.addEdge(12, 8)
        graph.addEdge(8, 1)
        graph.addEdge(1, 10)
        graph.addEdge(10, 9)
        graph.addEdge(9, 8)

        val path = graph.breadthFirstPathFind(10, 5)
        Assertions.assertEquals(10, path[0])
        Assertions.assertEquals(9, path[1])
        Assertions.assertEquals(0, path[2])
        Assertions.assertEquals(7, path[3])
        Assertions.assertEquals(6, path[4])
        Assertions.assertEquals(5, path[5])
    }

    @Test
    fun `dfs path build`() {
        val nodeCount = 8
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = nodeCount)
        graph.addEdge(start = 0, end = 1)
        graph.addEdge(start = 0, end = 3)
        graph.addEdge(start = 1, end = 4)
        graph.addEdge(start = 1, end = 2)
        graph.addEdge(start = 1, end = 5)
        graph.addEdge(start = 2, end = 6)
        graph.addEdge(start = 3, end = 7)

        // cycle
        graph.addEdge(start = 1, end = 2)
        graph.addEdge(start = 2, end = 3)
        Assertions.assertEquals(18, graph.edgeCount())

        val path = graph.depthFirstPathFind(0, 6)
        Assertions.assertEquals(0, path[0])
        Assertions.assertEquals(1, path[1])
        Assertions.assertEquals(2, path[2])
        Assertions.assertEquals(6, path[3])
    }

}