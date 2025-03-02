package main.dsa.nonlinear.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static main.dsa.nonlinear.graph.topologicalsort.CourseSchedule.queueTask;

/*
Eventual Safe States
Difficulty: MediumAccuracy: 55.52%Submissions: 59K+Points: 4
A directed graph of V vertices and E edges is given in the form of an adjacency list adj. Each node of the graph is labeled with a distinct integer in the range 0 to V - 1.

A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node.

You have to return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.

Example 1:
Input:

Output:
2 4 5 6
Explanation:
The given graph is shown above.
Nodes 5 and 6 are terminal nodes as there are no
outgoing edges from either of them.
Every path starting at nodes 2, 4, 5, and 6 all
lead to either node 5 or 6.

Example 2:
Input:

Output:
3
Explanation:
Only node 3 is a terminal node, and every path
starting at node 3 leads to node 3.
Your Task:
You don't need to read or print anything. Your task is to complete the function eventualSafeNodes() which takes an integer V denoting no. Of vertices and adj denoting an adjacency list of the graph and returns an array of safe nodes.

Expected Time Complexity: O(V + E)

Expected Space Complexity: O(V)

Constraints:

1 <= V <= 104
0 <= E <= 104
The graph won't contain self-loops.
Each node in the graph has a distinct value in the range 0 to V - 1.
*/
public class EventualSafeStates {

	List<Integer> eventualSafeNodes(int v, List<List<Integer>> adj) {

		int[] visited = new int[v];
		int[] visitedPath = new int[v];
		int[] check = new int[v];
		for (int i = 0; i < v; i++) {
			if (visited[i] == 0) {
				dfsCheck(i, adj, visited, visitedPath, check);
			}
		}
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < v; i++) {
			if (check[i] == 1) {
				result.add(i);
			}
		}
		return result;

	}

	private boolean dfsCheck(int i, List<List<Integer>> adj, int[] visited, int[] visitedPath, int[] check) {

		visited[i] = 1;
		visitedPath[i] = 1;
		check[i] = 0;

		for (int n : adj.get(i)) {
			if (visited[n] == 0 && dfsCheck(n, adj, visited, visitedPath, check)) {
				return true;
			} else if (visitedPath[n] == 1) {
				return true;
			}
		}

		check[i] = 1;
		visitedPath[i] = 0;
		return false;
	}

	static List<Integer> eventualSafeNodesUsingTopoSort(int v, List<List<Integer>> adj) {

		ArrayList<ArrayList<Integer>> reverseList = new ArrayList<>();
		for (int i = 0; i < v; i++) {
			reverseList.add(new ArrayList<>());
		}

		int[] inDegree = new int[v];
		for (int i = 0; i < v; i++) {
			for (int j : adj.get(i)) {
				reverseList.get(j).add(i);
				inDegree[i]++;
			}
		}

		Queue<Integer> queue = new LinkedList<>();
		List<Integer> res = new ArrayList<>();

		for (int i = 0; i < v; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}

		queueTask(reverseList, inDegree, queue, res);

		Collections.sort(res);
		return res;
	}

	public static void main(String[] args) {
		int V = 12;
		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<>());
		}
		adj.get(0).add(1);
		adj.get(1).add(2);
		adj.get(2).add(3);
		adj.get(3).add(4);
		adj.get(3).add(5);
		adj.get(4).add(6);
		adj.get(5).add(6);
		adj.get(6).add(7);
		adj.get(8).add(1);
		adj.get(8).add(9);
		adj.get(9).add(10);
		adj.get(10).add(8);
		adj.get(11).add(9);

		EventualSafeStates obj = new EventualSafeStates();
		List<Integer> safeNodes = obj.eventualSafeNodes(V, adj);
		for (int node : safeNodes) {
			System.out.print(node + " ");
		}
		System.out.println("");

		safeNodes = eventualSafeNodesUsingTopoSort(V, adj);
		for (int node : safeNodes) {
			System.out.print(node + " ");
		}
		System.out.println("");
	}
}
