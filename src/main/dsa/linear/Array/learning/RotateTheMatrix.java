package main.dsa.linear.Array.learning;

/*
Problem statement
You are given a square matrix ‘Mat’ of size ‘N’. You need to rotate ‘Mat’ by 90 degrees in the clockwise direction.

Note:

You must rotate the matrix in place, i.e., you must modify the given matrix itself. You must not allocate another square matrix for rotation.
For example

When,
‘N’ = 2 and ‘Mat’ = {{1, 2}, {3, 4}}, we must modify ‘Mat’ to {{3, 1}, {4, 2}}.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= N <= 100
1 <= Mat[i][j] <= 10^9

Time Limit: 1 sec
*/
public class RotateTheMatrix {

	public static void rotateMatrix(int[][] mat) {
		// Write your code here.

		// Step : Transpose the matrix
		int n = mat.length;
		int m = mat[0].length;

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < m; j++) {
				int temp = mat[i][j];
				mat[i][j] = mat[j][i];
				mat[j][i] = temp;
			}
		}

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length / 2; j++) {
				int temp = 0;
				temp = mat[i][j];
				mat[i][j] = mat[i][mat.length - 1 - j];
				mat[i][mat.length - 1 - j] = temp;
			}
		}
	}

	public static void main(String[] args) {
		int[][] arr =  {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		rotateMatrix(arr);
		System.out.println("Rotated Image");
		for (int[] ints : arr) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print(ints[j] + " ");
			}
			System.out.println();
		}

	}
}
