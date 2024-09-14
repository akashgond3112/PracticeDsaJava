package main.dynamic.programming;

import java.util.Arrays;

/*
Problem statement
You are given an array 'arr' of size 'n' containing positive integers and a target sum 'k'.
Find the number of ways of selecting the elements from the array such that the sum of chosen elements is equal to the target 'k'.
Since the number of ways can be very large, print it modulo 10 ^ 9 + 7.

Example:
Input: 'arr' = [1, 1, 4, 5]

Output: 3

Explanation: The possible ways are:
[1, 4]
[1, 4]
[5]
Hence the output will be 3. Please note that both 1 present in 'arr' are treated differently.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
4 5
1 4 4 5

Sample Output 1 :
 3

Explanation For Sample Output 1:
The possible ways are:
[1, 4]
[1, 4]
[5]
Hence the output will be 3. Please note that both 1 present in 'arr' are treated differently.

Sample Input 2 :
3 2
1 1 1

Sample Output 2 :
3

Explanation For Sample Output 1:
There are three 1 present in the array. Answer is the number of ways to choose any two of them.

Sample Input 3 :
3 40
2 34 5

Sample Output 3 :
0

Expected time complexity :
The expected time complexity is O('n' * 'k').

Constraints:
1 <= 'n' <= 100
0 <= 'arr[i]' <= 1000
1 <= 'k' <= 1000

Time limit: 1 sec
*/
public class CountSubsetsWithSumK {

	public static int findWaysUsingTabularSpaceOptimal(int[] num, int n, int k) {
		// Create a 2D DP array to store the number of ways to achieve each target sum
		int[][] dp = new int[n][k + 1];

		// Initialize the first row of the DP array
		for (int i = 0; i < n; i++) {
			dp[i][0] = 1;
		}

		// Initialize the first column of the DP array
		if (num[0] <= k) {
			dp[0][num[0]] = 1;
		}

		// Fill in the DP array using bottom-up dynamic programming
		for (int ind = 1; ind < n; ind++) {
			for (int target = 1; target <= k; target++) {
				// Calculate the number of ways when the current element is not taken
				int notTaken = dp[ind - 1][target];

				// Calculate the number of ways when the current element is taken
				int taken = 0;
				if (num[ind] <= target) {
					taken = dp[ind - 1][target - num[ind]];
				}

				// Update the DP array for the current element and target sum
				dp[ind][target] = notTaken + taken;
			}
		}

		// The result is stored in the last cell of the DP array
		return dp[n - 1][k];
	}


	public static int findWaysUsingTabular(int[] num, int n, int k) {
		// Create a 2D DP array to store the number of ways to achieve each target sum
		int[][] dp = new int[n][k + 1];

		// Initialize the first row of the DP array
		for (int i = 0; i < n; i++) {
			dp[i][0] = 1;
		}

		// Initialize the first column of the DP array
		if (num[0] <= k) {
			dp[0][num[0]] = 1;
		}

		// Fill in the DP array using bottom-up dynamic programming
		for (int ind = 1; ind < n; ind++) {
			for (int target = 1; target <= k; target++) {
				// Calculate the number of ways when the current element is not taken
				int notTaken = dp[ind - 1][target];

				// Calculate the number of ways when the current element is taken
				int taken = 0;
				if (num[ind] <= target) {
					taken = dp[ind - 1][target - num[ind]];
				}

				// Update the DP array for the current element and target sum
				dp[ind][target] = notTaken + taken;
			}
		}

		// The result is stored in the last cell of the DP array
		return dp[n - 1][k];
	}


	public static int findWaysUsingMemo(int[] arr, int n, int totalSum, int[][] dp) {
		// Write your code here.

		// Base case: if we've considered all elements
		if (totalSum == 0)
			return 1;
		if (n == 0)
			return arr[n] == totalSum ? 1 : 0;

		// Check if this problem has already been solved
		if (dp[n][totalSum] != -1) {
			return dp[n][totalSum];
		}

		// Recursive cases:
		// 1. Include the current element in subset 1
		int include = 0;
		if (totalSum >= arr[n])
			include = findWays(arr, n - 1, totalSum - arr[n]);

		// 2. Exclude the current element from subset 1 (so it's in subset 2)
		int exclude = findWays(arr, n - 1, totalSum);

		// Return the count of the two possibilities
		dp[n][totalSum] = include + exclude;
		return dp[n][totalSum];
	}

	public static int findWays(int[] arr, int n, int totalSum) {
		// Write your code here.

		// Base case: if we've considered all elements
		if (totalSum == 0)
			return 1;
		if (n == 0)
			return arr[n] == totalSum ? 1 : 0;

		// Recursive cases:
		// 1. Include the current element in subset 1
		int include = 0;
		if (totalSum >= arr[n])
			include = findWays(arr, n - 1, totalSum - arr[n]);

		// 2. Exclude the current element from subset 1 (so it's in subset 2)
		int exclude = findWays(arr, n - 1, totalSum);

		// Return the count of the two possibilities
		return include + exclude;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 2, 3 };
		int n = arr.length;
		int k = 3;

		System.out.println(findWays(arr, n - 1, k));

		// Memoization table for storing results (initialize to -1)
		int[][] dp = new int[n + 1][k + 1];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}
		System.out.println(findWaysUsingMemo(arr, n - 1, k, dp));

		System.out.println(findWaysUsingTabular(arr, n, k));


	}
}
