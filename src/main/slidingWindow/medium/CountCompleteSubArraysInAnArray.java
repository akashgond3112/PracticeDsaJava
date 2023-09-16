package main.slidingWindow.medium;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author agond
 * @package main.slidingWindow.medium
 * @Date 16/09/2023
 * @Project PracticeDSA
 */

/*
2799. Count Complete Subarrays in an Array

Hint
You are given an array nums consisting of positive integers.

We call a subarray of an array complete if the following condition is satisfied:

The number of distinct elements in the subarray is equal to the number of distinct elements in the whole array.
Return the number of complete subarrays.

A subarray is a contiguous non-empty part of an array.

Example 1:

Input: nums = [1,3,1,2,2]
Output: 4
Explanation: The complete subarrays are the following: [1,3,1,2], [1,3,1,2,2], [3,1,2] and [3,1,2,2].
Example 2:

Input: nums = [5,5,5,5]
Output: 10
Explanation: The array consists only of the integer 5, so any subarray is complete. The number of subarrays that we can choose is 10.


Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 2000
*/
public class CountCompleteSubArraysInAnArray {

    public static int countCompleteSubArrays(int[] nums) {
        int startIndex = 0;
        int endIndex = 0;
        int max = 0;
        Map<Integer, Integer> integerMap = new HashMap<>();

        for (int num : nums) {
            integerMap.put(num, 0);
        }

        int size = integerMap.size();

        if (size == 1) {
            return (nums.length * (nums.length + 1)) / 2;
        }
        integerMap.clear();


        while (endIndex < nums.length) {

            integerMap.put(nums[endIndex], integerMap.getOrDefault(nums[endIndex], 0) + 1);
            while (integerMap.size() == size) {
                max += nums.length - endIndex;
                integerMap.put(nums[startIndex], integerMap.getOrDefault(nums[startIndex], 0) - 1);
                if (integerMap.get(nums[startIndex]) == 0) {
                    integerMap.remove(nums[startIndex]);
                }
                startIndex++;
            }
            endIndex++;

        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 1, 2, 2};
        int result = countCompleteSubArrays(arr);
        Assert.assertEquals(4, result);

        int[] arr2 = {5, 5, 5, 5};
        result = countCompleteSubArrays(arr2);
        Assert.assertEquals(10, result);

    }
}
