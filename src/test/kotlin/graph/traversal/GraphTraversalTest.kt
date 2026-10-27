package graph.traversal

import graph.representation.ArrayAdjacencyListGraph
import graph.representation.Graph
import graph.representation.MapAdjacencyListGraph

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class GraphTraversalTest {

    @Test
    fun `the same algorithm accepts map based graphs`() {
        val graph: Graph<String> = MapAdjacencyListGraph(
            directed = false,
            vertices = listOf("A", "B", "C", "D")
        )
        graph.addEdge("A", "B")
        graph.addEdge("A", "C")
        graph.addEdge("B", "D")

        Assertions.assertEquals(
            listOf("A", "B", "C", "D"),
            breadthFirstTraversal(graph, "A")
        )
    }

    @Test
    fun `bfs test`() {
        val vertexCount = 8
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = vertexCount)
        graph.addEdge(from = 0, to = 1)
        graph.addEdge(from = 0, to = 2)
        graph.addEdge(from = 0, to = 3)
        graph.addEdge(from = 1, to = 4)
        graph.addEdge(from = 1, to = 5)
        graph.addEdge(from = 2, to = 6)
        graph.addEdge(from = 3, to = 7)
        // cycle
        graph.addEdge(from = 1, to = 2)
        graph.addEdge(from = 2, to = 3)
        Assertions.assertEquals(9, graph.edgeCount)

        val bfsNodes = breadthFirstTraversal(graph, 0)
        Assertions.assertEquals(vertexCount, bfsNodes.size)
        for (index in 0 until vertexCount) {
            Assertions.assertEquals(index, bfsNodes[index])
        }
    }

    @Test
    fun `dfs test`() {
        val vertexCount = 8
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = vertexCount)
        graph.addEdge(from = 0, to = 1)
        graph.addEdge(from = 0, to = 2)
        graph.addEdge(from = 0, to = 3)
        graph.addEdge(from = 1, to = 4)
        graph.addEdge(from = 1, to = 5)
        graph.addEdge(from = 2, to = 6)
        graph.addEdge(from = 3, to = 7)
        // cycle
        graph.addEdge(from = 1, to = 2)
        graph.addEdge(from = 2, to = 3)
        Assertions.assertEquals(9, graph.edgeCount)

        val dfsNodes = depthFirstTraversal(graph, 0)
        Assertions.assertEquals(vertexCount, dfsNodes.size)
        Assertions.assertEquals(0, dfsNodes[0])
        Assertions.assertEquals(1, dfsNodes[1])
        Assertions.assertEquals(4, dfsNodes[2])
        Assertions.assertEquals(5, dfsNodes[3])
        Assertions.assertEquals(2, dfsNodes[4])
        Assertions.assertEquals(6, dfsNodes[5])
        Assertions.assertEquals(3, dfsNodes[6])
        Assertions.assertEquals(7, dfsNodes[7])
    }

    @Test
    fun `dfs recursive test`() {
        val vertexCount = 8
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = vertexCount)
        graph.addEdge(from = 0, to = 1)
        graph.addEdge(from = 0, to = 2)
        graph.addEdge(from = 0, to = 3)
        graph.addEdge(from = 1, to = 4)
        graph.addEdge(from = 1, to = 5)
        graph.addEdge(from = 2, to = 6)
        graph.addEdge(from = 3, to = 7)
        // cycle
        graph.addEdge(from = 1, to = 2)
        graph.addEdge(from = 2, to = 3)
        Assertions.assertEquals(9, graph.edgeCount)

        val dfsNodes = depthFirstTraversalRecursive(graph, 0)
        Assertions.assertEquals(vertexCount, dfsNodes.size)
        Assertions.assertEquals(0, dfsNodes[0])
        Assertions.assertEquals(1, dfsNodes[1])
        Assertions.assertEquals(4, dfsNodes[2])
        Assertions.assertEquals(5, dfsNodes[3])
        Assertions.assertEquals(2, dfsNodes[4])
        Assertions.assertEquals(6, dfsNodes[5])
        Assertions.assertEquals(3, dfsNodes[6])
        Assertions.assertEquals(7, dfsNodes[7])
    }

}
