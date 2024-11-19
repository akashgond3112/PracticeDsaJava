package main.dsa.dp.grid;

import java.util.Arrays;

/*
Problem statement
Ninjaland is a country in the shape of a 2-Dimensional grid 'GRID', with 'N' rows and 'M' columns. Each point in the grid has some cost associated with it.



Find a path from top left i.e. (0, 0) to the bottom right i.e. ('N' - 1, 'M' - 1) which minimizes the sum of the cost of all the numbers along the path. You need to tell the minimum sum of that path.



Note:
You can only move down or right at any point in time.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 100
1 <= N, M <= 10^2
1 <= GRID[i][j] <= 10^5

Where 'GRID[i][j]' denotes the value of the cell in the matrix.

Time limit: 1 sec
Sample Input 1:
2
2 3
5 9 6
11 5 2
1 1
5
Sample Output 1:
21
5
Explanation For Sample Output 1:
In test case 1, Consider a grid of 2*3:

For this the grid the path with minimum value is (0,0) -> (0,1) -> (1,1) -> (1,2). And the sum along this path is 5 + 9 +5 + 2 = 21. So the ans is 21.

In test case 2, The given grid is:

For this the grid the path with minimum value is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2).The sum along this path is 1 + 2 + 3 + 4 + 9 = 19.
Sample Input 2:
2
2 2
5 6
1 2
3 3
1 2 3
4 5 4
7 5 9
Sample Output 2:
8
19
Explanation For Sample Output 2:
In test case 1, For this the grid the path with minimum value is (0,0) -> (1,0) -> (1,1). The sum along this path is 5 + 1 + 2 = 8.

In test case 2, The given grid is:

For this the grid the path with minimum value is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2).The sum along this path is 1 + 2 + 3 + 4 + 9 = 19.
*/
public class MinimumPathSum {

	public static int minSumPathUsingRecursionUsingTabularOptimal(int n, int m, int[][] grid) {
		// Write your code here.
		int[] prev = new int[m];
		for (int i = 0; i < n; i++) {
			int[] cur = new int[m]; // Array to store results for the current row

			for (int j = 0; j < m; j++) {
				if (i == 0 && j == 0)
					cur[j] = grid[i][j]; // If we're at the top-left cell, the minimum sum is its value
				else {
					int up = Integer.MAX_VALUE, left = Integer.MAX_VALUE;

					if (i > 0)
						up = prev[j]; // Value from above
					if (j > 0)
						left = cur[j - 1]; // Value from the left

					// Add the current grid value to the minimum of up and left
					cur[j] = grid[i][j] + Math.min(up, left);
				}
			}
			prev = cur;
		}
		// The final result is stored in the bottom-right cell of the DP matrix
		return prev[m - 1];

	}

	public static int minSumPathUsingTabular(int n, int m, int[][] grid, int[][] dp) {
		// Iterate over the grid to fill the DP table
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == 0 && j == 0) {
					dp[i][j] = grid[i][j]; // Top-left corner, just take the grid value
				} else {
					int up = Integer.MAX_VALUE, left = Integer.MAX_VALUE;

					if (i > 0)
						up = dp[i - 1][j]; // Value from above
					if (j > 0)
						left = dp[i][j - 1]; // Value from the left

					// Add the current grid value to the minimum of up and left
					dp[i][j] = grid[i][j] + Math.min(up, left);
				}
			}
		}

		// The result will be stored in the bottom-right corner of the dp array
		return dp[n - 1][m - 1];
	}

	public static int minSumPathUsingRecursionUsingMemo(int n, int m, int[][] grid, int[][] dp) {
		// Write your code here.

		if (m == 0 && n == 0)
			return grid[0][0];
		if (m < 0 || n < 0)
			return (int) Math.pow(10, 9);


		if (dp[n][m] != -1) {
			return dp[n][m];
		}

		int fromUp = grid[n][m] + minSumPathUsingRecursionUsingMemo(n - 1, m, grid, dp);
		int fromLeft = grid[n][m] + minSumPathUsingRecursionUsingMemo(n, m - 1, grid, dp);

		dp[n][m] = Math.min(fromUp, fromLeft);

		return dp[n][m];
	}

	public static int minSumPathUsingRecursion(int n, int m, int[][] grid) {
		// Base cases
		if (m == 0 && n == 0)
			return grid[n][m]; // If at the top-left corner, return its value
		if (m < 0 || n < 0)
			return (int) Math.pow(10, 9);
		; // Out of bounds

		// Recursive cases: moving from up or from the left
		int fromUp = grid[n][m] + minSumPathUsingRecursion(n - 1, m, grid);
		int fromLeft = grid[n][m] + minSumPathUsingRecursion(n, m - 1, grid);

		// Return the minimum path sum
		return Math.min(fromLeft, fromUp);
	}

	public static int getMinSumPathUsingRecursion(int n, int m, int[][] grid) {
		// m and n are the dimensions, so pass m-1 and n-1 for 0-indexed recursion
		return minSumPathUsingRecursion(n - 1, m - 1, grid);
	}

	public static int getMinSumPathUsingMemo(int m, int n, int[][] grid) {

		int[][] dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			Arrays.fill(dp[i], -1);
		}

		return minSumPathUsingRecursionUsingMemo(m - 1, n - 1, grid, dp);
	}

	public static int getMinSumPathUsingTabular(int n, int m, int[][] grid) {

		int[][] dp = new int[n][m];

		return minSumPathUsingTabular(n, m, grid, dp);
	}

	public static void main(String[] args) {
		int[][] grid = { { 5, 9, 6 }, { 11, 5, 2 } };
		int n = grid.length;
		int m = grid[0].length;

		System.out.println(getMinSumPathUsingRecursion(n, m, grid));
		System.out.println(getMinSumPathUsingMemo(n, m, grid));
		System.out.println(getMinSumPathUsingTabular(n, m, grid));
		System.out.println(minSumPathUsingRecursionUsingTabularOptimal(n, m, grid));
	}
}
