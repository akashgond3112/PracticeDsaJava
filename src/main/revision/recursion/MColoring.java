package main.revision.recursion;

import java.util.ArrayList;
import java.util.List;

public class MColoring {
	public static boolean graphColoring(List<Integer>[] g, int[] colors, int i, int m, int n) {
		// Your code here
		if (i == n) {
			return true;
		}

		for (int j = 1; j <= m; j++) {
			if (isSafe(i, g, colors, n, j)) {
				colors[i] = j;
				if (graphColoring(g, colors, i + 1, m, m)) {
					return true;
				}
				colors[i] = 0;
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
		int n = G.length;
		boolean ans = graphColoring(G, color, 0, M, n);
		if (ans)
			System.out.println("1");
		else
			System.out.println("0");
	}
}
