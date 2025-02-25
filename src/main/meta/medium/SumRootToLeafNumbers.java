package main.meta.medium;

import main.blind75.binary.trees.Pair;
import main.blind75.binary.trees.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <pre>
 *  129. Sum Root to Leaf Numbers
 * Medium
 * Topics
 * Companies
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 * Each root-to-leaf path in the tree represents a number.
 * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 * Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
 * A leaf node is a node with no children.
 *
 *
 *
 * Example 1:
 * Input: root = [1,2,3]
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 *
 * Example 2:
 * Input: root = [4,9,0,5,1]
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 1000].
 * 0 <= Node.val <= 9
 * The depth of the tree will not exceed 10.
 * Topics
 * Tree
 * Depth-First Search
 * Binary Tree
 * </pre>
 */
public class SumRootToLeafNumbers {

	/**
	 * <pre>
	 * Time & Space Complexity
	 * Time complexity: O(n)
	 * Space complexity: O(h) for recursion stack.
	 * Where n is the number of nodes and h is the height of the given tree.
	 * </pre>
	 */
	static class SolutionUsingDfs {
		public int sumNumbers(BinarySearchTreeIterator.TreeNode root) {
			if (root == null)
				return 0;
			return dfs(root, 0);
		}

		private int dfs(BinarySearchTreeIterator.TreeNode root, int i) {
			if (root == null)
				return 0;

			i = i * 10 + root.val;
			if (root.left == null && root.right == null)
				return i;
			return dfs(root.left, i) + dfs(root.right, i);
		}
	}

	/**
	 * <pre>
	 * Time & Space Complexity
	 * Time complexity: O(n)
	 * Space complexity: O(n).
	 * </pre>
	 */
	public static class SolutionUsingBfs {
		public int sumNumbers(TreeNode root) {
			int res = 0;
			Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();
			q.offer(new Pair<>(root, 0));
			while (!q.isEmpty()) {
				Pair<TreeNode, Integer> p = q.poll();
				TreeNode cur = p.getKey();
				int num = p.getValue() * 10 + cur.val;
				if (cur.left == null && cur.right == null) {
					res += num;
					continue;
				}

				if (cur.left != null)
					q.offer(new Pair<>(cur.left, num));
				if (cur.right != null)
					q.offer(new Pair<>(cur.right, num));
			}

			return res;
		}
	}

	/**
	 * <pre>
	 * Time & Space Complexity
	 * Time complexity: O(n)
	 * Space complexity: O(1).
	 * </pre>
	 */
	public static class SolutionUsingMorrisTraversal {
		public int sumNumbers(TreeNode root) {
			int res = 0, num = 0;
			int[] power = new int[10];
			power[0] = 1;
			for (int i = 1; i < 10; i++) {
				power[i] = power[i - 1] * 10;
			}

			TreeNode cur = root;
			while (cur != null) {
				if (cur.left == null) {
					num = num * 10 + cur.val;
					if (cur.right == null)
						res += num;
					cur = cur.right;
				} else {
					TreeNode prev = cur.left;
					int steps = 1;
					while (prev.right != null && prev.right != cur) {
						prev = prev.right;
						steps++;
					}

					if (prev.right == null) {
						prev.right = cur;
						num = num * 10 + cur.val;
						cur = cur.left;
					} else {
						prev.right = null;
						if (prev.left == null)
							res += num;
						num /= power[steps];
						cur = cur.right;
					}
				}
			}
			return res;
		}
	}
}
