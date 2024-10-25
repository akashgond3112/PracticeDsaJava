package main.dynamic.programming.medium;

/**
 * 55. Jump Game Solved Medium Topics Companies You are given an integer array nums. You are initially positioned at the
 * array's first index, and each element in the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4] Output: true Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [3,2,1,0,4] Output: false Explanation: You will always arrive at index 3 no matter what. Its maximum
 * jump length is 0, which makes it impossible to reach the last index.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104 0 <= nums[i] <= 105
 */
public class JumpGame {

	public static boolean canJump(int[] nums) {
		if (nums.length <= 1)
			return true;  // If we are already at the last index or there's only one element

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
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[] heights = { 2, 3, 1, 1, 4 };  // Example heights of stones
		int n = heights.length;  // Index of the last stone

		// Initialize dp array with -1 (indicating that no value is computed yet)
		int[] dp = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			dp[i] = -1;
		}

		System.out.println(canJump(heights));
	}
}
