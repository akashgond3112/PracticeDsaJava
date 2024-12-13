package main.blind75.sliding.window;

/**
 * 121. Best Time to Buy and Sell Stock Easy Topics Companies You are given an array prices where prices[i] is the price
 * of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future
 * to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 * Example 1:
 *
 * Input: prices = [7,1,5,3,6,4] Output: 5 Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit =
 * 6-1 = 5. Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell. Example
 * 2:
 *
 * Input: prices = [7,6,4,3,1] Output: 0 Explanation: In this case, no transactions are done and the max profit = 0.
 *
 * Constraints:
 *
 * 1 <= prices.length <= 105 0 <= prices[i] <= 104
 */
public class BestTimeToBuyAndSellStock {

	/**
	 * Time complexity: O(n^2 ) Space complexity: O(1)
	 */
	public static class BruteForce {
		public int maxProfit(int[] prices) {
			int res = 0;
			for (int i = 0; i < prices.length; i++) {
				int buy = prices[i];
				for (int j = i + 1; j < prices.length; j++) {
					int sell = prices[j];
					res = Math.max(res, sell - buy);
				}
			}
			return res;
		}
	}

	/**
	 * Time complexity: O(n) Space complexity: O(1)
	 */
	public static class TwoPointers {
		public int maxProfit(int[] prices) {
			int l = 0, r = 1;
			int maxP = 0;

			while (r < prices.length) {
				if (prices[l] < prices[r]) {
					int profit = prices[r] - prices[l];
					maxP = Math.max(maxP, profit);
				} else {
					l = r;
				}
				r++;
			}
			return maxP;
		}
	}

	/**
	 * Time complexity: O(n) Space complexity: O(1)
	 */
	public static class Dp {
		public int maxProfit(int[] prices) {
			int maxP = 0;
			int minBuy = prices[0];

			for (int sell : prices) {
				maxP = Math.max(maxP, sell - minBuy);
				minBuy = Math.min(minBuy, sell);
			}
			return maxP;
		}
	}
}
