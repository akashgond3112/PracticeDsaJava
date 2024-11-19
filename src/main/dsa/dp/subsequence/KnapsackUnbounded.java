package main.dsa.dp.subsequence;

import java.util.Arrays;

/*
You are given ‘n’ items with certain ‘profit’ and ‘weight’ and a knapsack with weight capacity ‘w’.



You need to fill the knapsack with the items in such a way that you get the maximum profit. You are allowed to take one item multiple times.



Example:
Input:
'n' = 3, 'w' = 10,
'profit' = [5, 11, 13]
'weight' = [2, 4, 6]

Output: 27

Explanation:
We can fill the knapsack as:

1 item of weight 6 and 1 item of weight 4.
1 item of weight 6 and 2 items of weight 2.
2 items of weight 4 and 1 item of weight 2.
5 items of weight 2.

The maximum profit will be from case 3 = 11 + 11 + 5 = 27. Therefore maximum profit = 27.


Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
3 15
7 2 4
5 10 20

Expected Answer:
21


Output on console:
21


Explanation of Sample Input 1
The given knapsack capacity is 15. We can fill the knapsack as [1, 1, 1] giving us profit 21 and as [1,2] giving us profit 9. Thus maximum profit will be 21.


Sample Input 2
2 3
6 12
4 17

Expected Answer:
0


Output on console:
0


Explanation of Sample Input 2:
We can clearly see that no item has weight less than knapsack capacity. Therefore we can not fill knapsack with any item.


Expected Time Complexity:
Try to solve this in O(n*w).


Constraints
1 <= n <= 10^3
1 <= w <= 10^3
1 <= profit[i] , weight[i] <= 10^8

Time Limit: 1 sec
*/
public class KnapsackUnbounded {

	public static int unboundedKnapsackUsingTabularSpaceOptimal1D(int[] weight, int[] value, int n, int maxWeight) {
		// Base Case:
		int[] prev = new int[maxWeight + 1];

		// Initialize the first row for the first item
		for (int w = 0; w <= maxWeight; w++) {
			prev[w] = (w / weight[0]) * value[0];
		}

		for (int i = 1; i < n; i++) {

			for (int j = 1; j <= maxWeight; j++) {
				// 1. Include the current element in subset 1
				int include = Integer.MIN_VALUE;
				if (j >= weight[i])
					include = value[i] + prev[j - weight[i]];

				// 2. Exclude the current element from subset 1 (so it's in subset 2)
				int exclude = prev[j];

				// Return the count of the two possibilities
				prev[j] = Math.max(include, exclude);

			}
		}
		return prev[maxWeight];
	}

	public static int unboundedKnapsackUsingTabularSpaceOptimal(int[] weight, int[] value, int n, int maxWeight) {
		// Base Case:
		int[] prev = new int[maxWeight + 1];
		int[] current = new int[maxWeight + 1];  // Current row

		// Initialize the first row for the first item
		for (int w = 0; w <= maxWeight; w++) {
			prev[w] = (w / weight[0]) * value[0];
		}

		for (int i = 1; i < n; i++) {
			for (int j = 1; j <= maxWeight; j++) {
				// 1. Include the current element in subset 1
				int include = Integer.MIN_VALUE;
				if (j >= weight[i])
					include = value[i] + current[j - weight[i]];

				// 2. Exclude the current element from subset 1 (so it's in subset 2)
				int exclude = prev[j];

				// Return the count of the two possibilities
				current[j] = Math.max(include, exclude);

			}
			prev = current;
		}
		return prev[maxWeight];
	}

	public static int unboundedKnapsackUsingTabular(int[] weight, int[] value, int n, int maxWeight) {
		// Base Case:
		int[][] dp = new int[n][maxWeight + 1];

		// Initialize the first row for the first item
		for (int w = 0; w <= maxWeight; w++) {
			dp[0][w] = (w / weight[0]) * value[0];
		}

		for (int i = 1; i < n; i++) {
			for (int j = 1; j <= maxWeight; j++) {
				// 1. Include the current element in subset 1
				int include = Integer.MIN_VALUE;
				if (j >= weight[i])
					include = value[i] + dp[i][j - weight[i]];

				// 2. Exclude the current element from subset 1 (so it's in subset 2)
				int exclude = dp[i - 1][j];

				// Return the count of the two possibilities
				dp[i][j] = Math.max(include, exclude);

			}
		}
		return dp[n - 1][maxWeight];
	}

	public static int unboundedKnapsackUsingMemo(int[] weight, int[] value, int n, int maxWeight, int[][] dp) {
		// Base Case:
		if (n == 0) {
			// If we can take the first item multiple times
			if (weight[0] <= maxWeight) {
				return (maxWeight / weight[0]) * value[0];
			} else {
				return 0;
			}
		}

		// Check if this problem has already been solved
		if (dp[n][maxWeight] != -1) {
			return dp[n][maxWeight];
		}

		// Recursive cases:
		// 1. Include the current item (we do not reduce 'n' because it's unbounded)
		int include = Integer.MIN_VALUE;
		if (maxWeight >= weight[n]) {
			include = value[n] + unboundedKnapsack(weight, value, n, maxWeight - weight[n]);
		}

		// 2. Exclude the current item
		int exclude = unboundedKnapsack(weight, value, n - 1, maxWeight);

		// Return the maximum of both possibilities
		// Return the count of the two possibilities
		dp[n][maxWeight] = Math.max(include, exclude);
		return dp[n][maxWeight];
	}

	public static int unboundedKnapsack(int[] weight, int[] value, int n, int maxWeight) {
		// Base Case:
		if (n == 0) {
			// If we can take the first item multiple times
			if (weight[0] <= maxWeight) {
				return (maxWeight / weight[0]) * value[0];
			} else {
				return 0;
			}
		}

		// Recursive cases:
		// 1. Include the current item (we do not reduce 'n' because it's unbounded)
		int include = Integer.MIN_VALUE;
		if (maxWeight >= weight[n]) {
			include = value[n] + unboundedKnapsack(weight, value, n, maxWeight - weight[n]);
		}

		// 2. Exclude the current item
		int exclude = unboundedKnapsack(weight, value, n - 1, maxWeight);

		// Return the maximum of both possibilities
		return Math.max(include, exclude);
	}

	public static void main(String[] args) {
		int[] value = new int[] { 5, 11, 13 };
		int[] weight = new int[] { 2, 4, 6 };
		int n = value.length;
		int maxWeight = 10;

		System.out.println(unboundedKnapsack(weight, value, n - 1, maxWeight));

		// Memoization table for storing results (initialize to -1)
		int[][] dp = new int[n + 1][maxWeight + 1];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}

		System.out.println(unboundedKnapsackUsingMemo(weight, value, n - 1, maxWeight, dp));
		System.out.println(unboundedKnapsackUsingTabular(weight, value, n, maxWeight));
		System.out.println(unboundedKnapsackUsingTabularSpaceOptimal(weight, value, n, maxWeight));
		System.out.println(unboundedKnapsackUsingTabularSpaceOptimal1D(weight, value, n, maxWeight));

	}
}
