package main.dsa.dp.string;

/*
Problem statement
Given two strings, ‘A’ and ‘B’. Return the shortest supersequence string ‘S’, containing both ‘A’ and ‘B’ as its subsequences. If there are multiple answers, return any of them.

Note: A string 's' is a subsequence of string 't' if deleting some number of characters from 't' (possibly 0) results in the string 's'.

For example:
Suppose ‘A’ = “brute”, and ‘B’ = “groot”

The shortest supersequence will be “bgruoote”. As shown below, it contains both ‘A’ and ‘B’ as subsequences.

A   A A     A A
b g r u o o t e
  B B   B B B

It can be proved that the length of supersequence for this input cannot be less than 8. So the output will be bgruoote.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
2
brute
groot
bleed
blue
Sample Output 1 :
bgruoote
bleued
Explanation For Sample Input 1 :
For First Case - Same as explained in above example.

For the second case -

‘A’ = “bleed”, and ‘B’ = “blue”

The shortest supersequence will be “bleued”. As shown below, it contains both ‘A’ and ‘B’ as subsequences.

A A A   A A
b l e u e d
B B   B B

It can be proved that the length of supersequence for this input cannot be less than 6. So the output will be bleued.
Sample Input 2 :
2
coding
ninjas
blinding
lights
Sample Output 2 :
codningjas
blindinghts
*/
public class ShortestCommonSuperSequence {

	public static String shortestSuperSequence(String s1, String s2, int i, int j) {
		// DP table to store the length of the Longest Common Subsequence (LCS)
		int[][] dp = new int[i + 1][j + 1];

		// Fill the dp table
		for (int i1 = 1; i1 <= i; i1++) {
			for (int j1 = 1; j1 <= j; j1++) {
				if (s1.charAt(i1 - 1) == s2.charAt(j1 - 1)) {
					dp[i1][j1] = 1 + dp[i1 - 1][j1 - 1];
				} else {
					dp[i1][j1] = Math.max(dp[i1 - 1][j1], dp[i1][j1 - 1]);
				}
			}
		}

		// Now, construct the shortest super sequence using the dp table
		StringBuilder superSequence = new StringBuilder();
		int i1 = i, j1 = j;

		while (i1 > 0 && j1 > 0) {
			if (s1.charAt(i1 - 1) == s2.charAt(j1 - 1)) {
				// If characters match, add it to the super sequence and move diagonally up
				superSequence.append(s1.charAt(i1 - 1));
				i1--;
				j1--;
			} else if (dp[i1 - 1][j1] > dp[i1][j1 - 1]) {
				// If moving up gives a longer subsequence, move up and add character from s1
				superSequence.append(s1.charAt(i1 - 1));
				i1--;
			} else {
				// If moving left gives a longer subsequence, move left and add character from s2
				superSequence.append(s2.charAt(j1 - 1));
				j1--;
			}
		}

		// Add the remaining characters from s1 and s2
		while (i1 > 0) {
			superSequence.append(s1.charAt(i1 - 1));
			i1--;
		}
		while (j1 > 0) {
			superSequence.append(s2.charAt(j1 - 1));
			j1--;
		}

		// The constructed sequence is in reverse order, so reverse it
		return superSequence.reverse().toString();
	}

	public static void main(String[] args) {
		String s1 = "brute";
		String s2 = "groot";
		System.out.println(shortestSuperSequence(s1, s2, s1.length(), s2.length()));
	}

}
