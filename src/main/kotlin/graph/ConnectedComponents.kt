package graph

class ConnectedComponents {

    private val nodeCount: Int
    private val visited: Array<Boolean>
    val connectedComponents: Array<Int>
    var componentCount: Int = 0
        private set

    constructor(nodeCount: Int) {
        if (nodeCount <= 0) throw IllegalArgumentException()

        this.nodeCount = nodeCount
        this.connectedComponents = Array<Int>(nodeCount) { 0 }
        this.visited = Array<Boolean>(nodeCount) { false }
    }

    fun findConnectedComponents(graph: GraphUsingAdjacencyList) {
        // For all nodes, if not visited, do dfs/bfs and assign them to component
        for (node in 0 until nodeCount) {
            if (!visited[node]) {
                dfsRecursive(node, graph)
                componentCount += 1
            }
        }
    }

    private fun dfsRecursive(node: Int, graph: GraphUsingAdjacencyList) {
        visited[node] = true
        connectedComponents[node] = componentCount

        val edges = graph.getEdges(node)
        for (edge in edges) {
            if (!visited[edge.end])
                dfsRecursive(edge.end, graph)
        }
    }
}