package graph

import org.junit.jupiter.api.Test

internal class ShortestPathDijkstraTest {

    @Test
    fun `shortest Path`() {
        val graph = GraphUsingAdjacencyList(directed = true, nodeCount = 8)
        graph.addEdge(start = 0, end = 1, weight = 3)
        graph.addEdge(start = 0, end = 2, weight = 6)
        graph.addEdge(start = 1, end = 3, weight = 4)
        graph.addEdge(start = 1, end = 2, weight = 4)
        graph.addEdge(start = 1, end = 4, weight = 11)
        graph.addEdge(start = 2, end = 3, weight = 8)
        graph.addEdge(start = 2, end = 6, weight = 11)
        graph.addEdge(start = 3, end = 4, weight = -4)
        graph.addEdge(start = 3, end = 5, weight = 5)
        graph.addEdge(start = 3, end = 6, weight = 2)
        graph.addEdge(start = 4, end = 7, weight = 9)
        graph.addEdge(start = 5, end = 7, weight = 1)
        graph.addEdge(start = 6, end = 7, weight = 2)

        val shortestPaths = graph.shortestPathDijkstra()
        for (index in shortestPaths.indices)
            println("$index: ${shortestPaths.get(index)}")
    }

    @Test
    fun `shortest Path 2`() {
        val graph = GraphUsingAdjacencyList(directed = true, nodeCount = 5)
        graph.addEdge(start = 0, end = 1, weight = 4)
        graph.addEdge(start = 0, end = 2, weight = 1)
        graph.addEdge(start = 2, end = 1, weight = 2)
        graph.addEdge(start = 1, end = 3, weight = 1)
        graph.addEdge(start = 2, end = 3, weight = 5)
        graph.addEdge(start = 3, end = 4, weight = 3)

        val shortestPaths = graph.shortestPathDijkstra()
        for (index in shortestPaths.indices)
            println("$index: ${shortestPaths.get(index)}")
    }

    @Test
    fun `shortest Path 3`() {
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 9)
        graph.addEdge(start = 0, end = 1, weight = 4)
        graph.addEdge(start = 0, end = 7, weight = 8)

        graph.addEdge(start = 1, end = 7, weight = 11)
        graph.addEdge(start = 1, end = 2, weight = 8)

        graph.addEdge(start = 7, end = 8, weight = 7)
        graph.addEdge(start = 7, end = 6, weight = 1)

        graph.addEdge(start = 2, end = 3, weight = 7)
        graph.addEdge(start = 2, end = 5, weight = 4)
        graph.addEdge(start = 2, end = 8, weight = 2)

        graph.addEdge(start = 8, end = 6, weight = 6)

        graph.addEdge(start = 6, end = 5, weight = 2)

        graph.addEdge(start = 3, end = 4, weight = 9)
        graph.addEdge(start = 3, end = 5, weight = 14)

        graph.addEdge(start = 5, end = 4, weight = 10)

        val shortestPaths = graph.shortestPathDijkstra()
        for (index in shortestPaths.indices)
            println("$index: ${shortestPaths.get(index)}")
    }

}