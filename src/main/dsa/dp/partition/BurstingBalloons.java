package main.dsa.dp.partition;

import java.util.Arrays;

/*
Problem statement
You are given an array 'ARR' of N integers. Each integer represents the height of a balloon. So, there are N balloons lined up.

Your aim is to destroy all these balloons. Now, a balloon can only be destroyed if the player shoots its head. So, to do the needful, he/ she shoots an arrow from the left to the right side of the platform, from an arbitrary height he/she chooses. The arrow moves from left to right, at a chosen height ARR[i] until it finds a balloon. The moment when an arrow touches a balloon, the balloon gets destroyed and disappears and the arrow continues its way from left to right at a height decreased by 1. Therefore, if the arrow was moving at height ARR[i], after destroying the balloon it travels at height ARR[i]-1. The player wins this game if he destroys all the balloons in minimum arrows.

You have to return the minimum arrows required to complete the task.

Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= N <= 10^5
1 <= ARR[i] <= 10^9

Time Limit: 1sec
Sample Input 1:
5
2 1 5 4 3
Sample Output 1:
2
Explanation of the Sample Input1:
We need to shoot the arrow at height 5 - which destroys balloons at the height [5, 4, 3], and shoots an arrow at height 2 - which destroys [2, 1].  Therefore we require a minimum of 2 arrows.
Sample Input 2:
3
3 2 1
Sample Output 2:
1
Explanation of the Sample Input 2:
We need to shoot the arrow at height 3 - which destroys balloons at the height [3,2,1]. Therefore we need to shoot only 1 arrow.
*/
public class BurstingBalloons {

	public static int burstingBalloonsTabular(int[] arr, int n) {
		// Base case: if no balloons left
		int[][] dp = new int[n + 2][n + 2];


		for (int i = n; i >= 1; i--) {
			for (int j = 1; j <= n; j++) {

				if (i > j)
					continue;

				// Initialize max to store the maximum coins obtained
				int max = Integer.MIN_VALUE;

				for (int k = i; k <= j; k++) {
					// Cost of bursting balloon `k`
					int cost = (arr[i - 1] * arr[k] * arr[j + 1]) + dp[i][k - 1] // Burst left side
							+ dp[k + 1][j]; // Burst right side

					max = Math.max(max, cost); // Update the max value
				}
				dp[i][j] = max;

			}
		}

		return dp[1][n];
	}

	public static int burstingBalloonsMemo(int[] arr, int i, int j, int[][] dp) {
		// Base case: if no balloons left
		if (i > j)
			return 0;

		if (dp[i][j] != -1)
			return dp[i][j];

		// Initialize max to store the maximum coins obtained
		int max = Integer.MIN_VALUE;

		for (int k = i; k <= j; k++) {
			// Cost of bursting balloon `k`
			int cost = (arr[i - 1] * arr[k] * arr[j + 1]) + burstingBalloons(arr, i, k - 1) // Burst left side
					+ burstingBalloons(arr, k + 1, j); // Burst right side

			max = Math.max(max, cost); // Update the max value
		}

		return dp[i][j] = max;
	}

	public static int burstingBalloons(int[] arr, int i, int j) {
		// Base case: if no balloons left
		if (i > j)
			return 0;

		// Initialize max to store the maximum coins obtained
		int max = Integer.MIN_VALUE;

		for (int k = i; k <= j; k++) {
			// Cost of bursting balloon `k`
			int cost = (arr[i - 1] * arr[k] * arr[j + 1]) + burstingBalloons(arr, i, k - 1) // Burst left side
					+ burstingBalloons(arr, k + 1, j); // Burst right side

			max = Math.max(max, cost); // Update the max value
		}

		return max;
	}

	public static void main(String[] args) {
		// Input balloons array (with virtual balloons 1 at boundaries)
		int[] balloons = { 2, 1, 5, 4, 3 };
		int n = balloons.length;

		// Modify the array to include boundaries
		int[] arr = new int[n + 2];
		arr[0] = arr[n + 1] = 1;
		System.arraycopy(balloons, 0, arr, 1, n);

		// Call the burstingBalloons function
		System.out.println("Maximum coins obtained: " + burstingBalloons(arr, 1, n));

		int[][] dp = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(dp[i], -1);
		}

		System.out.println("Maximum coins obtained: " + burstingBalloonsMemo(arr, 1, n, dp));
		System.out.println("Maximum coins obtained: " + burstingBalloonsTabular(arr, n));
	}
}
