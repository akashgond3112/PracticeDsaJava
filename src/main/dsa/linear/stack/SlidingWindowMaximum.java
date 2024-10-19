package main.dsa.linear.stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 239. Sliding Window Maximum Hard Topics Companies Hint You are given an array of integers nums, there is a sliding
 * window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in
 * the window. Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3 Output: [3,3,5,5,6,7] Explanation: Window position                Max
 * ---------------               ----- [1  3  -1] -3  5  3  6  7       3 1 [3  -1  -3] 5  3  6  7       3 1  3 [-1  -3
 * 5] 3  6  7       5 1  3  -1 [-3  5  3] 6  7       5 1  3  -1  -3 [5  3  6] 7       6 1  3  -1  -3  5 [3  6  7]      7
 * Example 2:
 *
 * Input: nums = [1], k = 1 Output: [1]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105 -104 <= nums[i] <= 104 1 <= k <= nums.length
 */
public class SlidingWindowMaximum {

	public static int[] maxSlidingWindow(int[] nums, int k) {
		int n = nums.length;
		int[] res = new int[n - k + 1]; // Correct length of the result array

		for (int i = 0; i <= n - k; i++) { // Iterate over each window
			int max = Integer.MIN_VALUE; // Initialize max for the current window
			for (int j = i; j < i + k; j++) { // Iterate within the window of size k
				max = Math.max(max, nums[j]); // Update max in the current window
			}
			res[i] = max; // Store the max for the current window
		}
		return res; // Return the result array
	}

	public static int[] maxSlidingWindowOptimal(int[] nums, int k) {
		if (nums == null || k == 0) {
			return new int[0];
		}

		int n = nums.length;
		int[] res = new int[n - k + 1]; // result array to store maximums
		Deque<Integer> deque = new LinkedList<>(); // deque to store indices of elements

		for (int i = 0; i < n; i++) {
			// Remove elements from the front of deque if they are outside the window
			if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
				deque.pollFirst();
			}

			// Remove elements from the back of deque if they are smaller than the current element
			while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
				deque.pollLast();
			}

			// Add the current element's index at the back of deque
			deque.offerLast(i);

			// Once we have processed the first k elements, we can start storing the results
			if (i >= k - 1) {
				res[i - k + 1] = nums[deque.peekFirst()]; // The element at the front of deque is the maximum
			}
		}

		return res;
	}

	public static void main(String[] args) {

		System.out.println(Arrays.toString(maxSlidingWindow(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3)));
		System.out.println(Arrays.toString(maxSlidingWindowOptimal(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3)));

	}
}
