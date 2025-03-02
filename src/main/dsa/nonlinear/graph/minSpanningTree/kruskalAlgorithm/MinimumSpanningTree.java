package main.dsa.nonlinear.graph.minSpanningTree.kruskalAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.System.*;

/*
Minimum Spanning Tree
Difficulty: MediumAccuracy: 47.82%Submissions: 127K+Points: 4
Given a weighted, undirected, and connected graph with V vertices and E edges, your task is to find the sum of the weights of the edges in the Minimum Spanning Tree (MST) of the graph. The graph is represented by an adjacency list, where each element adj[i] is a vector containing pairs of integers. Each pair represents an edge, with the first integer denoting the endpoint of the edge and the second integer denoting the weight of the edge.

Example 1:

Input:
3 3
0 1 5
1 2 3
0 2 1

Output:
4
Explanation:

The Spanning Tree resulting in a weight
of 4 is shown above.
Example 2:

Input:
2 1
0 1 5

Output:
5
Explanation:
Only one Spanning Tree is possible
which has a weight of 5.


Your task:
Since this is a functional problem you don't have to worry about input, you just have to complete the function spanningTree() which takes a number of vertices V and an adjacency list adj as input parameters and returns an integer denoting the sum of weights of the edges of the Minimum Spanning Tree. Here adj[i] contains vectors of size 2, where the first integer in that vector denotes the end of the edge and the second integer denotes the edge weight.

Expected Time Complexity: O(ElogV).
Expected Auxiliary Space: O(V2).


Constraints:
2 ≤ V ≤ 1000
V-1 ≤ E ≤ (V*(V-1))/2
1 ≤ w ≤ 1000
The graph is connected and doesn't contain self-loops & multiple edges.

Time Complexity: O(E*logE) + O(E*logE)~ O(E*logE), where E = no. of given edges.
The maximum size of the priority queue can be E so after at most E iterations the priority queue will be empty and the loop will end. Inside the loop, there is a pop operation that will take logE time. This will result in the first O(E*logE) time complexity. Now, inside that loop, for every node, we need to traverse all its adjacent nodes where the number of nodes can be at most E. If we find any node unvisited, we will perform a push operation and for that, we need a logE time complexity. So this will result in the second O(E*logE).

Space Complexity: O(E) + O(V), where E = no. of edges and V = no. of vertices. O(E) occurs due to the size of the priority queue and O(V) due to the visited array. If we wish to get the mst, we need an extra O(V-1) space to store the edges of the most.

Time Complexity: O(N+E) + O(E logE) + O(E*4α*2)   where N = no. of nodes and E = no. of edges. O(N+E) for extracting edge information from the adjacency list. O(E logE) for sorting the array consists of the edge tuples. Finally, we are using the disjoint set operations inside a loop. The loop will continue to E times. Inside that loop, there are two disjoint set operations like findUPar() and UnionBySize() each taking 4 and so it will result in 4*2. That is why the last term O(E*4*2) is added.

Space Complexity: O(N) + O(N) + O(E) where E = no. of edges and N = no. of nodes. O(E) space is taken by the array that we are using to store the edge information. And in the disjoint set data structure, we are using two N-sized arrays i.e. a parent and a size array (as we are using unionBySize() function otherwise, a rank array of the same size if unionByRank() is used) which result in the first two terms O(N).
*/
public class MinimumSpanningTree {

	static class Edge implements Comparable<Edge> {
		int src;
		int dest;
		int weight;

		Edge(int _src, int _dest, int _wt) {
			this.src = _src;
			this.dest = _dest;
			this.weight = _wt;
		}

		// Comparator function used for
		// sorting edges based on their weight
		public int compareTo(Edge compareEdge) {
			return this.weight - compareEdge.weight;
		}
	}

	int spanningTree(int v, ArrayList<ArrayList<ArrayList<Integer>>> adj) {

		List<Edge> edges = new ArrayList<>();
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < adj.get(i).size(); j++) {
				int adjNode = adj.get(i).get(j).get(0);
				int wt = adj.get(i).get(j).get(1);

				Edge tmp = new Edge(i, adjNode, wt);
				edges.add(tmp);
			}
		}
		DisjointSet set = new DisjointSet(v);
		Collections.sort(edges);
		int mstWt = 0;

		for (Edge edge : edges) {
			int wt = edge.weight;
			int currU = edge.src;
			int currV = edge.dest;

			if (set.findUPar(currU) != set.findUPar(currV)) {
				mstWt += wt;
				set.unionBySize(currU, currV);
			}
		}
		return mstWt;
	}

	public static void main(String[] args) {
		int i1 = 5;
		ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();
		int[][] edges = { { 0, 1, 2 }, { 0, 2, 1 }, { 1, 2, 1 }, { 2, 3, 2 }, { 3, 4, 1 }, { 4, 2, 2 } };

		for (int i = 0; i < i1; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 0; i < 6; i++) {
			int u = edges[i][0];
			int v = edges[i][1];
			int w = edges[i][2];

			ArrayList<Integer> tmp1 = new ArrayList<>();
			ArrayList<Integer> tmp2 = new ArrayList<>();
			tmp1.add(v);
			tmp1.add(w);

			tmp2.add(u);
			tmp2.add(w);

			adj.get(u).add(tmp1);
			adj.get(v).add(tmp2);
		}

		MinimumSpanningTree obj = new MinimumSpanningTree();
		int sum = obj.spanningTree(i1, adj);
		out.println("The sum of all the edge weights: " + sum);
	}
}
