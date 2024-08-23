package main.dsa.linear.LinkedList.SinglyLinkedList;

public class MergeTwoSortedLinkedLists extends SinglyLinkedList {

	public static Node mergerTwoLSorts(Node first, Node second) {
		// Write your code here.
		if (first == null || second == null) {
			return first;
		}
		Node temp1 = first;
		Node temp2 = second;

		Node dummy = new Node(-1);
		Node temp = dummy;

		while (temp1 != null && temp2 != null) {

			if (temp1.data < temp2.data) {
				temp.next = temp1;
				temp = temp1;
				temp1 = temp1.next;
			} else {
				temp.next = temp2;
				temp = temp2;
				temp2 = temp2.next;
			}
		}

		if (temp1 != null) {
			temp.next = temp1;
		}
		if (temp2 != null) {
			temp.next = temp2;
		}
		return dummy.next;
	}

	public static void main(String[] args) {
		Node head = new Node(0);
		head.next = new Node(1);
		head.next.next = new Node(2);
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(3);
		head.next.next.next.next.next = new Node(6);
		head.next.next.next.next.next.next = new Node(9);

		Node hea2 = new Node(1);
		hea2.next = new Node(2);
		hea2.next.next = new Node(3);
		hea2.next.next.next = new Node(4);
		hea2.next.next.next.next = new Node(4);
		hea2.next.next.next.next.next = new Node(6);
		hea2.next.next.next.next.next.next = new Node(7);

		printList(head);
		System.out.println();
		printList(hea2);
		System.out.println();
		printList(mergerTwoLSorts(head, hea2));
	}
}
