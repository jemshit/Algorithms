package graph.connectivity

import graph.representation.ArrayAdjacencyListGraph

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class CycleDetectionTest {

    @Test
    fun testUndirected1() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 5)
        graph.addEdge(1, 0)
        graph.addEdge(2, 0)
        graph.addEdge(0, 3)
        graph.addEdge(3, 4)

        val cycleExist = hasUndirectedCycle(graph)

        assertFalse(cycleExist)
    }

    @Test
    fun testUndirected2() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 3)
        graph.addEdge(1, 0)
        graph.addEdge(2, 1)
        graph.addEdge(2, 0)

        val cycleExist = hasUndirectedCycle(graph)

        assertTrue(cycleExist)
    }

    @Test
    fun testDirected1() {
        val graph = ArrayAdjacencyListGraph(directed = true, vertexCount = 3)
        graph.addEdge(1, 0)
        graph.addEdge(0, 2)
        graph.addEdge(2, 1)

        val cycleExist = hasDirectedCycle(graph)

        assertTrue(cycleExist)
    }

    @Test
    fun testDirected2() {
        val graph = ArrayAdjacencyListGraph(directed = true, vertexCount = 4)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(1, 3)
        graph.addEdge(2, 3)

        val cycleExist = hasDirectedCycle(graph)

        assertFalse(cycleExist)
    }

    @Test
    fun testDirected3() {
        val graph = ArrayAdjacencyListGraph(directed = true, vertexCount = 4)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(1, 2)
        graph.addEdge(2, 0)
        graph.addEdge(2, 3)
        val cycleExist = hasDirectedCycle(graph)

        assertTrue(cycleExist)
    }

}
