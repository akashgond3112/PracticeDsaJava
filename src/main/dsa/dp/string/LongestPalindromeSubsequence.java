package main.dsa.dp.string;

/*
Problem statement
Given two strings, 'S' and 'T' with lengths 'M' and 'N', find the length of the 'Longest Common Subsequence'.

For a string 'str'(per se) of length K, the subsequences are the strings containing characters in the same relative order as they are present in 'str,' but not necessarily contiguous. Subsequences contain all the strings of length varying from 0 to K.

Example :
Subsequences of string "abc" are:  ""(empty string), a, b, c, ab, bc, ac, abc.
Detailed explanation ( Input/output format, Notes, Images )
Constraints :
0 <= M <= 10 ^ 3
0 <= N <= 10 ^ 3

Time Limit: 1 sec
Sample Input 1 :
adebc
dcadb
Sample Output 1 :
3
Explanation of the Sample Output 1 :
Both the strings contain a common subsequence 'adb', which is the longest common subsequence with length 3.
Sample Input 2 :
ab
defg
Sample Output 2 :
0
Explanation of the Sample Output 2 :
The only subsequence that is common to both the given strings is an empty string("") of length 0.
*/

public class LongestPalindromeSubsequence {

	public static String lcsStringUsingTabular(String s1, String s2, int i, int j) {
		int[][] dp = new int[i + 1][j + 1];

		// Initialize the first row and first column to 0
		for (int j1 = 0; j1 <= j; j1++) {
			dp[0][j1] = 0;
		}
		for (int i1 = 0; i1 <= i; i1++) {
			dp[i1][0] = 0;
		}

		// Fill the dp table
		for (int i1 = 1; i1 <= i; i1++) {
			for (int j1 = 1; j1 <= j; j1++) {

				// If the characters match, move diagonally in the grid
				if (s1.charAt(i1 - 1) == s2.charAt(j1 - 1)) {
					dp[i1][j1] = 1 + dp[i1 - 1][j1 - 1];
				} else {
					// Otherwise, consider two possibilities:
					// 1. Exclude current character from s1 and move left
					// 2. Exclude current character from s2 and move up
					// Take the maximum of these two possibilities
					dp[i1][j1] = Math.max(dp[i1 - 1][j1], dp[i1][j1 - 1]);
				}
			}
		}

		int len = dp[i][j];
		int index = len - 1;
		StringBuilder ss = new StringBuilder(s1);
		StringBuilder str2 = new StringBuilder("$".repeat(Math.max(0, len)) // dummy string
		);
		while (i > 0 && j > 0) {
			if (ss.charAt(i - 1) == s2.charAt(j - 1)) {
				str2.setCharAt(index, ss.charAt(i - 1));
				index--;
				i--;
				j--;
			} else if (ss.charAt(i - 1) > s2.charAt(j - 1)) {
				i--;
			} else
				j--;
		}
		return str2.toString();
	}

	public static void main(String[] args) {
		String s1 = "abadebc";

		StringBuilder sb = new StringBuilder(s1);
		String s2 = sb.reverse().toString();
		int i = s1.length();
		int j = s2.length();

		System.out.println(lcsStringUsingTabular(s1, s2, i, j));
	}
}
