package main.slidingWindow.medium;

import org.junit.Assert;

/**
 * @author agond
 * @package main.slidingWindow.medium
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

public class MaxConsecutiveOnesThree {

    public static int longestOnes(int[] nums, int k) {
        int ans = Integer.MIN_VALUE;
        int startIndex = 0;
        int endIndex = 0;
        int zeroes = 0;

        while (endIndex < nums.length) {

            if (nums[endIndex] == 0) {
                zeroes++;
            }

            while (zeroes > k) {
                if (nums[startIndex] == 0) {
                    zeroes--;
                }
                startIndex++;
            }
            ans = Math.max(ans, endIndex - startIndex + 1);

            endIndex++;
        }
        return ans;

    }

    public static void main(String[] args) {
        int[] arr;
        int k;

        arr = new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        k = 2;
        int result = longestOnes(arr, k);
        Assert.assertEquals("Test 1", 6, result);

        arr = new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        k = 3;
        result = longestOnes(arr, k);
        Assert.assertEquals("Test 2", 10, result);

        arr = new int[]{0, 0, 0, 0};
        k = 0;
        result = longestOnes(arr, k);
        Assert.assertEquals("Test 3", 0, result);

        arr = new int[]{0, 0, 1, 1, 1, 0, 0};
        result = longestOnes(arr, k);
        Assert.assertEquals("Test 4", 3, result);

    }
}
