package search

fun <T : Comparable<T>> binarySearch(
    sortedArray: Array<T>,
    find: T,
    leftIndex: Int,
    rightIndex: Int
): Int {
    val size = rightIndex - leftIndex + 1
    val middleIndex = size / 2 + leftIndex
    val middleItem = sortedArray[middleIndex]

    if (middleItem == find)
        return middleIndex
    else if (find < middleItem && middleIndex > 0)
        return binarySearch(sortedArray, find, leftIndex = 0, rightIndex = middleIndex - 1)
    else if (find > middleItem && middleIndex < (sortedArray.size - 1))
        return binarySearch(sortedArray, find, leftIndex = middleIndex + 1, rightIndex = sortedArray.size - 1)
    else
        return -1
}