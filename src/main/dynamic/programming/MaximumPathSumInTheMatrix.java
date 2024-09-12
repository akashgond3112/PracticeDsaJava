package main.dynamic.programming;

import java.util.Arrays;

/*
Problem statement
You have been given an N*M matrix filled with integer numbers, find the maximum sum that can be obtained from a path starting from any cell in the first row to any cell in the last row.

From a cell in a row, you can move to another cell directly below that row, or diagonally below left or right. So from a particular cell (row, col), we can move in three directions i.e.

Down: (row+1,col)
Down left diagonal: (row+1,col-1)
Down right diagonal: (row+1, col+1)
Detailed explanation ( Input/output format, Notes, Images )
Constraints :
1 <= T <= 50
1 <= N <= 100
1 <= M <= 100
-10^4 <= matrix[i][j] <= 10^4

Where 'T' is the number of test cases.
Where 'N' is the number of rows in the given matrix, and 'M' is the number of columns in the given matrix.
And, matrix[i][j] denotes the value at (i,j) cell in the matrix.

Time Limit: 1sec
Input 1 :
2
4 4
1 2 10 4
100 3 2 1
1 1 20 2
1 2 2 1
3 3
10 2 3
3 7 2
8 1 5
Output 1 :
105
25
Explanation Of Input 1 :
In the first test case for the given matrix,

The maximum path sum will be 2->100->1->2, So the sum is 105(2+100+1+2).

In the second test case for the given matrix, the maximum path sum will be 10->7->8, So the sum is 25(10+7+8).
Input 2 :
2
3 3
1 2 3
9 8 7
4 5 6
4 6
10 10 2 -13 20 4
1 -9 -81 30 2 5
0 10 4 -79 2 -10
1 -5 2 20 -11 4
Output 2 :
17
74
Explanation Of Input 2 :
In the first test case for the given matrix, the maximum path sum will be 3->8->6, So the sum is 17(3+8+6).

In the second test case for the given matrix, the maximum path sum will be 20->30->4->20, So the sum is 74(20+30+4+20).
*/
public class MaximumPathSumInTheMatrix {

	public static int maxPathSumUsingTabularSpaceOptimal(int n, int m, int[][] matrix) {
		// Initialize the base case: the previous row is the same as the matrix's bottom row
		int[] prev = new int[m];
		System.arraycopy(matrix[n - 1], 0, prev, 0, m);

		// Fill the dp array from bottom to top
		for (int i = n - 2; i >= 0; i--) {
			int[] curr = new int[m]; // New current row for each iteration
			for (int j = 0; j < m; j++) {
				// Three potential moves (up, diagonal left, diagonal right)
				int fromDown = prev[j];

				int fromDownLeft = Integer.MIN_VALUE;
				if (j > 0) { // Ensure we are within bounds for diagonal left
					fromDownLeft = prev[j - 1];
				}

				int fromDownRight = Integer.MIN_VALUE;
				if (j < m - 1) { // Ensure we are within bounds for diagonal right
					fromDownRight = prev[j + 1];
				}

				// Take the maximum of the three possible moves
				curr[j] = matrix[i][j] + Math.max(fromDown, Math.max(fromDownLeft, fromDownRight));
			}
			prev = curr; // Update prev to be the current row for the next iteration
		}

		// The answer is the maximum value in the first row (now stored in 'prev')
		int maxSum = Integer.MIN_VALUE;
		for (int j = 0; j < m; j++) {
			maxSum = Math.max(maxSum, prev[j]);
		}

		return maxSum;
	}


	public static int maxPathSumUsingTabular(int n, int m, int[][] matrix, int[][] dp) {
		// Initialize the base case: bottom row of dp is the same as the matrix
		System.arraycopy(matrix[n - 1], 0, dp[n - 1], 0, m);

		// Fill the dp array from bottom to top
		for (int i = n - 2; i >= 0; i--) {
			for (int j = 0; j < m; j++) {
				// Three potential moves (up, diagonal left, diagonal right)
				int fromDown = dp[i + 1][j];

				int fromDownLeft = Integer.MIN_VALUE;
				if (j > 0) { // Ensure we are within bounds for diagonal left
					fromDownLeft = dp[i + 1][j - 1];
				}

				int fromDownRight = Integer.MIN_VALUE;
				if (j < m - 1) { // Ensure we are within bounds for diagonal right
					fromDownRight = dp[i + 1][j + 1];
				}

				// Take the maximum of the three possible moves
				dp[i][j] = matrix[i][j] + Math.max(fromDown, Math.max(fromDownLeft, fromDownRight));
			}
		}

		// The answer is the maximum value in the top row
		int maxSum = Integer.MIN_VALUE;
		for (int j = 0; j < m; j++) {
			maxSum = Math.max(maxSum, dp[0][j]);
		}

		return maxSum;
	}

