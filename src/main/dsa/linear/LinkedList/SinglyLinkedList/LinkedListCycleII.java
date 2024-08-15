package main.dsa.linear.LinkedList.SinglyLinkedList;

import java.util.HashSet;

/*
Problem statement
You are given a singly linked list that may or may not contain a cycle. You are supposed to return the node where the cycle begins, if a cycle exists, else return 'NULL'.

A cycle occurs when a node's next pointer returns to a previous node in the list.

Example:
In the given linked list, there is a cycle starting at position 0, hence we return 0.

Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
1 2 3 4 -1
1
Sample Output 1 :
1

Explanation For Sample Input 1 :
For the first test case,

Sample Input 2 :
1 2 3 4 -1
0
Sample Output 2 :
0
Explanation For Sample Input 2 :
For the first test case,

Follow-Up:
Can you do this in O(n) time and using constant space?
Constraints :
-10^4 <= n <= 10^4
-1 <= pos < n
-10^9 <= data <= 10^9 and data != -1

Time Limit: 1 sec
*/
public class LinkedListCycleII extends SinglyLinkedList {

	public static Node firstNode(Node head) {
		// Write your code here.

		Node temp = head;
		HashSet<Node> hashSet = new HashSet<>();

		while (temp != null) {

			if (hashSet.contains(temp)) {
				return temp;
			}
			hashSet.add(temp);
			temp = temp.next;
		}
		return null;
	}

	public static Node firstNodeUsingTAndH(Node head) {
		//Your code goes here
		if (head == null || head.next == null) {
			return null;
		}
		Node slow = head;
		Node fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				slow = head;
				while (slow != fast) {
					slow = slow.next;
					fast = fast.next;
				}
				return slow;
			}
		}

		return null;
	}

	public static void main(String[] args) {
		Node head = new Node(0);
		head.next = new Node(1);
		head.next.next = new Node(2);
		head.next.next.next = new Node(3);
		head.next.next.next.next = new Node(4);
		head.next.next.next.next.next = new Node(5);

		// Creating a cycle for testing: 5 -> 2
		head.next.next.next.next.next.next = head.next.next;

		//		printList(head);
		System.out.println();
		Node cycleStart = firstNodeUsingTAndH(head);
		if (cycleStart != null) {
			System.out.println("Cycle starts at node with data: " + cycleStart.data);
		} else {
			System.out.println("No cycle detected.");
		}
	}
}
