package main.dsa.linear.stack;

import java.util.Stack;

/**
 * 907. Sum of Subarray Minimums Medium Topics Companies Given an array of integers arr, find the sum of min(b), where b
 * ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.
 *
 * Example 1:
 *
 * Input: arr = [3,1,2,4] Output: 17 Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2],
 * [1,2,4], [3,1,2,4]. Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1. Sum is 17. Example 2:
 *
 * Input: arr = [11,81,94,43,3] Output: 444
 *
 * Constraints:
 *
 * 1 <= arr.length <= 3 * 104 1 <= arr[i] <= 3 * 104
 */
public class SumOfSubarrayMinimums {

	public int sumSubarrayMaxs(int[] arr) {
		int n = arr.length;
		int mod = (int) 1e9 + 7;

		// Arrays to store the distance to the next and previous greater element
		int[] nextGreater = new int[n];
		int[] prevGreater = new int[n];

		Stack<Integer> stack = new Stack<>();

		// Calculate next greater element distances
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
				nextGreater[stack.pop()] = i;
			}
			stack.push(i);
		}

		// If no next greater element exists, treat it as the last index (n)
		while (!stack.isEmpty()) {
			nextGreater[stack.pop()] = n;
		}

		// Calculate previous greater element distances
		for (int i = n - 1; i >= 0; i--) {
			while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
				prevGreater[stack.pop()] = i;
			}
			stack.push(i);
		}

		// If no previous greater element exists, treat it as before the first index (-1)
		while (!stack.isEmpty()) {
			prevGreater[stack.pop()] = -1;
		}

		// Calculate the sum of subarray maximums using the next and previous greater element arrays
		long result = 0;
		for (int i = 0; i < n; i++) {
			int leftCount = i - prevGreater[i]; // Distance to the previous greater element
			int rightCount = nextGreater[i] - i; // Distance to the next greater element
			result = (result + (long) arr[i] * leftCount * rightCount) % mod;
		}

		return (int) result;
	}


	/**
	 * Time and Space Complexity: Time complexity: O(n), because we traverse the array twice (once to find previous
	 * smaller elements and once to find next smaller elements). Space complexity: O(n), because we use stacks and
	 * additional arrays to store the previous and next smaller elements.
	 */
	public int sumSubarrayMinsOptimal(int[] arr) {
		int mod = (int) 1e9 + 7;
		int n = arr.length;

		// Arrays to store previous and next smaller elements
		int[] prevSmaller = new int[n];
		int[] nextSmaller = new int[n];

		// Monotonic stack
		Stack<Integer> stack = new Stack<>();

		// Find previous smaller elements
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
				stack.pop();
			}
			prevSmaller[i] = stack.isEmpty() ? -1 : stack.peek();
			stack.push(i);
		}

		stack.clear();

		// Find next smaller elements
		for (int i = n - 1; i >= 0; i--) {
			while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
				stack.pop();
			}
			nextSmaller[i] = stack.isEmpty() ? n : stack.peek();
			stack.push(i);
		}

		// Calculate the result
		int result = 0;
		for (int i = 0; i < n; i++) {
			int leftCount = i - prevSmaller[i];
			int rightCount = nextSmaller[i] - i;
			result = (result + (arr[i] * leftCount * rightCount) % mod) % mod;
		}

		return result;
	}


	/**
	 * Time and Space Complexity: Time complexity: O(n²) because for each starting index i, the inner loop runs over all
	 * possible subarrays ending at index j, resulting in a quadratic number of subarray evaluations. Space complexity:
	 * O(1) (ignoring the input array and the space for result storage) since we only use a few variables to keep track
	 * of the minimum and result.
	 */
	public int sumSubarrayMins(int[] arr) {
		int result = 0;
		int mod = (int) 1e9 + 7;

		// Loop through all starting points of subarrays
		for (int i = 0; i < arr.length; i++) {
			int min = arr[i]; // Start with the current element as the minimum

			// Loop through all possible subarrays starting at index i
			for (int j = i; j < arr.length; j++) {
				// Update the minimum in the current subarray
				min = Math.min(min, arr[j]);

				// Add the current minimum to the result
				result = (result + min) % mod;
			}
		}

		return result;
	}


	public static void main(String[] args) {
		SumOfSubarrayMinimums sumOfSubarrayMinimums = new SumOfSubarrayMinimums();
		System.out.println(sumOfSubarrayMinimums.sumSubarrayMins(new int[] { 3, 1, 2, 4 }));
		System.out.println(sumOfSubarrayMinimums.sumSubarrayMinsOptimal(new int[] { 3, 1, 2, 4 }));
	}
}
