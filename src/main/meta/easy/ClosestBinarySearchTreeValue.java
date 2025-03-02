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

	static class Solution {
		public int closestValue(TreeNode root, double target) {
			int ans = root.val;
			double mi = Double.MAX_VALUE;
			while (root != null) {
				double t = Math.abs(root.val - target);
				if (t < mi || (t == mi && root.val < ans)) {
					mi = t;
					ans = root.val;
				}
				if (root.val > target) {
					root = root.left;
				} else {
					root = root.right;
				}
			}
			return ans;
		}
	}
}
