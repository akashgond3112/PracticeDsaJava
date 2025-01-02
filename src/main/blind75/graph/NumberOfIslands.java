package main.blind75.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 *200. Number of Islands
 * Medium
 * Topics
 * Companies
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 *  You may assume all four edges of the grid are all surrounded by water.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 *
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'. */
public class NumberOfIslands {

	/**
	 * Time complexity: O(m∗n) Space complexity: O(m∗n) Where m is the number of rows and n
	 * is the number of columns in the grid.
	 */
	static public class SolutionUsingBfs {
		private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

		public int numIslands(char[][] grid) {
			int ROWS = grid.length, COLS = grid[0].length;
			int islands = 0;

			for (int r = 0; r < ROWS; r++) {
				for (int c = 0; c < COLS; c++) {
					if (grid[r][c] == '1') {
						bfs(grid, r, c);
						islands++;
					}
				}
			}

			return islands;
		}

		private void bfs(char[][] grid, int r, int c) {
			Queue<int[]> q = new LinkedList<>();
			grid[r][c] = '0';
			q.add(new int[] { r, c });

			while (!q.isEmpty()) {
				int[] node = q.poll();
				int row = node[0], col = node[1];

				for (int[] dir : directions) {
					int nr = row + dir[0], nc = col + dir[1];
					if (nr >= 0 && nc >= 0 && nr < grid.length && nc < grid[0].length && grid[nr][nc] == '1') {
						q.add(new int[] { nr, nc });
						grid[nr][nc] = '0';
					}
				}
			}
		}
	}


	/**
	 * Time complexity: O(m∗n) Space complexity: O(m∗n) Where m is the number of rows and n is the number of columns in
	 * the grid.
	 */
	public static class SolutionUsingDFS {
		private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

		public int numIslands(char[][] grid) {
			int ROWS = grid.length, COLS = grid[0].length;
			int islands = 0;

			for (int r = 0; r < ROWS; r++) {
				for (int c = 0; c < COLS; c++) {
					if (grid[r][c] == '1') {
						dfs(grid, r, c);
						islands++;
					}
				}
			}

			return islands;
		}

		private void dfs(char[][] grid, int r, int c) {
			if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0') {
				return;
			}

			grid[r][c] = '0';
			for (int[] dir : directions) {
				dfs(grid, r + dir[0], c + dir[1]);
			}
		}
	}
}
