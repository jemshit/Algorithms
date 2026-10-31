package graph.traversal

import graph.representation.ArrayAdjacencyListGraph

import org.junit.jupiter.api.Assertions.assertEquals
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
        assertEquals(9, graph.edgeCount)

        val path = bfsPath(graph, 0, 6)
        assertEquals(listOf(0, 2, 6), path)
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

        val path = bfsPath(graph, 10, 5)
        assertEquals(listOf(10, 9, 0, 7, 6, 5), path)
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
        assertEquals(8, graph.edgeCount)

        val path = dfsPath(graph, 0, 6)
        assertEquals(listOf(0, 1, 2, 6), path)
    }

    @Test
    fun `returns empty path when target is unreachable`() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 3)
        graph.addEdge(from = 0, to = 1)

        assertEquals(emptyList<Int>(), bfsPath(graph, start = 0, target = 2))
        assertEquals(emptyList<Int>(), dfsPath(graph, start = 0, target = 2))
    }

    @Test
    fun `path from a vertex to itself contains that vertex`() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 3)

        assertEquals(listOf(1), bfsPath(graph, start = 1, target = 1))
        assertEquals(listOf(1), dfsPath(graph, start = 1, target = 1))
    }

}
