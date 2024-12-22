package main.blind75.binary.trees;

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree Medium Topics Companies Given a binary search tree (BST), find
 * the lowest common ancestor (LCA) node of two given nodes in the BST.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as
 * the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 *
 *
 * Example 1:
 * <p>
 *
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8 Output: 6 Explanation: The LCA of nodes 2 and 8 is 6.
 * Example 2:
 * <p>
 *
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4 Output: 2 Explanation: The LCA of nodes 2 and 4 is 2, since
 * a node can be a descendant of itself according to the LCA definition. Example 3:
 * <p>
 * Input: root = [2,1], p = 2, q = 1 Output: 2
 * <p>
 *
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [2, 105]. -109 <= Node.val <= 109 All Node.val are unique. p != q p
 * and q will exist in the BST.
 */
public class LowestCommonAncestorOfABinarySearchTree {

	/**
	 * Time complexity: O(h) Space complexity: O(h) Where h is the height of the tree.
	 */
	static class SolutionUsingDFSPreOrder {
		public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
			if (root == null || p == null || q == null) {
				return null;
			}
			if (Math.max(p.val, q.val) < root.val) {
				return lowestCommonAncestor(root.left, p, q);
			} else if (Math.min(p.val, q.val) > root.val) {
				return lowestCommonAncestor(root.right, p, q);
			} else {
				return root;
			}
		}
	}

	/**
	 * Time complexity: O(h) Space complexity: O(1) Where h is the height of the tree.
	 */
	public static class SolutionUsingIteration {
		public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
			TreeNode cur = root;

			while (cur != null) {
				if (p.val > cur.val && q.val > cur.val) {
					cur = cur.right;
				} else if (p.val < cur.val && q.val < cur.val) {
					cur = cur.left;
				} else {
					return cur;
				}
			}
			return null;
		}
	}
}
