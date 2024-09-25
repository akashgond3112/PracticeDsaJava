package main.dynamic.programming.lis;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem statement
You are given an array 'arr' of strings, where each string consists of English lowercase letters.

A string chain of 'arr' is defined as:

(1) A sequence of string formed using elements of 'arr'.
(2) Every string in the sequence can be formed by inserting a lowercase English letter into the previous string (except the first string).

Find the length of the longest possible string chain of 'arr'.
Example :
Input: 'arr' = ["x", "xx", "y", "xyx"]

Output: 3

Explanation:
The longest possible string chain is “x” -> “xx” -> “xyx”.
The length of the given chain is 3, hence the answer is 3.

Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
3
m
nm
mmm

Expected Answer :
2

Output on console :
2

Explanation For Sample Input 1 :
In this testcase, the longest possible string chain is "m" -> "nm".
The length of the given chain is 2, hence the answer is 2.

Sample Input 2 :
5
a
bc
ad
adc
bcd
Expected Answer :
3
Output on console :
3
Explanation For Sample Input 2 :
In this testcase, the longest possible string chain is “a” -> “ad” -> “adc”.
The length of the given chain is 3, hence the answer is 3.

Expected Time Complexity :
Try to solve this in O(n*n*l), where 'n' is the size of array 'arr' and 'l' is the maximum length of a string in 'arr'.

Constraints :
1 ≤ n ≤ 300
1 ≤ arr[i].length ≤ 16

Time limit: 1 sec
*/
public class LongestStringChain {
	public static int longestStrChain(String[] arr, int n) {
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		int max = 1;

		// Sort the array based on word lengths to ensure the chain builds correctly
		Arrays.sort(arr, (a, b) -> a.length() - b.length());

		for (int i = 0; i < n; i++) {
			for (int prevIndex = 0; prevIndex < i; prevIndex++) {
				if (checkPossibilities(arr[i], arr[prevIndex]) && 1 + dp[prevIndex] > dp[i]) {
					dp[i] = 1 + dp[prevIndex];
				}
			}
			max = Math.max(max, dp[i]);
		}

		return max;
	}

	private static boolean checkPossibilities(String s, String s1) {
		if (s.length() != s1.length() + 1) {
			return false;
		}

		int first = 0;
		int second = 0;

		while (first < s.length() && second < s1.length()) {
			if (s.charAt(first) == s1.charAt(second)) {
				second++;
			}
			first++;
		}

		return second == s1.length();
	}

	public static void main(String[] args) {
		String[] arr = new String[] { "a", "b", "ba", "bca", "bda", "bdca" };
		int n = arr.length;
		System.out.println(longestStrChain(arr, n));  // Output should be 4 (Chain: "a" -> "ba" -> "bda" -> "bdca")
	}
}
