package main.blind75.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 91. Decode Ways
 * Medium
 * Topics
 * Companies
 * You have intercepted a secret message encoded as a string of numbers. The message is decoded via the following mapping:
 *
 * "1" -> 'A'
 * "2" -> 'B'
 * "25" -> 'Y'
 * "26" -> 'Z'
 * However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes ("2" and "5" vs "25").
 *
 * For example, "11106" can be decoded into:
 *
 * "AAJF" with the grouping (1, 1, 10, 6)
 * "KJF" with the grouping (11, 10, 6)
 * The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
 * Note: there may be strings that are impossible to decode.
 *
 * Given a string s containing only digits, return the number of ways to decode it. If the entire string cannot be decoded in any valid way, return 0.
 *
 * The test cases are generated so that the answer fits in a 32-bit integer.
 *
 *
 *
 * Example 1:
 * Input: s = "12"
 * Output: 2
 * Explanation:
 * "12" could be decoded as "AB" (1 2) or "L" (12).
 *
 * Example 2:
 * Input: s = "226"
 * Output: 3
 * Explanation:
 *
 * "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 * Example 3:
 * Input: s = "06"
 * Output: 0
 * Explanation:
 *
 * "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06"). In this case, the string is not a valid encoding, so return 0.
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s contains only digits and may contain leading zero(s).*/
public class DecodeWays {

	/**
	 * Time complexity: O(2^n ) Space complexity: O(n)
	 */
	static class SolutionRecursion {
		private int dfs(int i, String s) {
			if (i == s.length())
				return 1;
			if (s.charAt(i) == '0')
				return 0;

			int res = dfs(i + 1, s);
			if (i < s.length() - 1) {
				if (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) < '7')) {
					res += dfs(i + 2, s);
				}
			}
			return res;
		}

		public int numDecodings(String s) {
			return dfs(0, s);
		}
	}

	/**
	 * Time complexity: O(n ) Space complexity: O(n)
	 */
	static class SolutionDynamicProgrammingTopDown {
		public int numDecodings(String s) {
			Map<Integer, Integer> dp = new HashMap<>();
			dp.put(s.length(), 1);

			return dfs(s, 0, dp);
		}

		private int dfs(String s, int i, Map<Integer, Integer> dp) {
			if (dp.containsKey(i)) {
				return dp.get(i);
			}
			if (s.charAt(i) == '0') {
				return 0;
			}

			int res = dfs(s, i + 1, dp);
			if (i + 1 < s.length() && (s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i + 1) < '7')) {
				res += dfs(s, i + 2, dp);
			}
			dp.put(i, res);
			return res;
		}
	}

	/**
	 * Time complexity: O(n ) Space complexity: O(n)
	 */
	static class SolutionDynamicProgrammingBottomUp {
		public int numDecodings(String s) {
			int[] dp = new int[s.length() + 1];
			dp[s.length()] = 1;
			for (int i = s.length() - 1; i >= 0; i--) {
				if (s.charAt(i) == '0') {
					dp[i] = 0;
				} else {
					dp[i] = dp[i + 1];
					if (i + 1 < s.length() && (s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i + 1) < '7')) {
						dp[i] += dp[i + 2];
					}
				}
			}
			return dp[0];
		}
	}

	/**
	 * Time complexity: O(n ) Space complexity: O(1)
	 */
	static class SolutionDynamicProgrammingMemoization {
		public int numDecodings(String s) {
			int dp = 0, dp2 = 0;
			int dp1 = 1;
			for (int i = s.length() - 1; i >= 0; i--) {
				if (s.charAt(i) == '0') {
					dp = 0;
				} else {
					dp = dp1;
					if (i + 1 < s.length() && (s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i + 1) < '7')) {
						dp += dp2;
					}
				}
				dp2 = dp1;
				dp1 = dp;
				dp = 0;
			}
			return dp1;
		}
	}
}
