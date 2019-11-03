package graph

import org.junit.jupiter.api.Test

internal class LongestPathDAGTest {

    @Test
    fun `longest Path`() {
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

        val longestPaths = graph.longestPathDAG()
        for (index in longestPaths.indices)
            println("$index: ${longestPaths.get(index)}")
    }
}