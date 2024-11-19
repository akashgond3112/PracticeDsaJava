package main.dsa.dp.lis;

import java.util.Arrays;

/*
Problem statement
Given an integer array ‘arr’ of length ‘n’, return the number of longest increasing subsequences in it.

The longest increasing subsequence(LIS) is the longest subsequence of the given sequence such that all subsequent elements are in strictly increasing order.

Example:
Input: ‘n’ = 5, ‘arr’ = [50, 3, 90, 60, 80].

Output: 2

Explanation:
In this array, the longest increasing subsequences are [50, 60, 80] and [3, 60, 80].

There are other increasing subsequences as well, but we need the number of the longest ones. Hence the answer is 2.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
4
3 7 4 6
Sample Output 1 :
1
Explanation For Sample Input 1 :
The length of LIS is 3, and there is only one such LIS, which is [3, 4, 6].
Sample Input 2 :
4
5 7 2 3
Sample Output 2 :
2
Explanation For Sample Input 2 :
The length of LIS is 2, and there are two such LIS, which are [5, 7] and [2, 3].
Expected Time Complexity:
The expected time complexity is O(n^2).
Constraints :
1 ≤ ‘n’ ≤ 5000
1 ≤ ‘arr’[i] ≤ 10 ^ 6

Time limit: 1 sec
*/
public class NumberOfLongestIncreasingSubsequence {

	public static int findNumberOfLIS(int[] arr, int n) {
		// Write your code here.

		int[] dp = new int[n];
		int[] count = new int[n];
		Arrays.fill(dp, 1);
		Arrays.fill(count, 1);
		int max = 0;


		for (int i = 0; i < n; i++) {
			for (int prevIndex = 0; prevIndex < i; prevIndex++) {
				if (arr[prevIndex] < arr[i] && 1 + dp[prevIndex] > dp[i]) {
					dp[i] = 1 + dp[prevIndex];
					count[i] = count[prevIndex];
				} else if (arr[i] > arr[prevIndex] && dp[prevIndex] + 1 == dp[i]) {
					count[i] += count[prevIndex];

				}
			}
			max = Math.max(dp[i], max);
		}

		int countNew = 0;
		for (int i = 0; i < n; i++) {
			if (dp[i] == max) {
				countNew += count[i];
			}
		}

		return countNew;
	}

	public static void main(String[] args) {
		int[] arr = { 50, 3, 90, 60, 80 };
		System.out.println(findNumberOfLIS(arr, arr.length));
	}
}
