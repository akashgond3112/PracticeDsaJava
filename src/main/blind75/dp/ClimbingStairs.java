package main.blind75.dp;

/**
 * 70. Climbing Stairs Easy Topics Companies Hint You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2 Output: 2 Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps Example 2:
 *
 * Input: n = 3 Output: 3 Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 *
 * Constraints:
 *
 * 1 <= n <= 45
 */
public class ClimbingStairs {

	/**
	 * Time complexity: O(2^n ) Space complexity: O(n)
	 */
	public static class SolutionUsingRecursion {
		public int climbStairs(int n) {
			return dfs(n, 0);
		}

		public int dfs(int n, int i) {
			if (i >= n)
				return i == n ? 1 : 0;
			return dfs(n, i + 1) + dfs(n, i + 2);
		}
	}

	/**
	 * Time complexity: O(n ) Space complexity: O(n)
	 */
	public static class SolutionDynamicProgrammingTopDown {
		int[] cache;
		public int climbStairs(int n) {
			cache = new int[n];
			for (int i = 0; i < n; i++) {
				cache[i] = -1;
			}
			return dfs(n, 0);
		}

		public int dfs(int n, int i) {
			if (i >= n) return i == n ? 1 : 0;
			if (cache[i] != -1) return cache[i];
			return cache[i] = dfs(n, i + 1) + dfs(n, i + 2);
		}
	}

	/**
	 * Time complexity: O(n ) Space complexity: O(n)
	 */
	public static class SolutionDynamicProgrammingBottomUp {
		public int climbStairs(int n) {
			if (n <= 2) {
				return n;
			}
			int[] dp = new int[n + 1];
			dp[1] = 1;
			dp[2] = 2;
			for (int i = 3; i <= n; i++) {
				dp[i] = dp[i - 1] + dp[i - 2];
			}
			return dp[n];
		}
	}

	/**
	 * Time complexity: O(n ) Space complexity: O(1)
	 */
	public static class SolutionDynamicProgrammingMemoization {
		public int climbStairs(int n) {
			int one = 1, two = 1;

			for (int i = 0; i < n - 1; i++) {
				int temp = one;
				one = one + two;
				two = temp;
			}

			return one;
		}
	}
}
