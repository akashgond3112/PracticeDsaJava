package main.dynamic.programming.subsequence;

import java.util.Arrays;

/*
Problem statement
A thief is robbing a store and can carry a maximal weight of W into his knapsack. There are N items and the ith item weighs wi and is of value vi.
Considering the constraints of the maximum weight that a knapsack can carry, you have to find and return the maximum value that a thief can generate by stealing items.

Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 10
1 <= N <= 10^2
1<= wi <= 50
1 <= vi <= 10^2
1 <= W <= 10^3

Time Limit: 1 second
Sample Input:
1
4
1 2 4 5
5 4 8 6
5
Sample Output:
13
*/
public class Knapsack {

	static int knapsackUsingTabularSpaceOptimal2(int[] weight, int[] value, int n, int maxWeight) {

		// Array to store the results of the previous row (previous item)
		int[] prev = new int[maxWeight + 1];

		// Initialize the prev array for the first item
		for (int w = 0; w <= maxWeight; w++) {
			if (weight[0] <= w) {
				prev[w] = value[0];
			}
		}

		// Loop through all items starting from the second one
		for (int i = 1; i < n; i++) {
			for (int j = maxWeight; j >= 0; j--) {
				// 1. Exclude the current item
				int exclude = prev[j];

				// 2. Include the current item (if it fits within the weight limit)
				int include = 0;
				if (weight[i] <= j) {
					include = value[i] + prev[j - weight[i]];
				}
				// Update the current DP value
				prev[j] = Math.max(include, exclude);
			}

		}

		// The last element of prev will hold the maximum value we can achieve
		return prev[maxWeight];
	}

	static int knapsackUsingTabularSpaceOptimal(int[] weight, int[] value, int n, int maxWeight) {

		// Array to store the results of the previous row (previous item)
		int[] prev = new int[maxWeight + 1];
		int[] current = new int[maxWeight + 1];  // Current row

		// Initialize the prev array for the first item
		for (int w = 0; w <= maxWeight; w++) {
			if (weight[0] <= w) {
				prev[w] = value[0];
			}
		}

		// Loop through all items starting from the second one
		for (int i = 1; i < n; i++) {

			for (int j = 0; j <= maxWeight; j++) {
				// 1. Exclude the current item
				int exclude = prev[j];

				// 2. Include the current item (if it fits within the weight limit)
				int include = 0;
				if (weight[i] <= j) {
					include = value[i] + current[j - weight[i]];
				}

				// Update the current DP value
				current[j] = Math.max(include, exclude);
			}

			// Move to the next item by setting current as the new prev
			prev = current;
		}

		// The last element of prev will hold the maximum value we can achieve
		return prev[maxWeight];
	}

	static int knapsackUsingTabular(int[] weight, int[] value, int n, int maxWeight) {

		int[][] dp = new int[n][maxWeight + 1];

		// Initialize the first row for the first item
		for (int w = 0; w <= maxWeight; w++) {
			if (weight[0] <= w) {
				dp[0][w] = value[0];
			}
		}

		for (int i = 1; i < n; i++) {
			for (int j = 1; j <= maxWeight; j++) {
				// 1. Include the current element in subset 1
				int include = Integer.MIN_VALUE;
				if (j >= weight[i])
					include = value[i] + dp[i - 1][j - weight[i]];

				// 2. Exclude the current element from subset 1 (so it's in subset 2)
				int exclude = dp[i - 1][j];

				// Return the count of the two possibilities
				dp[i][j] = Math.max(include, exclude);

			}
		}

		return dp[n - 1][maxWeight];
	}

	static int knapsackUsingMemoization(int[] weight, int[] value, int n, int maxWeight, int[][] dp) {

		// Base Case:
		if (n == 0) {
			if (weight[0] <= maxWeight) {
				return value[0];
			} else {
				return 0;
			}
		}

		// Check if this problem has already been solved
		if (dp[n][maxWeight] != -1) {
			return dp[n][maxWeight];
		}

		// Recursive cases:
		// 1. Include the current element in subset 1
		int include = Integer.MIN_VALUE;
		if (maxWeight >= weight[n])
			include = value[n] + knapsackUsingRecursion(weight, value, n - 1, maxWeight - weight[n]);

		// 2. Exclude the current element from subset 1 (so it's in subset 2)
		int exclude = knapsackUsingRecursion(weight, value, n - 1, maxWeight);

		// Return the count of the two possibilities
		dp[n][maxWeight] = Math.max(include, exclude);
		return dp[n][maxWeight];
	}

	static int knapsackUsingRecursion(int[] weight, int[] value, int n, int maxWeight) {

		// Base Case:
		if (n == 0) {
			if (weight[0] <= maxWeight) {
				return value[0];
			} else {
				return 0;
			}
		}

		// Recursive cases:
		// 1. Include the current element in subset 1
		int include = Integer.MIN_VALUE;
		if (maxWeight >= weight[n])
			include = value[n] + knapsackUsingRecursion(weight, value, n - 1, maxWeight - weight[n]);

		// 2. Exclude the current element from subset 1 (so it's in subset 2)
		int exclude = knapsackUsingRecursion(weight, value, n - 1, maxWeight);

		// Return the count of the two possibilities
		return Math.max(include, exclude);
	}

	public static void main(String[] args) {
		int[] weight = new int[] { 1, 2, 4, 5 };
		int[] value = new int[] { 5, 4, 8, 6 };
		int n = 4;
		int maxWeight = 5;

		System.out.println(knapsackUsingRecursion(weight, value, n - 1, maxWeight));

		// Memoization table for storing results (initialize to -1)
		int[][] dp = new int[n + 1][maxWeight + 1];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}

		System.out.println(knapsackUsingMemoization(weight, value, n - 1, maxWeight, dp));
		System.out.println(knapsackUsingTabular(weight, value, n, maxWeight));
		System.out.println(knapsackUsingTabularSpaceOptimal(weight, value, n, maxWeight));
		System.out.println(knapsackUsingTabularSpaceOptimal2(weight, value, n, maxWeight));
	}
}
