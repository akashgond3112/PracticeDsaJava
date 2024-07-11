package main.dsa.nonlinear.graph.learning;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*
Number of Distinct Islands
Difficulty: MediumAccuracy: 62.29%Submissions: 73K+Points: 4
Given a boolean 2D matrix grid of size n * m. You have to find the number of distinct islands where a group of connected 1s (horizontally or vertically) forms an island. Two islands are considered to be distinct if and only if one island is not equal to another (not rotated or reflected).

Example 1:

Input:
grid[][] = {{1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1},
            {0, 0, 0, 1, 1}}
Output:
1
Explanation:
grid[][] = {{1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1},
            {0, 0, 0, 1, 1}}
Same colored islands are equal.
We have 2 equal islands, so we
have only 1 distinct island.

Example 2:
Input:
grid[][] = {{1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1},
            {1, 1, 0, 1, 1}}
Output:
3
Explanation:
grid[][] = {{1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1},
            {1, 1, 0, 1, 1}}
Same colored islands are equal.
We have 4 islands, but 2 of them
are equal, So we have 3 distinct islands.

Your Task:

You don't need to read or print anything. Your task is to complete the function countDistinctIslands() which takes the grid as an input parameter and returns the total number of distinct islands.

Expected Time Complexity: O(n * m)
Expected Space Complexity: O(n * m)

Constraints:
1 ≤ n, m ≤ 500
grid[i][j] == 0 or grid[i][j] == 1
*/

public class NumberOfDistinctIslands {

	static final int[] delRow = { -1, 0, +1, 0 };
	static final int[] delCol = { 0, 1, 0, -1 };

	public static class Pair {
		int first;
		int second;

		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}

		@Override
		public String toString() {
			return "Pair{" + "first=" + first + ", second=" + second + '}';
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Pair pair = (Pair) o;
			//			return first == pair.first && second == pair.second;
			return (Integer.toString(first) + Integer.toString(second)).equals(
					Integer.toString(pair.first) + Integer.toString(pair.second));
		}

		@Override
		public int hashCode() {
			return Objects.hash(first, second);
		}
	}


	int countDistinctIslands(int[][] grid) {
		// Your Code here
		Set<ArrayList<Pair>> set = new HashSet<>();
		int n = grid.length;
		int m = grid[0].length;
		int[][] visited = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1 && visited[i][j] == 0) {
					ArrayList<Pair> list = new ArrayList<>();
					dfs(i, j, visited, grid, list, i, j);
					set.add(list);
				}
			}
		}
		return set.size();
	}

	private void dfs(int i, int j, int[][] visited, int[][] grid, ArrayList<Pair> list, int i1, int j1) {
		visited[i][j] = 1;
		list.add(new Pair(i - i1, j - j1));
		int n = grid.length;
		int m = grid[0].length;

		for (int k = 0; k < 4; k++) {
			int newRow = i + delRow[k];
			int newCol = j + delCol[k];

			if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && visited[newRow][newCol] == 0 && grid[newRow][newCol] == 1) {
				dfs(newRow, newCol, visited, grid, list, newRow, newCol);
			}
		}

	}

	public static void main(String[] args) {

		NumberOfDistinctIslands islands = new NumberOfDistinctIslands();
		int[][] grid = { { 1, 1, 0, 1, 1 }, { 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1 }, { 1, 1, 0, 1, 1 } };

		System.out.println(islands.countDistinctIslands(grid));
	}
}
