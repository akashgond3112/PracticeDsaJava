package main.dsa.dp.string;

import java.util.Arrays;

/*
Problem statement
Given a text and a wildcard pattern of size N and M respectively, implement a wildcard pattern matching algorithm that finds if the wildcard pattern is matched with the text. The matching should cover the entire text not partial text.

The wildcard pattern can include the characters ‘?’ and ‘*’

 ‘?’ – matches any single character
 ‘*’ – Matches any sequence of characters(sequence can be of length 0 or more)
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 100
1 <= N <= 200
1 <= M <= 200

Where 'N' denotes the length of 'TEXT' and 'M' denotes the length of 'PATTERN'.

'TEXT' and 'PATTERN' contain only lowercase letters and patterns may contain special characters ‘*’ and ‘?’

Time Limit: 1sec
Sample Input 1:
3
?ay
ray
ab*cd
abdefcd
ab?d
abcc
Sample Output 1:
True
True
False
Explanation of the Sample Input1:
Test Case 1:
The pattern is “?ay” and the text is ray.
‘?’ can match a character so it matches with a character ‘r’ of the text and rest of the text matches with the pattern so we print True.

Test Case 2:
“ab” of text matches with “ab” of pattern and then ‘*’ of pattern matches with “def” of the text and “cd” of text matches with “cd” of the pattern. Whole text matches with the pattern so we print True.

Test Case 3:
“ab” of pattern matches with “ab” of text. ‘?’ of pattern matches with ‘c’ of the text but ‘d’ of the pattern do not match with ‘c’ of the text so we print False.
Sample Input 1:
1
ba*a?
baaabab
Sample Output 1:
True
*/
public class WildcardPatternMatching {

	public static boolean wildcardMatchingUsingTabularSpaceOptimal(String pattern, String text, int i, int j) {
		int[] prev = new int[j + 1];
		int[] curr = new int[j + 1];

		// Initialize the dp array
		prev[0] = 1; // Empty pattern matches empty text

		// If pattern is empty but text is not, it doesn't match
		for (int j1 = 1; j1 <= j; j1++) {
			prev[j1] = 0;
		}

		// Fill the dp table
		for (int i1 = 1; i1 <= i; i1++) {
			// If text is empty but pattern is not, it only matches if the pattern is all '*'
			curr[0] = pattern.charAt(i1 - 1) == '*' ? prev[0] : 0;

			for (int j1 = 1; j1 <= j; j1++) {
				if (pattern.charAt(i1 - 1) == text.charAt(j1 - 1) || pattern.charAt(i1 - 1) == '?') {
					curr[j1] = prev[j1 - 1];
				} else if (pattern.charAt(i1 - 1) == '*') {
					// '*' can match zero characters (prev[j1]) or one character (curr[j1 - 1])
					curr[j1] = prev[j1] == 1 || curr[j1 - 1] == 1 ? 1 : 0;
				} else {
					curr[j1] = 0;
				}
			}
			prev = curr.clone(); // Copy current row to previous row for the next iteration
		}

		return prev[j] == 1; // Return true if pattern fully matches text
	}

	public static boolean wildcardMatchingUsingTabular(String pattern, String text, int i, int j) {
		int[][] dp = new int[i + 1][j + 1];

		// Initialize the dp array
		dp[0][0] = 1; // Empty pattern matches empty text

		// If pattern is empty but text is not, it doesn't match
		for (int j1 = 1; j1 <= j; j1++) {
			dp[0][j1] = 0;
		}

		// If text is empty but pattern is not, it only matches if the pattern is all '*'
		for (int i1 = 1; i1 <= i; i1++) {
			dp[i1][0] = pattern.charAt(i1 - 1) == '*' ? dp[i1 - 1][0] : 0;
		}

		// Fill the dp table
		for (int i1 = 1; i1 <= i; i1++) {
			for (int j1 = 1; j1 <= j; j1++) {
				if (pattern.charAt(i1 - 1) == text.charAt(j1 - 1) || pattern.charAt(i1 - 1) == '?') {
					dp[i1][j1] = dp[i1 - 1][j1 - 1];
				} else if (pattern.charAt(i1 - 1) == '*') {
					// '*' can match zero characters (dp[i1-1][j1]) or one character (dp[i1][j1-1])
					dp[i1][j1] = dp[i1 - 1][j1] == 1 || dp[i1][j1 - 1] == 1 ? 1 : 0;
				} else {
					dp[i1][j1] = 0;
				}
			}
		}

		return dp[i][j] == 1; // Return true if pattern fully matches text
	}

	public static boolean wildcardMatchingUsingMemo(String pattern, String text, int i, int j, int[][] dp) {
		// Base cases
		if (i < 0 && j < 0) {
			return true; // Both strings are fully matched
		} else if (i < 0) {
			return false; // Pattern is exhausted, but text is not
		} else if (j < 0) {
			// Check if the remaining pattern can match an empty string (only '*' can)
			for (int i1 = 0; i1 <= i; i1++) {
				if (pattern.charAt(i1) != '*') {
					return false;
				}
			}
			return true;
		}

		// Check if this problem has already been solved
		if (dp[i][j] != -1) {
			return dp[i][j] == 1; // Convert stored value to boolean
		}

		// Recursive cases
		boolean match;
		if (pattern.charAt(i) == text.charAt(j) || pattern.charAt(i) == '?') {
			match = wildcardMatchingUsingMemo(pattern, text, i - 1, j - 1, dp);
		} else if (pattern.charAt(i) == '*') {
			// '*' can match zero characters (move in pattern) or one character (move in text)
			match = (wildcardMatchingUsingMemo(pattern, text, i - 1, j, dp) || wildcardMatchingUsingMemo(pattern, text,
					i, j - 1, dp));
		} else {
			match = false; // Characters do not match and it's not a wildcard
		}

		// Store result in dp array and convert boolean to integer
		dp[i][j] = match ? 1 : 0;
		return match;
	}


	public static boolean wildcardMatching(String pattern, String text, int i, int j) {
		// Base cases
		if (i < 0 && j < 0) {
			return true; // Both strings are fully matched
		} else if (i < 0) {
			return false; // Pattern is exhausted, but text is not
		} else if (j < 0) {
			// Check if the remaining pattern can match an empty string (only '*' can)
			for (int i1 = 0; i1 <= i; i1++) {
				if (pattern.charAt(i1) != '*') {
					return false;
				}
			}
			return true;
		}

		// Recursive cases
		if (pattern.charAt(i) == text.charAt(j) || pattern.charAt(i) == '?') {
			return wildcardMatching(pattern, text, i - 1, j - 1);
		} else if (pattern.charAt(i) == '*') {
			// '*' can match zero characters (move in pattern) or one character (move in text)
			return (wildcardMatching(pattern, text, i - 1, j) || wildcardMatching(pattern, text, i, j - 1));
		} else {
			return false; // Characters do not match and it's not a wildcard
		}
	}

	public static void main(String[] args) {
		String pattern = "?ay";
		String text = "ray";

		System.out.println(
				wildcardMatching(pattern, text, pattern.length() - 1, text.length() - 1)); // Should print true

		int[][] dp = new int[pattern.length() + 1][text.length() + 1];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}

		System.out.println(wildcardMatchingUsingMemo(pattern, text, pattern.length() - 1, pattern.length() - 1, dp));
		System.out.println(wildcardMatchingUsingTabular(pattern, text, pattern.length(), pattern.length()));
		System.out.println(wildcardMatchingUsingTabularSpaceOptimal(pattern, text, pattern.length(), pattern.length()));
	}

}
