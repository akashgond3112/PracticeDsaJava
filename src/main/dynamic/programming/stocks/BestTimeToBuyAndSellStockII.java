package main.dynamic.programming.stocks;

import java.util.Arrays;

/*
Problem statement
You have been given stock values/prices for N number of days. Every i-th day signifies the price of a stock on that day. Your task is to find the maximum profit which you can make by buying and selling the stocks.

 Note :
You may make as many transactions as you want but can not have more than one transaction at a time i.e, if you have the stock, you need to sell it first, and then only you can buy it again.
Detailed explanation ( Input/output format, Notes, Images )
Constraints :
1 <= t <= 10^2
0 <= N <= 10^5
Time Limit: 1 sec
Sample Input 1 :
1
7
1 2 3 4 5 6 7
Sample Output 1 :
6
Explanation :
We can make the maximum profit by buying the stock on the first day and selling it on the last day.
Sample Input 2 :
1
7
7 6 5 4 3 2 1
Sample Output 2 :
0
Explanation :
We can make the maximum profit by not buying the stock.
*/
public class BestTimeToBuyAndSellStockII {


	public static long getMaximumProfitUsingRecursionTabularSpaceOptimalUsingVariable(int n, long[] values) {

		// Initialize the last row with 0, as no transactions can be made after the last day
		long aheadBuy = 0;
		long aheadNotBuy = 0;
		long currBuy;
		long currNotBuy;

		// Fill the table in reverse order
		for (int index = n - 1; index >= 0; index--) {

			// Option to sell or hold
			long sell = values[index] + aheadBuy;
			long notSell = aheadNotBuy;
			currNotBuy = Math.max(sell, notSell);

			// Option to buy or skip
			long take = -values[index] + aheadNotBuy;
			long notTake = aheadBuy;
			currBuy = Math.max(take, notTake);

			aheadBuy = currBuy;
			aheadNotBuy = currNotBuy;
		}

		// The result will be stored at dp[0][1], as we start from day 0 with the option to buy
		return aheadBuy;
	}

	public static long getMaximumProfitUsingRecursionTabularSpaceOptimal(int n, long[] values) {

		// Initialize the last row with 0, as no transactions can be made after the last day
		long[] prev = new long[2];
		long[] curr = new long[2];

		// Fill the table in reverse order
		for (int index = n - 1; index >= 0; index--) {
			for (int buy = 0; buy <= 1; buy++) {
				if (buy == 1) {
					// Option to buy or skip
					long take = -values[index] + prev[0];
					long notTake = prev[1];
					curr[buy] = Math.max(take, notTake);
				} else {
					// Option to sell or hold
					long sell = values[index] + prev[1];
					long notSell = prev[0];
					curr[buy] = Math.max(sell, notSell);
				}
			}

			prev = curr;
		}

		// The result will be stored at dp[0][1], as we start from day 0 with the option to buy
		return prev[1];
	}

	public static long getMaximumProfitUsingRecursionTabular(int n, long[] values) {
		long[][] dp = new long[n + 1][2];

		// Initialize the last row with 0, as no transactions can be made after the last day
		dp[n][0] = 0;
		dp[n][1] = 0;

		// Fill the table in reverse order
		for (int index = n - 1; index >= 0; index--) {
			for (int buy = 0; buy <= 1; buy++) {
				if (buy == 1) {
					// Option to buy or skip
					long take = -values[index] + dp[index + 1][0];
					long notTake = dp[index + 1][1];
					dp[index][buy] = Math.max(take, notTake);
				} else {
					// Option to sell or hold
					long sell = values[index] + dp[index + 1][1];
					long notSell = dp[index + 1][0];
					dp[index][buy] = Math.max(sell, notSell);
				}
			}
		}

		// The result will be stored at dp[0][1], as we start from day 0 with the option to buy
		return dp[0][1];
	}

	public static long getMaximumProfitUsingRecursionMemo(int n, long[] values, boolean canBuy, long[][] dp) {
		// Base case: if we've processed all days, return 0 profit
		if (n == values.length)
			return 0;

		// Convert boolean to index for dp array (0 for canBuy, 1 for can'tBuy)
		int canBuyIndex = canBuy ? 1 : 0;

		// Check if the value is already computed
		if (dp[n][canBuyIndex] != -1) {
			return dp[n][canBuyIndex];
		}

		if (canBuy) {
			// Option to buy or skip
			long take = -values[n] + getMaximumProfitUsingRecursionMemo(n + 1, values, false, dp);
			long notTake = getMaximumProfitUsingRecursionMemo(n + 1, values, true, dp);
			dp[n][canBuyIndex] = Math.max(take, notTake);
		} else {
			// Option to sell or hold
			long sell = values[n] + getMaximumProfitUsingRecursionMemo(n + 1, values, true, dp);
			long notSell = getMaximumProfitUsingRecursionMemo(n + 1, values, false, dp);
			dp[n][canBuyIndex] = Math.max(sell, notSell);
		}

		// Store the result in dp array
		return dp[n][canBuyIndex];
	}

	public static long getMaximumProfitUsingRecursion(int n, long[] values, boolean canBuy) {
		// Your code goes here.

		if (values.length == n)
			return 0;

		long maxProfit;

		if (canBuy) {
			long take = -values[n] + getMaximumProfitUsingRecursion(n + 1, values, false);
			long notTake = getMaximumProfitUsingRecursion(n + 1, values, true);
			maxProfit = Math.max(take, notTake);
		} else {
			long sell = values[n] + getMaximumProfitUsingRecursion(n + 1, values, true);
			long notSell = getMaximumProfitUsingRecursion(n + 1, values, false);
			maxProfit = Math.max(sell, notSell);
		}
		return maxProfit;
	}

	public static void main(String[] args) {
		long[] values = new long[] { 1, 2, 3, 4, 5, 6, 7 };
		boolean canBuy = true;
		long[][] dp = new long[values.length][2];

		for (long[] row : dp) {
			Arrays.fill(row, -1);
		}
		System.out.println(getMaximumProfitUsingRecursion(0, values, canBuy));
		System.out.println(getMaximumProfitUsingRecursionMemo(0, values, canBuy, dp));
		System.out.println(getMaximumProfitUsingRecursionTabular(values.length, values));
		System.out.println(getMaximumProfitUsingRecursionTabularSpaceOptimal(values.length, values));
		System.out.println(getMaximumProfitUsingRecursionTabularSpaceOptimalUsingVariable(values.length, values));
	}
}
