package main.blind75.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 300. Longest Increasing Subsequence Medium Topics Companies Given an integer array nums, return the length of the
 * longest strictly increasing subsequence .
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,9,2,5,3,7,101,18] Output: 4 Explanation: The longest increasing subsequence is [2,3,7,101],
 * therefore the length is 4. Example 2:
 *
 * Input: nums = [0,1,0,3,2,3] Output: 4 Example 3:
 *
 * Input: nums = [7,7,7,7,7,7,7] Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2500 -104 <= nums[i] <= 104
 *
 *
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
public class LongestIncreasingSubsequence {

	/**
	 * Time complexity: O(2 n ) Space complexity: O(n)
	 */
	public static class SolutionUsingRecursion {
		public int lengthOfLIS(int[] nums) {
			return dfs(nums, 0, -1);
		}

		private int dfs(int[] nums, int i, int j) {
			if (i == nums.length) {
				return 0;
			}

			int LIS = dfs(nums, i + 1, j); // not include

			if (j == -1 || nums[j] < nums[i]) {
				LIS = Math.max(LIS, 1 + dfs(nums, i + 1, i)); // include
			}

			return LIS;
		}
	}


	/**
	 * Time complexity: O(n^2 ) Space complexity: O(n^2 )
	 */
	public static class SolutionUsingDpTopDown {
		private int[][] memo;

		private int dfs(int i, int j, int[] nums) {
			if (i == nums.length) {
				return 0;
			}
			if (memo[i][j + 1] != -1) {
				return memo[i][j + 1];
			}

			int LIS = dfs(i + 1, j, nums);

			if (j == -1 || nums[j] < nums[i]) {
				LIS = Math.max(LIS, 1 + dfs(i + 1, i, nums));
			}

			memo[i][j + 1] = LIS;
			return LIS;
		}

		public int lengthOfLIS(int[] nums) {
			int n = nums.length;
			memo = new int[n][n + 1];
			for (int[] row : memo) {
				Arrays.fill(row, -1);
			}
			return dfs(0, -1, nums);
		}
	}
}
