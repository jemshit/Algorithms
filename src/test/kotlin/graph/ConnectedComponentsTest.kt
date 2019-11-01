package graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ConnectedComponentsTest {

    @Test
    fun `test 8 nodes`() {
        val nodeCount = 8
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = nodeCount)
        graph.addEdge(start = 0, end = 1)
        graph.addEdge(start = 1, end = 2)
        graph.addEdge(start = 0, end = 2)

        graph.addEdge(start = 3, end = 4)

        graph.addEdge(start = 5, end = 6)
        graph.addEdge(start = 6, end = 7)
        graph.addEdge(start = 5, end = 7)

        val connectedComponents = ConnectedComponents(nodeCount)
        connectedComponents.findConnectedComponents(graph)

        assertEquals(3, connectedComponents.componentCount)

        val components = connectedComponents.connectedComponents
        for (index in 0 until nodeCount)
            println("$index: ${components[index]}")
    }

}