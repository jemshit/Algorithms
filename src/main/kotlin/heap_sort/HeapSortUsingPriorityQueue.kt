package heap_sort

import java.util.*

// O(n*logn) time, O(n) space
// In-place, Not stable
fun <T : Comparable<T>> heapSortPriorityQueue(items: Array<T>) {
    if (items.isEmpty()) return

    val heap = PriorityQueue<T>(items.toList()) // Min Heap
    for (index in items.indices) {
        items[index] = heap.poll()
    }
}
