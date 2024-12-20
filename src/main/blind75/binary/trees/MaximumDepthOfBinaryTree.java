package main.blind75.binary.trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 104. Maximum Depth of Binary Tree Easy Topics Companies Given the root of a binary tree, return
 * its maximum depth.
 * 
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node
 * down to the farthest leaf node.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [3,9,20,null,null,15,7] Output: 3 Example 2:
 * 
 * Input: root = [1,null,2] Output: 2
 * 
 * 
 * Constraints:
 * 
 * The number of nodes in the tree is in the range [0, 104]. -100 <= Node.val <= 100
 */
public class MaximumDepthOfBinaryTree {

    /*
     * Time complexity: O(n) Space complexity: O(h) Best Case (balanced tree): O(log(n)) Worst Case
     * (degenerate tree): O(n) Where n is the number of nodes in the tree and h is the height of the
     * tree.
     * 
     */
    public class SolutionUsingRecursiveDFS {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }

            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
    }

    /*
     * Time complexity: O(n) Space complexity: O(n) Where n is the number of nodes in the tree.
     * 
     */
    public class SolutionUsingIterativeDFS {
        public int maxDepth(TreeNode root) {
            Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
            stack.push(new Pair<>(root, 1));
            int res = 0;

            while (!stack.isEmpty()) {
                Pair<TreeNode, Integer> current = stack.pop();
                TreeNode node = current.getKey();
                int depth = current.getValue();

                if (node != null) {
                    res = Math.max(res, depth);
                    stack.push(new Pair<>(node.left, depth + 1));
                    stack.push(new Pair<>(node.right, depth + 1));
                }
            }
            return res;
        }
    }

    /*
     * Time complexity: O(n) Space complexity: O(n) Where n is the number of nodes in the tree.
     * 
     */
    public class SolutionUsingBFS {
        public int maxDepth(TreeNode root) {
            Queue<TreeNode> q = new LinkedList<>();
            if (root != null) {
                q.add(root);
            }

            int level = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = q.poll();
                    if (node.left != null) {
                        q.add(node.left);
                    }
                    if (node.right != null) {
                        q.add(node.right);
                    }
                }
                level++;
            }
            return level;
        }
    }

}