	public static int maxPathSumUsingRecursionUsingMemo(int n, int m, int[][] matrix, int[][] dp) {

		// Base case: If we are in the top row, just return the matrix value at that point
		if (n == 0) {
			return matrix[n][m];
		}

		if (dp[n][m] != -1) {
			return dp[n][m];
		}

		// Initialize variables for three potential moves (up, diagonal left, diagonal right)
		int fromUp = matrix[n][m] + maxPathSumUsingRecursionUsingMemo(n - 1, m, matrix, dp);

		int fromUpLeft = Integer.MIN_VALUE;
		if (m > 0) { // Ensure we are within bounds for diagonal left
			fromUpLeft = matrix[n][m] + maxPathSumUsingRecursionUsingMemo(n - 1, m - 1, matrix, dp);
		}

		int fromUpRight = Integer.MIN_VALUE;
		if (m < matrix[0].length - 1) { // Ensure we are within bounds for diagonal right
			fromUpRight = matrix[n][m] + maxPathSumUsingRecursionUsingMemo(n - 1, m + 1, matrix, dp);
		}

		// Return the maximum path sum of the three possible paths
		dp[n][m] = Math.max(fromUp, Math.max(fromUpLeft, fromUpRight));
		return dp[n][m];
	}

	public static int maxPathSumUsingRecursion(int n, int m, int[][] matrix) {
		// Base case: If we are in the top row, just return the matrix value at that point
		if (n == 0) {
			return matrix[n][m];
		}

		// Initialize variables for three potential moves (up, diagonal left, diagonal right)
		int fromUp = matrix[n][m] + maxPathSumUsingRecursion(n - 1, m, matrix);

		int fromUpLeft = Integer.MIN_VALUE;
		if (m > 0) { // Ensure we are within bounds for diagonal left
			fromUpLeft = matrix[n][m] + maxPathSumUsingRecursion(n - 1, m - 1, matrix);
		}

		int fromUpRight = Integer.MIN_VALUE;
		if (m < matrix[0].length - 1) { // Ensure we are within bounds for diagonal right
			fromUpRight = matrix[n][m] + maxPathSumUsingRecursion(n - 1, m + 1, matrix);
		}

		// Return the maximum path sum of the three possible paths
		return Math.max(fromUp, Math.max(fromUpLeft, fromUpRight));
	}

	private static int getMaxSumUsingRecursion(int m, int n, int[][] matrix) {
		int maxSum = Integer.MIN_VALUE;
		// We need to start at the bottom row and try all columns for the max path
		for (int col = 0; col < m; col++) {
			maxSum = Math.max(maxSum, maxPathSumUsingRecursion(n - 1, col, matrix));
		}
		return maxSum;
	}

	private static int getMaxSumUsingRecursionUsingMemo(int m, int n, int[][] matrix, int[][] dp) {
		int maxSum = Integer.MIN_VALUE;
		// We need to start at the bottom row and try all columns for the max path
		for (int col = 0; col < m; col++) {
			maxSum = Math.max(maxSum, maxPathSumUsingRecursionUsingMemo(n - 1, col, matrix, dp));
		}
		return maxSum;
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 10, 4 }, { 100, 3, 2, 1 }, { 1, 1, 20, 2 }, { 1, 2, 2, 1 } };


		int n = matrix.length;
		int m = matrix[0].length;

		int[][] dp = new int[n][m];
		for (int k = 0; k < n; k++) {
			Arrays.fill(dp[k], -1);
		}
		System.out.println("Maximum Path Sum: " + getMaxSumUsingRecursion(m, n, matrix));
		System.out.println("Maximum Path Sum: " + getMaxSumUsingRecursionUsingMemo(m, n, matrix, dp));
		System.out.println("Maximum Path Sum: " + maxPathSumUsingTabular(m, n, matrix, dp));
		System.out.println("Maximum Path Sum: " + maxPathSumUsingTabularSpaceOptimal(m, n, matrix));
	}

}
