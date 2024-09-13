package main.dynamic.programming;

import java.util.Arrays;

/*
Problem statement
You are given an array/list ‘ARR’ of ‘N’ positive integers and an integer ‘K’. Your task is to check if there exists a subset in ‘ARR’ with a sum equal to ‘K’.

Note: Return true if there exists a subset with sum equal to ‘K’. Otherwise, return false.

For Example :
If ‘ARR’ is {1,2,3,4} and ‘K’ = 4, then there exists 2 subsets with sum = 4. These are {1,3} and {4}. Hence, return true.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 5
1 <= N <= 10^3
0 <= ARR[i] <= 10^9
0 <= K <= 10^3

Time Limit: 1 sec
Sample Input 1:
2
4 5
4 3 2 1
5 4
2 5 1 6 7
Sample Output 1:
true
false
Explanation For Sample Input 1:
In example 1, ‘ARR’ is {4,3,2,1} and ‘K’ = 5. There exist 2 subsets with sum = 5. These are {4,1} and {3,2}. Hence, return true.
In example 2, ‘ARR’ is {2,5,1,6,7} and ‘K’ = 4. There are no subsets with sum = 4. Hence, return false.
Sample Input 2:
2
4 4
6 1 2 1
5 6
1 7 2 9 10
Sample Output 2:
true
false
Explanation For Sample Input 2:
In example 1, ‘ARR’ is {6,1,2,1} and ‘K’ = 4. There exist 1 subset with sum = 4. That is {1,2,1}. Hence, return true.
In example 2, ‘ARR’ is {1,7,2,9,10} and ‘K’ = 6. There are no subsets with sum = 6. Hence, return false.


Hints:
1. Can you find every possible subset of ‘ARR’ and check if its sum is equal to ‘K’?
2. Can you use dynamic programming and use the previously calculated result to calculate the new result?
3. Try to use a recursive approach followed by memoization by including both index and sum we can form.
Javascript (node v10.20.0)

*/
public class SubsetSumEqualToK {


	public static boolean subsetSumToKUsingTabularSpaceOptimal(int n, int k, int[] arr) {
		// Write your code here.

		boolean[] prev = new boolean[k + 1];

		prev[0] = true;

		// Initialize the first column of the DP table
		if (arr[0] <= k) {
			prev[arr[0]] = true;
		}

		for (int i = 1; i < n; i++) {
			boolean[] curr = new boolean[k + 1];
			curr[0] = true;

			for (int j = 1; j <= k; j++) {

				boolean notTake = prev[j];

				boolean take = false;

				if (j >= arr[i]) {
					take = prev[j - arr[i]];
				}

				curr[j] = notTake || take;
			}
			prev = curr;
		}
		return prev[k];
	}

	public static boolean subsetSumToKUsingTabular(int n, int k, int[] arr) {
		// Write your code here.

		boolean[][] dp = new boolean[n][k + 1];

		for (int i = 0; i < n; i++) {
			dp[i][0] = true;
		}

		dp[0][arr[0]] = true;

		for (int i = 1; i < n; i++) {
			for (int j = 1; j <= k; j++) {

				boolean notTake = dp[i - 1][j];

				boolean take = false;

				if (j >= arr[i]) {
					take = dp[i - 1][j - arr[i]];
				}

				dp[i][j] = notTake || take;

			}
		}
		return dp[n - 1][k];
	}

	public static boolean subsetSumToKUsingMemo(int n, int k, int arr[], int[][] dp) {
		// Write your code here.

		if (k == 0)
			return true;

		if (n == 0)
			return arr[0] == k;

		if (dp[n][k] != -1)
			return false;

		boolean notTake = subsetSumToK(n - 1, k, arr);

		boolean take = false;

		if (k >= arr[n]) {
			take = subsetSumToK(n - 1, k - arr[n], arr);
		}

		dp[n][k] = k;
		return take || notTake;
	}

	public static boolean subsetSumToK(int n, int k, int arr[]) {
		// Write your code here.

		if (k == 0)
			return true;

		if (n == 0)
			return arr[0] == k;

		boolean notTake = subsetSumToK(n - 1, k, arr);

		boolean take = false;

		if (k >= arr[n]) {
			take = subsetSumToK(n - 1, k - arr[n], arr);
		}

		return take || notTake;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5 };
		int n = arr.length;
		int k = 7;
		System.out.println(subsetSumToK(n - 1, k, arr));

		int[][] dp = new int[n + 1][k + 1];
		for (int[] row : dp)
			Arrays.fill(row, -1);

		System.out.println(subsetSumToKUsingMemo(n - 1, k, arr, dp));
		System.out.println(subsetSumToKUsingTabular(n - 1, k, arr));
		System.out.println(subsetSumToKUsingTabularSpaceOptimal(n - 1, k, arr));
	}
}
