package main.dsa.nonlinear.graph.learning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Bipartite Graph
Difficulty: MediumAccuracy: 31.25%Submissions: 169K+Points: 4
Given an adjacency list of a graph adj of V no. of vertices having 0 based index. Check whether the graph is bipartite or not.

Know more about Bipartite Graph: - https://www.geeksforgeeks.org/what-is-bipartite-graph/

Example 1:

Input:

Output: 1
Explanation: The given graph can be colored
in two colors so, it is a bipartite graph.
Example 2:

Input:

Output: 0
Explanation: The given graph cannot be colored
in two colors such that color of adjacent
vertices differs.


Your Task:
You don't need to read or print anything. Your task is to complete the function isBipartite() which takes V denoting no. of vertices and adj denoting adjacency list of the graph and returns a boolean value true if the graph is bipartite otherwise returns false.


Expected Time Complexity: O(V + E)
Expected Space Complexity: O(V)

Constraints:
1 ≤ V, E ≤ 105
*/
public class BipartiteGraph {

	public boolean isBipartiteUsingBfs(int v, List<ArrayList<Integer>> adj) {
		int[] visited = new int[v];
		Arrays.fill(visited, -1);

		for (int i = 0; i < v; i++) {
			if (visited[i] == -1 && !checkUsingBFS(i, adj, visited)) {
				return false;
			}

		}
		return true;

	}

	public boolean isBipartiteUsingDfs(int v, List<ArrayList<Integer>> adj) {
		int[] visited = new int[v];
		Arrays.fill(visited, -1);

		for (int i = 0; i < v; i++) {
			if (visited[i] == -1 && checkUsingDFS(i, 0, adj, visited)) {
				return false;
			}

		}
		return true;

	}

	private boolean checkUsingDFS(int node, int col, List<ArrayList<Integer>> adj, int[] color) {

		color[node] = col;

		for (int it : adj.get(node)) {
			if (color[it] == -1 && checkUsingDFS(it, 1 - col, adj, color)) {
				return true;
			} else if (color[it] == col)
				return true;
		}
		return false;
	}

	private boolean checkUsingBFS(int i, List<ArrayList<Integer>> adj, int[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(i);
		visited[i] = 0;
		while (!queue.isEmpty()) {
			int cur = queue.peek();
			queue.remove();
			for (int neighbor : adj.get(cur)) {
				if (visited[neighbor] == -1) {
					visited[neighbor] = 1 - visited[cur];
					queue.add(neighbor);
				} else if (visited[neighbor] == visited[cur]) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {

		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			adj.add(new ArrayList<>());
		}
		adj.get(0).add(2);
		adj.get(2).add(0);
		adj.get(0).add(3);
		adj.get(3).add(0);
		adj.get(1).add(3);
		adj.get(3).add(1);
		adj.get(2).add(3);
		adj.get(3).add(2);

		BipartiteGraph obj = new BipartiteGraph();
		boolean ans = obj.isBipartiteUsingBfs(4, adj);
		if (ans)
			System.out.println("1");
		else
			System.out.println("0");

		boolean ansDfs = obj.isBipartiteUsingDfs(4, adj);
		if (ansDfs)
			System.out.println("1");
		else
			System.out.println("0");
	}
}
