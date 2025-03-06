package main.meta.easy;

/**
 * <pre>
 * 270. Closest Binary Search Tree Value
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 *
 * Note:
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest to the target.
 * Example:
 *
 * Input: root = [4,2,5,1,3], target = 3.714286
 *
 *     4
 *    / \
 *   2   5
 *  / \
 * 1   3
 *
 * Output: 4
 * Difficulty:
 * Easy
 * Company:
 * Amazon Bloomberg Facebook Google LinkedIn Microsoft Snapchat
 * </pre>
 */
public class ClosestBinarySearchTreeValue {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	/**
	 * <pre>
	 * Approach: Binary Search Tree Property Traversal
	 *
	 * This solution finds the value in a Binary Search Tree (BST) that is closest to a given target value.
	 * It leverages the BST property to efficiently navigate the tree, moving left or right based on
	 * comparisons with the target value.
	 *
	 * Algorithm:
	 * 1. Start at the root and initialize the closest value seen so far
	 * 2. While traversing down the tree:
	 *    a. Calculate the absolute difference between current node's value and target
	 *    b. Update the closest value if current node is closer (or equal but smaller)
	 *    c. Move left if current value > target (smaller values are on the left)
	 *    d. Move right if current value ≤ target (larger values are on the right)
	 *
	 * Time Complexity: O(h) where h is the height of the tree
	 * - In a balanced BST, this would be O(log n)
	 * - In the worst case (skewed tree), this would be O(n)
	 *
	 * Space Complexity: O(1)
	 * - We only use a constant amount of extra space regardless of tree size
	 * - The iterative approach avoids using recursion stack space
	 *
	 * Example:
	 *      4
	 *     / \
	 *    2   5
	 *   / \
	 *  1   3
	 *
	 * Target = 3.7
	 *
	 * Traversal:
	 * - At node 4: |4 - 3.7| = 0.3, closest = 4, go left (4 > 3.7)
	 * - At node 2: |2 - 3.7| = 1.7, closest still 4, go right (2 < 3.7)
	 * - At node 3: |3 - 3.7| = 0.7, closest still 4, go right (3 < 3.7)
	 * - No more nodes to visit
	 *
	 * Comparing final candidates:
	 * - |4 - 3.7| = 0.3 vs |3 - 3.7| = 0.7
	 * - 0.3 < 0.7, so closest = 4
	 *
	 * Wait, that's not right for this example. Let me trace again:
	 *
	 * - At node 4: |4 - 3.7| = 0.3, closest = 4, go left (4 > 3.7)
	 * - At node 2: |2 - 3.7| = 1.7, 1.7 > 0.3, so closest still 4, go right (2 < 3.7)
	 * - At node 3: |3 - 3.7| = 0.7, 0.7 > 0.3, so closest still 4, go right (3 < 3.7)
	 * - No right child, traversal ends
	 *
	 * Result: 4 is closest to 3.7
	 *
	 * Corrected example with target = 2.9:
	 * - At node 4: |4 - 2.9| = 1.1, closest = 4, go left (4 > 2.9)
	 * - At node 2: |2 - 2.9| = 0.9, 0.9 < 1.1, so closest = 2, go right (2 < 2.9)
	 * - At node 3: |3 - 2.9| = 0.1, 0.1 < 0.9, so closest = 3, go right (3 > 2.9)
	 * - No more nodes to visit
	 *
	 * Result: 3 is closest to 2.9
	 * </pre>
	 */
	public int closestValue(TreeNode root, double target) {
		// Initialize the answer with root's value
		int closestValue = root.val;
		double minDifference = Double.MAX_VALUE;

		// Traverse down the tree
		TreeNode currentNode = root;
		while (currentNode != null) {
			// Calculate difference between current value and target
			double currentDifference = Math.abs(currentNode.val - target);

			// Update closest value if current is closer
			// Or if equally close but current value is smaller
			if (currentDifference < minDifference ||
					(currentDifference == minDifference && currentNode.val < closestValue)) {
				minDifference = currentDifference;
				closestValue = currentNode.val;
			}

			// Decide which subtree to explore next
			if (currentNode.val > target) {
				// If current value is greater than target, go left
				currentNode = currentNode.left;
			} else {
				// If current value is less than or equal to target, go right
				currentNode = currentNode.right;
			}
		}

		return closestValue;
	}
}
