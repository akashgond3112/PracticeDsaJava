package main.dsa.linear.Array.learning;

import java.util.HashMap;

/*
Problem statement
You are given an array 'a' of size 'n' and an integer 'k'.
Find the length of the longest subarray of 'a' whose sum is equal to 'k'.

Example :
Input: ‘n’ = 7 ‘k’ = 3
‘a’ = [1, 2, 3, 1, 1, 1, 1]

Output: 3

Explanation: Sub arrays whose sum = ‘3’ are:

[1, 2], [3], [1, 1, 1] and [1, 1, 1]

Here, the length of the longest subarray is 3, which is our final answer.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
7 3
1 2 3 1 1 1 1

Sample Output 1 :
3

Explanation Of Sample Input 1 :
Sub arrays whose sum = ‘3’ are:
[1, 2], [3], [1, 1, 1] and [1, 1, 1]
Here, the length of the longest subarray is 3, which is our final answer.

Sample Input 2 :
4 2
1 2 1 3

Sample Output 2 :
1

Sample Input 3 :
5 2
2 2 4 1 2

Sample Output 3 :
1

Expected time complexity :
The expected time complexity is O(n).

Constraints :
1 <= 'n' <= 5 * 10 ^ 6
1 <= 'k' <= 10^18
0 <= 'a[i]' <= 10 ^ 9

Time Limit: 1-second
*/
public class LongestSubarrayWithSumK {

	public static long longestSubarrayWithSumKOptimal(long[] a, long k) {

		long sum = 0;  // Cumulative sum
		long maxLength = 0;  // To store the maximum length of subarray

		int left = 0;
		int right = 0;

		while (right < a.length) {
			while (left <= right && sum > k) {
				sum -= a[left];
				left++;
			}
			if (sum == k) {
				maxLength = Math.max(maxLength, right - left + 1);
			}

			right++;
			if (right < a.length) {
				sum += a[right];
			}

		}
		return maxLength;

	}

	public static long longestSubarrayWithSumK(long[] a, long k) {
		// HashMap to store the cumulative sum and its corresponding index
		HashMap<Long, Integer> map = new HashMap<>();
		long sum = 0;  // Cumulative sum
		long maxLength = 0;  // To store the maximum length of subarray

		for (int i = 0; i < a.length; i++) {
			sum += a[i];

			// If the cumulative sum is equal to k, update the maxLength
			if (sum == k) {
				maxLength = i + 1;
			}

			long rem = sum - k;  // The remaining sum needed to form sum 'k'

			// If 'rem' exists in the map, it means a subarray with sum 'k' exists
			if (map.containsKey(rem)) {
				long len = i - map.get(rem);  // Calculate the length of the subarray
				maxLength = Math.max(maxLength, len);  // Update the maximum length
			}

			// Add the current cumulative sum and its index to the map if it doesn't already exist
			// (to maintain the earliest occurrence of this sum)
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}

		return maxLength;
	}

	public static void main(String[] args) {
		long[] arr = { 1, 2, 3, 7, 5, 0, 0 };
		long k = 12;

		System.out.println("Longest subarray with sum " + k + ": " + longestSubarrayWithSumK(arr, k));
		System.out.println("Longest subarray with sum " + k + ": " + longestSubarrayWithSumKOptimal(arr, k));
	}
}
