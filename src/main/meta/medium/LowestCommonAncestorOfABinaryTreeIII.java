package main.meta.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Description Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 *
 * Each node will have a reference to its parent node. The definition for Node is below:
 *
 * class Node { public int val; public Node left; public Node right; public Node parent; } According to the definition
 * of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p
 * and q as descendants (where we allow a node to be a descendant of itself)."
 *
 * Example 1:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1 Output: 3 Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4 Output: 5 Explanation: The LCA of nodes 5 and 4 is 5 since
 * a node can be a descendant of itself according to the LCA definition. Example 3:
 *
 * Input: root = [1,2], p = 1, q = 2 Output: 1
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 105]. -109 <= Node.val <= 109 All Node.val are unique. p != q p
 * and q exist in the tree.
 */
public class LowestCommonAncestorOfABinaryTreeIII {

	static class Node {
		public int val;
		public Node left;
		public Node right;
		public Node parent;
	}

	static class Solution {
		/**
		 * Finds the lowest common ancestor of two nodes in a binary tree using a set to track
		 * visited nodes.
		 *
		 * @param p the first node
		 * @param q the second node
		 * @return the lowest common ancestor of nodes p and q
		 *
		 * Time Complexity: O(h), where h is the height of the tree. Space Complexity: O(h),
		 * due to the space required to store the visited nodes in the set.
		 */
		public Node lowestCommonAncestor(Node p, Node q) {
			Set<Node> vis = new HashSet<>();
			for (Node node = p; node != null; node = node.parent) {
				vis.add(node);
			}
			for (Node node = q;; node = node.parent) {
				if (!vis.add(node)) {
					return node;
				}
			}
		}
	}

	class SolutionOptimal {
		/**
		 * Finds the lowest common ancestor of two nodes in a binary tree using two pointers.
		 *
		 * @param p the first node
		 * @param q the second node
		 * @return the lowest common ancestor of nodes p and q
		 *
		 * Time Complexity: O(h), where h is the height of the tree. Space Complexity: O(1),
		 * as it uses a constant amount of extra space.
		 */
		public Node lowestCommonAncestor(Node p, Node q) {
			Node a = p, b = q;
			while (a != b) {
				a = a.parent == null ? q : a.parent;
				b = b.parent == null ? p : b.parent;
			}
			return a;
		}
	}

}
