package graph.representation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class ArrayAdjacencyListGraphTest {

    @Test
    fun `directed graph implements the common contract`() {
        val graph: Graph<Int> = ArrayAdjacencyListGraph(directed = true, vertexCount = 3)

        graph.addEdge(0, 1, 5)
        graph.addEdge(1, 2, 7)

        assertEquals(3, graph.vertexCount)
        assertEquals(2, graph.edgeCount)
        assertEquals(listOf(Edge(1, 5)), graph.outgoingEdges(0))
        assertTrue(graph.hasEdge(1, 2))
        assertFalse(graph.hasEdge(2, 1))
    }

    @Test
    fun `adding an existing edge updates its weight`() {
        val graph = ArrayAdjacencyListGraph(directed = true, vertexCount = 2)

        graph.addEdge(0, 1, 5)
        graph.addEdge(0, 1, 9)

        assertEquals(1, graph.edgeCount)
        assertEquals(listOf(Edge(1, 9)), graph.outgoingEdges(0))
    }

    @Test
    fun `undirected edge is stored twice but counted once`() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 3)

        graph.addEdge(0, 1, 5)
        graph.addEdge(1, 2, 7)

        assertEquals(2, graph.edgeCount)
        assertEquals(listOf(Edge(1, 5)), graph.outgoingEdges(0))
        assertEquals(listOf(Edge(0, 5), Edge(2, 7)), graph.outgoingEdges(1))
        assertEquals(listOf(Edge(1, 7)), graph.outgoingEdges(2))
    }

    @Test
    fun `remove edge removes both undirected adjacency entries`() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 2)
        graph.addEdge(0, 1)

        assertTrue(graph.removeEdge(0, 1))
        assertFalse(graph.removeEdge(0, 1))
        assertEquals(0, graph.edgeCount)
        assertTrue(graph.outgoingEdges(0).isEmpty())
        assertTrue(graph.outgoingEdges(1).isEmpty())
    }

    @Test
    fun `outgoing edges returns a snapshot`() {
        val graph = ArrayAdjacencyListGraph(directed = true, vertexCount = 3)
        graph.addEdge(0, 1)
        val snapshot = graph.outgoingEdges(0)

        graph.addEdge(0, 2)

        assertEquals(listOf(Edge(1)), snapshot)
        assertEquals(listOf(Edge(1), Edge(2)), graph.outgoingEdges(0))
    }
}
