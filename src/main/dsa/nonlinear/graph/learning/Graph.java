package main.dsa.nonlinear.graph.learning;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

// Class to represent a graph using an adjacency list
class Graph {
	int vertices;
	LinkedList<Integer>[] adjList;

	@SuppressWarnings("unchecked")
	Graph(int vertices) {
		this.vertices = vertices;
		adjList = new LinkedList[vertices];
		for (int i = 0; i < vertices; ++i)
			adjList[i] = new LinkedList<>();
	}

	// Function to add an edge to the graph
	void addEdge(int u, int v) {
		adjList[u].add(v);
	}

	// Function to perform Breadth-First Search on a graph
	// represented using an adjacency list
	void bfs(int startNode) {
		// Create a queue for BFS
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[vertices];

		// Mark the current node as visited and enqueue it
		visited[startNode] = true;
		queue.add(startNode);

		// Iterate over the queue
		while (!queue.isEmpty()) {
			// Dequeue a vertex from queue and print it
			int currentNode = queue.poll();
			System.out.print(currentNode + " ");

			// Get all adjacent vertices of the dequeued
			// vertex currentNode If an adjacent has not
			// been visited, then mark it visited and
			// enqueue it
			for (int neighbor : adjList[currentNode]) {
				if (!visited[neighbor]) {
					visited[neighbor] = true;
					queue.add(neighbor);
				}
			}
		}
	}

	// A function used by DFS
	void dfsutil(int v, boolean visited[]) {
		// Mark the current node as visited and print it
		visited[v] = true;
		System.out.print(v + " ");

		// Recur for all the vertices adjacent to this
		// vertex
		for (int n : adjList[v]) {
			if (!visited[n])
				dfsutil(n, visited);
		}
	}

	// The function to do DFS traversal.
	// It uses recursive dfsutil()
	void dfs(int v) {
		// Mark all the vertices as
		// not visited (set as
		// false by default in java)
		boolean visited[] = new boolean[vertices];

		// Call the recursive helper
		// function to print DFS
		// traversal
		dfsutil(v, visited);
	}


	public static void main(String[] args) {
		// Number of vertices in the graph
		int vertices = 5;

		// Create a graph
		Graph graph = new Graph(vertices);

		// Add edges to the graph
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 4);

		// Perform BFS traversal starting from vertex 0
		System.out.print("Breadth First Traversal starting from vertex 0: ");
		graph.bfs(0);

		Graph g = new Graph(4);

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);

		System.out.print("Following is Depth First Traversal " + "(starting from vertex 2)");
		g.dfs(0);
	}
}

