package graph.dag

import graph.representation.ArrayAdjacencyListGraph
import graph.representation.Graph
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class TopologicalSortTest {

    @Test
    fun `both algorithms return a valid topological order`() {
        val graph = ArrayAdjacencyListGraph(directed = true, vertexCount = 6)
        graph.addEdge(from = 5, to = 2)
        graph.addEdge(from = 5, to = 0)
        graph.addEdge(from = 4, to = 0)
        graph.addEdge(from = 4, to = 1)
        graph.addEdge(from = 2, to = 3)
        graph.addEdge(from = 3, to = 1)

        val results = listOf(
            topologicalSortDfs(graph),
            topologicalSortKahn(graph)
        )

        for (result in results) {
            assertEquals(graph.vertexCount, result.size)
            assertTopologicalOrder(graph, result)
        }
    }

    @Test
    fun `both algorithms reject a directed cycle`() {
        val graph = ArrayAdjacencyListGraph(directed = true, vertexCount = 3)
        graph.addEdge(from = 0, to = 1)
        graph.addEdge(from = 1, to = 2)
        graph.addEdge(from = 2, to = 0)

        assertThrows(IllegalArgumentException::class.java) { topologicalSortDfs(graph) }
        assertThrows(IllegalArgumentException::class.java) { topologicalSortKahn(graph) }
    }

    @Test
    fun `both algorithms reject an undirected graph`() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 2)
        graph.addEdge(from = 0, to = 1)

        assertThrows(IllegalArgumentException::class.java) { topologicalSortDfs(graph) }
        assertThrows(IllegalArgumentException::class.java) { topologicalSortKahn(graph) }
    }

    private fun <V> assertTopologicalOrder(graph: Graph<V>, result: List<V>) {
        val position = result.withIndex().associate { (index, vertex) -> vertex to index }
        for (from in graph.vertices()) {
            for (edge in graph.outgoingEdges(from)) {
                assertTrue(position.getValue(from) < position.getValue(edge.to))
            }
        }
    }
}
