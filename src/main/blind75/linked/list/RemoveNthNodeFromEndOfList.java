package main.blind75.linked.list;

import java.util.ArrayList;
import java.util.List;

/**
 * 19. Remove Nth Node From End of List Solved Medium Topics Companies Hint Given the head of a linked list, remove the
 * nth node from the end of the list and return its head.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5], n = 2 Output: [1,2,3,5] Example 2:
 *
 * Input: head = [1], n = 1 Output: [] Example 3:
 *
 * Input: head = [1,2], n = 1 Output: [1]
 *
 * Constraints:
 *
 * The number of nodes in the list is sz. 1 <= sz <= 30 0 <= Node.val <= 100 1 <= n <= sz
 *
 * Follow up: Could you do this in one pass?
 */
public class RemoveNthNodeFromEndOfList {

	//	 * Definition for singly-linked list.
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
	 * Time & Space Complexity Time complexity: O(N) Space complexity: O(N)
	 */
	public static class BruteForce {
		public ListNode removeNthFromEnd(ListNode head, int n) {
			List<ListNode> nodes = new ArrayList<>();
			ListNode cur = head;
			while (cur != null) {
				nodes.add(cur);
				cur = cur.next;
			}

			int removeIndex = nodes.size() - n;
			if (removeIndex == 0) {
				return head.next;
			}

			nodes.get(removeIndex - 1).next = nodes.get(removeIndex).next;
			return head;
		}
	}

	/**
	 * Time & Space Complexity Time complexity: O(N) Space complexity: O(1)
	 */
	public static class TwoPass {
		public ListNode removeNthFromEnd(ListNode head, int n) {
			int N = 0;
			ListNode cur = head;
			while (cur != null) {
				N++;
				cur = cur.next;
			}

			int removeIndex = N - n;
			if (removeIndex == 0) {
				return head.next;
			}

			cur = head;
			for (int i = 0; i < N - 1; i++) {
				if ((i + 1) == removeIndex) {
					cur.next = cur.next.next;
					break;
				}
				cur = cur.next;
			}
			return head;
		}
	}

	/**
	 * Time & Space Complexity Time complexity: O(N) Space complexity: O(1)
	 */
	public static class OnePass {
		public ListNode removeNthFromEnd(ListNode head, int n) {
			ListNode dummy = new ListNode(0, head);
			ListNode left = dummy;
			ListNode right = head;

			while (n > 0) {
				right = right.next;
				n--;
			}

			while (right != null) {
				left = left.next;
				right = right.next;
			}

			left.next = left.next.next;
			return dummy.next;
		}
	}
}
