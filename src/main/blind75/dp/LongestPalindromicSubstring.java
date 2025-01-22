package main.blind75.dp;

/**
 * 5. Longest Palindromic Substring Given a string s, return the longest palindromic
 *
 * substring in s.
 *
 * Example 1: Input: s = "babad" Output: "bab" Explanation: "aba" is also a valid answer.
 *
 * Example 2: Input: s = "cbbd" Output: "bb"
 *
 * Constraints: 1 <= s.length <= 1000 s consist of only digits and English letters.
 */
public class LongestPalindromicSubstring {

	/**
	 * Time complexity: O(n^3 ) Space complexity: O(1)
	 */
	public static class SolutionBruteForce {
		public String longestPalindrome(String s) {
			String res = "";
			int resLen = 0;

			for (int i = 0; i < s.length(); i++) {
				for (int j = i; j < s.length(); j++) {
					int l = i, r = j;
					while (l < r && s.charAt(l) == s.charAt(r)) {
						l++;
						r--;
					}

					if (l >= r && resLen < (j - i + 1)) {
						res = s.substring(i, j + 1);
						resLen = j - i + 1;
					}
				}
			}

			return res;
		}
	}

	/**
	 * Time complexity: O(n^2 ) Space complexity: O(n^2)
	 */
	public static class SolutionDynamicProgramming {
		public String longestPalindrome(String s) {
			int resIdx = 0, resLen = 0;
			int n = s.length();

			boolean[][] dp = new boolean[n][n];

			for (int i = n - 1; i >= 0; i--) {
				for (int j = i; j < n; j++) {
					if (s.charAt(i) == s.charAt(j) &&
							(j - i <= 2 || dp[i + 1][j - 1])) {

						dp[i][j] = true;
						if (resLen < (j - i + 1)) {
							resIdx = i;
							resLen = j - i + 1;
						}
					}
				}
			}

			return s.substring(resIdx, resIdx + resLen);
		}
	}

	/**
	 * Time complexity: O(n^2 ) Space complexity: O(1)
	 */
	static class SolutionTwoPointers {
		public String longestPalindrome(String s) {
			int resLen = 0, resIdx = 0;

			for (int i = 0; i < s.length(); i++) {
				// odd length
				int l = i, r = i;
				while (l >= 0 && r < s.length() &&
						s.charAt(l) == s.charAt(r)) {
					if (r - l + 1 > resLen) {
						resIdx = l;
						resLen = r - l + 1;
					}
					l--;
					r++;
				}

				// even length
				l = i;
				r = i + 1;
				while (l >= 0 && r < s.length() &&
						s.charAt(l) == s.charAt(r)) {
					if (r - l + 1 > resLen) {
						resIdx = l;
						resLen = r - l + 1;
					}
					l--;
					r++;
				}
			}

			return s.substring(resIdx, resIdx + resLen);
		}
	}

	/**
	 * Time complexity: O(n ) Space complexity: O(n)
	 */
	public static class SolutionManachersAlgorithm {
		public int[] manacher(String s) {
			StringBuilder t = new StringBuilder("#");
			for (char c : s.toCharArray()) {
				t.append(c).append("#");
			}
			int n = t.length();
			int[] p = new int[n];
			int l = 0, r = 0;
			for (int i = 0; i < n; i++) {
				p[i] = (i < r) ? Math.min(r - i, p[l + (r - i)]) : 0;
				while (i + p[i] + 1 < n && i - p[i] - 1 >= 0 &&
						t.charAt(i + p[i] + 1) == t.charAt(i - p[i] - 1)) {
					p[i]++;
				}
				if (i + p[i] > r) {
					l = i - p[i];
					r = i + p[i];
				}
			}
			return p;
		}

		public String longestPalindrome(String s) {
			int[] p = manacher(s);
			int resLen = 0, center_idx = 0;
			for (int i = 0; i < p.length; i++) {
				if (p[i] > resLen) {
					resLen = p[i];
					center_idx = i;
				}
			}
			int resIdx = (center_idx - resLen) / 2;
			return s.substring(resIdx, resIdx + resLen);
		}
	}
}
