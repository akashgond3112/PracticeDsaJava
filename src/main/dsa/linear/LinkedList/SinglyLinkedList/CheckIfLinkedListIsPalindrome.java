package main.dsa.linear.LinkedList.SinglyLinkedList;

/*
 Problem statement
You are given a Singly Linked List of integers. You have to return true if the linked list is palindrome, else return false.



A Linked List is a palindrome if it reads the same from left to right and from right to left.



Example:
The lists (1 -> 2 -> 1), (3 -> 4 -> 4-> 3), and (1) are palindromes, while the lists (1 -> 2 -> 3) and (3 -> 4) are not.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
1 2 2 1 -1
Sample Output 1:
True
Explanation for Sample Input 1:
The given list is a palindrome.
Sample Input 2:
3 2 1 -1
Sample Output 2:
False
Constraints :
1 <= 'N' <= 5 * 10^4
-10^9 <= 'data' <= 10^9 and 'data' != -1

Where 'N' is the number of nodes in the linked list and ‘data’ represents the value of the list's nodes.

Time Limit: 1sec
*/
public class CheckIfLinkedListIsPalindrome extends SinglyLinkedList {

	public static Node reverseLinkedList(Node head) {
		// Check if the list is empty or has only one node
		if (head == null || head.next == null) {

			// No change is needed;
			// return the current head
			return head;
		}

		// Recursive step: Reverse the remaining
		// part of the list and get the new head
		Node newHead = reverseLinkedList(head.next);

		// Store the next node in 'front'
		// to reverse the link
		Node front = head.next;

		// Update the 'next' pointer of 'front' to
		// point to the current head, effectively
		// reversing the link direction
		front.next = head;

		// Set the 'next' pointer of the
		// current head to 'null' to
		// break the original link
		head.next = null;

		// Return the new head obtained
		// from the recursion
		return newHead;
	}

	public static boolean isPalindromeRecursive(Node head) {
		// write your code here

		if (head == null || head.next == null) {
			return true;
		}

		Node slow_ptr = head;
		Node fast_ptr = head;

		while (fast_ptr.next != null && fast_ptr.next.next != null) {
			fast_ptr = fast_ptr.next.next;
			slow_ptr = slow_ptr.next;
		}

		assert slow_ptr != null;
		Node newHead = reverseLinkedList(slow_ptr.next);
		Node firstHead = head;
		Node secondHead = newHead;

		while (secondHead != null) {
			if (firstHead.data != secondHead.data) {
				reverseLinkedList(newHead);
				return false;
			}
			firstHead = firstHead.next;
			secondHead = secondHead.next;
		}
		reverseLinkedList(newHead);
		return true;
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(3);
		head.next.next.next.next = new Node(2);
		head.next.next.next.next.next = new Node(1);

		printList(head);
		System.out.println();
		System.out.println(isPalindromeRecursive(head));

	}
}
