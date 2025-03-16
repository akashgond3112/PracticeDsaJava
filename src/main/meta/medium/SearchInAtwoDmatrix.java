package main.meta.medium;

import java.util.*;

/**
 *
 *
 * <pre>
 * 74. Search a 2D Matrix
 * Medium
 * Topics
 * Companies
 * You are given an m x n integer matrix matrix with the following two properties:
 *
 * Each row is sorted in non-decreasing order.
 * The first integer of each row is greater than the last integer of the previous row.
 * Given an integer target, return true if target is in matrix or false otherwise.
 *
 * You must write a solution in O(log(m * n)) time complexity.
 *
 * Example 1:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 * Example 2:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 * Topics
 * Array
 * Binary Search
 * Matrix
 * </pre>
 */
public class SearchInAtwoDmatrix {

  /**
   * Solution class for searching for a target value in a sorted 2D matrix. This solution treats the
   * 2D matrix as a flattened sorted array and applies binary search.
   */
  static class Solution {
    /**
     * Searches for a target value in a sorted matrix where: - Each row is sorted in ascending order
     * from left to right - The first element of each row is greater than the last element of the
     * previous row
     *
     * <p>Time Complexity: O(log(m*n)) where m is the number of rows and n is the number of columns
     * Space Complexity: O(1) as we only use a constant amount of extra space
     *
     * @param matrix The sorted 2D matrix to search
     * @param target The value to search for
     * @return true if the target is found, false otherwise
     */
    public boolean searchMatrix(int[][] matrix, int target) {
      // There appears to be an error in dimension calculation
      // Let's correct it:
      int rows = matrix.length; // Number of rows
      int cols = matrix[0].length; // Number of columns

      int low = 0;
      int high = rows * cols - 1;

      while (low <= high) {
        int mid = (low + high) / 2;
        int curRow = mid / cols; // Calculate row index from mid
        int curCol = mid % cols; // Calculate column index from mid

        if (matrix[curRow][curCol] == target) {
          return true;
        } else if (matrix[curRow][curCol] < target) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
      return false;
    }
  }
}
