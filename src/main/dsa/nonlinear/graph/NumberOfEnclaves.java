package main.dsa.nonlinear.graph;

import java.util.LinkedList;
import java.util.Queue;

/*
Number Of Enclaves
Difficulty: MediumAccuracy: 50.93%Submissions: 72K+Points: 4
You are given an n x m binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.

A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.

Find the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

Example 1:

Input:
grid[][] = {{0, 0, 0, 0},
            {1, 0, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}}
Output:
3
Explanation:
0 0 0 0
1 0 1 0
0 1 1 0
0 0 0 0
The highlighted cells represents the land cells.
Example 2:

Input:
grid[][] = {{0, 0, 0, 1},
            {0, 1, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 1},
            {0, 1, 1, 0}}
Output:
4
Explanation:
0 0 0 1
0 1 1 0
0 1 1 0
0 0 0 1
0 1 1 0
The highlighted cells represents the land cells.
Your Task:

You don't need to print or input anything. Complete the function numberOfEnclaves() which takes a 2D integer matrix grid as the input parameter and returns an integer,
denoting the number of land cells.

Expected Time Complexity: O(n * m)

Expected Space Complexity: O(n * m)

Constraints:

1 <= n, m <= 500
grid[i][j] == 0 or 1
*/
public class NumberOfEnclaves {

	static final int[] delRow = { -1, 0, +1, 0 };
	static final int[] delCol = { 0, 1, 0, -1 };


	class Pair {
		int index;
		int parent;

		public Pair(int index, int parent) {
			this.index = index;
			this.parent = parent;
		}
	}

	int numberOfEnclaves(int[][] grid) {

		int n = grid.length;
		int m = grid[0].length;

		int[][] visited = new int[n][m];
		Queue<Pair> queue = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if ((i == 0 || j == 0 || i == n - 1 || j == m - 1) && grid[i][j] == 1) {
					queue.add(new Pair(i, j));
					visited[i][j] = 1;
				}

			}
		}

		bfs(grid, queue, n, m, visited);

		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visited[i][j] == 0 && grid[i][j] == 1) {
					count++;
				}
			}
		}
		return count;
	}

	private void bfs(int[][] grid, Queue<Pair> queue, int n, int m, int[][] visited) {
		while (!queue.isEmpty()) {
			Pair pair = queue.peek();
			int curRow = pair.index;
			int curCol = pair.parent;
			queue.remove();

			for (int k = 0; k < 4; k++) {
				int newRow = curRow + delRow[k];
				int newCol = curCol + delCol[k];

				if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && visited[newRow][newCol] == 0 && grid[newRow][newCol] == 1) {
					queue.add(new Pair(newRow, newCol));
					visited[newRow][newCol] = 1;
				}
			}
		}
	}

	public static void main(String[] args) {

		NumberOfEnclaves numberOfEnclaves = new NumberOfEnclaves();
		int[][] input = new int[][] { { 0, 0, 0, 0 }, { 1, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } };

		System.out.println(numberOfEnclaves.numberOfEnclaves(input));
	}
}
