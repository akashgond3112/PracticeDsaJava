package main.meta.easy;


/**
 * 88. Merge Sorted Array Solved Easy Topics Companies Hint You are given two integer arrays nums1
 * and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of
 * elements in nums1 and nums2 respectively.
 *
 * <p>Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 *
 * <p>The final sorted array should not be returned by the function, but instead be stored inside
 * the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements
 * denote the elements that should be merged, and the last n elements are set to 0 and should be
 * ignored. nums2 has a length of n.
 *
 * <p>Example 1:
 *
 * <p>Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3 Output: [1,2,2,3,5,6] Explanation:
 * The arrays we are merging are [1,2,3] and [2,5,6]. The result of the merge is [1,2,2,3,5,6] with
 * the underlined elements coming from nums1. Example 2:
 *
 * <p>Input: nums1 = [1], m = 1, nums2 = [], n = 0 Output: [1] Explanation: The arrays we are
 * merging are [1] and []. The result of the merge is [1]. Example 3:
 *
 * <p>Input: nums1 = [0], m = 0, nums2 = [1], n = 1 Output: [1] Explanation: The arrays we are
 * merging are [] and [1]. The result of the merge is [1]. Note that because m = 0, there are no
 * elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
 *
 * <p>Constraints:
 *
 * <p>nums1.length == m + n nums2.length == n 0 <= m, n <= 200 1 <= m + n <= 200 -109 <= nums1[i],
 * nums2[j] <= 109
 *
 * <p>Follow up: Can you come up with an algorithm that runs in O(m + n) time?
 */
public class MergeSortedArray {

  /**
   * Solution for merging two sorted arrays using the Gap Method algorithm. This is an in-place
   * merging technique that uses O(1) extra space.
   */
  static class Solution {
    /**
     * Swaps elements between arrays if they are out of order.
     *
     * @param a first array
     * @param b second array
     * @param i index in first array
     * @param j index in second array
     *     <p>Time Complexity: O(1) Space Complexity: O(1)
     */
    private static void swap(int[] a, int[] b, int i, int j) {
      if (a[i] > b[j]) {
        int temp = a[i];
        a[i] = b[j];
        b[j] = temp;
      }
    }

    /**
     * Merges two sorted arrays using the Gap Method algorithm.
     *
     * <p>Algorithm: 1. Calculate initial gap as ceiling of (n+m)/2 2. Compare and swap elements
     * with current gap distance 3. Reduce gap by half (ceiling) until gap becomes 1 4. Final pass
     * with gap=1 ensures array is sorted
     *
     * <p>Example: a = [1, 4, 7, 8, 10] n = 5 b = [2, 3, 9] m = 3
     *
     * <p>Process: Initial gap = ceil((8/2)) = 4 Compare elements 4 positions apart: - Compare 1,10
     * | 4,2 | 7,3 | 8,9 - After swaps: [1,4,3,8,9] [2,7,10]
     *
     * <p>Gap = ceil(4/2) = 2 Compare elements 2 positions apart And so on...
     *
     * @param a first sorted array with space for merging
     * @param n length of first array
     * @param b second sorted array
     * @param m length of second array
     *     <p>Time Complexity: O((n+m) * log(n+m)) - Outer loop runs log(n+m) times (gap reducing by
     *     half) - Inner loop runs O(n+m) times for each gap
     *     <p>Space Complexity: O(1) - Only uses a constant amount of extra space - Merging is done
     *     in-place
     *     <p>Key Features: 1. In-place merging 2. No extra space required 3. Maintains stability of
     *     sorted arrays 4. Works with arrays of different sizes
     */
    public void merge(int[] a, int n, int[] b, int m) {
      // Calculate total length of merged array
      int length = n + m;

      // Calculate initial gap using ceiling division
      int gap = (length / 2) + (length % 2);

      while (gap > 0) {
        // Initialize pointers for current gap
        int left = 0;
        int right = left + gap;

        // Compare elements with current gap
        while (right < length) {
          // Case 1: left in array a right in array b
          if (left < n && right >= n) {
            swap(a, b, left, right - n);
          }
          // Case 2: both pointers in array b
          else if (left >= n) {
            swap(b, b, left - n, right - n);
          }
          // Case 3: both pointers in array a
          else {
            swap(a, a, left, right);
          }

          // Move pointers
          left++;
          right++;
        }

        // Break if gap is 1, else reduce gap
        if (gap == 1) {
          break;
        }
        gap = (gap / 2) + (gap % 2);
      }
    }

    /**
     * Example Usage:
     *
     * <p>int[] a = {1, 4, 7, 8, 10, 0, 0, 0}; // Extra space for merging int[] b = {2, 3, 9};
     * Solution solution = new Solution(); solution.merge(a, 5, b, 3); // Result: a = [1, 2, 3, 4,
     * 7, 8, 9, 10]
     *
     * <p>Edge Cases Handled: 1. One empty array 2. Arrays of very different sizes 3. Arrays with
     * duplicate elements 4. Already sorted arrays
     */
  }
}
