package main.dynamic.programming.grid;

/*
Problem statement
Ninja is planing this ‘N’ days-long training schedule. Each day, he can perform any one of these three activities. (Running, Fighting Practice or Learning New Moves). Each activity has some merit points on each day. As Ninja has to improve all his skills, he can’t do the same activity in two consecutive days. Can you help Ninja find out the maximum merit points Ninja can earn?

You are given a 2D array of size N*3 ‘POINTS’ with the points corresponding to each day and activity. Your task is to calculate the maximum number of merit points that Ninja can earn.

For Example
If the given ‘POINTS’ array is [[1,2,5], [3 ,1 ,1] ,[3,3,3] ],the answer will be 11 as 5 + 3 + 3.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 10
1 <= N <= 100000.
1 <= values of POINTS arrays <= 100 .

Time limit: 1 sec
Sample Input 1:
2
3
1 2 5
3 1 1
3 3 3
3
10 40 70
20 50 80
30 60 90
Sample Output 1:
11
210
Explanation of sample input 1:
For the first test case,
One of the answers can be:
On the first day, Ninja will learn new moves and earn 5 merit points.
On the second day, Ninja will do running and earn 3 merit points.
On the third day, Ninja will do fighting and earn 3 merit points.
The total merit point is 11 which is the maximum.
Hence, the answer is 11.

For the second test case:
One of the answers can be:
On the first day, Ninja will learn new moves and earn 70 merit points.
On the second day, Ninja will do fighting and earn 50 merit points.
On the third day, Ninja will learn new moves and earn 90 merit points.
The total merit point is 210 which is the maximum.
Hence, the answer is 210.
Sample Input 2:
2
3
18 11 19
4 13 7
1 8 13
2
10 50 1
5 100 11
Sample Output 2:
45
*/

import java.util.Arrays;

public class NinjasTraining {

	// Function to find the maximum points for ninja training
	static int ninjaTrainingUsingTabularFormOptimised(int n, int[][] points) {
		// Initialize an array 'prev' to store the maximum points for the previous day
		int[] prev = new int[4];

		// Initialize the first day's maximum points based on the available choices
		prev[0] = Math.max(points[0][1], points[0][2]);
		prev[1] = Math.max(points[0][0], points[0][2]);
		prev[2] = Math.max(points[0][0], points[0][1]);
		prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

		// Iterate through each day starting from the second day
		for (int day = 1; day < n; day++) {
			// Initialize an array 'temp' to store the maximum points for the current day
			int[] temp = new int[4];
			for (int last = 0; last < 4; last++) {
				temp[last] = 0; // Initialize the maximum points for the current day and last activity
				// Consider each possible task for the current day
				for (int task = 0; task <= 2; task++) {
					if (task != last) { // Ensure that the current task is different from the last
						// Calculate the points for the current activity and add it to the maximum points from the previous day
						temp[last] = Math.max(temp[last], points[day][task] + prev[task]);
					}
				}
			}
			// Update 'prev' to store the maximum points for the current day
			prev = temp;
		}

		// Return the maximum points achievable after all days (last activity is 3)
		return prev[3];
	}


	// Function to find the maximum points for ninja training
	static int ninjaTrainingUsingTabularForm(int n, int[][] points) {
		// Initialize a 2D array 'dp' to store the maximum points
		int[][] dp = new int[n][4];

		// Initialize the first day's maximum points based on the available choices
		dp[0][0] = Math.max(points[0][1], points[0][2]);
		dp[0][1] = Math.max(points[0][0], points[0][2]);
		dp[0][2] = Math.max(points[0][0], points[0][1]);
		dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

		// Iterate through each day and each activity
		for (int day = 1; day < n; day++) {
			for (int last = 0; last < 4; last++) {
				dp[day][last] = 0; // Initialize the maximum points for the current day and last activity
				// Consider each possible task for the current day
				for (int task = 0; task <= 2; task++) {
					if (task != last) { // Ensure that the current task is different from the last
						// Calculate the points for the current activity and add it to the maximum points from the previous day
						int activity = points[day][task] + dp[day - 1][task];
						// Update the maximum points for the current day and last activity
						dp[day][last] = Math.max(dp[day][last], activity);
					}
				}
			}
		}

		// Return the maximum points achievable after all days (last activity is 3)
		return dp[n - 1][3];
	}


	public static int ninjaTrainingUsingMemo(int day, int last, int[][] points, int[][] dp) {

		if (dp[day][last] != -1)
			return dp[day][last];

		// Write your code here..
		if (day == 0) {
			int max = 0;

			for (int i = 0; i < 3; i++) {
				if (i != last)
					max = Math.max(max, points[0][i]);
			}
			return max;
		}

		int max = 0;
		for (int i = 0; i < 3; i++) {
			if (i != last) {
				int point = points[day][i] + ninjaTrainingUsingMemo(day - 1, i, points, dp);
				max = Math.max(max, point);
			}
		}
		return dp[day][last] = max;
	}

	public static int ninjaTrainingUsingRecursion(int day, int last, int points[][]) {

		// Write your code here..
		if (day == 0) {
			int max = 0;

			for (int i = 0; i < 3; i++) {
				if (i != last)
					max = Math.max(max, points[0][i]);
			}
			return max;
		}

		int max = 0;
		for (int i = 0; i < 3; i++) {
			if (i != last) {
				int point = points[day][i] + ninjaTrainingUsingRecursion(day - 1, i, points);
				max = Math.max(max, point);
			}
		}
		return max;
	}

	public static int getNinjaTrainingUsingRecursion(int n, int points[][]) {

		// Write your code here..
		return ninjaTrainingUsingRecursion(n - 1, 3, points);
	}

	public static int getNinjaTrainingUsingMemo(int n, int points[][]) {

		// Write your code here..
		int[][] dp = new int[n][4];
		for (int[] row : dp)
			Arrays.fill(row, -1);

		return ninjaTrainingUsingMemo(n - 1, 3, points, dp);
	}

	public static void main(String[] args) {

		int[][] points = new int[][] { { 1, 2, 5 }, { 3, 1, 1 }, { 3, 3, 3 } };

		System.out.println(getNinjaTrainingUsingRecursion(points.length, points));
		System.out.println(getNinjaTrainingUsingMemo(points.length, points));
		System.out.println(ninjaTrainingUsingTabularForm(points.length, points));
		System.out.println(ninjaTrainingUsingTabularFormOptimised(points.length, points));
	}
}
