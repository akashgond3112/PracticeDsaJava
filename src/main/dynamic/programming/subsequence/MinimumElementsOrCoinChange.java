package main.dynamic.programming.subsequence;

import java.util.Arrays;

/*
Problem statement
You are given an array of ‘N’ distinct integers and an integer ‘X’ representing the target sum. You have to tell the minimum number of elements you have to take to reach the target sum ‘X’.

Note:
You have an infinite number of elements of each type.
For example
If N=3 and X=7 and array elements are [1,2,3].
Way 1 - You can take 4 elements  [2, 2, 2, 1] as 2 + 2 + 2 + 1 = 7.
Way 2 - You can take 3 elements  [3, 3, 1] as 3 + 3 + 1 = 7.
Here, you can see in Way 2 we have used 3 coins to reach the target sum of 7.
Hence the output is 3.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 10
1 <= N <= 15
1 <= nums[i] <= (2^31) - 1
1 <= X <= 10000

All the elements of the “nums” array will be unique.
Time limit: 1 sec
Sample Input 1 :
2
3 7
1 2 3
1 0
1
Sample output 1 :
 3
 0
Explanation For Sample Output 1:
For the first test case,
Way 1 - You can take 4 elements  [2, 2, 2, 1] as 2 + 2 + 2 + 1 = 7.
Way 2 - You can take 3 elements  [3, 3, 1] as 3 + 3 + 1 = 7.
Here, you can see in Way 2 we have used 3 coins to reach the target sum of 7.
Hence the output is 3.

For the second test case,
Way 1 - You can take 3 elements  [1, 1, 1] as 1 + 1 + 1  = 3.
Way 2 - You can take 2 elements  [2, 1] as 2 + 1 = 3.
Here, you can see in Way 2 we have used 2 coins to reach the target sum of 7.
Hence the output is 2.
Sample Input 2 :
2
3 4
12 1 3
2 11
2 1
Sample output 2 :
2
6
*/
public class MinimumElementsOrCoinChange {


	public static int minimumElementsUsingTabularSpaceOptimal(int num[], int target, int n) {

		int[] prev = new int[target + 1];
		int[] current = new int[target + 1];

		// Initialize the first row for the first item
		for (int t = 0; t <= target; t++) {
			if (t % num[0] == 0) {
				prev[t] = t / num[0];  // Corrected to t / num[0]
			} else {
				prev[t] = (int) 1e9;  // Use a large number to indicate it's not possible
			}
		}

		// Fill the DP table for all remaining items
		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= target; j++) {

				// Case 1: Include the current element
				int include = (int) 1e9;  // Initialize to a large number
				if (num[i] <= j)
					include = current[j - num[i]] + 1;  // Corrected indexing here

				// Case 2: Exclude the current element
				int exclude = prev[j];

				// Minimum of including or excluding the element
				current[j] = Math.min(include, exclude);
			}
			prev = current;
		}

		// If the result is still 1e9, it means it's impossible to form the target
		// Get the minimum number of elements needed for the target sum
		int ans = prev[target];

		// If it's not possible to achieve the target sum, return -1
		if (ans >= (int) Math.pow(10, 9))
			return -1;
		return ans;
	}

	public static int minimumElementsUsingTabular(int num[], int target, int n) {

		int[][] dp = new int[n][target + 1];

		// Initialize the first row for the first item
		for (int t = 0; t <= target; t++) {
			if (t % num[0] == 0) {
				dp[0][t] = t / num[0];  // Corrected to t / num[0]
			} else {
				dp[0][t] = (int) 1e9;  // Use a large number to indicate it's not possible
			}
		}

		// Fill the DP table for all remaining items
		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= target; j++) {

				// Case 1: Include the current element
				int include = (int) 1e9;  // Initialize to a large number
				if (num[i] <= j)
					include = dp[i][j - num[i]] + 1;  // Corrected indexing here

				// Case 2: Exclude the current element
				int exclude = dp[i - 1][j];

				// Minimum of including or excluding the element
				dp[i][j] = Math.min(include, exclude);
			}
		}

		// If the result is still 1e9, it means it's impossible to form the target
		return dp[n - 1][target] >= (int) 1e9 ? -1 : dp[n - 1][target];
	}

	public static int minimumElementsUsingMemo(int num[], int x, int n, int[][] dp) {
		// Base Case:
		if (n == 0) {
			if (x % num[n] == 0) {
				return x / num[n];
			} else {
				return (int) 1e9;
			}
		}

		// Check if this problem has already been solved
		if (dp[n][x] != -1) {
			return dp[n][x];
		}

		// Recursive cases:
		// 1. Include the current element in subset 1
		int include = Integer.MAX_VALUE;
		if (num[n] <= x)
			include = minimumElementsUsingMemo(num, x - num[n], n, dp) + 1;

		// 2. Exclude the current element from subset 1 (so it's in subset 2)
		int exclude = minimumElementsUsingMemo(num, x, n - 1, dp);

		// Return the count of the two possibilities
		dp[n][x] = Math.min(include, exclude);
		return dp[n][x];
	}

	public static int minimumElementsUsingRecursion(int num[], int x, int n) {
		// Base Case:
		if (n == 0) {
			if (x % num[n] == 0) {
				return x / num[n];
			} else {
				return (int) 1e9;
			}
		}

		// Recursive cases:
		// 1. Include the current element in subset 1
		int include = Integer.MAX_VALUE;
		if (num[n] <= x)
			include = minimumElementsUsingRecursion(num, x - num[n], n) + 1;

		// 2. Exclude the current element from subset 1 (so it's in subset 2)
		int exclude = minimumElementsUsingRecursion(num, x, n - 1);

		// Return the count of the two possibilities
		return Math.min(include, exclude);
	}

	public static void main(String[] args) {
		int[] num = new int[] { 1, 2, 3 };
		int n = num.length;
		int x = 7;
		System.out.println(minimumElementsUsingRecursion(num, x, n - 1));


		// Memoization table for storing results (initialize to -1)
		int[][] dp = new int[n + 1][x + 1];
		for (int[] row : dp) {
			Arrays.fill(row, -1);
		}
		System.out.println(minimumElementsUsingMemo(num, x, n - 1, dp));
		System.out.println(minimumElementsUsingTabular(num, x, n));
		System.out.println(minimumElementsUsingTabularSpaceOptimal(num, x, n));

	}
}
