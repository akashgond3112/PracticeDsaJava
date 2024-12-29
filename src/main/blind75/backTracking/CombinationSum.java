package main.blind75.backTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. Combination Sum Solved Medium Topics Companies Given an array of distinct integers candidates and a target
 * integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You
 * may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
 * frequency of at least one of the chosen numbers is different.
 *
 * The test cases are generated such that the number of unique combinations that sum up to target is less than 150
 * combinations for the given input.
 *
 *
 *
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7 Output: [[2,2,3],[7]] Explanation: 2 and 3 are candidates, and 2 + 2 + 3 =
 * 7. Note that 2 can be used multiple times. 7 is a candidate, and 7 = 7. These are the only two combinations. Example
 * 2:
 *
 * Input: candidates = [2,3,5], target = 8 Output: [[2,2,2,2],[2,3,3],[3,5]] Example 3:
 *
 * Input: candidates = [2], target = 1 Output: []
 *
 *
 * Constraints:
 *
 * 1 <= candidates.length <= 30 2 <= candidates[i] <= 40 All elements of candidates are distinct. 1 <= target <= 40
 */
public class CombinationSum {

	/**
	 * Time complexity: O(2t/m )
	 * Space complexity: O(t/m)
	 * Where t is the given target and m is the minimum value in nums.
	 */
	public static class Solution {
		List<List<Integer>> res;

		public List<List<Integer>> combinationSum(int[] nums, int target) {
			res = new ArrayList<List<Integer>>();
			List<Integer> cur = new ArrayList<>();
			backtrack(nums, target, cur, 0);
			return res;
		}

		public void backtrack(int[] nums, int target, List<Integer> cur, int i) {
			if (target == 0) {
				res.add(new ArrayList<>(cur));
				return;
			}
			if (target < 0 || i >= nums.length) {
				return;
			}

			cur.add(nums[i]);
			backtrack(nums, target - nums[i], cur, i);
			cur.removeLast();
			backtrack(nums, target, cur, i + 1);
		}
	}

	/**
	 * Time complexity: O(2t/m )
	 * Space complexity: O(t/m)
	 * Where t is the given target and m is the minimum value in nums.
	 */
	public static class SolutionUsingDFS {
		List<List<Integer>> res;

		public List<List<Integer>> combinationSum(int[] nums, int target) {
			res = new ArrayList<>();
			Arrays.sort(nums);

			dfs(0, new ArrayList<>(), 0, nums, target);
			return res;
		}

		private void dfs(int i, List<Integer> cur, int total, int[] nums, int target) {
			if (total == target) {
				res.add(new ArrayList<>(cur));
				return;
			}

			for (int j = i; j < nums.length; j++) {
				if (total + nums[j] > target) {
					return;
				}
				cur.add(nums[j]);
				dfs(j, cur, total + nums[j], nums, target);
				cur.removeLast();
			}
		}
	}
}
