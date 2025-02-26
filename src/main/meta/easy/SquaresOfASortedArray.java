package main.meta.easy;

/**
 * <pre>
 * 977. Squares of a Sorted Array
 * Easy
 * Topics
 * Companies
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
 *
 * Example 1:
 * Input: nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Explanation: After squaring, the array becomes [16,1,0,9,100].
 * After sorting, it becomes [0,1,9,16,100].
 *
 * Example 2:
 * Input: nums = [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 *
 * Constraints:
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums is sorted in non-decreasing order.
 *
 * Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?
 * </pre>
 */
public class SquaresOfASortedArray {

	/**
	 * Solution for the "Squares of a Sorted Array" problem.
	 * This class squares each element in a sorted array and returns a new array that is also sorted.
	 */
	static class Solution {
		/**
		 * Returns an array of the squares of each number in the input array, sorted in non-decreasing order.
		 *
		 * The input array is sorted in non-decreasing order, but may contain negative numbers.
		 * After squaring, we need to ensure the result array remains sorted.
		 *
		 * @param nums Input array sorted in non-decreasing order
		 * @return A new array containing the squares of each number, sorted in non-decreasing order
		 *
		 * Time Complexity: O(n) where n is the length of the input array
		 * Space Complexity: O(n) for the result array
		 */
		public int[] sortedSquares(int[] nums) {
			int[] result = new int[nums.length];

			// Create squared values in the original array
			for (int i = 0; i < nums.length; i++) {
				nums[i] = nums[i] * nums[i];
			}

			int left = 0;
			int right = nums.length - 1;

			// Fill the result array from the end (largest values first)
			for (int i = nums.length - 1; i >= 0; i--) {
				if (nums[left] > nums[right]) {
					result[i] = nums[left];
					left++;
				} else {
					result[i] = nums[right];
					right--;
				}
			}

			return result;
		}
	}
}
