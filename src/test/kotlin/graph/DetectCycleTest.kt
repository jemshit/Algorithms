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
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 3)
        graph.addEdge(1, 0)
        graph.addEdge(2, 1)
        graph.addEdge(2, 0)

        val cycleExist = graph.isCycleExistUndirected()

        assertTrue(cycleExist)
    }

    @Test
    fun testDirected1() {
        val graph = GraphUsingAdjacencyList(directed = true, nodeCount = 3)
        graph.addEdge(1, 0)
        graph.addEdge(0, 2)
        graph.addEdge(2, 1)

        val cycleExist = graph.isCycleExistDirected()

        assertTrue(cycleExist)
    }

    @Test
    fun testDirected2() {
        val graph = GraphUsingAdjacencyList(directed = true, nodeCount = 4)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(1, 3)
        graph.addEdge(2, 3)

        val cycleExist = graph.isCycleExistDirected()

        assertFalse(cycleExist)
    }

    @Test
    fun testDirected3() {
        val graph = GraphUsingAdjacencyList(directed = true, nodeCount = 4)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(1, 2)
        graph.addEdge(2, 0)
        graph.addEdge(2, 3)
        graph.addEdge(3, 3)
        val cycleExist = graph.isCycleExistDirected()

        assertTrue(cycleExist)
    }

}