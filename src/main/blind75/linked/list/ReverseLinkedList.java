package main.blind75.linked.list;

import main.dsa.linear.LinkedList.SinglyLinkedList.SinglyLinkedList;

import java.util.Stack;

/**
 * 206. Reverse Linked List Easy Topics Companies Given the head of a singly linked list, reverse the list, and return
 * the reversed list.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5] Output: [5,4,3,2,1] Example 2:
 *
 * Input: head = [1,2] Output: [2,1] Example 3:
 *
 * Input: head = [] Output: []
 *
 * Constraints:
 *
 * The number of nodes in the list is the range [0, 5000]. -5000 <= Node.val <= 5000
 *
 *
 * Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList {


	//	 Definition for singly-linked list.
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

	static class BruteForce {
		/**
		 * Time & Space Complexity Time complexity: O(2n) Space complexity: O(n)
		 */
		public ListNode reverseListUsingStack(ListNode head) {
			if (head == null)
				return null;

			ListNode temp = head;
			Stack<Integer> stack = new Stack<>();

			// Push all node values onto the stack
			while (temp != null) {
				stack.push(temp.val);
				temp = temp.next;
			}

			// Reset temp to head and replace values using stack
			temp = head;
			while (!stack.isEmpty()) {
				temp.val = stack.pop();
				temp = temp.next;
			}

			// Return the head of the modified list
			return head;
		}

	}

	static class optimal {
		/**
		 * Time & Space Complexity Time complexity: O(n) Space complexity: O(1)
		 */
		public ListNode reverseList(ListNode head) {

			ListNode temp = head;
			ListNode prev = null;

			while (temp != null) {
				ListNode front = temp.next;
				temp.next = prev;
				prev = temp;
				temp = front;
			}
			return prev;
		}

		/**
		 * Time & Space Complexity Time complexity: O(n) Space complexity: O(n)
		 */
		public ListNode reverseListUsingRecursion(ListNode head) {
			if (head == null || head.next == null) {
				return head;
			}

			ListNode newHead = reverseListUsingRecursion(head.next);
			head.next.next = head;
			head.next = null;

			return newHead;
		}
	}
}
