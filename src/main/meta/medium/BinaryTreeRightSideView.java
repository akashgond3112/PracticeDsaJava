package main.meta.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. Binary Tree Right Side View Medium Topics Companies Given the root of a binary tree, imagine yourself standing
 * on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * <p>
 * Example 1: Input: root = [1,2,3,null,5,null,4] Output: [1,3,4]
 * <p>
 * Example 2: Input: root = [1,2,3,4,null,null,null,5] Output: [1,3,4,5]
 * <p>
 * Example 3: Input: root = [1,null,3] Output: [1,3]
 * <p>
 * Example 4: Input: root = [] Output: []
 * <p>
 * Constraints: The number of nodes in the tree is in the range [0, 100]. -100 <= Node.val <= 100
 */
public class BinaryTreeRightSideView {

	/**
	 * Approach: Modified Preorder Traversal for Tree Side Views
	 *
	 * This solution provides methods to find both the right side view and left side view
	 * of a binary tree - the values that would be visible when looking at the tree
	 * from either the right or left side.
	 *
	 * Algorithm for right side view:
	 * 1. Perform a modified preorder traversal with right child first, then left child
	 * 2. Track the level/depth of each node during traversal
	 * 3. For each level, the first node encountered will be the rightmost node
	 *
	 * Algorithm for left side view:
	 * 1. Perform a modified preorder traversal with left child first, then right child
	 * 2. Track the level/depth of each node during traversal
	 * 3. For each level, the first node encountered will be the leftmost node
	 *
	 * Time Complexity: O(n) for both methods
	 * - Each node in the tree is visited exactly once
	 * - All operations at each node are O(1)
	 *
	 * Space Complexity: O(h) where h is the height of the tree
	 * - In the worst case (skewed tree), space complexity is O(n)
	 * - The recursion stack can grow to the height of the tree
	 * - The result list contains at most one node per level, so O(h) space
	 *
	 * Example visualization:
	 *     1
	 *    / \
	 *   2   3
	 *  / \   \
	 * 4   5   6
	 *
	 * Right side view: [1,3,6]
	 * Left side view: [1,2,4]
	 */
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

	static class TreeSideViews {
		/**
		 * Returns the right side view of the binary tree.
		 */
		public List<Integer> rightSideView(TreeNode root) {
			List<Integer> rightSideNodes = new ArrayList<>();
			traverseRightFirst(root, 0, rightSideNodes);
			return rightSideNodes;
		}

		/**
		 * Helper method to perform a right-first DFS traversal.
		 *
		 * @param currentNode The current node being processed
		 * @param currentLevel The depth/level of the current node (root is level 0)
		 * @param rightmostNodes List to store the rightmost node at each level
		 */
		private void traverseRightFirst(TreeNode currentNode, int currentLevel, List<Integer> rightmostNodes) {
			// Base case: if current node is null, return
			if (currentNode == null)
				return;

			// If this is the first node we've seen at this level
			// It must be the rightmost node since we process right subtrees first
			if (currentLevel == rightmostNodes.size()) {
				rightmostNodes.add(currentNode.val);
			}

			// Process right subtree first (higher priority)
			traverseRightFirst(currentNode.right, currentLevel + 1, rightmostNodes);

			// Then process left subtree
			traverseRightFirst(currentNode.left, currentLevel + 1, rightmostNodes);
		}

		/**
		 * Returns the left side view of the binary tree.
		 */
		public List<Integer> leftSideView(TreeNode root) {
			List<Integer> leftSideNodes = new ArrayList<>();
			traverseLeftFirst(root, 0, leftSideNodes);
			return leftSideNodes;
		}

		/**
		 * Helper method to perform a left-first DFS traversal.
		 *
		 * @param currentNode The current node being processed
		 * @param currentLevel The depth/level of the current node (root is level 0)
		 * @param leftmostNodes List to store the leftmost node at each level
		 */
		private void traverseLeftFirst(TreeNode currentNode, int currentLevel, List<Integer> leftmostNodes) {
			// Base case: if current node is null, return
			if (currentNode == null)
				return;

			// If this is the first node we've seen at this level
			// It must be the leftmost node since we process left subtrees first
			if (currentLevel == leftmostNodes.size()) {
				leftmostNodes.add(currentNode.val);
			}

			// Process left subtree first (higher priority)
			traverseLeftFirst(currentNode.left, currentLevel + 1, leftmostNodes);

			// Then process right subtree
			traverseLeftFirst(currentNode.right, currentLevel + 1, leftmostNodes);
		}
	}

	/**
	 * Reverse Post-Order Traversal Approach:
	 * <p>
	 * 1) Uses depth-first search with a twist: visits right subtree before left subtree
	 * <p>
	 * 2) Keeps track of the maximum level seen so far
	 * <p>
	 * 3) Adds a node to the result if it's the first node seen at its level
	 * <p>
	 * 4) Time complexity: O(n),
	 * <p>
	 * 5) Space complexity: O(h) where h is height of tree
	 */
	static class SolutionReversePostOrderTraversal {
		int maxLevel = -1;  // Track the maximum level seen so far

		public List<Integer> rightSideView(TreeNode root) {
			List<Integer> list = new ArrayList<>();
			rightView(root, 0, list);
			return list;
		}

		private void rightView(TreeNode node, int level, List<Integer> list) {
			if (node == null) {
				return;
			}

			// If this is the first node we've seen at this level
			if (level > maxLevel) {
				list.add(node.val);
				maxLevel = level;
			}

			// Visit right subtree first
			rightView(node.right, level + 1, list);
			rightView(node.left, level + 1, list);
		}
	}

	/**
	 * Level-Order Traversal Approach:
	 * <p>
	 * 1) Uses breadth-first search with a queue
	 * <p>
	 * 2) Processes nodes level by level
	 * <p>
	 * 3) For each level, adds the rightmost node to the result
	 * <p>
	 * 4) Time complexity: O(n),
	 * <p>
	 * 5) Space complexity: O(w) where w is max width of tree*/
	static class SolutionLevelOrderTraversal {
		public List<Integer> rightSideView(TreeNode root) {
			List<Integer> list = new ArrayList<>();
			if (root == null) {
				return list;
			}

			Queue<TreeNode> queue = new LinkedList<>();
			queue.offer(root);

			while (!queue.isEmpty()) {
				int levelSize = queue.size();

				// Process all nodes at current level
				for (int i = 0; i < levelSize; i++) {
					TreeNode current = queue.poll();

					// If this is the last node in the current level, add it to result
					if (i == levelSize - 1) {
						assert current != null;
						list.add(current.val);
					}

					// Add children to queue
					assert current != null;
					if (current.left != null) {
						queue.offer(current.left);
					}
					if (current.right != null) {
						queue.offer(current.right);
					}
				}
			}

			return list;
		}
	}


}
