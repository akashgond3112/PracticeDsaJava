package main.dynamic.programming;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem statement
You are given an array/list of ‘N’ integers. You are supposed to return the maximum sum of the subsequence with the constraint that no two elements are adjacent in the given array/list.

Note:
A subsequence of an array/list is obtained by deleting some number of elements (can be zero) from the array/list, leaving the remaining elements in their original order.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 500
1 <= N <= 1000
0 <= ARR[i] <= 10^5

Where 'ARR[i]' denotes the 'i-th' element in the array/list.

Time Limit: 1 sec.
Sample Input 1:
2
3
1 2 4
4
2 1 4 9
Sample Output 1:
5
11
Explanation to Sample Output 1:
In test case 1, the sum of 'ARR[0]' & 'ARR[2]' is 5 which is greater than 'ARR[1]' which is 2 so the answer is 5.

In test case 2, the sum of 'ARR[0]' and 'ARR[2]' is 6, the sum of 'ARR[1]' and 'ARR[3]' is 10, and the sum of 'ARR[0]' and 'ARR[3]' is 11. So if we take the sum of 'ARR[0]' and 'ARR[3]', it will give the maximum sum of sequence in which no elements are adjacent in the given array/list.
Sample Input 2:
2
5
1 2 3 5 4
9
1 2 3 1 3 5 8 1 9
Sample Output 2:
8
24
Explanation to Sample Output 2:
In test case 1, out of all the possibilities, if we take the sum of 'ARR[0]', 'ARR[2]' and 'ARR[4]', i.e. 8, it will give the maximum sum of sequence in which no elements are adjacent in the given array/list.

In test case 2, out of all the possibilities, if we take the sum of 'ARR[0]', 'ARR[2]', 'ARR[4]', 'ARR[6]' and 'ARR[8]', i.e. 24 so, it will give the maximum sum of sequence in which no elements are adjacent in the given array/list.
*/
public class MaximumSumOfNonAdjacentElements {

	public static int maximumNonAdjacentSumUsingTabularOptimal(ArrayList<Integer> nums, int n) {
		int prev = nums.get(0);  // Start with the first element
		int prev2 = 0;  // Base case for the second previous element (which is none for index 0)

		for (int i = 1; i < n; i++) {
			int pick = nums.get(i) + (i > 1 ? prev2 : 0);  // Pick the current element and add prev2 (if applicable)
			int notPick = prev;  // Not pick means take the previous result

			int curr = Math.max(pick, notPick);  // Choose the maximum of pick and notPick
			prev2 = prev;  // Shift prev2 to prev
			prev = curr;  // Shift prev to current
		}
		return prev;  // The answer is stored in prev
	}

	public static int maximumNonAdjacentSumUsingTabular(ArrayList<Integer> nums, int n, int[] dp) {
		dp[0] = nums.get(0);  // Initialize dp[0] as the first element

		for (int i = 1; i < n; i++) {
			int pick = nums.get(i) + (i > 1 ? dp[i - 2] : 0);  // Pick current element + dp[i-2] if exists
			int notPick = dp[i - 1];  // Not pick means take the previous dp value

			dp[i] = Math.max(pick, notPick);  // Store the maximum
		}
		return dp[n - 1];  // The result is stored in dp[n-1]
	}

	public static int maximumNonAdjacentSum(ArrayList<Integer> nums, int n, int[] dp) {
		// Write your code here.
		if (n == 0) {
			return nums.get(n);  // Base case: no cost for starting point
		}

		if (n < 0) {
			return 0;
		}

		// If the result is already computed, return it
		if (dp[n] != -1) {
			return dp[n];
		}

		int pick = nums.get(n) + maximumNonAdjacentSum(nums, n - 2, dp);
		int notPick = maximumNonAdjacentSum(nums, n - 1, dp);

		return dp[n] = Math.max(pick, notPick);
	}

	public static void main(String[] args) {
		ArrayList<Integer> nums = new ArrayList<>();
		nums.add(1);
		nums.add(2);
		nums.add(3);
		nums.add(4);
		nums.add(5);

		int n = nums.size();  // Index of the last stone

		// Initialize dp array with -1 (indicating that no value is computed yet)
		int[] dpMemo = new int[n];
		Arrays.fill(dpMemo, -1);

		int[] dp = new int[n];

		System.out.println(maximumNonAdjacentSum(nums, n - 1, dpMemo));
		System.out.println(maximumNonAdjacentSumUsingTabular(nums, n, dp));
		System.out.println(maximumNonAdjacentSumUsingTabularOptimal(nums, n));
	}
}
