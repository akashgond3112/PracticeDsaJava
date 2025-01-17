package main.blind75.dp;

import java.util.Arrays;

/**
 * 198. House Robber
 * Medium
 * Topics
 * Companies
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 *
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400*/
public class HouseRobber {

	/**
	 * Time complexity: O(2^n ) Space complexity: O(n)
	 */
	public static class SolutionUsingRecursion {
		public int rob(int[] nums) {
			return dfs(nums, 0);
		}

		private int dfs(int[] nums, int i) {
			if (i >= nums.length) {
				return 0;
			}
			return Math.max(dfs(nums, i + 1),
					nums[i] + dfs(nums, i + 2));
		}
	}

	/**
	 * Time complexity: O(n ) Space complexity: O(n)
	 */
	public static class SolutionDynamicProgrammingTopDown {
		private int[] memo;

		public int rob(int[] nums) {
			memo = new int[nums.length];
			Arrays.fill(memo, -1);
			return dfs(nums, 0);
		}

		private int dfs(int[] nums, int i) {
			if (i >= nums.length) {
				return 0;
			}
			if (memo[i] != -1) {
				return memo[i];
			}
			memo[i] = Math.max(dfs(nums, i + 1),
					nums[i] + dfs(nums, i + 2));
			return memo[i];
		}
	}

	/**
	 * Time complexity: O(n ) Space complexity: O(n)
	 */
	public static class SolutionDynamicProgrammingBottomUp {
		public int rob(int[] nums) {
			if (nums.length == 0) return 0;
			if (nums.length == 1) return nums[0];

			int[] dp = new int[nums.length];
			dp[0] = nums[0];
			dp[1] = Math.max(nums[0], nums[1]);

			for (int i = 2; i < nums.length; i++) {
				dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
			}

			return dp[nums.length - 1];
		}
	}

	/**
	 * Time complexity: O(n ) Space complexity: O(1)
	 */
	public static class SolutionDynamicProgrammingMemoization {
		public int rob(int[] nums) {
			int rob1 = 0, rob2 = 0;

			for (int num : nums) {
				int temp = Math.max(num + rob1, rob2);
				rob1 = rob2;
				rob2 = temp;
			}
			return rob2;
		}
	}
}
