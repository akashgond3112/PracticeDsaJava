package main.dsa.nonlinear.graph.learning.Dijkstra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
Dijkstra Algorithm
Difficulty: MediumAccuracy: 50.83%Submissions: 163K+Points: 4
Given a weighted, undirected and connected graph of V vertices and an adjacency list adj where adj[i] is a list of lists containing two integers where the first integer of each list j denotes there is edge between i and j , second integers corresponds to the weight of that  edge .
You are given the source vertex S and You to Find the shortest distance of all the vertex's from the source vertex S. You have to return a list of integers denoting shortest distance between each node and Source vertex S.

Note: The Graph doesn't contain any negative weight cycle.

Example 1:

Input:
V = 2
adj [] = {{{1, 9}}, {{0, 9}}}
S = 0
Output:
0 9
Explanation:

The source vertex is 0. Hence, the shortest
distance of node 0 is 0 and the shortest
distance from node 1 is 9.

Example 2:

Input:
V = 3, E = 3
adj = {{{1, 1}, {2, 6}}, {{2, 3}, {0, 1}}, {{1, 3}, {0, 6}}}
S = 2
Output:
4 3 0
Explanation:

For nodes 2 to 0, we can follow the path-
2-1-0. This has a distance of 1+3 = 4,
whereas the path 2-0 has a distance of 6. So,
the Shortest path from 2 to 0 is 4.
The shortest distance from 0 to 1 is 1 .

Your Task:
You don't need to read input or print anything. Your task is to complete the function dijkstra()  which takes the number of vertices V and an adjacency list adj as input parameters and Source vertex S returns a list of integers, where ith integer denotes the shortest distance of the ith node from the Source node.
Here adj[i] contains a list of lists containing two integers where the first integer j denotes that there is an edge between i and j and the second integer w denotes that the weight between edge i and j is w.

Expected Time Complexity: O(V2).
Expected Auxiliary Space: O(V2).

Constraints:
1 ≤ V ≤ 1000
0 ≤ adj[i][j] ≤ 1000
1 ≤ adj.size() ≤ [ (V*(V - 1)) / 2 ]
0 ≤ S < V
*/
public class DijkstraAlgorithm {

	static class Pair {
		int node;
		int distance;

		public Pair(int node, int distance) {
			this.node = node;
			this.distance = distance;
		}
	}

	//Function to find the shortest distance of all the vertices
	//from the source vertex S.
	static int[] dijkstra(int v, ArrayList<ArrayList<ArrayList<Integer>>> adj, int s) {

		PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.distance));

		int[] dist = new int[v];
		Arrays.fill(dist, (int) 1e9);
		dist[s] = 0;
		pq.add(new Pair(s, 0));

		while (!pq.isEmpty()) {
			Pair currentPair = pq.poll();
			int node = currentPair.node;
			int distance = currentPair.distance;

			for (ArrayList<Integer> neighbor : adj.get(node)) {
				int adjNode = neighbor.get(0);
				int edgeWeight = neighbor.get(1);

				if (distance + edgeWeight < dist[adjNode]) {
					dist[adjNode] = distance + edgeWeight;
					pq.add(new Pair(adjNode, dist[adjNode]));
				}
			}
		}

		return dist;
	}

	public static void main(String[] args) {
		int V = 3, S = 2;
		ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();

		adj.add(new ArrayList<>()); // for node 0
		adj.add(new ArrayList<>()); // for node 1
		adj.add(new ArrayList<>()); // for node 2

		adj.get(0).add(new ArrayList<>(Arrays.asList(1, 1)));
		adj.get(0).add(new ArrayList<>(Arrays.asList(2, 6)));
		adj.get(1).add(new ArrayList<>(Arrays.asList(2, 3)));
		adj.get(1).add(new ArrayList<>(Arrays.asList(0, 1)));
		adj.get(2).add(new ArrayList<>(Arrays.asList(1, 3)));
		adj.get(2).add(new ArrayList<>(Arrays.asList(0, 6)));

		int[] result = dijkstra(V, adj, S);

		for (int i = 0; i < V; i++) {
			System.out.print(result[i] + " ");
		}
	}
}
