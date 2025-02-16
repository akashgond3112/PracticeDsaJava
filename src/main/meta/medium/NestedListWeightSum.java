package main.meta.medium;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 339. Nested List Weight Sum
 * Description
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.
 * <p>
 * The depth of an integer is the number of lists that it is inside. For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth.
 * <p>
 * Return the sum of each integer in nestedList multiplied by its depth.
 * <p>
 * Example 1:
 * <p>
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: 10
 * Explanation: Four 1's at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10.
 * Example 2:
 * <p>
 * Input: nestedList = [1,[4,[6]]]
 * Output: 27
 * Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3. 1*1 + 4*2 + 6*3 = 27.
 * Example 3:
 * <p>
 * Input: nestedList = [0]
 * Output: 0
 * <p>
 * Constraints:
 * <p>
 * 1 <= nestedList.length <= 50
 * The values of the integers in the nested list is in the range [-100, 100].
 * The maximum depth of any integer is less than or equal to 50.*/
public class NestedListWeightSum {

	/**
	 * Interface for creating nested lists. Each NestedInteger can hold either:
	 * 1. A single integer value
	 * 2. A list of NestedInteger objects
	 */
	public abstract static class NestedInteger {
		/**
		 * Constructor initializes an empty nested list.
		 */
		public NestedInteger() {}

		/**
		 * Constructor initializes a single integer.
		 * @param value the integer value to be stored
		 */
		public NestedInteger(int value) {}

		/**
		 * Checks if this NestedInteger holds a single integer value.
		 * @return true if holds an integer, false if holds a nested list
		 */
		public abstract boolean isInteger();

		/**
		 * Returns the integer value if this NestedInteger holds a single integer.
		 * @return the integer value or null if this holds a nested list
		 */
		public abstract Integer getInteger();

		/**
		 * Sets this NestedInteger to hold a single integer value.
		 * @param value the integer value to be stored
		 */
		public abstract void setInteger(int value);

		/**
		 * Adds a NestedInteger to the nested list held by this NestedInteger.
		 * @param ni the NestedInteger to be added to the list
		 */
		public abstract void add(NestedInteger ni);

		/**
		 * Returns the nested list held by this NestedInteger.
		 * @return the nested list or empty list if this holds a single integer
		 */
		public abstract List<NestedInteger> getList();
	}

	/**
	 * Solution for calculating the weighted sum of nested integers based on their depth.
	 *
	 * Problem:
	 * Given a nested list of integers, return the sum of all integers multiplied by their depth.
	 * Each element is either an integer or a list whose elements may also be integers or other lists.
	 *
	 * Example:
	 * Input: [[1,1],2,[1,1]]
	 * Process:
	 * - [1,1] at depth 2: 1*2 + 1*2 = 4
	 * - 2 at depth 1: 2*1 = 2
	 * - [1,1] at depth 2: 1*2 + 1*2 = 4
	 * Output: 10 (4 + 2 + 4)
	 */
	static class Solution {
		/**
		 * Calculates the depth-weighted sum of all integers in the nested list structure.
		 * <p>
		 * Algorithm:
		 * 1. Start DFS with initial depth of 1
		 * 2. For each element in the current list:
		 *    - If it's an integer: add (value * current_depth) to sum
		 *    - If it's a list: recursively process it with (depth + 1)
		 * 3. Return accumulated sum
		 *
		 * @param nestedList the root level nested list
		 * @return the depth-weighted sum of all integers
		 * <p>
		 * Time Complexity: O(N)
		 * - N is total number of nested elements (both integers and lists)
		 * - Each element is processed exactly once
		 * - isInteger(), getInteger(), and getList() operations are O(1)
		 * <p>
		 * Space Complexity: O(D)
		 * - D is maximum depth of nesting
		 * - In worst case (highly unbalanced structure), D can be O(N)
		 * - Space is used by the recursive call stack
		 * <p>
		 * Edge Cases Handled:
		 * 1. Empty list: returns 0
		 * 2. Single integer: returns integer * 1
		 * 3. Nested empty lists: properly traversed
		 * 4. Deep nesting: handled via recursion
		 */
		public int depthSum(List<NestedInteger> nestedList) {
			// Validate input
			if (nestedList == null || nestedList.isEmpty()) {
				return 0;
			}
			return dfs(nestedList, 1);
		}

