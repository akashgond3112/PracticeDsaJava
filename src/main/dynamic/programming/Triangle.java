package main.dynamic.programming;

import java.util.Arrays;

/*
Problem statement
You are given a triangular array/list 'TRIANGLE'. Your task is to return the minimum path sum to reach from the top to the bottom row.

The triangle array will have N rows and the i-th row, where 0 <= i < N will have i + 1 elements.

You can move only to the adjacent number of row below each step. For example, if you are at index j in row i, then you can move to i or i + 1 index in row j + 1 in each step.

For Example :
If the array given is 'TRIANGLE' = [[1], [2,3], [3,6,7], [8,9,6,1]] the triangle array will look like:

1
2,3
3,6,7
8,9,6,10

For the given triangle array the minimum sum path would be 1->2->3->8. Hence the answer would be 14.
Detailed explanation ( Input/output format, Notes, Images )
Constraints :
1 <= T <= 5
1 <= N <= 10^3
-10^6 <= TRIANGLE[i][pos] <= 10^6 ,

Where 'TRIANGLE[i][pos]' is the element at row = 'i' & position = 'pos' in triangle array.

Time limit: 1 sec
Sample Input 1 :
2
4
2
3 4
6 5 7
4 1 8 3
1
-10
Sample output 1 :
11
-10
Sample Input explanation:
Test case 1:
Here our triangle array is:

2
3 4
6 5 7
4 1 8 3

In this array, the minimum path will be 2->3->5->1, so the minimum sum path would be 2+3+5+1=11

Test case 2:
In this case, there is one row. Thus, the minimum path will be -10, and the minimum sum path=-10.
Sample input 2 :
2
4
1
2 3
4 5 6
7 8 9 10
3
5
-1 3
22 1 -9
Sample Output 2 :
14
-1
*/
public class Triangle {

	public static int minimumPathSumUsingTabularSpaceOptimal(int[][] triangle, int n) {

		int[] front = new int[n];

		// Initialize the base case bottom row of dp with the values from the triangle's bottom row
		System.arraycopy(triangle[n - 1], 0, front, 0, n);

		// Fill the dp array from the second-last row upwards
		for (int i = n - 2; i >= 0; i--) {
			int[] current = new int[n];
			for (int j = 0; j <= i; j++) {
				int fromDown = front[j];     // Value from directly below
				int fromDiagonal = front[j + 1]; // Value from the diagonal

				// Add the current triangle value to the minimum of the two possible paths
				current[j] = triangle[i][j] + Math.min(fromDown, fromDiagonal);

			}
			front = current;
		}

		// The minimum path sum will be stored at the front
		return front[0];
	}

	public static int minimumPathSumUsingTabular(int[][] triangle, int n) {

		int[][] dp = new int[n][n];

		// Initialize the base case bottom row of dp with the values from the triangle's bottom row
		/*
			for (int j = 0; j < n; j++) {
				dp[n - 1][j] = triangle[n - 1][j];
			}
		*/
		System.arraycopy(triangle[n - 1], 0, dp[n - 1], 0, n);

		// Fill the dp array from the second-last row upwards
		for (int i = n - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				int fromDown = dp[i + 1][j];     // Value from directly below
				int fromDiagonal = dp[i + 1][j + 1]; // Value from the diagonal

				// Add the current triangle value to the minimum of the two possible paths
				dp[i][j] = triangle[i][j] + Math.min(fromDown, fromDiagonal);
			}
		}

		// The minimum path sum will be stored at the top of the triangle (dp[0][0])
		return dp[0][0];
	}

	public static int minimumPathSumUsingMemo(int i, int j, int[][] triangle, int n) {

		int[][] dp = new int[n][n];
		for (int k = 0; k < n; k++) {
			Arrays.fill(dp[k], -1);
		}

		// Write your code here.
		if (i == n - 1) {
			return triangle[n - 1][j];
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		// Recursive cases: moving to down or to the diagonal right
		int fromUp = triangle[i][j] + minimumPathSumUsingMemo(i + 1, j, triangle, n);
		int fromLeft = triangle[i][j] + minimumPathSumUsingMemo(i + 1, j + 1, triangle, n);

		// Return the minimum path sum
		dp[i][j] = Math.min(fromLeft, fromUp);

		return dp[i][j];
	}


	public static int minimumPathSumUsingRecursion(int i, int j, int[][] triangle, int n) {
		// Write your code here.
		if (i == n - 1) {
			return triangle[n - 1][j];
		}

		// Recursive cases: moving to down or to the diagonal right
		int fromUp = triangle[i][j] + minimumPathSumUsingRecursion(i + 1, j, triangle, n);
		int fromLeft = triangle[i][j] + minimumPathSumUsingRecursion(i + 1, j + 1, triangle, n);

		// Return the minimum path sum
		return Math.min(fromLeft, fromUp);
	}

	public static void main(String[] args) {
		int[][] triangle = new int[][] { { 2 }, { 3, 4 }, { 6, 5, 7 }, { 4, 1, 8, 3 } };
		int n = triangle.length;
		System.out.println(minimumPathSumUsingRecursion(0, 0, triangle, n));
		System.out.println(minimumPathSumUsingMemo(0, 0, triangle, n));
		System.out.println(minimumPathSumUsingTabular(triangle, n));
		System.out.println(minimumPathSumUsingTabularSpaceOptimal(triangle, n));
	}
}
