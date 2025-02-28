package main.meta.medium;

/**
 * <pre>
 *     19. Remove Nth Node From End of List
 * Solved
 * Medium
 * Topics
 * Companies
 * Hint
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 *
 * Example 2:
 * Input: head = [1], n = 1
 * Output: []
 *
 * Example 3:
 * Input: head = [1,2], n = 1
 * Output: [1]
 *
 * Constraints:
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * </pre>*/

public class RemoveNthNodeFromEndOfList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

    /**
     * Solution for removing the nth node from the end of a linked list.
     *
     * Approach:
     * - The original solution has an infinite loop and logic errors
     * - The corrected solution uses a two-pass approach:
     *   1. First pass: Count the total number of nodes
     *   2. Second pass: Remove the (length-n+1)th node from the beginning
     * - Special handling for removing the head node
     *
     * Time Complexity: O(L) where L is the length of the linked list
     *   - We traverse the list twice, each taking O(L) time
     *
     * Space Complexity: O(1) - only using constant extra space
     */
    static class Solution {
        /**
         * Removes the nth node from the end of the linked list.
         *
         * @param head The head of the linked list
         * @param n The position from the end to remove (1-indexed)
         * @return The head of the modified linked list
         */
        public ListNode removeNthFromEnd(ListNode head, int n) {
            // Edge case: empty list
            if (head == null) {
                return null;
            }

            // First pass: count the number of nodes
            int length = 0;
            ListNode current = head;
            while (current != null) {
                length++;
                current = current.next;
            }

            // Calculate position from beginning (0-indexed)
            int positionFromBeginning = length - n;

            // Edge case: removing the head node
            if (positionFromBeginning == 0) {
                return head.next;
            }

            // Second pass: find the node before the one to be removed
            current = head;
            for (int i = 0; i < positionFromBeginning - 1; i++) {
                current = current.next;
            }

            // Remove the nth node
            current.next = current.next.next;

            return head;
        }
    }

    /**
     * Alternative one-pass solution using two pointers.
     *
     * Approach:
     * - Use a fast and slow pointer
     * - Move fast pointer n nodes ahead
     * - Then move both pointers until fast reaches the end
     * - Remove the node after slow pointer
     *
     * Time Complexity: O(L) where L is the length of the linked list
     *   - Only requires a single pass through the list
     *
     * Space Complexity: O(1) - only using constant extra space
     */
    static class OptimalSolution {
        /**
         * Removes the nth node from the end of the linked list in one pass.
         *
         * @param head The head of the linked list
         * @param n The position from the end to remove (1-indexed)
         * @return The head of the modified linked list
         */
        public ListNode removeNthFromEnd(ListNode head, int n) {
            // Create a dummy node to handle edge cases like removing the head
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode fast = dummy;
            ListNode slow = dummy;

            // Move fast pointer n nodes ahead
            for (int i = 0; i <= n; i++) {
                fast = fast.next;
            }

            // Move both pointers until fast reaches the end
            while (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }

            // Remove the nth node from the end
            slow.next = slow.next.next;

            return dummy.next;
        }
    }


}
