package main.blind75.dp;

/**
 * 647. Palindromic Substrings
 * Medium
 * Topics
 * Companies
 * Hint
 * Given a string s, return the number of palindromic substrings in it.
 * A string is a palindrome when it reads the same backward as forward.
 * A substring is a contiguous sequence of characters within the string.
 *
 * Example 1:
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 * Example 2:
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists of lowercase English letters.*/
public class PalindromicSubstrings {

	/**
	 * Time complexity: O(n^3 ) Space complexity: O(1)
	 */
	public class SolutionBruteForce {
		public int countSubstrings(String s) {
			int res = 0;

			for (int i = 0; i < s.length(); i++) {
				for (int j = i; j < s.length(); j++) {
					int l = i, r = j;
					while (l < r && s.charAt(l) == s.charAt(r)) {
						l++;
						r--;
					}
					res += (l >= r) ? 1 : 0;
				}
			}

			return res;
		}
	}

	/**
	 * Time complexity: O(n^2 ) Space complexity: O(n^2)
	 */
	public static class SolutionDynamicProgramming {
		public int countSubstrings(String s) {
			int res = 0, n = s.length();
			boolean[][] dp = new boolean[n][n];

			for (int i = n - 1; i >= 0; i--) {
				for (int j = i; j < n; j++) {
					if (s.charAt(i) == s.charAt(j) &&
							(j - i <= 2 || dp[i + 1][j - 1])) {

						dp[i][j] = true;
						res++;
					}
				}
			}

			return res;
		}
	}

	/**
	 * Time complexity: O(n^2 ) Space complexity: O(1)
	 */
	public static class SolutionTwoPointers {
		public int countSubstrings(String s) {
			int res = 0;

			for (int i = 0; i < s.length(); i++) {
				// odd length
				res = countPalindromes(s, i, i, res);

				// even length
				res = countPalindromes(s, i, i + 1, res);
			}

			return res;
		}

		private static int countPalindromes(String s, int l, int r, int res) {
			while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
				res++;
				l--;
				r++;
			}
			return res;
		}
	}
}
