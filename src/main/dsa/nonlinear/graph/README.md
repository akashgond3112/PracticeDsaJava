# Graph Data Structure Cheat Sheet (Java)

## Definition
A graph G = (V, E) consists of:
- V: a set of vertices (nodes)
- E: a set of edges connecting pairs of vertices

## Types of Graphs

### Undirected Graph
- Edges have no direction
- If (u, v) is an edge, then (v, u) is also an edge

### Directed Graph (Digraph)
- Edges have direction
- If (u, v) is an edge, it doesn't mean (v, u) is also an edge

### Weighted Graph
- Each edge has a weight/cost associated with it

### Unweighted Graph
- All edges have the same weight/cost

## Graph Representations

### Adjacency Matrix
```java
// For a graph with n vertices
int[][] graph = new int[n][n];

// For an edge from vertex i to j
graph[i][j] = 1;  // or weight if weighted

// For undirected graph
graph[j][i] = 1;  // or weight if weighted
```

- Space complexity: O(V²)
- Check if edge exists: O(1)
- Find all neighbors: O(V)
- Add/remove edge: O(1)
- Add vertex: O(V²) (need to resize)

### Adjacency List
```java
// For a graph with n vertices
ArrayList<ArrayList<Integer>> graph = new ArrayList<>(n);
for (int i = 0; i < n; i++) {
    graph.add(new ArrayList<>());
}

// For an edge from vertex i to j
graph.get(i).add(j);

// For undirected graph
graph.get(j).add(i);

// For weighted graph
class Edge {
    int dest, weight;
    Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}

ArrayList<ArrayList<Edge>> weightedGraph = new ArrayList<>(n);
// Adding edge
weightedGraph.get(i).add(new Edge(j, weight));
```

- Space complexity: O(V + E)
- Check if edge exists: O(degree(V))
- Find all neighbors: O(degree(V))
- Add edge: O(1)
- Remove edge: O(degree(V))
- Add vertex: O(1)

## Graph Traversal Algorithms

### Breadth-First Search (BFS) - O(V + E)
```java
public static void bfs(ArrayList<ArrayList<Integer>> graph, int start) {
    boolean[] visited = new boolean[graph.size()];
    Queue<Integer> queue = new LinkedList<>();
    
    visited[start] = true;
    queue.add(start);
    
    while (!queue.isEmpty()) {
        int vertex = queue.poll();
        System.out.print(vertex + " ");  // Process vertex
        
        for (int neighbor : graph.get(vertex)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                queue.add(neighbor);
            }
        }
    }
}
```

Applications:
- Finding shortest path in unweighted graphs
- Connected components
- Level-order traversal
- Testing bipartiteness

### Depth-First Search (DFS) - O(V + E)
```java
public static void dfs(ArrayList<ArrayList<Integer>> graph, int start) {
    boolean[] visited = new boolean[graph.size()];
    dfsUtil(graph, start, visited);
}

private static void dfsUtil(ArrayList<ArrayList<Integer>> graph, int vertex, boolean[] visited) {
    visited[vertex] = true;
    System.out.print(vertex + " ");  // Process vertex
    
    for (int neighbor : graph.get(vertex)) {
        if (!visited[neighbor]) {
            dfsUtil(graph, neighbor, visited);
        }
    }
}
```

Applications:
- Topological sorting
- Cycle detection
- Path finding
- Connected components
- Strongly connected components

## Shortest Path Algorithms

### Dijkstra's Algorithm - O((V + E) log V)
```java
public static int[] dijkstra(ArrayList<ArrayList<Edge>> graph, int start) {
    int n = graph.size();
    int[] distances = new int[n];
    Arrays.fill(distances, Integer.MAX_VALUE);
    distances[start] = 0;
    
    PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
    pq.add(new Node(start, 0));
    
    while (!pq.isEmpty()) {
        Node current = pq.poll();
        int u = current.vertex;
        
        if (current.distance > distances[u]) continue;
        
        for (Edge e : graph.get(u)) {
            int v = e.dest;
            int weight = e.weight;
            
            if (distances[u] + weight < distances[v]) {
                distances[v] = distances[u] + weight;
                pq.add(new Node(v, distances[v]));
            }
        }
    }
    
    return distances;
}

static class Node {
    int vertex, distance;
    Node(int v, int d) { vertex = v; distance = d; }
}
```

