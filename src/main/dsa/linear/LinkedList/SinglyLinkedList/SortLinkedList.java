package main.dsa.linear.LinkedList.SinglyLinkedList;

/*
Problem statement
Given a linked list of 'N' nodes, where each node has an integer value that can be 0, 1, or 2. You need to sort the linked list in non-decreasing order and the return the head of the sorted list.



Example:
Given linked list is 1 -> 0 -> 2 -> 1 -> 2.
The sorted list for the given linked list will be 0 -> 1 -> 1 -> 2 -> 2.


Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
7
1 0 2 1 0 2 1


Sample Output 1:
0 0 1 1 1 2 2


Explanation Of Sample Input 1:
Input: 1 -> 0 -> 2 -> 1 -> 0 -> 2 -> 1

Output: 0 -> 0 -> 1 -> 1 -> 1 -> 2 -> 2

Explanation:
In this example, the original linked list contains two 0s, three 1s, and two 2s. The sorted linked list has all the 0s at the beginning, followed by all the 1s, and finally, all the 2s at the end.


Sample Input 2:
8
2 1 0 2 1 0 0 2


Sample Output 2:
0 0 0 1 1 2 2 2


Follow Up:
Can you solve this without updating the Nodes of the given linked list?


Constraints :
1 <= N <= 10^3
0 <= data <= 2

Where 'N' is the length of the linked list.

Time Limit: 1 sec
*/
public class SortLinkedList extends SinglyLinkedList {

	public static Node sortList(Node head) {
		// Write your code here
		if (head == null || head.next == null) {
			return head;
		}

		Node zeroesHead = new Node(-1);
		Node oneHead = new Node(-1);
		Node twoHead = new Node(-1);

		Node zero = zeroesHead;
		Node one = oneHead;
		Node two = twoHead;

		Node temp = head;

		while (temp != null) {

			if (temp.data == 0) {
				zero.next = temp;
				zero = temp;
			} else if (temp.data == 1) {
				one.next = temp;
				one = temp;
			} else {
				two.next = temp;
				two = temp;
			}
			temp = temp.next;
		}

		zero.next = oneHead.next != null ? oneHead.next : twoHead.next;
		one.next = twoHead.next;
		two.next = null;

		return zeroesHead.next;
	}

	public static void main(String[] args) {
		Node head = new Node(0);
		head.next = new Node(1);
		head.next.next = new Node(2);
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(0);
		head.next.next.next.next.next = new Node(1);

		printList(head);
		head = sortList(head);
		System.out.println();
		printList(head);
	}
}
