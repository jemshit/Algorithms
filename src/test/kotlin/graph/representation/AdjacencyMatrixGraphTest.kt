package graph.representation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class AdjacencyMatrixGraphTest {

    @Test
    fun `directed edges are stored and updated`() {
        val graph = AdjacencyMatrixGraph(directed = true, vertexCount = 3)

        graph.addEdge(0, 1, -4)
        graph.addEdge(0, 2, 0)
        graph.addEdge(0, 1, 7)

        assertEquals(2, graph.edgeCount)
        assertEquals(listOf(Edge(1, 7), Edge(2, 0)), graph.outgoingEdges(0))
        assertFalse(graph.hasEdge(1, 0))
    }

    @Test
    fun `undirected edge is stored symmetrically and counted once`() {
        val graph = AdjacencyMatrixGraph(directed = false, vertexCount = 2)

        graph.addEdge(0, 1, 5)

        assertEquals(1, graph.edgeCount)
        assertTrue(graph.hasEdge(0, 1))
        assertTrue(graph.hasEdge(1, 0))
        assertEquals(listOf(Edge(0, 5)), graph.outgoingEdges(1))

        assertTrue(graph.removeEdge(1, 0))
        assertEquals(0, graph.edgeCount)
        assertFalse(graph.hasEdge(0, 1))
        assertFalse(graph.removeEdge(0, 1))
    }

    @Test
    fun `invalid vertices and self loops are rejected`() {
        val graph = AdjacencyMatrixGraph(directed = true, vertexCount = 2)

        assertThrows(IllegalArgumentException::class.java) { graph.addEdge(0, 0) }
        assertThrows(IllegalArgumentException::class.java) { graph.addEdge(0, 1, Int.MAX_VALUE) }
        assertThrows(IllegalArgumentException::class.java) { graph.hasEdge(0, 2) }
    }
}
