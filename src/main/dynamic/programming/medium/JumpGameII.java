package main.dynamic.programming.medium;

import java.util.Arrays;

/**
 * 45. Jump Game II Medium Topics Companies You are given a 0-indexed array of integers nums of length n. You are
 * initially positioned at nums[0].
 *
 * Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at
 * nums[i], you can jump to any nums[i + j] where:
 *
 * 0 <= j <= nums[i] and i + j < n Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated
 * such that you can reach nums[n - 1].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4] Output: 2 Explanation: The minimum number of jumps to reach the last index is 2. Jump 1
 * step from index 0 to 1, then 3 steps to the last index. Example 2:
 *
 * Input: nums = [2,3,0,1,4] Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104 0 <= nums[i] <= 1000 It's guaranteed that you can reach nums[n - 1].
 *
 * Seen this question in a real interview before? 1/5 Yes No Accepted
 * 1.5M Submissions
 * 3.6M Acceptance Rate
 * 40.8% Topics Array Dynamic Programming Greedy
 */
public class JumpGameII {

	public static int jumpUsingTabular(int[] nums, int n) {
		int[] dp = new int[n]; // dp[i] stores the minimum jumps to reach the last index from i
		// Fill the dp array with a large value to signify unreachability initially
		Arrays.fill(dp, Integer.MAX_VALUE);

		dp[0] = 0; // It takes 0 jumps to stay at the first position

		// Iterate through the array to fill dp table
		for (int i = 0; i < n; i++) {
			int maxJump = nums[i]; // Maximum jumps possible from index i
			for (int j = 1; j <= maxJump && i + j < n; j++) {
				dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
			}
		}

		return dp[n - 1]; // The value at dp[n-1] gives the minimum jumps to reach the last index
	}



	public static int jumpUsingMemo(int[] nums, int ind, int[] dp) {
		if (nums.length <= 1)
			return 0;  // If we are already at the last index or there's only one element

		if (ind == nums.length - 1)
			return 0;

		// If the result is already computed, return it
		if (dp[ind] != -1) {
			return dp[ind];
		}

		int re = (int) (1e7);
		int jumps = nums[ind], k = ind + 1;
		while (jumps-- > 0 && k < nums.length) {
			int r = 1 + jumpUsingMemo(nums, k, dp);
			k++;
			re = Math.min(re, r);
		}
		dp[ind] = re;
		return dp[ind];
	}


	/**
	 * Will get time limit exceeded
	 */
	public static int jumpUsingRecursion(int[] nums, int ind) {
		if (nums.length <= 1)
			return 0;  // If we are already at the last index or there's only one element

		if (ind == nums.length - 1)
			return 0;

		int re = (int) (1e7);
		int jumps = nums[ind], k = ind + 1;
		while (jumps-- > 0 && k < nums.length) {
			int r = 1 + jumpUsingRecursion(nums, k);
			k++;
			re = Math.min(re, r);
		}
		return re;
	}

	public static int jumpUsingGreedy(int[] nums) {
		if (nums.length <= 1)
			return 0;  // If we are already at the last index or there's only one element

		int jumps = 0;         // To keep track of the number of jumps made
		int currentEnd = 0;    // The farthest point we can reach with the current jump
		int farthest = 0;      // The farthest point we can reach with the next jump

		for (int i = 0; i < nums.length - 1; i++) {
			farthest = Math.max(farthest, i + nums[i]);  // Update the farthest we can go from index i

			if (i == currentEnd) {  // If we've reached the end of our current jump range
				jumps++;            // We need another jump
				currentEnd = farthest;  // Update the current end to the farthest point we can reach

				// If we've already reached the last index, we can stop
				if (currentEnd >= nums.length - 1) {
					break;
				}
			}
		}
		return jumps;
	}

	public static void main(String[] args) {
		int[] heights = { 2, 3, 1, 1, 4 };  // Example heights of stones
		int n = heights.length;  // Index of the last stone

		// Initialize dp array with -1 (indicating that no value is computed yet)
		int[] dp = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			dp[i] = -1;
		}

		System.out.println(jumpUsingGreedy(heights));
		System.out.println(jumpUsingRecursion(heights, 0));
		System.out.println(jumpUsingMemo(heights, 0, dp));
		System.out.println(jumpUsingTabular(heights, n));
	}
}
