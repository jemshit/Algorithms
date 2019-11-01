package sort.bubble_sort

// In-Place; Stable
// Shifts biggest item to rightmost index in every iteration
// Best: O(n), Worst: O(n^2)
// Space: O(1)
fun <T : Comparable<T>> bubbleSort(items: Array<T>) {
    if (items.isEmpty()) return

    var swappedItem = true
    var lastSortedIndex = items.size // don't swap already sorted item
    while (swappedItem) {
        swappedItem = false

        for (index in 0 until lastSortedIndex - 1) {
            if (items[index].compareTo(items[index + 1]) > 0) {
                val temp = items[index]
                items[index] = items[index + 1]
                items[index + 1] = temp

                swappedItem = true
            }
        }

        lastSortedIndex -= 1
    }
}