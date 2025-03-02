package main.dsa.nonlinear.graph.topologicalsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Directed Graph Cycle
Difficulty: MediumAccuracy: 27.88%Submissions: 381K+Points: 4
Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges, check whether it contains any cycle or not.


Example 1:

Input:
0------->1
		|
		|
		V
3<------2

Output: 1
Explanation: 3 -> 3 is a cycle

Example 2:

Input:
0------->1
		|
		|
		V
		2

Output: 0
Explanation: no cycle in the graph

Your task:
You dont need to read input or print anything. Your task is to complete the function isCyclic() which takes the integer V denoting the number of vertices and adjacency list adj as input parameters and returns a boolean value denoting if the given directed graph contains a cycle or not.
In the adjacency list adj, element adj[i][j] represents an edge from i to j.


Expected Time Complexity: O(V + E)
Expected Auxiliary Space: O(V)


Constraints:
1 ≤ V, E ≤ 105
*/
public class DetectACycleInADirectedGraph {

	public boolean isCyclic(int v, List<ArrayList<Integer>> adj) {

		boolean[] visited = new boolean[v];
		boolean[] visitedPath = new boolean[v];
		Arrays.fill(visited, false);
		Arrays.fill(visitedPath, false);

		for (int i = 0; i < v; i++) {
			if (!visited[i] && checkUsingDFS(i, visited, visitedPath, adj)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkUsingDFS(int i, boolean[] visited, boolean[] visitedPath, List<ArrayList<Integer>> adj) {

		visitedPath[i] = true;
		visited[i] = true;

		for (Integer adjVertex : adj.get(i)) {

			if (!visited[adjVertex] && checkUsingDFS(adjVertex, visited, visitedPath, adj)) {
				return true;
			} else if (visitedPath[adjVertex]) {
				return true;
			}
		}
		visitedPath[i] = false;
		return false;
	}

	public static void main(String[] args) {
		int V = 11;
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<>());
		}
		adj.get(1).add(2);
		adj.get(2).add(3);
		adj.get(3).add(4);
		adj.get(3).add(7);
		adj.get(4).add(5);
		adj.get(5).add(6);
		adj.get(7).add(5);
		adj.get(8).add(9);
		adj.get(9).add(10);
		adj.get(10).add(8);

		DetectACycleInADirectedGraph obj = new DetectACycleInADirectedGraph();
		boolean ans = obj.isCyclic(V, adj);
		if (ans)
			System.out.println("True");
		else
			System.out.println("False");

	}
}
