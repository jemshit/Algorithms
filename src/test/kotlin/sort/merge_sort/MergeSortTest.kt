package sort.merge_sort

import org.junit.jupiter.api.Test
import sort.randomIntSortTest
import sort.sortReverseSortedInput
import sort.sortSortedInput
import sort.stringSortTest

internal class MergeSortTest {

    @Test
    fun `random int sort test`() = randomIntSortTest(::mergeSortProxy)

    @Test
    fun `string sort test`() = stringSortTest(::mergeSortProxy)

    @Test
    fun `sort sorted input`() = sortSortedInput(::mergeSortProxy)

    @Test
    fun `sort reverse sorted input`() = sortReverseSortedInput(::mergeSortProxy)
}