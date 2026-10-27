package graph.connectivity

import graph.representation.MapAdjacencyListGraph
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class KosarajuTest {

    @Test
    fun `finds strongly connected components including an isolated vertex`() {
        val graph = MapAdjacencyListGraph(
            directed = true,
            vertices = listOf("A", "B", "C", "D", "E", "F")
        )
        graph.addEdge("A", "B")
        graph.addEdge("B", "C")
        graph.addEdge("C", "A")
        graph.addEdge("C", "D")
        graph.addEdge("D", "E")
        graph.addEdge("E", "D")

        val components = findStronglyConnectedComponents(graph).toSet()

        assertEquals(
            setOf(setOf("A", "B", "C"), setOf("D", "E"), setOf("F")),
            components
        )
    }

    @Test
    fun `undirected graph is rejected`() {
        val graph = MapAdjacencyListGraph(directed = false, vertices = listOf("A", "B"))

        assertThrows(IllegalArgumentException::class.java) {
            findStronglyConnectedComponents(graph)
        }
    }
}
