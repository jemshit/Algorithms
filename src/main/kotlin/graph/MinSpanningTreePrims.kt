package graph

import java.util.*

class MSTEdge(val start: Int, val end: Int, var weight: Int? = null) : Comparable<MSTEdge> {
    override fun compareTo(other: MSTEdge): Int {
        return (weight ?: 0).compareTo(other.weight ?: 0)
    }

    override fun toString(): String {
        return "($start-$end)"
    }
}

fun GraphUsingAdjacencyList.Edge.toMSTEdge(): MSTEdge {
    return MSTEdge(start, end, weight)
}

fun GraphUsingAdjacencyList.primsMinimumSpanningTree(): Pair<Array<MSTEdge>, Int> {
    val mst = arrayOfNulls<MSTEdge>(nodeCount - 1)
    val priorityQueue = PriorityQueue<MSTEdge>()
    val visitedNode: Array<Boolean> = Array<Boolean>(nodeCount) { false }
    var edgeCount = 0
    var cost = 0

    // append edges of node 0
    visitedNode[0] = true
    for (edge in getEdges(0)) {
        if (!visitedNode[edge.end])
            priorityQueue.add(edge.toMSTEdge())
    }

    while (priorityQueue.isNotEmpty() && edgeCount != nodeCount - 1) {
        val edge = priorityQueue.poll()
        val endNode = edge.end

        // skip stale edge
        if (visitedNode[endNode])
            continue

        mst[edgeCount++] = edge
        cost += edge.weight ?: 0

        // append edges of this node
        visitedNode[endNode] = true
        for (edge in getEdges(endNode)) {
            if (!visitedNode[edge.end]) // skip already visited edge
                priorityQueue.add(edge.toMSTEdge())
        }
    }

    return Pair(mst as Array<MSTEdge>, cost)
}