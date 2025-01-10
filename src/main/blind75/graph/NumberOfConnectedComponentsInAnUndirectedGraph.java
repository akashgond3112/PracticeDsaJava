package main.blind75.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Number of Connected Components in an Undirected Graph
 * There is an undirected graph with n nodes. There is also an edges array, where edges[i] = [a, b] means that there is an edge between node a and node b in the graph.
 *
 * The nodes are numbered from 0 to n - 1.
 *
 * Return the total number of connected components in that graph.
 *
 * Example 1:
 *
 * Input:
 * n=3
 * edges=[[0,1], [0,2]]
 *
 * Output:
 * 1
 * Example 2:
 *
 * Input:
 * n=6
 * edges=[[0,1], [1,2], [2,3], [4,5]]
 *
 * Output:
 * 2
 * Constraints:
 *
 * 1 <= n <= 100
 * 0 <= edges.length <= n * (n - 1) / 2
 *
 * For this connected components problem, I would ask these key clarifying questions:
 *
 * 1. "How should we handle isolated nodes (nodes with no edges)? Are they counted as individual components?" (This helps understand how to treat single nodes - from Example 2, it appears each isolated node is its own component)
 *
 * 2. "Can we assume there are no duplicate edges in the input?" (This clarifies if we need to handle duplicate edges)
 *
 * 3. "Can we assume all node numbers in edges are valid (i.e., less than n)?" (This helps establish if we need input validation)
 *
 * 4. "For n = 1, would an empty edges array return 1?" (This helps clarify the base case)
 *
 * The most important question here is the first one about isolated nodes, as it establishes how we count components and affects our
 * approach to traversing the graph.
 * */
public class NumberOfConnectedComponentsInAnUndirectedGraph {

	/**
	 * Time complexity: O(V+E) Space complexity: O(V+E) Where V is the number of vertices and E is the number of edges
	 * in the graph.
	 */
	public static class SolutionUsingDfs {
		public int countComponents(int n, int[][] edges) {
			List<List<Integer>> adj = new ArrayList<>();
			boolean[] visit = new boolean[n];
			for (int i = 0; i < n; i++) {
				adj.add(new ArrayList<>());
			}
			for (int[] edge : edges) {
				adj.get(edge[0]).add(edge[1]);
				adj.get(edge[1]).add(edge[0]);
			}

			int res = 0;
			for (int node = 0; node < n; node++) {
				if (!visit[node]) {
					dfs(adj, visit, node);
					res++;
				}
			}
			return res;
		}

		private void dfs(List<List<Integer>> adj, boolean[] visit, int node) {
			visit[node] = true;
			for (int nei : adj.get(node)) {
				if (!visit[nei]) {
					dfs(adj, visit, nei);
				}
			}
		}
	}

	/**
	 * Time complexity: O(V+E) Space complexity: O(V+E) Where V is the number of vertices and E is the number of edges
	 * in the graph.
	 */
	public static class SolutionUsingBfs {
		public int countComponents(int n, int[][] edges) {
			List<List<Integer>> adj = new ArrayList<>();
			boolean[] visit = new boolean[n];
			for (int i = 0; i < n; i++) {
				adj.add(new ArrayList<>());
			}
			for (int[] edge : edges) {
				adj.get(edge[0]).add(edge[1]);
				adj.get(edge[1]).add(edge[0]);
			}

			int res = 0;
			for (int node = 0; node < n; node++) {
				if (!visit[node]) {
					bfs(adj, visit, node);
					res++;
				}
			}
			return res;
		}

		private void bfs(List<List<Integer>> adj, boolean[] visit, int node) {
			Queue<Integer> q = new LinkedList<>();
			q.offer(node);
			visit[node] = true;
			while (!q.isEmpty()) {
				int cur = q.poll();
				for (int nei : adj.get(cur)) {
					if (!visit[nei]) {
						visit[nei] = true;
						q.offer(nei);
					}
				}
			}
		}
	}
}
