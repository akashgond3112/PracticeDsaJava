package main.dsa.linear.LinkedList.SinglyLinkedList;

public class SinglyLinkedList {

	protected static class Node {
		public int data;
		public Node next;

		public Node(){}

		public Node(int data) {
			this.data = data;
		}

		public Node(int data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	public static Node push(Node head, int newData) {
		// Create a new node with the given data
		Node newNode = new Node(newData);

		// Make the new node point to the current head
		newNode.next = head;

		// The new node becomes the new head of the list
		head = newNode;

		// Return the new head of the list
		return head;
	}


	protected static void printList(Node n) {
		// Iterate till n reaches null
		while (n != null) {
			// Print the data
			System.out.print(n.data + " ");
			n = n.next;
		}
	}

	public static void main(String[] args) {
		Node head = null;
		Node second = null;
		Node third = null;

		head = new Node();
		second = new Node();
		third = new Node();

		head.data = 1;
		head.next = second;

		second.data = 2;
		second.next = third;

		third.data = 3;
		third.next = null;

		printList(head);
	}
}
