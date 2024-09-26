package main.dynamic.programming.partition;

import java.util.Arrays;

/*
Problem statement
You are given chocolate of ‘N’ length. The chocolate is labeled from 0 to ‘N’. You are also given an array ‘CUTS’ of size ‘C’, denoting the positions at which you can do a cut. The order of cuts can be changed. The costUsing of one cut is the length of the chocolate to be cut. Therefore, the total costUsing is the sum of all the cuts. Print the minimum costUsing to cut the chocolate.

Note:

All the integers in the ‘CUTS’ array are distinct.
For example:
Let ‘N’ be: 4
Let the ‘CUTS’ array be: [1, 3].

Let the order of doing the cut be [1, 3].
The first cut of 1 on length 4 results in a costUsing of 4, and chocolate is split into two parts of the length of 1 and 3.
The second cut of 3 on length 3 results in a costUsing of 3, and chocolate is split into two parts again of the length of 2 and 1. So the total costUsing is 7.

The costUsing will remain the same if we change the order of cutting. So the result is 7.
Detailed explanation ( Input/output format, Notes, Images )
Constraints :
1 <= T <= 10
2 <= N <= 10^5
1 <= C <= 10^3
1 <= CUTS[i] <= N - 1

Time Limit: 1 sec
Sample Input 1 :
2
4 2
1 3
5 3
1 3 4
Sample Output 1 :
7
10
Explanation For Sample Output 1 :
For test case 1:
Let the order of doing the cut be [1, 3].
The first cut of 1 on length 4 results in a costUsing of 4, and chocolate is split into two parts of the length of 1 and 3.
The second cut of 3 on length 3 results in a costUsing of 3, and chocolate is split into two parts again of the length of 2 and 1. So the total costUsing is 7.

The costUsing will remain the same if we change the order of cutting. So the result is 7.

For test case 2:
Let the order of doing the cut be [1, 3, 4].
The first cut of 1 on length 5 results in a costUsing of 5, and chocolate is split into two parts of the length of 1 and 4.
The second cut of 3 on length 4 results in a costUsing of 4, and chocolate is split into two parts again of the length of 2 and 2. The total costUsing is 9.
The third cut of 4 on length 2 results in a costUsing of 2, and chocolate is split into two parts again of the length of 1 and 1. The total costUsing is 11.

The minimum costUsing will be 10 when the order of doing cuts will be: [3, 1, 4].
Sample Input 2 :
2
10 4
1 3 4 7
10 2
1 3
Sample Output 2 :
23
13
*/
public class CostToCutAChocolate {

	static int f(int[] cuts, int n, int c) {
		// Initialize dp array
		int[][] dp = new int[c + 2][c + 2];

		// Base case: when there's no space between cuts, the cost is 0
		for (int i = 1; i <= c; i++) {
			dp[i][i] = 0;
		}

		// Iterate for every possible interval length
		for (int i = c; i >= 1; i--) {
			for (int j = 1; j <= c; j++) {
				if (i > j)
					continue;

				int mini = Integer.MAX_VALUE;

				// Try every cut as the partition point
				for (int ind = i; ind <= j; ind++) {
					int cost = cuts[j + 1] - cuts[i - 1] + dp[i][ind - 1] + dp[ind + 1][j];
					mini = Math.min(mini, cost);
				}
				dp[i][j] = mini;
			}
		}

		// The result is stored in dp[1][c]
		return dp[1][c];
	}

	static int f(int i, int j, int[] cuts, int[][] dp) {
		// Base case
		if (i > j) {
			return 0;
		}

		if (dp[i][j] != 0) {
			return dp[i][j];
		}

		int mini = Integer.MAX_VALUE;

		for (int ind = i; ind <= j; ind++) {
			int ans = cuts[j + 1] - cuts[i - 1] + f(i, ind - 1, cuts, dp) + f(ind + 1, j, cuts, dp);

			mini = Math.min(mini, ans);
		}

		return dp[i][j] = mini;
	}

	static int f(int i, int j, int[] cuts) {
		// Base case
		if (i > j) {
			return 0;
		}

		int mini = Integer.MAX_VALUE;

		for (int ind = i; ind <= j; ind++) {
			int ans = cuts[j + 1] - cuts[i - 1] + f(i, ind - 1, cuts) + f(ind + 1, j, cuts);

			mini = Math.min(mini, ans);
		}

		return mini;
	}

	// Function to calculate the minimum costUsing
	static int costUsing(int n, int c, int[] cuts, String type) {
		// Modify the cuts array
		int[] newCuts = new int[c + 2]; // To include 0 and n
		newCuts[0] = 0;
		newCuts[c + 1] = n;

		if (c >= 0)
			System.arraycopy(cuts, 0, newCuts, 1, c);

		// Sort the new cuts array
		Arrays.sort(newCuts);

		if (type.equals("recursion"))
			return f(1, c, newCuts);
		else if (type.equals("memo")) {
			int[][] dp = new int[newCuts.length + 1][newCuts.length + 1];
			return f(1, c, newCuts, dp);
		} else {
			return f(newCuts, newCuts.length, c);
		}
	}

	public static void main(String[] args) {
		int[] cuts = { 3, 5, 1, 4 };
		int c = cuts.length;
		int n = 7;

		System.out.println("The minimum costUsing incurred: " + costUsing(n, c, cuts, "recursion"));
		System.out.println("The minimum costUsing incurred: " + costUsing(n, c, cuts, "memo"));
		System.out.println("The minimum costUsing incurred: " + costUsing(n, c, cuts, "tab"));
	}
}
