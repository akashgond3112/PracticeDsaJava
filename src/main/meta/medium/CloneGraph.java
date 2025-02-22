package main.meta.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 133. Clone Graph
 * Medium
 * Topics
 * Companies
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 *
 * Test case format:
 *
 * For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on.
 * The graph is represented in the test case using an adjacency list.
 *
 * An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
 *
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * Example 2:
 *
 *
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
 * Example 3:
 *
 * Input: adjList = []
 * Output: []
 * Explanation: This an empty graph, it does not have any nodes.
 *
 *
 * Constraints:
 *
 * The number of nodes in the graph is in the range [0, 100].
 * 1 <= Node.val <= 100
 * Node.val is unique for each node.
 * There are no repeated edges and no self-loops in the graph.
 * The Graph is connected and all nodes can be visited starting from the given node.*/
public class CloneGraph {

	// Definition for a Node.
	public static class Node {
		public int val;
		public List<Node> neighbors;

		public Node() {
			val = 0;
			neighbors = new ArrayList<>();
		}

		public Node(int _val) {
			val = _val;
			neighbors = new ArrayList<>();
		}

		public Node(int _val, ArrayList<Node> _neighbors) {
			val = _val;
			neighbors = _neighbors;
		}
	}

	/**
	 * Time complexity: O(V+E) Space complexity: O(V) Where V is the number of vertices and E is
	 * the number of edges.
	 */
	static public class SolutionUsingDFS {
		public Node cloneGraph(Node node) {
			Map<Node, Node> oldToNew = new HashMap<>();

			return dfs(node, oldToNew);
		}

		private Node dfs(Node node, Map<Node, Node> oldToNew) {
			if (node == null) {
				return null;
			}

			if (oldToNew.containsKey(node)) {
				return oldToNew.get(node);
			}

			Node copy = new Node(node.val);
			oldToNew.put(node, copy);

			for (Node nei : node.neighbors) {
				copy.neighbors.add(dfs(nei, oldToNew));
			}

			return copy;
		}
	}

	/**
	 * Time complexity: O(V+E) Space complexity: O(V) Where V is the number of vertices and E is
	 * the number of edges.
	 */
	public static class SolutionUsingBFS {
		public Node cloneGraph(Node node) {
			if (node == null)
				return null;
			Map<Node, Node> oldToNew = new HashMap<>();
			Queue<Node> q = new LinkedList<>();
			oldToNew.put(node, new Node(node.val));
			q.add(node);

			while (!q.isEmpty()) {
				Node cur = q.poll();
				for (Node nei : cur.neighbors) {
					if (!oldToNew.containsKey(nei)) {
						oldToNew.put(nei, new Node(nei.val));
						q.add(nei);
					}
					oldToNew.get(cur).neighbors.add(oldToNew.get(nei));
				}
			}
			return oldToNew.get(node);
		}
	}
}
