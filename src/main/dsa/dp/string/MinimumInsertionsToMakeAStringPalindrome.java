package main.dsa.dp.string;

/*
Problem statement
A palindrome string is one that reads the same backward as well as forward.

You are given a string 'str'.

Find the minimum number of characters needed to insert in 'str' to make it a palindromic string.

Example :
Input: 'str' = "abca"

Output: 1

Explanation:
If we insert the character ‘b’ after ‘c’, we get the string "abcba", which is a palindromic string. Please note that there are also other ways possible.

Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
abca

Sample Output 1:
1

Explanation for input 1:
If we insert the character ‘b’ after ‘c’, we get the string "abcba", which is a palindromic string. Please note that there are also other ways possible.

Sample Input 2:
abcdefg

Sample Output 2:
6

Sample Input 3:
aaaaa

Sample Output 3:
0

Expected time complexity :
The expected time complexity is O(|str| ^ 2).

Constraints:
1 <= |str| <= 1000

Where |str| represents the length of the string 'str'.

Time Limit: 1 sec.
*/
public class MinimumInsertionsToMakeAStringPalindrome {

	public static int minInsertionUsingTabular(String s1, String s2, int i, int j) {
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
		return str2.toString().length();
	}

	public static int minInsertion(String s1) {
		// Write your code here.

		StringBuilder sb = new StringBuilder(s1);
		String s2 = sb.reverse().toString();
		int i = s1.length();
		int j = s2.length();


		return s1.length() - minInsertionUsingTabular(s1, s2, i, j);
	}

	public static void main(String[] args) {

		System.out.println(minInsertion("abcdefg"));
	}
}
