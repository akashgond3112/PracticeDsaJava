package main.meta.medium;

import java.util.LinkedList;
import java.util.Queue;

/*
Find the number of islands
Difficulty: MediumAccuracy: 42.12%Submissions: 186K+Points: 4
Given a grid of size n*m (n is the number of rows and m is the number of columns in the grid) consisting of '0's (Water) and '1's(Land). Find the number of islands.

Note: An island is either surrounded by water or boundary of grid and is formed by connecting adjacent lands horizontally or vertically or diagonally i.e., in all 8 directions.

Example 1:

Input:
grid = {{0,1},{1,0},{1,1},{1,0}}
Output:
1

Explanation:
The grid is-
0 1
1 0
1 1
1 0
All lands are connected.


Example 2:
Input:
grid = {{0,1,1,1,0,0,0},{0,0,1,1,0,1,0}}
Output:
2

Explanation:
The grid is-
0 1 1 1 0 0 0
0 0 1 1 0 1 0

There are two islands :- one is colored in blue
and other in orange.
Your Task:
You don't need to read or print anything. Your task is to complete the function numIslands() which takes the grid as an input parameter and returns the total number of islands.

Expected Time Complexity: O(n*m)
Expected Space Complexity: O(n*m)

Constraints:
1 ≤ n, m ≤ 500
* */

/**
 * A utility class to solve the Number of Islands problem using both BFS and DFS approaches.
 * The problem involves counting the number of islands in a 2D grid where '1' represents land
 * and '0' represents water.
 */
public class NumberOfIsland {

	/**
	 * Represents a pair of coordinates (row, column) in the grid.
	 */
	public static class Pair {
		int first;
		int second;

		/**
		 * Constructs a new Pair with given coordinates.
		 * @param first the row coordinate
		 * @param second the column coordinate
		 */
		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}

	/**
	 * Performs Breadth-First Search starting from a given cell to mark all connected land cells.
	 *
	 * Time Complexity: O(8 * N) ≈ O(N) where N is the number of cells in the connected component,
	 * as each cell is processed once and for each cell, we check 8 neighbors.
	 * Space Complexity: O(N) where N is the size of the queue which can store up to all cells
	 * in the worst case.
	 *
	 * @param row starting row coordinate
	 * @param col starting column coordinate
	 * @param visited matrix to track visited cells
	 * @param grid input grid representing the map
	 */
	static void bfs(int row, int col, int[][] visited, char[][] grid) {
		visited[row][col] = 1;
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(row, col));
		int n = grid.length;
		int m = grid[0].length;

		while (!queue.isEmpty()) {
			int r = queue.peek().first;
			int c = queue.peek().second;
			queue.remove();

			for (int k = -1; k <= 1; k++) {
				for (int l = -1; l <= 1; l++) {
					int newRow = r + k;
					int newCol = c + l;

					if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m
							&& grid[newRow][newCol] == '1' && visited[newRow][newCol] == 0) {
						visited[newRow][newCol] = 1;
						queue.add(new Pair(newRow, newCol));
					}
				}
			}
		}
	}

	/**
	 * Performs Depth-First Search starting from a given cell to mark all connected land cells.
	 *
	 * Time Complexity: O(8 * N) ≈ O(N) where N is the number of cells in the connected component,
	 * as each cell is processed once and for each cell, we check 8 neighbors.
	 * Space Complexity: O(N) in the worst case due to recursion stack.
	 *
	 * @param row starting row coordinate
	 * @param col starting column coordinate
	 * @param visited matrix to track visited cells
	 * @param grid input grid representing the map
	 */
	static void dfs(int row, int col, int[][] visited, char[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		visited[row][col] = 1;

		for (int k = -1; k <= 1; k++) {
			for (int l = -1; l <= 1; l++) {
				int newRow = row + k;
				int newCol = col + l;

				if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m
						&& grid[newRow][newCol] == '1' && visited[newRow][newCol] == 0) {
					dfs(newRow, newCol, visited, grid);
				}
			}
		}
	}

	/**
	 * Counts the number of islands in the given grid using either BFS or DFS.
	 * An island is a group of '1's connected horizontally, vertically, or diagonally.
	 *
	 * Time Complexity: O(N * M) where N and M are the dimensions of the grid.
	 * Each cell is visited exactly once.
	 * Space Complexity: O(N * M) for the visited array and recursion stack/queue.
	 *
	 * @param grid the input grid where '1' represents land and '0' represents water
	 * @param useDFS boolean flag to choose between DFS (true) or BFS (false)
	 * @return the number of islands in the grid
	 */
	static int numberOfIslands(char[][] grid, boolean useDFS) {
		int n = grid.length;
		int m = grid[0].length;
		int[][] visited = new int[n][m];
		int count = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visited[i][j] == 0 && grid[i][j] == '1') {
					if (useDFS) {
						dfs(i, j, visited, grid);
					} else {
						bfs(i, j, visited, grid);
					}
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		char[][] grid = {
				{'1', '1', '0', '0'},
				{'1', '1', '0', '0'},
				{'0', '0', '1', '0'},
				{'0', '0', '0', '1'}
		};

		System.out.println("Number of islands (using BFS): " + numberOfIslands(grid, false));
		System.out.println("Number of islands (using DFS): " + numberOfIslands(grid, true));
	}
}
