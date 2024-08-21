package main.dsa.linear.LinkedList.SinglyLinkedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Problem statement
You are given a linked list containing 'n' 'head' nodes, where every node in the linked list contains two pointers:

(1) ‘next’ which points to the next node in the list

(2) ‘child’ pointer to a linked list where the current node is the head.

Each of these child linked lists is in sorted order and connected by 'child' pointer.

Your task is to flatten this linked such that all nodes appear in a single layer or level in a 'sorted order'.

Example:
Input: Given linked list is:

Output:
1 → 2 → 3 → 4 → 5 → 6 → 7 → 8 → 9 → 12 → 20 → null.

Explanation:
The returned linked list should be in a sorted order. All the elements in this returned linked list are connected by 'child' pointers and 'next' pointers point to null.

Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
4
3
1 2 3
3
8 10 15
2
18 22
1
29

Sample Output 1 :
1 2 3 8 10 15 18 22 29

Explanation For Sample Input 1:
The given linked list is

Therefore after flattening the list will become-
1 -> 2 -> 3 -> 8 -> 10 -> 15 -> 18 -> 22 -> 29 ->null

Sample Input 2 :
5
2
4 6
2
5 71
3
7 8 9
3
11 12 19
3
14 15 17

Sample Output 2 :
4 5 6 7 8 9 11 12 14 15 17 19 71

Expected Time Complexity:
Try solving this in O(n*n*k), where ‘n’ denotes the number of head nodes and ‘k’ is the average number of child nodes in all 'n' sub-lists.

Expected Space Complexity:
Try solving this without using any extra space.

Constraints:
1 <= n <= 100
1 <= k <= 20
1 <= Node.data <= 10^9

Time Limit: 1 sec
*/
public class FlattenALinkedList {

	static class Node {
		public int data;
		public Node next;
		public Node child;

		Node() {
			this.data = 0;
			this.next = null;
			this.child = null;
		}

		Node(int data) {
			this.data = data;
			this.next = null;
			this.child = null;
		}

		Node(int data, Node next, Node child) {
			this.data = data;
			this.next = next;
			this.child = child;
		}
	}

	static Node convertArrToLinkedList(ArrayList<Integer> arr) {
		// Create a dummy node to serve as
		// the head of the linked list
		Node dummyNode = new Node(-1);
		Node temp = dummyNode;

		// Iterate through the ArrayList and
		// create nodes with elements
		for (Integer integer : arr) {
			// Create a new node with the element
			temp.child = new Node(integer);
			// Move the temporary pointer
			// to the newly created node
			temp = temp.child;
		}
		// Return the linked list starting
		// from the next of the dummy node
		return dummyNode.child;
	}

	public static Node flattenLinkedListBruteForce(Node head) {
		//Write your code here
		if (head == null || head.next == null) {
			return head;
		}

		ArrayList<Integer> list = new ArrayList<>();

		Node temp = head;

		while (temp != null) {

			Node t2 = temp;

			while (t2 != null) {
				list.add(t2.data);
				t2 = t2.child;
			}
			temp = temp.next;
		}

		Collections.sort(list);

		Node node = convertArrToLinkedList(list);
		return node;
	}

	private static Node merge(Node head1, Node head2) {

		Node dummyNode = new Node(-1);
		Node result = dummyNode;

		while (head1 != null && head2 != null) {
			if (head1.data < head2.data) {
				result.child = head1;
				result = head1;
				head1 = head1.child;
			} else {
				result.child = head2;
				result = head2;
				head2 = head2.child;
			}
			result.next = null;
		}

		if (head1 != null) {
			result.child = head1;
		}
		if (head2 != null) {
			result.next = head2;
		}
		return dummyNode.child;
	}

	public static Node flattenLinkedList(Node head) {

		if (head == null || head.next == null) {
			return head;
		}

		Node mergedHead = flattenLinkedList(head.next);
		head = merge(head, mergedHead);
		return head;

	}

	static void printLinkedList(Node head) {
		while (head != null) {
			System.out.print(head.data + " ");
			head = head.child;
		}
		System.out.println();
	}

	// Print the linked list
	// in a grid-like structure
	static void printOriginalLinkedList(Node head, int depth) {
		while (head != null) {
			System.out.print(head.data);

			// If child exists, recursively
			// print it with indentation
			if (head.child != null) {
				System.out.print(" -> ");
				printOriginalLinkedList(head.child, depth + 1);
			}

			// Add vertical bars
			// for each level in the grid
			if (head.next != null) {
				System.out.println();
				for (int i = 0; i < depth; ++i) {
					System.out.print("| ");
				}
			}
			head = head.next;
		}
	}

	public static void main(String[] args) {
		// Create a linked list with child pointers
		Node head = new Node(5);
		head.child = new Node(14);

		head.next = new Node(10);
		head.next.child = new Node(4);

		head.next.next = new Node(12);
		head.next.next.child = new Node(20);
		head.next.next.child.child = new Node(13);

		head.next.next.next = new Node(7);
		head.next.next.next.child = new Node(17);

		// Print the original
		// linked list structure
		System.out.println("Original linked list:");
		printOriginalLinkedList(head, 0);

		// Flatten the linked list
		// and print the flattened list
		Node flattened = flattenLinkedListBruteForce(head);
		System.out.println("\nFlattened linked list: ");
		printLinkedList(flattened);
	}
}
