package main.dynamic.programming;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem statement
Mr. X is a professional robber planning to rob houses along a street. Each house has a certain amount of money hidden.



All houses along this street are arranged in a circle. That means the first house is the neighbour of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses are broken into on the same night.



You are given an array/list of non-negative integers 'ARR' representing the amount of money of each house. Your task is to return the maximum amount of money Mr. X can rob tonight without alerting the police.



Note:
It is possible for Mr. X to rob the same amount of money by looting two different sets of houses. Just print the maximum possible robbed amount, irrespective of sets of houses robbed.


For example:
(i) Given the input array arr[] = {2, 3, 2} the output will be 3 because Mr X cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses. So, he’ll rob only house 2 (money = 3)

(ii) Given the input array arr[] = {1, 2, 3, 1} the output will be 4 because Mr X rob house 1 (money = 1) and then rob house 3 (money = 3).

(iii) Given the input array arr[] = {0} the output will be 0 because Mr. X has got nothing to rob.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 10
1 <= N <= 5 x 10 ^ 3
1 <= ARR[i] <= 10 ^ 9

Time limit: 1 sec.
Sample Input 1:
3
1
0
3
2 3 2
4
1 3 2 1
Sample Output 1:
0
3
4
Explanation of Input 1:
(i) Mr. X has only one house to rob, but with no money.

(ii) Mr. X cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses (remember, it’s a circular street). So, he’ll rob only house 2 (money = 3) with a maximum value

(iii) Mr. X will get maximum value when he robs house 2 (money = 3) and then robs house 4 (money = 1) i.e. 4 units of money.
Sample Input 2:
3
5
1 5 1 2 6
3
2 3 5
4
1 3 2 0
Sample Output 2:
11
5
3
*/
public class HouseRobberII {

	public static long houseRobberUsingTabularOptimal(int[] nums, int n) {
		int prev = nums[0]; // Start with the first element
		int prev2 = 0;  // Base case for the second previous element (which is none for index 0)

		for (int i = 1; i < n; i++) {
			int pick = nums[i] + (i > 1 ? prev2 : 0);  // Pick the current element and add prev2 (if applicable)
			int notPick = prev;  // Not pick means take the previous result

			int curr = Math.max(pick, notPick);  // Choose the maximum of pick and notPick
			prev2 = prev;  // Shift prev2 to prev
			prev = curr;  // Shift prev to current
		}
		return prev;  // The answer is stored in prev
	}

	public static long getHouseRobberUsingTabularOptimal(int[] nums, int n) {

		int[] temp = new int[n];
		int[] temp2 = new int[n];
		if (n == 1)
			return nums[0];

		for (int i = 0; i < n; i++) {
			if (i != 0)
				temp[i] = nums[i];
			if (i != n - 1)
				temp2[i] = nums[i];
		}

		return Math.max(houseRobberUsingTabularOptimal(temp, n), houseRobberUsingTabularOptimal(temp2, n));
	}

	public static int houseRobberUsingMemo(int[] nums, int n, int[] dp) {
		// Write your code here.
		if (n == 0) {
			return nums[n];  // Base case: no cost for starting point
		}

		if (n < 0) {
			return 0;
		}

		// If the result is already computed, return it
		if (dp[n] != -1) {
			return dp[n];
		}

		int pick = nums[n] + houseRobberUsingMemo(nums, n - 2, dp);
		int notPick = houseRobberUsingMemo(nums, n - 1, dp);

		return dp[n] = Math.max(pick, notPick);
	}


	// Function to handle the variation where the first and last houses cannot be both robbed
	public static int getHouseRobberUsingMemo(int[] nums) {
		int n = nums.length;

		// Base cases
		if (n == 0) return 0;
		if (n == 1) return nums[0];
		if (n == 2) return Math.max(nums[0], nums[1]);

		// Create two scenarios: robbing from 0 to n-2 or from 1 to n-1
		int[] temp1 = Arrays.copyOfRange(nums, 0, n - 1);
		int[] temp2 = Arrays.copyOfRange(nums, 1, n);

		// Initialize dp arrays for each scenario
		int[] dp1 = new int[temp1.length];
		int[] dp2 = new int[temp2.length];

		Arrays.fill(dp1, -1);
		Arrays.fill(dp2, -1);

		// Calculate the maximum sum for each scenario
		int max1 = houseRobberUsingMemo(temp1, temp1.length - 1, dp1);
		int max2 = houseRobberUsingMemo(temp2, temp2.length - 1, dp2);

		return Math.max(max1, max2);
	}


	// Main function to initiate the process
	public static void main(String[] args) {
		int[] valueInHouse = { 2, 3, 2 };  // Example heights of stones
		int n = valueInHouse.length;  // Index of the last stone



		// Compute the minimum cost for the frog to reach the last stone
		System.out.println("Minimum energy spent: " + getHouseRobberUsingMemo(valueInHouse));
		System.out.println("Minimum energy spent: " + getHouseRobberUsingTabularOptimal(valueInHouse, n));
	}
}
