package main.dynamic.programming.string;

import java.util.Arrays;

import static main.dynamic.programming.string.LongestCommonSubsequence.lcsUsingMemo;
import static main.dynamic.programming.string.LongestCommonSubsequence.lcsUsingRecursion;
import static main.dynamic.programming.string.LongestCommonSubsequence.lcsUsingTabular;
import static main.dynamic.programming.string.LongestCommonSubsequence.lcsUsingTabularSpaceOptimal;

/*
Problem statement
You are given 2 non-empty strings 's1' and 's2' consisting of lowercase English alphabets only.

In one operation you can do either of the following on 's1':

(1) Remove a character from any position in 's1'.

(2) Add a character to any position in 's1'.

Find the minimum number of operations required to convert string 's1' into 's2'.

Example:
Input: 's1' = "abcd", 's2' = "anc"

Output: 3

Explanation:
Here, 's1' = "abcd", 's2' = "anc".
In one operation remove 's1[3]', after this operation 's1' becomes "abc".
In the second operation remove 's1[1]', after this operation 's1' becomes "ac".
In the third operation add 'n' in 's1[1]', after this operation 's1' becomes "anc".

Hence, the minimum operations required will be 3. It can be shown that there's no way to convert s1 into s2 in less than 3 moves.

Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
aaa
aa

Expected Answer :
1

Output on console :
1

Explanation For Sample Output 1:
For this test case,
's1' = "aaa", 's2' = "aa"

In one operation remove 's1[2]', after this operation 's1' becomes "aa".

Hence, the output will be 1.

Sample Input 2 :
edl
xcqja
Expected Answer :
8

Output on console :
8

Expected Time Complexity :
Try to do this in O(n*m), where n is the length of string 's1' and 'm' is the length of string 's2'.

Constraints:
1 <= s1.length, s2.length <= 100

Time limit: 1 sec
*/
public class MinimumNumberOfDeletionsAndInsertions {


	public static int canYouMakeUsingRecursion(String s1, String s2) {
		// Write your code here.
		return s1.length() + s2.length() - 2 * (lcsUsingRecursion(s1, s2, s1.length() - 1, s2.length() - 1));
	}

	public static int canYouMakeUsingMemo(String s1, String s2, int[][] dp) {
		// Write your code here.
		return s1.length() + s2.length() - 2 * (lcsUsingMemo(s1, s2, s1.length() - 1, s2.length() - 1, dp));
	}

	public static int canYouMakeUsingTabular(String s1, String s2) {
		// Write your code here.
		return s1.length() + s2.length() - 2 * (lcsUsingTabular(s1, s2, s1.length(), s2.length()));

	}

	public static int canYouMakeTabularSpaceOptimal(String s1, String s2) {
		// Write your code here.
		return s1.length() + s2.length() - 2 * (lcsUsingTabularSpaceOptimal(s1, s2, s1.length(), s2.length()));
	}


	public static void main(String[] args) {
		String s1 = "abcd";
		String s2 = "anc";

		int i = s1.length();
		int j = s2.length();

		// Memoization table for storing results (initialize to -1)
		int[][] dp = new int[i + 1][j + 1];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}
		System.out.println(canYouMakeUsingRecursion(s1, s2));
		System.out.println(canYouMakeUsingMemo(s1, s2, dp));
		System.out.println(canYouMakeUsingTabular(s1, s2));
		System.out.println(canYouMakeTabularSpaceOptimal(s1, s2));

	}
}
