package main.two_pointers.medium;

/*
Container With Most Water

Medium

You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.

Example 1:
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

Example 2:
Input: height = [1,1]
Output: 1

Constraints:
n == height.length
2 <= n <= 10^5
0 <= height[i] <= 10^4
*/

import static org.junit.Assert.assertEquals;

public class ContainerWithMostWater {

	public static int maxArea(int[] height) {

		if(height.length == 0) return 0;

		if(height.length == 2) return Math.min(height[0], height[1]);

		int maxArea = 0;

		int left = 0, right = height.length - 1;

		while (left < right) {

			int currentArea = Math.min(height[left], height[right]) * (right - left);
			maxArea = Math.max(maxArea, currentArea);

			if (height[left] < height[right]) {
				left++;
			} else {
				right--;
			}

		}

		return maxArea;

	}

	public static void main(String[] args) {
		int[] arr;
		int result;

		arr = new int[] { 1,2,4,3 };
		result = maxArea(arr);
		assertEquals(4, result);

	}
}
