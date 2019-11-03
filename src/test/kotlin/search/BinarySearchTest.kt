package search

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class BinarySearchTest {

    @Test
    fun `binarySearch first index`() {
        val input = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        val foundIndex = binarySearch(input, find = 1, leftIndex = 0, rightIndex = 9)

        assertEquals(0, foundIndex)
    }

    @Test
    fun `binarySearch last index`() {
        val input = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        val foundIndex = binarySearch(input, find = 10, leftIndex = 0, rightIndex = 9)

        assertEquals(9, foundIndex)
    }

    @Test
    fun `binarySearch middle index`() {
        val input = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

        val foundIndex = binarySearch(input, find = 6, leftIndex = 0, rightIndex = 10)

        assertEquals(5, foundIndex)
    }

    @Test
    fun `binarySearch not found`() {
        val input = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

        val foundIndex = binarySearch(input, find = 12, leftIndex = 0, rightIndex = 10)

        assertEquals(-1, foundIndex)
    }

    @Test
    fun `binarySearch not found 2`() {
        val input = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

        val foundIndex = binarySearch(input, find = -1, leftIndex = 0, rightIndex = 10)

        assertEquals(-1, foundIndex)
    }
}