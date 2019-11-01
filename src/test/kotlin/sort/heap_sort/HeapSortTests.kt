package sort.heap_sort

import org.junit.jupiter.api.Test
import sort.randomIntSortTest
import sort.sortReverseSortedInput
import sort.sortSortedInput
import sort.stringSortTest

internal class HeapSortUsingPriorityQueueTest {

    @Test
    fun `random int sort test`() = randomIntSortTest(::heapSortPriorityQueue)

    @Test
    fun `string sort test`() = stringSortTest(::heapSortPriorityQueue)

    @Test
    fun `sort sorted input`() = sortSortedInput(::heapSortPriorityQueue)

    @Test
    fun `sort reverse sorted input`() = sortReverseSortedInput(::heapSortPriorityQueue)

}

internal class HeapSortTest {

    @Test
    fun `random int sort test`() = randomIntSortTest(::heapSort)

    @Test
    fun `string sort test`() = stringSortTest(::heapSort)

    @Test
    fun `sort sorted input`() = sortSortedInput(::heapSort)

    @Test
    fun `sort reverse sorted input`() = sortReverseSortedInput(::heapSort)
}