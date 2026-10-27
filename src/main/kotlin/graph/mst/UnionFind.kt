package graph.mst

/**
 * Union-Find with path compression and union by size.
 * Operations take O(α(n)) amortized time, which is effectively constant time in practice.
 */
internal class UnionFind(elementCount: Int) {
    private val parent: IntArray
    private val componentSizes: IntArray

    init {
        require(elementCount >= 0) { "elementCount must not be negative" }
        // Initially, every element is its own parent and therefore its own component.
        parent = IntArray(elementCount) { element -> element }
        componentSizes = IntArray(elementCount) { 1 } // One element in each initial component.
    }

    fun find(element: Int): Int {
        // This is an O(1) bounds check, not a search through the parent array.
        require(element in parent.indices) { "Unknown element: $element" }

        var root = element
        while (root != parent[root]) root = parent[root]

        var current = element
        while (current != root) {
            val next = parent[current]
            parent[current] = root
            current = next
        }
        return root
    }

    /** Merges two components and returns false if they were already connected. */
    fun union(first: Int, second: Int): Boolean {
        var firstRoot = find(first)
        var secondRoot = find(second)
        if (firstRoot == secondRoot) return false

        if (componentSizes[firstRoot] < componentSizes[secondRoot]) {
            val temporary = firstRoot
            firstRoot = secondRoot
            secondRoot = temporary
        }

        // After the possible swap, firstRoot represents the larger component.
        parent[secondRoot] = firstRoot
        componentSizes[firstRoot] += componentSizes[secondRoot]
        return true
    }
}
