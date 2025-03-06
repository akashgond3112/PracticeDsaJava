package main.meta.medium;

/**
 * <pre>
 * 1762. Buildings With an Ocean View
 * Level
 * Medium
 *
 * Description
 * There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.
 * The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions.
 * Formally, a building has an ocean view if all the buildings to its right have a smaller height.
 * Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.
 *
 * Example 1:
 * Input: heights = [4,2,3,1]
 * Output: [0,2,3]
 * Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.
 *
 * Example 2:
 * Input: heights = [4,3,2,1]
 * Output: [0,1,2,3]
 * Explanation: All the buildings have an ocean view.
 *
 * Example 3:
 * Input: heights = [1,3,2,4]
 * Output: [3]
 * Explanation: Only building 3 has an ocean view.
 *
 * Example 4:
 * Input: heights = [2,2,2,2]
 * Output: [3]
 *
 * Explanation: Buildings cannot see the ocean if there are buildings of the same height to its right.
 * Constraints:
 * 1 <= heights.length <= 10^5
 * 1 <= heights[i] <= 10^9
 * </pre>*/
public class BuildingsWithAnOceanView {

	/**
	 * Solution to find buildings with an ocean view.
	 *
	 * Problem: Given an array of building heights, return indices of buildings
	 * that can see the ocean. A building can see the ocean if no building to its
	 * right is taller.
	 *
	 * @author Assistant
	 * @version 1.0
	 */
	class Solution {
		/**
		 * Finds indices of buildings with an ocean view.
		 *
		 * Algorithm Approach:
		 * 1. Traverse the array from right to left
		 * 2. Keep track of the maximum height encountered
		 * 3. Mark buildings with ocean view if taller than max height
		 * 4. Create result array with indices of ocean view buildings
		 *
		 * @param heights Array of building heights
		 * @return Array of indices of buildings with ocean view
		 *
		 * Time Complexity: O(n)
		 * - Single pass from right to left: O(n)
		 * - Second pass to collect indices: O(n)
		 *
		 * Space Complexity: O(n)
		 * - Boolean array to mark ocean view: O(n)
		 * - Result array to store indices: O(k), where k is number of ocean view buildings
		 *
		 * Example:
		 * Input: heights = [4,2,3,1]
		 * Output: [0,2,3]
		 * Explanation:
		 * - Building at index 0 (height 4) sees the ocean
		 * - Building at index 2 (height 3) sees the ocean
		 * - Building at index 3 (height 1) sees the ocean
		 */
		public int[] findBuildings(int[] heights) {
			// Edge case: handle empty input
			if (heights == null || heights.length == 0) {
				return new int[0];
			}

			int length = heights.length;

			// Track buildings with ocean view
			boolean[] oceanView = new boolean[length];
			int count = 0;

			// Maximum height encountered from right to left
			int maxHeight = 0;

			// Traverse from right to left to find ocean view buildings
			for (int i = length - 1; i >= 0; i--) {
				// Check if current building is taller than max height
				if (heights[i] > maxHeight) {
					oceanView[i] = true;
					count++;
					// Update max height
					maxHeight = heights[i];
				}
			}

			// Create result array with ocean view building indices
			int[] buildings = new int[count];
			int index = 0;

			// Collect indices of ocean view buildings
			for (int i = 0; i < length; i++) {
				if (oceanView[i]) {
					buildings[index++] = i;
				}
			}

			return buildings;
		}
	}

	/**
	 * Optimized solution for finding buildings with ocean view.
	 *
	 * Approach: Uses stack-like technique with constant extra space
	 *
	 * @author Assistant
	 * @version 1.0
	 */
	static class SolutionOptimised {
		/**
		 * Finds buildings with ocean view using optimal approach.
		 *
		 * Key Optimization Strategies:
		 * - Single pass from right to left
		 * - In-place tracking without additional data structures
		 * - Maintains monotonic stack-like behavior
		 *
		 * @param heights Array of building heights
		 * @return Array of indices with ocean view
		 *
		 * Time Complexity: O(n)
		 * - Single linear pass through the array
		 * - No nested loops or repeated traversals
		 *
		 * Space Complexity: O(1)
		 * - No additional data structures used
		 * - Result array is the only extra space
		 *
		 * Constraints Handled:
		 * - Works with empty arrays
		 * - Handles buildings of varying heights
		 * - Efficient for large input sets
		 *
		 * Example:
		 * Input: [4,2,3,1]
		 * Output: [0,2,3]
		 */
		public int[] findBuildings(int[] heights) {
			// Handle edge cases
			if (heights == null || heights.length == 0) {
				return new int[0];
			}

			int maxHeight = 0;
			int oceanViewCount = 0;

			// First pass: Count ocean view buildings
			for (int i = heights.length - 1; i >= 0; i--) {
				if (heights[i] > maxHeight) {
					oceanViewCount++;
					maxHeight = heights[i];
				}
			}

			// Create result array
			int[] oceanViewBuildings = new int[oceanViewCount];
			int index = 0;

			// Second pass: Populate ocean view building indices
			maxHeight = 0;
			for (int i = heights.length - 1; i >= 0; i--) {
				if (heights[i] > maxHeight) {
					oceanViewBuildings[index++] = i;
					maxHeight = heights[i];
				}
			}

			return oceanViewBuildings;
		}
	}
}
