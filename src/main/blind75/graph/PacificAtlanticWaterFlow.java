package main.blind75.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 417. Pacific Atlantic Water Flow
 * Medium
 * Topics
 * Companies
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 *
 * The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 *
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.
 *
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
 * [0,4]: [0,4] -> Pacific Ocean
 *        [0,4] -> Atlantic Ocean
 * [1,3]: [1,3] -> [0,3] -> Pacific Ocean
 *        [1,3] -> [1,4] -> Atlantic Ocean
 * [1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
 *        [1,4] -> Atlantic Ocean
 * [2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
 *        [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
 * [3,0]: [3,0] -> Pacific Ocean
 *        [3,0] -> [4,0] -> Atlantic Ocean
 * [3,1]: [3,1] -> [3,0] -> Pacific Ocean
 *        [3,1] -> [4,1] -> Atlantic Ocean
 * [4,0]: [4,0] -> Pacific Ocean
 *        [4,0] -> Atlantic Ocean
 * Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
 * Example 2:
 *
 * Input: heights = [[1]]
 * Output: [[0,0]]
 * Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
 *
 *
 * Constraints:
 *
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 105*/
public class PacificAtlanticWaterFlow {

	/**
	 * Time complexity: O(m∗n∗4^m∗n ) Space complexity: O(m∗n) Where m is the number of rows and n is the number of
	 * columns.
	 */
	public static class SolutionUsingBacktracking {
		int ROWS, COLS;
		boolean pacific, atlantic;
		int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

		public List<List<Integer>> pacificAtlantic(int[][] heights) {
			ROWS = heights.length;
			COLS = heights[0].length;
			List<List<Integer>> res = new ArrayList<>();

			for (int r = 0; r < ROWS; r++) {
				for (int c = 0; c < COLS; c++) {
					pacific = false;
					atlantic = false;
					dfs(heights, r, c, Integer.MAX_VALUE);
					if (pacific && atlantic) {
						res.add(Arrays.asList(r, c));
					}
				}
			}
			return res;
		}

		private void dfs(int[][] heights, int r, int c, int prevVal) {
			if (r < 0 || c < 0) {
				pacific = true;
				return;
			}
			if (r >= ROWS || c >= COLS) {
				atlantic = true;
				return;
			}
			if (heights[r][c] > prevVal) {
				return;
			}

			int tmp = heights[r][c];
			heights[r][c] = Integer.MAX_VALUE;
			for (int[] dir : directions) {
				dfs(heights, r + dir[0], c + dir[1], tmp);
				if (pacific && atlantic) {
					break;
				}
			}
			heights[r][c] = tmp;
		}
	}

	/**
	 * Time complexity: O(m∗n) Space complexity: O(m∗n) Where m is the number of rows and n is the number of columns.
	 */
	public static class SolutionUsingDfs {
		private final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

		public List<List<Integer>> pacificAtlantic(int[][] heights) {
			int ROWS = heights.length, COLS = heights[0].length;
			boolean[][] pac = new boolean[ROWS][COLS];
			boolean[][] atl = new boolean[ROWS][COLS];

			for (int c = 0; c < COLS; c++) {
				dfs(0, c, pac, heights);
				dfs(ROWS - 1, c, atl, heights);
			}
			for (int r = 0; r < ROWS; r++) {
				dfs(r, 0, pac, heights);
				dfs(r, COLS - 1, atl, heights);
			}

			List<List<Integer>> res = new ArrayList<>();
			for (int r = 0; r < ROWS; r++) {
				for (int c = 0; c < COLS; c++) {
					if (pac[r][c] && atl[r][c]) {
						res.add(Arrays.asList(r, c));
					}
				}
			}
			return res;
		}

		private void dfs(int r, int c, boolean[][] ocean, int[][] heights) {
			ocean[r][c] = true;
			for (int[] d : directions) {
				int nr = r + d[0], nc = c + d[1];
				if (nr >= 0 && nr < heights.length && nc >= 0 && nc < heights[0].length && !ocean[nr][nc] && heights[nr][nc] >= heights[r][c]) {
					dfs(nr, nc, ocean, heights);
				}
			}
		}
	}

	/**
	 * Time complexity: O(m∗n) Space complexity: O(m∗n) Where m is the number of rows and n is the number of columns.
	 */
	public static class SolutionUsingBfs {
		private final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

		public List<List<Integer>> pacificAtlantic(int[][] heights) {
			int ROWS = heights.length, COLS = heights[0].length;
			boolean[][] pac = new boolean[ROWS][COLS];
			boolean[][] atl = new boolean[ROWS][COLS];

			Queue<int[]> pacQueue = new LinkedList<>();
			Queue<int[]> atlQueue = new LinkedList<>();

			for (int c = 0; c < COLS; c++) {
				pacQueue.add(new int[] { 0, c });
				atlQueue.add(new int[] { ROWS - 1, c });
			}
			for (int r = 0; r < ROWS; r++) {
				pacQueue.add(new int[] { r, 0 });
				atlQueue.add(new int[] { r, COLS - 1 });
			}

			bfs(pacQueue, pac, heights);
			bfs(atlQueue, atl, heights);

			List<List<Integer>> res = new ArrayList<>();
			for (int r = 0; r < ROWS; r++) {
				for (int c = 0; c < COLS; c++) {
					if (pac[r][c] && atl[r][c]) {
						res.add(Arrays.asList(r, c));
					}
				}
			}
			return res;
		}

		private void bfs(Queue<int[]> q, boolean[][] ocean, int[][] heights) {
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				int r = cur[0], c = cur[1];
				ocean[r][c] = true;
				for (int[] d : directions) {
					int nr = r + d[0], nc = c + d[1];
					if (nr >= 0 && nr < heights.length && nc >= 0 && nc < heights[0].length && !ocean[nr][nc] && heights[nr][nc] >= heights[r][c]) {
						q.add(new int[] { nr, nc });
					}
				}
			}
		}
	}
}
