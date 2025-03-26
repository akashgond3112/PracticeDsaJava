package main.dsa.nonlinear.binarysearchtree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 230. Kth Smallest Element in a BST Medium Topics Companies Hint Given the
 * root of a binary search tree, and an
 * integer k, return the kth smallest value (1-indexed) of all the values of the
 * nodes in the tree.
 * <p>
 *
 *
 * Example 1:
 * <p>
 *
 * Input: root = [3,1,4,null,2], k = 1 Output: 1 Example 2:
 * <p>
 *
 * Input: root = [5,3,6,2,4,null,null,1], k = 3 Output: 3
 * <p>
 *
 * Constraints:
 * <p>
 * The number of nodes in the tree is n. 1 <= k <= n <= 104 0 <= Node.val <= 104
 * <p>
 *
 * Follow up: If the BST is modified often (i.e., we can do insert and delete
 * operations) and you need to find the kth
 * smallest frequently, how would you optimize?
 */
public class KthSmallestElementInABST {

	/**
	 * Time complexity: O(nlogn) Space complexity: O(n)
	 */
	static class SolutionBruteForce {
		public int kthSmallest(Node root, int k) {
			List<Integer> arr = new ArrayList<>();

			dfs(root, arr);
			Collections.sort(arr);
			return arr.get(k - 1);
		}

		private void dfs(Node node, List<Integer> arr) {
			if (node == null) {
				return;
			}

			arr.add(node.key);
			dfs(node.left, arr);
			dfs(node.right, arr);
		}
	}

	/**
	 * Time complexity: O(n) Space complexity: O(n)
	 */
	public static class SolutionDfsInOrder {
		public int kthSmallest(Node root, int k) {
			List<Integer> arr = new ArrayList<>();

			dfs(root, arr);
			return arr.get(k - 1);
		}

		private void dfs(Node node, List<Integer> arr) {
			if (node == null) {
				return;
			}

			dfs(node.left, arr);
			arr.add(node.key);
			dfs(node.right, arr);
		}
	}

	/**
	 * Time complexity: O(n) Space complexity: O(n)
	 */
	public static class SolutionRecursiveDfs {
		public int kthSmallest(Node root, int k) {
			int[] tmp = new int[2];
			tmp[0] = k;
			dfs(root, tmp);
			return tmp[1];
		}

		private void dfs(Node node, int[] tmp) {
			if (node == null) {
				return;
			}

			dfs(node.left, tmp);
			tmp[0] -= 1;
			if (tmp[0] == 0) {
				tmp[1] = node.key;
				return;
			}
			dfs(node.right, tmp);
		}
	}

	/**
	 * Time complexity: O(n) Space complexity: O(n)
	 */
	static class SolutionIterativeDfs {
		public int kthSmallest(Node root, int k) {
			Stack<Node> stack = new Stack<>();
			Node curr = root;

			while (!stack.isEmpty() || curr != null) {
				while (curr != null) {
					stack.push(curr);
					curr = curr.left;
				}
				curr = stack.pop();
				k--;
				if (k == 0) {
					return curr.key;
				}
				curr = curr.right;
			}

			return -1;
		}
	}

	/**
	 * Time complexity: O(n) Space complexity: O(1)
	 */
	public static class SolutionMorrisTraversal {
		public int kthSmallest(Node root, int k) {
			Node curr = root;

			while (curr != null) {
				if (curr.left == null) {
					k--;
					if (k == 0)
						return curr.key;
					curr = curr.right;
				} else {
					Node pred = curr.left;
					while (pred.right != null && pred.right != curr)
						pred = pred.right;

					if (pred.right == null) {
						pred.right = curr;
						curr = curr.left;
					} else {
						pred.right = null;
						k--;
						if (k == 0)
							return curr.key;
						curr = curr.right;
					}
				}
			}
			return -1;
		}
	}
}
