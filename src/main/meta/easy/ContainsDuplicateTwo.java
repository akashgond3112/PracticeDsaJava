package main.meta.easy;

import java.util.HashSet;

/**
 * <pre>
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 * Example 2:
 *
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 * Example 3:
 *
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * </pre>
 */
public class ContainsDuplicateTwo {

	// Brute force method
	/**
	 * This class provides solutions to determine if an array contains duplicate elements
	 * that are within a certain distance of each other.
	 *
	 * Problem: Given an array of integers and an integer k, determine if there are two
	 * distinct indices i and j in the array such that nums[i] = nums[j] and abs(i - j) <= k.
	 */
	public static class ContainsNearbyDuplicate {

		/**
		 * Brute force approach to find nearby duplicates.
		 *
		 * Time Complexity: O(n²) - Uses nested loops to compare all possible pairs
		 * Space Complexity: O(1) - Uses constant extra space
		 *
		 * @param nums Array of integers to check for nearby duplicates
		 * @param k Maximum allowed distance between duplicate elements
		 * @return true if nearby duplicates exist, false otherwise
		 */
		public static boolean containsNearbyDuplicateBruteForce(int[] nums, int k) {
			for (int i = 0; i < nums.length; i++) {
				for (int j = i + 1; j < nums.length; j++) {
					if (nums[i] == nums[j] && Math.abs(i - j) <= k) {
						return true;
					}
				}
			}
			return false;
		}

		/**
		 * Optimized approach using a sliding window with HashSet to find nearby duplicates.
		 * Maintains a set of elements within a window of size k+1.
		 *
		 * Time Complexity: O(n) - Each element is processed once
		 * Space Complexity: O(min(n, k+1)) - The set stores at most k+1 elements
		 *
		 * @param nums Array of integers to check for nearby duplicates
		 * @param k Maximum allowed distance between duplicate elements
		 * @return true if nearby duplicates exist, false otherwise
		 */
		public static boolean containsNearbyDuplicate(int[] nums, int k) {
			int startIndex = 0;
			int endIndex = 0;

			if (nums == null || nums.length < 2 || k == 0)
				return false;

			HashSet<Integer> set = new HashSet<>();

			while (endIndex < nums.length) {
				if (!set.add(nums[endIndex]))
					return true;
				if (set.size() >= k + 1)
					set.remove(nums[startIndex++]);
				endIndex++;
			}
			return false;
		}

		/**
		 * Main method to demonstrate the functionality with example test cases.
		 *
		 * @param args Command line arguments (not used)
		 */
		public static void main(String[] args) {
			int[] nums = { 1, 2, 3, 1 };
			int k = 3;
			System.out.println(containsNearbyDuplicate(nums, k));  // Expected: true

			nums = new int[] { 1, 0, 1, 1 };
			k = 1;
			System.out.println(containsNearbyDuplicate(nums, k));  // Expected: true

			nums = new int[] { 1, 2, 3, 1, 2, 3 };
			k = 2;
			System.out.println(containsNearbyDuplicate(nums, k));  // Expected: false
		}
	}
}
