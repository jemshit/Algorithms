package graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class FindBridgesTest {

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
        val expected = listOf(
            GraphUsingAdjacencyList.Edge(0, 1),
            GraphUsingAdjacencyList.Edge(0, 2),
            GraphUsingAdjacencyList.Edge(2, 5),
            GraphUsingAdjacencyList.Edge(3, 7),
            GraphUsingAdjacencyList.Edge(4, 3),
            GraphUsingAdjacencyList.Edge(4, 10),
            GraphUsingAdjacencyList.Edge(5, 4),
            GraphUsingAdjacencyList.Edge(5, 6),
            GraphUsingAdjacencyList.Edge(5, 11),
            GraphUsingAdjacencyList.Edge(7, 8),
            GraphUsingAdjacencyList.Edge(7, 9)
        )

        val sortedBridges: List<GraphUsingAdjacencyList.Edge> = graph.findBridges().sorted()

        assertEquals(expected, sortedBridges)
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
        val expected = listOf(
            GraphUsingAdjacencyList.Edge(3, 7),
            GraphUsingAdjacencyList.Edge(4, 10),
            GraphUsingAdjacencyList.Edge(7, 8),
            GraphUsingAdjacencyList.Edge(7, 9)
        )

        val sortedBridges: List<GraphUsingAdjacencyList.Edge> = graph.findBridges().sorted()

        assertEquals(expected, sortedBridges)
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
        val expected: List<GraphUsingAdjacencyList.Edge> = listOf(
            GraphUsingAdjacencyList.Edge(0, 1),
            GraphUsingAdjacencyList.Edge(1, 2),
            GraphUsingAdjacencyList.Edge(3, 4),
            GraphUsingAdjacencyList.Edge(7, 8),
            GraphUsingAdjacencyList.Edge(8, 9),
            GraphUsingAdjacencyList.Edge(8, 10)
        )

        val sortedBridges: List<GraphUsingAdjacencyList.Edge> = graph.findBridges().sorted()

        assertEquals(expected, sortedBridges)
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
        val expected: List<GraphUsingAdjacencyList.Edge> = listOf(
            GraphUsingAdjacencyList.Edge(1, 6)
        )

        val sortedBridges: List<GraphUsingAdjacencyList.Edge> = graph.findBridges().sorted()

        assertEquals(expected, sortedBridges)
    }

    @Test
    fun test6() {
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 5)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(1, 2)
        graph.addEdge(0, 3)
        graph.addEdge(3, 4)
        val expected: List<GraphUsingAdjacencyList.Edge> = listOf(
            GraphUsingAdjacencyList.Edge(0, 3),
            GraphUsingAdjacencyList.Edge(3, 4)
        )

        val sortedBridges: List<GraphUsingAdjacencyList.Edge> = graph.findBridges().sorted()

        assertEquals(expected, sortedBridges)
    }

    @Test
    fun test7() {
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 4)
        graph.addEdge(0, 1)
        graph.addEdge(1, 2)
        graph.addEdge(2, 3)
        val expected: List<GraphUsingAdjacencyList.Edge> = listOf(
            GraphUsingAdjacencyList.Edge(0, 1),
            GraphUsingAdjacencyList.Edge(1, 2),
            GraphUsingAdjacencyList.Edge(2, 3)
        )

        val sortedBridges: List<GraphUsingAdjacencyList.Edge> = graph.findBridges().sorted()

        assertEquals(expected, sortedBridges)
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

        val sortedBridges: List<GraphUsingAdjacencyList.Edge> = graph.findBridges().sorted()

        assertTrue(sortedBridges.isEmpty())
    }

    @Test
    fun test9() {
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 5)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(2, 1)
        graph.addEdge(0, 3)
        graph.addEdge(3, 4)
        val expected: List<GraphUsingAdjacencyList.Edge> = listOf(
            GraphUsingAdjacencyList.Edge(0, 3),
            GraphUsingAdjacencyList.Edge(3, 4)
        )

        val sortedBridges: List<GraphUsingAdjacencyList.Edge> = graph.findBridges().sorted()

        assertEquals(expected, sortedBridges)
    }

    @Test
    fun test10() {
        val graph = GraphUsingAdjacencyList(directed = false, nodeCount = 5)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(2, 1)
        graph.addEdge(3, 4)
        val expected: List<GraphUsingAdjacencyList.Edge> = listOf(
            GraphUsingAdjacencyList.Edge(3, 4)
        )

        val sortedBridges: List<GraphUsingAdjacencyList.Edge> = graph.findBridges().sorted()

        assertEquals(expected, sortedBridges)
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
        val expected: List<GraphUsingAdjacencyList.Edge> = listOf(
            GraphUsingAdjacencyList.Edge(2, 3),
            GraphUsingAdjacencyList.Edge(2, 5),
            GraphUsingAdjacencyList.Edge(3, 4)
        )

        val sortedBridges: List<GraphUsingAdjacencyList.Edge> = graph.findBridges().sorted()

        assertEquals(expected, sortedBridges)
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
        val expected: List<GraphUsingAdjacencyList.Edge> = listOf(
            GraphUsingAdjacencyList.Edge(2, 3),
            GraphUsingAdjacencyList.Edge(3, 4)
        )

        val sortedBridges: List<GraphUsingAdjacencyList.Edge> = graph.findBridges().sorted()

        assertEquals(expected, sortedBridges)
    }

}