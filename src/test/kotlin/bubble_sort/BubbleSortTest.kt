package bubble_sort

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class BubbleSortTest {

    @Test
    fun `random int sort test`() {
        for (loopIndex in 1..500) {
            val intArray = Array<Int>(10) { Random.nextInt(-10000, 10000) }
            val intArrayCopy = intArray.clone()

            intArrayCopy.sort()
            bubbleSort(intArray)

            for (index in 0 until 10) {
                assertEquals(intArrayCopy[index], intArray[index])
            }
        }
    }

    @Test
    fun `string sort test`() {
        val strArray = arrayOf("david", "alex", "valerie", "arthur", "james", "monk", "submarine")
        val strArrayCopy = strArray.clone()

        strArrayCopy.sort()
        bubbleSort(strArray)

        for (index in strArray.indices) {
            assertEquals(strArrayCopy[index], strArray[index])
        }
    }

}