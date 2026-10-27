package sorting.selection

import org.junit.jupiter.api.Test
import sorting.randomIntSortTest
import sorting.sortReverseSortedInput
import sorting.sortSortedInput
import sorting.stringSortTest

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