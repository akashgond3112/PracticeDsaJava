package main.dsa.linear.LinkedList.DoublyLinkedList;

public class DoublyLinkedList {

	static class Node {
		int data;
		Node next;
		Node prev;

		public Node(int data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}

		public Node(int data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}

	}

	static Node push(int[] new_data) {
		Node new_node = new Node(new_data[0]);

		Node prev = new_node;
		for (int i = 1; i < new_data.length; i++) {
			Node temp = new Node(new_data[i], null, prev);
			prev.next = temp;
			prev = temp;
		}
		return new_node;
	}

	static Node deleteHead(Node head) {
		if (head == null || head.next == null) {
			return null;
		}

		Node prev = head;
		head = head.next;
		head.prev = null;
		prev.next = null;
		return head;
	}

	static Node deleteTail(Node head) {
		if (head == null || head.next == null) {
			return null;
		}

		Node tail = head;

		while (tail.next != null) {
			tail = tail.next;
		}

		Node prev = tail.prev;
		tail.prev = null;
		prev.next = null;
		return head;
	}

	static Node getTail(Node head) {
		if (head == null || head.next == null) {
			return null;
		}

		Node tail = head;

		while (tail.next != null) {
			tail = tail.next;
		}
		return tail;
	}

	static Node deleteKth(Node head, int k) {
		if (head == null || head.next == null || k == 0) {
			return head;
		}

		Node temp = head;
		int count = 0;
		while (temp != null) {
			count++;
			if (count == k)
				break;
			temp = temp.next;
		}

		Node prev = temp.prev;
		Node next = temp.next;

		if (prev == null && next == null) {
			return null;
		} else if (temp.prev == null) {
			deleteHead(temp);
		} else if (temp.next == null) {
			deleteTail(temp);
		} else {
			prev.next = next;
			next.prev = prev;

			temp.next = null;
			temp.prev = null;
		}
		return head;
	}

	static void deleteNode(Node temp) {
		Node prev = temp.prev;
		Node next = temp.next;

		if (next == null) {
			prev.next = null;
			temp.prev = null;
			return;
		} else {
			prev.next = next;
			next.prev = prev;

			temp.next = null;
			temp.prev = null;
		}
	}

	static void printList(Node node) {
		Node last = null;

		System.out.print("\n Traversal in forwards \n");

		while (node != null) {
			System.out.print(" " + node.data + " ");
			last = node;
			node = node.next;
		}
		System.out.print("\n Traversal in backwards \n");

		while (last != null) {
			System.out.print(" " + last.data + " ");
			last = last.prev;
		}
	}

	static Node insertAtHead(Node head, int data) {

		if (head == null || head.next == null) {
			Node new_node = new Node(data);
			new_node.next = null;
			new_node.prev = null;
			return new_node;
		}
		Node new_node = new Node(data, head, null);
		head.prev = new_node;
		new_node.next = head;
		return new_node;
	}

	static Node insertAtTail(Node head, int data) {
		if (head == null || head.next == null) {
			Node new_node = new Node(data);
			new_node.next = null;
			new_node.prev = null;
			return new_node;
		}

		Node tail = head;

		while (tail.next != null) {
			tail = tail.next;
		}
		Node new_node = new Node(data);
		tail.next = new_node;
		new_node.prev = tail;
		new_node.next = null;

		return head;
	}

	static Node insertAtKth(Node head, int k, int data) {

		if (k == 1) {
			return insertAtHead(head, data);
		}

		Node temp = head;
		int count = 0;
		while (temp != null) {
			if (count == k) {
				break;
			}
			count++;
			temp = temp.next;
		}

		Node prev = temp.prev;

		Node new_node = new Node(data, temp, prev);
		prev.next = new_node;
		temp.prev = new_node;
		return head;
	}

	static void insertAtNode(Node temp, int data) {
		Node prev = temp.prev;

		Node new_node = new Node(data, temp, prev);
		prev.next = new_node;
		temp.prev = new_node;
	}

	public static void main(String[] args) {

		int[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Node head = push(data);
		printList(head);

		System.out.print("\n Delete Head \n");
		head = deleteHead(head);
		printList(head);

		System.out.print("\n Delete Tail \n");
		head = deleteTail(head);
		printList(head);

		System.out.print("\n Delete Kth element \n");
		head = deleteKth(head, 2);
		printList(head);

		System.out.print("\n Delete Kth element \n");
		deleteNode(head.next.next);
		printList(head);

		System.out.print("\n Insert at head \n");
		head = insertAtHead(head, 100);
		printList(head);

		System.out.print("\n Insert at tail \n");
		head = insertAtTail(head, 200);
		printList(head);

		System.out.print("\n Insert at kth \n");
		head = insertAtKth(head, 4, 500);
		printList(head);

		System.out.print("\n Insert at node \n");
		insertAtNode(head.next, 600);
		printList(head);
	}


}
