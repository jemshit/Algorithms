package graph.dag

import graph.representation.ArrayAdjacencyListGraph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DagLongestPathsTest {

    @Test
    fun `longest Path`() {
        val graph = ArrayAdjacencyListGraph(directed = true, vertexCount = 8)
        graph.addEdge(from = 0, to = 1, weight = 3)
        graph.addEdge(from = 0, to = 2, weight = 6)
        graph.addEdge(from = 1, to = 3, weight = 4)
        graph.addEdge(from = 1, to = 2, weight = 4)
        graph.addEdge(from = 1, to = 4, weight = 11)
        graph.addEdge(from = 2, to = 3, weight = 8)
        graph.addEdge(from = 2, to = 6, weight = 11)
        graph.addEdge(from = 3, to = 4, weight = -4)
        graph.addEdge(from = 3, to = 5, weight = 5)
        graph.addEdge(from = 3, to = 6, weight = 2)
        graph.addEdge(from = 4, to = 7, weight = 9)
        graph.addEdge(from = 5, to = 7, weight = 1)
        graph.addEdge(from = 6, to = 7, weight = 2)

        val longestPaths = dagLongestPaths(graph, 0)

        assertEquals(mapOf(0 to 0, 1 to 3, 2 to 7, 3 to 15, 4 to 14, 5 to 20, 6 to 18, 7 to 23), longestPaths)
    }
}
