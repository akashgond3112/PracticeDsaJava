package main.blind75.binary.trees;

/**
 * 124. Binary Tree Maximum Path Sum Hard Topics Companies A path in a binary tree is a sequence of nodes where each
 * pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most
 * once. Note that the path does not need to pass through the root.
 * <p>
 * The path sum of a path is the sum of the node's values in the path.
 * <p>
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 * <p>
 *
 *
 * Example 1:
 * <p>
 *
 * Input: root = [1,2,3] Output: 6 Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * <p>
 * Example 2: Input: root = [-10,9,20,null,null,15,7] Output: 42 Explanation: The optimal path is 15 -> 20 -> 7 with a
 * path sum of 15 + 20 + 7 = 42.
 * <p>
 *
 * Constraints: The number of nodes in the tree is in the range [1, 3 * 104]. -1000 <= Node.val <= 1000
 */
public class BinaryTreeMaximumPathSum {

	/**
	 * Time complexity: O(n^2 ) Space complexity: O(n)
	 */
	static public class SolutionUsingDfs {
		int res = Integer.MIN_VALUE;

		public int maxPathSum(TreeNode root) {
			dfs(root);
			return res;
		}

		private int getMax(TreeNode root) {
			if (root == null)
				return 0;
			int left = getMax(root.left);
			int right = getMax(root.right);
			int path = root.val + Math.max(left, right);
			return Math.max(0, path);
		}

		private void dfs(TreeNode root) {
			if (root == null)
				return;
			int left = getMax(root.left);
			int right = getMax(root.right);
			res = Math.max(res, root.val + left + right);
			dfs(root.left);
			dfs(root.right);
		}
	}

	/**
	 * Time complexity: O(n) Space complexity: O(n)
	 */
	public static class SolutionUsingDfsOptimal {

		public int maxPathSum(TreeNode root) {
			int[] res = new int[] {root.val};
			dfs(root, res);
			return res[0];
		}

		private int dfs(TreeNode root, int[] res) {
			if (root == null) {
				return 0;
			}

			int leftMax = Math.max(dfs(root.left, res), 0);
			int rightMax = Math.max(dfs(root.right, res), 0);

			res[0] = Math.max(res[0], root.val + leftMax + rightMax);
			return root.val + Math.max(leftMax, rightMax);
		}
	}
	
}
