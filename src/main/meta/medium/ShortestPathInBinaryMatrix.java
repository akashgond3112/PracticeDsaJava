package main.meta.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1091. Shortest Path in Binary Matrix
 * Medium
 * Topics
 * Companies
 * Hint
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
 * <p>
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 * <p>
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 * <p>
 * Example 1:
 * Input: grid = [[0,1],[1,0]]
 * Output: 2
 * <p>
 * Example 2:
 * Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
 * Output: 4
 * Example 3:
 * <p>
 * Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
 * Output: -1
 * <p>
 * Constraints:
 * <p>
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] is 0 or 1*/
public class ShortestPathInBinaryMatrix {

	static public class Solution {
		/**
		 * Finds the shortest path from top-left (0,0) to bottom-right (N-1,N-1) in a binary matrix.
		 * A 0 represents a passable cell, and 1 represents a blocked cell.
		 * Movement is allowed in 8 directions (horizontal, vertical, and diagonal).
		 * <p>
		 * Algorithm:
		 * 1. First checks if start or end points are blocked (value 1)
		 * 2. Uses BFS (Breadth First Search) with a queue to explore cells in order of distance
		 * 3. For each cell, explores all 8 adjacent cells if they are:
		 *    - Within grid bounds
		 *    - Unblocked (value 0)
		 *    - Not previously visited
		 * 4. Path length is tracked for each cell and incremented at each step
		 * <p>
		 * Time Complexity: O(N²) where N is the grid dimension
		 *   - In worst case, we might need to visit all cells in the grid
		 *   - For each cell, we check 8 directions
		 *   - Total: O(N² * 8) = O(N²)
		 * <p>
		 * Space Complexity: O(N²)
		 *   - visited array: O(N²)
		 *   - queue: O(N²) in worst case where all cells are added
		 *
		 * @param grid N x N binary matrix where 0 represents passable cell and 1 represents blocked cell
		 * @return Length of shortest path from (0,0) to (N-1,N-1), or -1 if no path exists
		 */
		public int shortestPathBinaryMatrix(int[][] grid) {
			int N = grid.length;
			if (grid[0][0] == 1 || grid[N - 1][N - 1] == 1)
				return -1;

			/*
			 * Direction Vectors:
			 * {0,1}   -> Right     (→)
			 * {1,0}   -> Down      (↓)
			 * {0,-1}  -> Left      (←)
			 * {-1,0}  -> Up        (↑)
			 * {1,1}   -> Down-Right (↘)
			 * {-1,-1} -> Up-Left    (↖)
			 * {1,-1}  -> Down-Left  (↙)
			 * {-1,1}  -> Up-Right   (↗)
			 * */
			int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { -1, -1 }, { 1, -1 },
					{ -1, 1 } };
			boolean[][] visit = new boolean[N][N];

			Queue<int[]> q = new LinkedList<>();
			q.offer(new int[] { 0, 0, 1 });  // [row, col, path_length]
			visit[0][0] = true;

			while (!q.isEmpty()) {
				int[] cell = q.poll();
				int row = cell[0], col = cell[1], length = cell[2];

				if (row == N - 1 && col == N - 1)
					return length;

				for (int[] d : directions) {
					int nr = row + d[0], nc = col + d[1];
					if (nr >= 0 && nc >= 0 && nr < N && nc < N && grid[nr][nc] == 0 && !visit[nr][nc]) {
						q.offer(new int[] { nr, nc, length + 1 });
						visit[nr][nc] = true;
					}
				}
			}
			return -1;
		}
	}
}
