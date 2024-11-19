package main.dsa.dp.partition;

import java.util.Stack;

/*
Problem statement
You are given an 'N' * 'M' sized binary-valued matrix 'MAT, where 'N' is the number of rows and 'M' is the number of columns. You need to return the maximum size (area) of the submatrix which consists of all 1’s i.e. the maximum area of a submatrix in which each cell has only the value ‘1’.


In the above image, areas in green, red, and violet color are all submatrices of the original 4x4 matrix.
Note:

1. Binary valued matrix has only two values in each cell : 0 and 1.
2. A submatrix is a matrix formed by selecting certain rows and columns from a larger matrix.
3. The area of a matrix with 'h' rows and 'w' columns is equal to 'h' * 'w'.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= 'T' <= 50
1 <= 'N', 'M' <= 100

Time Limit: 1 sec
Sample Input 1:
2
2 2
1 1
1 1
5 4
1 0 1 1
1 0 1 1
0 1 0 1
1 1 1 1
0 0 0 1
Sample Output 1:
4
5
Explanation For Sample Input 1:
For First Test Case: It is easy to see that whole matrix of size 2 * 2 contains '1' only hence the required area will be 4.

For Second Test Case:

Sample Input 2:
2
2 2
1 0
0 1
4 4
1 1 1 1
1 1 1 1
0 0 1 1
0 0 1 1
Sample Output 2:
1
8
*/
public class MaximumSizeRectangleSubMatrixWithAllOnes {

	static int largestRectangleArea(int histo[]) {
		Stack<Integer> st = new Stack<>();
		int maxA = 0;
		int n = histo.length;
		for (int i = 0; i <= n; i++) {
			while (!st.empty() && (i == n || histo[st.peek()] >= histo[i])) {
				int height = histo[st.peek()];
				st.pop();
				int width;
				if (st.empty())
					width = i;
				else
					width = i - st.peek() - 1;
				maxA = Math.max(maxA, width * height);
			}
			st.push(i);
		}
		return maxA;
	}

	public static int maximalAreaOfSubMatrixOfAll1(int[][] mat, int n, int m) {
		// Write your code here.
		int max = 0;
		int[] heights = new int[m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (mat[i][j] == 1) {
					heights[j]++;
				} else
					heights[j] = 0;
			}
			int area = largestRectangleArea(heights);
			max = Math.max(area, max);
		}
		return max;
	}

	public static void main(String[] args) {

		int[][] arr = new int[][] { { 1, 0, 1, 1 }, { 1, 0, 1, 1 }, { 0, 1, 0, 1 }, { 1, 1, 1, 1 }, { 0, 0, 0, 1 }, };
		System.out.println(maximalAreaOfSubMatrixOfAll1(arr, arr.length, arr[0].length));
	}
}
