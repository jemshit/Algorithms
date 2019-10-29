package insertion_sort

// In-Place; Stable
// Inserts each item to left sorted subarray, to correct location
// Best: O(n), Worst: O(n^2)
// Space: O(1)
fun <T : Comparable<T>> insertionSort(items: Array<T>) {
    if (items.isEmpty()) return

    var sortedLastIndex = 0
    for (index in 1 until items.size) {
        var currentItemIndex = index
        for (compareIndex in sortedLastIndex downTo 0) {
            if (items[currentItemIndex].compareTo(items[compareIndex]) < 0) {
                val temp = items[currentItemIndex]
                items[currentItemIndex] = items[compareIndex]
                items[compareIndex] = temp
                currentItemIndex -= 1
            } else {
                break
            }
        }

        sortedLastIndex += 1
    }
}