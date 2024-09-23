package main.dynamic.programming.stocks;


import java.util.Arrays;

/*
Problem statement
You are given a list of stock prices of size 'n' called ‘prices’, where ‘prices[i]’ represents the price on ‘i’th day.

Your task is to calculate the maximum profit you can earn by trading stocks if you can only hold one stock at a time.

After you sell your stock on the ‘i’th day, you can only buy another stock on ‘i + 2’ th day or later.

Example:
Input: 'prices' = [4, 9, 0, 4, 10]

Output: 11

Explanation:
You are given prices = [4, 9, 0, 4, 10]. To get maximum profits you will have to buy on day 0 and sell on day 1 to make a profit of 5, and then you have to buy on day 3  and sell on day 4 to make the total profit of 11. Hence the maximum profit is 11.

Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
4
1 2 3 4

Expected Answer:
3

Output on console:
3

Explanation:
For this test case, prices = [1, 2, 3, 4]. To get the maximum profit you have to buy the stock on day 0 and sell on day 3 to get the maximum profit of 4. Hence the answer is 4.

Sample Input 2:
3
5 4 3

Expected Answer:
0

Output on console:
0

Expected Time Complexity:
Try to solve this in O(n).

Constraints:
1 <= n <= 10^5
1 <= prices[i] <= 10^6

Time Limit: 1 sec
*/
public class BestTimeToBuyAndSellStockWithCooldown {

	// Not Recommended
	public static int stockProfitUsingRecursionUsingTabularSpaceOptimal(int[] prices, int n) {
		// Write your code here.

		int[] front2 = new int[2];
		int[] front1 = new int[2];
		int[] curr = new int[2];

		// Fill the table in reverse order
		for (int index = n - 1; index >= 0; index--) {
			for (int buy = 0; buy <= 1; buy++) {
				if (buy == 1) {
					// Option to buy or skip
					int take = -prices[index] + front1[0];
					int notTake = front1[1];
					curr[buy] = Math.max(take, notTake);
				} else {
					// Option to sell or hold
					int sell = prices[index] + front2[1];
					int notSell = front1[0];
					curr[buy] = Math.max(sell, notSell);
				}
			}
			front2 = front1;
			front1 = curr;
		}

		// The result will be stored at dp[0][1], as we start from day 0 with the option to buy
		return curr[1];
	}


	public static int stockProfitUsingRecursionUsingTabular(int[] prices, int n) {
		// Write your code here.

		int[][] dp = new int[n + 2][2];

		// Fill the table in reverse order
		for (int index = n - 1; index >= 0; index--) {
			for (int buy = 0; buy <= 1; buy++) {
				if (buy == 1) {
					// Option to buy or skip
					int take = -prices[index] + dp[index + 1][0];
					int notTake = dp[index + 1][1];
					dp[index][buy] = Math.max(take, notTake);
				} else {
					// Option to sell or hold
					int sell = prices[index] + dp[index + 2][1];
					int notSell = dp[index + 1][0];
					dp[index][buy] = Math.max(sell, notSell);
				}
			}
		}

		// The result will be stored at dp[0][1], as we start from day 0 with the option to buy
		return dp[0][1];
	}

	public static int stockProfitUsingRecursionUsingMemo(int[] prices, int ind, boolean canBuy, int[][] dp) {
		// Write your code here.

		if (ind >= prices.length)
			return 0;

		// Convert boolean to index for dp array (0 for canBuy, 1 for can'tBuy)
		int canBuyIndex = canBuy ? 1 : 0;

		// Check if the value is already computed
		if (dp[ind][canBuyIndex] != -1) {
			return dp[ind][canBuyIndex];
		}

		if (canBuy) {
			int take = -prices[ind] + stockProfitUsingRecursion(prices, ind + 1, false);
			int notTake = stockProfitUsingRecursion(prices, ind + 1, true);
			dp[ind][canBuyIndex] = Math.max(take, notTake);
		} else {
			int sell = prices[ind] + stockProfitUsingRecursion(prices, ind + 2, true);
			int notSell = stockProfitUsingRecursion(prices, ind + 1, false);
			dp[ind][canBuyIndex] = Math.max(sell, notSell);
		}

		return dp[ind][canBuyIndex];
	}


	public static int stockProfitUsingRecursion(int[] prices, int ind, boolean canBuy) {
		// Write your code here.

		if (ind >= prices.length)
			return 0;

		int maxProfit;

		if (canBuy) {
			int take = -prices[ind] + stockProfitUsingRecursion(prices, ind + 1, false);
			int notTake = stockProfitUsingRecursion(prices, ind + 1, true);
			maxProfit = Math.max(take, notTake);
		} else {
			int sell = prices[ind] + stockProfitUsingRecursion(prices, ind + 2, true);
			int notSell = stockProfitUsingRecursion(prices, ind + 1, false);
			maxProfit = Math.max(sell, notSell);
		}

		return maxProfit;
	}

	public static void main(String[] args) {
		int[] values = new int[] { 4, 9, 0, 4, 10 };
		boolean canBuy = true;
		int[][] dp = new int[values.length][2];

		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}
		System.out.println(stockProfitUsingRecursion(values, 0, canBuy));
		System.out.println(stockProfitUsingRecursionUsingMemo(values, 0, canBuy, dp));
		System.out.println(stockProfitUsingRecursionUsingTabular(values, values.length));
		System.out.println(stockProfitUsingRecursionUsingTabularSpaceOptimal(values, values.length));
		//		System.out.println(getMaximumProfitUsingRecursionTabularSpaceOptimalUsingVariable(values.length, values));
	}
}
