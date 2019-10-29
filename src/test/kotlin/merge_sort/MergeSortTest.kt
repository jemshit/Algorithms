package merge_sort

import org.junit.jupiter.api.Test
import randomIntSortTest
import sortReverseSortedInput
import sortSortedInput
import stringSortTest

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