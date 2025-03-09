package main.meta.medium;

import java.util.*;

/**
 *
 *
 * <pre>
 * 378. Kth Smallest Element in a Sorted Matrix
 * Medium
 * Topics
 * Companies
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * You must find a solution with a memory complexity better than O(n2).
 *
 * Example 1:
 * Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 * Example 2:
 * Input: matrix = [[-5]], k = 1
 * Output: -5
 *
 *
 * Constraints:
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 300
 * -109 <= matrix[i][j] <= 109
 * All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
 * 1 <= k <= n2
 *
 *
 * Follow up:
 *
 * Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
 * Could you solve the problem in O(n) time complexity? The solution may be too advanced for an interview but you may find reading this paper fun.
 * Topics
 * Array
 * Binary Search
 * Sorting
 * Heap (Priority Queue)
 * Matrix
 * </pre>
 */
/** */
public class KthSmallestInSortedMatrix {

  /**
   * Finds the kth smallest element in a sorted matrix where both rows and columns are sorted in
   * ascending order. Solution for finding the kth smallest element in a sorted matrix.
   *
   * <p>In this implementation, we use a binary search approach on the range of values rather than
   * searching through indices. We count elements smaller than a given value to determine where the
   * kth smallest element falls.
   *
   * <p>Time Complexity: O(n * log(max-min)) where n is the dimension of the matrix and (max-min) is
   * the range of values in the matrix. Space Complexity: O(1) - uses constant extra space.
   *
   * @param matrix The n x n sorted matrix
   * @param k The position of the element to find (1-based)
   * @return The kth smallest element in the matrix
   */
  public int kthSmallest(int[][] matrix, int k) {
    int n = matrix.length;
    int low = matrix[0][0];
    int high = matrix[n - 1][n - 1];

    while (low < high) {
      int mid = low + (high - low) / 2;
      int count = countSmallerOrEqual(matrix, mid);

      if (count < k) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }

    return low;
  }

  /**
   * Counts the number of elements in the matrix that are smaller than or equal to target. Uses the
   * property that rows and columns are sorted to efficiently count.
   *
   * @param matrix The sorted matrix
   * @param target The target value
   * @return Count of elements smaller than or equal to target
   */
  private int countSmallerOrEqual(int[][] matrix, int target) {
    int n = matrix.length;
    int count = 0;
    int col = n - 1;

    // Start from top right corner
    for (int row = 0; row < n; row++) {
      // Move left while current element is greater than target
      while (col >= 0 && matrix[row][col] > target) {
        col--;
      }
      // Add number of elements in this row that are <= target
      count += (col + 1);
    }

    return count;
  }

  /** Main method with test cases. */
  public static void main(String[] args) {
    KthSmallestInSortedMatrix solution = new KthSmallestInSortedMatrix();

    // Test case 1
    int[][] matrix1 = {
      {1, 5, 9},
      {10, 11, 13},
      {12, 13, 15}
    };
    int k1 = 8;
    System.out.println("Test Case 1:");
    System.out.println("Expected: 13");
    System.out.println("Actual: " + solution.kthSmallest(matrix1, k1));
    System.out.println();

    // Test case 2
    int[][] matrix2 = {{-5}};
    int k2 = 1;
    System.out.println("Test Case 2:");
    System.out.println("Expected: -5");
    System.out.println("Actual: " + solution.kthSmallest(matrix2, k2));
    System.out.println();

    // Test case 3 - larger matrix
    int[][] matrix3 = {
      {1, 4, 7, 11, 15},
      {2, 5, 8, 12, 19},
      {3, 6, 9, 16, 22},
      {10, 13, 14, 17, 24},
      {18, 21, 23, 26, 30}
    };
    int k3 = 5;
    System.out.println("Test Case 3:");
    System.out.println("Expected: 5");
    System.out.println("Actual: " + solution.kthSmallest(matrix3, k3));
  }
}
