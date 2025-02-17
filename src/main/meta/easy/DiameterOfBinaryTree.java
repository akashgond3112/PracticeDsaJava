package main.meta.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 543. Diameter of Binary Tree Easy Topics Companies Given the root of a binary tree, return the
 * length of the diameter of the tree.
 *
 * <p>The diameter of a binary tree is the length of the longest path between any two nodes in a
 * tree. This path may or may not pass through the root.
 *
 * <p>The length of a path between two nodes is represented by the number of edges between them.
 *
 * <p>Example 1:
 *
 * <p>Input: root = [1,2,3,4,5] Output: 3 Explanation: 3 is the length of the path [4,2,1,3] or
 * [5,2,1,3]. Example 2:
 *
 * <p>Input: root = [1,2] Output: 1
 *
 * <p>Constraints:
 *
 * <p>The number of nodes in the tree is in the range [1, 104]. -100 <= Node.val <= 100
 */
class DiameterOfBinaryTree {

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

  static class Solution {
    /**
     * Computes the diameter of a binary tree using an **iterative** DFS approach.
     *
     * <p>Time Complexity: **O(N)** - Each node is **pushed and popped** once from the stack → O(N).
     * - The map stores depths, ensuring each node is processed only once.
     *
     * <p>Space Complexity: **O(N)** - The worst case occurs when the tree is **skewed**, requiring
     * O(N) stack space. - The map stores depths for each node, leading to O(N) space usage.
     *
     * @param root The root of the binary tree.
     * @return The diameter of the binary tree.
     */
    public int diameterOfBinaryTree(TreeNode root) {
      Map<TreeNode, Integer> map = new HashMap<>();
      Stack<TreeNode> stack = new Stack<>();
      int diameter = 0;

      if (root != null) {
        stack.push(root);
      }

      while (!stack.isEmpty()) {
        TreeNode node = stack.peek();

        if (node.left != null && !map.containsKey(node.left)) {
          stack.push(node.left);
        } else if (node.right != null && !map.containsKey(node.right)) {
          stack.push(node.right);
        } else {
          stack.pop();
          int leftDepth = map.getOrDefault(node.left, 0);
          int rightDepth = map.getOrDefault(node.right, 0);
          map.put(node, 1 + Math.max(leftDepth, rightDepth));
          diameter = Math.max(diameter, leftDepth + rightDepth);
        }
      }
      return diameter;
    }
  }

  static class SolutionUsingRecursion {
    /**
     * Computes the diameter of a binary tree using a **recursive DFS** approach.
     *
     * <p>Time Complexity: **O(N)** - Each node is visited **once**, and the height function runs in
     * O(1) per node.
     *
     * <p>Space Complexity: **O(N)** (worst case) - The recursion call stack requires **O(H)**
     * space, where H is the height of the tree. - For a **balanced tree**, H = O(log N) → O(log N)
     * space. - For a **skewed tree**, H = O(N) → O(N) space.
     *
     * @param node The root node of the binary tree.
     * @param max The array storing the maximum diameter found.
     * @return The computed diameter of the binary tree.
     */
    public static int diameterOfABinaryTree(TreeNode node, int[] max) {
      int[] diameter = new int[1];
      height(node, diameter);
      return diameter[0];
    }

    /**
     * Helper function to compute height while updating diameter.
     *
     * @param node The current node.
     * @param max The array storing the maximum diameter found.
     * @return Height of the current node.
     */
    private static int height(TreeNode node, int[] max) {
      if (node == null) return 0;

      int left = height(node.left, max);
      int right = height(node.right, max);

      max[0] = Math.max(max[0], left + right);

      return 1 + Math.max(left, right);
    }
  }

  public static void main(String[] args) {
    System.out.println("Hello");
  }
}
