package graph.dag

import graph.representation.ArrayAdjacencyListGraph

import org.junit.jupiter.api.Test

internal class TopologicalSortTest {

    @Test
    fun `topological ordering 1`() {
        val graph = ArrayAdjacencyListGraph(directed = true, vertexCount = 13)
        graph.addEdge(from = 0, to = 1)
        graph.addEdge(from = 0, to = 2)
        graph.addEdge(from = 1, to = 3)
        graph.addEdge(from = 2, to = 3)
        graph.addEdge(from = 4, to = 1)
        graph.addEdge(from = 4, to = 3)
        graph.addEdge(from = 4, to = 5)
        graph.addEdge(from = 3, to = 6)
        graph.addEdge(from = 3, to = 7)
        graph.addEdge(from = 5, to = 8)
        graph.addEdge(from = 5, to = 9)
        graph.addEdge(from = 6, to = 9)
        graph.addEdge(from = 6, to = 10)
        graph.addEdge(from = 7, to = 10)
        graph.addEdge(from = 9, to = 11)
        graph.addEdge(from = 9, to = 12)
        graph.addEdge(from = 10, to = 12)

        val topologicalSort = topologicalSort(graph)
        for (order in topologicalSort)
            print("$order->")
    }
}