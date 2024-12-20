package main.blind75.binary.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 226. Invert Binary Tree Easy Topics Companies Given the root of a binary tree, invert the tree,
 * and return its root.
 * 
 * Example 1:
 * 
 * Input: root = [4,2,7,1,3,6,9] Output: [4,7,2,9,6,3,1] Example 2:
 * 
 * 
 * Input: root = [2,1,3] Output: [2,3,1] Example 3:
 * 
 * Input: root = [] Output: []
 * 
 * 
 * Constraints:
 * 
 * The number of nodes in the tree is in the range [0, 100]. -100 <= Node.val <= 100
 */
public class InvertBinaryTree {

    /*
     * Time complexity: O(n) Space complexity: O(n)
     */
    public class SolutionUsingBFS {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            return root;
        }
    }

    /*
     * Time complexity: O(n) Space complexity: O(n)
     */
    class SolutionUsingDFS {
        public TreeNode invertTree(TreeNode root) {
            if (root == null)
                return null;

            TreeNode node = new TreeNode(root.val);

            node.right = invertTree(root.left);
            node.left = invertTree(root.right);

            return node;
        }
    }

    public static void main(String[] args) {
        // Example 1
        TreeNode root = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7, new TreeNode(6), new TreeNode(9)));
        TreeNode invertedRoot = new InvertBinaryTree().new SolutionUsingBFS().invertTree(root);
        System.out.println(invertedRoot.val); // 4
        System.out.println(invertedRoot.left.val); // 7
        System.out.println(invertedRoot.right.val); // 2
        System.out.println(invertedRoot.left.left.val); // 9
        System.out.println(invertedRoot.left.right.val); // 6
        System.out.println(invertedRoot.right.left.val); // 3
        System.out.println(invertedRoot.right.right.val); // 1

        // Example 2
        root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        invertedRoot = new InvertBinaryTree().new SolutionUsingBFS().invertTree(root);
        System.out.println(invertedRoot.val); // 2
        System.out.println(invertedRoot.left.val); // 3
        System.out.println(invertedRoot.right.val); // 1

        // Example 3
        root = null;
        invertedRoot = new InvertBinaryTree().new SolutionUsingBFS().invertTree(root);
        System.out.println(invertedRoot); // null
    }

}
