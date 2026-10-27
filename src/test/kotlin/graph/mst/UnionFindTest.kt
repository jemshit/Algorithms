package graph.mst

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class UnionFindTest {

    @Test
    fun `union reports whether two components were merged`() {
        val unionFind = UnionFind(5)

        assertTrue(unionFind.union(0, 1))
        assertTrue(unionFind.union(1, 2))
        assertFalse(unionFind.union(0, 2))
        assertTrue(unionFind.find(0) == unionFind.find(2))
        assertFalse(unionFind.find(0) == unionFind.find(3))
    }

    @Test
    fun `invalid size and elements are rejected`() {
        assertThrows(IllegalArgumentException::class.java) { UnionFind(-1) }

        val unionFind = UnionFind(2)
        assertThrows(IllegalArgumentException::class.java) { unionFind.find(2) }
        assertThrows(IllegalArgumentException::class.java) { unionFind.union(-1, 0) }
    }
}
