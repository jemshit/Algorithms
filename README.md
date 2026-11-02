# Kotlin Algorithms

A focused collection of algorithms implemented from scratch in Kotlin. Built for learning, interview preparation, and understanding how common algorithms work.

The implementations favor readable, textbook-style code, explicit control flow, and tests that demonstrate expected behavior.

## Graph algorithms

### Representations

- [Array adjacency list](src/main/kotlin/graph/representation/ArrayAdjacencyListGraph.kt) — adjacency lists for integer vertices.
- [Map adjacency list](src/main/kotlin/graph/representation/MapAdjacencyListGraph.kt) — adjacency lists for generic vertex identifiers.
- [Adjacency matrix](src/main/kotlin/graph/representation/AdjacencyMatrixGraph.kt) — matrix-backed graph for integer vertices.

### Traversal and paths

- [Breadth-first search](src/main/kotlin/graph/traversal/BreadthFirstSearch.kt) — iterative BFS.
- [Depth-first search](src/main/kotlin/graph/traversal/DepthFirstSearch.kt) — iterative and recursive DFS.
- [Graph paths](src/main/kotlin/graph/traversal/GraphPaths.kt) — BFS and DFS path reconstruction.

### Connectivity

- [Connected components](src/main/kotlin/graph/connectivity/ConnectedComponents.kt)
- [Cycle detection](src/main/kotlin/graph/connectivity/CycleDetection.kt) — directed and undirected graphs.
- [Bipartite checking](src/main/kotlin/graph/connectivity/Bipartite.kt)
- [Bridges](src/main/kotlin/graph/connectivity/Bridges.kt)
- [Articulation points](src/main/kotlin/graph/connectivity/ArticulationPoints.kt)
- [Strongly connected components](src/main/kotlin/graph/connectivity/Kosaraju.kt) — Kosaraju's algorithm.

### Directed acyclic graphs

- [Topological sorting](src/main/kotlin/graph/dag/TopologicalSort.kt) — DFS and Kahn's algorithm.
- [DAG shortest paths](src/main/kotlin/graph/dag/DagShortestPaths.kt)
- [DAG longest paths](src/main/kotlin/graph/dag/DagLongestPaths.kt)

### Shortest paths

- [Dijkstra's algorithm](src/main/kotlin/graph/shortestpath/Dijkstra.kt)
- [Bellman-Ford algorithm](src/main/kotlin/graph/shortestpath/BellmanFord.kt)

### Minimum spanning trees

- [Prim's algorithm](src/main/kotlin/graph/mst/Prim.kt)
- [Kruskal's algorithm](src/main/kotlin/graph/mst/Kruskal.kt)
- [Union-Find](src/main/kotlin/graph/mst/UnionFind.kt)

### Grids

- [Grid](src/main/kotlin/graph/grid/Grid.kt) — coordinates, movement directions, and neighbor calculation.
- [Grid paths](src/main/kotlin/graph/grid/GridPaths.kt) — BFS pathfinding over an implicit grid graph.

## Sorting and searching

### Sorting

- [Bubble sort](src/main/kotlin/sorting/bubble/BubbleSort.kt)
- [Selection sort](src/main/kotlin/sorting/selection/SelectionSort.kt)
- [Insertion sort](src/main/kotlin/sorting/insertion/InsertionSort.kt)
- [Merge sort](src/main/kotlin/sorting/merge/MergeSort.kt)
- [Quick sort](src/main/kotlin/sorting/quick/QuickSort.kt)
- [Heap sort](src/main/kotlin/sorting/heap/HeapSort.kt)
- [Heap sort using PriorityQueue](src/main/kotlin/sorting/heap/HeapSortUsingPriorityQueue.kt)

### Searching

- [Binary search](src/main/kotlin/searching/BinarySearch.kt) — iterative and recursive implementations.

## Run the tests

```bash
./gradlew test
```

Inspired by [William Fiset](https://www.youtube.com/watch?v=RBSGKlAvoiM) and [Donglai Fu](https://github.com/labuladong/fucking-algorithm).
