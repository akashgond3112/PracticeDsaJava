package main.dsa.slidingWindow.medium;

import org.junit.Assert;

/**
 * @author agond
 * @package main.dsa.slidingWindow.medium
 * @Date 11/12/2023
 * @Project PracticeDSA
 * <p>
 * 978. Longest Turbulent Subarray
 * Medium
 * Topics
 * Companies
 * Given an integer array arr, return the length of a maximum size turbulent subarray of arr.
 * <p>
 * A subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.
 * <p>
 * More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is said to be turbulent if and only if:
 * <p>
 * For i <= k < j:
 * arr[k] > arr[k + 1] when k is odd, and
 * arr[k] < arr[k + 1] when k is even.
 * Or, for i <= k < j:
 * arr[k] > arr[k + 1] when k is even, and
 * arr[k] < arr[k + 1] when k is odd.
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [9,4,2,10,7,8,8,1,9]
 * Output: 5
 * Explanation: arr[1] > arr[2] < arr[3] > arr[4] < arr[5]
 * Example 2:
 * <p>
 * Input: arr = [4,8,12,16]
 * Output: 2
 * Example 3:
 * <p>
 * Input: arr = [100]
 * Output: 1
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 4 * 104
 * 0 <= arr[i] <= 109
 */
public class LongestTurbulentSubarray {

    public static int maxTurbulenceSize(int[] arr) {
        if (arr.length == 1) return 1;
        int startIndex = 0;
        int endIndex = 1;
        int max = 1;
        int prevCmp = Integer.compare(arr[1], arr[0]);

        while (endIndex < arr.length) {
            int cmp = Integer.compare(arr[endIndex], arr[endIndex - 1]);

            if (cmp == 0) {
                startIndex = endIndex;
            } else if (cmp == prevCmp) {
                startIndex = endIndex - 1;
            }

            max = Math.max(max, endIndex - startIndex + 1);
            prevCmp = cmp;
            endIndex++;
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr;
        int result;

        arr = new int[]{9,4,2,10,7,8,8,1,9};
        result = maxTurbulenceSize(arr);
        Assert.assertEquals(5, result);

        arr = new int[]{4,8,12,16};
        result = maxTurbulenceSize(arr);
        Assert.assertEquals(2, result);

        arr = new int[]{100};
        result = maxTurbulenceSize(arr);
        Assert.assertEquals(1, result);


    }
}
