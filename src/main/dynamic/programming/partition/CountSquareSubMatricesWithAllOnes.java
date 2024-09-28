package main.dynamic.programming.partition;

/*
Problem statement
A matrix 'arr' with 'n' rows and 'm' columns is given.



Count the number of square submatrices in matrix ‘arr’ with all elements equal to 1.



A square matrix is a matrix with square dimensions.



Example :
Input: If 'n' = 2, 'm' = 2, and 'arr' = [ [1, 1], [1, 1] ].

Output: 5

Explanation: We have 4 square submatrices of size 1*1 and 1 square submatrix of size 2*2.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
2 2
1 0
0 1
Sample Output 1 :
2
Explanation of sample input 1:
There are two square submatrices of size 1*1, so the answer is 2.
Sample Input 2 :
3 4
0 1 1 0
1 1 1 0
0 0 1 0
Sample Output 2 :
7
Explanation of sample input 2:
There are six square submatrices of size 1*1, and there is one square submatrix of size 2*2. So, the answer is 6 + 1 = 7.
Expected time complexity:
The expected time complexity is O(n*m), where 'n' and 'm' are the dimensions of the matrix.
Constraints :
1 ≤ 'n', 'm' ≤ 1000
0 ≤ 'arr'[i][j] ≤ 1

Time limit: 1 sec
*/
public class CountSquareSubMatricesWithAllOnes {

	public static int countSquares(int[][] arr, int n, int m) {
		int[][] dp = new int[n][m];

		// Initialize the first row and first column
		for (int i = 0; i < m; i++) {
			dp[0][i] = arr[0][i];
		}
		for (int i = 0; i < n; i++) {
			dp[i][0] = arr[i][0];
		}

		// Fill the dp array based on square submatrix conditions
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				if (arr[i][j] == 1) {
					dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
				} else {
					dp[i][j] = 0;
				}
			}
		}

		// Sum up all square submatrices from dp table
		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sum += dp[i][j];
			}
		}

		return sum;
	}

	public static void main(String[] args) {
		int[][] arr = { { 0, 1, 1, 0 }, { 1, 1, 1, 0 }, { 0, 0, 1, 0 } };
		System.out.println("Number of square submatrices: " + countSquares(arr, arr.length, arr[0].length));
	}
}
