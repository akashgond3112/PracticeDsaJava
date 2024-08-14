package main.dsa.linear.LinkedList.SinglyLinkedList;

/*
Problem statement
You’re given a linked list. The last node might point to null, or it might point to a node in the list, thus forming a cycle.



Find out whether the linked list has a cycle or not, and the length of the cycle if it does.



If there is no cycle, return 0, otherwise return the length of the cycle.

Example:
Input: Linked List: 4 -> 10 -> 3 -> 5 -> 10(at position 2)

Output: Length of cycle = 3

Explanation: The cycle is 10, 3, 5.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
4
4 10 3 5
2
Sample Output 1:
3
Explanation Of Sample Input 1 :
The cycle is 10, 3, 5.
Sample Input 2 :
4
4 10 3 5
0
Sample Output 2 :
0
Explanation Of Sample Input 2 :
Since ‘p’ = 0, the last node is pointing to null, so no cycle exists.


Constraints:
1 <= ‘n’ <= 100000
1 <= Data in linked list node <= 10^9
0 <= ‘p’ <= ‘n’
* */
public class LengthOfLinkedList extends SinglyLinkedList {

	static int getCount(Node head) {

		Node temp = head;
		int new_count = 0;

		while (temp != null) {
			new_count++;
			temp = temp.next;
		}

		return new_count;
	}

	/* Returns count of nodes in linked list using recursive*/
	public static int getCountRecursive(Node node) {
		// Base case
		if (node == null)
			return 0;

		// Count is this node plus rest of the list
		return 1 + getCountRecursive(node.next);
	}

	/* Wrapper over getCountRec() */
	public static int getCountNew(Node head) {
		return getCountRecursive(head);
	}

	private static int findLength(Node slow, Node fast) {

		int count = 0;
		fast = fast.next;
		while (slow != fast) {
			count++;
			fast = fast.next;
		}
		return count;
	}

	public static int getCountNewRecursive(Node head) {
		if (head == null || head.next == null) {
			return 0;
		}
		Node slow = head;
		Node fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				return findLength(slow, fast);
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		/* Start with the empty list */
		Node head = new Node(0);
		head.next = new Node(1);
		head.next.next = new Node(2);
		head.next.next.next = new Node(3);
		head.next.next.next.next = new Node(4);
		head.next.next.next.next.next = new Node(5);

		printList(head);
		System.out.println();
		// Function call
		System.out.println("Count of nodes is " + getCount(head));
		System.out.println("Count of nodes is " + getCountNew(head));
		System.out.println("Count of nodes is " + getCountNewRecursive(head));
	}


}
