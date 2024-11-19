package main.dsa.dp.string;


import java.util.Arrays;

/*
Problem statement
You are given two strings 'S' and 'T' of lengths 'N' and 'M' respectively. Find the "Edit Distance" between the strings.

Edit Distance of two strings is the minimum number of steps required to make one string equal to the other. In order to do so, you can perform the following three operations:

1. Delete a character
2. Replace a character with another one
3. Insert a character
Note:
Strings don't contain spaces in between.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
0 <= N <= 10 ^ 3
0 <= M <= 10 ^ 3

Time Limit : 1sec
Sample Input 1 :
abc
dc
Sample Output 1 :
2
 Explanation For Sample Input 1 :
In 2 operations we can make the string T to look like string S. First, insert the character 'a' to string T, which makes it "adc".

And secondly, replace the character 'd' of the string T with 'b' from the string S. This would make string T to "abc" which is also the string S. Hence, the minimum distance.
Sample Input 2 :
whgtdwhgtdg
aswcfg
Sample Output 2 :
9
*/
public class EditDistance {


	public static int editDistanceUsingRecursionTabularSpaceOptimal(String s1, String s2, int i, int j) {
		int[] prev = new int[j + 1];
		int[] curr = new int[j + 1];

		for (int j1 = 0; j1 <= j; j1++) {
			prev[j1] = j1;
		}

		for (int i1 = 1; i1 <= i; i1++) {
			curr[0] = i1;
			for (int j1 = 1; j1 <= j; j1++) {
				if (s1.charAt(i1 - 1) == s2.charAt(j1 - 1)) {
					curr[j1] = prev[j1 - 1];
				} else {
					curr[j1] = 1 + Math.min((curr[j1 - 1]), Math.min((prev[j1]), prev[j1 - 1]));
				}
			}
			prev = curr.clone();
		}
		return prev[j];
	}


	public static int editDistanceUsingRecursionTabular(String s1, String s2, int i, int j) {
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];

		for (int i1 = 0; i1 <= i; i1++) {
			dp[i1][0] = i1;
		}

		// If s1 is an empty string and s2 is not, there are no subsequences that match
		for (int j1 = 0; j1 <= j; j1++) {
			dp[0][j1] = j1;
		}

		for (int i1 = 1; i1 <= i; i1++) {
			for (int j1 = 1; j1 <= j; j1++) {
				if (s1.charAt(i1 - 1) == s2.charAt(j1 - 1)) {
					dp[i1][j1] = dp[i1 - 1][j1 - 1];
				} else {
					dp[i1][j1] = 1 + Math.min((dp[i1][j1 - 1]), Math.min((dp[i1 - 1][j1]), dp[i1 - 1][j1 - 1]));
				}

			}
		}
		return dp[i][j];
	}

	public static int editDistanceUsingRecursionMemo(String s1, String s2, int i, int j, int[][] dp) {
		if (i < 0) {
			return j + 1;
		}
		if (j < 0) {
			return i + 1;
		}

		// Check if this problem has already been solved
		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		if (s1.charAt(i) == s2.charAt(j)) {
			dp[i][j] = editDistanceUsingRecursion(s1, s2, i - 1, j - 1);
		} else {
			dp[i][j] = Math.min((1 + editDistanceUsingRecursion(s1, s2, i, j - 1)),
					Math.min((1 + editDistanceUsingRecursion(s1, s2, i - 1, j)),
							1 + editDistanceUsingRecursion(s1, s2, i - 1, j - 1)));
		}
		return dp[i][j];
	}

	public static int editDistanceUsingRecursion(String s1, String s2, int i, int j) {
		if (i < 0) {
			return j + 1;
		}
		if (j < 0) {
			return i + 1;
		}
		if (s1.charAt(i) == s2.charAt(j)) {
			return editDistanceUsingRecursion(s1, s2, i - 1, j - 1);
		} else {
			return Math.min((1 + editDistanceUsingRecursion(s1, s2, i, j - 1)),
					Math.min((1 + editDistanceUsingRecursion(s1, s2, i - 1, j)),
							1 + editDistanceUsingRecursion(s1, s2, i - 1, j - 1)));
		}
	}

	public static void main(String[] args) {
		String s1 = "whgtdwhgtdg";
		String s2 = "aswcfg";
		System.out.println(editDistanceUsingRecursion(s1, s2, s1.length() - 1, s2.length() - 1));

		// Memoization table for storing results (initialize to -1)
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}

		System.out.println(editDistanceUsingRecursionMemo(s1, s2, s1.length() - 1, s2.length() - 1, dp));
		System.out.println(editDistanceUsingRecursionTabular(s1, s2, s1.length(), s2.length()));
		System.out.println(editDistanceUsingRecursionTabularSpaceOptimal(s1, s2, s1.length(), s2.length()));
	}
}
