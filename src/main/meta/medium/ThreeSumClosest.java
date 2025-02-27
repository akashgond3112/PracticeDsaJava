package main.meta.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * 16. 3Sum Closest
 * Medium
 * Topics
 * Companies
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 *
 * You may assume that each input would have exactly one solution.
 *
 * Example 1:
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * Example 2:
 * Input: nums = [0,0,0], target = 1
 * Output: 0
 * Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
 *
 * Constraints:
 * 3 <= nums.length <= 500
 * -1000 <= nums[i] <= 1000
 * -104 <= target <= 104
 *
 * Topics
 * Array
 * Two Pointers
 * Sorting
 * </pre>*/
public class ThreeSumClosest {

	/**
	 * Time complexity: O(n^2), where n is the length of the given array.
	 * Sorting takes O(n log n), and the two-pointer approach runs in O(n) for each element,
	 * resulting in an overall complexity of O(n^2).
	 *
	 * Space complexity: O(1), as the algorithm operates in-place without using extra space.
	 *
	 * Where n is the length of the given array.
	 */

	static class SolutionOptimal {
		public int threeSumClosest(int[] nums, int target) {
			Arrays.sort(nums);
			int n = nums.length;
			int closestSum = nums[0] + nums[1] + nums[2];

			for (int i = 0; i < n - 2; i++) {
				int left = i + 1;
				int right = n - 1;

				while (left < right) {
					int currentSum = nums[i] + nums[left] + nums[right];

					if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
						closestSum = currentSum;
					}

					if (currentSum < target) {
						left++;
					} else if (currentSum > target) {
						right--;
					} else {
						// Found exact match, can return immediately
						return target;
					}
				}
			}

			return closestSum;
		}
	}

	static class SolutionBruteForce {

		/**
		 * Time complexity: O(n^3 ) Space complexity: O(m)
		 * Where m is the number of triplets and n is
		 * the length of the given array.
		 */
		public int threeSumClosest(int[] arr, int target) {
			int n = arr.length;
			int closestSum = arr[0] + arr[1] + arr[2];

			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					for (int k = j + 1; k < n; k++) {
						int currentSum = arr[i] + arr[j] + arr[k];
						if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
							closestSum = currentSum;
						}
					}
				}
			}
			return closestSum;
		}
	}
}
