package main.dynamic.programming;

import java.util.Arrays;

/*
Problem statement
You are given an array ‘ARR’ of ‘N’ integers and a target number, ‘TARGET’. Your task is to build an expression out of an array by adding one of the symbols '+' and '-' before each integer in an array, and then by concatenating all the integers, you want to achieve a target. You have to return the number of ways the target can be achieved.

For Example :
You are given the array ‘ARR’ = [1, 1, 1, 1, 1], ‘TARGET’ = 3. The number of ways this target can be achieved is:
1. -1 + 1 + 1 + 1 + 1 = 3
2. +1 - 1 + 1 + 1 + 1 = 3
3. +1 + 1 - 1 + 1 + 1 = 3
4. +1 + 1 + 1 - 1 + 1 = 3
5. +1 + 1 + 1 + 1 - 1 = 3
These are the 5 ways to make. Hence the answer is 5.
Detailed explanation ( Input/output format, Notes, Images )
Constraints :
1 <= T <= 10
1 <= N <= 25
-1000 <= TARGET <= 1000
0 <= ARR[i] <= 1000

Time Limit: 1 sec
Note :
You do not need to print anything. It has already been taken care of. Just implement the given function.
Sample input 1 :
2
5 3
1 1 1 1 1
4 3
1 2 3 1
Sample Output 2 :
5
2
Explanation For Sample Input 1 :
For the first test case, ‘ARR’ = [1, 1, 1, 1, 1], ‘TARGET’ = 3. The number of ways this target can be achieved is:
1. -1 + 1 + 1 + 1 + 1 = 3
2. +1 - 1 + 1 + 1 + 1 = 3
3. +1 + 1 - 1 + 1 + 1 = 3
4. +1 + 1 + 1 - 1 + 1 = 3
5. +1 + 1 + 1 + 1 - 1 = 3
These are the 5 ways to get the target. Hence the answer is 5.

For the second test case, ‘ARR’ = [1, 2, 3, 1]. ‘TARGET’ = 3, The number of ways this target can be achieved is:
1. +1 - 2 + 1 + 3 = 3
2. -1 + 2 - 1 + 3 = 3
These are the 3 ways to get the target. Hence the answer is 2.
Sample Input 2 :
2
3 2
1 2 3
2 0
1 1
Sample Output 2 :
1
2
C++ (g++ 5.4)
12345
#include <bits/stdc++.h>
int targetSum(int n, int target, vector<int>& arr) {
    // Write your code here.
}


*/
public class TargetSum {

	public static int countWaysUsingMemoization(int n, int totalSum, int[] arr, int[][] dp) {
		// Base case: if we've considered all elements
		if (n == 0) {
			if (totalSum == 0 && arr[0] == 0)
				return 2;
			if (totalSum == 0 || totalSum == arr[0])
				return 1;
			return 0;
		}

		// Check if this problem has already been solved
		if (dp[n][totalSum] != -1) {
			return dp[n][totalSum];
		}

		// Recursive cases:
		// 1. Include the current element in subset 1
		int include = 0;
		if (totalSum >= arr[n])
			include = countWaysUsingMemoization(n - 1, totalSum - arr[n], arr, dp);

		// 2. Exclude the current element from subset 1 (so it's in subset 2)
		int exclude = countWaysUsingMemoization(n - 1, totalSum, arr, dp);

		// Return the count of the two possibilities
		dp[n][totalSum] = (include + exclude);
		return dp[n][totalSum];
	}

	public static int countWaysUsingRecursion(int ind, int target, int[] arr) {
		// Base cases
		if (ind == 0) {
			if (target == 0 && arr[0] == 0)
				return 2;
			if (target == 0 || target == arr[0])
				return 1;
			return 0;
		}

		// Recursive cases
		int notTaken = countWaysUsingRecursion(ind - 1, target, arr);

		int taken = 0;
		if (arr[ind] <= target) {
			taken = countWaysUsingRecursion(ind - 1, target - arr[ind], arr);
		}

		// Return the sum of both cases (taking modulo)
		return (notTaken + taken);
	}

	public static int countTargetSumUsingRecursion(int n, int d, int[] arr, String ways) {
		// Calculate total sum of the array
		int totalSum = 0;
		for (int i = 0; i < n; i++) {
			totalSum += arr[i];
		}

		// Check if the partition is possible
		if ((totalSum - d) < 0 || (totalSum - d) % 2 != 0) {
			return 0;
		}

		int newTotalSum = (totalSum - d) / 2;

		// Memoization array to store intermediate results
		if (ways.equals("recursion")) {
			return countWaysUsingRecursion(n - 1, newTotalSum, arr);
		} else if (ways.equals("memoization")) {
			// Memoization table for storing results (initialize to -1)
			int[][] dp = new int[n + 1][newTotalSum + 1];
			for (int[] row : dp) {
				Arrays.fill(row, -1);
			}

			return countWaysUsingMemoization(n, newTotalSum, arr, dp);
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 5, 2, 6, 4 };
		int n = arr.length;
		int d = 3;


		System.out.println(countTargetSumUsingRecursion(n - 1, d, arr, "recursion"));
		System.out.println(countTargetSumUsingRecursion(n - 1, d, arr, "memoization"));


	}
}
