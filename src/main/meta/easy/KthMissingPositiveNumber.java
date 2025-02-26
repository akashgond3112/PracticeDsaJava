package main.meta.easy;

/**
 * <pre>
 * 1539. Kth Missing Positive Number
 * Easy
 * Topics
 * Companies
 * Hint
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 *
 * Return the kth positive integer that is missing from this array.
 *
 * Example 1:
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
 * Example 2:
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 *
 * Constraints:
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 1000
 * 1 <= k <= 1000
 * arr[i] < arr[j] for 1 <= i < j <= arr.length
 *
 * Follow up:
 * Could you solve this problem in less than O(n) complexity?
 * </pre>
 */
public class KthMissingPositiveNumber {

	/**
	 * Solutions for the "Kth Missing Positive Number" problem.
	 * This class provides two approaches to find the kth positive integer that is missing from the array.
	 */
	static class SolutionBruteForce {
		/**
		 * Finds the kth missing positive integer using a linear approach.
		 *
		 * Algorithm:
		 * As we iterate through the array, we increment k whenever we encounter a number
		 * less than or equal to the current k. This effectively "shifts" k to account for
		 * numbers that are present in the array.
		 *
		 * @param arr A strictly increasing array of positive integers
		 * @param k The position of the missing number to find
		 * @return The kth missing positive integer
		 *
		 * Time Complexity: O(n) where n is the length of the input array
		 * Space Complexity: O(1) using constant extra space
		 */
		public int findKthPositive(int[] arr, int k) {
			for (int j : arr) {
				if (j <= k) {
					k++;
				} else {
					break;
				}
			}
			return k;
		}
	}

	/**
	 * Optimal solution using binary search.
	 */
	static class SolutionOptimal {
		/**
		 * Finds the kth missing positive integer using binary search.
		 *
		 * Algorithm:
		 * The key insight is that for a sorted array, the number of missing integers
		 * before arr[i] is (arr[i] - (i+1)). We use binary search to find the index
		 * where the number of missing integers is at least k.
		 *
		 * @param arr A strictly increasing array of positive integers
		 * @param k The position of the missing number to find
		 * @return The kth missing positive integer
		 *
		 * Time Complexity: O(log n) where n is the length of the input array
		 * Space Complexity: O(1) using constant extra space
		 */
		public int findKthPositive(int[] arr, int k) {
			int low = 0, high = arr.length - 1;

			while (low <= high) {
				int mid = low + (high - low) / 2;
				int missing = arr[mid] - (mid + 1);

				if (missing < k) {
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			}

			// When the loop ends, low is the index where the number of
			// missing integers becomes >= k
			// The kth missing number is (high + 1) + (k - missing at high)
			// which simplifies to low + k - 1
			return low + k;
		}
	}
}
