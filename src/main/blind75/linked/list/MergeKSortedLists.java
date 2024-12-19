package main.blind75.linked.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 23. Merge k Sorted Lists Hard Topics Companies You are given an array of k linked-lists lists, each linked-list is
 * sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * Example 1: Input: lists = [[1,4,5],[1,3,4],[2,6]] Output: [1,1,2,3,4,4,5,6] Explanation: The linked-lists are: [
 * 1->4->5, 1->3->4, 2->6 ] merging them into one sorted list: 1->1->2->3->4->4->5->6 Example 2:
 *
 * Input: lists = [] Output: [] Example 3:
 *
 * Input: lists = [[]] Output: []
 *
 * Constraints:
 *
 * k == lists.length 0 <= k <= 104 0 <= lists[i].length <= 500 -104 <= lists[i][j] <= 104 lists[i] is sorted in
 * ascending order. The sum of lists[i].length will not exceed 104.
 */
public class MergeKSortedLists {

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

	/**
	 * Time & Space Complexity Time complexity: O(nlogn) Space complexity: O(n)
	 */
	static class BruteForce {
		public ListNode mergeKLists(ListNode[] lists) {
			List<Integer> nodes = new ArrayList<>();
			for (ListNode lst : lists) {
				while (lst != null) {
					nodes.add(lst.val);
					lst = lst.next;
				}
			}
			Collections.sort(nodes);

			ListNode res = new ListNode(0);
			ListNode cur = res;
			for (int node : nodes) {
				cur.next = new ListNode(node);
				cur = cur.next;
			}
			return res.next;
		}
	}

	/**
	 * Time complexity: O(n∗k) Space complexity: O(1) Where k is the total number of lists and n is the total number of
	 * nodes across k lists.
	 */
	static class OptimalI {
		public ListNode mergeKLists(ListNode[] lists) {
			ListNode res = new ListNode(0);
			ListNode cur = res;

			while (true) {
				int minNode = -1;
				for (int i = 0; i < lists.length; i++) {
					if (lists[i] == null) {
						continue;
					}
					if (minNode == -1 || lists[minNode].val > lists[i].val) {
						minNode = i;
					}
				}

				if (minNode == -1) {
					break;
				}
				cur.next = lists[minNode];
				lists[minNode] = lists[minNode].next;
				cur = cur.next;
			}

			return res.next;
		}
	}

	/**
	 * Time complexity: O(n∗k) Space complexity: O(1) Where k is the total number of lists and n is the total number of
	 * nodes across k lists.
	 */
	static class OptimalII {
		public ListNode mergeKLists(ListNode[] lists) {
			if (lists.length == 0)
				return null;

			for (int i = 1; i < lists.length; i++) {
				lists[i] = merge(lists[i], lists[i - 1]);
			}
			return lists[lists.length - 1];
		}

		private ListNode merge(ListNode l1, ListNode l2) {
			ListNode dummy = new ListNode(0);
			ListNode curr = dummy;

			while (l1 != null && l2 != null) {
				if (l1.val <= l2.val) {
					curr.next = l1;
					l1 = l1.next;
				} else {
					curr.next = l2;
					l2 = l2.next;
				}

				curr = curr.next;
			}

			if (l1 != null) {
				curr.next = l1;
			} else {
				curr.next = l2;
			}

			return dummy.next;
		}
	}

	/**
	 * Time complexity: O(nlogk) Space complexity: O(k) Where k is the total number of lists and n is the total number
	 * of nodes across k lists.
	 */
	static class Best {
		public ListNode mergeKLists(ListNode[] lists) {
			// Handle edge cases
			if (lists == null || lists.length == 0)
				return null;

			// Create min heap prioritizing nodes with smaller values
			PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

			// Add first node from each list to the heap
			for (ListNode list : lists) {
				if (list != null) {
					minHeap.offer(list);
				}
			}

			ListNode dummy = new ListNode(0);
			ListNode current = dummy;

			// Process nodes until heap is empty
			while (!minHeap.isEmpty()) {
				// Get the node with minimum value
				ListNode node = minHeap.poll();
				current.next = node;
				current = current.next;

				// If there are more nodes in the list, add the next node to heap
				if (node.next != null) {
					minHeap.offer(node.next);
				}
			}

			return dummy.next;
		}
	}

}
