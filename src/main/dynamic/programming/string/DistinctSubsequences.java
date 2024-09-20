package main.dynamic.programming.string;

import java.util.Arrays;

/*
Problem statement
A Subsequence of a string is the string that is obtained by deleting 0 or more letters from the string and keeping the rest of the letters in the same order.

We are given two strings, 'str' and 'sub'.

Find the number of subsequences of 'str' which are equal to 'sub'.

Since the answer can be very large, print it modulo 10 ^ 9 + 7.

Example :
Input: 'str' = “brootgroot” and 'sub' = “brt”

Output: 3

Explanation: The following subsequences formed by characters at given indices (0-based) of 'str' are equal to 'sub' :

str[0] = ‘b’, str[1] = ‘r’, str[4] = ‘t’

str[0] = ‘b’, str[1] = ‘r’, str[9] = ‘t’

str[0] = ‘b’, str[6] = ‘r’, str[9] = ‘t’
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
brootgroot
brt

Sample Output 1 :
3

Explanation For Sample Input 1 :
The following subsequences formed by characters at given indices (0-based) of 'str' are equal to 'sub' :

str[0] = ‘b’, str[1] = ‘r’, str[4] = ‘t’

str[0] = ‘b’, str[1] = ‘r’, str[9] = ‘t’

str[0] = ‘b’, str[6] = ‘r’, str[9] = ‘t’

Sample Input 2 :
dingdingdingding
ing

Sample Output 2 :
20

Sample Input 3:
aaaaa
a

Sample Output 3:
5

Expected time complexity :
The expected time complexity is O(|str| * |sub|).

Constraints:
1 <= |str| <= 1000
1 <= |sub| <= 1000

Where |str| represents the length of the string 'str', and |sub| represents the length of the string 'sub'.

Time Limit: 1 sec.
*/
public class DistinctSubsequences {

	public static int distinctSubsequencesUsingTabularSpaceOptimalMore(String s1, String s2, int i, int j) {
		int[] prev = new int[i + 1];

		// If s2 is an empty string, there is exactly one subsequence of s1 that matches (the empty subsequence)
		for (int i1 = 0; i1 <= i; i1++) {
			prev[0] = 1;
		}

		for (int i1 = 1; i1 <= i; i1++) {
			for (int j1 = j; j1 >= 1; j1--) {
				if (s1.charAt(i1 - 1) == s2.charAt(j1 - 1)) {
					prev[j1] = prev[j1 - 1] + prev[j1];
				}
			}
		}
		return prev[j];
	}

	public static int distinctSubsequencesUsingTabularSpaceOptimal(String s1, String s2, int i, int j) {
		int[] prev = new int[i + 1];
		int[] curr = new int[j + 1];

		// If s2 is an empty string, there is exactly one subsequence of s1 that matches (the empty subsequence)
		for (int i1 = 0; i1 <= i; i1++) {
			prev[0] = 1;
		}

		for (int i1 = 1; i1 <= i; i1++) {
			for (int j1 = 1; j1 <= j; j1++) {
				if (s1.charAt(i1 - 1) == s2.charAt(j1 - 1)) {
					curr[j1] = prev[j1 - 1] + prev[j1];
				} else {
					curr[j1] = prev[j1];
				}
			}
			prev = curr.clone();
		}
		return prev[j];
	}


	public static int distinctSubsequencesUsingTabular(String s1, String s2, int i, int j) {
		int[][] dp = new int[i + 1][j + 1];

		// If s2 is an empty string, there is exactly one subsequence of s1 that matches (the empty subsequence)
		for (int i1 = 0; i1 <= i; i1++) {
			dp[i1][0] = 1;
		}

		// If s1 is an empty string and s2 is not, there are no subsequences that match
		for (int j1 = 1; j1 <= j; j1++) {
			dp[0][j1] = 0;
		}

		for (int i1 = 1; i1 <= i; i1++) {
			for (int j1 = 1; j1 <= j; j1++) {
				if (s1.charAt(i1 - 1) == s2.charAt(j1 - 1)) {
					dp[i1][j1] = dp[i1 - 1][j1 - 1] + dp[i1 - 1][j1];
				} else {
					dp[i1][j1] = dp[i1 - 1][j1];
				}
			}
		}
		return dp[i][j];
	}

	public static int distinctSubsequencesUsingMemo(String s1, String s2, int i, int j, int[][] dp) {
		// Write your code here.

		// Base case: if we reach the beginning of either string
		if (j < 0) {
			return 1;
		} else if (i < 0) {
			return 0;
		}

		// Check if this problem has already been solved
		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		// If the characters match, move diagonally in the grid
		if (s1.charAt(i) == s2.charAt(j)) {
			dp[i][j] = distinctSubsequencesUsingRecursion(s1, s2, i - 1, j - 1) + distinctSubsequencesUsingRecursion(s1,
					s2, i - 1, j);
		} else {
			// Otherwise, consider two possibilities:
			// 1. Move left in s1
			// 2. Move left in s2
			// Take the maximum of these two possibilities
			dp[i][j] = distinctSubsequencesUsingRecursion(s1, s2, i - 1, j);
		}

		return dp[i][j];
	}

	public static int distinctSubsequencesUsingRecursion(String s1, String s2, int i, int j) {
		// Write your code here.

		// Base case: if we reach the beginning of either string
		if (j < 0) {
			return 1;
		} else if (i < 0) {
			return 0;
		}

		// If the characters match, move diagonally in the grid
		if (s1.charAt(i) == s2.charAt(j)) {
			return distinctSubsequencesUsingRecursion(s1, s2, i - 1, j - 1) + distinctSubsequencesUsingRecursion(s1, s2,
					i - 1, j);
		} else {
			// Otherwise, consider two possibilities:
			// 1. Move left in s1
			// 2. Move left in s2
			// Take the maximum of these two possibilities
			return distinctSubsequencesUsingRecursion(s1, s2, i - 1, j);
		}
	}

	public static void main(String[] args) {
		String s1 = "brootgroot";
		String s2 = "brt";

		System.out.println(distinctSubsequencesUsingRecursion(s1, s2, s1.length() - 1, s2.length() - 1));


		// Memoization table for storing results (initialize to -1)
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}

		System.out.println(distinctSubsequencesUsingMemo(s1, s2, s1.length() - 1, s2.length() - 1, dp));
		System.out.println(distinctSubsequencesUsingTabular(s1, s2, s1.length(), s2.length()));
		System.out.println(distinctSubsequencesUsingTabularSpaceOptimal(s1, s2, s1.length(), s2.length()));
		System.out.println(distinctSubsequencesUsingTabularSpaceOptimalMore(s1, s2, s1.length(), s2.length()));

	}
}
