package main.meta.easy;

import java.util.*;

/**
 *
 *
 * <pre>
 * 234. Palindrome Linked List
 * Easy
 * Topics
 * Companies
 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,2,1]
 * Output: true
 * Example 2:
 *
 *
 * Input: head = [1,2]
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [1, 105].
 * 0 <= Node.val <= 9
 *
 *
 * Follow up: Could you do it in O(n) time and O(1) space?
 * Seen this question in a real interview before?
 * 1/5
 * Yes
 * No
 * Accepted
 * 2.3M
 * Submissions
 * 4.2M
 * Acceptance Rate
 * 55.2%
 * Topics
 * Linked List
 * Two Pointers
 * Stack
 * Recursion
 * </pre>
 */
public class PalindromeLinkedList {
  /**
   * Palindrome Linked List
   *
   * <p>Problem: Given the head of a singly linked list, determine if it is a palindrome. A
   * palindrome is a sequence that reads the same forward and backward.
   *
   * <p>This class presents two approaches to solve the problem: 1. Brute Force approach using a
   * stack 2. Optimized approach using fast/slow pointers and list reversal
   */
  public static class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  /**
   * Brute Force solution using a stack.
   *
   * <p>Approach: 1. Push all elements of the linked list onto a stack 2. Traverse the linked list
   * again and compare each node's value with the top of the stack 3. If all values match, it's a
   * palindrome
   *
   * <p>Time Complexity: O(n) where n is the number of nodes in the linked list Space Complexity:
   * O(n) for storing all nodes in the stack
   */
  static class SolutionBruteForce {
    /**
     * Determines if a linked list is a palindrome using stack approach.
     *
     * @param head The head of the linked list
     * @return true if the linked list is a palindrome, false otherwise
     */
    public boolean isPalindrome(ListNode head) {
      Stack<Integer> stack = new Stack<>();
      ListNode temp = head;

      // Push all values onto the stack
      while (temp != null) {
        stack.push(temp.val);
        temp = temp.next;
      }

      // Compare each node with the top of the stack (reverse order)
      temp = head;
      while (temp != null) {
        if (stack.peek() != temp.val) return false;
        temp = temp.next;
        stack.pop();
      }

      return true;
    }
  }

  /**
   * Helper method to reverse a linked list.
   *
   * <p>Approach: Recursive reversal of the linked list
   *
   * <p>Time Complexity: O(n) Space Complexity: O(n) due to recursive call stack
   *
   * @param head The head of the linked list to reverse
   * @return The new head of the reversed linked list
   */
  public static ListNode reverseLinkedList(ListNode head) {
    // Base case: empty list or single node
    if (head == null || head.next == null) {
      return head;
    }

    // Recursive case: reverse the rest of the list
    ListNode newHead = reverseLinkedList(head.next);

    // Reverse the current node
    head.next.next = head;
    head.next = null;

    return newHead;
  }

  /**
   * Optimized solution using fast and slow pointers.
   *
   * <p>Approach: 1. Find the middle of the linked list using fast and slow pointers 2. Reverse the
   * second half of the linked list 3. Compare values of the first half with the reversed second
   * half 4. Restore the original list structure by reversing the second half again
   *
   * <p>Time Complexity: O(n) where n is the number of nodes in the linked list Space Complexity:
   * O(1) as we only use a constant amount of extra space
   */
  static class SolutionBetter {
    /**
     * Determines if a linked list is a palindrome using the two-pointer approach.
     *
     * @param head The head of the linked list
     * @return true if the linked list is a palindrome, false otherwise
     */
    public boolean isPalindrome(ListNode head) {
      // Edge case: empty list or single node is always a palindrome
      if (head == null || head.next == null) {
        return true;
      }

      // Find the middle of the linked list
      ListNode fast = head;
      ListNode slow = head;
      while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
      }

      // Reverse the second half of the linked list
      ListNode secondHalfHead = reverseLinkedList(slow.next);

      // Compare the first half with the reversed second half
      ListNode firstHalf = head;
      ListNode secondHalf = secondHalfHead;
      boolean isPalindrome = true;

      while (secondHalf != null) {
        if (firstHalf.val != secondHalf.val) {
          isPalindrome = false;
          break;
        }
        firstHalf = firstHalf.next;
        secondHalf = secondHalf.next;
      }

      // Restore the original list by reversing the second half again
      slow.next = reverseLinkedList(secondHalfHead);

      return isPalindrome;
    }
  }

  /**
   * Main method for testing the solutions.
   *
   * <p>Example 1: Input: head = [1,2,2,1] Output: true
   *
   * <p>Example 2: Input: head = [1,2] Output: false
   */
  public static void main(String[] args) {
    // Test cases can be implemented here
  }
}