### Bellman-Ford Algorithm - O(V * E)
```java
public static int[] bellmanFord(ArrayList<ArrayList<Edge>> graph, int start, int n) {
    int[] distances = new int[n];
    Arrays.fill(distances, Integer.MAX_VALUE);
    distances[start] = 0;
    
    // Relax all edges V-1 times
    for (int i = 0; i < n - 1; i++) {
        for (int u = 0; u < n; u++) {
            for (Edge e : graph.get(u)) {
                int v = e.dest;
                int weight = e.weight;
                
                if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                }
            }
        }
    }
    
    // Check for negative weight cycles
    for (int u = 0; u < n; u++) {
        for (Edge e : graph.get(u)) {
            int v = e.dest;
            int weight = e.weight;
            
            if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                System.out.println("Graph contains negative weight cycle");
                return null;
            }
        }
    }
    
    return distances;
}
```

### Floyd-Warshall Algorithm - O(V³)
```java
public static int[][] floydWarshall(int[][] graph) {
    int n = graph.length;
    int[][] dist = new int[n][n];
    
    // Initialize dist matrix
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            dist[i][j] = graph[i][j];
        }
    }
    
    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE 
                        && dist[i][k] + dist[k][j] < dist[i][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }
    
    return dist;
}
```

## Minimum Spanning Tree (MST) Algorithms

### Kruskal's Algorithm - O(E log E)
```java
public static List<Edge> kruskal(ArrayList<ArrayList<Edge>> graph, int n) {
    List<EdgeTuple> edges = new ArrayList<>();
    
    // Collect all edges
    for (int u = 0; u < n; u++) {
        for (Edge e : graph.get(u)) {
            if (u < e.dest) // To avoid duplicates in undirected graph
                edges.add(new EdgeTuple(u, e.dest, e.weight));
        }
    }
    
    // Sort edges by weight
    edges.sort(Comparator.comparingInt(e -> e.weight));
    
    // DisjointSet for Union-Find
    int[] parent = new int[n];
    for (int i = 0; i < n; i++) parent[i] = i;
    
    List<Edge> mst = new ArrayList<>();
    
    for (EdgeTuple edge : edges) {
        int u = edge.u;
        int v = edge.v;
        int weight = edge.weight;
        
        int rootU = find(parent, u);
        int rootV = find(parent, v);
        
        if (rootU != rootV) {
            union(parent, rootU, rootV);
            mst.add(new Edge(v, weight));
        }
    }
    
    return mst;
}

static int find(int[] parent, int x) {
    if (parent[x] != x)
        parent[x] = find(parent, parent[x]);
    return parent[x];
}

static void union(int[] parent, int x, int y) {
    parent[x] = y;
}

static class EdgeTuple {
    int u, v, weight;
    EdgeTuple(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.weight = w;
    }
}
```

### Prim's Algorithm - O((V + E) log V)
```java
public static List<Edge> prim(ArrayList<ArrayList<Edge>> graph, int start) {
    int n = graph.size();
    boolean[] visited = new boolean[n];
    
    PriorityQueue<PrimEdge> pq = new PriorityQueue<>(
        Comparator.comparingInt(e -> e.weight)
    );
    pq.add(new PrimEdge(start, -1, 0)); // (vertex, parent, weight)
    
    List<Edge> mst = new ArrayList<>();
    
    while (!pq.isEmpty()) {
        PrimEdge current = pq.poll();
        int u = current.vertex;
        
        if (visited[u]) continue;
        
        visited[u] = true;
        if (current.parent != -1) {
            mst.add(new Edge(u, current.weight));
        }
        
        for (Edge e : graph.get(u)) {
            int v = e.dest;
            if (!visited[v]) {
                pq.add(new PrimEdge(v, u, e.weight));
            }
        }
    }
    
    return mst;
}

static class PrimEdge {
    int vertex, parent, weight;
    PrimEdge(int v, int p, int w) {
        vertex = v; parent = p; weight = w;
    }
}
```

## Other Important Graph Algorithms

### Topological Sort - O(V + E)
```java
public static List<Integer> topologicalSort(ArrayList<ArrayList<Integer>> graph) {
    int n = graph.size();
    boolean[] visited = new boolean[n];
    Stack<Integer> stack = new Stack<>();
    
    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            topologicalSortUtil(graph, i, visited, stack);
        }
    }
    
    List<Integer> result = new ArrayList<>();
    while (!stack.isEmpty()) {
        result.add(stack.pop());
    }
    
    return result;
}

private static void topologicalSortUtil(ArrayList<ArrayList<Integer>> graph, 
                                       int vertex, boolean[] visited, Stack<Integer> stack) {
    visited[vertex] = true;
    
    for (int neighbor : graph.get(vertex)) {
        if (!visited[neighbor]) {
            topologicalSortUtil(graph, neighbor, visited, stack);
        }
    }
    
    stack.push(vertex);
}
```

