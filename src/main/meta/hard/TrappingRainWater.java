package main.meta.hard;

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

	/**
	 *Time Complexity: O(N*N) as for each index we are calculating leftMax and rightMax so it is a nested loop.
	 * Space Complexity: O(1).
	 * */
	static class SolutionUsingBruteForce {
		static int trap(int[] arr) {
			int n = arr.length;
			int waterTrapped = 0;
			for (int i = 0; i < n; i++) {
				int j = i;
				int leftMax = 0, rightMax = 0;
				while (j >= 0) {
					leftMax = Math.max(leftMax, arr[j]);
					j--;
				}
				j = i;
				while (j < n) {
					rightMax = Math.max(rightMax, arr[j]);
					j++;
				}
				waterTrapped += Math.min(leftMax, rightMax) - arr[i];
			}
			return waterTrapped;
		}

		public static void main(String[] args) {
			int[] arr = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
			System.out.println("The duplicate element is " + trap(arr));
		}
	}

		/**
	 * Time Complexity: O(3*N) as we are traversing through the array only once. And O(2*N) for computing prefix and suffix array.
	 * Space Complexity: O(N)+O(N) for prefix and suffix arrays.
	 * */
		static class SolutionUsingPrefixAndSuffix {
			static int trap(int[] arr) {
				int n = arr.length;
				int[] prefix = new int[n];
				int[] suffix = new int[n];
				prefix[0] = arr[0];
				for (int i = 1; i < n; i++) {
					prefix[i] = Math.max(prefix[i - 1], arr[i]);
				}
				suffix[n - 1] = arr[n - 1];
				for (int i = n - 2; i >= 0; i--) {
					suffix[i] = Math.max(suffix[i + 1], arr[i]);
				}
				int waterTrapped = 0;
				for (int i = 0; i < n; i++) {
					waterTrapped += Math.min(prefix[i], suffix[i]) - arr[i];
				}
				return waterTrapped;
			}

			public static void main(String[] args) {
				int[] arr = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
				System.out.println("The duplicate element is " + trap(arr));
			}
		}

	/**
	 * Solution for the "Trapping Rain Water" problem that calculates the total amount
	 * of water that can be trapped between the bars.
	 *
	 * Algorithm:
	 * Uses two pointers (left and right) to scan the array from both ends.
	 * For each position, the amount of water that can be trapped depends on the
	 * minimum of maximum heights from both sides minus the current height.
	 *
	 * The key insight is:
	 * 1. If height[left] < height[right], we know right side has higher wall
	 * 2. Therefore, water trapped at left depends only on leftMax
	 * 3. Similar logic applies when height[right] is smaller
	 *
	 * Visual example:
	 * [0,1,0,2,1,0,1,3,2,1,2,1]
	 * Total trapped water = 6 units
	 *
	 * Time Complexity: O(n) where n is the length of height array
	 * Space Complexity: O(1) as we only use constant extra space
	 *
	 * @param height Array representing elevation map where height[i] is height at index i
	 * @return Total units of water that can be trapped
	 */
	public static int trap(int[] height) {
		// Total water trapped
		int total = 0;

		// Maximum height encountered from left and right sides
		int leftMax = 0;
		int rightMax = 0;

		// Two pointers for scanning array from both ends
		int left = 0;
		int right = height.length - 1;

		while (left < right) {
			// If left bar is smaller, process left side
			if (height[left] < height[right]) {
				// If current height is less than leftMax, water can be trapped
				if (leftMax > height[left]) {
					total += (leftMax - height[left]);
				}
				// Update leftMax if current height is higher
				else {
					leftMax = height[left];
				}
				left++;
			}
			// If right bar is smaller or equal, process right side
			else {
				// If current height is less than rightMax, water can be trapped
				if (rightMax > height[right]) {
					total += (rightMax - height[right]);
				}
				// Update rightMax if current height is higher
				else {
					rightMax = height[right];
				}
				right--;
			}
		}

		return total;
	}

	// Example usage
	public static void main(String[] args) {
		int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		System.out.println("Water trapped: " + trap(height)); // Output: 6
	}
}
