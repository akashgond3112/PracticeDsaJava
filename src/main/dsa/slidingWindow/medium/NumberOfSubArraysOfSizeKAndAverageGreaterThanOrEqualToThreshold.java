package main.dsa.slidingWindow.medium;

/**
 * @author agond
 * @package main.dsa.slidingWindow.medium
 * @Date 13/09/2023
 * @Project PracticeDSA
 */

/*
    1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold
    Hint
    Given an array of integers arr and two integers k and threshold, return the number of sub-arrays of size k and average greater than or
    equal to threshold.

    Example 1:

    Input: arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
    Output: 3
    Explanation: Sub-arrays [2,5,5],[5,5,5] and [5,5,8] have averages 4, 5 and 6 respectively.
    All other sub-arrays of size 3 have averages less than 4 (the threshold).
    Example 2:

    Input: arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
    Output: 6
    Explanation: The first 6 sub-arrays of size 3 have averages greater than 5. Note that averages are not integers.

    Constraints:
    1 <= arr.length <= 105
    1 <= arr[i] <= 104
    1 <= k <= arr.length
    0 <= threshold <= 104
*/
public class NumberOfSubArraysOfSizeKAndAverageGreaterThanOrEqualToThreshold {

    public static int numOfSubarrays(int[] arr, int k, int threshold) {

        int result = 0;
        int startIndex = 0;
        int endIndex = 0;
        int max = 0;

        while (endIndex < arr.length) {
            result = result + arr[endIndex];

            if (endIndex - startIndex + 1 < k) {
                endIndex++;
            } else if (endIndex - startIndex + 1 == k) {
                if ((result / k) >= threshold) {
                    max++;
                    result = result - arr[startIndex];
                    startIndex++;
                    endIndex++;
                } else {
                    result = result - arr[startIndex];
                    startIndex++;
                    endIndex++;
                }


            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {11, 13, 17, 23, 29, 31, 7, 5, 2, 3};
        int k = 3;
        int threshold = 5;
        System.out.println(numOfSubarrays(arr, k, threshold));
    }

}
