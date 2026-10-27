package graph.dag

import graph.representation.ArrayAdjacencyListGraph
import graph.representation.Graph
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class KahnTopologicalSortTest {

    @Test
    fun `returns a valid topological order`() {
        val graph = ArrayAdjacencyListGraph(directed = true, vertexCount = 6)
        graph.addEdge(5, 2)
        graph.addEdge(5, 0)
        graph.addEdge(4, 0)
        graph.addEdge(4, 1)
        graph.addEdge(2, 3)
        graph.addEdge(3, 1)

        val order = kahnTopologicalSort(graph)

        assertEquals(graph.vertexCount, order.size)
        assertTopologicalOrder(graph, order)
    }

    @Test
    fun `directed cycle is rejected`() {
        val graph = ArrayAdjacencyListGraph(directed = true, vertexCount = 3)
        graph.addEdge(0, 1)
        graph.addEdge(1, 2)
        graph.addEdge(2, 0)

        assertThrows(IllegalArgumentException::class.java) { kahnTopologicalSort(graph) }
    }

    private fun <V> assertTopologicalOrder(graph: Graph<V>, order: List<V>) {
        val position = order.withIndex().associate { (index, vertex) -> vertex to index }
        for (from in graph.vertices()) {
            for (edge in graph.outgoingEdges(from)) {
                check(position.getValue(from) < position.getValue(edge.to))
            }
        }
    }
}
