package sort.quick_sort

import org.junit.jupiter.api.Test
import sort.randomIntSortTest
import sort.sortReverseSortedInput
import sort.sortSortedInput
import sort.stringSortTest

internal class QuickSortTest {
    @Test
    fun `random int sort test`() = randomIntSortTest(::quickSortProxy)

    @Test
    fun `string sort test`() = stringSortTest(::quickSortProxy)

    @Test
    fun `sort sorted input`() = sortSortedInput(::quickSortProxy)

    @Test
    fun `sort reverse sorted input`() = sortReverseSortedInput(::quickSortProxy)
}