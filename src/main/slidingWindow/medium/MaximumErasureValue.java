package main.slidingWindow.medium;

import org.junit.Assert;

/**
 * @author agond
 * @package main.slidingWindow.medium
 * @Date 22/09/2023
 * @Project PracticeDSA
 */

/*
1695. Maximum Erasure Value
Hint
You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.

Return the maximum score you can get by erasing exactly one subarray.

An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).

Example 1:

Input: nums = [4,2,4,5,6]
Output: 17
Explanation: The optimal subarray here is [2,4,5,6].
Example 2:

Input: nums = [5,2,1,2,5,2,1,2,5]
Output: 8
Explanation: The optimal subarray here is [5,2,1] or [1,2,5].


Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 104
*/
public class MaximumErasureValue {
    public static int maximumUniqueSubarray(int[] nums) {
        int startIndex = 0;
        int endIndex = 0;
        int max = 0;

        return max;
    }

    public static void main(String[] args) {
        int[] arr;
        int result = 0;

        arr = new int[]{4,2,4,5,6};
        result = maximumUniqueSubarray(arr);
        Assert.assertEquals("Test 1", 17, result);

        arr = new int[]{5,2,1,2,5,2,1,2,5};
        result = maximumUniqueSubarray(arr);
        Assert.assertEquals("Test 1", 8, result);
    }

}
