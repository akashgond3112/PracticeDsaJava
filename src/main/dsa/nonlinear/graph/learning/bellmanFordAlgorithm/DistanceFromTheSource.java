package main.dsa.nonlinear.graph.learning.bellmanFordAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;

/*
Distance from the Source (Bellman-Ford Algorithm)
Difficulty: MediumAccuracy: 48.11%Submissions: 118K+Points: 4
Given a weighted and directed graph of V vertices and E edges, Find the shortest distance of all the vertex's from the source vertex S. If a vertices can't be reach from the S then mark the distance as 10^8. Note: If the Graph contains a negative cycle then return an array consisting of only -1.

Example 1:

Input:

E = [[0,1,9]]
S = 0
Output:
0 9
Explanation:
Shortest distance of all nodes from
source is printed.
Example 2:

Input:

E = [[0,1,5],[1,0,3],[1,2,-1],[2,0,1]]
S = 2
Output:
1 6 0
Explanation:
For nodes 2 to 0, we can follow the path-
2-0. This has a distance of 1.
For nodes 2 to 1, we cam follow the path-
2-0-1, which has a distance of 1+5 = 6,


Your Task:
You don't need to read input or print anything. Your task is to complete the function bellmanFord( ) which takes a number of vertices V and an E-sized list of lists of three integers where the three integers are u,v, and w; denoting there's an edge from u to v, which has a weight of w and source node S as input parameters and returns a list of integers where the ith integer denotes the distance of an ith node from the source node.

If some node isn't possible to visit, then its distance should be 100000000(1e8). Also, If the Graph contains a negative cycle then return an array consisting of a single -1.

Expected Time Complexity: O(V*E).
Expected Auxiliary Space: O(V).

Constraints:
1 ≤ V ≤ 500
1 ≤ E ≤ V*(V-1)
-1000 ≤ adj[i][j] ≤ 1000
0 ≤ S < V
*/
public class DistanceFromTheSource {

	static int[] bellmanFord(int v, ArrayList<ArrayList<Integer>> edges, int s) {
		// Write your code here
		int[] dist = new int[v];
		Arrays.fill(dist, (int) 1e9);
		dist[s] = 0;

		for (int i = 0; i < v - 1; i++) {
			for (ArrayList<Integer> edge : edges) {
				int currU = edge.get(0);
				int currV = edge.get(1);
				int currWt = edge.get(2);

				if (dist[currU] != (int) 1e9 && dist[currU] + currWt < dist[currV]) {
					dist[currV] = dist[currU] + currWt;
				}
			}
		}

		for (ArrayList<Integer> edge : edges) {
			int currU = edge.get(0);
			int currV = edge.get(1);
			int currWt = edge.get(2);

			if (dist[currU] != (int) 1e9 && dist[currU] + currWt < dist[currV]) {
				int[] temp = new int[1];
				temp[0] = -1;
				return temp;
			}
		}
		return dist;
	}

	public static void main(String[] args) {
		int V = 6;
		int S = 0;
		ArrayList<ArrayList<Integer>> edges = new ArrayList<>() {
			{
				add(new ArrayList<Integer>(Arrays.asList(3, 2, 6)));
				add(new ArrayList<Integer>(Arrays.asList(5, 3, 1)));
				add(new ArrayList<Integer>(Arrays.asList(0, 1, 5)));
				add(new ArrayList<Integer>(Arrays.asList(1, 5, -3)));
				add(new ArrayList<Integer>(Arrays.asList(1, 2, -2)));
				add(new ArrayList<Integer>(Arrays.asList(3, 4, -2)));
				add(new ArrayList<Integer>(Arrays.asList(2, 4, 3)));
			}
		};


		int[] dist = DistanceFromTheSource.bellmanFord(V, edges, S);
		for (int i = 0; i < V; i++) {
			System.out.print(dist[i] + " ");
		}
		System.out.println("");
	}
}
