package graph.connectivity

import graph.representation.ArrayAdjacencyListGraph

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class BipartiteTest {

    @Test
    fun test1() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 5)
        graph.addEdge(0, 1)
        graph.addEdge(0, 3)
        graph.addEdge(1, 2)
        graph.addEdge(2, 3)

        val isBipartite = isBipartite(graph)

        assertTrue(isBipartite)
    }

    @Test
    fun test2() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 5)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(0, 3)
        graph.addEdge(3, 2)

        val isBipartite = isBipartite(graph)

        assertFalse(isBipartite)
    }

    @Test
    fun test3() {
        // An odd cycle cannot be split into two vertex sets.
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 3)
        graph.addEdge(0, 1)
        graph.addEdge(1, 2)
        graph.addEdge(2, 0)

        val isBipartite = isBipartite(graph)

        assertFalse(isBipartite)
    }

    @Test
    fun `directed graphs are rejected`() {
        val graph = ArrayAdjacencyListGraph(directed = true, vertexCount = 2)
        graph.addEdge(1, 0)

        assertThrows(IllegalArgumentException::class.java) {
            isBipartite(graph)
        }
    }

}
