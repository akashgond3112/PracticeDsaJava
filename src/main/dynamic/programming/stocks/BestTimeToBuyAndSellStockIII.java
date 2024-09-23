package main.dynamic.programming.stocks;

import java.util.Arrays;

/*
Problem statement
You are Harshad Mehta’s friend. He told you the price of a particular stock for the next ‘n’ days.

You are given an array ‘prices’ which such that ‘prices[i]’ denotes the price of the stock on the ith day.

You don't want to do more than 2 transactions. Find the maximum profit that you can earn from these transactions.

Note

1. Buying a stock and then selling it is called one transaction.

2. You are not allowed to do multiple transactions at the same time. This means you have to sell the stock before buying it again.
Example:
Input: ‘n’ = 7, ‘prices’ = [3, 3, 5, 0, 3, 1, 4].

Output: 6

Explanation:
The maximum profit can be earned by:
Transaction 1: Buying the stock on day 4 (price 0) and then selling it on day 5 (price 3).
Transaction 2: Buying the stock on day 6 (price 1) and then selling it on day 6 (price 4).
Total profit earned will be (3 - 0) + ( 4 - 1) = 6.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
6
1 3 1 2 4 8
Sample Output 1:
9
Explanation Of Sample Input 1 :
The maximum profit can be earned by:
Transaction 1: Buying the stock on day 1 (price 1) and then selling it on day 2 (price 3).
Transaction 2: Buying the stock on day 3 (price 1) and then selling it on day 6 (price 8).
Total profit earned will be (3 - 1) + ( 8 - 1) = 9.
Sample Input 2:
5
5 4 3 2 1
Sample Output 2:
0
Explanation of Sample Output 2:
It's better not to do any transactions as the stock prices are decreasing.
Expected time complexity:
The expected time complexity is O(n).
Constraints:
1 <= ‘n’ <= 10^6
0 <= ‘prices[i]’ <= 10^3

Where ‘prices[i]’ is the stock price on ith day.

Time Limit: 1 sec.
*/
public class BestTimeToBuyAndSellStockIII {

	public static int maxProfitUsingRecursionUsingTabularSpaceOptimalMore(int[] values, int maxTransaction) {
		int n = values.length;
		int[][] dp = new int[2][maxTransaction + 1];

		// Start iterating from the last day to the first day
		for (int i = n - 1; i >= 0; i--) {
			int[][] newDp = new int[2][maxTransaction + 1]; // This array will hold the results for the current day
			for (int transactions = 1; transactions <= maxTransaction; transactions++) {
				// Option to buy (transactions % 2 == 1) or skip
				if (transactions % 2 == 1) {
					int take = -values[i] + dp[1][transactions];
					int notTake = dp[0][transactions];
					newDp[0][transactions] = Math.max(take, notTake);
				} else { // Option to sell (transactions % 2 == 0) or hold
					int sell = values[i] + dp[0][transactions - 1];
					int notSell = dp[1][transactions];
					newDp[1][transactions] = Math.max(sell, notSell);
				}
			}
			dp = newDp; // Move to the next day
		}

		// The answer will be in dp[0][maxTransaction] (starting with the first day and the option to buy)
		return dp[0][maxTransaction];
	}

