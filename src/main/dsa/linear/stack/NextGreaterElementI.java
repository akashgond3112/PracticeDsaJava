package main.dsa.linear.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 496. Next Greater Element I Easy Topics Companies The next greater element of some element x in an array is the first
 * greater element that is to the right of x in the same array.
 *
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 *
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater
 * element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
 *
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 *
 * Example 1:
 *
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2] Output: [-1,3,-1] Explanation: The next greater element for each value of
 * nums1 is as follows:
 * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
 * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 *
 * Example 2:
 *
 * Input: nums1 = [2,4], nums2 = [1,2,3,4] Output: [3,-1] Explanation: The next greater element for each value of nums1
 * is as follows:
 * - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
 * - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
 *
 * Constraints:
 *
 * 1 <= nums1.length <= nums2.length <= 1000 0 <= nums1[i], nums2[i] <= 104 All integers in nums1 and nums2 are unique.
 * All the integers of nums1 also appear in nums2.
 *
 *
 * Follow up: Could you find an O(nums1.length + nums2.length) solution? Monotonic Stack
 *
 * Time Complexity: Building the map for nums2: We iterate through the elements of nums2 once. For each element, the
 * operations on the stack (push, pop) take constant time. Each element is pushed and popped from the stack at most
 * once. Therefore, the time complexity of this step is O(n), where n is the length of nums2. Building the result array
 * for nums1: After constructing the map, we iterate through nums1, and for each element, we perform a constant-time
 * lookup in the map. Therefore, this step takes O(m), where m is the length of nums1. Overall, the time complexity is
 * O(n + m).
 *
 * Space Complexity: Stack: In the worst case, all elements of nums2 are pushed onto the stack. This takes O(n) space.
 * Map: We store the next greater element for each element of nums2 in a map, which requires O(n) space. Result Array:
 * The result array has a length equal to nums1, which requires O(m) space. Overall, the space complexity is O(n + m).
 *
 * Summary: Time Complexity: O(n + m) Space Complexity: O(n + m) Where n is the length of nums2 and m is the length of
 * nums1.
 */
public class NextGreaterElementI {

	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		// Create a map to store the next greater element for each number in nums2
		Map<Integer, Integer> map = new HashMap<>();
		Stack<Integer> stack = new Stack<>();

		// Traverse nums2 to find the next greater element for each element
		for (int i = nums2.length - 1; i >= 0; i--) {
			while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				map.put(nums2[i], -1);  // No greater element found
			} else {
				map.put(nums2[i], stack.peek());  // Next greater element
			}
			stack.push(nums2[i]);
		}

		// For nums1, find the corresponding next greater element using the map
		int[] result = new int[nums1.length];
		for (int i = 0; i < nums1.length; i++) {
			result[i] = map.get(nums1[i]);
		}

		return result;
	}

	public static void main(String[] args) {
		NextGreaterElementI nextGreaterElementI = new NextGreaterElementI();
		System.out.println(Arrays.toString(
				nextGreaterElementI.nextGreaterElement(new int[] { 4, 1, 2 }, new int[] { 1, 3, 4, 2 })));
	}
}
