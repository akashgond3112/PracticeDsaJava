package main.blind75.twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
* 15. 3Sum
Medium
Topics
Companies
Hint
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation:
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.

Example 2:
Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.

Constraints:

3 <= nums.length <= 3000
-105 <= nums[i] <= 105*/
public class ThreeSum {

	/**
	 * Time & Space Complexity Time complexity: O(n^2 ) Space complexity: O(n)
	 */
	public List<List<Integer>> threeSumUsingMap(int[] nums) {
		Arrays.sort(nums);
		Map<Integer, Integer> count = new HashMap<>();
		for (int num : nums) {
			count.put(num, count.getOrDefault(num, 0) + 1);
		}

		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			count.put(nums[i], count.get(nums[i]) - 1);
			if (i > 0 && nums[i] == nums[i - 1])
				continue;

			for (int j = i + 1; j < nums.length; j++) {
				count.put(nums[j], count.get(nums[j]) - 1);
				if (j > i + 1 && nums[j] == nums[j - 1])
					continue;

				int target = -(nums[i] + nums[j]);
				if (count.getOrDefault(target, 0) > 0) {
					res.add(Arrays.asList(nums[i], nums[j], target));
				}
			}

			for (int j = i + 1; j < nums.length; j++) {
				count.put(nums[j], count.get(nums[j]) + 1);
			}
		}
		return res;
	}

	/**
	 * Time complexity: O(n^2 ) Space complexity: O(1) or O(n) depending on the sorting
	 * algorithm.
	 */
	public List<List<Integer>> threeSumOptimal(int[] nums) {
		// Write your code here.
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) break;
			if (i > 0 && nums[i] == nums[i - 1]) continue;

			int l = i + 1, r = nums.length - 1;
			while (l < r) {
				int sum = nums[i] + nums[l] + nums[r];
				if (sum > 0) {
					r--;
				} else if (sum < 0) {
					l++;
				} else {
					res.add(Arrays.asList(nums[i], nums[l], nums[r]));
					l++;
					r--;
					while (l < r && nums[l] == nums[l - 1]) {
						l++;
					}
				}
			}
		}
		return res;
	}
}
