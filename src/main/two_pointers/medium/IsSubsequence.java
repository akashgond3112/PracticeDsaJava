package main.two_pointers.medium;

/*
392. Is Subsequence
Easy

Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
Input: s = "abc", t = "ahbgdc"
Output: true

Example 2:
Input: s = "axc", t = "ahbgdc"
Output: false

Constraints:
0 <= s.length <= 100
0 <= t.length <= 10^4
s and t consist only of lowercase English letters.

Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109, and you want to check one by one to see if t has its subsequence. In this scenario, how would you change your code?
*/

import static org.junit.Assert.assertEquals;

public class IsSubsequence {

	public static boolean isSubsequence(String s, String t) {

        int i = 0;  // Initialize pointers for s and t
		int j = 0;

		while (i < s.length() && j < t.length()) {
			if (s.charAt(i) == t.charAt(j)) {
				i++;  // Move pointer i when characters match
			}
			j++;  // Always move pointer j
		}

		return i == s.length();
	}

	public static void main(String[] args) {
		// Example 1
		String s1 = "abc";
		String t1 = "ahbgdc";
		assertEquals(s1, t1);  // Output: true

		// Example 2
		String s2 = "axc";
		String t2 = "ahbgdc";
		assertEquals(s2, t2);  // Output: true
		;  // Output: false
	}

}