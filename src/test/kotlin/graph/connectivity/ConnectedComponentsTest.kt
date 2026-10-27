package graph.connectivity

import graph.representation.ArrayAdjacencyListGraph
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ConnectedComponentsTest {

    @Test
    fun `find components including isolated vertices`() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 8)
        graph.addEdge(0, 1)
        graph.addEdge(1, 2)
        graph.addEdge(3, 4)
        graph.addEdge(5, 6)
        graph.addEdge(6, 7)

        val result = findConnectedComponents(graph)

        assertEquals(3, result.count)
        assertEquals(result.componentOf[0], result.componentOf[2])
        assertEquals(result.componentOf[3], result.componentOf[4])
        assertEquals(result.componentOf[5], result.componentOf[7])
    }
}
