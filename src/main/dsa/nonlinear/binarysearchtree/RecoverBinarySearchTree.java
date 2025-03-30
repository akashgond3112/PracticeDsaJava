package main.dsa.nonlinear.binarysearchtree;

/**
 * <pre>
 * 99. Recover Binary Search Tree
 * Medium
 * Topics
 * Companies
 * You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.
 *
 * Example 1:
 * Input: root = [1,3,null,null,2]
 * Output: [3,1,null,null,2]
 * Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
 * Example 2:
 *
 * Input: root = [3,1,4,null,null,2]
 * Output: [2,1,4,null,null,3]
 * Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [2, 1000].
 * -231 <= Node.val <= 231 - 1
 *
 * Follow up: A solution using O(n) space is pretty straight-forward. Could you devise a constant O(1) space solution?
 * Topics
 * Tree
 * Depth-First Search
 * Binary Search Tree
 * Binary Tree
 * </pre>
 */
public class RecoverBinarySearchTree {

    /**
     * Solution class for recovering a Binary Search Tree where two nodes are
     * swapped.
     * This implementation uses Morris Traversal to detect and fix the swapped
     * nodes.
     */
    class Solution {

        private Node first;
        private Node prev;
        private Node middle;
        private Node last;

        /**
         * Recovers the Binary Search Tree by finding and swapping two misplaced nodes.
         * Uses inorder traversal to identify violations of BST property where two nodes
         * are swapped.
         * 
         * The algorithm handles two cases:
         * 1. When the swapped nodes are adjacent (uses first and middle)
         * 2. When the swapped nodes are not adjacent (uses first and last)
         *
         * @param root The root node of the binary search tree to be recovered
         */
        public void recoverTree(Node root) {

            first = middle = last = null;

            prev = new Node(Integer.MIN_VALUE);
            inOrder(root);

            if (first != null && last != null) {
                int t = first.key;
                first.key = last.key;
                last.key = t;
            } else if (first != null && middle != null) {
                int t = first.key;
                first.key = middle.key;
                middle.key = t;
            }

        }

        /**
         * Performs inorder traversal of the tree to find violations of BST property.
         * Updates first, middle, and last pointers when violations are found.
         * 
         * @param root The current node being processed in the traversal
         */
        private void inOrder(Node root) {
            if (root == null)
                return;

            inOrder(root.left);

            if (prev != null && (root.key < prev.key)) {
                if (first == null) {
                    first = prev;
                    middle = root;
                } else {
                    last = root;
                }
            }

            prev = root;
            inOrder(root.right);
        }
    }
}
