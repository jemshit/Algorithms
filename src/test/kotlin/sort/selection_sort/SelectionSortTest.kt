package sort.selection_sort

import org.junit.jupiter.api.Test
import sort.randomIntSortTest
import sort.sortReverseSortedInput
import sort.sortSortedInput
import sort.stringSortTest

internal class SelectionSortTest {

    @Test
    fun `random int sort test`() = randomIntSortTest(::selectionSort)

    @Test
    fun `string sort test`() = stringSortTest(::selectionSort)

    @Test
    fun `sort sorted input`() = sortSortedInput(::selectionSort)

    @Test
    fun `sort reverse sorted input`() = sortReverseSortedInput(::selectionSort)


}