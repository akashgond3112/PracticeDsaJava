package main.meta.easy;

/**
 * <pre>
 *  766. Toeplitz Matrix
 * Easy
 * Topics
 * Companies
 * Hint
 * Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.
 *
 * Example 1:
 * Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 * Output: true
 * Explanation:
 * In the above grid, the diagonals are:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
 * In each diagonal all elements are the same, so the answer is True.
 *
 * Example 2:
 * Input: matrix = [[1,2],[2,2]]
 * Output: false
 * Explanation:
 * The diagonal "[1, 2]" has different elements.
 *
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 20
 * 0 <= matrix[i][j] <= 99
 *
 * Follow up:
 * What if the matrix is stored on disk, and the memory is limited such that you can only load at most one row of the matrix into the memory at once?
 * What if the matrix is so large that you can only load up a partial row into the memory at once?
 * Topics
 * Array
 * Matrix
 * </pre>
 */
public class ToeplitzMatrix {

	/**
	 * Solution for determining if a matrix is a Toeplitz matrix.
	 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.
	 */
	static class Solution {
		/**
		 * Checks if the input matrix is a Toeplitz matrix.
		 *
		 * A matrix is Toeplitz if elements on any diagonal from top-left to bottom-right
		 * have the same value.
		 *
		 * @param matrix The input matrix to check
		 * @return true if the matrix is a Toeplitz matrix, false otherwise
		 *
		 * Time Complexity: O(m*n) where m is the number of rows and n is the number of columns
		 * Space Complexity: O(1) as we use constant extra space
		 */
		public boolean isToeplitzMatrix(int[][] matrix) {
			int rows = matrix.length;
			int cols = matrix[0].length;

			// Check all diagonals starting from first column
			for (int i = 0; i < rows; i++) {
				if (isDiagonalValid(matrix, i, 0)) {
					return false;
				}
			}

			// Check all diagonals starting from first row (except the first element)
			for (int j = 1; j < cols; j++) {
				if (isDiagonalValid(matrix, 0, j)) {
					return false;
				}
			}

			return true;
		}

		/**
		 * Checks if all elements in a diagonal starting at (row, col) have the same value.
		 *
		 * @param matrix The input matrix
		 * @param row The starting row index of the diagonal
		 * @param col The starting column index of the diagonal
		 * @return true if all elements in the diagonal are the same, false otherwise
		 */
		private boolean isDiagonalValid(int[][] matrix, int row, int col) {
			int rows = matrix.length;
			int cols = matrix[0].length;
			int val = matrix[row][col];

			while (row < rows && col < cols) {
				if (matrix[row][col] != val) {
					return true;
				}
				row++;
				col++;
			}
			return false;
		}
	}
}
