package main.dsa.linear.Array.easy;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {
	//My Solution
	public static int[] twoSum(int[] nums, int target) {

		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[] { i, j };
				}
			}
		}
		return null;
	}

	// Better Solution
	public static int[] twoSumNew(int[] nums, int target) {
		HashMap<Integer, Integer> check = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (check.containsKey(nums[i])) {
				return new int[] { check.get(nums[i]), i };
			}
			check.put(target - nums[i], i);
		}

		return new int[] { 0, 0 };
	}

	public static int[] twoSumOptimal(int[] nums, int target) {

		Arrays.sort(nums);
		int left = 0, right = nums.length - 1;
		while (left < right) {
			int sum = nums[left] + nums[right];
			if (sum == target) {
				return new int[] { left - 1, right };
			}
			if (sum < target) {
				left++;
			}
			if (sum > target) {
				right--;
			}
		}
		return new int[] { 0, 0 };
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 3, 2, 3 };
		System.out.println(Arrays.toString(twoSumNew(nums, 6)));
		System.out.println(Arrays.toString(twoSum(nums, 6)));
		System.out.println(Arrays.toString(twoSumOptimal(nums, 6)));

	}

}
