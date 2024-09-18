package main.dynamic.programming;

import java.util.Arrays;

/*
Problem statement
You are given an infinite supply of coins of each of denominations D = {D0, D1, D2, D3, ...... Dn-1}. You need to figure out the total number of ways W, in which you can make a change for value V using coins of denominations from D. Print 0, if a change isn't possible.

Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
3
1 2 3
4
Sample Output 1:
4
Explanation for Sample Output 1:
We can make a change for the value V = 4 in four ways.
1. (1,1,1,1),
2. (1,1, 2), [One thing to note here is, (1, 1, 2) is same as that of (2, 1, 1) and (1, 2, 1)]
3. (1, 3), and
4. (2, 2)
Sample Input 2 :
3
5 3 2
1
Sample Output 2:
0
*/
public class WaysToMakeCoinChange {


	public static long countWaysToMakeChangeUsingTabularSpaceOptimal(int denominations[], int value, int n) {
		//write your code here

		long[] prev = new long[value + 1];
		long[] current = new long[value + 1];

		for (int j = 0; j <= value; j++) {
			if (j % denominations[0] == 0) {
				prev[j] = 1;
			} else {
				prev[j] = 0;
			}
		}
		for (int j = 1; j < n; j++) {
			for (int target = 0; target <= value; target++) {

				long exclude = prev[target];

				long include = 0;
				if (denominations[j] <= target) {
					include = current[target - denominations[j]];
				}

				current[target] = exclude + include;
			}
			prev = current;
		}

		return prev[value];
	}


	public static long countWaysToMakeChangeUsingTabular(int denominations[], int value, int n) {
		//write your code here

		long[][] dp = new long[n][value + 1];
		for (int j = 0; j <= value; j++) {
			if (j % denominations[0] == 0) {
				dp[0][j] = 1;
			}
		}
		for (int j = 1; j < n; j++) {
			for (int target = 0; target <= value; target++) {

				long exclude = dp[j - 1][target];

				long include = 0;
				if (denominations[j] <= target) {
					include = dp[j][target - denominations[j]];
				}

				dp[j][target] = exclude + include;
			}
		}

		return dp[n - 1][value];
	}

	public static long countWaysToMakeChangeUsingMemo(int denominations[], int value, int i, long[][] dp) {
		//write your code here

		if (i == 0) {
			if (value % denominations[i] == 0) {
				return 1;
			} else {
				return 0;
			}
		}

		// Check if this problem has already been solved
		if (dp[i][value] != -1) {
			return dp[i][value];
		}

		long exclude = countWaysToMakeChangeUsingMemo(denominations, value, i - 1, dp);

		long include = 0;
		if (denominations[i] <= value) {
			include = countWaysToMakeChangeUsingMemo(denominations, value - denominations[i], i, dp);
		}
		dp[i][value] = exclude + include;
		return dp[i][value];
	}

	public static long countWaysToMakeChange(int denominations[], int value, int i) {
		//write your code here

		if (i == 0) {
			if (value % denominations[i] == 0) {
				return 1;
			} else {
				return 0;
			}
		}

		long exclude = countWaysToMakeChange(denominations, value, i - 1);

		long include = 0;
		if (denominations[i] <= value) {
			include = countWaysToMakeChange(denominations, value - denominations[i], i);
		}
		return exclude + include;
	}

	public static void main(String[] args) {
		int[] denominations = { 1, 2, 3 };
		int target = 4;
		int n = denominations.length;
		System.out.println(countWaysToMakeChange(denominations, target, n - 1));

		// Memoization table for storing results (initialize to -1)
		long[][] dp = new long[n + 1][target + 1];
		for (long[] row : dp) {
			Arrays.fill(row, -1);
		}
		System.out.println(countWaysToMakeChangeUsingMemo(denominations, target, n - 1, dp));
		System.out.println(countWaysToMakeChangeUsingTabular(denominations, target, n));
		System.out.println(countWaysToMakeChangeUsingTabularSpaceOptimal(denominations, target, n));
	}


}
