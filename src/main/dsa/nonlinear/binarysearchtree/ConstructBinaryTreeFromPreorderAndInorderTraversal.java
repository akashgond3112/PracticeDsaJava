package main.dsa.nonlinear.binarysearchtree;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal Given two
 * integer arrays preorder and inorder where
 * preorder is the preorder traversal of a binary tree and inorder is the
 * inorder traversal of the same tree, construct
 * and return the binary tree.
 * <p>
 *
 *
 * Example 1:
 * <p>
 *
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7] Output:
 * [3,9,20,null,null,15,7] Example 2:
 * <p>
 * Input: preorder = [-1], inorder = [-1] Output: [-1]
 * <p>
 *
 * Constraints:
 * <p>
 * 1 <= preorder.length <= 3000 inorder.length == preorder.length -3000 <=
 * preorder[i], inorder[i] <= 3000 preorder and
 * inorder consist of unique values. Each value of inorder also appears in
 * preorder. preorder is guaranteed to be the
 * preorder traversal of the tree. inorder is guaranteed to be the inorder
 * traversal of the tree.
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

	/**
	 * Time complexity: O(n^2) Space complexity: O(n^2)
	 */
	static class SolutionBruteForceDFS {
		public Node buildTree(int[] preorder, int[] inorder) {
			if (preorder.length == 0 || inorder.length == 0) {
				return null;
			}

			Node root = new Node(preorder[0]);
			int mid = -1;
			for (int i = 0; i < inorder.length; i++) {
				if (inorder[i] == preorder[0]) {
					mid = i;
					break;
				}
			}

			int[] leftPreorder = Arrays.copyOfRange(preorder, 1, mid + 1);
			int[] leftInorder = Arrays.copyOfRange(inorder, 0, mid);
			root.left = buildTree(leftPreorder, leftInorder);

			int[] rightPreorder = Arrays.copyOfRange(preorder, mid + 1, preorder.length);
			int[] rightInorder = Arrays.copyOfRange(inorder, mid + 1, inorder.length);
			root.right = buildTree(rightPreorder, rightInorder);

			return root;
		}
	}

	/**
	 * Time complexity: O(n) Space complexity: O(n)
	 */
	public static class SolutionUsingHashMap {
		int pre_idx = 0;
		HashMap<Integer, Integer> indices = new HashMap<>();

		public Node buildTree(int[] preorder, int[] inorder) {
			for (int i = 0; i < inorder.length; i++) {
				indices.put(inorder[i], i);
			}
			return dfs(preorder, 0, inorder.length - 1);
		}

		private Node dfs(int[] preorder, int l, int r) {
			if (l > r)
				return null;
			int root_val = preorder[pre_idx++];
			Node root = new Node(root_val);
			int mid = indices.get(root_val);
			root.left = dfs(preorder, l, mid - 1);
			root.right = dfs(preorder, mid + 1, r);
			return root;
		}
	}

	/**
	 * Time complexity: O(n) Space complexity: O(n)
	 */
	public static class SolutionUsingDFSOptimal {
		int preIdx = 0;
		int inIdx = 0;

		public Node buildTree(int[] preorder, int[] inorder) {
			return dfs(preorder, inorder, Integer.MAX_VALUE);
		}

		private Node dfs(int[] preorder, int[] inorder, int limit) {
			if (preIdx >= preorder.length)
				return null;
			if (inorder[inIdx] == limit) {
				inIdx++;
				return null;
			}

			Node root = new Node(preorder[preIdx++]);
			root.left = dfs(preorder, inorder, root.key);
			root.right = dfs(preorder, inorder, limit);
			return root;
		}
	}
}
