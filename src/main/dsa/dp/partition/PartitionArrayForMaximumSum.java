package main.dsa.dp.partition;

import java.util.Arrays;

/*
Problem statement
You are given an array 'arr' of 'n' integers.



You have to divide the array into some subarrays such that each element is present in exactly one of the subarrays.



The length of each subarray should be at most 'k'. After partitioning all the elements of each subarray will be changed to the maximum element present in that subarray.



Find the maximum possible sum of all the elements after partitioning.



Note:

Input is given such that the answer will fit in a 32-bit integer.


Example:
Input: 'k' = 3, 'arr' = [1, 20, 13, 4, 4, 1]

Output: 80

Explanation:
We can divide the array into the following subarrays
[1,20] max of this subarray is 20 so the contribution of
this subarray in the final answer will be 20*2=40.
[13,4,4]  max of this subarray is 13 so the contribution of
this subarray in the final answer will be 13*3=39.
[1]  max of this subarray is 1 so the contribution of this
subarray in the final answer will be 1*1=1.

So, the answer will be 40 + 39 + 1 = 80.


Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
5 2
1 21 1 5 4


Expected Answer:
56


Output on console:
56


Explanation For Sample Output 1:
We can divide the array into the following subarrays
[1,21] max of this subarray is 21 so the contribution of this subarray in the final answer will be 21*2=42.
[1,5]  max of this subarray is 5 so the contribution of this subarray in the final answer will be 5*2=10.
[4]  max of this subarray is 4 so the contribution of this subarray in the final answer will be 1*4=4.
So, the answer will be 42 + 10 + 4 = 56.

.

Sample Input 2 :
1 1
6


Expected Answer:
6


Output on console:
6


Expected Time Complexity:
Try to solve this in O(n*k).


Constraints:
1 <= 'n' <= 300
0 <= 'arr[i]' <= 10^9
1 <= 'k' <= 'n'

Time limit: 1 sec
*/
public class PartitionArrayForMaximumSum {


	public static int maximumSubarrayUsingTabular(int[] arr, int k, int n) {
		int[] dp = new int[n + 1];

		// Base case: no partitions are needed beyond the end of the string
		dp[n] = 0;

		for (int index = n - 1; index >= 0; index--) {

			int length = 0;
			int maxElement = Integer.MIN_VALUE;
			int maxSum = Integer.MIN_VALUE;

			// Iterate from index to the minimum of (index + k, n)
			for (int j = index; j < Math.min(n, index + k); j++) {
				length++;
				maxElement = Math.max(maxElement, arr[j]); // get the max element in the current window

				// Calculate sum: (current partition size * max element in that partition) + recursively calculate the rest
				int sum = (length * maxElement) + dp[j + 1];
				maxSum = Math.max(maxSum, sum); // track the maximum sum
			}

			dp[index] = maxSum;

		}

		return dp[0];
	}


	public static int maximumSubarrayUsingMemo(int[] arr, int k, int n, int index, int[] dp) {
		if (index == n) {
			return 0; // base case, no more elements to consider
		}

		if (dp[index] != -1) {
			return dp[index];
		}

		int length = 0;
		int maxElement = Integer.MIN_VALUE;
		int maxSum = Integer.MIN_VALUE;

		// Iterate from index to the minimum of (index + k, n)
		for (int j = index; j < Math.min(n, index + k); j++) {
			length++;
			maxElement = Math.max(maxElement, arr[j]); // get the max element in the current window

			// Calculate sum: (current partition size * max element in that partition) + recursively calculate the rest
			int sum = (length * maxElement) + maximumSubarrayUsingMemo(arr, k, n, j + 1, dp);
			maxSum = Math.max(maxSum, sum); // track the maximum sum
		}

		return dp[index] = maxSum;
	}


	public static int maximumSubarray(int[] arr, int k, int n, int index) {
		if (index == n) {
			return 0; // base case, no more elements to consider
		}

		int length = 0;
		int maxElement = Integer.MIN_VALUE;
		int maxSum = Integer.MIN_VALUE;

		// Iterate from index to the minimum of (index + k, n)
		for (int j = index; j < Math.min(n, index + k); j++) {
			length++;
			maxElement = Math.max(maxElement, arr[j]); // get the max element in the current window

			// Calculate sum: (current partition size * max element in that partition) + recursively calculate the rest
			int sum = (length * maxElement) + maximumSubarray(arr, k, n, j + 1);
			maxSum = Math.max(maxSum, sum); // track the maximum sum
		}

		return maxSum;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 20, 13, 4, 4, 1 };
		int k = 3;
		int n = arr.length;
		System.out.println(
				"Maximum subarray sum with partition size at most " + k + ": " + maximumSubarray(arr, k, n, 0));

		int[] dp = new int[n + 1];
		Arrays.fill(dp, -1);

		System.out.println(
				"Maximum subarray sum with partition size at most " + k + ": " + maximumSubarrayUsingMemo(arr, k, n, 0,
						dp));

		System.out.println(
				"Maximum subarray sum with partition size at most " + k + ": " + maximumSubarrayUsingTabular(arr, k,
						n));
	}
}
