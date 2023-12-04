package main.slidingWindow.medium;

import org.junit.Assert;

import java.util.PriorityQueue;

/**
 * @author agond
 * @package main.slidingWindow.medium
 * @Date 04/12/2023
 * @Project PracticeDSA
 * 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
 * Medium
 * Topics
 * Companies
 * Hint
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [8,2,4,7], limit = 4
 * Output: 2
 * Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4.
 * [8,2] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
 * [2] with maximum absolute diff |2-2| = 0 <= 4.
 * [2,4] with maximum absolute diff |2-4| = 2 <= 4.
 * [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
 * [4] with maximum absolute diff |4-4| = 0 <= 4.
 * [4,7] with maximum absolute diff |4-7| = 3 <= 4.
 * [7] with maximum absolute diff |7-7| = 0 <= 4.
 * Therefore, the size of the longest subarray is 2.
 * Example 2:
 * <p>
 * Input: nums = [10,1,2,4,7,2], limit = 5
 * Output: 4
 * Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
 * Example 3:
 * <p>
 * Input: nums = [4,2,2,2,4,4,2,2], limit = 0
 * Output: 3
 * <p>
 * Constraints:
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 0 <= limit <= 109
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    public static int longestSubarray(int[] nums, int limit) {

        int startIndex = 0;
        int endIndex = 0;
        int max = 0;

        PriorityQueue<Integer> maxQ = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        PriorityQueue<Integer> minQ = new PriorityQueue<>(Integer::compareTo);

        while (endIndex < nums.length) {

            maxQ.offer(nums[endIndex]);
            minQ.offer(nums[endIndex]);

            while (!maxQ.isEmpty() && !minQ.isEmpty() && maxQ.peek() - minQ.peek() > limit) {
                maxQ.remove(nums[startIndex]);
                minQ.remove(nums[startIndex]);
                startIndex++;
            }

            max = Math.max(max, endIndex - startIndex + 1);

            endIndex++;
        }

        return max;
    }


    public static void main(String[] args) {
        int[] arr;
        int result = 0;
        int limit = 0;

        limit = 4;
        arr = new int[]{8, 2, 4, 7};
        result = longestSubarray(arr, limit);
        Assert.assertEquals(2, result);

        limit=5;
        arr = new int[]{10,1,2,4,7,2};
        result = longestSubarray(arr, limit);
        Assert.assertEquals(4, result);

        limit=0;
        arr = new int[]{4,2,2,2,4,4,2,2};
        result = longestSubarray(arr, limit);
        Assert.assertEquals(3, result);


    }
}
