package main.meta.medium;

/**
 * 498. Diagonal Traverse
 * Medium
 * Topics
 * Companies
 * Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,4,7,5,3,6,8,9]
 * Example 2:
 *
 * Input: mat = [[1,2],[3,4]]
 * Output: [1,2,3,4]
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * -105 <= mat[i][j] <= 105*/
public class DiagonalTraverse {

	/**
	 * Approach: Zigzag Matrix Traversal
	 *
	 * This solution implements a diagonal traversal of a matrix in a zigzag pattern.
	 * The traversal alternates between moving in the up-right direction and down-left direction.
	 *
	 * Algorithm:
	 * 1. Start from the top-left corner (0,0)
	 * 2. Move in alternating directions (up-right and down-left)
	 * 3. When hitting a boundary, change direction according to specific rules:
	 *    - When moving up-right and hit right boundary: move down, change direction
	 *    - When moving up-right and hit top boundary: move right, change direction
	 *    - When moving down-left and hit bottom boundary: move right, change direction
	 *    - When moving down-left and hit left boundary: move down, change direction
	 * 4. Continue until all matrix elements are processed
	 *
	 * Time Complexity: O(m*n) where m is the number of rows and n is the number of columns
	 * - We visit each cell exactly once
	 * - Each cell operation takes O(1) time
	 *
	 * Space Complexity: O(m*n)
	 * - We use a result array of size m*n to store the traversal
	 * - Other than that, we only use a constant amount of extra space
	 *
	 * Example visualization for a 3x3 matrix:
	 * [1, 2, 3]    Traversal order: 1→2→4→7→5→3→6→8→9
	 * [4, 5, 6]    Following the pattern: up-right, down-left, up-right...
	 * [7, 8, 9]
	 */
	public int[] findDiagonalOrder(int[][] mat) {
		// Handle empty matrix case
		if (mat == null || mat.length == 0) return new int[0];

		// Get dimensions of matrix
		int m = mat.length;    // number of rows
		int n = mat[0].length; // number of columns

		// Create result array to store our diagonal walk
		int[] result = new int[m * n];
		int row = 0, col = 0;  // Start from top-left corner
		int dir = 1;           // 1 means going up-right, -1 means going down-left
		int index = 0;         // Index for result array

		// Continue until we've filled the result array
		while (index < m * n) {
			// Add current element to result
			result[index] = mat[row][col];
			index++;

			// Moving up-right
			if (dir == 1) {
				if (col == n-1) {        // Reached right boundary
					row++;               // Move down
					dir = -1;            // Change direction to down-left
				}
				else if (row == 0) {     // Reached top boundary
					col++;               // Move right
					dir = -1;            // Change direction to down-left
				}
				else {                   // Normal up-right movement
					row--;
					col++;
				}
			}
			// Moving down-left
			else {
				if (row == m-1) {        // Reached bottom boundary
					col++;               // Move right
					dir = 1;             // Change direction to up-right
				}
				else if (col == 0) {     // Reached left boundary
					row++;               // Move down
					dir = 1;             // Change direction to up-right
				}
				else {                   // Normal down-left movement
					row++;
					col--;
				}
			}
		}

		return result;
	}
}
