package main.dsa.nonlinear.graph.topologicalsort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Topological sort
Difficulty: MediumAccuracy: 56.52%Submissions: 216K+Points: 4
Given an adjacency list for a Directed Acyclic Graph (DAG) where adj_list[i] contains a list of all vertices j such that there is a directed edge from vertex i to vertex j,
with  V  vertices and E  edges, your task is to find any valid topological sorting of the graph.

In a topological sort, for every directed-edge u -> v, u must come before v in the ordering.

Example 1:

Input:

Output:
1
Explanation:
 output 1 denotes that the order is
valid. So, if you have implemented
your function correctly, then output
would be 1 for all test cases.
One possible Topological order for the
graph is 3, 2, 1, 0.

Example 2:

Input:
Output:
1
Explanation:
 output 1 denotes that the order is
valid. So, if you have implemented
your function correctly, then output
would be 1 for all test cases.
One possible Topological order for the
graph is 5, 4, 2, 1, 3, 0.
Your Task:
You don't need to read input or print anything. Your task is to complete the function topoSort()  which takes the integer V denoting the number of vertices and adjacency
list as input parameters and returns an array consisting of the vertices in Topological order. As there are multiple Topological orders possible, you may return any of them.
If your returned topo sort is correct, then the console output will be 1 else 0.

Expected Time Complexity: O(V + E).
Expected Auxiliary Space: O(V).

Constraints:
2 ≤ V ≤ 104
1 ≤ E ≤ (N*(N-1))/2
*/
public class KahnsAlgorithm {

	static int[] topoSort(int v, List<ArrayList<Integer>> adj) {

		int[] inDegrees = new int[v];
		for (int i = 0; i < v; i++) {
			for (int it : adj.get(i)) {
				inDegrees[it]++;
			}
		}

		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < v; i++) {
			if (inDegrees[i] == 0) {
				q.add(i);
			}
		}

		int[] topo = new int[v];
		int i = 0;
		while (!q.isEmpty()) {
			int node = q.peek();
			q.remove();
			topo[i++] = node;

			for (int next : adj.get(node)) {
				inDegrees[next]--;
				if (inDegrees[next] == 0) {
					q.add(next);
				}
			}
		}
		return topo;
	}

	public static void main(String[] args) {
		int V = 6;
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<>());
		}
		adj.get(2).add(3);
		adj.get(3).add(1);
		adj.get(4).add(0);
		adj.get(4).add(1);
		adj.get(5).add(0);
		adj.get(5).add(2);

		int[] ans = KahnsAlgorithm.topoSort(V, adj);
		for (int node : ans) {
			System.out.print(node + " ");
		}
		System.out.println("");
	}

}
