package main.dsa.nonlinear.graph.learning;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
Difficulty: MediumAccuracy: 47.7%Submissions: 84K+Points: 4
Given a binary grid of n*m. Find the distance of the nearest 1 in the grid for each cell.
The distance is calculated as |i1 - i2| + |j1 - j2|, where i1, j1 are the row number and column number of the current cell, and i2, j2
are the row number and column number of the nearest cell having value 1. There should be the least one 1 in the grid.

Example 1:

Input: grid = {{0,1,1,0},{1,1,0,0},{0,0,1,1}}
Output: {{1,0,0,1},{0,0,1,1},{1,1,0,0}}
Explanation: The grid is-
0 1 1 0
1 1 0 0
0 0 1 1
0's at (0,0), (0,3), (1,2), (1,3), (2,0) and
(2,1) are at a distance of 1 from 1's at (0,1),
(0,2), (0,2), (2,3), (1,0) and (1,1)
respectively.

Example 2:

Input: grid = {{1,0,1},{1,1,0},{1,0,0}}
Output: {{0,1,0},{0,0,1},{0,1,2}}
Explanation: The grid is-
1 0 1
1 1 0
1 0 0
0's at (0,1), (1,2), (2,1) and (2,2) are at a
distance of 1, 1, 1 and 2 from 1's at (0,0),
(0,2), (2,0) and (1,1) respectively.

Your Task:
You don't need to read or print anything, Your task is to complete the function nearest() which takes the grid as an input parameter and returns
a matrix of the same dimensions where the value at index (i, j) in the resultant matrix signifies the minimum distance of 1 in the matrix from grid[i][j].

Expected Time Complexity: O(n*m)
Expected Auxiliary Space: O(n*m)

Constraints:
1 ≤ n, m ≤ 500
* */
public class DistanceOfNearestCellHaving1 {

	public class NearestCell {
		int first;
		int second;
		int distance;

		public NearestCell(int first, int second, int distance) {
			this.first = first;
			this.second = second;
			this.distance = distance;
		}
	}

	public int[][] distanceOfNearestCellHaving1(int[][] adjacencyList) {

		int n = adjacencyList.length;
		int m = adjacencyList[0].length;

		int[][] visited = new int[n][m];
		int[][] result = new int[n][m];

		Queue<NearestCell> queue = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (adjacencyList[i][j] == 1) {
					queue.add(new NearestCell(i, j, 0));
					visited[i][j] = 1;
				} else {
					visited[i][j] = 0;
				}
			}
		}

		int[] delRow = { -1, 0, +1, 0 };
		int[] delCol = { 0, +1, 0, -1 };

		while (!queue.isEmpty()) {
			int row = queue.peek().first;
			int col = queue.peek().second;
			int distance = queue.peek().distance;
			queue.remove();
			result[row][col] = distance;

			for (int k = 0; k < 4; k++) {
				int nRow = row + delRow[k];
				int nCol = col + delCol[k];

				if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && visited[nRow][nCol] == 0) {
					visited[nRow][nCol] = 1;
					queue.add(new NearestCell(nRow, nCol, distance + 1));
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {

		DistanceOfNearestCellHaving1 graph = new DistanceOfNearestCellHaving1();
		int[][] input = new int[][] { { 1, 0, 1 }, { 1, 1, 0 }, { 1, 0, 0 } };

		System.out.println(Arrays.deepToString(graph.distanceOfNearestCellHaving1(input)));
	}
}
