package main.dsa.linear.LinkedList.SinglyLinkedList;

import java.util.LinkedList;

/*
Problem statement
You are given a Singly Linked List of integers. You need to reverse the Linked List by changing the links between nodes.

Example:

Input:
2 4 5 -1

Output:
5 4 2 -1

Explanation: 2->4->5 is the initial linked list. If we reverse this, we get 5->4->2.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
1 2 4 -1
Sample Output 1 :
4 2 1 -1
Explanation for Sample Input 1 :
1->2->4 is the initial linked list. If we reverse this, we get 4->2->1.
Sample Input 2 :
1
1 1 1 -1
Sample Output 2 :
1 1 1 -1
Constraints :
1 <= 'N' <= 10^4
0 <= 'data' <= 10^9

Where 'N' is the number of nodes in the linked list.

Time Limit: 1 sec
*/
public class ReverseLinkedList extends SinglyLinkedList {

	public static Node reverseLinkedList(Node head) {
		// Write your code here.
		Node temp = head;
		Node prev = null;

		while (temp != null) {
			Node front = temp.next;
			temp.next = prev;
			prev = temp;
			temp = front;
		}
		return prev;
	}

	public static Node reverseLinkedListUsingRecursion(Node head) {
		// Write your code here.
		if (head == null || head.next == null) {
			return head;
		}

		Node newHead = reverseLinkedListUsingRecursion(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;
	}

	public static void main(String[] args) {
		Node head = new Node(0);
		head.next = new Node(1);
		head.next.next = new Node(2);
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(0);
		head.next.next.next.next.next = new Node(1);

		printList(head);
		System.out.println();
		Node reversed = reverseLinkedList(head);
		printList(reversed);
		System.out.println();
		Node reverseLinkedListUsingRecursion = reverseLinkedListUsingRecursion(reversed);
		printList(reverseLinkedListUsingRecursion);
	}
}
