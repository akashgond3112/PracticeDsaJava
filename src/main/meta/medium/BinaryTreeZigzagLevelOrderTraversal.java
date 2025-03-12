package main.meta.medium;

import java.util.*;

/**
 *
 *
 * <pre>
 * 103. Binary Tree Zigzag Level Order Traversal
 * Solved
 * Medium
 * Topics
 * Companies
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
 * Example 2:
 *
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 * Seen this question in a real interview before?
 * 1/5
 * Yes
 * No
 * Accepted
 * 1.4M
 * Submissions
 * 2.3M
 * Acceptance Rate
 * 61.0%
 * Topics
 * Tree
 * Breadth-First Search
 * Binary Tree
 * </pre>
 */
public class BinaryTreeZigzagLevelOrderTraversal {
  /** Definition for a binary tree node. */
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  /**
   * Binary Tree Zigzag Level Order Traversal
   *
   * <p>Problem: Given a binary tree, return the zigzag level order traversal of its nodes' values.
   * Zigzag traversal means: - First level: left to right - Second level: right to left - Third
   * level: left to right And so on, alternating between levels.
   *
   * <p>Solution Approach: - Use a standard BFS (Breadth-First Search) traversal with a queue - Keep
   * track of the current level's traversal direction using a boolean flag - For levels that should
   * be traversed right-to-left, reverse the collected values
   *
   * <p>Time Complexity: O(n) where n is the number of nodes in the tree - Each node is processed
   * exactly once - The reversal operation for each level is at most O(n/2), which simplifies to
   * O(n)
   *
   * <p>Space Complexity: O(n) where n is the number of nodes in the tree - The queue will contain
   * at most n/2 nodes (at the lowest level in a balanced tree) - The result list will contain all n
   * node values
   */
  public static class Solution {
    /**
     * Returns the zigzag level order traversal of the binary tree nodes' values.
     *
     * @param root The root node of the binary tree
     * @return List of lists containing node values in zigzag level order
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
      List<List<Integer>> result = new ArrayList<>();
      if (root == null) {
        return result;
      }

      // Flag to track the current level's traversal direction
      boolean leftToRight = true;

      // Queue for BFS traversal
      Queue<TreeNode> queue = new LinkedList<>();
      queue.offer(root);

      while (!queue.isEmpty()) {
        int size = queue.size();
        List<Integer> vector = new ArrayList<>(size);

        // Process all nodes at the current level
        for (int i = 0; i < size; i++) {
          TreeNode node = queue.peek();
          queue.poll();
          assert node != null;

          // Add current node's value to the level list
          vector.add(node.val);

          // Add children to queue for next level processing
          if (node.left != null) {
            queue.offer(node.left);
          }
          if (node.right != null) {
            queue.offer(node.right);
          }
        }

        // For right-to-left levels, reverse the collected values
        if (!leftToRight) Collections.reverse(vector);

        // Toggle direction for the next level
        leftToRight = !leftToRight;

        // Add current level's values to result
        result.add(vector);
      }

      return result;
    }
  }

  /** Main method for testing the solution. */
  public static void main(String[] args) {
    // Example usage:
    // TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new
    // TreeNode(7)));
    // Solution solution = new Solution();
    // List<List<Integer>> result = solution.zigzagLevelOrder(root);
    // Expected output: [[3], [20, 9], [15, 7]]
  }
}
