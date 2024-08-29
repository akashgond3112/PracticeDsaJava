package main.dsa.linear.LinkedList.SinglyLinkedList;

import java.util.HashMap;

/*
Problem statement
You are given a linked list containing 'n' nodes, where every node in the linked list contains two pointers:

(1) ‘next’ which points to the next node in the list

(2) ‘random’ which points to a random node in the list or 'null'.

Your task is to create a 'deep copy' of the given linked list and return its head.

Note:
A 'deep copy' of a linked list means we do not copy the references of the nodes of the original linked list, rather for each node in the original linked list, a new node is created.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
5
1 2 3 4 5
2 0 4 4 1

Sample Output 1:
True
Explanation of Sample Input 1:
For the given test case, “True” will be printed if the linked list is successfully cloned.

Sample Input 2:
2
1 2
1 0

Sample Output 2:
True

Expected Time Complexity:
Try to do this in O(n).

Expected Space Complexity:
Try to do this without using extra space.

Constraints:
1 <= n <= 5 * 10^4
-10^5 <= Node.data <= 10^5
-1 <= ri < n
*/
public class CloneALinkedListWithRandomPointers {

	static class Node {
		// Data stored in the node
		int data;
		// Pointer to the next node
		Node next;
		// Pointer to a random node in the list
		Node random;

		// Constructors for Node class
		Node() {
			this.data = 0;
			this.next = null;
			this.random = null;
		}

		Node(int x) {
			this.data = x;
			this.next = null;
			this.random = null;
		}

		Node(int x, Node nextNode, Node randomNode) {
			this.data = x;
			this.next = nextNode;
			this.random = randomNode;
		}
	}

	public static Node cloneLLBruteForce(Node head) {
		// Write your code here.

		if (head == null)
			return null;

		Node temp = head;
		HashMap<Node, Node> map = new HashMap<>();

		while (temp != null) {
			Node newNode = new Node(temp.data);
			map.put(temp, newNode);
			temp = temp.next;
		}

		temp = head;
		while (temp != null) {
			Node copy = map.get(temp);
			copy.next = map.get(temp.next);
			copy.random = map.get(temp.random);
			temp = temp.next;
		}

		return map.get(head);
	}

	public static void printClonedLinkedList(Node head) {
		while (head != null) {
			System.out.print("Data: " + head.data);
			if (head.random != null) {
				System.out.print(", Random: " + head.random.data);
			} else {
				System.out.print(", Random: nullptr");
			}
			System.out.println();
			// Move to the next node in the list
			head = head.next;
		}
	}

	public static Node cloneLL(Node head) {

		if (head == null)
			return null;

		Node temp = head;

		while (temp != null) {
			Node next = temp.next;
			Node copyNode = new Node(temp.data);

			copyNode.next = next;
			temp.next = copyNode;
			temp = next;
		}

		temp = head;
		while (temp != null) {
			Node copyNode = temp.next;
			if (temp.random != null) {
				copyNode.random = temp.random.next;
			} else {
				copyNode.random = null;
			}
			temp = temp.next.next;
		}

		Node dummy = new Node(-1);
		Node result = dummy;
		temp = head;

		while (temp != null) {
			result.next = temp.next;
			result = result.next;

			temp.next = temp.next.next;
			temp = temp.next;
		}
		return dummy.next;
	}

	// Main function
	public static void main(String[] args) {
		// Example linked list: 7 -> 14 -> 21 -> 28
		Node head = new Node(7);
		head.next = new Node(14);
		head.next.next = new Node(21);
		head.next.next.next = new Node(28);

		// Assigning random pointers
		head.random = head.next.next;
		head.next.random = head;
		head.next.next.random = head.next.next.next;
		head.next.next.next.random = head.next;

		System.out.println("Original Linked List with Random Pointers:");
		printClonedLinkedList(head);

		// Clone the linked list
		Node clonedList = cloneLLBruteForce(head);

		System.out.println("\nCloned Linked List with Random Pointers:");
		printClonedLinkedList(clonedList);


		// Clone the linked list
		Node clonedList2 = cloneLL(head);

		System.out.println("\nCloned Linked List with Random Pointers:");
		printClonedLinkedList(clonedList2);
	}
}
