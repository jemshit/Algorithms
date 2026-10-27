package graph.shortestpath

import graph.representation.ArrayAdjacencyListGraph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class BellmanFordTest {

    @Test
    fun `shortest Path`() {
        val graph = ArrayAdjacencyListGraph(directed = true, vertexCount = 8)
        graph.addEdge(from = 0, to = 1, weight = 3)
        graph.addEdge(from = 0, to = 2, weight = 6)
        graph.addEdge(from = 1, to = 3, weight = 4)
        graph.addEdge(from = 1, to = 2, weight = 4)
        graph.addEdge(from = 1, to = 4, weight = 11)
        graph.addEdge(from = 2, to = 3, weight = 8)
        graph.addEdge(from = 2, to = 6, weight = 11)
        graph.addEdge(from = 3, to = 4, weight = -4)
        graph.addEdge(from = 3, to = 5, weight = 5)
        graph.addEdge(from = 3, to = 6, weight = 2)
        graph.addEdge(from = 4, to = 7, weight = 9)
        graph.addEdge(from = 5, to = 7, weight = 1)
        graph.addEdge(from = 6, to = 7, weight = 2)

        val shortestPaths = bellmanFord(graph, 0)

        assertEquals(mapOf(0 to 0, 1 to 3, 2 to 6, 3 to 7, 4 to 3, 5 to 12, 6 to 9, 7 to 11), shortestPaths)
    }

    @Test
    fun `shortest Path 2`() {
        val graph = ArrayAdjacencyListGraph(directed = true, vertexCount = 5)
        graph.addEdge(from = 0, to = 1, weight = 4)
        graph.addEdge(from = 0, to = 2, weight = 1)
        graph.addEdge(from = 2, to = 1, weight = 2)
        graph.addEdge(from = 1, to = 3, weight = 1)
        graph.addEdge(from = 2, to = 3, weight = 5)
        graph.addEdge(from = 3, to = 4, weight = 3)

        val shortestPaths = bellmanFord(graph, 0)

        assertEquals(mapOf(0 to 0, 1 to 3, 2 to 1, 3 to 4, 4 to 7), shortestPaths)
    }

    @Test
    fun `shortest Path 3`() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 9)
        graph.addEdge(from = 0, to = 1, weight = 4)
        graph.addEdge(from = 0, to = 7, weight = 8)

        graph.addEdge(from = 1, to = 7, weight = 11)
        graph.addEdge(from = 1, to = 2, weight = 8)

        graph.addEdge(from = 7, to = 8, weight = 7)
        graph.addEdge(from = 7, to = 6, weight = 1)

        graph.addEdge(from = 2, to = 3, weight = 7)
        graph.addEdge(from = 2, to = 5, weight = 4)
        graph.addEdge(from = 2, to = 8, weight = 2)

        graph.addEdge(from = 8, to = 6, weight = 6)

        graph.addEdge(from = 6, to = 5, weight = 2)

        graph.addEdge(from = 3, to = 4, weight = 9)
        graph.addEdge(from = 3, to = 5, weight = 14)

        graph.addEdge(from = 5, to = 4, weight = 10)

        val shortestPaths = bellmanFord(graph, 0)

        assertEquals(
            mapOf(0 to 0, 1 to 4, 2 to 12, 3 to 19, 4 to 21, 5 to 11, 6 to 9, 7 to 8, 8 to 14),
            shortestPaths
        )
    }

    @Test
    fun `shortest Path negative cycle`() {
        val graph = ArrayAdjacencyListGraph(directed = true, vertexCount = 2)
        graph.addEdge(from = 0, to = 1, weight = -3)
        graph.addEdge(from = 1, to = 0, weight = 1)

        val shortestPaths = bellmanFord(graph, 0)

        assertEquals(mapOf(0 to Int.MIN_VALUE, 1 to Int.MIN_VALUE), shortestPaths)
    }


}
