package main.meta.medium;

import java.util.*;

/**
 *
 *
 * <pre>
 * 515. Find Largest Value in Each Tree Row
 * Medium
 * Topics
 * Companies
 * Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
 *
 *
 * Example 1:
 *
 * Input: root = [1,3,2,5,3,null,9]
 * Output: [1,3,9]
 * Example 2:
 * Input: root = [1,2,3]
 * Output: [1,3]
 *
 *
 * Constraints:
 * The number of nodes in the tree will be in the range [0, 104].
 * -231 <= Node.val <= 231 - 1
 * Topics
 * Tree
 * Depth-First Search
 * Breadth-First Search
 * Binary Tree
 * </pre>
 */
public class FindLargestValueInEachTreeRow {

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

  /** Solution class containing the algorithm to find the largest value in each tree row. */
  static class Solution {
    /**
     * Finds the largest value in each level of a binary tree using breadth-first search.
     *
     * <p>Algorithm: 1. Perform a level-order traversal of the tree. 2. For each level, find the
     * maximum value among all nodes at that level. 3. Add the maximum value to the result list.
     *
     * <p>Time Complexity: O(n) where n is the number of nodes in the tree. Each node is processed
     * exactly once.
     *
     * <p>Space Complexity: O(w) where w is the maximum width of the tree. In the worst case, the
     * queue might contain all nodes at the widest level of the tree.
     *
     * @param root The root of the binary tree
     * @return A list containing the largest value in each row of the tree
     */
    public List<Integer> largestValues(TreeNode root) {
      List<Integer> result = new ArrayList<>();
      if (root == null) return result;

      Queue<TreeNode> queue = new LinkedList<>();
      queue.offer(root);

      while (!queue.isEmpty()) {
        int levelSize = queue.size();
        int curMax = Integer.MIN_VALUE;

        // Process all nodes at current level
        for (int i = 0; i < levelSize; i++) {
          TreeNode node = queue.poll();

          // Update maximum value for current level
          curMax = Math.max(curMax, node.val);

          // Add children to queue for next level processing
          if (node.left != null) {
            queue.offer(node.left);
          }
          if (node.right != null) {
            queue.offer(node.right);
          }
        }

        // Add maximum value of current level to result
        result.add(curMax);
      }

      return result;
    }
  }

  /**
   * Main method for testing the solution.
   *
   * <p>Tests the solution with multiple test cases: 1. A basic binary tree with multiple levels 2.
   * A tree with negative values 3. A tree with a single node 4. An unbalanced tree with deeper
   * right side
   *
   * @param args Command line arguments (not used)
   */
  public static void main(String[] args) {
    Solution solution = new Solution();

    // Test Case 1: Regular binary tree
    // Tree structure:
    //       1
    //      / \
    //     3   2
    //    / \   \
    //   5   3   9
    TreeNode root1 = new TreeNode(1);
    root1.left = new TreeNode(3);
    root1.right = new TreeNode(2);
    root1.left.left = new TreeNode(5);
    root1.left.right = new TreeNode(3);
    root1.right.right = new TreeNode(9);

    List<Integer> result1 = solution.largestValues(root1);
    System.out.println("Test Case 1 - Regular tree:");
    System.out.println("Expected output: [1, 3, 9]");
    System.out.println("Actual output: " + result1);
    System.out.println();

    // Test Case 2: Tree with negative values
    // Tree structure:
    //      -10
    //      / \
    //     -5  -3
    //    /    / \
    //   -1   -2  -9
    TreeNode root2 = new TreeNode(-10);
    root2.left = new TreeNode(-5);
    root2.right = new TreeNode(-3);
    root2.left.left = new TreeNode(-1);
    root2.right.left = new TreeNode(-2);
    root2.right.right = new TreeNode(-9);

    List<Integer> result2 = solution.largestValues(root2);
    System.out.println("Test Case 2 - Tree with negative values:");
    System.out.println("Expected output: [-10, -3, -1]");
    System.out.println("Actual output: " + result2);
    System.out.println();

    // Test Case 3: Single node tree
    TreeNode root3 = new TreeNode(42);

    List<Integer> result3 = solution.largestValues(root3);
    System.out.println("Test Case 3 - Single node tree:");
    System.out.println("Expected output: [42]");
    System.out.println("Actual output: " + result3);
    System.out.println();

    // Test Case 4: Unbalanced tree - right side deeper
    // Tree structure:
    //       7
    //      / \
    //     4   8
    //        / \
    //       6  10
    //           \
    //           11
    TreeNode root4 = new TreeNode(7);
    root4.left = new TreeNode(4);
    root4.right = new TreeNode(8);
    root4.right.left = new TreeNode(6);
    root4.right.right = new TreeNode(10);
    root4.right.right.right = new TreeNode(11);

    List<Integer> result4 = solution.largestValues(root4);
    System.out.println("Test Case 4 - Unbalanced tree (right side deeper):");
    System.out.println("Expected output: [7, 8, 10, 11]");
    System.out.println("Actual output: " + result4);
  }
}
