package main.meta.medium;

/**
 * 162. Find Peak Element Medium Topics Companies A peak element is an element that is strictly
 * greater than its neighbors.
 *
 * Given a 0-indexed integer array nums, find a peak element, and return its index. If the array
 * contains multiple peaks, return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to
 * be strictly greater than a neighbor that is outside the array.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1] Output: 2 Explanation: 3 is a peak element and your function should
 * return the index number 2. Example 2:
 *
 * Input: nums = [1,2,1,3,5,6,4] Output: 5 Explanation: Your function can return either index number
 * 1 where the peak element is 2, or index number 5 where the peak element is 6.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000 -231 <= nums[i] <= 231 - 1 nums[i] != nums[i + 1] for all valid i.
 */
public class FindPeakElement {

	/*
	 * Time Complexity: O(n) Space Complexity: O(1)
	 */
	static class SolutionBruteForce {
		public int findPeakElement(int[] nums) {

			for (int i = 0; i < nums.length; i++) {
				if ((i == 0 && i + 1 < nums.length && nums[i] > nums[i + 1])
						|| (i == nums.length - 1 && i - 1 >= 0 && nums[i] > nums[i - 1])
						|| (i - 1 >= 0 && i + 1 < nums.length && nums[i] > nums[i - 1]
								&& nums[i] > nums[i + 1])) {
					return i;
				}
			}
			return -1;
		}
	}

	/*
	 * Time Complexity: O(log n) Space Complexity: O(1)
	 */
	static class SolutionBinarySearch {

		public int findPeakElement(int[] nums) {
			int left = 0;
			int right = nums.length - 1;

			if (nums.length == 1) {
				return 0;
			}

			while (left <= right) {
				int mid = left + (right - left) / 2;

				if (mid > 0 && nums[mid - 1] > nums[mid]) {
					left = mid + 1;
				} else if (mid < nums.length - 1 && nums[mid + 1] > nums[mid]) {
					right = mid - 1;
				} else {
					return mid;

				}
			}
			return -1;
		}

		public int findPeakElementV2(int[] nums) {

			if (nums.length == 1) {
				return 0;
			} else if (nums[0] > nums[1]) {
				return 0;
			} else if (nums[nums.length - 1] > nums[nums.length - 2]) {
				return nums.length - 1;
			}

			int left = 1;
			int right = nums.length - 2;

			while (left <= right) {
				int mid = (right + left) / 2;

				if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
					return mid;
				} else if (nums[mid] > nums[mid - 1]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}

			}
			return -1;
		}
	}

}
