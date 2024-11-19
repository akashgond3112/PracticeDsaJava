package main.dsa.dp;

import java.util.Arrays;

/*
Problem statement
You are given an array 'arr' containing 'n' non-negative integers.



Your task is to partition this array into two subsets such that the absolute difference between subset sums is minimum.



You just need to find the minimum absolute difference considering any valid division of the array elements.



Note:

1. Each array element should belong to exactly one of the subsets.

2. Subsets need not always be contiguous.
For example, for the array : [1, 2, 3], some of the possible divisions are
   a) {1,2} and {3}
   b) {1,3} and {2}.

3. Subset-sum is the sum of all the elements in that subset.
Example:
Input: 'n' = 5, 'arr' = [3, 1, 5, 2, 8].

Ouput: 1

Explanation: We can partition the given array into {3, 1, 5} and {2, 8}.
This will give us the minimum possible absolute difference i.e. (10 - 9 = 1).
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
4
1 2 3 4
Sample Output 1:
0
Explanation for sample input 1:
We can partition the given array into {2,3} and {1,4}.
This will give us the minimum possible absolute difference i.e. (5 - 5 = 0) in this case.
Sample Input 2:
3
8 6 5
Sample Output 2:
3
Explanation for sample input 2:
We can partition the given array into {8} and {6,5}.
This will give us the minimum possible absolute difference i.e. (11 - 8 = 3).
Expected time complexity:
The expected time complexity is O(n * 𝚺 'arr'[i]), where 𝚺 'arr'[i] denotes the sum of all elements in 'arr'.
Constraints:
1 <= 'n' <= 10^3
0 <= 'arr'[i] <= 10^3
0 <= 𝚺 'arr'[i] <= 10^4,

where 𝚺 'arr'[i] denotes the sum of all elements in 'arr'.

Time Limit: 1sec
*/
public class ArrayPartitionWithMinimumDifference {

	public static int minSubsetSumDifferenceUsingTabularSpaceOptimisation(int[] arr, int n, int k) {
		// Write your code here.
		boolean[] prev = new boolean[k + 1];

		if (arr[0] <= k)
			prev[0] = true;

		// Initialize the first column of the DP table
		if (arr[0] <= k) {
			prev[arr[0]] = true;
		}

		for (int i = 1; i < n; i++) {
			boolean[] curr = new boolean[k + 1];
			curr[0] = true;

			for (int j = 1; j <= k; j++) {

				boolean notTake = prev[j];

				boolean take = false;

				if (j >= arr[i]) {
					take = prev[j - arr[i]];
				}

				curr[j] = notTake || take;
			}
			prev = curr;
		}


		// Find the minimum subset sum difference by checking sums up to totalSum/2
		int min = (int) 1e9;
		for (int i = 0; i <= k / 2; i++) {
			if (prev[i]) {
				min = Math.min(min, Math.abs(k - 2 * i));
			}
		}

		return min;
	}

	public static int minSubsetSumDifferenceUsingTabular(int[] arr, int n, int k) {
		// Write your code here.

		boolean[][] dp = new boolean[n][k + 1];

		for (int i = 0; i < n; i++) {
			dp[i][0] = true;
		}

		if (arr[0] <= k)
			dp[0][arr[0]] = true;

		for (int i = 1; i < n; i++) {
			for (int j = 1; j <= k; j++) {

				boolean notTake = dp[i - 1][j];

				boolean take = false;

				if (j >= arr[i]) {
					take = dp[i - 1][j - arr[i]];
				}

				dp[i][j] = notTake || take;

			}
		}

		// Find the minimum subset sum difference by checking sums up to totalSum/2
		int min = (int) 1e9;
		for (int i = 0; i <= k / 2; i++) {
			if (dp[n - 1][i]) {
				min = Math.min(min, Math.abs(k - 2 * i));
			}
		}

		return min;
	}

	public static int minSubsetSumDifferenceMemo(int[] arr, int n, int currentSum, int totalSum, int[][] dp) {
		// Base case: if we've considered all elements
		if (n == 0) {
			int subset2 = totalSum - currentSum;
			return Math.abs(currentSum - subset2);
		}

		// Check if this problem has already been solved
		if (dp[n][currentSum] != -1) {
			return dp[n][currentSum];
		}

		// Recursive cases:
		// 1. Include the current element in subset 1
		int include = minSubsetSumDifferenceMemo(arr, n - 1, currentSum + arr[n - 1], totalSum, dp);

		// 2. Exclude the current element from subset 1 (so it's in subset 2)
		int exclude = minSubsetSumDifferenceMemo(arr, n - 1, currentSum, totalSum, dp);

		// Store the result in dp array
		dp[n][currentSum] = Math.min(include, exclude);
		return dp[n][currentSum];
	}

	public static int minSubsetSumDifference(int[] arr, int n, int currentSum, int totalSum) {
		// Base case: if we've considered all elements
		if (n == 0) {
			int subset2 = totalSum - currentSum;
			return Math.abs(currentSum - subset2);
		}

		// Recursive cases:
		// 1. Include the current element in subset 1
		int include = minSubsetSumDifference(arr, n - 1, currentSum + arr[n - 1], totalSum);

		// 2. Exclude the current element from subset 1 (so it's in subset 2)
		int exclude = minSubsetSumDifference(arr, n - 1, currentSum, totalSum);

		// Return the minimum of the two possibilities
		return Math.min(include, exclude);
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4 };
		int n = arr.length;

		int totalSum = 0;
		for (int value : arr) {
			totalSum += value;
		}

		System.out.println(minSubsetSumDifference(arr, n, 0, totalSum));

		// Memoization table for storing results (initialize to -1)
		int[][] dp = new int[n + 1][totalSum + 1];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}

		// Call the memoized function
		System.out.println(minSubsetSumDifferenceMemo(arr, n, 0, totalSum, dp));

		System.out.println(minSubsetSumDifferenceUsingTabular(arr, n, totalSum));
		System.out.println(minSubsetSumDifferenceUsingTabularSpaceOptimisation(arr, n, totalSum));


	}
}
