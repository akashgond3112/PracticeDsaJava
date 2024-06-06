package main.two_pointers.medium;

import java.util.List;

import static java.lang.System.*;

/*
2856. Minimum Array Length After Pair Removals
Solved
Medium
Topics
Companies
Hint
Given an integer array num sorted in non-decreasing order.

You can perform the following operation any number of times:

Choose two indices, i and j, where nums[i] < nums[j].
Then, remove the elements at indices i and j from nums. The remaining elements retain their original order, and the array is re-indexed.
Return the minimum length of nums after applying the operation zero or more times.

Example 1:
Input: nums = [1,2,3,4]
Output: 0

Example 2:
Input: nums = [1,1,2,2,3,3]
Output: 0

Example 3:
Input: nums = [1000000000,1000000000]
Output: 2
Explanation: Since both numbers are equal, they cannot be removed.

Example 4:
Input: nums = [2,3,4,4,4]
Output: 1
*/

public class MinimumArrayLengthAfterPairRemovals {

	public int minLengthAfterRemovals(List<Integer> nums) {
		int n = nums.size();
		int left = 0;
		int right = (n + 1) / 2;
		int pairedCount = n;

		while (left < n / 2 && right < n) {
			if (nums.get(left) < nums.get(right)) {
				pairedCount -= 2;
			}
			left++;
			right++;
		}

		// The minimum length after removals is the total length minus the number of paired elements
		return pairedCount;
	}

	public static void main(String[] args) {
		MinimumArrayLengthAfterPairRemovals sol = new MinimumArrayLengthAfterPairRemovals();
		List<Integer> nums1 = List.of(1, 2, 3, 4);
		List<Integer> nums2 = List.of(1, 1, 2, 2, 3, 3);
		List<Integer> nums3 = List.of(2, 3, 4, 4, 4);

		out.println(sol.minLengthAfterRemovals(nums1)); // Output: 0
		out.println(sol.minLengthAfterRemovals(nums2)); // Output: 0
		out.println(sol.minLengthAfterRemovals(nums3)); // Output: 0
	}
}
