package sort.bubble_sort

import org.junit.jupiter.api.Test
import sort.randomIntSortTest
import sort.sortReverseSortedInput
import sort.sortSortedInput
import sort.stringSortTest

internal class BubbleSortTest {

    @Test
    fun `random int sort test`() = randomIntSortTest(::bubbleSort)

    @Test
    fun `string sort test`() = stringSortTest(::bubbleSort)

    @Test
    fun `sort sorted input`() = sortSortedInput(::bubbleSort)

    @Test
    fun `sort reverse sorted input`() = sortReverseSortedInput(::bubbleSort)

}