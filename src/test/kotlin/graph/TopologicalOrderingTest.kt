package graph

import org.junit.jupiter.api.Test

internal class TopologicalOrderingTest {

    @Test
    fun `topological ordering 1`() {
        val graph = GraphUsingAdjacencyList(directed = true, nodeCount = 13)
        graph.addEdge(start = 0, end = 1)
        graph.addEdge(start = 0, end = 2)
        graph.addEdge(start = 1, end = 3)
        graph.addEdge(start = 2, end = 3)
        graph.addEdge(start = 4, end = 1)
        graph.addEdge(start = 4, end = 3)
        graph.addEdge(start = 4, end = 5)
        graph.addEdge(start = 3, end = 6)
        graph.addEdge(start = 3, end = 7)
        graph.addEdge(start = 5, end = 8)
        graph.addEdge(start = 5, end = 9)
        graph.addEdge(start = 6, end = 9)
        graph.addEdge(start = 6, end = 10)
        graph.addEdge(start = 7, end = 10)
        graph.addEdge(start = 9, end = 11)
        graph.addEdge(start = 9, end = 12)
        graph.addEdge(start = 10, end = 12)

        val topologicalOrdering = graph.topologicalOrdering()
        for (order in topologicalOrdering)
            print("$order->")
    }
}