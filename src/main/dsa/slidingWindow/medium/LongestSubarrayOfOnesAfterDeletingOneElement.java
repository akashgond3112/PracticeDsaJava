package main.dsa.slidingWindow.medium;

import org.junit.Assert;

/**
 * @author agond
 * @package main.dsa.slidingWindow.medium
 * @Date 14/09/2023
 * @Project PracticeDSA
 */

/*
    1493. Longest Subarray of 1's After Deleting One Element
    Hint
    Given a binary array nums, you should delete one element from it.

    Return the size of the longest non-empty subarray containing only 1's in the resulting array.
    Return 0 if there is no such subarray.

    Example 1:

    Input: nums = [1,1,0,1]
    Output: 3
    Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
    Example 2:

    Input: nums = [0,1,1,1,0,1,1,0,1]
    Output: 5
    Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
    Example 3:

    Input: nums = [1,1,1]
    Output: 2
    Explanation: You must delete one element.

    Constraints:

    1 <= nums.length <= 105
    nums[i] is either 0 or 1.
*/
public class LongestSubarrayOfOnesAfterDeletingOneElement {
    public static int longestSubarray(int[] nums) {
        int ans = 0;
        int startIndex = 0;
        int endIndex = 0;
        int zeroes = 0;

        while (endIndex < nums.length) {

            if (nums[endIndex] == 0) {
                zeroes++;
            }

            while (zeroes > 1) {
                if (nums[startIndex] == 0) {
                    zeroes--;
                }
                startIndex++;
            }
            ans = Math.max(ans, endIndex - startIndex + 1 - zeroes);

            endIndex++;

        }
        if (ans == nums.length) {
            ans = ans - 1;
        }
        return ans;

    }

    public static void main(String[] args) {
        int[] test1 = {1, 1, 0, 1};
        int result = longestSubarray(test1);
        System.out.println("Test 1: " + result);
        Assert.assertEquals(3, result);


        int[] test2 = {1, 1, 1};
         result = longestSubarray(test2);
        System.out.println("Test 1: " + result);
        Assert.assertEquals(2, result);
    }
}
