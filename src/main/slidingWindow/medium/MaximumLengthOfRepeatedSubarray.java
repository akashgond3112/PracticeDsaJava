package main.slidingWindow.medium;

import org.junit.Assert;

/**
 * @author agond
 * @package main.slidingWindow.medium
 * @Date 03/10/2023
 * @Project PracticeDSA
 * <p>
 * 718. Maximum Length of Repeated Subarray
 * Medium
 * Topics
 * Companies
 * Hint
 * Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * Output: 3
 * Explanation: The repeated subarray with maximum length is [3,2,1].
 * Example 2:
 * <p>
 * Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * Output: 5
 * Explanation: The repeated subarray with maximum length is [0,0,0,0,0].
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 100
 */
public class MaximumLengthOfRepeatedSubarray {

    public static int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        // Initialize a table to store common subarray lengths
        int[][] dp = new int[m + 1][n + 1];

        int maxLen = 0;

        // Fill the dp table based on dynamic programming
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }

        return maxLen;
    }


    public static void main(String[] args) {
        int[] nums1;
        int[] nums2;
        int result;

        nums1 = new int[]{1, 2, 3, 2, 1};
        nums2 = new int[]{3, 2, 1, 4, 7};
        result = findLength(nums1, nums2);
        Assert.assertEquals("Test 1", 3, result);

        nums1 = new int[]{0, 0, 0, 0, 0};
        nums2 = new int[]{0, 0, 0, 0, 0};
        result = findLength(nums1, nums2);
        Assert.assertEquals("Test 2", 5, result);

        nums1 = new int[]{0, 1, 1, 1, 1};
        nums2 = new int[]{1, 0, 1, 0, 1};
        result = findLength(nums1, nums2);
        Assert.assertEquals("Test 3", 2, result);

    }

}
