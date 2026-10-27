package graph.connectivity

import graph.representation.ArrayAdjacencyListGraph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class BridgesTest {

    @Test
    fun testTreeCase() {
        // Every edge should be a bridge if the input a tree
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 12)
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
            (0 to 1),
            (0 to 2),
            (2 to 5),
            (3 to 7),
            (4 to 3),
            (4 to 10),
            (5 to 4),
            (5 to 6),
            (5 to 11),
            (7 to 8),
            (7 to 9)
        )

        val sortedBridges: List<Pair<Int, Int>> = findBridges(graph).sortedWith(compareBy({ it.first }, { it.second }))

        assertEquals(expected, sortedBridges)
    }

    @Test
    fun graphWithCyclesTest() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 12)
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
            (3 to 7),
            (4 to 10),
            (7 to 8),
            (7 to 9)
        )

        val sortedBridges: List<Pair<Int, Int>> = findBridges(graph).sortedWith(compareBy({ it.first }, { it.second }))

        assertEquals(expected, sortedBridges)
    }

    @Test
    fun testDisconnectedGraph() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 11)
        graph.addEdge(0, 1)
        graph.addEdge(2, 1)
        graph.addEdge(3, 4)
        graph.addEdge(5, 7)
        graph.addEdge(5, 6)
        graph.addEdge(6, 7)
        graph.addEdge(8, 7)
        graph.addEdge(8, 9)
        graph.addEdge(8, 10)
        val expected: List<Pair<Int, Int>> = listOf(
            (0 to 1),
            (1 to 2),
            (3 to 4),
            (7 to 8),
            (8 to 9),
            (8 to 10)
        )

        val sortedBridges: List<Pair<Int, Int>> = findBridges(graph).sortedWith(compareBy({ it.first }, { it.second }))

        assertEquals(expected, sortedBridges)
    }

    @Test
    fun test5() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 7)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(1, 2)
        graph.addEdge(1, 3)
        graph.addEdge(3, 5)
        graph.addEdge(1, 4)
        graph.addEdge(4, 5)
        graph.addEdge(1, 6)
        val expected: List<Pair<Int, Int>> = listOf(
            (1 to 6)
        )

        val sortedBridges: List<Pair<Int, Int>> = findBridges(graph).sortedWith(compareBy({ it.first }, { it.second }))

        assertEquals(expected, sortedBridges)
    }

    @Test
    fun test6() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 5)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(1, 2)
        graph.addEdge(0, 3)
        graph.addEdge(3, 4)
        val expected: List<Pair<Int, Int>> = listOf(
            (0 to 3),
            (3 to 4)
        )

        val sortedBridges: List<Pair<Int, Int>> = findBridges(graph).sortedWith(compareBy({ it.first }, { it.second }))

        assertEquals(expected, sortedBridges)
    }

    @Test
    fun test7() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 4)
        graph.addEdge(0, 1)
        graph.addEdge(1, 2)
        graph.addEdge(2, 3)
        val expected: List<Pair<Int, Int>> = listOf(
            (0 to 1),
            (1 to 2),
            (2 to 3)
        )

        val sortedBridges: List<Pair<Int, Int>> = findBridges(graph).sortedWith(compareBy({ it.first }, { it.second }))

        assertEquals(expected, sortedBridges)
    }

    @Test
    fun test8() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 5)
        graph.addEdge(0, 1)
        graph.addEdge(1, 2)
        graph.addEdge(2, 0)
        graph.addEdge(2, 3)
        graph.addEdge(3, 4)
        graph.addEdge(4, 2)

        val sortedBridges: List<Pair<Int, Int>> = findBridges(graph).sortedWith(compareBy({ it.first }, { it.second }))

        assertTrue(sortedBridges.isEmpty())
    }

    @Test
    fun test9() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 5)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(2, 1)
        graph.addEdge(0, 3)
        graph.addEdge(3, 4)
        val expected: List<Pair<Int, Int>> = listOf(
            (0 to 3),
            (3 to 4)
        )

        val sortedBridges: List<Pair<Int, Int>> = findBridges(graph).sortedWith(compareBy({ it.first }, { it.second }))

        assertEquals(expected, sortedBridges)
    }

    @Test
    fun test10() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 5)
        graph.addEdge(0, 1)
        graph.addEdge(0, 2)
        graph.addEdge(2, 1)
        graph.addEdge(3, 4)
        val expected: List<Pair<Int, Int>> = listOf(
            (3 to 4)
        )

        val sortedBridges: List<Pair<Int, Int>> = findBridges(graph).sortedWith(compareBy({ it.first }, { it.second }))

        assertEquals(expected, sortedBridges)
    }

    @Test
    fun test11() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 9)
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
        val expected: List<Pair<Int, Int>> = listOf(
            (2 to 3),
            (2 to 5),
            (3 to 4)
        )

        val sortedBridges: List<Pair<Int, Int>> = findBridges(graph).sortedWith(compareBy({ it.first }, { it.second }))

        assertEquals(expected, sortedBridges)
    }

    @Test
    fun test12() {
        val graph = ArrayAdjacencyListGraph(directed = false, vertexCount = 9)
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
        val expected: List<Pair<Int, Int>> = listOf(
            (2 to 3),
            (3 to 4)
        )

        val sortedBridges: List<Pair<Int, Int>> = findBridges(graph).sortedWith(compareBy({ it.first }, { it.second }))

        assertEquals(expected, sortedBridges)
    }

}