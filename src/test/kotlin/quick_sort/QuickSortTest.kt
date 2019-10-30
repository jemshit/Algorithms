package quick_sort

import org.junit.jupiter.api.Test
import randomIntSortTest
import sortReverseSortedInput
import sortSortedInput
import stringSortTest

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