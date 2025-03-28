package main.dsa.nonlinear.binarysearchtree;

import main.dsa.nonlinear.binarysearchtree.BinarySearchTreeIterator.BSTIterator;

/**
 * <pre>
 * 653. Two Sum IV - Input is a BST
 * Easy
 * Topics
 * Companies
 * Given the root of a binary search tree and an integer k, return true if there exist two elements in the BST such that their sum is equal to k, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,3,6,2,4,null,7], k = 9
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [5,3,6,2,4,null,7], k = 28
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -104 <= Node.val <= 104
 * root is guaranteed to be a valid binary search tree.
 * -105 <= k <= 105
 * Seen this question in a real interview before?
 * 1/5
 * Yes
 * No
 * Accepted
 * 631.1K
 * Submissions
 * 1M
 * Acceptance Rate
 * 62.0%
 * Topics
 * Hash Table
 * Two Pointers
 * Tree
 * Depth-First Search
 * Breadth-First Search
 * Binary Search Tree
 * Binary Tree
 * </pre>
 */
public class TwoSumInputisABinarySearchTree {

    /**
     * Determines if there exist two elements in a Binary Search Tree that sum to a
     * given target.
     * Uses two BST iterators to traverse the tree from both ends (smallest to
     * largest and largest to smallest)
     * implementing a two-pointer technique.
     *
     * Time Complexity: O(n), where n is the number of nodes in the tree
     * Space Complexity: O(h), where h is the height of the tree (due to iterator
     * stack space)
     *
     * @param root The root node of the Binary Search Tree
     * @param k    The target sum to find
     * @return true if two different nodes in the tree sum to target k, false
     *         otherwise
     */
    class Solution {
        public boolean findTarget(Node root, int k) {

            if (root == null)
                return false;

            BSTIterator binarySearchTreeIteratorOne = new BSTIterator(root, false);
            BSTIterator binarySearchTreeIteratorTwo = new BSTIterator(root, true);

            int i = binarySearchTreeIteratorOne.next();
            int j = binarySearchTreeIteratorTwo.next();

            while (i < j) {
                int val = i + j;
                if (val == k)
                    return true;
                else if (val < k) {
                    i = binarySearchTreeIteratorOne.next();
                } else {
                    j = binarySearchTreeIteratorTwo.next();
                }
            }
            return false;
        }
    }

}
