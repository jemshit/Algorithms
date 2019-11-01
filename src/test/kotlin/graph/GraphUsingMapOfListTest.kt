package graph

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GraphUsingMapOfListTest {

    @Test
    fun `directed, add-get edge string edge`() {
        val graph = GraphUsingMapOfList<String, String?>(directed = true)
        assertEquals(0, graph.edgeCount())

        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("one"), GraphUsingMapOfList.Node("two"), 1)
        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("two"), GraphUsingMapOfList.Node("three"), 2)
        assertEquals(2, graph.edgeCount())

        val edgesOfOne = graph.getEdges("one")
        assertEquals(1, edgesOfOne.size)
        assertEquals("one", edgesOfOne[0].start.key)
        assertEquals("two", edgesOfOne[0].end.key)
        assertEquals(1, edgesOfOne[0].weight)

        val edgesOfTwo = graph.getEdges("two")
        assertEquals(1, edgesOfTwo.size)
        assertEquals("two", edgesOfTwo[0].start.key)
        assertEquals("three", edgesOfTwo[0].end.key)
        assertEquals(2, edgesOfTwo[0].weight)
    }

    @Test
    fun `directed, update string edge`() {
        val graph = GraphUsingMapOfList<String, String?>(directed = true)
        assertEquals(0, graph.edgeCount())

        // Insert
        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("one"), GraphUsingMapOfList.Node("two"), 1)
        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("two"), GraphUsingMapOfList.Node("three"), 2)
        assertEquals(2, graph.edgeCount())

        // Update
        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("one"), GraphUsingMapOfList.Node("two"), 2)
        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("two"), GraphUsingMapOfList.Node("three"), 3)
        assertEquals(2, graph.edgeCount())
        val edgesOfOneUpdated = graph.getEdges("one")
        assertEquals(1, edgesOfOneUpdated.size)
        assertEquals("one", edgesOfOneUpdated[0].start.key)
        assertEquals("two", edgesOfOneUpdated[0].end.key)
        assertEquals(2, edgesOfOneUpdated[0].weight)
        val edgesOfTwoUpdated = graph.getEdges("two")
        assertEquals(1, edgesOfTwoUpdated.size)
        assertEquals("two", edgesOfTwoUpdated[0].start.key)
        assertEquals("three", edgesOfTwoUpdated[0].end.key)
        assertEquals(3, edgesOfTwoUpdated[0].weight)
    }

    @Test
    fun `directed, remove string edge`() {
        val graph = GraphUsingMapOfList<String, String?>(directed = true)
        assertEquals(0, graph.edgeCount())

        // Insert
        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("one"), GraphUsingMapOfList.Node("two"), 1)
        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("two"), GraphUsingMapOfList.Node("three"), 2)
        assertEquals(2, graph.edgeCount())

        // Update
        assertTrue(graph.removeEdge("one", "two"))
        assertEquals(1, graph.edgeCount())
        assertEquals(0, graph.getEdges("one").size)

        assertFalse(graph.removeEdge("one", "three"))

        assertTrue(graph.removeEdge("two", "three"))
        assertEquals(0, graph.edgeCount())
        assertEquals(0, graph.getEdges("two").size)
    }

    @Test
    fun `directed, get all edges`() {
        val graph = GraphUsingMapOfList<String, String?>(directed = true)
        assertEquals(0, graph.edgeCount())

        // Insert
        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("one"), GraphUsingMapOfList.Node("two"), 1)
        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("two"), GraphUsingMapOfList.Node("three"), 2)
        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("one"), GraphUsingMapOfList.Node("three"), 3)
        assertEquals(3, graph.edgeCount())

        // Get all edges
        val allEdges = graph.getAllEdges()
        assertEquals(3, allEdges.size)
    }

    @Test
    fun `undirected, add-get edge string edge`() {
        val graph = GraphUsingMapOfList<String, String?>(directed = false)
        assertEquals(0, graph.edgeCount())

        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("one"), GraphUsingMapOfList.Node("two"), 1)
        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("two"), GraphUsingMapOfList.Node("three"), 2)
        assertEquals(4, graph.edgeCount())

        val edgesOfOne = graph.getEdges("one")
        assertEquals(1, edgesOfOne.size)
        assertEquals("one", edgesOfOne[0].start.key)
        assertEquals("two", edgesOfOne[0].end.key)
        assertEquals(1, edgesOfOne[0].weight)

        val edgesOfTwo = graph.getEdges("two")
        assertEquals(2, edgesOfTwo.size)

        val edgesOfThree = graph.getEdges("three")
        assertEquals(1, edgesOfThree.size)
        assertEquals("three", edgesOfThree[0].start.key)
        assertEquals("two", edgesOfThree[0].end.key)
        assertEquals(2, edgesOfThree[0].weight)
    }

    @Test
    fun `undirected, update string edge`() {
        val graph = GraphUsingMapOfList<String, String?>(directed = false)
        assertEquals(0, graph.edgeCount())

        // Insert
        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("one"), GraphUsingMapOfList.Node("two"), 1)
        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("two"), GraphUsingMapOfList.Node("three"), 2)
        assertEquals(4, graph.edgeCount())

        // Update
        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("one"), GraphUsingMapOfList.Node("two"), 2)
        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("two"), GraphUsingMapOfList.Node("three"), 3)
        assertEquals(4, graph.edgeCount())
        val edgesOfOneUpdated = graph.getEdges("one")
        assertEquals(1, edgesOfOneUpdated.size)
        assertEquals("one", edgesOfOneUpdated[0].start.key)
        assertEquals("two", edgesOfOneUpdated[0].end.key)
        assertEquals(2, edgesOfOneUpdated[0].weight)
        val edgesOfTwoUpdated = graph.getEdges("two")
        assertEquals(2, edgesOfTwoUpdated.size)
        val edgesOfThreeUpdated = graph.getEdges("three")
        assertEquals(1, edgesOfThreeUpdated.size)
        assertEquals("three", edgesOfThreeUpdated[0].start.key)
        assertEquals("two", edgesOfThreeUpdated[0].end.key)
        assertEquals(3, edgesOfThreeUpdated[0].weight)
    }

    @Test
    fun `undirected, remove string edge`() {
        val graph = GraphUsingMapOfList<String, String?>(directed = false)
        assertEquals(0, graph.edgeCount())

        // Insert
        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("one"), GraphUsingMapOfList.Node("two"), 1)
        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("two"), GraphUsingMapOfList.Node("three"), 2)
        assertEquals(4, graph.edgeCount())

        // Update
        assertTrue(graph.removeEdge("one", "two"))
        assertEquals(2, graph.edgeCount())
        assertEquals(0, graph.getEdges("one").size)
        assertEquals(1, graph.getEdges("two").size)
        assertEquals(1, graph.getEdges("three").size)

        assertFalse(graph.removeEdge("one", "three"))

        assertTrue(graph.removeEdge("two", "three"))
        assertEquals(0, graph.edgeCount())
        assertEquals(0, graph.getEdges("one").size)
        assertEquals(0, graph.getEdges("two").size)
        assertEquals(0, graph.getEdges("three").size)
    }

    @Test
    fun `undirected, get all edges`() {
        val graph = GraphUsingMapOfList<String, String?>(directed = false)
        assertEquals(0, graph.edgeCount())

        // Insert
        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("one"), GraphUsingMapOfList.Node("two"), 1)
        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("two"), GraphUsingMapOfList.Node("three"), 2)
        graph.addOrUpdateEdge(GraphUsingMapOfList.Node("one"), GraphUsingMapOfList.Node("three"), 3)
        assertEquals(6, graph.edgeCount())

        // Get all edges
        val allEdges = graph.getAllEdges()
        assertEquals(6, allEdges.size)
    }

}