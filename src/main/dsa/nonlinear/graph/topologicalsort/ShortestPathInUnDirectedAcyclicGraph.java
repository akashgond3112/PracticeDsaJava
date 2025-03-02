package main.dsa.nonlinear.graph.topologicalsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Shortest path in Undirected Graph
Difficulty: MediumAccuracy: 49.98%Submissions: 69K+Points: 4
You are given an Undirected Graph having unit weight of the edges, find the shortest path from src to all the vertex and if it is unreachable to reach any vertex, then return -1 for that vertex.

Example1:

Input:
n = 9, m= 10
edges=[[0,1],[0,3],[3,4],[4,5],[5,6],[1,2],[2,6],[6,7],[7,8],[6,8]]
src=0
Output:
0 1 2 1 2 3 3 4 4
Explanation:
Example2:

Input:
n = 4, m= 4
edges=[[0,0],[1,1],[1,3],[3,0]]
src=3
Output:
1 1 -1 0
Explanation:
Your Task:
You don't need to print or input anything. Complete the function shortest path() which takes a 2d vector or array of edges representing the edges of an undirected graph with unit weight, an integer n as the number of nodes, an integer m as a number of edges and an integer src as the input parameters and returns an integer array or vector, denoting the vector of distance from src to all nodes.

Constraint:
1<=n,m<=10000
0<=edges[i][j]<=n-1

Expected Time Complexity: O(N + E), where N is the number of nodes and E is the edges
Expected Space Complexity: O(N)
* */
public class ShortestPathInUnDirectedAcyclicGraph {

	public int[] shortestPath(int[][] edges, int n, int m, int src) {

		List<ArrayList<Integer>> adjList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adjList.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			adjList.get(edges[i][0]).add(edges[i][1]);
			adjList.get(edges[i][1]).add(edges[i][0]);
		}

		int[] dist = new int[n];
		Arrays.fill(dist, (int) 1e9);
		dist[src] = 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(src);
		while (!queue.isEmpty()) {
			int node = queue.peek();
			queue.remove();
			for (int i : adjList.get(node)) {
				if (dist[i] > dist[node] + 1) {
					dist[i] = dist[node] + 1;
					queue.add(i);
				}
			}
		}

		for (int i = 0; i < n; i++) {
			if (dist[i] == 1e9) {
				dist[i] = -1;
			}
		}
		return dist;
	}

	public static void main(String[] args) {
		int n=9, m=10;
		int[][] edge = {{0,1},{0,3},{3,4},{4,5},{5,6},{1,2},{2,6},{6,7},{7,8},{6,8}};

		ShortestPathInUnDirectedAcyclicGraph obj = new ShortestPathInUnDirectedAcyclicGraph();
		int res[] = obj.shortestPath(edge,n,m,0);
		for(int i=0;i<n;i++){
			System.out.print(res[i]+" ");
		}
		System.out.println();
	}
}
