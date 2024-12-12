package main.blind75.twoPointers;

/*
11. Container With Most Water
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
2 <= n <= 105
0 <= height[i] <= 104
*/
public class ContainerWithMostWater {

	/**
	 * Time complexity: O(n^2 )
	 * Space complexity: O(1)
	 */
	public int maxArea(int[] heights) {
		int res = 0;
		for (int i = 0; i < heights.length; i++) {
			for (int j = i + 1; j < heights.length; j++) {
				res = Math.max(res, Math.min(heights[i], heights[j]) * (j - i));
			}
		}
		return res;
	}

	/**
	 * Time complexity: O(n) Space complexity: O(1)
	 */
	public int maxAreaOptimal(int[] heights) {
		int l = 0;
		int r = heights.length - 1;
		int res = 0;

		while (l < r) {
			int area = Math.min(heights[l], heights[r]) * (r - l);
			res = Math.max(res, area);
			if (heights[l] <= heights[r]) {
				l++;
			} else {
				r--;
			}
		}
		return res;
	}
}
