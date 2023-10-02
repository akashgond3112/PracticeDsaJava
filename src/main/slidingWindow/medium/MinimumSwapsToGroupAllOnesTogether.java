package main.slidingWindow.medium;

import org.junit.Assert;

/**
 * @author agond
 * @package main.slidingWindow.medium
 * @Date 02/10/2023
 * @Project PracticeDSA
 * <p>
 * 2134. Minimum Swaps to Group All 1's Together II
 * Medium
 * Topics
 * Companies
 * Hint
 * A swap is defined as taking two distinct positions in an array and swapping the values in them.
 * <p>
 * A circular array is defined as an array where we consider the first element and the last element to be adjacent.
 * <p>
 * Given a binary circular array nums, return the minimum number of swaps required to group all 1's present in the array together at any location.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1,0,1,1,0,0]
 * Output: 1
 * Explanation: Here are a few of the ways to group all the 1's together:
 * [0,0,1,1,1,0,0] using 1 swap.
 * [0,1,1,1,0,0,0] using 1 swap.
 * [1,1,0,0,0,0,1] using 2 swaps (using the circular property of the array).
 * There is no way to group all 1's together with 0 swaps.
 * Thus, the minimum number of swaps required is 1.
 * Example 2:
 * <p>
 * Input: nums = [0,1,1,1,0,0,1,1,0]
 * Output: 2
 * Explanation: Here are a few of the ways to group all the 1's together:
 * [1,1,1,0,0,0,0,1,1] using 2 swaps (using the circular property of the array).
 * [1,1,1,1,1,0,0,0,0] using 2 swaps.
 * There is no way to group all 1's together with 0 or 1 swaps.
 * Thus, the minimum number of swaps required is 2.
 * Example 3:
 * <p>
 * Input: nums = [1,1,0,0,1]
 * Output: 0
 * Explanation: All the 1's are already grouped together due to the circular property of the array.
 * Thus, the minimum number of swaps required is 0.
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 */
public class MinimumSwapsToGroupAllOnesTogether {

    public static int minSwaps(int[] nums) {
        int totalOnes = 0;
        for (int one : nums) {
            if (one == 1) totalOnes++;
        }

        int n = nums.length,
                startIndex = 0,
                endIndex = 0,
                currentWindowOnes = 0,
                swaps = n;

        while (endIndex < 2 * n) {

            if (endIndex < totalOnes) {
                if (nums[endIndex] == 1) currentWindowOnes++;

                endIndex++;
                swaps = totalOnes - currentWindowOnes;

            } else {
                if (nums[startIndex % n] == 1) currentWindowOnes--;

                if (nums[endIndex % n] == 1) currentWindowOnes++;

                startIndex++;
                endIndex++;

                swaps = Math.min(swaps, totalOnes - currentWindowOnes);
            }

        }

        return swaps;
    }

    public static void main(String[] args) {
        int[] arr;
        int result = 0;
        arr = new int[]{0, 1, 0, 1, 1, 0, 0};
        result = minSwaps(arr);
        Assert.assertEquals("Test 1", 1, result);
    }

}
