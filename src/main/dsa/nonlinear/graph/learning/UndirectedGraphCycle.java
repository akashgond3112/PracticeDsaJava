package main.dsa.nonlinear.graph.learning;

/*
Undirected Graph Cycle
Difficulty: MediumAccuracy: 30.13%Submissions: 415K+Points: 4
Given an undirected graph with V vertices labelled from 0 to V-1 and E edges, check whether it contains any cycle or not.
The Graph is in the form of adjacency list where adj[i] contains all the nodes ith node is having edge with.

Example 1:
Input:
V = 5, E = 5
adj = {{1}, {0, 2, 4}, {1, 3}, {2, 4}, {1, 3}}
Output: 1
Explanation:

1->2->3->4->1 is a cycle.

Example 2:
Input:
V = 4, E = 2
adj = {{}, {2}, {1, 3}, {2}}
Output: 0
Explanation:

No cycle in the graph.
Your Task:
You don't need to read or print anything. Your task is to complete the function isCycle() which takes V denoting the number of vertices and adjacency list as input parameters and returns a boolean value denoting if the undirected graph contains any cycle or not, return 1 if a cycle is presently else return 0.

NOTE: The adjacency list denotes the edges of the graph where edges[i] stores all other vertices to which ith vertex is connected.

Expected Time Complexity: O(V + E)
Expected Space Complexity: O(V)

Constraints:
1 ≤ V, E ≤ 105
* */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UndirectedGraphCycle {

	public static boolean dfs(int node, int parent, List<ArrayList<Integer>> adj, int[] vis) {

		vis[node] = 1;
		for (int v : adj.get(node)) {
			if (vis[v] == 0) {
				if (dfs(v, node, adj, vis))
					return true;
			} else if (v != parent) {
				return true;
			}
		}
		return false;
	}

	public boolean isCyclicGraph(int v, List<ArrayList<Integer>> adj) {
		int[] visited = new int[v];
		for (int i = 0; i < v; i++) {
			if (visited[i] == 0 && dfs(i, -1, adj, visited))
					return true;
		}
		return false;
	}

	public static void main(String[] args) {

		UndirectedGraphCycle graph = new UndirectedGraphCycle();
		List<ArrayList<Integer>> adj = new ArrayList<>();
		adj.add(new ArrayList<>(Arrays.asList(0, 4)));
		adj.add(new ArrayList<>(Arrays.asList(1, 2)));
		adj.add(new ArrayList<>(Arrays.asList(1, 4)));
		adj.add(new ArrayList<>(Arrays.asList(2, 3)));
		adj.add(new ArrayList<>(Arrays.asList(3, 4)));

		System.out.println(graph.isCyclicGraph(5, adj));
	}

}
