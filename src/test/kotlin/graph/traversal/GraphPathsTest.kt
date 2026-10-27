package graph.traversal

import graph.representation.ArrayAdjacencyListGraph

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class GraphPathsTest {

    @Test
    fun `bfs path build`() {
        val vertexCount = 8
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = vertexCount)
        graph.addEdge(from = 0, to = 1)
        graph.addEdge(from = 0, to = 2)
        graph.addEdge(from = 0, to = 3)
        graph.addEdge(from = 1, to = 4)
        graph.addEdge(from = 1, to = 5)
        graph.addEdge(from = 2, to = 6)
        graph.addEdge(from = 3, to = 7)
        // cycle
        graph.addEdge(from = 1, to = 2)
        graph.addEdge(from = 2, to = 3)
        Assertions.assertEquals(9, graph.edgeCount)

        val path = breadthFirstPath(graph, 0, 6)
        Assertions.assertEquals(0, path[0])
        Assertions.assertEquals(2, path[1])
        Assertions.assertEquals(6, path[2])
    }

    @Test
    fun `bfs path build 2`() {
        val vertexCount = 13
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = vertexCount)
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

        val path = breadthFirstPath(graph, 10, 5)
        Assertions.assertEquals(10, path[0])
        Assertions.assertEquals(9, path[1])
        Assertions.assertEquals(0, path[2])
        Assertions.assertEquals(7, path[3])
        Assertions.assertEquals(6, path[4])
        Assertions.assertEquals(5, path[5])
    }

    @Test
    fun `dfs path build`() {
        val vertexCount = 8
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = vertexCount)
        graph.addEdge(from = 0, to = 1)
        graph.addEdge(from = 0, to = 3)
        graph.addEdge(from = 1, to = 4)
        graph.addEdge(from = 1, to = 2)
        graph.addEdge(from = 1, to = 5)
        graph.addEdge(from = 2, to = 6)
        graph.addEdge(from = 3, to = 7)

        // cycle
        graph.addEdge(from = 1, to = 2)
        graph.addEdge(from = 2, to = 3)
        Assertions.assertEquals(8, graph.edgeCount)

        val path = depthFirstPath(graph, 0, 6)
        Assertions.assertEquals(0, path[0])
        Assertions.assertEquals(1, path[1])
        Assertions.assertEquals(2, path[2])
        Assertions.assertEquals(6, path[3])
    }

}
