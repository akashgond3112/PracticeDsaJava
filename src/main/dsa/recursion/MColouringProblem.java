package main.dsa.recursion;

import java.util.ArrayList;
import java.util.List;

public class MColouringProblem {

	public static boolean graphColoring(List<Integer>[] g, int[] colors, int i, int m) {
		// Your code here
		int n = g.length;
		return solve(i, g, colors, n, m);
	}

	private static boolean solve(int node, List<Integer>[] g, int[] colors, int n, int m) {
		if (node == n) {
			return true;
		}

		for (int i = 1; i <= m; i++) {
			if (isSafe(node, g, colors, n, i)) {
				colors[node] = i;
				if (solve(node + 1, g, colors, n, m)) {
					return true;
				}
				colors[node] = 0;
			}
		}
		return false;

	}

	private static boolean isSafe(int node, List<Integer>[] g, int[] colors, int n, int i) {
		// Iterate through all neighbors of 'node' in the graph 'g'
		for (Integer it : g[node]) {
			// Check if the color of the neighbor is the same as the current color 'i'
			if (colors[it] == i) {
				return false; // If any neighbor has the same color, return false
			}
		}
		return true; // Safe to assign the color 'i' to this node
	}

	public static void main(String[] args) {
		int N = 4, M = 3;
		List<Integer>[] G = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			G[i] = new ArrayList<>();
		}
		G[0].add(1);
		G[1].add(0);
		G[1].add(2);
		G[2].add(1);
		G[2].add(3);
		G[3].add(2);
		G[3].add(0);
		G[0].add(3);
		G[0].add(2);
		G[2].add(0);
		int[] color = new int[N];
		boolean ans = graphColoring(G, color, 0, M);
		if (ans)
			System.out.println("1");
		else
			System.out.println("0");
	}


}
