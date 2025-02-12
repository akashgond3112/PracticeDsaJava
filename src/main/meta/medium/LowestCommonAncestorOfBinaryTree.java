package main.meta.medium;

/**
 *236. Lowest Common Ancestor of a Binary Tree
 * Medium
 * Topics
 * Companies
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 * Example 3:
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q will exist in the tree. */

public class LowestCommonAncestorOfBinaryTree {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

	/**
	 * Solution using In-Order traversal approach
	 * Time Complexity: O(N) - visits each node once
	 * Space Complexity: O(H) - recursion stack space, H is tree height
	 */
	static class SolutionUsingInOrder {
		public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
			// Base case
			if (root == null || root == p || root == q) {
				return root;
			}

			// Process left subtree first
			TreeNode left = lowestCommonAncestor(root.left, p, q);
			// Then process right subtree
			TreeNode right = lowestCommonAncestor(root.right, p, q);

			// If both subtrees return non-null, current node is LCA
			if (left != null && right != null) {
				return root;
			}

			// Return non-null node
			return left != null ? left : right;
		}
	}

	/**
	 * Solution using Pre-Order traversal approach
	 * Processes current node before children
	 * Time Complexity: O(N) - visits each node once
	 * Space Complexity: O(H) - recursion stack space
	 */
	static class SolutionUsingPreOrder {
		private TreeNode lca = null;
		
		public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
			// Process root first
			findLCA(root, p, q);
			return lca;
		}

		private boolean findLCA(TreeNode node, TreeNode p, TreeNode q) {
			if (node == null) return false;
			
			// Current node match
			int curr = (node == p || node == q) ? 1 : 0;
			
			// Process left and right
			int left = findLCA(node.left, p, q) ? 1 : 0;
			int right = findLCA(node.right, p, q) ? 1 : 0;
			
			// If any two values are true, we found LCA
			if (curr + left + right >= 2) {
				lca = node;
			}
			
			return (curr + left + right > 0);
		}
	}

	/**
	 * Solution using Post-Order traversal approach
	 * Processes children before current node
	 * Time Complexity: O(N) - visits each node once
	 * Space Complexity: O(H) - recursion stack space
	 */
	static class SolutionUsingPostOrder {
		public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
			if (root == null) return null;
			
			// Process left and right subtrees first
			TreeNode left = lowestCommonAncestor(root.left, p, q);
			TreeNode right = lowestCommonAncestor(root.right, p, q);
			
			// If current node is one of p or q, it is LCA
			if (root == p || root == q) {
				return root;
			}
			
			// If nodes found in different subtrees, current node is LCA
			if (left != null && right != null) {
				return root;
			}
			
			// Return the non-null subtree result
			return left != null ? left : right;
		}
	}
}
