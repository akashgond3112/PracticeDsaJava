package main.meta.easy;

/**
 * <pre>
 * 938. Range Sum of BST Easy Topics Companies Given the root node of a binary search tree and two
 * integers low and high, return the sum of values of all nodes with a value in the inclusive range
 * [low, high].
 *
 * Example 1:
 * Input: root = [10,5,15,3,7,null,18], low = 7, high = 15 Output: 32 Explanation: Nodes 7, 10, and
 * 15 are in the range [7, 15]. 7 + 10 + 15 = 32. Example 2:
 *
 * Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10 Output: 23 Explanation: Nodes 6,
 * 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.
 *
 * Constraints:
 * 
 * The number of nodes in the tree is in the range [1, 2 * 104]. 1 <= Node.val <= 105 1 <= low <=
 * high <= 105 All Node.val are unique.
 * </pre>
 */
public class RangeSumOfBST {

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

    static class SolutionBruteForce {
        /**
         * This method calculates the sum of all nodes' values within the given range [low, high].
         * It uses a brute force approach by traversing the entire tree and checking each node's
         * value.
         *
         * @param root the root node of the binary search tree
         * 
         * @param low the lower bound of the range
         * 
         * @param high the upper bound of the range
         * 
         * @return the sum of values of all nodes within the range [low, high]
         * Time Complexity : O(n), where n is the number of nodes in the tree
         * Space Complexity: O(n), where n is the number of nodes in the tree
         */
        public int rangeSumBST(TreeNode root, int low, int high) {
            int total = 0;
            if (root == null) {
                return total;
            }

            if (root.val >= low && root.val <= high) {
                total += root.val;
            }

            total += rangeSumBST(root.left, low, high);
            total += rangeSumBST(root.right, low, high);

            return total;
        }
    }


    /**
     * This method calculates the sum of all nodes' values within the given range [low, high]. It
     * uses an optimized approach by traversing the tree only once and checking each node's value.
     * The optimized approach avoids unnecessary recursive calls by checking the value of the
     * current node. 
     * Time Complexity: O(n), where n is the number of nodes in the tree 
     * Space Complexity: O(n), where n is the number of nodes in the tree
     */
	static class SolutionOptimized {
        public int rangeSumBST(TreeNode root, int low, int high) {

            if (root == null) {
                return 0;
            }

            int total = 0;

            if (root.val > low) {
                total += rangeSumBST(root.left, low, high);
            }

            if (root.val >= low && root.val <= high) {
                total += root.val;

            }

            if (root.val < high) {
                total += rangeSumBST(root.right, low, high);
            }

            return total;

        }
    }


}
