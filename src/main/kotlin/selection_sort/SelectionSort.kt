package selection_sort

// In-Place; Not Stable because compare&swap! changes position of items (can be made stable)
// Find min item, swap it with leftmost unsorted item
// O(n) for min search, O(n) for find-min for all items: O(n^2)
// Space: O(1)
fun <T : Comparable<T>> selectionSort(items: Array<T>) {
    if (items.isEmpty()) return

    var lastSortedIndex = -1
    for (item in items) {
        // Find Min
        var minIndex = lastSortedIndex + 1
        var minItem = items[minIndex]
        for (index in (minIndex + 1) until items.size) {
            if (items[index].compareTo(minItem) < 0) {
                minIndex = index
                minItem = items[minIndex]
            }
        }

        // Swap
        if (minIndex != (lastSortedIndex + 1)) {
            items[minIndex] = items[lastSortedIndex + 1]
            items[lastSortedIndex + 1] = minItem
        }

        lastSortedIndex += 1
    }
}