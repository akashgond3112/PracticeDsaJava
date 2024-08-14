package main.dsa.linear.LinkedList.SinglyLinkedList;

/*
Problem statement
You are given a Singly Linked List of integers. Return true if it has a cycle, else return false.

A cycle occurs when a node's next points back to a previous node in the list.

Example:
In the given linked list, there is a cycle, hence we return true.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
1 2 3 4 -1
1
Sample Output 1 :
true

Explanation of Sample Input 1:
The linked list given in the input is as follows:

Sample Input 2 :
1 2 3 4 -1
0

Sample Output 2 :
true

Explanation of Sample Input 2:
The linked list given in the input is as follows:
Sample Input 3 :
5 -1
-1
Sample Output 3 :
false

Explanation of Sample Input 3:
 The linked list given in the input is as follows:

Expected Time Complexity:
Try to solve this problem in O(n).

Expected Space Complexity:
Try to solve this problem in O(1).

Constraints :
0 <= n <= 10^6
-1 <= pos < n
-10^9 <= data <= 10^9 and data != -1

Where 'n' is the size of the singly linked list, 'pos' represents the position (0-indexed) in the linked list where the tail connects to, and 'data' is the Integer data of the singly linked list.

Time Limit: 1 sec
*/
public class CycleDetectionInASinglyLinkedList extends SinglyLinkedList {

	public static boolean detectCycle(Node head) {
		//Your code goes here
		if (head == null || head.next == null) {
			return false;
		}
		Node slow = head;
		Node fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Node head = new Node(0);
		head.next = new Node(1);
		head.next.next = new Node(2);
		head.next.next.next = new Node(3);
		head.next.next.next.next = new Node(4);
		head.next.next.next.next.next = new Node(5);

		printList(head);
		System.out.println(detectCycle(head));
	}
}
