package main.blind75.arraysAndHashing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 1. Two Sum Solved Easy Topics Companies Hint Given an array of integers nums and an integer target, return indices of
 * the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 *
 * Example 1:
 *
 * Input: nums = [2,7,11,15], target = 9 Output: [0,1] Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * Example 2:
 *
 * Input: nums = [3,2,4], target = 6 Output: [1,2] Example 3:
 *
 * Input: nums = [3,3], target = 6 Output: [0,1]
 *
 * Constraints:
 *
 * 2 <= nums.length <= 104 -109 <= nums[i] <= 109 -109 <= target <= 109 Only one valid answer exists.
 *
 *
 * Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 */
public class TwoSum {

	/**
	 * Time & Space Complexity Time complexity: O( n^2 ) Space complexity: O(1)
	 */
	public int[] twoSumBruteForce(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[] { i, j };
				}
			}
		}
		return new int[0];
	}

	/**
	 * Time complexity: O(nlogn) Space complexity: O(n)
	 */
	public int[] twoSumOptimal(int[] nums, int target) {
		int[][] A = new int[nums.length][2];
		for (int i = 0; i < nums.length; i++) {
			A[i][0] = nums[i];
			A[i][1] = i;
		}

		Arrays.sort(A, Comparator.comparingInt(a -> a[0]));

		int i = 0, j = nums.length - 1;
		while (i < j) {
			int cur = A[i][0] + A[j][0];
			if (cur == target) {
				return new int[] { Math.min(A[i][1], A[j][1]), Math.max(A[i][1], A[j][1]) };
			} else if (cur < target) {
				i++;
			} else {
				j--;
			}
		}
		return new int[0];
	}

	/**
	 * Time complexity: O(n)
	 * Space complexity: O(n)
	 */
	public int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> prevMap = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			int diff = target - num;

			if (prevMap.containsKey(diff)) {
				return new int[] { prevMap.get(diff), i };
			}

			prevMap.put(num, i);
		}

		return new int[] {};
	}
}
