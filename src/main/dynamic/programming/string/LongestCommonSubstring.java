package main.dynamic.programming.string;

import java.util.Arrays;

/*
Problem statement
You are given two strings, 'str1' and 'str2'. You have to find the length of the longest common substring.

A substring is a continuous segment of a string. For example, "bcd" is a substring of "abcd", while "acd" or "cda" are not.

Example:
Input: ‘str1’ = “abcjklp” , ‘str2’ = “acjkp”.

Output: 3

Explanation:  The longest common substring between ‘str1’ and ‘str2’ is “cjk”, of length 3.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
wasdijkl
wsdjkl
Sample Output 1:
 3
Explanation Of Sample Input 1 :
 The longest common substring is “jkl”, of length 3.
Sample Input 2:
tyfg
cvbnuty
Sample Output 2:
2
Explanation Of Sample Input 2 :
The longest common substring is “ty”, of length 2.
Expected time complexity:
The expected time complexity is O(n*m),
Where ‘n’ and ‘m’ are the lengths of ‘st1’ and ‘str2’ respectively.
Constraints:
1 <= str1.length <= 1000
1 <= str2.length <= 1000
*/
public class LongestCommonSubstring {

	public static int lcsStringUsingTabularSpaceOptimal(String s1, String s2, int i, int j) {
		// Space optimization using two arrays
		int[] prev = new int[j + 1];
		int[] curr = new int[j + 1];

		int ans = 0;  // To store the length of the longest common substring

		// Fill the dp table
		for (int i1 = 1; i1 <= i; i1++) {
			for (int j1 = 1; j1 <= j; j1++) {

				// If the characters match, move diagonally in the grid
				if (s1.charAt(i1 - 1) == s2.charAt(j1 - 1)) {
					curr[j1] = 1 + prev[j1 - 1];
					ans = Math.max(ans, curr[j1]);
				} else {
					curr[j1] = 0;  // Reset for new substring calculation
				}
			}
			// Move to the next row, so the current becomes previous
			prev = curr.clone();  // Ensure deep copy to avoid mutation
		}
		return ans;
	}

	public static int lcsStringUsingTabular(String s1, String s2, int i, int j) {
		// Write your code here.
		int[][] dp = new int[i + 1][j + 1];

		// Initialize the first row and first column to 0
		for (int j1 = 0; j1 <= j; j1++) {
			dp[0][j1] = 0;
		}
		for (int i1 = 0; i1 <= i; i1++) {
			dp[i1][0] = 0;
		}
		int ans = 0;

		// Fill the dp table
		for (int i1 = 1; i1 <= i; i1++) {
			for (int j1 = 1; j1 <= j; j1++) {

				// If the characters match, move diagonally in the grid
				if (s1.charAt(i1 - 1) == s2.charAt(j1 - 1)) {
					dp[i1][j1] = 1 + dp[i1 - 1][j1 - 1];
					ans = Math.max(ans, dp[i1][j1]);
				} else {
					// Otherwise, consider two possibilities:
					// 1. Exclude current character from s1 and move left
					// 2. Exclude current character from s2 and move up
					// Take the 0 of these two possibilities
					dp[i1][j1] = 0;
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		String s1 = "adebc";
		String s2 = "dcadb";

		int i = s1.length();
		int j = s2.length();

		System.out.println(lcsStringUsingTabular(s1, s2, i, j));
		System.out.println(lcsStringUsingTabularSpaceOptimal(s1, s2, i, j));
	}
}
