package main.blind75.dp;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 322. Coin Change Solved Medium Topics Companies You are given an integer array coins representing
 * coins of different denominations and an integer amount representing a total amount of money.
 * 
 * Return the fewest number of coins that you need to make up that amount. If that amount of money
 * cannot be made up by any combination of the coins, return -1.
 * 
 * You may assume that you have an infinite number of each kind of coin.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: coins = [1,2,5], amount = 11 Output: 3 Explanation: 11 = 5 + 5 + 1 Example 2:
 * 
 * Input: coins = [2], amount = 3 Output: -1 Example 3:
 * 
 * Input: coins = [1], amount = 0 Output: 0
 * 
 * 
 * Constraints:
 * 
 * 1 <= coins.length <= 12 1 <= coins[i] <= 231 - 1 0 <= amount <= 104
 */
public class CoinChange {

    /*
     * Time complexity: O(n^t) Space complexity: O(t), Where n is the length of the array coins and
     * t is the given amount.
     */
    public class SolutionUsingRecursion {
        public int dfs(int[] coins, int amount) {
            if (amount == 0)
                return 0;

            int res = (int) 1e9;
            for (int coin : coins) {
                if (amount - coin >= 0) {
                    res = Math.min(res, 1 + dfs(coins, amount - coin));
                }
            }
            return res;
        }

        public int coinChange(int[] coins, int amount) {
            int minCoins = dfs(coins, amount);
            return (minCoins >= 1e9) ? -1 : minCoins;
        }
    }

    /*
     * Time complexity: O(n∗t) Space complexity: O(t) Where n is the length of the array coins and t
     * is the given amount.
     */
    public class SolutionDpTopDown {
        HashMap<Integer, Integer> memo = new HashMap<>();

        public int dfs(int amount, int[] coins) {
            if (amount == 0)
                return 0;
            if (memo.containsKey(amount))
                return memo.get(amount);

            int res = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (amount - coin >= 0) {
                    int result = dfs(amount - coin, coins);
                    if (result != Integer.MAX_VALUE) {
                        res = Math.min(res, 1 + result);
                    }
                }
            }

            memo.put(amount, res);
            return res;
        }

        public int coinChange(int[] coins, int amount) {
            int minCoins = dfs(amount, coins);
            return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
        }
    }

    /*
     * Time complexity: O(n∗t) Space complexity: O(t) Where n is the length of the array coins and t
     * is the given amount.
     */
    public class SolutionDpBottomUp {
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, amount + 1);
            dp[0] = 0;
            for (int i = 1; i <= amount; i++) {
                for (int j = 0; j < coins.length; j++) {
                    if (coins[j] <= i) {
                        dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                    }
                }
            }
            return dp[amount] > amount ? -1 : dp[amount];
        }
    }

}