### Cycle Detection
```java
public static boolean hasCycle(ArrayList<ArrayList<Integer>> graph) {
    int n = graph.size();
    boolean[] visited = new boolean[n];
    boolean[] recStack = new boolean[n];
    
    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            if (hasCycleUtil(graph, i, visited, recStack)) {
                return true;
            }
        }
    }
    
    return false;
}

private static boolean hasCycleUtil(ArrayList<ArrayList<Integer>> graph, 
                                  int vertex, boolean[] visited, boolean[] recStack) {
    visited[vertex] = true;
    recStack[vertex] = true;
    
    for (int neighbor : graph.get(vertex)) {
        if (!visited[neighbor]) {
            if (hasCycleUtil(graph, neighbor, visited, recStack)) {
                return true;
            }
        } else if (recStack[neighbor]) {
            return true;
        }
    }
    
    recStack[vertex] = false;
    return false;
}
```

### Bipartite Graph Check
```java
public static boolean isBipartite(ArrayList<ArrayList<Integer>> graph) {
    int n = graph.size();
    int[] color = new int[n]; // -1: not colored, 0: color A, 1: color B
    Arrays.fill(color, -1);
    
    for (int i = 0; i < n; i++) {
        if (color[i] == -1) {
            if (!isBipartiteUtil(graph, i, color)) {
                return false;
            }
        }
    }
    
    return true;
}

private static boolean isBipartiteUtil(ArrayList<ArrayList<Integer>> graph, int start, int[] color) {
    Queue<Integer> queue = new LinkedList<>();
    color[start] = 0;
    queue.add(start);
    
    while (!queue.isEmpty()) {
        int u = queue.poll();
        
        for (int v : graph.get(u)) {
            if (color[v] == -1) {
                color[v] = 1 - color[u];
                queue.add(v);
            } else if (color[v] == color[u]) {
                return false;
            }
        }
    }
    
    return true;
}
```

## Network Flow Algorithms

### Ford-Fulkerson (Edmonds-Karp Implementation) - O(V * E²)
```java
public static int edmondsKarp(int[][] graph, int source, int sink) {
    int n = graph.length;
    int maxFlow = 0;
    
    int[][] residual = new int[n][n];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            residual[i][j] = graph[i][j];
        }
    }
    
    int[] parent = new int[n];
    
    while (bfs(residual, source, sink, parent)) {
        int pathFlow = Integer.MAX_VALUE;
        
        // Find minimum capacity in the augmenting path
        for (int v = sink; v != source; v = parent[v]) {
            int u = parent[v];
            pathFlow = Math.min(pathFlow, residual[u][v]);
        }
        
        // Update residual capacities
        for (int v = sink; v != source; v = parent[v]) {
            int u = parent[v];
            residual[u][v] -= pathFlow;
            residual[v][u] += pathFlow;  // Add reverse edge
        }
        
        maxFlow += pathFlow;
    }
    
    return maxFlow;
}

private static boolean bfs(int[][] residual, int source, int sink, int[] parent) {
    int n = residual.length;
    boolean[] visited = new boolean[n];
    Arrays.fill(parent, -1);
    
    Queue<Integer> queue = new LinkedList<>();
    queue.add(source);
    visited[source] = true;
    parent[source] = -2;
    
    while (!queue.isEmpty()) {
        int u = queue.poll();
        
        for (int v = 0; v < n; v++) {
            if (!visited[v] && residual[u][v] > 0) {
                queue.add(v);
                parent[v] = u;
                visited[v] = true;
            }
        }
    }
    
    return visited[sink];
}
```

## Time and Space Complexity Summary

| Algorithm | Time Complexity | Space Complexity |
|-----------|----------------|-----------------|
| BFS | O(V + E) | O(V) |
| DFS | O(V + E) | O(V) |
| Dijkstra | O((V + E) log V) | O(V) |
| Bellman-Ford | O(V * E) | O(V) |
| Floyd-Warshall | O(V³) | O(V²) |
| Kruskal | O(E log E) | O(V) |
| Prim | O((V + E) log V) | O(V) |
| Topological Sort | O(V + E) | O(V) |
| Strongly Connected Components | O(V + E) | O(V) |
| Ford-Fulkerson | O(max_flow * E) | O(V²) |
| Edmonds-Karp | O(V * E²) | O(V²) |