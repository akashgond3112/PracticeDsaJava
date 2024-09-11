package main.dynamic.programming;

import java.util.Arrays;

/*
Problem statement
You are present at point ‘A’ which is the top-left cell of an M X N matrix, your destination is point ‘B’, which is the bottom-right cell of the same matrix. Your task is to find the total number of unique paths from point ‘A’ to point ‘B’.In other words, you will be given the dimensions of the matrix as integers ‘M’ and ‘N’, your task is to find the total number of unique paths from the cell MATRIX[0][0] to MATRIX['M' - 1]['N' - 1].

To traverse in the matrix, you can either move Right or Down at each step. For example in a given point MATRIX[i] [j], you can move to either MATRIX[i + 1][j] or MATRIX[i][j + 1].

Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 ≤ T ≤ 100
1 ≤ M ≤ 15
1 ≤ N ≤ 15

Where ‘M’ is the number of rows and ‘N’ is the number of columns in the matrix.

Time limit: 1 sec
Sample Input 1:
2
2 2
1 1
Sample Output 1:
2
1
Explanation of Sample Output 1:
In test case 1, we are given a 2 x 2 matrix, to move from matrix[0][0] to matrix[1][1] we have the following possible paths.

Path 1 = (0, 0) -> (0, 1) -> (1, 1)
Path 2 = (0, 0) -> (1, 0) -> (1, 1)

Hence a total of 2 paths are available, so the output is 2.

In test case 2, we are given a 1 x 1 matrix, hence we just have a single cell which is both the starting and ending point. Hence the output is 1.
Sample Input 2:
2
3 2
1 6
Sample Output 2:
3
1
Explanation of Sample Output 2:
In test case 1, we are given a 3 x 2 matrix, to move from matrix[0][0] to matrix[2][1] we have the following possible paths.

Path 1 = (0, 0) -> (0, 1) -> (1, 1) -> (2, 1)
Path 2 = (0, 0) -> (1, 0) -> (2, 0) -> (2, 1)
Path 3 =  (0, 0) -> (1, 0) -> (1, 1) -> (2, 1)

Hence, a total of 3 paths are available, so the output is 3.

In test case 2, we are given a 1 x 6 matrix, hence we just have a single row to traverse and thus total path will be 1.
*/
public class UniquePaths {

	public static int uniquePathsRecursionUsingTabularSpaceOptimal(int m, int n) {
		int[] prev = new int[n]; // Array to store results from the previous row

		for (int i = 0; i < m; i++) {
			int[] cur = new int[n]; // Array to store results for the current row
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0) {
					cur[j] = 1; // Start point
				} else {
					int fromLeft = (j > 0) ? cur[j - 1] : 0; // Paths from the left
					int fromUp = prev[j]; // Paths from the top
					cur[j] = fromLeft + fromUp;
				}
			}
			prev = cur; // Update prev to the current row after processing
		}
		return prev[n - 1]; // Return the result at the bottom-right corner
	}


	public static int uniquePathsRecursionUsingTabular(int m, int n, int[][] dp) {
		dp[0][0] = 1; // Starting point

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0) {
					continue; // Skip the start point
				}
				int fromUp = 0;
				int fromLeft = 0;
				if (i > 0)
					fromUp = dp[i - 1][j]; // Check the cell above
				if (j > 0)
					fromLeft = dp[i][j - 1]; // Check the cell to the left
				dp[i][j] = fromUp + fromLeft; // Sum of paths from top and left
			}
		}
		return dp[m - 1][n - 1]; // Return the result in the bottom-right corner
	}

	public static int uniquePathsRecursionUsingMemo(int m, int n, int[][] dp) {
		// Write your code here.
		if (m == 0 && n == 0)
			return 1;
		if (m < 0 || n < 0)
			return 0;


		if (dp[m][n] != -1)
			return dp[m][n];

		int up = uniquePathsRecursion(m - 1, n);
		int down = uniquePathsRecursion(m, n - 1);
		dp[m][n] = up + down;
		return dp[m][n];
	}

	public static int uniquePathsRecursion(int m, int n) {
		// Write your code here.
		if (m == 0 && n == 0)
			return 1;
		if (m < 0 || n < 0)
			return 0;

		int up = uniquePathsRecursion(m - 1, n);
		int down = uniquePathsRecursion(m, n - 1);
		return up + down;
	}

	public static int getUniquePaths(int m, int n) {
		return uniquePathsRecursion(m - 1, n - 1);
	}

	public static int getUniquePathsMemo(int m, int n) {

		int[][] dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			Arrays.fill(dp[i], -1);
		}

		return uniquePathsRecursionUsingMemo(m - 1, n - 1, dp);
	}

	public static int getUniquePathsTabular(int m, int n) {

		int[][] dp = new int[m][n];

		return uniquePathsRecursionUsingTabular(m, n, dp);
	}


	public static void main(String[] args) {
		System.out.println(getUniquePaths(3, 2));
		System.out.println(getUniquePathsMemo(3, 2));
		System.out.println(getUniquePathsTabular(3, 2));
		System.out.println(uniquePathsRecursionUsingTabularSpaceOptimal(3, 2));
	}
}
