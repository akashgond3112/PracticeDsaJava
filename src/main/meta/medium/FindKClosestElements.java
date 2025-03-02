package main.meta.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 * 658. Find K Closest Elements
 * Medium
 * Topics
 * Companies
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
 * An integer a is closer to x than an integer b if:
 *
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 *
 * Example 1:
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 *
 * Example 2:
 * Input: arr = [1,1,2,3,4,5], k = 4, x = -1
 * Output: [1,1,2,3]
 *
 * Constraints:
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 104
 * arr is sorted in ascending order.
 * -104 <= arr[i], x <= 104
 *
 * Topics
 * Array
 * Two Pointers
 * Binary Search
 * Sliding Window
 * Sorting
 * Heap (Priority Queue)
 * </pre>
 */
public class FindKClosestElements {
	static class Solution {
		/**
		 * Finds the k closest elements to a given value x in a sorted array.
		 *
		 * @param arr
		 * 		The sorted input array
		 * @param k
		 * 		Number of closest elements to find
		 * @param x
		 * 		Target value
		 * @return List of k closest elements, sorted in ascending order
		 *
		 * 		Time Complexity: O(log n + k + k log k) Space Complexity: O(k)
		 */
		public List<Integer> findClosestElements(int[] arr, int k, int x) {
			// Find the index of the element less than or equal to x (floor)
			int i = findFloor(arr, x);
			int j = i + 1;

			List<Integer> result = new ArrayList<>();

			// Use two pointers to collect the k closest elements
			while (k > 0 && i >= 0 && j < arr.length) {
				// Compare distances from x
				if (Math.abs(x - arr[i]) <= Math.abs(x - arr[j])) {
					result.add(arr[i--]);
				} else {
					result.add(arr[j++]);
				}
				k--;
			}

			// Handle edge cases where one pointer has reached the array boundary
			while (k > 0 && i >= 0) {
				result.add(arr[i--]);
				k--;
			}

			while (k > 0 && j < arr.length) {
				result.add(arr[j++]);
				k--;
			}

			// Sort the result
			Collections.sort(result);
			return result;
		}

		/**
		 * Finds the index of the element in the array that is less than or equal to x. If x is smaller than all
		 * elements, returns 0.
		 *
		 * @param arr
		 * 		The sorted input array
		 * @param x
		 * 		Target value
		 * @return Index of the floor element
		 *
		 * 		Time Complexity: O(log n) Space Complexity: O(1)
		 */
		private int findFloor(int[] arr, int x) {
			int start = 0;
			int end = arr.length - 1;
			int ans = 0;

			while (start <= end) {
				int mid = start + (end - start) / 2;

				if (arr[mid] == x) {
					return mid;
				}

				if (arr[mid] < x) {
					ans = mid;
					start = mid + 1;
				}

				if (arr[mid] > x) {
					end = mid - 1;
				}
			}

			return ans;
		}
	}

	static class SolutionUsingBinarySearch {
		/**
		 * Finds the k closest elements to a given value x in a sorted array. Optimized solution using binary search to
		 * find the starting point of the k-length window directly.
		 *
		 * @param arr
		 * 		The sorted input array
		 * @param k
		 * 		Number of closest elements to find
		 * @param x
		 * 		Target value
		 * @return List of k closest elements, sorted in ascending order
		 *
		 * 		Time Complexity: O(log(n-k) + k) Space Complexity: O(k)
		 */
		public List<Integer> findClosestElements(int[] arr, int k, int x) {
			// Binary search to find the starting index of the k-length window
			int left = 0;
			int right = arr.length - k;

			while (left < right) {
				int mid = left + (right - left) / 2;

				// Compare distances from x to elements at the window boundaries
				if (x - arr[mid] > arr[mid + k] - x) {
					// Window needs to move right
					left = mid + 1;
				} else {
					// Window needs to move left or stay
					right = mid;
				}
			}

			// Create result list from the window starting at 'left'
			List<Integer> result = new ArrayList<>(k);
			for (int i = 0; i < k; i++) {
				result.add(arr[left + i]);
			}

			return result;
		}
	}

	/**
	 * Alternative optimized solution using two pointers and skipping the sorting step at the end.
	 *
	 * Time Complexity: O(log n + k) Space Complexity: O(k)
	 */
	static class AlternativeSolution {
		public List<Integer> findClosestElements(int[] arr, int k, int x) {
			// Find insertion position of x
			int pos = Arrays.binarySearch(arr, x);

			// If x is not found, binarySearch returns (-(insertion_point) - 1)
			if (pos < 0) {
				pos = -(pos + 1);
			}

			// Initialize two pointers around the found position
			int left = pos - 1;
			int right = pos;

			LinkedList<Integer> result = new LinkedList<>();

			// Add k closest elements
			while (result.size() < k) {
				// If right pointer is out of bounds or left element is closer
				if (right >= arr.length || (left >= 0 && Math.abs(arr[left] - x) <= Math.abs(arr[right] - x))) {
					// Add to front to maintain order
					result.addFirst(arr[left--]);
				} else {
					// Add to end to maintain order
					result.addLast(arr[right++]);
				}
			}

			return result;
		}
	}
}
