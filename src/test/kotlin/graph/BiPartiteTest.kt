package graph

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class BiPartiteTest {

    @Test
    fun test1() {
        val graph = GraphUsingAdjacencyList(directed = true, nodeCount = 5)
        graph.addEdge(0, 1)
        graph.addEdge(0, 3)
        graph.addEdge(1, 0)
        graph.addEdge(1, 2)
        graph.addEdge(2, 1)
        graph.addEdge(2, 3)
        graph.addEdge(3, 0)
        graph.addEdge(3, 2)

        val isBipartite = graph.isBipartite()

        assertTrue(isBipartite)
    }

    @Test
    fun test2() {
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 5)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(0, 3)
        graph.addEdge(3, 2)

        val isBipartite = graph.isBipartite()

        assertFalse(isBipartite)
    }

    @Test
    fun test3() {
        // self loop
        val graph = GraphUsingAdjacencyList(directed = true, nodeCount = 5)
        graph.addEdge(1, 1)

        val isBipartite = graph.isBipartite()

        assertFalse(isBipartite)
    }

}