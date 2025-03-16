package main.meta.easy;

import java.util.*;

/**
 *
 *
 * <pre>
 * 724. Find Pivot Index
 * Easy
 * Topics
 * Companies
 * Hint
 * Given an array of integers nums, calculate the pivot index of this array.
 *
 * The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.
 *
 * If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.
 *
 * Return the leftmost pivot index. If no such index exists, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,7,3,6,5,6]
 * Output: 3
 * Explanation:
 * The pivot index is 3.
 * Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
 * Right sum = nums[4] + nums[5] = 5 + 6 = 11
 * Example 2:
 *
 * Input: nums = [1,2,3]
 * Output: -1
 * Explanation:
 * There is no index that satisfies the conditions in the problem statement.
 * Example 3:
 *
 * Input: nums = [2,1,-1]
 * Output: 0
 * Explanation:
 * The pivot index is 0.
 * Left sum = 0 (no elements to the left of index 0)
 * Right sum = nums[1] + nums[2] = 1 + -1 = 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -1000 <= nums[i] <= 1000
 *
 *
 * Note: This question is the same as 1991: https://leetcode.com/problems/find-the-middle-index-in-array/
 *
 * Seen this question in a real interview before?
 * 1/5
 * Yes
 * No
 * Accepted
 * 1.3M
 * Submissions
 * 2.2M
 * Acceptance Rate
 * 60.0%
 * Topics
 * Array
 * Prefix Sum
 * </pre>
 */
public class FindPivotIndex {

  /**
   * Solution class for finding the pivot index in an array. The pivot index is the index where the
   * sum of all elements to the left equals the sum of all elements to the right.
   */
  static class Solution {
    /**
     * Finds the pivot index in the given array.
     *
     * <p>Time Complexity: O(n) where n is the length of the array Space Complexity: O(1) as we only
     * use a constant amount of extra space
     *
     * @param nums The input array
     * @return The pivot index if found, -1 otherwise
     */
    public int pivotIndex(int[] nums) {
      int rightSum = 0;
      int leftSum = 0;

      // Calculate the total sum of all elements
      for (int num : nums) {
        rightSum += num;
      }

      // Iterate through the array to find the pivot index
      for (int i = 0; i < nums.length; i++) {
        int val = nums[i];
        rightSum -= val; // Remove current element from right sum

        if (leftSum == rightSum) {
          return i;
        }

        leftSum += val; // Add current element to left sum
      }
      return -1;
    }
  }

  /**
   * Main method for testing the Solution.
   *
   * @param args Command line arguments (not used)
   */
  public static void main(String[] args) {}
}
