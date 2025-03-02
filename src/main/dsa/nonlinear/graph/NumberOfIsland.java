package main.dsa.nonlinear.graph;

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

public class NumberOfIsland {

	public static class Pair {
		int first;
		int second;

		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}

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

					if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && grid[newRow][newCol] == '1' && visited[newRow][newCol] == 0) {
						visited[newRow][newCol] = 1;
						queue.add(new Pair(newRow, newCol));
					}
				}
			}
		}
	}

	static int numberOfIslands(char[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[][] visited = new int[n][m];
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visited[i][j] == 0 && grid[i][j] == '1') {
					bfs(i, j, visited, grid);
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {

		char[][] grid = new char[][] { { 0, 1 }, { 1, 0 }, { 1, 1 }, { 1, 0 } };
		System.out.println(numberOfIslands(grid));
	}
}
