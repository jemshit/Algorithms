package graph.mst

import graph.representation.ArrayAdjacencyListGraph
import graph.representation.MapAdjacencyListGraph
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class KruskalTest {

    @Test
    fun `finds an MST with negative edges`() {
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

        val result = kruskalMinimumSpanningTree(graph)

        assertEquals(6, result.edges.size)
        assertEquals(9, result.totalWeight)
    }

    @Test
    fun `supports arbitrary vertex identifiers`() {
        val graph = MapAdjacencyListGraph(
            directed = false,
            vertices = listOf("A", "B", "C", "D")
        )
        graph.addEdge("A", "B", 1)
        graph.addEdge("A", "C", 4)
        graph.addEdge("B", "C", 2)
        graph.addEdge("B", "D", 5)
        graph.addEdge("C", "D", 3)

        val result = kruskalMinimumSpanningTree(graph)

        assertEquals(3, result.edges.size)
        assertEquals(6, result.totalWeight)
    }

    @Test
    fun `disconnected and directed graphs are rejected`() {
        val disconnected = ArrayAdjacencyListGraph(directed = false, vertexCount = 3)
        disconnected.addEdge(0, 1)
        val directed = ArrayAdjacencyListGraph(directed = true, vertexCount = 2)
        directed.addEdge(0, 1)

        assertThrows(IllegalArgumentException::class.java) {
            kruskalMinimumSpanningTree(disconnected)
        }
        assertThrows(IllegalArgumentException::class.java) {
            kruskalMinimumSpanningTree(directed)
        }
    }
}
