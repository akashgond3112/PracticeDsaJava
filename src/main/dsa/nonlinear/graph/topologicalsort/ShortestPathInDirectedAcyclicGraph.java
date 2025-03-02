package main.dsa.nonlinear.graph.topologicalsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
Shortest path in Directed Acyclic Graph
Difficulty: MediumAccuracy: 48.48%Submissions: 111K+Points: 4
Given a Directed Acyclic Graph of N vertices from 0 to N-1 and a 2D Integer array(or vector) edges[ ][ ] of length M, where there is a directed edge from edge[i][0] to edge[i][1] with a distance of edge[i][2] for all i.

Find the shortest path from src(0) vertex to all the vertices and if it is impossible to reach any vertex, then return -1 for that vertex.

Example1:

Input:
N = 4, M = 2
edge = [[0,1,2],[0,2,1]]
Output:
0 2 1 -1
Explanation:
Shortest path from 0 to 1 is 0->1 with edge weight 2.
Shortest path from 0 to 2 is 0->2 with edge weight 1.
There is no way we can reach 3, so it's -1 for 3.
Example2:

Input:
N = 6, M = 7
edge = [[0,1,2],[0,4,1],[4,5,4],[4,2,2],[1,2,3],[2,3,6],[5,3,1]]
Output:
0 2 3 6 1 5
Explanation:
Shortest path from 0 to 1 is 0->1 with edge weight 2.
Shortest path from 0 to 2 is 0->4->2 with edge weight 1+2=3.
Shortest path from 0 to 3 is 0->4->5->3 with edge weight 1+4+1=6.
Shortest path from 0 to 4 is 0->4 with edge weight 1.
Shortest path from 0 to 5 is 0->4->5 with edge weight 1+4=5.
Your Task:

You don't need to print or input anything. Complete the function shortest path() which takes an integer N as number of vertices, an integer M as number of edges and a 2D Integer array(or vector) edges as the input parameters and returns an integer array(or vector), denoting the list of distance from src to all nodes.

Expected Time Complexity: O(N+M), where N is the number of nodes and M is edges
Expected Space Complexity: O(N)

Constraint:
1 <= N <= 100
1 <= M <= min((N*(N-1))/2,4000)
0 <= edgei,0,edgei,1 < n
0 <= edgei,2 <=105
*/

public class ShortestPathInDirectedAcyclicGraph {

	static class Pair {
		int index;
		int parent;

		public Pair(int index, int parent) {
			this.index = index;
			this.parent = parent;
		}
	}

	public int[] shortestPath(int N, int M, int[][] edges) {

		List<ArrayList<Pair>> adj = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			ArrayList<Pair> list = new ArrayList<>();
			adj.add(list);
		}

		for (int i = 0; i < M; i++) {
			int u = edges[i][0];
			int v = edges[i][1];
			int weight = edges[i][2];
			adj.get(u).add(new Pair(v, weight));
		}

		int[] visited = new int[N];
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < N; i++) {
			if (visited[i] == 0) {
				topoSort(i, adj, visited, stack);
			}
		}

		int[] distance = new int[N];
		Arrays.fill(distance, (int) (1e9));

		distance[0] = 0;
		while (!stack.isEmpty()) {
			int node = stack.peek();
			stack.pop();

			for (int i = 0; i < adj.get(node).size(); i++) {
				int v = adj.get(node).get(i).index;
				int w = adj.get(node).get(i).parent;

				if (distance[node] + w < distance[v]) {
					distance[v] = distance[node] + w;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			if (distance[i] == (int) (1e9))
				distance[i] = -1;
		}
		return distance;
	}

	private void topoSort(int i, List<ArrayList<Pair>> adj, int[] visited, Stack<Integer> stack) {
		visited[i] = 1;
		for (int j = 0; j < adj.get(i).size(); j++) {
			int v = adj.get(i).get(j).index;

			if (visited[v] == 0) {
				topoSort(v, adj, visited, stack);
			}
		}
		stack.push(i);
	}

	public static void main(String[] args) {
		int n = 6;
		int m = 7;
		int[][] edge = { { 0, 1, 2 }, { 0, 4, 1 }, { 4, 5, 4 }, { 4, 2, 2 }, { 1, 2, 3 }, { 2, 3, 6 }, { 5, 3, 1 } };
		ShortestPathInDirectedAcyclicGraph obj = new ShortestPathInDirectedAcyclicGraph();
		int[] res = obj.shortestPath(n, m, edge);
		for (int i = 0; i < n; i++) {
			System.out.print(res[i] + " ");
		}
		System.out.println();
	}
}
