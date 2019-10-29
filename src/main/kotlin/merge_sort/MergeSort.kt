package merge_sort

fun <T : Comparable<T>> mergeSortProxy(items: Array<T>) {
    if (items.isEmpty()) return

    val sorted = mergeSort(items, 0, items.size - 1)
    for (index in sorted.indices) {
        items[index] = sorted[index]
    }
}

// O(logn) for divide, O(n) for merge, so it is always O(n*logn)
// Space is O(n)
// Not in-place, Stable
private fun <T : Comparable<T>> mergeSort(
    items: Array<T>,
    startIndex: Int,
    endIndex: Int
): Array<T> {
    // stop dividing
    if (startIndex == endIndex)
        return arrayOf<Comparable<T>>(items[startIndex]) as Array<T>

    // divide
    val size = endIndex - startIndex + 1
    val middleIndex = size / 2 + startIndex
    val leftSorted = mergeSort(items, startIndex, middleIndex - 1)
    val rightSorted = mergeSort(items, middleIndex, endIndex)

    // merge back
    val result = arrayOfNulls<Comparable<T>>(size)
    var resultIndex = 0
    var leftIndex = 0
    var rightIndex = 0
    while (leftIndex < leftSorted.size && rightIndex < rightSorted.size) {
        // put right first only if it is smaller. This will make algorithm stable
        if (rightSorted[rightIndex].compareTo(leftSorted[leftIndex]) < 0) {
            result[resultIndex] = rightSorted[rightIndex]
            rightIndex += 1
        } else {
            result[resultIndex] = leftSorted[leftIndex]
            leftIndex += 1
        }
        resultIndex += 1
    }
    // merge leftovers if left & right arrays sizes are different
    while (leftIndex < leftSorted.size) {
        result[resultIndex] = leftSorted[leftIndex]
        leftIndex += 1
        resultIndex += 1
    }
    while (rightIndex < rightSorted.size) {
        result[resultIndex] = rightSorted[rightIndex]
        rightIndex += 1
        resultIndex += 1
    }

    return result as Array<T>
}