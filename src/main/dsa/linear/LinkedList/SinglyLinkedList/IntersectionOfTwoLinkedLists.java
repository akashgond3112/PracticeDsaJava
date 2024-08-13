package main.dsa.linear.LinkedList.SinglyLinkedList;

import java.util.HashMap;
import java.util.Map;

/*
Problem statement
You are given two Singly Linked Lists of integers, which may have an intersection point.

Your task is to return the first intersection node. If there is no intersection, return NULL.

Example:-
The Linked Lists, where a1, a2, c1, c2, c3 is the first linked list and b1, b2, b3, c1, c2, c3 is the second linked list, merging at node c1.

Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
4 1 -1
5 6 -1
8 -1
Sample Output 1 :
8
Explanation For Sample Input 1:
As shown in the diagram, the node with data is 8, at which merging starts

Sample Input 2 :
1 9 1 -1
3 -1
2 -1
Sample Output 2 :
2
Constraints :
-10^9 <= data <= 10^9 and data != -1
 The length of both the linked list is positive.
 Time Limit: 1 sec
*/
public class IntersectionOfTwoLinkedLists extends SinglyLinkedList {

	public static int findIntersectionBruteForce(Node firstHead, Node secondHead) {
		// Map to store nodes from the first linked list
		Map<Node, Boolean> map = new HashMap<>();

		// Traverse the first linked list and store each node in the map
		Node temp = firstHead;
		while (temp != null) {
			map.put(temp, true);
			temp = temp.next;
		}

		// Traverse the second linked list and check if any node is in the map
		temp = secondHead;
		while (temp != null) {
			if (map.containsKey(temp)) {
				return temp.data; // Intersection found
			}
			temp = temp.next;
		}

		// No intersection found
		return 0;
	}

	public static int findIntersectionOptimalApproach(Node firstHead, Node secondHead) {
		// Step 1: Find the length of both linked lists
		if (firstHead == null || secondHead == null) {
			return 0;
		}

		int countFirst = getCount(firstHead);
		int countSecond = getCount(secondHead);

		// Calculate the difference in lengths
		int diff = Math.abs(countFirst - countSecond);

		Node longer = countFirst > countSecond ? firstHead : secondHead;
		Node shorter = countFirst > countSecond ? secondHead : firstHead;

		// Move the pointer of the longer list by 'diff' nodes ahead
		for (int i = 0; i < diff; i++) {
			longer = longer.next;
		}

		// Step 2: Move both pointers until they meet at the intersection point
		while (longer != null && shorter != null) {
			if (longer == shorter) {
				return longer.data; // Intersection found
			}
			longer = longer.next;
			shorter = shorter.next;
		}

		// No intersection found
		return 0;
	}

	private static int getCount(Node head) {
		int count = 0;
		Node temp = head;
		while (temp != null) {
			count++;
			temp = temp.next;
		}
		return count;
	}

	public static int findIntersectionIterative(Node firstHead, Node secondHead) {
		if (firstHead == null || secondHead == null) {
			return 0;
		}

		Node tempFirst = firstHead;
		Node tempSecond = secondHead;

		while (tempFirst != tempSecond) {
			tempFirst = (tempFirst != null) ? tempFirst.next : secondHead;
			tempSecond = (tempSecond != null) ? tempSecond.next : firstHead;
		}

		return (tempFirst != null) ? tempFirst.data : 0;
	}


	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);

		// Create linked list 2: 6 -> 3 -> 4 (where 3 -> 4 is the intersection)
		Node head2 = new Node(6);
		head2.next = head1.next.next; // 3 -> 4

		printList(head1);
		System.out.println();
		printList(head2);
		System.out.println();
		System.out.println(findIntersectionBruteForce(head1, head2));
		System.out.println(findIntersectionOptimalApproach(head1, head2));
		System.out.println(findIntersectionIterative(head1, head2));

	}
}
