package main.meta.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. Subsets Solved Medium Topics Companies Given an integer array nums of unique elements, return all possible
 * subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * Example 1:
 * Input: nums = [1,2,3] Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * Example 2:
 * Input: nums = [0] Output: [[],[0]]
 *
 * Constraints:
 * 1 <= nums.length <= 10 -10 <= nums[i] <= 10 All the numbers of nums are unique. Seen this question in a real
 * interview before? 1/5 Yes No Accepted
 * 2.3M Submissions
 * 2.9M Acceptance Rate
 * 80.2% Topics Array Backtracking Bit Manipulation
 */
public class Subsets {

	/**
	 * Solution for generating all possible subsets of an array using backtracking.
	 * For example: [1,2] generates [], [1], [2], [1,2]
	 */
	static class Solution {
		/**
		 * Main method to generate all subsets of the given array.
		 *
		 * @param nums Input array of integers
		 * @return List of all possible subsets
		 *
		 * Time Complexity: O(2^n) where n is the length of nums
		 * Space Complexity: O(n) for recursion stack
		 */
		public List<List<Integer>> subsets(int[] nums) {
			List<List<Integer>> subsets = new ArrayList<>();
			generateSubsets(0, nums, new ArrayList<>(), subsets);
			return subsets;
		}

		/**
		 * Recursive helper method that generates subsets using backtracking.
		 * For each number, we have two choices:
		 * 1. Include the number in the current subset
		 * 2. Exclude the number from the current subset
		 *
		 * @param index Current index in nums array being processed
		 * @param nums Input array of integers
		 * @param currentSubset Current subset being built
		 * @param subsets Result list containing all subsets
		 */
		private void generateSubsets(int index, int[] nums,
				List<Integer> currentSubset,
				List<List<Integer>> subsets) {
			// Base case: reached end of array
			if (index == nums.length) {
				subsets.add(new ArrayList<>(currentSubset)); // Add copy of current subset
				return;
			}

			// Decision 1: Include current number
			currentSubset.add(nums[index]);
			generateSubsets(index + 1, nums, currentSubset, subsets);

			// Decision 2: Exclude current number (backtrack)
			currentSubset.removeLast();
			generateSubsets(index + 1, nums, currentSubset, subsets);
		}
	}
}