	public static int maxProfitUsingRecursionUsingTabularSpaceOptimal(int[] values, int maxTransaction) {
		int n = values.length;
		int[][] after = new int[2][maxTransaction + 1];
		int[][] curr = new int[2][maxTransaction + 1];

		// Initialize dp array
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= maxTransaction; j++) {
				curr[0][j] = 0; // Can't buy on day 0
				curr[1][j] = 0; // No transactions yet
			}
		}

		// Fill the dp table
		for (int i = n - 1; i >= 0; i--) {
			for (int cap = 1; cap <= maxTransaction; cap++) {
				// Option to buy
				curr[0][cap] = Math.max(-values[i] + after[1][cap], after[0][cap]);
				// Option to sell
				curr[1][cap] = Math.max(values[i] + after[0][cap - 1], after[1][cap]);
			}
			after = curr;
		}

		// The result is in dp[0][0][maxTransaction] since we start with the first day and can buy
		return after[0][maxTransaction];
	}

	public static int maxProfitUsingRecursionUsingTabular(int[] values, int maxTransaction) {
		int n = values.length;
		int[][][] dp = new int[n + 1][2][maxTransaction + 1];

		// Initialize dp array
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= maxTransaction; j++) {
				dp[i][0][j] = 0; // Can't buy on day 0
				dp[i][1][j] = 0; // No transactions yet
			}
		}

		// Fill the dp table
		for (int i = n - 1; i >= 0; i--) {
			for (int cap = 1; cap <= maxTransaction; cap++) {
				// Option to buy
				dp[i][0][cap] = Math.max(-values[i] + dp[i + 1][1][cap], dp[i + 1][0][cap]);
				// Option to sell
				dp[i][1][cap] = Math.max(values[i] + dp[i + 1][0][cap - 1], dp[i + 1][1][cap]);
			}
		}

		// The result is in dp[0][0][maxTransaction] since we start with the first day and can buy
		return dp[0][0][maxTransaction];
	}


	public static int maxProfitUsingRecursionUsingMemo(int[] values, int i, int maxTransaction, int canBuyIndex,
			int[][][] dp) {
		// Base case: if we've processed all days or reached max transactions
		if (i >= values.length || maxTransaction == 0) {
			return 0;
		}

		// If the result for this state is already calculated, return it
		if (dp[i][canBuyIndex][maxTransaction] != -1) {
			return dp[i][canBuyIndex][maxTransaction];
		}

		int maxProfit;

		if (canBuyIndex == 0) {
			// Option to buy or skip
			int take = -values[i] + maxProfitUsingRecursionUsingMemo(values, i + 1, maxTransaction, 1, dp);
			int notTake = maxProfitUsingRecursionUsingMemo(values, i + 1, maxTransaction, 0, dp);
			maxProfit = Math.max(take, notTake);
		} else {
			// Option to sell or hold
			int sell = values[i] + maxProfitUsingRecursionUsingMemo(values, i + 1, maxTransaction - 1, 0, dp);
			int notSell = maxProfitUsingRecursionUsingMemo(values, i + 1, maxTransaction, 1, dp);
			maxProfit = Math.max(sell, notSell);
		}

		// Store the result in the dp array
		return dp[i][canBuyIndex][maxTransaction] = maxProfit;
	}

	public static int maxProfitUsingRecursion(int[] values, int i, int maxTransaction, boolean canBuy) {
		// Base case: if we've processed all days or reached max transactions
		if (i >= values.length || maxTransaction == 0) {
			return 0;
		}

		int maxProfit;

		if (canBuy) {
			// Option to buy or skip
			int take = -values[i] + maxProfitUsingRecursion(values, i + 1, maxTransaction, false);
			int notTake = maxProfitUsingRecursion(values, i + 1, maxTransaction, true);
			maxProfit = Math.max(take, notTake);
		} else {
			// Option to sell or hold
			int sell = values[i] + maxProfitUsingRecursion(values, i + 1, maxTransaction - 1, true);
			int notSell = maxProfitUsingRecursion(values, i + 1, maxTransaction, false);
			maxProfit = Math.max(sell, notSell);
		}

		return maxProfit;
	}

	public static void main(String[] args) {

		int[] values = new int[] { 3, 3, 5, 0, 3, 1, 4 };
		boolean canBuy = true;
		int n = values.length;
		int maxTransaction = 2; // Set the maximum number of transactions
		// Creating a 3D dp array of size [n][2][maxTransaction + 1]
		int[][][] dp = new int[n][2][maxTransaction + 1];

		// Initialize the dp array with -1
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}


		System.out.println(maxProfitUsingRecursion(values, 0, 2, canBuy));
		System.out.println(maxProfitUsingRecursionUsingMemo(values, 0, 2, 0, dp));
		System.out.println(maxProfitUsingRecursionUsingTabular(values, 2));
		System.out.println(maxProfitUsingRecursionUsingTabularSpaceOptimal(values, 2));

		System.out.println(maxProfitUsingRecursionUsingTabularSpaceOptimalMore(values, 4));

	}
}
