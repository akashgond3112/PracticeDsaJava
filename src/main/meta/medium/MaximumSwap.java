package main.meta.medium;

/**
 * 670. Maximum Swap Medium Topics Companies You are given an integer num. You can swap two digits at most once to get
 * the maximum valued number.
 *
 * Return the maximum valued number you can get.
 *
 * Example 1: Input: num = 2736 Output: 7236 Explanation: Swap the number 2 and the number 7.
 *
 * Example 2: Input: num = 9973 Output: 9973 Explanation: No swap.
 *
 * Constraints: 0 <= num <= 108 Topics Math Greedy
 */
public class MaximumSwap {

	/**
	 * Solution to find the maximum value possible after at most one swap of two digits.
	 * For example: 2736 -> 7236 (swap 2 and 7 to get maximum value)
	 */
	static class SolutionSwapOnes {
		/**
		 * Finds the maximum possible value after at most one swap of two digits.
		 *
		 * @param num The input number
		 * @return The maximum possible value after at most one swap
		 *
		 * Time Complexity: O(n^2) where n is the number of digits
		 * Space Complexity: O(n) for the character array
		 */
		public int maximumSwap(int num) {
			// Convert number to char array for easy swapping
			char[] digits = String.valueOf(num).toCharArray();
			int n = digits.length;

			// For each position
			for (int i = 0; i < n - 1; i++) {
				// Find the maximum digit after current position
				int maxIdx = i;
				for (int j = i + 1; j < n; j++) {
					if (digits[j] >= digits[maxIdx]) {
						maxIdx = j;
					}
				}

				// If we found a larger digit, swap with current position
				if (maxIdx != i && digits[maxIdx] > digits[i]) {
					// Swap
					char temp = digits[i];
					digits[i] = digits[maxIdx];
					digits[maxIdx] = temp;
					return Integer.parseInt(new String(digits));
				}
			}

			// No swap needed
			return num;
		}
	}

	/**
	 * Solution to find maximum possible value after at most one swap using recursion.
	 */
	static class SolutionSwapOnesRecursion {
		int maxNum;  // Store the maximum value found so far

		/**
		 * Main method to find maximum value after at most one swap.
		 *
		 * @param num Input number
		 * @return Maximum possible value after one swap
		 */
		public int maximumSwap(int num) {
			maxNum = num;
			char[] digits = String.valueOf(num).toCharArray();
			// Start recursive swapping from index 0
			swapAndFind(digits, 0, false);
			return maxNum;
		}

		/**
		 * Recursive helper method to try all possible single swaps.
		 *
		 * @param digits Array of digits
		 * @param start Current position being considered
		 * @param swapped Whether we have already made a swap
		 */
		private void swapAndFind(char[] digits, int start, boolean swapped) {
			// Base case: reached end of digits
			if (start == digits.length) {
				maxNum = Math.max(maxNum, Integer.parseInt(new String(digits)));
				return;
			}

			// Try swapping current digit with each following digit
			for (int i = start; i < digits.length; i++) {
				for (int j = i + 1; j < digits.length; j++) {
					// Only swap if we haven't swapped before and it's beneficial
					if (!swapped && digits[j] > digits[i]) {
						// Swap
						char temp = digits[i];
						digits[i] = digits[j];
						digits[j] = temp;

						// Recurse with swapped set to true
						swapAndFind(digits, start + 1, true);

						// Backtrack (undo swap)
						temp = digits[i];
						digits[i] = digits[j];
						digits[j] = temp;
					}
				}
			}

			// Try without swapping current digit
			swapAndFind(digits, start + 1, swapped);
		}
	}

	/**
	 * Solution to find maximum possible value after at most k swaps of digits.
	 * Time Complexity: O(n² * k)
	 * For each swap, we try all possible pairs
	 * We do this k times
	 * <p>
	 * Space Complexity: O(k)
	 * Recursion stack depth is k
	 * Character array is O(n)
	 */
	static class SolutionKSwaps {
		int maxNum;  // Store the maximum value found

		/**
		 * Returns maximum value possible after k swaps.
		 *
		 * @param num Input number
		 * @param k Maximum number of swaps allowed A
		 * @return Maximum value possible after k swaps
		 */
		public int maximumSwap(int num, int k) {
			maxNum = num;
			char[] digits = String.valueOf(num).toCharArray();
			findMaxWithKSwaps(digits, k);
			return maxNum;
		}

		/**
		 * Recursive helper method to find maximum value with k swaps.
		 *
		 * @param digits Array of digits
		 * @param k Number of swaps remaining
		 */
		private void findMaxWithKSwaps(char[] digits, int k) {
			// Update maximum if current number is larger
			maxNum = Math.max(maxNum, Integer.parseInt(new String(digits)));

			// Base case: no more swaps left
			if (k == 0) {
				return;
			}

			int n = digits.length;

			// Try swapping each pair of digits
			for (int i = 0; i < n - 1; i++) {
				for (int j = i + 1; j < n; j++) {
					// Only swap if it's beneficial
					if (digits[j] > digits[i]) {
						// Swap
						char temp = digits[i];
						digits[i] = digits[j];
						digits[j] = temp;

						// Recurse with k-1 swaps
						findMaxWithKSwaps(digits, k - 1);

						// Backtrack
						temp = digits[i];
						digits[i] = digits[j];
						digits[j] = temp;
					}
				}
			}
		}
	}
}
