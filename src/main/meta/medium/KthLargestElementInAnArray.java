package main.meta.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * * 215. Kth Largest Element in an Array Medium Topics Companies Given an integer array nums and an
 * integer k, return the kth largest element in the array.
 * 
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * 
 * Can you solve it without sorting?
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [3,2,1,5,6,4], k = 2 Output: 5 Example 2:
 * 
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4 Output: 4
 * 
 * 
 * Constraints:
 * 
 * 1 <= k <= nums.length <= 105 -104 <= nums[i] <= 104 Seen this question in a real interview
 * before? 1/5 Yes No Accepted 2.8M Submissions 4.1M Acceptance Rate 67.5% Topics Array Divide and
 * Conquer Sorting Heap (Priority Queue) Quickselect
 */
public class KthLargestElementInAnArray {

    /**
     * Approach: Sorting
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(1)
     */
    class Solution {
        public int findKthLargest(int[] nums, int k) {

            Arrays.sort(nums);
            return nums[nums.length - k];
        }
    }


    /**
     * Approach: Quick Select
     *
     * Time Complexity: O(n) on average, O(n^2) in worst case
     * Space Complexity: O(1)
     */
    class Solution2 {
        public int findKthLargest(int[] nums, int k) {
            return quickSelect(nums, 0, nums.length - 1, k);
        }

        private int quickSelect(int[] nums, int low, int high, int k) {
            int pivot = nums[high];
            int left = low;
            for (int i = low; i < high; i++) {
                if (nums[i] < pivot) {
                    swap(nums, i, left++);
                }
            }
            swap(nums, left, high);
            int right = high - left + 1;
            if (right == k) {
                return nums[left];
            } else if (right > k) {
                return quickSelect(nums, left + 1, high, k);
            } else {
                return quickSelect(nums, low, left - 1, k - right);
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    /**
     * Approach: Heap
     *
     * Time Complexity: O(n log k)
     * Space Complexity: O(k)
     */
    class SolutionOptimal {

        public int findKthLargest(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
                throw new IllegalArgumentException("Invalid input");
            }

            // Create a min heap of size k
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();

            // Process first k elements
            for (int i = 0; i < k; i++) {
                minHeap.offer(nums[i]);
            }

            // Process remaining elements
            for (int i = k; i < nums.length; i++) {
                // If current element is larger than heap's minimum
                if (nums[i] > minHeap.peek()) {
                    minHeap.poll(); // Remove smallest element
                    minHeap.offer(nums[i]); // Add current element
                }
            }

            // The top of min heap is the kth largest element
            return minHeap.peek();
        }
    }
    
    

}
