package main.dsa.nonlinear.graph.learning;

import java.util.LinkedList;
import java.util.Queue;

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

	}
}
