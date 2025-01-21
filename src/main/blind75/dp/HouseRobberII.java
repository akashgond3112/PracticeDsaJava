package main.blind75.dp;

import java.util.Arrays;

/**
 * 213. House Robber II Medium Topics Companies Hint You are a professional robber planning to rob houses along a
 * street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That
 * means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected,
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you
 * can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: nums = [2,3,2] Output: 3 Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
 * because they are adjacent houses. Example 2:
 *
 * Input: nums = [1,2,3,1] Output: 4 Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3). Total amount
 * you can rob = 1 + 3 = 4. Example 3:
 *
 * Input: nums = [1,2,3] Output: 3
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100 0 <= nums[i] <= 1000
 */
public class HouseRobberII {

	/**
	 * Time complexity: O(2^n ) Space complexity: O(n)
	 */
	static class SolutionRecursion {
		public int rob(int[] nums) {
			if (nums.length == 1)
				return nums[0];
			return Math.max(dfs(0, true, nums), dfs(1, false, nums));
		}

		private int dfs(int i, boolean flag, int[] nums) {
			if (i >= nums.length || (flag && i == nums.length - 1))
				return 0;

			return Math.max(dfs(i + 1, flag, nums), nums[i] + dfs(i + 2, flag || i == 0, nums));
		}
	}

	/**
	 * Time complexity: O(n ) Space complexity: O(n)
	 */
	public class SolutionDynamicProgrammingTopDown {
		private int[][] memo;

		public int rob(int[] nums) {
			if (nums.length == 1)
				return nums[0];

			memo = new int[nums.length][2];
			for (int i = 0; i < nums.length; i++) {
				memo[i][0] = -1;
				memo[i][1] = -1;
			}

			return Math.max(dfs(0, 1, nums), dfs(1, 0, nums));
		}

		private int dfs(int i, int flag, int[] nums) {
			if (i >= nums.length || (flag == 1 && i == nums.length - 1))
				return 0;
			if (memo[i][flag] != -1)
				return memo[i][flag];
			memo[i][flag] = Math.max(dfs(i + 1, flag, nums), nums[i] + dfs(i + 2, flag | (i == 0 ? 1 : 0), nums));
			return memo[i][flag];
		}
	}

	/**
	 * Time complexity: O(n ) Space complexity: O(n)
	 */
	public static class SolutionDynamicProgrammingBottomUp {
		public int rob(int[] nums) {
			if (nums.length == 1)
				return nums[0];

			return Math.max(helper(Arrays.copyOfRange(nums, 1, nums.length)),
					helper(Arrays.copyOfRange(nums, 0, nums.length - 1)));
		}

		private int helper(int[] nums) {
			if (nums.length == 0)
				return 0;
			if (nums.length == 1)
				return nums[0];

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
			return Math.max(nums[0], Math.max(helper(Arrays.copyOfRange(nums, 1, nums.length)),
					helper(Arrays.copyOfRange(nums, 0, nums.length - 1))));
		}

		private int helper(int[] nums) {
			int rob1 = 0, rob2 = 0;

			for (int num : nums) {
				int newRob = Math.max(rob1 + num, rob2);
				rob1 = rob2;
				rob2 = newRob;
			}
			return rob2;
		}
	}
}
