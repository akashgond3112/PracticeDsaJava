package main.dynamic.programming;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem statement
Given a ‘N’ * ’M’ maze with obstacles, count and return the number of unique paths to reach the right-bottom cell from the top-left cell. A cell in the given maze has a value '-1' if it is a blockage or dead-end, else 0. From a given cell, we are allowed to move to cells (i+1, j) and (i, j+1) only. Since the answer can be large, print it modulo 10^9 + 7.

For Example :
Consider the maze below :
0 0 0
0 -1 0
0 0 0

There are two ways to reach the bottom left corner -

(1, 1) -> (1, 2) -> (1, 3) -> (2, 3) -> (3, 3)
(1, 1) -> (2, 1) -> (3, 1) -> (3, 2) -> (3, 3)

Hence the answer for the above test case is 2.
Detailed explanation ( Input/output format, Notes, Images )
Constraints :
1 <= T <= 10
1 <= N,M <= 200


Note: It is guaranteed that the top-left cell does not have an obstacle.

Time Limit: 1 sec
Sample Input 1 :
2
2 2
0 0
0 0
3 3
0 0 0
0 -1 0
0 0 0
Sample Output 1 :
2
2
Explanation For Sample Output 1 :
For the first test case, there are two possible paths to reach (2, 2) from (1, 1) :
    (1, 1) -> (1, 2) -> (2, 2)
    (1, 1) -> (2, 1) -> (2, 2)

For the second test case, there are two ways to reach the bottom left corner -
(1, 1) -> (1, 2) -> (1, 3) -> (2, 3) -> (3, 3)
(1, 1) -> (2, 1) -> (3, 1) -> (3, 2) -> (3, 3)
Sample Input 2 :
1
2 2
0 -1
-1  0
Sample Output 2 :
0
*/
public class UniquePathsII {

	public static int uniquePathsRecursionUsingTabularSpaceOptimal(int m, int n, ArrayList<ArrayList<Integer>> mat) {
		int[] prev = new int[n]; // Array to store results from the previous row

		for (int i = 0; i < m; i++) {
			int[] cur = new int[n]; // Array to store results for the current row
			for (int j = 0; j < n; j++) {
				if ((i == 0 && j == 0) || mat.get(i).get(j) == -1) {
					cur[j] = 1; // Start point
				} else {
					int fromLeft = (j > 0) ? cur[j - 1] : 0; // Paths from the left
					int fromUp = prev[j]; // Paths from the top
					cur[j] = fromLeft + fromUp;
				}
			}
			prev = cur; // Update prev to the current row after processing
		}
		return prev[n - 1]; // Return the result at the bottom-right corner
	}


	public static int uniquePathsRecursionUsingTabular(int m, int n, int[][] dp, ArrayList<ArrayList<Integer>> mat) {
		dp[0][0] = 1; // Starting point

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if ((i == 0 && j == 0) || mat.get(i).get(j) == -1) {
					continue; // Skip the start point
				}
				int fromUp = 0;
				int fromLeft = 0;
				if (i > 0)
					fromUp = dp[i - 1][j]; // Check the cell above
				if (j > 0)
					fromLeft = dp[i][j - 1]; // Check the cell to the left
				dp[i][j] = fromUp + fromLeft; // Sum of paths from top and left
			}
		}
		return dp[m - 1][n - 1]; // Return the result at the bottom-right corner
	}

	public static int uniquePathsRecursionUsingMemo(int m, int n, int[][] dp, ArrayList<ArrayList<Integer>> mat) {
		// Write your code here.
		if (m >= 0 && n >= 0 && mat.get(m).get(n) == -1) {
			return 0;
		}
		if (m == 0 && n == 0)
			return 1;
		if (m < 0 || n < 0)
			return 0;


		if (dp[m][n] != -1)
			return dp[m][n];

		int up = uniquePathsRecursionUsingMemo(m - 1, n, dp, mat);
		int down = uniquePathsRecursionUsingMemo(m, n - 1, dp, mat);
		dp[m][n] = up + down;
		return dp[m][n];
	}

	public static int uniquePathsRecursion(int m, int n, ArrayList<ArrayList<Integer>> mat) {
		// Write your code here.
		if (m >= 0 && n >= 0 && mat.get(m).get(n) == -1) {
			return 0;
		}
		if (m == 0 && n == 0)
			return 1;
		if (m < 0 || n < 0)
			return 0;

		int up = uniquePathsRecursion(m - 1, n, mat);
		int down = uniquePathsRecursion(m, n - 1, mat);
		return up + down;
	}

	public static int getUniquePaths(int m, int n, ArrayList<ArrayList<Integer>> mat) {
		return uniquePathsRecursion(m - 1, n - 1, mat);
	}

	public static int getUniquePathsMemo(int m, int n, ArrayList<ArrayList<Integer>> mat) {

		int[][] dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			Arrays.fill(dp[i], -1);
		}

		return uniquePathsRecursionUsingMemo(m - 1, n - 1, dp, mat);
	}

	public static int getUniquePathsTabular(int m, int n, ArrayList<ArrayList<Integer>> mat) {

		int[][] dp = new int[m][n];

		return uniquePathsRecursionUsingTabular(m, n, dp, mat);
	}


	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> mat = new ArrayList<>();
		mat.add(new ArrayList<>(Arrays.asList(0, 0, 0)));
		mat.add(new ArrayList<>(Arrays.asList(0, -1, 0)));
		mat.add(new ArrayList<>(Arrays.asList(0, 0, 0)));

		System.out.println(getUniquePaths(3, 3, mat));
		System.out.println(getUniquePathsMemo(3, 3, mat));
		System.out.println(getUniquePathsTabular(3, 3, mat));
		System.out.println(uniquePathsRecursionUsingTabularSpaceOptimal(3, 2, mat));
	}
}
