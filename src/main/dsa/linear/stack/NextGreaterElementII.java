package main.dsa.linear.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 503. Next Greater Element II Medium Topics Companies Given a circular integer array nums (i.e., the next element of
 * nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.
 *
 * The next greater number of a number x is the first greater number to its traversing-order next in the array, which
 * means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.
 *
 * Example 1:
 *
 * Input: nums = [1,2,1] Output: [2,-1,2] Explanation: The first 1's next greater number is 2; The number 2 can't find
 * next greater number. The second 1's next greater number needs to search circularly, which is also 2. Example 2:
 *
 * Input: nums = [1,2,3,4,3] Output: [2,3,4,-1,4]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104 -109 <= nums[i] <= 109
 *
 * Seen this question in a real interview before? 1/5
 * ### Time Complexity: The time complexity of this solution is **O(n)**, where `n` is the number of elements in the
 * input array `nums`.
 *
 * - We iterate over the array twice (due to the circular array simulation), but the key insight is that each element is
 * pushed and popped from the stack at most once. This makes the time complexity linear.
 * - The loop runs `2 * n` times, but each element is handled once when it is pushed onto the stack and once when it is
 * popped.
 *
 * Thus, the overall time complexity is **O(n)**.
 *
 * ### Space Complexity: The space complexity is **O(n)**.
 *
 * - The result array `res` takes **O(n)** space to store the final results.
 * - The stack can hold at most **n** elements in the worst case, which contributes to **O(n)** additional space.
 *
 * So, the overall space complexity is **O(n)**.
 */
public class NextGreaterElementII {

	public int[] nextGreaterElements(int[] nums) {

		int[] res = new int[nums.length];
		Stack<Integer> stack = new Stack<>();
		int n = nums.length;
		for (int i = 2 * n - 1; i >= 0; i--) {
			while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
				stack.pop();
			}
			if (i < n) {
				res[i] = stack.isEmpty() ? -1 : stack.peek();
			}
			stack.push(nums[i % n]);
		}
		return res;
	}

	public static void main(String[] args) {
		NextGreaterElementII nextGreater = new NextGreaterElementII();
		int[] nums = new int[] { 1, 2, 1 };
		int[] res = nextGreater.nextGreaterElements(nums);
		System.out.println(Arrays.toString(res));
	}
}
