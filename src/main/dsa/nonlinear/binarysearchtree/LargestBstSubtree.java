package main.dsa.nonlinear.binarysearchtree;

/**
 * <pre>
 * 333. Largest BST Subtree
 * Description
 * Given the root of a binary tree, find the largest subtree, which is also a
 * Binary Search Tree (BST), where the largest means subtree has the largest
 * number of nodes.
 * 
 * A Binary Search Tree (BST) is a tree in which all the nodes follow the
 * below-mentioned properties:
 * 
 * The left subtree values are less than the value of their parent (root) node's
 * value.
 * The right subtree values are greater than the value of their parent (root)
 * node's value.
 * Note: A subtree must include all of its descendants.
 * 
 * Example 1:
 * 
 * Input: root = [10,5,15,1,8,null,7]
 * Output: 3
 * Explanation: The Largest BST Subtree in this case is the highlighted one. The
 * return value is the subtree's size, which is 3.
 * Example 2:
 * 
 * Input: root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1]
 * Output: 2
 * 
 * 
 * Constraints:
 * 
 * The number of nodes in the tree is in the range [0, 104].
 * -104 <= Node.val <= 104
 * </pre>
 */
public class LargestBstSubtree {

    public class NodeValue {

        public int maxNode = 0;
        public int minNode = 0;
        public int maxSize = 0;

        NodeValue(int maxNode, int minNode, int maxSize) {
            this.maxNode = maxNode;
            this.maxNode = minNode;
            this.maxSize = maxSize;
        }
    }

    public class Solution {

        /**
         * Helper method to find the largest BST subtree in a binary tree.
         * This method traverses the tree in a post-order manner and returns a NodeValue
         * object
         * containing information about the subtree at each node.
         *
         * @param root The root node of the current subtree being processed
         * @return NodeValue object containing:
         *         - minNode: minimum value in the valid BST subtree
         *         - maxNode: maximum value in the valid BST subtree
         *         - maxSize: size of the largest valid BST subtree found
         *         For invalid BST subtrees, returns boundary values to maintain BST
         *         property checking
         */
        private NodeValue helper(Node root) {

            if (root == null) {
                return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
            }

            NodeValue left = helper(root.left);
            NodeValue right = helper(root.right);

            if (left.maxNode < root.key && root.key < right.minNode) {
                return new NodeValue(Math.min(root.key, left.minNode), Math.max(root.key, right.maxNode),
                        left.maxSize + right.maxSize + 1);
            }

            return new NodeValue(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.maxSize, right.maxSize));
        }

        public int largestBstTree(Node root) {
            return helper(root).maxSize;
        }
    }

}
