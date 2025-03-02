package main.dsa.nonlinear.graph.minSpanningTree.kruskalAlgorithm;

import java.util.HashSet;

/*
Maximum Connected group
Difficulty: HardAccuracy: 49.48%Submissions: 39K+Points: 8
You are given a square binary grid. A grid is considered binary if every value in the grid is either 1 or 0. You can change at most one cell in the grid from 0 to 1. You need to find the largest group of connected  1's. Two cells are said to be connected if both are adjacent(top, bottom, left, right) to each other and both have the same value.

Examples :

Input: grid = [1, 1]
             [0, 1]
Output: 4
Explanation: By changing cell (2,1), we can obtain a connected group of 4 1's
[1, 1]
[1, 1]
Input: grid = [1, 0, 1]
             [1, 0, 1]
             [1, 0, 1]
Output: 7
Explanation: By changing cell (3,2), we can obtain a connected group of 7 1's
[1, 0, 1]
[1, 0, 1]
[1, 1, 1]
Expected Time Complexity: O(n2)
Expected Auxiliary Space: O(n2)

Constraints:
1 <= size of the grid<= 500
0 <= grid[i][j] <= 1
*/
public class MaximumConnectedGroup {

	public int MaxConnection(int[][] grid) {

		int n = grid.length;
		DisjointSet ds = stepOne(grid, n);

		int max = 0;
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				if (grid[row][col] == 1)
					continue;
				int[] dr = { -1, 0, 1, 0 };
				int[] dc = { 0, -1, 0, 1 };

				HashSet<Integer> set = new HashSet<>();
				for (int d = 0; d < 4; d++) {
					int newRow = row + dr[d];
					int newCol = col + dc[d];
					if (isValid(newRow, newCol, n) && grid[newRow][newCol] == 1) {
						set.add(ds.findUPar(newRow * n + newCol));
					}
				}

				int size = 0;

				for (Integer i : set) {
					size += ds.size.get(i);
				}
				max = Math.max(max, size + 1);
			}
		}

		for (int cell = 0; cell < n * n; cell++) {

			max = Math.max(max, ds.size.get(ds.findUPar(cell)));
		}
		return max;
	}

	private DisjointSet stepOne(int[][] grid, int n) {
		DisjointSet ds = new DisjointSet(n * n);
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				if (grid[row][col] == 0)
					continue;

				int[] dr = { -1, 0, 1, 0 };
				int[] dc = { 0, -1, 0, 1 };

				for (int d = 0; d < 4; d++) {

					int newRow = row + dr[d];
					int newCol = col + dc[d];

					if (isValid(newRow, newCol, n) && grid[newRow][newCol] == 1) {

						int nodeNo = row * n + col;
						int adjNodeNo = newRow * n + newCol;
						ds.unionBySize(nodeNo, adjNodeNo);
					}
				}
			}
		}
		return ds;
	}

	private boolean isValid(int newr, int newc, int n) {
		return newr >= 0 && newr < n && newc >= 0 && newc < n;
	}

	public static void main(String[] args) {
		int[][] grid = { { 1, 1, 0, 1, 1, 0 }, { 1, 1, 0, 1, 1, 0 }, { 1, 1, 0, 1, 1, 0 }, { 0, 0, 1, 0, 0, 0 },
				{ 0, 0, 1, 1, 1, 0 }, { 0, 0, 1, 1, 1, 0 } };

		MaximumConnectedGroup obj = new MaximumConnectedGroup();
		int ans = obj.MaxConnection(grid);
		System.out.println("The largest group of connected 1s is of size: " + ans);
	}
}
