package selection_sort

import org.junit.jupiter.api.Test
import randomIntSortTest
import sortReverseSortedInput
import sortSortedInput
import stringSortTest

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