package main.dynamic.programming.stocks;

import java.util.Arrays;

/*
Problem statement
You are given an array 'prices' of size 'n', denoting the price of stocks on 'n' days.

Rahul can buy one stock at a time, and he must sell it before buying stock on another day.

The entire transaction of selling and buying the stock requires some transaction fee, given as 'fee'.

Find the maximum profit Rahul can achieve by trading on the stocks.

Example :
Input: 'prices' =  [1, 2, 3] and 'fee' = 1

Output: 1

Explanation: We can generate the maximum profit of 1 by buying the stock on the first day for price = 1 and then selling it on the third day for price = 3.

The profit will be: 3 - 1 - 1(transaction fee) = 1
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
3 1
1 2 3

Sample Output 1 :
1

Explanation Of Sample Input 1 :
We can generate the maximum profit of 1 by buying the stock on the first day for price = 1 and then selling it on the third day for price = 3.
The profit will be: 3 - 1 - 1(transaction fee) = 1

Sample Input 2 :
4 2
1 3 5 6

Sample Output 2 :
3

Expected time complexity :
The expected time complexity is O(n).

Constraints :
1 <= 'n' <= 10 ^ 4
0 <= 'prices[i]' <= 10 ^ 5
0 <= 'fee'<= 10 ^ 5
*/
public class BestTimeToBuyAndSellStockWithTransactionFee {


	public static long getMaximumProfitUsingRecursionTabularSpaceOptimal(int n, long[] values, int fee) {

		// Initialize the last row with 0, as no transactions can be made after the last day
		long[] prev = new long[2];
		long[] curr = new long[2];

		// Fill the table in reverse order
		for (int index = n - 1; index >= 0; index--) {
			for (int buy = 0; buy <= 1; buy++) {
				if (buy == 1) {
					// Option to buy or skip
					long take = -values[index] - fee + prev[0];
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

	public static long getMaximumProfitUsingRecursionTabular(int n, long[] values, int fee) {
		long[][] dp = new long[n + 1][2];

		// Initialize the last row with 0, as no transactions can be made after the last day
		dp[n][0] = 0;
		dp[n][1] = 0;

		// Fill the table in reverse order
		for (int index = n - 1; index >= 0; index--) {
			for (int buy = 0; buy <= 1; buy++) {
				if (buy == 1) {
					// Option to buy or skip
					long take = -values[index] - fee + dp[index + 1][0];
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

	public static long getMaximumProfitUsingRecursionMemo(int n, long[] values, boolean canBuy, long[][] dp, int fee) {
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
			long take = -values[n] - fee + getMaximumProfitUsingRecursionMemo(n + 1, values, false, dp, fee);
			long notTake = getMaximumProfitUsingRecursionMemo(n + 1, values, true, dp, fee);
			dp[n][canBuyIndex] = Math.max(take, notTake);
		} else {
			// Option to sell or hold
			long sell = values[n] + getMaximumProfitUsingRecursionMemo(n + 1, values, true, dp, fee);
			long notSell = getMaximumProfitUsingRecursionMemo(n + 1, values, false, dp, fee);
			dp[n][canBuyIndex] = Math.max(sell, notSell);
		}

		// Store the result in dp array
		return dp[n][canBuyIndex];
	}

	public static long getMaximumProfitUsingRecursion(int n, long[] values, boolean canBuy, int fee) {
		// Your code goes here.

		if (values.length == n)
			return 0;

		long maxProfit;

		if (canBuy) {
			long take = -values[n] - fee + getMaximumProfitUsingRecursion(n + 1, values, false, fee);
			long notTake = getMaximumProfitUsingRecursion(n + 1, values, true, fee);
			maxProfit = Math.max(take, notTake);
		} else {
			long sell = values[n] + getMaximumProfitUsingRecursion(n + 1, values, true, fee);
			long notSell = getMaximumProfitUsingRecursion(n + 1, values, false, fee);
			maxProfit = Math.max(sell, notSell);
		}
		return maxProfit;
	}

	public static void main(String[] args) {
		long[] values = new long[] { 1, 3, 5, 6 };
		boolean canBuy = true;
		long[][] dp = new long[values.length][2];

		int fee = 2;

		for (long[] row : dp) {
			Arrays.fill(row, -1);
		}
		System.out.println(getMaximumProfitUsingRecursion(0, values, canBuy, fee));
		System.out.println(getMaximumProfitUsingRecursionMemo(0, values, canBuy, dp, fee));
		System.out.println(getMaximumProfitUsingRecursionTabular(values.length, values, fee));
		System.out.println(getMaximumProfitUsingRecursionTabularSpaceOptimal(values.length, values, fee));
	}
}
