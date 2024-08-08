package main.dsa.linear.LinkedList.DoublyLinkedList;

/*
Problem statement
You are given a doubly-linked list of size 'N', consisting of positive integers. Now your task is to reverse it and return the head of the modified list.

Note:
A doubly linked list is a kind of linked list that is bidirectional, meaning it can be traversed in both forward and backward directions.
Example:

Input:
4
4 3 2 1

This means you have been given doubly linked list of size 4 = 4 <-> 3 <-> 2 <-> 1.

Output:
1 2 3 4

This means after reversing the doubly linked list it becomes 1 <-> 2 <-> 3 <-> 4.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
8
1 2 3 4 5 6 7 8
Sample Output 1 :
8 7 6 5 4 3 2 1
Explanation for sample output 1
Input: 1 <-> 2 <-> 3 <-> 4 <-> 5 <-> 6 <-> 7 <-> 8
Output: 8 <-> 7 <-> 6 <-> 5 <-> 4 <-> 3 <-> 2 <-> 1
Explanation: It's self explanatory.
Sample Input 2 :
5
5 8 4 9 1
Sample Output 2 :
1 9 4 8 5
Constraints :
1 <= 'N' <= 10^3
0 <= 'data' <= 10^3

Where 'N' is the size of the doubly linked list.

Time Limit: 1 sec
*/
public class ReverseADoublyLinkedList extends DoublyLinkedList {

	public static Node reverseDLL(Node head) {
		// If the list is empty or has only one node, return the head
		if (head == null || head.next == null) {
			return head;
		}

		Node prev = null;
		Node current = head;

		// Traverse the list and reverse the links
		while (current != null) {
			prev = current.prev;        // Store the previous node
			current.prev = current.next; // Swap the prev and next pointers
			current.next = prev;         // Continue swapping the next pointer
			current = current.prev;      // Move to the next node in the original list
		}

		// prev is now pointing to the new head, so return prev.prev
		return prev.prev;
	}


	public static void main(String[] args) {

		int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Node head = push(data);
		printList(head);

		System.out.print("\n Reverse \n");
		reverseDLL(head);
		printList(head);
	}

}
