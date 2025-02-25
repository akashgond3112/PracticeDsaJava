package main.meta.medium;

import java.util.Arrays;

/**
 * <pre>
 * 1891. Cutting Ribbons
 * Level
 * Medium
 *
 * Description
 * You are given an integer array ribbons, where ribbons[i] represents the length of the i-th ribbon, and an integer k.
 * You may cut any of the ribbons into any number of segments of positive integer lengths, or perform no cuts at all.
 * For example, if you have a ribbon of length 4, you can:
 * Keep the ribbon of length 4,
 * Cut it into one ribbon of length 3 and one ribbon of length 1,
 * Cut it into two ribbons of length 2,
 * Cut it into one ribbon of length 2 and two ribbons of length 1, or
 * Cut it into four ribbons of length 1.
 * Your goal is to obtain k ribbons of all the same positive integer length. You are allowed to throw away any excess ribbon as a result of cutting.
 *
 * Return the maximum possible positive integer length that you can obtain k ribbons of, or 0 if you cannot obtain k ribbons of the same length.
 *
 * Example 1:
 * Input: ribbons = [9,7,5], k = 3
 * Output: 5
 * Explanation:
 * Cut the first ribbon to two ribbons, one of length 5 and one of length 4.
 * Cut the second ribbon to two ribbons, one of length 5 and one of length 2.
 * Keep the third ribbon as it is.
 * Now you have 3 ribbons of length 5.
 *
 * Example 2:
 * Input: ribbons = [7,5,9], k = 4
 * Output: 4
 * Explanation:
 * Cut the first ribbon to two ribbons, one of length 4 and one of length 3.
 * Cut the second ribbon to two ribbons, one of length 4 and one of length 1.
 * Cut the third ribbon to three ribbons, two of length 4 and one of length 1.
 * Now you have 4 ribbons of length 4.
 *
 * Example 3:
 * Input: ribbons = [5,7,9], k = 22
 * Output: 0
 * Explanation: You cannot obtain k ribbons of the same positive integer length.
 *
 * Constraints:
 * 1 <= ribbons.length <= 10^5
 * 1 <= ribbons[i] <= 10^5
 * 1 <= k <= 10^9
 * </pre>
 */
public class CuttingRibbons {

	/**
	 * Solution for the Cutting Ribbons problem.
	 * This class finds the maximum possible length to cut ribbons such that we get at least k pieces.
	 */
	public static class RibbonCutter {

		/**
		 * Finds the maximum length that can be used to cut ribbons to get at least k pieces.
		 *
		 * Algorithm steps:
		 * 1. Define search space between 1 and the maximum ribbon length
		 * 2. Use binary search to find the maximum valid cutting length
		 * 3. For each potential length, check if we can cut enough pieces
		 *
		 * @param nums Array of ribbon lengths
		 * @param k The minimum number of pieces required
		 * @return The maximum possible length of each cut piece, or 0 if not possible
		 *
		 * Time Complexity: O(n log m) where n is the number of ribbons and m is the maximum ribbon length
		 * Space Complexity: O(1) using constant extra space
		 */
		public static int getMaxLength(int[] nums, int k) {
			// No need to sort the array as we're just summing up pieces

			// Find the maximum ribbon length to set the upper bound of search space
			int maxLength = 0;
			for (int num : nums) {
				maxLength = Math.max(maxLength, num);
			}

			int left = 1;
			int right = maxLength;

			while (left <= right) {
				// Fix: Correct way to calculate mid to avoid integer overflow
				int mid = left + (right - left) / 2;

				if (canCutRibbons(nums, k, mid)) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}

			// If right becomes 0, it means we can't cut ribbons to get k pieces
			return right;
		}

		/**
		 * Checks if it's possible to cut the ribbons into pieces of given length
		 * such that we get at least k pieces in total.
		 *
		 * @param nums Array of ribbon lengths
		 * @param k The minimum number of pieces required
		 * @param length The length of each piece to cut
		 * @return true if at least k pieces can be cut, false otherwise
		 */
		private static boolean canCutRibbons(int[] nums, int k, int length) {
			int count = 0;

			for (int n : nums) {
				count += n / length;
			}

			return count >= k;
		}

		/**
		 * Main method for testing the solution.
		 *
		 * @param args Command line arguments
		 */
		public static void main(String[] args) {
			int[] nums = new int[]{9, 7, 5};
			System.out.println(getMaxLength(nums, 3));
		}
	}

}
