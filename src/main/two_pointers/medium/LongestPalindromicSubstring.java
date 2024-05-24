package main.two_pointers.medium;

/*
5. Longest Palindromic Substring
Medium
Topics
Companies
Hint
Given a string s, return the longest palindromic substring in s.

Example 1:
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.

Example 2:
Input: s = "cbbd"
Output: "bb"

Constraints:
1 <= s.length <= 1000
s consist of only digits and English letters.
*/

import static org.junit.Assert.assertEquals;

public class LongestPalindromicSubstring {

	public static String longestPalindrome(String s) {

		if (s == null || s.length() < 2) {
			return s;
		}

		int n = s.length();
		boolean[][] dp = new boolean[n][n];
		int maxLength = 1;
		int start = 0;

		// All substrings of length 1 are palindromes
		for (int i = 0; i < n; i++) {
			dp[i][i] = true;
		}

		// Check for substring of length 2
		for (int i = 0; i < n - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				dp[i][i + 1] = true;
				start = i;
				maxLength = 2;
			}
		}

		// Check for lengths greater than 2
		for (int length = 3; length <= n; length++) {
			for (int i = 0; i < n - length + 1; i++) {
				int j = i + length - 1;
				if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
					dp[i][j] = true;
					start = i;
					maxLength = length;
				}
			}
		}

		return s.substring(start, start + maxLength);

	}

	public static void main(String[] args) {

		String name = "babad";
		String result = longestPalindrome(name);
		assertEquals("aba", result);

//		name = "cbbd";
//		result = longestPalindrome(name);
//		assertEquals("bb", result);


	}
}
