package main.blind75.dp;

/**
 * 152. Maximum Product Subarray Medium Topics Companies Given an integer array nums, find a subarray that has the
 * largest product, and return the product.
 *
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,-2,4] Output: 6 Explanation: [2,3] has the largest product 6. Example 2:
 *
 * Input: nums = [-2,0,-1] Output: 0 Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104 -10 <= nums[i] <= 10 The product of any subarray of nums is guaranteed to fit in a 32-bit
 * integer.
 */
public class MaximumProductSubarray {

	/**
	 * Time complexity: O(n^2) Space complexity: O(1)
	 */
	static public class SolutionBruteForce {
		public int maxProduct(int[] nums) {
			int res = nums[0];

			for (int i = 0; i < nums.length; i++) {
				int cur = nums[i];
				res = Math.max(res, cur);
				for (int j = i + 1; j < nums.length; j++) {
					cur *= nums[j];
					res = Math.max(res, cur);
				}
			}

			return res;
		}
	}

	/**
	 * Time complexity: O(n) Space complexity: O(1)
	 */

	public static class SolutionPrefixSuffix {
		public int maxProduct(int[] nums) {
			int n = nums.length;
			int res = nums[0];
			int prefix = 0, suffix = 0;

			for (int i = 0; i < n; i++) {
				prefix = nums[i] * (prefix == 0 ? 1 : prefix);
				suffix = nums[n - 1 - i] * (suffix == 0 ? 1 : suffix);
				res = Math.max(res, Math.max(prefix, suffix));
			}
			return res;
		}
	}

	/**
	 * Time complexity: O(n) Space complexity: O(1)
	 */

	public static class SolutionKadane {
		public int maxProduct(int[] nums) {
			int res = nums[0];
			int curMin = 1, curMax = 1;

			for (int num : nums) {
				int tmp = curMax * num;
				curMax = Math.max(Math.max(num * curMax, num * curMin), num);
				curMin = Math.min(Math.min(tmp, num * curMin), num);
				res = Math.max(res, curMax);
			}
			return res;
		}
	}
}
