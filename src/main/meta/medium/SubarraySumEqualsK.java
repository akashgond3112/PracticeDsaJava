package main.meta.medium;

import java.util.HashMap;
import java.util.Map;

/*
 * * 560. Subarray Sum Equals K Medium Topics Companies Hint Given an array of integers nums and an
 * integer k, return the total number of subarrays whose sum equals to k.
 * 
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,1,1], k = 2 Output: 2 Example 2:
 * 
 * Input: nums = [1,2,3], k = 3 Output: 2
 * 
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 2 * 104 -1000 <= nums[i] <= 1000 -107 <= k <= 107 Seen this question in a
 * real interview before? 1/5 Yes No Accepted 1.6M Submissions 3.6M Acceptance Rate 44.8% Topics
 * Array Hash Table Prefix Sum
 */
public class SubarraySumEqualsK {

    /**
     * Finds number of subarrays with sum equal to k Time Complexity: O(n^3) - Three nested loops
     * iterating through array Space Complexity: O(1) - Only using constant extra space
     */
    class Solution {
        public int subarraySum(int[] nums, int l) {

            int count = 0;

            for (int i = 0; i < nums.length; i++) {
                for (int j = i; j < nums.length; j++) {
                    int sum = 0;
                    for (int k = i; k <= j; k++) {
                        sum = sum + nums[k];
                    }
                    if (sum == l) {
                        count++;
                    }
                }
            }

            return count;
        }
    }

    /**
     * Finds number of subarrays with sum equal to k Time Complexity: O(n^2) - Three nested loops
     * iterating through array Space Complexity: O(1) - Only using constant extra space
     */
    class SolutionBetter {
        public int subarraySum(int[] nums, int l) {
            int count = 0;

            for (int i = 0; i < nums.length; i++) {
                int sum = 0;
                for (int j = i; j < nums.length; j++) {
                    sum = sum + nums[j];
                    if (sum == l) {
                        count++;
                    }
                }
            }

            return count;
        }
    }

    /**
     * Finds number of subarrays with sum equal to k Time Complexity: O(n) - Single loop iterating
     * through array Space Complexity: O(n) - Using HashMap to store prefix sum
     */
    class SolutionOptimal {
        public int subarraySum(int[] arr, int k) {
            int n = arr.length; // size of the given array.
            Map<Integer, Integer> mpp = new HashMap<>();
            int preSum = 0, cnt = 0;

            mpp.put(0, 1); // Setting 0 in the map.
            for (int i = 0; i < n; i++) {
                // add current element to prefix Sum:
                preSum += arr[i];

                // Calculate x-k:
                int remove = preSum - k;

                // Add the number of subarrays to be removed:
                cnt += mpp.getOrDefault(remove, 0);

                // Update the count of prefix sum
                // in the map.
                mpp.put(preSum, mpp.getOrDefault(preSum, 0) + 1);
            }
            return cnt;

        }
    }

}
