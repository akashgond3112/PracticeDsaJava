package main.dsa.linear.LinkedList.SinglyLinkedList;

/*
19. Remove Nth Node From End of List
Solved
Medium
Topics
Companies
Hint
Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

Example 2:
Input: head = [1], n = 1
Output: []

Example 3:
Input: head = [1,2], n = 1
Output: [1]

Constraints:
The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
*/

public class DeletionOfNodeForGivenPosition {

	Node head;

	void push(int new_Data) {
		Node new_node = new Node(new_Data);

		new_node.next = head;
		head = new_node;
	}

	Node delete(int position) {

		if (head == null) {
			return null;
		}

		Node dummy = new Node(0);
		dummy.next = head;
		Node fast = dummy;
		Node slow = dummy;

		// Move fast ahead by n+1 steps
		for (int i = 0; i <= position; i++) {
			fast = fast.next;
		}

		// Move both fast and slow pointers until fast reaches the end
		while (fast != null) {
			fast = fast.next;
			slow = slow.next;
		}

		// Remove the nth node from end
		slow.next = slow.next.next;

		return dummy.next;
	}

	void printList() {
		Node tNode = head;
		while (tNode != null) {
			System.out.print(tNode.data + " ");
			tNode = tNode.next;
		}
	}

	class Node {
		int data;
		Node next;

		Node(int d) {
			data = d;
			next = null;
		}

	}


	public static void main(String[] args) {

		DeletionOfNodeForGivenPosition data = new DeletionOfNodeForGivenPosition();

		data.push(1);
		data.push(2);
//		data.push(3);
//		data.push(4);
//		data.push(5);

		System.out.println("\n Create linked list is: ");
		data.printList();

		Node delete = data.delete(1);

		System.out.println("\n Updated linked list is: ");
		while (delete != null) {
			System.out.print(delete.data + " ");
			delete = delete.next;
		}
	}

}
