package main.dsa.linear.Array.learning.hard;

import java.util.Arrays;

/*
Problem statement
You are given an integer ‘N’. You need to return the first ‘N’ rows of Pascal’s triangle.

Example:

Input:
N = 4
Output:
1
1 1
1 2 1
1 3 3 1
Explanation: The output matrix has the first four rows of Pascal’s Triangle.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= N <= 30
Time Limit: 1 sec
Sample Input 1:
5
Sample Output 1:
1
1 1
1 2 1
1 3 3 1
1 4 6 4 1
Explanation Of Sample Input 1:
Input:
N = 5

Output:
1
1 1
1 2 1
1 3 3 1
1 4 6 4 1
Explanation: The output matrix has the first five rows of Pascal’s Triangle.
Sample Input 2:
3
Sample Output 2:
1
1 1
1 2 1
Sample Input 3:
4
Sample Output 3:
1
1 1
1 2 1
1 3 3 1
*/
import java.util.Arrays;

public class PrintPascalsTriangle {

	// Function to calculate nCr (combination formula)
	static int ncr(int n, int r) {
		int res = 1;
		for (int i = 0; i < r; i++) {
			res = res * (n - i);
			res = res / (i + 1);
		}
		return res;
	}

	// Function to get a specific value from Pascal's Triangle
	public static int pascalTriangle(int n, int m) {
		// nCr where the row index starts from 1 and column index starts from 1
		return ncr(n - 1, m - 1);
	}

	// Function to print a given row of Pascal's Triangle
	public static int[] printGivenRowOfPascalTriangle(int row) {
		// The row will have exactly 'row' elements
		int[] ans = new int[row];
		ans[0] = 1;
		int res = 1;

		// Compute each element in the row
		for (int i = 1; i < row; i++) {
			res = res * (row - i);
			res = res / i;
			ans[i] = res;
		}
		return ans;
	}

	// Function to print Pascal's Triangle up to N rows
	public static int[][] pascalTriangle(int N) {
		// Initialize result array with N rows and N columns (each row can have different size)
		int[][] res = new int[N][];

		for (int i = 1; i <= N; i++) {
			res[i - 1] = printGivenRowOfPascalTriangle(i);
		}

		return res;
	}

	public static void main(String[] args) {
		// Get the element at row 5, column 3 (1-based indexing)
		System.out.println("Element at row 5, column 3: " + pascalTriangle(5, 3));

		// Print the entire 5th row of Pascal's Triangle
		System.out.println("5th row of Pascal's Triangle: " + Arrays.toString(printGivenRowOfPascalTriangle(5)));

		// Print Pascal's Triangle up to 5 rows
		System.out.println("Pascal's Triangle up to 5 rows:");
		int[][] triangle = pascalTriangle(5);
		for (int[] row : triangle) {
			System.out.println(Arrays.toString(row));
		}
	}
}

