import org.junit.jupiter.api.Assertions
import kotlin.random.Random

internal fun randomIntSortTest(sortFunction: (Array<Int>) -> Unit) {
    for (loopIndex in 1..500) {
        val intArray = Array<Int>(10) { Random.nextInt(-10000, 10000) }
        val intArrayCopy = intArray.clone()

        intArrayCopy.sort()
        sortFunction(intArray)

        for (index in 0 until 10) {
            Assertions.assertEquals(intArrayCopy[index], intArray[index])
        }
    }
}

internal fun stringSortTest(sortFunction: (Array<String>) -> Unit) {
    val strArray = arrayOf("david", "alex", "valerie", "arthur", "james", "monk", "submarine")
    val strArrayCopy = strArray.clone()

    strArrayCopy.sort()
    sortFunction(strArray)

    for (index in strArray.indices) {
        Assertions.assertEquals(strArrayCopy[index], strArray[index])
    }
}

internal fun sortSortedInput(sortFunction: (Array<Int>) -> Unit) {
    val intArray = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val intArrayCopy = intArray.clone()

    intArrayCopy.sort()
    sortFunction(intArray)

    for (index in intArray.indices) {
        Assertions.assertEquals(intArrayCopy[index], intArray[index])
    }
}

internal fun sortReverseSortedInput(sortFunction: (Array<Int>) -> Unit) {
    val intArray = arrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
    val intArrayCopy = intArray.clone()

    intArrayCopy.sort()
    sortFunction(intArray)

    for (index in intArray.indices) {
        Assertions.assertEquals(intArrayCopy[index], intArray[index])
    }
}