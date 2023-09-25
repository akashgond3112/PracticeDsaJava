package main.slidingWindow.medium;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author agond
 * @package main.slidingWindow.medium
 * @Date 25/09/2023
 * @Project PracticeDSA
 * <p>
 * 930. Binary Subarrays With Sum
 * Medium
 * Topics
 * Companies
 * Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
 * <p>
 * A subarray is a contiguous part of the array.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,0,1,0,1], goal = 2
 * Output: 4
 * Explanation: The 4 subarrays are bolded and underlined below:
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * Example 2:
 * <p>
 * Input: nums = [0,0,0,0,0], goal = 0
 * Output: 15
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 3 * 104
 * nums[i] is either 0 or 1.
 * 0 <= goal <= nums.length
 */
public class BinarySubarraysWithSum {

    public static int numSubarraysWithSum(int[] nums, int goal) {

        int count = 0;
        int sum = 0;
        Map<Integer, Integer> sumCounts = new HashMap<>();
        sumCounts.put(0, 1);

        for (int num : nums) {
            sum += num;
            count += sumCounts.getOrDefault(sum - goal, 0);
            sumCounts.put(sum, sumCounts.getOrDefault(sum, 0) + 1);
        }

        return count;

    }


    public static void main(String[] args) {
        int[] arr;
        int goal = 0;
        int result = 0;

        arr = new int[]{1, 0, 1, 0, 1};
        goal = 2;
        result = numSubarraysWithSum(arr, goal);
        Assert.assertEquals("Test 1", 4, result);


    }
}
