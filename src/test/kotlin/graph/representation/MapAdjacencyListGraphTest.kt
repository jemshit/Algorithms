package graph.representation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class MapAdjacencyListGraphTest {

    @Test
    fun `map graph implements the common contract`() {
        val graph: Graph<String> = MapAdjacencyListGraph(
            directed = true,
            vertices = listOf("A", "B", "C")
        )

        graph.addEdge("A", "B", 5)
        graph.addEdge("B", "C", 7)

        assertEquals(3, graph.vertexCount)
        assertEquals(2, graph.edgeCount)
        assertEquals(listOf("A", "B", "C"), graph.vertices().toList())
        assertEquals(listOf(Edge("B", 5)), graph.outgoingEdges("A"))
        assertTrue(graph.hasEdge("B", "C"))
        assertFalse(graph.hasEdge("C", "B"))
    }

    @Test
    fun `isolated vertices exist without edges`() {
        val graph = MapAdjacencyListGraph(
            directed = false,
            vertices = listOf("A", "B", "isolated")
        )
        graph.addEdge("A", "B")

        assertEquals(3, graph.vertexCount)
        assertTrue(graph.outgoingEdges("isolated").isEmpty())
    }

    @Test
    fun `undirected update and removal stay symmetric`() {
        val graph = MapAdjacencyListGraph(
            directed = false,
            vertices = listOf("A", "B")
        )

        graph.addEdge("A", "B", 2)
        graph.addEdge("A", "B", 8)
        assertEquals(1, graph.edgeCount)
        assertEquals(listOf(Edge("B", 8)), graph.outgoingEdges("A"))
        assertEquals(listOf(Edge("A", 8)), graph.outgoingEdges("B"))

        assertTrue(graph.removeEdge("A", "B"))
        assertEquals(0, graph.edgeCount)
        assertFalse(graph.hasEdge("A", "B"))
    }

    @Test
    fun `outgoing edges returns a snapshot`() {
        val graph = MapAdjacencyListGraph(
            directed = true,
            vertices = listOf("A", "B", "C")
        )
        graph.addEdge("A", "B")
        val snapshot = graph.outgoingEdges("A")

        graph.addEdge("A", "C")

        assertEquals(listOf(Edge("B")), snapshot)
        assertEquals(listOf(Edge("B"), Edge("C")), graph.outgoingEdges("A"))
    }
}
