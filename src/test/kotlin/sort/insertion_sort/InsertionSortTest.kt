package sort.insertion_sort

import org.junit.jupiter.api.Test
import sort.randomIntSortTest
import sort.sortReverseSortedInput
import sort.sortSortedInput
import sort.stringSortTest

internal class InsertionSortTest {

    @Test
    fun `random int sort test`() = randomIntSortTest(::insertionSort)

    @Test
    fun `string sort test`() = stringSortTest(::insertionSort)

    @Test
    fun `sort sorted input`() = sortSortedInput(::insertionSort)

    @Test
    fun `sort reverse sorted input`() = sortReverseSortedInput(::insertionSort)

}