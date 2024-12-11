package main.blind75.arraysAndHashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/*
128. Longest Consecutive Sequence
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.
Example 1:
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

* Example 2:
Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
*
Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109*/
public class LongestConsecutiveSequence {

	/**
	 * Time & Space Complexity Time complexity: O(n) Space complexity: O(n)
	 */
	public int longestConsecutive(int[] nums) {
		Map<Integer, Integer> mp = new HashMap<>();
		int res = 0;

		for (int num : nums) {
			if (!mp.containsKey(num)) {
				mp.put(num, mp.getOrDefault(num - 1, 0) + mp.getOrDefault(num + 1, 0) + 1);
				mp.put(num - mp.getOrDefault(num - 1, 0), mp.get(num));
				mp.put(num + mp.getOrDefault(num + 1, 0), mp.get(num));
				res = Math.max(res, mp.get(num));
			}
		}
		return res;
	}
}
