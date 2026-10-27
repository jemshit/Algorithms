package sorting.insertion

import org.junit.jupiter.api.Test
import sorting.randomIntSortTest
import sorting.sortReverseSortedInput
import sorting.sortSortedInput
import sorting.stringSortTest

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