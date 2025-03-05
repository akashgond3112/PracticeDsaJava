package main.meta.medium;

import org.junit.Assert;

/**
 * @author agond
 * @package main.dsa.slidingWindow.medium
 * @Date 17/09/2023
 * @Project PracticeDSA
 */

/*
1004. Max Consecutive Ones III

Hint
Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

Example 1:

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
Example 2:

Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
0 <= k <= nums.length
*/

public class MaxConsecutiveOnesIII {

    /**
     * Solution for the "Max Consecutive Ones III" problem.
     * Finds the length of the longest subarray containing only 1's after flipping at most k 0's.
     */
    public static class Solution {

        /**
         * Brute force solution for the "Max Consecutive Ones III" problem.
         * Finds the length of the longest subarray containing only 1's after flipping at most k 0's.
         *
         * @param nums Binary array with elements 0 and 1
         * @param k Maximum number of 0's that can be flipped to 1's
         * @return Length of the longest subarray containing only 1's after flipping at most k 0's
         *
         * Time Complexity: O(n²) where n is the length of the input array
         * Space Complexity: O(1) using constant extra space
         */
        public static int longestOnesBruteForce(int[] nums, int k) {
            int maxLength = 0;

            // Try each possible starting position
            for (int start = 0; start < nums.length; start++) {
                int zeroCount = 0;

                // Extend the subarray as far as possible
                for (int end = start; end < nums.length; end++) {
                    // Count zeros in the current window
                    if (nums[end] == 0) {
                        zeroCount++;
                    }

                    // If we have more zeros than k we can flip, break
                    if (zeroCount > k) {
                        break;
                    }

                    // Update max length if current window is valid
                    maxLength = Math.max(maxLength, end - start + 1);
                }
            }

            return maxLength;
        }

        /**
         * Finds the maximum length of a subarray with all 1's after flipping at most k 0's to 1's.
         *
         * Uses a sliding window approach to maintain a window where at most k zeros are flipped.
         *
         * @param nums Binary array with elements 0 and 1
         * @param k Maximum number of 0's that can be flipped to 1's
         * @return Length of the longest subarray containing only 1's after flipping at most k 0's
         *
         * Time Complexity: O(n) where n is the length of the input array
         * Space Complexity: O(1) using constant extra space
         */
        public static int longestOnes(int[] nums, int k) {
            // Initialize variables for sliding window
            int maxLength = 0;  // No need for Integer.MIN_VALUE as the minimum length is 0
            int left = 0;
            int right = 0;
            int zeroCount = 0;

            while (right < nums.length) {
                // Expand the window
                if (nums[right] == 0) {
                    zeroCount++;
                }

                // Shrink the window if we have too many zeros
                while (zeroCount > k) {
                    if (nums[left] == 0) {
                        zeroCount--;
                    }
                    left++;
                }

                // Update the maximum length
                maxLength = Math.max(maxLength, right - left + 1);

                // Move the right pointer
                right++;
            }

            return maxLength;
        }

        /**
         * Main method for testing the solution.
         *
         * @param args Command line arguments
         */
        public static void main(String[] args) {
            int[] arr;
            int k;
            int result;

            // Test case 1
            arr = new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
            k = 2;
            result = longestOnes(arr, k);
            assert result == 6 : "Test 1 failed: expected 6, got " + result;

            // Test case 2
            arr = new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
            k = 3;
            result = longestOnes(arr, k);
            assert result == 10 : "Test 2 failed: expected 10, got " + result;

            // Test case 3
            arr = new int[]{0, 0, 0, 0};
            k = 0;
            result = longestOnes(arr, k);
            assert result == 0 : "Test 3 failed: expected 0, got " + result;

            // Test case 4
            arr = new int[]{0, 0, 1, 1, 1, 0, 0};
            k = 0;  // Note: k value was missing in your test case 4
            result = longestOnes(arr, k);
            assert result == 3 : "Test 4 failed: expected 3, got " + result;

            System.out.println("All tests passed!");
        }
    }
}
