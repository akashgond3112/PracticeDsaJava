package main.dynamic.programming.partition;

import java.util.Arrays;

/*
Problem statement
Given a chain of matrices A1, A2, A3,.....An. Your task is to find out the minimum costUsing to multiply these matrices. The costUsing of matrix multiplication is defined as the number of scalar multiplications. A Chain of matrices A1, A2, A3,.....An is represented by a sequence of numbers in an array ‘arr’ where the dimension of 1st matrix is equal to arr[0] * arr[1] , 2nd matrix is arr[1] * arr[2], and so on.

For example:
For arr[ ] = { 10, 20, 30, 40}, matrix A1 = [10 * 20], A2 = [20 * 30], A3 = [30 * 40]

Scalar multiplication of matrix with dimension 10 * 20 is equal to 200.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
2
4
4 5 3 2
4
10 15 20 25
Sample Output 1:
70
8000
Sample Output Explanation 1:
In the first test case, there are three matrices of dimensions A = [4 5], B = [5 3] and C = [3 2]. The most efficient order of multiplication is A * ( B * C).
Cost of ( B * C ) = 5 * 3 * 2 = 30  and (B * C) = [5 2] and A * (B * C) = [ 4 5] * [5 2] = 4 * 5 * 2 = 40. So the overall costUsing is equal to 30 + 40 =70.

In the second test case, there are two ways to multiply the chain - A1*(A2*A3) or (A1*A2)*A3.

If we multiply in order- A1*(A2*A3), then the number of multiplications required is 11250.

If we multiply in order- (A1*A2)*A3, then the number of multiplications required is 8000.

Thus a minimum number of multiplications required is 8000.
Sample Input 2:
1
4
1 4 3 2
Sample Output 2:
18
Explanation of Sample Output 2:
In the first test case, there are three matrices of dimensions A = [1 4], B = [4 3] and C = [3 2]. The most efficient order of multiplication is (A *  B) * C .
*/
public class MatrixChainMultiplication {

	public static int matrixMultiplicationUsingTabular(int[] arr, int n) {
		// Write your code here

		int[][] dp = new int[n][n];
		for (int index = 1; index < n; index++) {
			dp[index][index] = 0;
		}

		for (int i1 = n - 1; i1 >= 1; i1--) {
			for (int j1 = i1 + 1; j1 < n; j1++) {
				int min = (int) 1e9;
				for (int k = i1; k < j1; k++) {
					int steps = arr[i1 - 1] * arr[k] * arr[j1] + dp[i1][k] + dp[k + 1][j1];
					min = Math.min(min, steps);
				}
				dp[i1][j1] = min;
			}
		}

		return dp[1][n - 1];
	}

	public static int matrixMultiplicationUsingMemo(int[] arr, int i, int j, int[][] dp) {
		// Write your code here

		if (i == j)
			return 0;

		if (dp[i][j] != -1)
			return dp[i][j];

		int min = (int) 1e9;

		for (int k = i; k < j; k++) {
			int steps = arr[i - 1] * arr[k] * arr[j] + matrixMultiplication(arr, i, k) + matrixMultiplication(arr,
					k + 1, j);
			min = Math.min(min, steps);
		}
		return dp[i][j] = min;
	}


	public static int matrixMultiplication(int[] arr, int i, int j) {
		// Write your code here

		if (i == j)
			return 0;

		int min = (int) 1e9;

		for (int k = i; k < j; k++) {
			int steps = arr[i - 1] * arr[k] * arr[j] + matrixMultiplication(arr, i, k) + matrixMultiplication(arr,
					k + 1, j);
			min = Math.min(min, steps);
		}
		return min;
	}

	public static void main(String[] args) {
		int[] arr = { 4, 5, 3, 2 };
		int n = arr.length;
		System.out.println(matrixMultiplication(arr, 1, arr.length - 1));

		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(dp[i], -1);
		}

		System.out.println(matrixMultiplicationUsingMemo(arr, 1, n - 1, dp));
		System.out.println(matrixMultiplicationUsingTabular(arr, n));
	}
}
