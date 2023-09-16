package main.slidingWindow.medium;

import org.junit.Assert;

import java.util.LinkedList;

/**
 * @author agond
 * @package main.slidingWindow.medium
 * @Date 16/09/2023
 * @Project PracticeDSA
 */

/*
1248. Count Number of Nice Subarrays

Hint
Given an array of integers nums and an integer k.
A continuous subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.

Example 1:

Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
Example 2:

Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There is no odd numbers in the array.
Example 3:

Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16

Constraints:

1 <= nums.length <= 50000
1 <= nums[i] <= 10^5
1 <= k <= nums.length
*/
public class CountNumberOfNiceSubArrays {

    public static int numberOfSubArrays(int[] nums, int k) {
        int startIndex = 0;
        int endIndex = 0;
        int max = 0;
        int count = 0;
        int temp = 0;

        while (endIndex < nums.length) {
            if (nums[endIndex] % 2 != 0) {
                count++;
                temp = 0;
            }

            while (count == k) {
                temp++;
                if (nums[startIndex] % 2 == 1) {
                    count--;
                }
                startIndex++;
            }
            max += temp;
            endIndex++;
        }

        return max;
    }

    public static int numberOfSubarrays(int[] nums, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(-1);
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] % 2 == 1)
                list.add(i);
            if (list.size() > k + 1)
                list.pop();
            if (list.size() == k + 1)
                res += list.get(1) - list.get(0);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 1, 1};
        int k = 3;
        int result = numberOfSubArrays(arr, k);
        Assert.assertEquals(2, result);

        int[] arr2 = {2, 4, 6};
        k = 1;
        result = numberOfSubArrays(arr2, k);
        Assert.assertEquals(0, result);

        int[] arr3 = {2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
        k = 2;
        result = numberOfSubarrays(arr3, k);
        Assert.assertEquals(16, result);

    }


}