		/**
		 * Recursive helper method to calculate depth-weighted sum.
		 *
		 * @param nestedList current level of nested list
		 * @param depth current depth in the nested structure
		 * @return depth-weighted sum for current branch
		 */
		private int dfs(List<NestedInteger> nestedList, int depth) {
			int depthSum = 0;

			for (NestedInteger item : nestedList) {
				if (item.isInteger()) {
					// For integer values, multiply by current depth
					depthSum += item.getInteger() * depth;
				} else {
					// For nested lists, increment depth and recurse
					depthSum += dfs(item.getList(), depth + 1);
				}
			}

			return depthSum;
		}

		/**
		 * BFS implementation to calculate depth-weighted sum.
		 * Uses a queue to process elements level by level.
		 *
		 * Algorithm:
		 * 1. Initialize queue with root level elements
		 * 2. Process level by level, tracking current depth
		 * 3. For each element in current level:
		 *    - If integer: add weighted value to sum
		 *    - If list: add all elements to queue for next level
		 *
		 * Example:
		 * Input: [[1,1],2,[1,1]]
		 * Level 1: [1,1], 2, [1,1]     -> process 2
		 * Level 2: 1, 1, 1, 1          -> process all 1s
		 *
		 * @param nestedList the root level nested list
		 * @return the depth-weighted sum of all integers
		 *
		 * Time Complexity: O(N)
		 * - Visits each element exactly once
		 * - Queue operations are O(1)
		 *
		 * Space Complexity: O(W)
		 * - W is the maximum width of the tree
		 * - In worst case, can be O(N/2) at the widest level
		 * - Better than DFS for deeply nested structures
		 */
		public int depthSumBFS(List<NestedInteger> nestedList) {
			if (nestedList == null || nestedList.isEmpty()) {
				return 0;
			}

			Queue<NestedInteger> queue = new LinkedList<>(nestedList);  // Add all root level elements

			int depth = 1;
			int totalSum = 0;

			while (!queue.isEmpty()) {
				int levelSize = queue.size();  // Number of elements at current depth

				// Process all elements at current depth
				for (int i = 0; i < levelSize; i++) {
					NestedInteger current = queue.poll();

					assert current != null;
					if (current.isInteger()) {
						totalSum += current.getInteger() * depth;
					} else {
						// Add all elements from nested list to queue
						queue.addAll(current.getList());
					}
				}

				depth++;  // Move to next depth level
			}

			return totalSum;
		}
	}

	/**
	 * Example usage comparing DFS and BFS approaches:
	 * <p>
	 * List<NestedInteger> input = [[1,1],2,[1,1]];
	 * Solution solution = new Solution();
	 * <p>
	 * // Both methods return 10
	 * int dfsSolution = solution.depthSum(input);
	 * int bfsSolution = solution.depthSumBFS(input);
	 * <p>
	 * Key differences between DFS and BFS implementations:
	 * <p>
	 * 1. Memory Usage:
	 *    - DFS: O(D) stack space, better for wide trees
	 *    - BFS: O(W) queue space, better for deep trees
	 * <p>
	 * 2. Processing Order:
	 *    - DFS: Processes one branch completely before moving to next
	 *    - BFS: Processes all elements at same depth before going deeper
	 * <p>
	 * 3. Use Cases:
	 *    - DFS: Better for sparse trees or when memory is constrained
	 *    - BFS: Better for level-based processing or when tree depth is large
	 * <p>
	 * 4. Implementation:
	 *    - DFS: Simpler implementation using recursion
	 *    - BFS: More complex but avoids stack overflow for deep structures
	 */
}
