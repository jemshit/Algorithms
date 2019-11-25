package graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class FindArticulationPointsTest {

    @Test
    fun testTreeCase() {
        // Every edge should be a bridge if the input a tree
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 12)
        graph.addEdge(1, 0)
        graph.addEdge(0, 2)
        graph.addEdge(2, 5)
        graph.addEdge(5, 6)
        graph.addEdge(5, 11)
        graph.addEdge(5, 4)
        graph.addEdge(4, 10)
        graph.addEdge(4, 3)
        graph.addEdge(3, 7)
        graph.addEdge(7, 8)
        graph.addEdge(7, 9)
        val expected = listOf(0, 2, 3, 4, 5, 7)

        val artPoints = graph.findArticulationPoints().sorted()

        assertEquals(expected, artPoints)
    }

    @Test
    fun graphWithCyclesTest() {
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 12)
        graph.addEdge(1, 0)
        graph.addEdge(0, 2)
        graph.addEdge(3, 1)
        graph.addEdge(2, 5)
        graph.addEdge(5, 6)
        graph.addEdge(5, 11)
        graph.addEdge(5, 4)
        graph.addEdge(4, 10)
        graph.addEdge(4, 3)
        graph.addEdge(3, 7)
        graph.addEdge(7, 8)
        graph.addEdge(7, 9)
        graph.addEdge(11, 6)
        val expected = listOf(3, 4, 5, 7)

        val artPoints = graph.findArticulationPoints().sorted()

        assertEquals(expected, artPoints)
    }

    @Test
    fun testDisconnectedGraph() {
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 11)
        graph.addEdge(0, 1)
        graph.addEdge(2, 1)
        graph.addEdge(3, 4)
        graph.addEdge(5, 7)
        graph.addEdge(5, 6)
        graph.addEdge(6, 7)
        graph.addEdge(8, 7)
        graph.addEdge(8, 9)
        graph.addEdge(8, 10)
        val expected = listOf(1, 7, 8)

        val artPoints = graph.findArticulationPoints().sorted()

        assertEquals(expected, artPoints)
    }

    @Test
    fun test5() {
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 7)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(1, 2)
        graph.addEdge(1, 3)
        graph.addEdge(3, 5)
        graph.addEdge(1, 4)
        graph.addEdge(4, 5)
        graph.addEdge(1, 6)
        val expected = listOf(1)

        val artPoints = graph.findArticulationPoints().sorted()

        assertEquals(expected, artPoints)
    }

    @Test
    fun test6() {
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 5)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(1, 2)
        graph.addEdge(0, 3)
        graph.addEdge(3, 4)
        val expected = listOf(0, 3)

        val artPoints = graph.findArticulationPoints().sorted()

        assertEquals(expected, artPoints)
    }

    @Test
    fun test7() {
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 4)
        graph.addEdge(0, 1)
        graph.addEdge(1, 2)
        graph.addEdge(2, 3)
        val expected = listOf(1, 2)

        val artPoints = graph.findArticulationPoints().sorted()

        assertEquals(expected, artPoints)
    }

    @Test
    fun test8() {
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 5)
        graph.addEdge(0, 1)
        graph.addEdge(1, 2)
        graph.addEdge(2, 0)
        graph.addEdge(2, 3)
        graph.addEdge(3, 4)
        graph.addEdge(4, 2)
        val expected = listOf(2)

        val artPoints = graph.findArticulationPoints().sorted()

        assertEquals(expected, artPoints)
    }

    @Test
    fun test10() {
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 5)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(2, 1)
        graph.addEdge(3, 4)
        val expected = emptyList<Int>()

        val artPoints = graph.findArticulationPoints().sorted()

        assertEquals(expected, artPoints)
    }

    @Test
    fun test11() {
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 9)
        graph.addEdge(0, 1)
        graph.addEdge(1, 2)
        graph.addEdge(2, 3)
        graph.addEdge(2, 5)
        graph.addEdge(2, 0)
        graph.addEdge(3, 4)
        graph.addEdge(5, 6)
        graph.addEdge(6, 7)
        graph.addEdge(7, 8)
        graph.addEdge(8, 5)
        val expected = listOf(2, 3, 5)

        val artPoints = graph.findArticulationPoints().sorted()

        assertEquals(expected, artPoints)
    }

    @Test
    fun test12() {
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 9)
        graph.addEdge(0, 1)
        graph.addEdge(1, 2)
        graph.addEdge(2, 3)
        graph.addEdge(2, 5)
        graph.addEdge(2, 0)
        graph.addEdge(3, 4)
        graph.addEdge(5, 6)
        graph.addEdge(6, 7)
        graph.addEdge(7, 8)
        graph.addEdge(8, 5)
        graph.addEdge(8, 2)
        val expected = listOf(2, 3)

        val artPoints = graph.findArticulationPoints().sorted()

        assertEquals(expected, artPoints)
    }

}