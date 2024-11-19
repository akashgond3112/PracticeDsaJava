package main.dsa.dp.medium;

/**
 * 322. Coin Change Medium Topics Companies You are given an integer array coins representing coins of different
 * denominations and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by
 * any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Example 1:
 *
 * Input: coins = [1,2,5], amount = 11 Output: 3 Explanation: 11 = 5 + 5 + 1 Example 2:
 *
 * Input: coins = [2], amount = 3 Output: -1 Example 3:
 *
 * Input: coins = [1], amount = 0 Output: 0
 *
 * Constraints:
 *
 * 1 <= coins.length <= 12 1 <= coins[i] <= 231 - 1 0 <= amount <= 104
 */
public class CoinChange {

	public static int coinChange(int[] coins, int amount, int n) {

		if (amount == 0) {
			return 1;
		}
		if (amount < 0) {
			return 0;
		}

		if (n <= 0) {
			return 0;
		}

		return coinChange(coins, amount - coins[n - 1], n) + coinChange(coins, amount, n - 1);

	}

	public static void main(String[] args) {
		int[] coins = { 1, 2, 3 };
		int n = coins.length;
		System.out.println(coinChange(coins, 5, n));
	}
}
