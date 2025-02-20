package main.meta.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 523. Continuous Subarray Sum
 * Medium
 * Topics
 * Companies
 * Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.
 *
 * A good subarray is a subarray where:
 *
 * its length is at least two, and
 * the sum of the elements of the subarray is a multiple of k.
 * Note that:
 *
 * A subarray is a contiguous part of the array.
 * An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 *
 *
 * Example 1:
 *
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 * Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
 * Example 2:
 *
 * Input: nums = [23,2,6,4,7], k = 6
 * Output: true
 * Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
 * 42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
 * Example 3:
 *
 * Input: nums = [23,2,6,4,7], k = 13
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 * 0 <= sum(nums[i]) <= 231 - 1
 * 1 <= k <= 231 - 1
 * */

/**
 * Determines if a contiguous subarray exists in the given array `nums`
 * such that its length is at least 2 and its sum is a multiple of `k`.
 *
 * <p><strong>Problem Explanation:</strong></p>
 * Given an array `nums` and an integer `k`, the task is to check if there
 * exists a subarray of at least two elements whose sum is a multiple of `k`.
 * This includes checking for a sum of 0, as 0 is always a multiple of any `k`.
 *
 * <p><strong>Example:</strong></p>
 * <pre>
 * nums = [23, 2, 4, 6, 7], k = 6
 * </pre>
 * The subarray `[2, 4]` sums to 6, which is a multiple of 6. So, the answer is `true`.
 *
 * <p><strong>Approach: Prefix Sums and Modulo</strong></p>
 * The solution uses prefix sums and the modulo operation:
 * <ul>
 *   <li><strong>Prefix Sums:</strong> Track cumulative sums up to each index.</li>
 *   <li><strong>Modulo Operation:</strong> If two prefix sums have the same remainder when divided by `k`,
 *       their difference is a multiple of `k`.</li>
 * </ul>
 *
 * <p><strong>Key Insight:</strong> If two prefix sums at indices `i` and `j` (where `j > i`)
 * have the same remainder modulo `k`, the subarray from `i+1` to `j` has a sum that's a multiple of `k`.</p>
 *
 * <p><strong>Algorithm:</strong></p>
 * <ul>
 *   <li>Use a hashmap to track the first occurrence of each remainder from prefix sums.</li>
 *   <li>Initialize the hashmap with `{0: -1}` to handle cases where a prefix sum itself is a multiple of `k`.</li>
 *   <li>Iterate through `nums`, updating the prefix sum and its remainder modulo `k`.</li>
 *   <li>Check if the remainder exists in the hashmap:
 *     <ul>
 *       <li>If yes, check if the subarray length is at least 2.</li>
 *       <li>If no, store the remainder and its index.</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <p><strong>Example Walkthrough:</strong></p>
 * <pre>
 * nums = [23, 2, 4, 6, 7], k = 6
 *
 * Step 1: Initialize
 *   prefix_mod = {0: -1}
 *   current_sum = 0
 *
 * Step 2: Process nums[0] = 23
 *   current_sum = 23
 *   23 % 6 = 5
 *   Store (5 -> index 0)
 *
 * Step 3: Process nums[1] = 2
 *   current_sum = 25
 *   25 % 6 = 1
 *   Store (1 -> index 1)
 *
 * Step 4: Process nums[2] = 4
 *   current_sum = 29
 *   29 % 6 = 5 (already in hashmap at index 0)
 *   Check subarray length: 2 - 0 = 2 (valid!)
 *   Return true.
 * </pre>
 *
 * <p><strong>Edge Cases Considered:</strong></p>
 * <ul>
 *   <li><strong>Subarray starts at index 0:</strong>
 *       Example: `nums = [6, 6], k = 6` → The sum is 12, which is a multiple of 6.</li>
 *   <li><strong>Zero elements:</strong>
 *       Example: `nums = [0, 0], k = 1` → The sum is 0, which is a multiple of 1.</li>
 * </ul>
 *
 * <p><strong>Time Complexity:</strong> O(N), as we iterate through `nums` once.</p>
 * <p><strong>Space Complexity:</strong> O(min(N, k)), due to the hashmap storing remainders.</p>
 */

public class ContinuousSubarraySum {

	public static class SolutionBruteForce {
		public boolean checkSubarraySum(int[] nums, int k) {
			for (int i = 0; i < nums.length - 1; i++) {
				int sum = nums[i];
				for (int j = i + 1; j < nums.length; j++) {
					sum += nums[j];
					if (sum % k == 0) return true;
				}
			}
			return false;
		}
	}

	public static class SolutionOptimal {
		public boolean checkSubarraySum(int[] nums, int k) {
			// Create a hashmap to store the first occurrence of each remainder.
			// Key: remainder of prefix sum modulo k, Value: index of the prefix sum.
			Map<Integer, Integer> prefixModMap = new HashMap<>();

			// Initialize the map with {0: -1} to handle cases where the prefix sum itself is a multiple of k.
			prefixModMap.put(0, -1);

			int currentSum = 0; // Track the cumulative sum of the array.

			// Iterate through the array to compute prefix sums and their remainders.
			for (int i = 0; i < nums.length; i++) {
				currentSum += nums[i]; // Update the cumulative sum.

				// Compute the remainder of the current sum modulo k.
				int remainder = currentSum % k;

				// Check if the remainder has been seen before.
				if (prefixModMap.containsKey(remainder)) {
					// If the remainder exists, check if the subarray length is at least 2.
					if (i - prefixModMap.get(remainder) >= 2) {
						return true; // A valid subarray exists.
					}
				} else {
					// If the remainder is not in the map, store it with the current index.
					prefixModMap.put(remainder, i);
				}
			}

			// If no valid subarray is found, return false.
			return false;
		}
	}

	/**
	 * Main method to test the functionality of the checkSubarraySum method.
	 *
	 * @param args Command-line arguments (not used).
	 */
	public static void main(String[] args) {
		SolutionOptimal solution = new SolutionOptimal();

		// Test case 1: Valid subarray exists.
		int[] nums1 = {23, 2, 4, 6, 7};
		int k1 = 6;
		System.out.println(solution.checkSubarraySum(nums1, k1)); // Output: true

		// Test case 2: Valid subarray exists.
		int[] nums2 = {23, 2, 6, 4, 7};
		int k2 = 6;
		System.out.println(solution.checkSubarraySum(nums2, k2)); // Output: true

		// Test case 3: No valid subarray exists.
		int[] nums3 = {23, 2, 6, 4, 7};
		int k3 = 13;
		System.out.println(solution.checkSubarraySum(nums3, k3)); // Output: false
	}
}
