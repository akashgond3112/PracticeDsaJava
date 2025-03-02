package main.dsa.nonlinear.graph.Dijkstra;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
Path With Minimum Effort
Difficulty: MediumAccuracy: 53.13%Submissions: 46K+Points: 4
You are a hiker preparing for an upcoming hike. You are given heights[][], a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find the route with minimum effort.

Note: A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Example 1:

Input:
row = 3
columns = 3
heights = [[1,2,2],[3,8,2],[5,3,5]]
Output:
2
Explanation:
The route 1->3->5->3->5 has a maximum absolute difference of 2 in consecutive cells. This is better than the route 1->2->2->2->5, where the maximum absolute difference is 3.
Example 2:

Input:
row = 2
columns = 2
heights = [[7,7],[7,7]]
Output:
0
Explanation:
Any route from the top-left cell to the bottom-right cell has a maximum absolute difference of 0 in consecutive cells.
Your Task:
You don't need to read input or print anything. Your task is to complete the function MinimumEffort() which takes intergers rows, columns, and the 2D array heights[][]  and returns the minimum effort required to travel from the top-left cell to the bottom-right cell.

Expected Time Complexity: O(rowsxcolumns)
Expected Space Complexity: O(rowsxcolumns)

Constraints:
1 <= rows, columns <= 100
rows == heights.length
columns == heights[i].length
0 <= heights[i][j] <= 106


*/
public class PathWithMinimumEffort {

	static class Tuple {
		int distance;
		int row;
		int col;

		public Tuple(int distance, int row, int col) {
			this.distance = distance;
			this.row = row;
			this.col = col;
		}
	}

	public static int MinimumEffort(int[][] heights) {

		PriorityQueue<Tuple> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.distance));

		int n = heights.length;
		int m = heights[0].length;
		int[][] distances = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				distances[i][j] = (int) 1e9;
			}
		}

		distances[0][0] = 0;
		pq.add(new Tuple(0, 0, 0));
		int[] delRow = { -1, 0, +1, 0 };
		int[] delCol = { 0, +1, 0, -1 };
		while (!pq.isEmpty()) {
			Tuple t = pq.poll();
			int distance = t.distance;
			int row = t.row;
			int col = t.col;

			if (row == n - 1 && col == m - 1)
				return distance;

			for (int k = 0; k < 4; k++) {
				int newRow = row + delRow[k];
				int newCol = col + delCol[k];

				if (newRow < n && newRow >= 0 && newCol < m && newCol >= 0) {
					int newDistance = Math.max(Math.abs(heights[row][col] - heights[newRow][newCol]), distance);
					if (newDistance < distances[newRow][newCol]) {
						distances[newRow][newCol] = newDistance;
						pq.add(new Tuple(newDistance, newRow, newCol));
					}


				}
			}
		}
		return 0;
	}

	public static void main(String[] args) {


		int row = 3;
		int columns = 3;
		int[][] heights = { { 1, 2, 2 }, { 3, 8, 2 }, { 5, 3, 5 } };

		System.out.println(MinimumEffort(heights));

	}
}
