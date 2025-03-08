package main.meta.easy;

import java.util.*;

/**
 *
 *
 * <pre>
 * 283. Move Zeroes
 * Easy
 * Topics
 * Companies
 * Hint
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Note that you must do this in-place without making a copy of the array.
 * Example 1:
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]#
 *
 * Example 2:
 * Input: nums = [0]
 * Output: [0]
 *
 * Constraints:
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 * </pre>
 */
public class MoveZeroes {

  static class Solution {
    /**
     * Moves all zeroes in the given array to the end while maintaining the relative order of
     * non-zero elements.
     *
     * <p>Algorithm approach: 1. Maintain an insertion position for non-zero elements 2. Iterate
     * through the array, placing non-zero elements at the insertion position 3. Fill the remaining
     * positions with zeroes
     *
     * @param nums The array of integers to be modified
     *     <p>Time Complexity: O(n) where n is the length of the array, as we iterate through the
     *     array once and then potentially fill remaining positions 
     * 	   <p>Space Complexity: O(1) as we
     *     modify the array in place without using additional data structures that scale with input
     *     size
     */
    public static void moveZeroes(int[] nums) {
      int insertPosition = 0;

      // Move all non-zero elements to the front
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] != 0) {
          nums[insertPosition] = nums[i];
          insertPosition++;
        }
      }

      // Fill the remaining positions with zeros
      while (insertPosition < nums.length) {
        nums[insertPosition++] = 0;
      }

      System.out.println(Arrays.toString(nums));
    }
  }

  /**
   * Main method to demonstrate the moveZeroes functionality.
   *
   * @param args Command line arguments (not used)
   */
  public static void main(String[] args) {
    int[] nums = new int[] {0, 2, 1, 5, 3, 4};
    Solution.moveZeroes(nums);
  }
}
