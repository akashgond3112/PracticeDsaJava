package main.dsa.nonlinear.binarysearchtree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 98. Validate Binary Search Tree Medium Topics Companies Given the root of a
 * binary tree, determine if it is a valid
 * binary search tree (BST).
 * <p>
 * A valid BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's
 * key. The right subtree of a node
 * contains only nodes with keys greater than the node's key. Both the left and
 * right subtrees must also be binary
 * search trees.
 * <p>
 *
 * Example 1:
 * <p>
 *
 * Input: root = [2,1,3] Output: true Example 2:
 * <p>
 *
 * Input: root = [5,1,4,null,null,3,6] Output: false Explanation: The root
 * node's value is 5 but its right child's value
 * is 4.
 * <p>
 *
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 104]. -231 <= Node.val <=
 * 231 - 1
 */
public class ValidateBinarySearchTree {

	/**
	 * Time complexity: O(n^2 ) Space complexity: O(n)
	 */
	static class SolutionBruteForce {
		static boolean left_check(int val, int limit) {
			return val < limit;
		}

		static boolean right_check(int val, int limit) {
			return val > limit;
		}

		public boolean isValidBST(Node root) {
			if (root == null) {
				return true;
			}

			if (!isValid(root.left, root.key, SolutionBruteForce::left_check) || !isValid(root.right, root.key,
					SolutionBruteForce::right_check)) {
				return false;
			}

			return isValidBST(root.left) && isValidBST(root.right);
		}

		public boolean isValid(Node root, int limit, CheckFunction check) {
			if (root == null) {
				return true;
			}
			if (!check.apply(root.key, limit)) {
				return false;
			}
			return isValid(root.left, limit, check) && isValid(root.right, limit, check);
		}

		interface CheckFunction {
			boolean apply(int val, int limit);
		}
	}

	/**
	 * Time complexity: O(n) Space complexity: O(n)
	 */
	static class SolutionBFS {
		public boolean isValidBST(Node root) {
			if (root == null) {
				return true;
			}

			Queue<Object[]> queue = new LinkedList<>();
			queue.offer(new Object[] { root, Long.MIN_VALUE, Long.MAX_VALUE });

			while (!queue.isEmpty()) {
				Object[] current = queue.poll();
				Node node = (Node) current[0];
				long left = (long) current[1];
				long right = (long) current[2];

				if (!(left < node.key && node.key < right)) {
					return false;
				}

				if (node.left != null) {
					queue.offer(new Object[] { node.left, left, (long) node.key });
				}
				if (node.right != null) {
					queue.offer(new Object[] { node.right, (long) node.key, right });
				}
			}

			return true;
		}
	}

	/**
	 * Time complexity: O(n) Space complexity: O(1)
	 */
	static class SolutionDFSUsingPreOrder {
		public boolean isValidBST(Node root) {
			return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
		}

		public boolean valid(Node node, long left, long right) {
			if (node == null) {
				return true;
			}
			if (!(left < node.key && node.key < right)) {
				return false;
			}
			return valid(node.left, left, node.key) && valid(node.right, node.key, right);
		}
	}

	/**
	 * Time complexity: O(n) Space complexity: O(n)
	 */
	public static class SolutionDFSUsingInOrder {

		boolean checkBST(Node root) {

			List<Integer> inOrderList = new LinkedList<>();

			// Populate the list
			helper(root, inOrderList);

			boolean isBST = true;
			int prev = inOrderList.getFirst();
			for (int i = 1; i < inOrderList.size(); i++) {

				// Check if new element is smaller than previous element
				// or if the element is duplicate
				if (inOrderList.get(i) <= prev)
					isBST = false;

				prev = inOrderList.get(i);
			}

			return isBST;
		}

		void helper(Node treeNode, List<Integer> inOrderList) {
			if (treeNode == null)
				return;

			helper(treeNode.left, inOrderList);
			inOrderList.add(treeNode.key);
			helper(treeNode.right, inOrderList);
		}

	}
}
