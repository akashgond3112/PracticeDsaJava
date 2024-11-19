package main.dsa.dp.subsequence;

import java.util.Arrays;

/*
Problem statement
Given a rod of length ‘N’ units. The rod can be cut into different sizes and each size has a cost associated with it. Determine the maximum cost obtained by cutting the rod and selling its pieces.

Note:
1. The sizes will range from 1 to ‘N’ and will be integers.

2. The sum of the pieces cut should be equal to ‘N’.

3. Consider 1-based indexing.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 50
1 <= N <= 100
1 <= A[i] <= 100

Where ‘T’ is the total number of test cases, ‘N’ denotes the length of the rod, and A[i] is the cost of sub-length.

Time limit: 1 sec.
Sample Input 1:
2
5
2 5 7 8 10
8
3 5 8 9 10 17 17 20
Sample Output 1:
12
24
Explanation of sample input 1:
Test case 1:

All possible partitions are:
1,1,1,1,1           max_cost=(2+2+2+2+2)=10
1,1,1,2             max_cost=(2+2+2+5)=11
1,1,3               max_cost=(2+2+7)=11
1,4                 max_cost=(2+8)=10
5                   max_cost=(10)=10
2,3                 max_cost=(5+7)=12
1,2,2               max _cost=(1+5+5)=12

Clearly, if we cut the rod into lengths 1,2,2, or 2,3, we get the maximum cost which is 12.


Test case 2:

Possible partitions are:
1,1,1,1,1,1,1,1         max_cost=(3+3+3+3+3+3+3+3)=24
1,1,1,1,1,1,2           max_cost=(3+3+3+3+3+3+5)=23
1,1,1,1,2,2             max_cost=(3+3+3+3+5+5)=22
and so on….

If we cut the rod into 8 pieces of length 1, for each piece 3 adds up to the cost. Hence for 8 pieces, we get 8*3 = 24.
Sample Input 2:
1
6
3 5 6 7 10 12
Sample Output 2:
18
*/
public class RodCuttingProblem {

	public static int cutRodUsingTabularSpaceOptimal1D(int[] price, int n) {
		// Write your code here.

		int[] prev = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			prev[i] = i * price[0];
		}

		for (int i = 1; i < n; i++) {
			for (int j = 1; j <= n; j++) {
				// Recursive cases:
				int include = Integer.MIN_VALUE;
				int rodLength = i + 1;
				if (rodLength <= j) {
					include = price[i] + prev[j - rodLength];
				}
				// 2. Exclude the current item
				int exclude = prev[j];

				// Return the maximum of both possibilities
				prev[j] = Math.max(include, exclude);

			}
		}
		return prev[n];
	}

	public static int cutRodUsingTabularSpaceOptimal(int[] price, int n) {
		// Write your code here.

		int[] prev = new int[n + 1];
		int[] curr = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			prev[i] = i * price[0];
		}

		for (int i = 1; i < n; i++) {
			for (int j = 1; j <= n; j++) {
				// Recursive cases:
				int include = Integer.MIN_VALUE;
				int rodLength = i + 1;
				if (rodLength <= j) {
					include = price[i] + curr[j - rodLength];
				}
				// 2. Exclude the current item
				int exclude = prev[j];

				// Return the maximum of both possibilities
				curr[j] = Math.max(include, exclude);

			}
			prev = curr;
		}
		return prev[n];
	}

	public static int cutRodUsingTabular(int[] price, int n) {
		// Write your code here.

		int[][] dp = new int[n][n + 1];

		for (int i = 1; i <= n; i++) {
			dp[0][i] = i * price[0];
		}

		for (int i = 1; i < n; i++) {
			for (int j = 1; j <= n; j++) {
				// Recursive cases:
				int include = Integer.MIN_VALUE;
				int rodLength = i + 1;
				if (rodLength <= j) {
					include = price[i] + dp[i][j - rodLength];
				}
				// 2. Exclude the current item
				int exclude = dp[i - 1][j];

				// Return the maximum of both possibilities
				dp[i][j] = Math.max(include, exclude);

			}
		}
		return dp[n - 1][n];
	}

	public static int cutRodUsingMemo(int[] price, int n, int maxRodLength, int[][] dp) {
		// Write your code here.
		if (n == 0) {
			return maxRodLength * price[0];
		}

		// Check if this problem has already been solved
		if (dp[n][maxRodLength] != -1) {
			return dp[n][maxRodLength];
		}

		// Recursive cases:
		int include = Integer.MIN_VALUE;
		int rodLength = n + 1;
		if (rodLength <= maxRodLength) {
			include = price[n] + cutRodUsingMemo(price, n, maxRodLength - rodLength, dp);
		}

		// 2. Exclude the current item
		int exclude = cutRodUsingMemo(price, n - 1, maxRodLength, dp);

		// Return the maximum of both possibilities
		dp[n][maxRodLength] = Math.max(include, exclude);
		return dp[n][maxRodLength];
	}

	public static int cutRod(int[] price, int n, int maxRodLength) {
		// Write your code here.
		if (n == 0) {
			return maxRodLength * price[0];
		}

		// Recursive cases:
		int include = Integer.MIN_VALUE;
		int rodLength = n + 1;
		if (rodLength <= maxRodLength) {
			include = price[n] + cutRod(price, n, maxRodLength - rodLength);
		}

		// 2. Exclude the current item
		int exclude = cutRod(price, n - 1, maxRodLength);

		// Return the maximum of both possibilities
		return Math.max(include, exclude);
	}

	public static void main(String[] args) {
		int[] price = { 2, 5, 7, 8, 10 };
		int n = price.length;

		System.out.println(cutRod(price, n - 1, n));

		// Memoization table for storing results (initialize to -1)
		int[][] dp = new int[n + 1][n + 1];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}
		System.out.println(cutRodUsingMemo(price, n - 1, n, dp));
		System.out.println(cutRodUsingTabular(price, n));
		System.out.println(cutRodUsingTabularSpaceOptimal(price, n));
		System.out.println(cutRodUsingTabularSpaceOptimal1D(price, n));
	}
}
