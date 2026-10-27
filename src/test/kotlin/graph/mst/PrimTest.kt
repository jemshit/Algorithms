package graph.mst

import graph.representation.ArrayAdjacencyListGraph
import graph.representation.AdjacencyMatrixGraph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class PrimTest {

    @Test
    fun `works with an adjacency matrix through the graph contract`() {
        val graph = AdjacencyMatrixGraph(directed = false, vertexCount = 4)
        graph.addEdge(0, 1, 1)
        graph.addEdge(0, 2, 4)
        graph.addEdge(1, 2, 2)
        graph.addEdge(1, 3, 5)
        graph.addEdge(2, 3, 3)

        val result = primMinimumSpanningTree(graph, 0)

        assertEquals(3, result.edges.size)
        assertEquals(6, result.totalWeight)
    }

    @Test
    fun test1() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 10)
        graph.addEdge(0, 1, 5)
        graph.addEdge(1, 2, 4)
        graph.addEdge(2, 9, 2)
        graph.addEdge(0, 4, 1)
        graph.addEdge(0, 3, 4)
        graph.addEdge(1, 3, 2)
        graph.addEdge(2, 7, 4)
        graph.addEdge(2, 8, 1)
        graph.addEdge(9, 8, 0)
        graph.addEdge(4, 5, 1)
        graph.addEdge(5, 6, 7)
        graph.addEdge(6, 8, 4)
        graph.addEdge(4, 3, 2)
        graph.addEdge(5, 3, 5)
        graph.addEdge(3, 6, 11)
        graph.addEdge(6, 7, 1)
        graph.addEdge(3, 7, 2)
        graph.addEdge(7, 8, 6)

        val result = primMinimumSpanningTree(graph, 0)

        assertEquals(9, result.edges.size)
        assertEquals(14, result.totalWeight)
    }

    @Test
    fun test2() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 7)
        graph.addEdge(0, 1, 9)
        graph.addEdge(0, 2, 0)
        graph.addEdge(0, 3, 5)
        graph.addEdge(0, 5, 7)
        graph.addEdge(1, 3, -2)
        graph.addEdge(1, 4, 3)
        graph.addEdge(1, 6, 4)
        graph.addEdge(2, 5, 6)
        graph.addEdge(3, 5, 2)
        graph.addEdge(3, 6, 3)
        graph.addEdge(4, 6, 6)
        graph.addEdge(5, 6, 1)

        val result = primMinimumSpanningTree(graph, 0)

        assertEquals(6, result.edges.size)
        assertEquals(9, result.totalWeight)
    }

    @Test
    fun test3() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 9)
        graph.addEdge(0, 1, 6)
        graph.addEdge(0, 3, 3)
        graph.addEdge(1, 2, 4)
        graph.addEdge(1, 4, 2)
        graph.addEdge(2, 5, 12)
        graph.addEdge(3, 4, 1)
        graph.addEdge(3, 6, 8)
        graph.addEdge(4, 5, 7)
        graph.addEdge(4, 7, 9)
        graph.addEdge(5, 8, 10)
        graph.addEdge(6, 7, 11)
        graph.addEdge(7, 8, 5)

        val result = primMinimumSpanningTree(graph, 0)

        assertEquals(8, result.edges.size)
        assertEquals(39, result.totalWeight)
    }

    @Test
    fun `disconnected graph is rejected`() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 3)
        graph.addEdge(0, 1)

        assertThrows(IllegalArgumentException::class.java) {
            primMinimumSpanningTree(graph, 0)
        }
    }

}
