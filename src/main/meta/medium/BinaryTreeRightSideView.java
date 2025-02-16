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

	static class SolutionReversePreOrderTraversal {
		public List<Integer> rightSideView(TreeNode root) {
			List<Integer> list = new ArrayList<>();
			rightLeftSideView(root, 0, list);
			return list;
		}

		private void rightLeftSideView(TreeNode root, int level, List<Integer> list) {
			if (root == null)
				return;
			if (level == list.size()) {
				list.add(root.val);
			}
			rightLeftSideView(root.right, level + 1, list);
			rightLeftSideView(root.left, level + 1, list);
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
