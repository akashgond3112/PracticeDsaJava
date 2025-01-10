package main.blind75.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 261. Graph Valid Tree - Explanation
 * Description
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
 *
 * Example 1:
 * Input:
 * n = 5
 * edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
 * Output: true
 * Example 2:
 * Input:
 * n = 5
 * edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
 * Output: false
 * Note:
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 * Constraints:
 * 1 <= n <= 100
 * 0 <= edges.length <= n * (n - 1) / 2
 *
 * For this graph valid tree problem, I would ask these key clarifying questions:
 *
 * 1. "What are the properties that make a graph a valid tree?" (This ensures we're on the same page about what constitutes a valid tree - no cycles and all nodes are connected)
 *
 * 2. "How should we handle the case when n > 0 but edges is empty?" (This helps understand how to handle disconnected components)
 *
 * 3. "Can we assume all node numbers in edges are valid (i.e., less than n)?" (This clarifies if we need input validation)
 *
 * 4. "In the case of n = 1, would an empty edges array be considered a valid tree?" (This helps clarify edge cases)
 *
 * The most critical question is the first one about tree properties, as it establishes the fundamental requirements we need to check. It helps confirm that we need to verify:
 * - No cycles exist
 * - All nodes are connected
 * - The number of edges is exactly n-1
 * */
public class GraphValidTree {

	/**
	 * Time complexity: O(V+E) Space complexity: O(V+E) Where V is the number vertices and E
	 * is the number of edges in the graph.
	 */
	public static class SolutionUsingDfs {
		public boolean validTree(int n, int[][] edges) {
			if (edges.length > n - 1) {
				return false;
			}

			List<List<Integer>> adj = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				adj.add(new ArrayList<>());
			}

			for (int[] edge : edges) {
				adj.get(edge[0]).add(edge[1]);
				adj.get(edge[1]).add(edge[0]);
			}

			Set<Integer> visit = new HashSet<>();
			if (dfs(0, -1, visit, adj)) {
				return false;
			}

			return visit.size() == n;
		}

		private boolean dfs(int node, int parent, Set<Integer> visit, List<List<Integer>> adj) {
			if (visit.contains(node)) {
				return true;
			}

			visit.add(node);
			for (int nei : adj.get(node)) {
				if (nei == parent) {
					continue;
				}
				if (dfs(nei, node, visit, adj)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Time complexity: O(V+E) Space complexity: O(V+E) Where V is the number vertices and E
	 * is the number of edges in the graph.
	 */
	public static class SolutionUsingBfs {
		public boolean validTree(int n, int[][] edges) {
			if (edges.length > n - 1) {
				return false;
			}

			List<List<Integer>> adj = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				adj.add(new ArrayList<>());
			}

			for (int[] edge : edges) {
				adj.get(edge[0]).add(edge[1]);
				adj.get(edge[1]).add(edge[0]);
			}

			Set<Integer> visit = new HashSet<>();
			Queue<int[]> q = new LinkedList<>();
			q.offer(new int[]{0, -1});  // {current node, parent node}
			visit.add(0);

			while (!q.isEmpty()) {
				int[] pair = q.poll();
				int node = pair[0], parent = pair[1];
				for (int nei : adj.get(node)) {
					if (nei == parent) {
						continue;
					}
					if (visit.contains(nei)) {
						return false;
					}
					visit.add(nei);
					q.offer(new int[]{nei, node});
				}
			}

			return visit.size() == n;
		}
	}
}
