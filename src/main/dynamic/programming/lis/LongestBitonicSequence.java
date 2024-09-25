package main.dynamic.programming.lis;

import java.util.Arrays;

/*
Problem statement
A Bitonic Sequence is a sequence of numbers that is first strictly increasing and then strictly decreasing.
A strictly ascending order sequence is also considered bitonic, with the decreasing part as empty, and same for a strictly descending order sequence.
For example, the sequences [1, 3, 5, 3, 2], [1, 2, 3, 4] are bitonic, whereas the sequences [5, 4, 1, 4, 5] and [1, 2, 2, 3] are not.
You are given an array 'arr' consisting of 'n' positive integers.

Find the length of the longest bitonic subsequence of 'arr'.

Example :
Input: 'arr' = [1, 2, 1, 2, 1]

Output: 3

Explanation: The longest bitonic subsequence for this array will be [1, 2, 1]. Please note that [1, 2, 2, 1] is not a valid bitonic subsequence, because the consecutive 2's are neither strictly increasing, nor strictly decreasing.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
5
1 2 1 2 1

Sample Output 1:
3

Explanation For Sample Input 1:
The longest bitonic subsequence for this array will be [1, 2, 1]. Please note that [1, 2, 2, 1] is not a valid bitonic subsequence, because the consecutive 2's are neither strictly increasing, nor strictly decreasing.

Sample Input 2 :
5
1 2 1 3 4

Sample Output 2 :
4

Explanation For Sample Input 2:
The longest bitonic sequence for this array will be [1, 2, 3, 4].

Expected time complexity :
The expected time complexity is O(n ^ 2).


Constraints:
1 <= 'n' <= 10^3
1 <= 'arr[i]' <= 10^5

Time Limit: 1sec
*/
public class LongestBitonicSequence {

	public static int longestBitonicSequence(int[] arr, int n) {
		// Write your code here.
		int[] dp = new int[n];
		int[] dp1 = new int[n];
		Arrays.fill(dp, 1);
		Arrays.fill(dp1, 1);
		int max = 0;


		for (int i = 0; i < n; i++) {
			for (int prevIndex = 0; prevIndex < i; prevIndex++) {
				if (arr[prevIndex] < arr[i] && 1 + dp[prevIndex] > dp[i]) {
					dp[i] = 1 + dp[prevIndex];
				}
			}
		}

		for (int i = n - 1; i > 0; i--) {
			for (int prevIndex = n - 1; prevIndex > i; prevIndex--) {
				if (arr[prevIndex] < arr[i] && 1 + dp1[prevIndex] > dp1[i]) {
					dp1[i] = 1 + dp1[prevIndex];
				}
			}
			max = Math.max(max, dp[i] + dp1[i] - 1);
		}
		return max;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 1, 2, 1 };
		System.out.println(longestBitonicSequence(arr, arr.length));
	}
}
