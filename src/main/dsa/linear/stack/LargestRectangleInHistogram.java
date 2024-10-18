package main.dsa.linear.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 84. Largest Rectangle in Histogram Hard Topics Companies Given an array of integers heights representing the
 * histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
 *
 *
 * Example 1:
 *
 * Input: heights = [2,1,5,6,2,3] Output: 10 Explanation: The above is a histogram where width of each bar is 1. The
 * largest rectangle is shown in the red area, which has an area = 10 units. Example 2:
 *
 * Input: heights = [2,4] Output: 4
 *
 * Constraints:
 *
 * 1 <= heights.length <= 105 0 <= heights[i] <= 104
 */
public class LargestRectangleInHistogram {

	public static int[] nextSmaller(int[] A) {
		int[] res = new int[A.length];
		Stack<Integer> stack = new Stack<>();

		for (int i = A.length - 1; i >= 0; i--) {
			// Pop elements from the stack if they are greater than or equal to the current element
			while (!stack.isEmpty() && A[stack.peek()] >= A[i]) {
				stack.pop();
			}
			// If the stack is empty, it means there is no smaller element to the right, so set to A.length
			res[i] = stack.isEmpty() ? A.length : stack.peek();
			// Push the index into the stack
			stack.push(i);
		}
		return res;
	}

	public static int[] prevSmaller(int[] A) {
		int[] res = new int[A.length];
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < A.length; i++) {
			while (!stack.isEmpty() && A[stack.peek()] >= A[i]) {
				stack.pop();
			}
			// If the stack is empty, it means there is no smaller element to the left, so set to -1
			res[i] = stack.isEmpty() ? -1 : stack.peek();
			// Push the index into the stack
			stack.push(i);
		}
		return res;
	}

	public static int largestRectangleArea(int[] heights) {
		int[] nextSmaller = nextSmaller(heights);
		int[] prevSmaller = prevSmaller(heights);

		System.out.println(Arrays.toString(nextSmaller));
		System.out.println(Arrays.toString(prevSmaller));

		int maxArea = 0;
		for (int i = 0; i < heights.length; i++) {
			// Calculate the width as the distance between nextSmaller and prevSmaller indices
			int width = nextSmaller[i] - prevSmaller[i] - 1;
			// Calculate the area for the current height
			int area = heights[i] * width;
			// Update the maximum area
			maxArea = Math.max(maxArea, area);
		}
		return maxArea;
	}

	public static int largestRectangleAreaOptimal(int[] heights) {
		int maxArea = 0;

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < heights.length; i++) {
			while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
				int height = stack.peek();
				stack.pop();
				int pse = stack.isEmpty() ? -1 : stack.peek();
				maxArea = Math.max(maxArea, heights[height] * (i - pse - 1));
			}
			stack.push(i);
		}

		while (!stack.isEmpty()) {
			int nse = heights.length;
			int height = stack.peek();
			stack.pop();
			int pse = stack.isEmpty() ? -1 : stack.peek();
			maxArea = Math.max(maxArea, heights[height] * (nse - pse - 1));
		}

		return maxArea;
	}

	public static void main(String[] args) {
		int[] heights = new int[] { 2, 1, 5, 6, 2, 3 };
		System.out.println(largestRectangleArea(heights));
		System.out.println(largestRectangleAreaOptimal(heights));
	}
}
