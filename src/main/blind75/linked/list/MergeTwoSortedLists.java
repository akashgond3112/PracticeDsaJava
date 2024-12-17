package main.blind75.linked.list;

import java.util.ArrayList;
import java.util.Collections;

import static main.dsa.linear.LinkedList.SinglyLinkedList.MergeKSortedLists.convertArrToLinkedList;

/**
 * 21. Merge Two Sorted Lists Easy Topics Companies You are given the heads of two sorted linked lists list1 and list2.
 *
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two
 * lists.
 *
 * Return the head of the merged linked list.
 *
 * Example 1:
 * Input: list1 = [1,2,4], list2 = [1,3,4] Output: [1,1,2,3,4,4] Example 2:
 * Input: list1 = [], list2 = [] Output: [] Example 3:
 * Input: list1 = [], list2 = [0] Output: [0]
 *
 *
 * Constraints:
 *
 * The number of nodes in both lists is in the range [0, 50]. -100 <= Node.val <= 100 Both list1 and list2 are sorted in
 * non-decreasing order.
 */
public class MergeTwoSortedLists {


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
	 * Time & Space Complexity Time complexity: O(n1)+O(n2)+O(NLogN)+O(N) Space complexity: O(N) + O(N)
	 */
	static class BruteForce {
		public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
			ArrayList<Integer> data = new ArrayList<>();

			while (list1 != null) {
				data.add(list1.val);
				list1 = list1.next;
			}
			while (list2 != null) {
				data.add(list2.val);
				list2 = list2.next;
			}

			Collections.sort(data);
			return (ListNode) convertArrToLinkedList(data);
		}
	}

	static class Optimal {

		/**
		 * Time complexity: O(n+m) Space complexity: O(n+m) Where n is the length of list1 and m is the length of
		 * list2.
		 */
		public ListNode mergeTwoListsUsingRecursion(ListNode list1, ListNode list2) {
			if (list1 == null) {
				return list2;
			}
			if (list2 == null) {
				return list1;
			}
			if (list1.val <= list2.val) {
				list1.next = mergeTwoListsUsingRecursion(list1.next, list2);
				return list1;
			} else {
				list2.next = mergeTwoListsUsingRecursion(list1, list2.next);
				return list2;
			}
		}

		/**
		 * Time complexity: O(n+m) Space complexity: O(1) Where n is the length of list1 and m is the length of list2.
		 */
		public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
			ListNode dummy = new ListNode(0);
			ListNode node = dummy;

			while (list1 != null && list2 != null) {
				if (list1.val < list2.val) {
					node.next = list1;
					list1 = list1.next;
				} else {
					node.next = list2;
					list2 = list2.next;
				}
				node = node.next;
			}

			if (list1 != null) {
				node.next = list1;
			} else {
				node.next = list2;
			}

			return dummy.next;
		}
	}


}
