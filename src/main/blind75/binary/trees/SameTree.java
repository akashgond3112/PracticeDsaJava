package main.blind75.binary.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 100. Same Tree Easy Topics Companies Given the roots of two binary trees p and q, write a
 * function to check if they are the same or not.
 * 
 * Two binary trees are considered the same if they are structurally identical, and the nodes have
 * the same value.
 * 
 * Example 1: Input: p = [1,2,3], q = [1,2,3] Output: true Example 2: Input: p = [1,2], q =
 * [1,null,2] Output: false Example 3: Input: p = [1,2,1], q = [1,1,2] Output: false
 * 
 * Constraints:
 * 
 * The number of nodes in both trees is in the range [0, 100]. -104 <= Node.val <= 104
 */
public class SameTree {

    /*
     * Time complexity: O(n) Space complexity: O(h) Best Case (balanced tree): O(log(n)) Worst Case
     * (degenerate tree): O(n) Where n is the number of nodes in the tree and h is the height of the
     * tree.
     */
    class SolutionUsingDFS {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            }
            if (p != null && q != null && p.val == q.val) {
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            } else {
                return false;
            }
        }
    }


    /*
     * Time complexity: O(n) Space complexity: O(n)
     */
    public class SolutionUsingBFS {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            Queue<TreeNode> q1 = new LinkedList<>();
            Queue<TreeNode> q2 = new LinkedList<>();
            q1.add(p);
            q2.add(q);

            while (!q1.isEmpty() && !q2.isEmpty()) {
                TreeNode nodeP = q1.poll();
                TreeNode nodeQ = q2.poll();

                if (nodeP == null && nodeQ == null)
                    continue;
                if (nodeP == null || nodeQ == null || nodeP.val != nodeQ.val)
                    return false;

                q1.add(nodeP.left);
                q1.add(nodeP.right);
                q2.add(nodeQ.left);
                q2.add(nodeQ.right);
            }

            return true;
        }
    }

}
