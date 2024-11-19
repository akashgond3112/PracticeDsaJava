package main.dsa.dp.stocks;

import java.util.Arrays;

/*
Problem statement
You have been given an array 'PRICES' consisting of 'N' integers where PRICES[i] denotes the price of a given stock on the i-th day. You are also given an integer 'K' denoting the number of possible transactions you can make.

Your task is to find the maximum profit in at most K transactions. A valid transaction involves buying a stock and then selling it.

Note
You can’t engage in multiple transactions simultaneously, i.e. you must sell the stock before rebuying it.
For Example
Input: N = 6 , PRICES = [3, 2, 6, 5, 0, 3] and K = 2.
Output: 7

Explanation : The optimal way to get maximum profit is to buy the stock on day 2(price = 2) and sell it on day 3(price = 6) and rebuy it on day 5(price = 0) and sell it on day 6(price = 3). The maximum profit will be (6 - 2) + (3 - 0) = 7.
Detailed explanation ( Input/output format, Notes, Images )
Constraints
1 <= T <= 100
1 <= N <= 5000
0 <= K <= N/2
0 <= ARR[i] <=10^5

Time Limit : 1 sec
Sample Input 1
2
5 2
8 5 1 3 10
4 2
10 8 6 2
Sample Output 1
9
0
Explanation for Sample 1
Test Case 1: In this case, we can make a maximum of 2 transactions. The optimal way to get maximum profit is to make only 1 transaction, i.e., buy the stock on day 3 (price = 1) and sell it on day 5(price = 10). The profit will be 10 - 1 = 9.

Test Case 2: In the second test case, we can make a maximum of 2 transactions. The optimal way to get maximum profit is to make 0 transactions because the price of stock is continuously decreasing and we will have a loss if we buy and sell the stock.
Sample Input 2
2
4 0
2 6 5 2
4 2
1 2 3 5
Sample Output 2
0
4
*/
public class BestTimeToBuyAndSellStockIV {

	public static int maximumProfitUsingRecursionTabularSpaceOptimal(int[] prices, int n, int k, int i,
			int transaction) {
		// Write your code here.

		int[] prev = new int[2 * k + 1];
		int[] curr = new int[2 * k + 1];

		for (int i1 = n - 1; i1 >= 0; i1--) {
			for (int j = (2 * k) - 1; j >= 0; j--) {
				if (j % 2 == 0) {
					curr[j] = Math.max((-prices[i1] + prev[j + 1]), prev[j]);
				} else {
					curr[j] = Math.max((prices[i1] + prev[j + 1]), prev[j]);
				}
			}
			prev = curr;
		}
		return prev[0];
	}


	public static int maximumProfitUsingRecursionTabular(int[] prices, int n, int k, int i, int transaction) {
		// Write your code here.

		int[][] dp = new int[n + 1][2 * k + 1];

		for (int i1 = n - 1; i1 >= 0; i1--) {
			for (int j = (2 * k) - 1; j >= 0; j--) {
				if (j % 2 == 0) {
					dp[i1][j] = Math.max((-prices[i1] + dp[i1 + 1][j + 1]), dp[i1 + 1][j]);
				} else {
					dp[i1][j] = Math.max((prices[i1] + dp[i1 + 1][j + 1]), dp[i1 + 1][j]);
				}
			}
		}
		return dp[0][0];
	}


	public static int maximumProfitUsingRecursionMemo(int[] prices, int n, int k, int i, int transaction, int[][] dp) {
		// Write your code here.
		if (i == n || transaction == 2 * k)
			return 0;


		// If the result for this state is already calculated, return it
		if (dp[i][transaction] != -1) {
			return dp[i][transaction];
		}

		if (transaction % 2 == 0) {

			return dp[i][transaction] = Math.max(
					(-prices[i] + maximumProfitUsingRecursionMemo(prices, n, k, i + 1, transaction + 1, dp)),
					maximumProfitUsingRecursionMemo(prices, n, k, i + 1, transaction, dp));
		} else {

			return dp[i][transaction] = Math.max(
					(prices[i] + maximumProfitUsingRecursionMemo(prices, n, k, i + 1, transaction + 1, dp)),
					(maximumProfitUsingRecursionMemo(prices, n, k, i + 1, transaction, dp)));
		}
	}

	public static int maximumProfitUsingRecursion(int[] prices, int n, int k, int i, int transaction) {
		// Write your code here.
		if (i == n || transaction == 2 * k)
			return 0;

		if (transaction % 2 == 0) {

			return Math.max((-prices[i] + maximumProfitUsingRecursion(prices, n, k, i + 1, transaction + 1)),
					maximumProfitUsingRecursion(prices, n, k, i + 1, transaction));
		} else {

			return Math.max((prices[i] + maximumProfitUsingRecursion(prices, n, k, i + 1, transaction + 1)),
					(maximumProfitUsingRecursion(prices, n, k, i + 1, transaction)));
		}
	}

	public static void main(String[] args) {
		int[] prices = new int[] { 8, 5, 1, 3, 10 };
		int n = prices.length;
		int k = 2;

		System.out.println(maximumProfitUsingRecursion(prices, n, k, 0, 0));


		// Creating a 3D dp array of size [n][2][maxTransaction + 1]
		int[][] dp = new int[n][2 * k + 1];

		// Initialize the dp array with -1
		for (int i = 0; i < n; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(maximumProfitUsingRecursionMemo(prices, n, k, 0, 0, dp));
		System.out.println(maximumProfitUsingRecursionTabular(prices, n, k, 0, 0));
		System.out.println(maximumProfitUsingRecursionTabularSpaceOptimal(prices, n, k, 0, 0));
	}
}
