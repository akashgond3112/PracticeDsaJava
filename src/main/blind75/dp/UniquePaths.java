package main.blind75.dp;

import java.util.Arrays;

/**
 *62. Unique Paths
 * Medium
 * Topics
 * Companies
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: m = 3, n = 7
 * Output: 28
 * Example 2:
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 *
 *
 * Constraints:
 *
 * 1 <= m, n <= 100 */
public class UniquePaths {

	/**
	 * Time complexity: O(2 m+n )
	 * Space complexity: O(m+n) Where m is the number of rows and
	 * n is the number of columns.
	 */
	static public class SolutionUsingRecursion {
		public int uniquePaths(int m, int n) {
			return dfs(0, 0, m, n);
		}

		public int dfs(int i, int j, int m, int n) {
			if (i == (m - 1) && j == (n - 1)) {
				return 1;
			}
			if (i >= m || j >= n) return 0;
			return dfs(i, j + 1, m, n) +
					dfs(i + 1, j, m, n);
		}
	}

	/**
	 * Time complexity: O(m*n)
	 * Space complexity: O(m*n) Where m is the number of rows and
	 * n is the number of columns.
	 */
	public static class SolutionUsingTopDownDP {
		int[][] memo;
		public int uniquePaths(int m, int n) {
			memo = new int[m][n];
			for(int[] it : memo) {
				Arrays.fill(it, -1);
			}
			return dfs(0, 0, m, n);
		}

		public int dfs(int i, int j, int m, int n) {
			if (i == (m - 1) && j == (n - 1)) {
				return 1;
			}
			if (i >= m || j >= n) return 0;
			if (memo[i][j] != -1) {
				return memo[i][j];
			}
			return memo[i][j] = dfs(i, j + 1, m, n) +
					dfs(i + 1, j, m, n);
		}
	}

	/**
	 * Time complexity: O(m*n)
	 * Space complexity: O(m*n) Where m is the number of rows and
	 * n is the number of columns.
	 */
	public static class SolutionUsingBottomUpDP {
		public int uniquePaths(int m, int n) {
			int[][] dp = new int[m + 1][n + 1];
			dp[m - 1][n - 1] = 1;

			for (int i = m - 1; i >= 0; i--) {
				for (int j = n - 1; j >= 0; j--) {
					dp[i][j] += dp[i + 1][j] + dp[i][j + 1];
				}
			}

			return dp[0][0];
		}
	}

	/**
	 * Time complexity: O(m*n)
	 * Space complexity: O(n) Where m is the number of rows and
	 * n is the number of columns.
	 */
	public static class SolutionUsingSpaceOptimized {
		public int uniquePaths(int m, int n) {
			int[] row = new int[n];
			Arrays.fill(row, 1);

			for (int i = 0; i < m - 1; i++) {
				int[] newRow = new int[n];
				Arrays.fill(newRow, 1);
				for (int j = n - 2; j >= 0; j--) {
					newRow[j] = newRow[j + 1] + row[j];
				}
				row = newRow;
			}
			return row[0];
		}
	}
}
