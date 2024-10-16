package main.dsa.linear.stack;

/**
 * 42. Trapping Rain Water Hard Topics Companies Given n non-negative integers representing an elevation map where the
 * width of each bar is 1, compute how much water it can trap after raining.
 *
 * Example 1: Input: height = [0,1,0,2,1,0,1,3,2,1,2,1] Output: 6 Explanation: The above elevation map (black section)
 * is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being
 * trapped.
 *
 * Example 2: Input: height = [4,2,0,3,2,5] Output: 9
 *
 * Constraints:
 *
 * n == height.length 1 <= n <= 2 * 104 0 <= height[i] <= 105
 *
 * Topics Array Two Pointers Dynamic Programming Stack Monotonic Stack
 */
public class TrappingRainWater {

	public static int trap(int[] height) {

		int total = 0;
		int leftMax = 0;
		int rightMax = 0;

		int left = 0;
		int right = height.length - 1;
		while (left < right) {

			if (height[left] < height[right]) {
				if (leftMax > height[left]) {
					total += (leftMax - height[left]);
				} else {
					leftMax = height[left];
				}
				left++;
			} else {
				if (rightMax > height[right]) {
					total += (rightMax - height[right]);
				} else {
					rightMax = height[right];
				}
				right--;
			}
		}

		return total;
	}

	public static void main(String[] args) {
		int[] height = new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		System.out.println(trap(height));
	}
}
