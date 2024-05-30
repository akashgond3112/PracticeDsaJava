package main.two_pointers.easy;

/*
876. Middle of the Linked List
Solved
Easy
Topics
Companies
Given the head of a singly linked list, return the middle node of the linked list.

If there are two middle nodes, return the second middle node.

Example 1:
Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.

Example 2:
Input: head = [1,2,3,4,5,6]
Output: [4,5,6]
Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.

Constraints:
The number of nodes in the list is in the range [1, 100].
1 <= Node.val <= 100
* */

import static org.junit.Assert.assertEquals;

public class MiddleOfTheLinkedList {

	public ListNode middleNode(ListNode head) {
		ListNode slow_ptr=head;
		ListNode fast_ptr=head;

		while (fast_ptr != null && fast_ptr.next != null) {
			fast_ptr = fast_ptr.next.next;
			slow_ptr = slow_ptr.next;
		}

		assert slow_ptr != null;
		return slow_ptr;
	}

	public static class IsSubsequence {

		public static boolean isSubsequence(String s, String t) {

			int i = 0;  // Initialize pointers for s and t
			int j = 0;

			while (i < s.length() && j < t.length()) {
				if (s.charAt(i) == t.charAt(j)) {
					i++;  // Move pointer i when characters match
				}
				j++;  // Always move pointer j
			}

			return i == s.length();
		}

		public static void main(String[] args) {
			// Example 1
			String s1 = "abc";
			String t1 = "ahbgdc";
			assertEquals(s1, t1);  // Output: true

			// Example 2
			String s2 = "axc";
			String t2 = "ahbgdc";
			assertEquals(s2, t2);  // Output: true
			;  // Output: false
		}

	}
}
