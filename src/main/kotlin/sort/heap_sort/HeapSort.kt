package sort.heap_sort

import kotlin.math.max

// O(n*logn) time, O(1) space
// In-place, Not stable
fun <T : Comparable<T>> heapSort(items: Array<T>) {
    if (items.isEmpty()) return

    // Build Max Heap
    val lastChildIndex = items.size - 1
    val lastParentIndex = (lastChildIndex - 1) / 2
    for (sinkIndex in max(0, lastParentIndex) downTo 0)
        sink(items, sinkIndex, items.size)

    // Poll max item, place it in the end. Then Consider heap.size-=1
    for (lastHeapIndex in (items.size - 1) downTo 1) {
        // swap 0 and lastHeapIndex
        val temp = items[lastHeapIndex]
        items[lastHeapIndex] = items[0]
        items[0] = temp

        // sink 0 until lastHeapIndex
        sink(items, 0, lastHeapIndex)
    }
}

private fun <T : Comparable<T>> sink(
    items: Array<T>,
    index: Int,
    untilIndex: Int
) {
    var sinkIndex = index
    while (sinkIndex < untilIndex) {
        // find biggest child index to swap with
        var biggestIndex = sinkIndex
        val childOneIndex = sinkIndex * 2 + 1
        val childTwoIndex = sinkIndex * 2 + 2
        if (childOneIndex < untilIndex && items[childOneIndex].compareTo(items[biggestIndex]) > 0)
            biggestIndex = childOneIndex
        if (childTwoIndex < untilIndex && items[childTwoIndex].compareTo(items[biggestIndex]) > 0)
            biggestIndex = childTwoIndex

        // swap
        if (biggestIndex == sinkIndex)
            break
        val temp = items[sinkIndex]
        items[sinkIndex] = items[biggestIndex]
        items[biggestIndex] = temp

        sinkIndex = biggestIndex
    }
}
