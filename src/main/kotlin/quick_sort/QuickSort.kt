package quick_sort

fun <T : Comparable<T>> quickSortProxy(items: Array<T>) {
    if (items.isEmpty()) return

    quickSort(items, 0, items.size - 1)
}

// In-place, Not Stable by default
// Best/Average case O(n*logn), Worst case O(n^2)
private fun <T : Comparable<T>> quickSort(
    items: Array<T>,
    startIndex: Int,
    endIndex: Int
) {
    // stop
    if (endIndex <= startIndex) return

    // put pivot at correct location
    var pivotIndex = endIndex
    val pivotItem = items[pivotIndex]
    var leftIndex = startIndex - 1
    for (rightIndex in startIndex until pivotIndex) {
        if (items[rightIndex].compareTo(pivotItem) < 0) {
            leftIndex += 1
            // swap leftItem and rightItem
            val temp = items[rightIndex]
            items[rightIndex] = items[leftIndex]
            items[leftIndex] = temp
        }
    }
    // swap leftItem and pivotItem
    leftIndex += 1
    if (leftIndex != pivotIndex) {
        items[pivotIndex] = items[leftIndex]
        items[leftIndex] = pivotItem
        pivotIndex = leftIndex
    }

    // sort right and left side of pivot
    quickSort(items, startIndex, pivotIndex - 1)
    quickSort(items, pivotIndex + 1, endIndex)
}