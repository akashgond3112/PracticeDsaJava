package main.dsa.nonlinear.graph.learning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class DetectACycleInAGraph {

	class Pair {
		int index;
		int parent;

		public Pair(int index, int parent) {
			this.index = index;
			this.parent = parent;
		}
	}

	// Function to detect cycle in an undirected graph.
	public boolean isCycle(int V, List<ArrayList<Integer>> adj) {
		// Code here

		boolean[] vis = new boolean[V];
		Arrays.fill(vis,false);
		int[] parent = new int[V];
		Arrays.fill(parent,-1);

		for(int i=0;i<V;i++)
			if(!vis[i] && checkForCycle(i, adj, vis))
					return true;

		return false;
	}

	private boolean checkForCycle(int i, List<ArrayList<Integer>> adj, boolean[] visited) {
		visited[i] = true;

		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(i, -1));
		while (!q.isEmpty()) {
			int index = q.peek().index;
			int parent = q.peek().parent;
			q.remove();

			for (int adjNode : adj.get(index)) {
				if (!visited[adjNode]) {
					visited[adjNode] = true;
					q.add(new Pair(adjNode, index));
				} else if (parent != adjNode) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {

		DetectACycleInAGraph graph = new DetectACycleInAGraph();
		List<ArrayList<Integer>> adj = new ArrayList<>();
		adj.add(new ArrayList<>(Arrays.asList(0, 4)));
		adj.add(new ArrayList<>(Arrays.asList(1, 2)));
		adj.add(new ArrayList<>(Arrays.asList(1, 4)));
		adj.add(new ArrayList<>(Arrays.asList(2, 3)));
		adj.add(new ArrayList<>(Arrays.asList(3, 4)));

		System.out.println(graph.isCycle(5, adj));
	}
}
