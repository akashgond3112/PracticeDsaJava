package main.dsa.linear.Array.learning;

import java.util.Arrays;

/*
Problem statement
You are given an array 'arr' of length 'n', consisting of integers.

A subarray is a contiguous segment of an array. In other words, a subarray can be formed by removing 0 or more integers from the beginning and 0 or more integers from the end of an array.

Find the sum of the subarray (including empty subarray) having maximum sum among all subarrays.
The sum of an empty subarray is 0.

Example :
Input: 'arr' = [1, 2, 7, -4, 3, 2, -10, 9, 1]

Output: 11

Explanation: The subarray yielding the maximum sum is [1, 2, 7, -4, 3, 2].
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
9
1 2 7 -4 3 2 -10 9 1

Sample Output 1 :
11

Explanation for Sample 1 :
The subarray yielding the maximum sum is [1, 2, 7, -4, 3, 2].

Sample Input 2 :
6
10 20 -30 40 -50 60

Sample Output 2 :
60

Sample Input 3 :
3
-3 -5 -6

Sample Output 3 :
0

Expected time complexity :
The expected time complexity is O(n).

Constraints :
1 <= 'n' <= 10 ^ 6
-10 ^ 6 <= 'arr[i]' <= 10 ^ 6

Time limit: 1sec
*/
public class MaximumSubarraySum {

	public static long[] getMaxSubarraySum(int[] arr, int n) {
		// write your code here
		long max = Integer.MIN_VALUE;
		long sum = 0;

		int start = 0;
		int end = 0;
		int ansStart = -1;
		int ansEnd = -1;

		for (int i = 0; i < n; i++) {
			if (sum == 0)
				start = i;
			sum += arr[i];

			if (sum > max) {
				ansStart = start;
				ansEnd = i;
				max = sum;
			}
			if (sum < 0) {
				sum = 0;
			}
		}

		return new long[] { ansStart, ansEnd };
	}


	// Kadane's Voting Algorithm
	// TC : O(N)
	// SC : O(1) -> No extra space
	public static long maxSubarraySum(int[] arr, int n) {
		// write your code here
		long max = Integer.MIN_VALUE;
		long sum = 0;

		for (int i = 0; i < n; i++) {
			sum += arr[i];

			if (sum > max) {
				max = sum;
			}
			if (sum < 0) {
				sum = 0;
			}
		}

		if (max < 0)
			return 0;
		return max;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 7, -4, 3, 2, -10, 9, 1 };
		System.out.println(maxSubarraySum(arr, arr.length));
		System.out.println(Arrays.toString(getMaxSubarraySum(arr, arr.length)));
	}
}
