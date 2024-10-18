package main.dsa.linear.stack;

import java.util.Stack;

/**
 * 85. Maximal Rectangle Hard Topics Companies Given a rows x cols binary matrix filled with 0's and 1's, find the
 * largest rectangle containing only 1's and return its area.
 *
 * Example 1:
 *
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]] Output: 6
 * Explanation: The maximal rectangle is shown in the above picture. Example 2:
 *
 * Input: matrix = [["0"]] Output: 0 Example 3:
 *
 * Input: matrix = [["1"]] Output: 1
 *
 *
 * Constraints:
 *
 * rows == matrix.length cols == matrix[i].length 1 <= row, cols <= 200 matrix[i][j] is '0' or '1'.
 */
public class MaximalRectangle {

	public int maximalRectangle(char[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}

		int n = matrix.length;
		int m = matrix[0].length;
		int[][] dp = new int[n][m];

		// Initialize dp with heights of histograms
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (matrix[i][j] == '1') {
					dp[i][j] = (i == 0) ? 1 : dp[i - 1][j] + 1; // accumulate height
				}
			}
		}

		int max = 0;
		// For each row in dp, treat it as the base of a histogram and calculate the maximal area
		for (int i = 0; i < n; i++) {
			max = Math.max(max, largestRectangleAreaOptimal(dp[i]));
		}

		return max;
	}

	// Assuming this is your already implemented function
	public int largestRectangleAreaOptimal(int[] heights) {
		int n = heights.length;
		Stack<Integer> stack = new Stack<>();
		int maxArea = 0;
		for (int i = 0; i <= n; i++) {
			int h = (i == n) ? 0 : heights[i];
			while (!stack.isEmpty() && h < heights[stack.peek()]) {
				int height = heights[stack.pop()];
				int width = stack.isEmpty() ? i : i - stack.peek() - 1;
				maxArea = Math.max(maxArea, height * width);
			}
			stack.push(i);
		}
		return maxArea;
	}

	public static void main(String[] args) {
		MaximalRectangle obj = new MaximalRectangle();
		char[][] matrix = {
				{'1', '0', '1', '0', '0'},
				{'1', '0', '1', '1', '1'},
				{'1', '1', '1', '1', '1'},
				{'1', '0', '0', '1', '0'}
		};
		System.out.println("Maximal Rectangle Area: " + obj.maximalRectangle(matrix));
	}
}
