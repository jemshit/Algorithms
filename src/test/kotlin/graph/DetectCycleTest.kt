package graph

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class DetectCycleTest {

    @Test
    fun testUndirected1() {
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 5)
        graph.addEdge(1, 0)
        graph.addEdge(2, 0)
        graph.addEdge(0, 3)
        graph.addEdge(3, 4)

        val cycleExist = graph.isCycleExistUndirected()

        assertFalse(cycleExist)
    }

    @Test
    fun testUndirected2() {
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 5)
        graph.addEdge(1, 0)
        graph.addEdge(2, 1)
        graph.addEdge(2, 0)

        val cycleExist = graph.isCycleExistUndirected()

        assertTrue(cycleExist)
    }
}