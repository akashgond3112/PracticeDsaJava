package main.blind75.binary.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 572. Subtree of Another Tree Easy Topics Companies Hint Given the roots of two binary trees root and subRoot, return
 * true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
 * <p>
 * A subtree of a binary tree is a tree that consists of a node in tree and all of this node's descendants. The tree
 * could also be considered as a subtree of itself.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [3,4,5,1,2], subRoot = [4,1,2] Output: true Example 2:
 * <p>
 * Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2] Output: false
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the root tree is in the range [1, 2000]. The number of nodes in the subRoot tree is in the
 * range [1, 1000]. -104 <= root.val <= 104 -104 <= subRoot.val <= 104
 */
public class SubtreeOfAnotherTree {

	/**
	 * Time complexity:O(m∗n) Space complexity: O(m+n), Where m is the number of nodes in subRoot and n is the number of
	 * nodes in root.
	 */
	static class SolutionUsingDFS {
		public boolean isSubtree(TreeNode root, TreeNode subRoot) {
			if (subRoot == null) {
				return true;
			}
			if (root == null) {
				return false;
			}

			if (sameTree(root, subRoot)) {
				return true;
			}
			return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
		}

		public boolean sameTree(TreeNode root, TreeNode subRoot) {
			if (root == null && subRoot == null) {
				return true;
			}
			if (root != null && subRoot != null && root.val == subRoot.val) {
				return sameTree(root.left, subRoot.left) && sameTree(root.right, subRoot.right);
			}
			return false;
		}
	}

	/**
	 * Time complexity:O(n) Space complexity: O(n)
	 */
	public static class SolutionUsingDFSPreOrderTraversal {

		String preOrderTraversal(TreeNode node) {
			if (node == null) {
				return "null";
			}

			StringBuilder sb = new StringBuilder("^");
			sb.append(node.val);
			sb.append(preOrderTraversal(node.left));
			sb.append(preOrderTraversal(node.right));

			return sb.toString();
		}

		boolean isSubtree(TreeNode root, TreeNode subRoot) {

			String fullTree = preOrderTraversal(root);
			String subTree = preOrderTraversal(subRoot);

			return (fullTree.contains(subTree));
		}

	}

	/**
	 * Time complexity: O(m * n), where `m` is the number of nodes in `subRoot` and `n` is the number of nodes in
	 * `root`. Space complexity: O(n), where `n` is the number of nodes in the tree (queue size in BFS).
	 */
	static class SolutionUsingBFS {
		public boolean isSubtree(TreeNode root, TreeNode subRoot) {
			if (subRoot == null) {
				return true; // An empty tree is always a subtree
			}
			if (root == null) {
				return false; // A non-empty tree cannot be a subtree of an empty tree
			}

			Queue<TreeNode> queue = new LinkedList<>();
			queue.add(root);

			while (!queue.isEmpty()) {
				TreeNode current = queue.poll();

				// Check if the current subtree is identical to subRoot
				if (sameTree(current, subRoot)) {
					return true;
				}

				// Add children to the queue for further BFS traversal
				if (current.left != null) {
					queue.add(current.left);
				}
				if (current.right != null) {
					queue.add(current.right);
				}
			}

			return false; // Subtree not found
		}

		// Helper method to check if two trees are identical
		public boolean sameTree(TreeNode root, TreeNode subRoot) {
			if (root == null && subRoot == null) {
				return true;
			}
			if (root == null || subRoot == null || root.val != subRoot.val) {
				return false;
			}
			return sameTree(root.left, subRoot.left) && sameTree(root.right, subRoot.right);
		}
	}


}
