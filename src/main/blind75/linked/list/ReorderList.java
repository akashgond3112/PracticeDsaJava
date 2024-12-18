package main.blind75.linked.list;

import java.util.ArrayList;
import java.util.List;

/**
 * 143. Reorder List Medium Topics Companies You are given the head of a singly linked-list. The list can be represented
 * as:
 *
 * L0 → L1 → … → Ln - 1 → Ln Reorder the list to be on the following form:
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → … You may not modify the values in the list's nodes. Only nodes themselves may
 * be changed.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4] Output: [1,4,2,3] Example 2:
 *
 * Input: head = [1,2,3,4,5] Output: [1,5,2,4,3]
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [1, 5 * 104]. 1 <= Node.val <= 1000
 */
public class ReorderList {

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
	 * Time & Space Complexity Time complexity: O(n) Space complexity: O(n)
	 */
	static class BruteForce {
		public void reorderList(ListNode head) {
			if (head == null) {
				return;
			}

			List<ListNode> nodes = new ArrayList<>();
			ListNode cur = head;
			while (cur != null) {
				nodes.add(cur);
				cur = cur.next;
			}

			int i = 0, j = nodes.size() - 1;
			while (i < j) {
				nodes.get(i).next = nodes.get(j);
				i++;
				if (i >= j) {
					break;
				}
				nodes.get(j).next = nodes.get(i);
				j--;
			}

			nodes.get(i).next = null;
		}
	}

	/**
	 * Time & Space Complexity Time complexity: O(n) Space complexity: O(n)
	 */
	static class UsingRecursion {
		public void reorderList(ListNode head) {
			head = rec(head, head.next);
		}

		private ListNode rec(ListNode root, ListNode cur) {
			if (cur == null) {
				return root;
			}
			root = rec(root, cur.next);
			if (root == null) {
				return null;
			}
			ListNode tmp = null;
			if (root == cur || root.next == cur) {
				cur.next = null;
			} else {
				tmp = root.next;
				root.next = cur;
				cur.next = tmp;
			}
			return tmp;
		}
	}

	/**
	 * Time & Space Complexity Time complexity: O(n) Space complexity: O(1)
	 */
	static class Optimal {
		public void reorderList(ListNode head) {
			ListNode slow = head;
			ListNode fast = head.next;
			while (fast != null && fast.next != null) {
				slow = slow.next;
				fast = fast.next.next;
			}

			ListNode second = slow.next;
			ListNode prev = slow.next = null;
			while (second != null) {
				ListNode tmp = second.next;
				second.next = prev;
				prev = second;
				second = tmp;
			}

			ListNode first = head;
			second = prev;
			while (second != null) {
				ListNode tmp1 = first.next;
				ListNode tmp2 = second.next;
				first.next = second;
				second.next = tmp1;
				first = tmp1;
				second = tmp2;
			}
		}
	}
}
