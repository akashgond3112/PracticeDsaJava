package main.dynamic.programming.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
Problem statement
For a given array with N elements, you need to find the length of the longest subsequence from the array such that all the elements of the subsequence are sorted in strictly increasing order.

Strictly Increasing Sequence is when each term in the sequence is larger than the preceding term.

For example:
[1, 2, 3, 4] is a strictly increasing array, while [2, 1, 4, 3] is not.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input :
6
5 4 11 1 16 8
Sample Output 1 :
3
Explanation of Sample Input 1:
Length of longest subsequence is 3 i.e. [5, 11, 16] or [4, 11, 16].
Sample Input 2:
3
1 2 2
Sample Output 2 :
2
*/
public class LongestIncreasingSubsequence {


	static int longestIncreasingSubsequenceUsingBinarySearch(int arr[], int n) {
		// List to store the end elements of the potential subsequences
		int[] dp = new int[n];
		int length = 0; // Length of the LIS found so far

		for (int i = 0; i < n; i++) {
			// Perform binary search to find the position where arr[i] can be placed
			int pos = Arrays.binarySearch(dp, 0, length, arr[i]);

			// If not found, binarySearch returns (-(insertion point) - 1)
			if (pos < 0) {
				pos = -(pos + 1);
			}

			// Place arr[i] in the correct position
			dp[pos] = arr[i];

			// If the element is placed at the end of the list, increase the length
			if (pos == length) {
				length++;
			}
		}

		return length;
	}

	static int printLongestIncreasingSubsequence(int arr[], int n) {
		int[] dp = new int[n];
		int[] hash = new int[n];
		Arrays.fill(dp, 1);
		Arrays.fill(hash, 1);

		for (int i = 0; i < n; i++) {
			hash[i] = i; // initializing with current index
			for (int prevIndex = 0; prevIndex < i; prevIndex++) {
				if (arr[prevIndex] < arr[i] && 1 + dp[prevIndex] > dp[i]) {
					dp[i] = 1 + dp[prevIndex];
					hash[i] = prevIndex;
				}
			}
		}

		int ans = -1;
		int lastIndex = -1;

		for (int i = 0; i < n; i++) {
			if (dp[i] > ans) {
				ans = dp[i];
				lastIndex = i;
			}
		}

		ArrayList<Integer> temp = new ArrayList<>();
		temp.add(arr[lastIndex]);

		while (hash[lastIndex] != lastIndex) { // till not reach the initialization value
			lastIndex = hash[lastIndex];
			temp.add(arr[lastIndex]);
		}

		// reverse the array
		System.out.print("The subsequence elements are: ");
		for (int i = temp.size() - 1; i >= 0; i--) {
			System.out.print(temp.get(i) + " ");
		}
		System.out.println();

		return ans;
	}

	public static int longestIncreasingSubsequenceUsingTabularSpaceOptimalMore(int arr[], int n) {

		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		int max = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			max = Math.max(max, dp[i]);
		}

		return max;
	}

	public static int longestIncreasingSubsequenceUsingTabularSpaceOptimal(int arr[], int n) {

		int[] prev = new int[n + 1];
		int[] curr = new int[n + 1];

		for (int i1 = n - 1; i1 >= 0; i1--) {
			for (int j1 = i1 - 1; j1 >= -1; j1--) {
				if (j1 == -1 || arr[i1] > arr[j1]) {
					curr[j1 + 1] = Math.max(curr[j1 + 1], 1 + prev[i1 + 1]);
				} else {
					curr[j1 + 1] = prev[j1 + 1];
				}
			}
			prev = curr;
		}

		return prev[-1 + 1];
	}


	public static int longestIncreasingSubsequenceUsingTabular(int arr[], int n) {

		int[][] dp = new int[n + 1][n + 1];

		for (int i1 = n - 1; i1 >= 0; i1--) {
			for (int j1 = i1 - 1; j1 >= -1; j1--) {
				if (j1 == -1 || arr[i1] > arr[j1]) {
					dp[i1][j1 + 1] = Math.max(dp[i1][j1 + 1], 1 + dp[i1 + 1][i1 + 1]);
				} else {
					dp[i1][j1 + 1] = dp[i1 + 1][j1 + 1];
				}
			}
		}

		return dp[0][-1 + 1];
	}

	public static int longestIncreasingSubsequenceUsingMemo(int arr[], int i, int prev, int[][] dp) {
		//Your code goes here

		if (i == arr.length) {
			return 0;
		}

		if (dp[i][prev + 1] != -1) {
			return dp[i][prev + 1];
		}

		int result = 0;

		if (prev == -1 || arr[i] > arr[prev]) {
			result = Math.max(result, 1 + longestIncreasingSubsequenceUsingMemo(arr, i + 1, i, dp));
		} else {
			result = longestIncreasingSubsequenceUsingMemo(arr, i + 1, prev, dp);
		}

		return dp[i][prev + 1] = result;
	}

	public static int longestIncreasingSubsequenceUsingRecursion(int arr[], int i, int prev) {
		//Your code goes here

		if (i == arr.length) {
			return 0;
		}

		int result = 0;

		if (prev == -1 || arr[i] > arr[prev]) {
			result = Math.max(result, 1 + longestIncreasingSubsequenceUsingRecursion(arr, i + 1, i));
		} else {
			result = longestIncreasingSubsequenceUsingRecursion(arr, i + 1, prev);
		}

		return result;
	}

	public static void main(String[] args) {

		int[] arr = new int[] { 5, 4, 11, 1, 16, 8 };
		int n = arr.length;
		System.out.println(longestIncreasingSubsequenceUsingRecursion(arr, 0, -1));

		// Memoization table for storing results (initialize to -1)
		int[][] dp = new int[n][n + 1];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}
		System.out.println(longestIncreasingSubsequenceUsingMemo(arr, 0, -1, dp));
		System.out.println(longestIncreasingSubsequenceUsingTabular(arr, n));
		System.out.println(longestIncreasingSubsequenceUsingTabularSpaceOptimal(arr, n));
		System.out.println(longestIncreasingSubsequenceUsingTabularSpaceOptimalMore(arr, n));
		System.out.println(printLongestIncreasingSubsequence(arr, n));
		System.out.println(longestIncreasingSubsequenceUsingBinarySearch(arr, n));
	}
}
