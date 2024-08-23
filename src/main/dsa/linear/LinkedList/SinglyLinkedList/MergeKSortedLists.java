package main.dsa.linear.LinkedList.SinglyLinkedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import static main.dsa.linear.LinkedList.SinglyLinkedList.MergeTwoSortedLinkedLists.mergerTwoLSorts;

public class MergeKSortedLists extends SinglyLinkedList {

	static class Pair {
		int first;
		Node node;

		public Pair(int first, Node node) {
			this.first = first;
			this.node = node;
		}
	}

	static Node convertArrToLinkedList(ArrayList<Integer> arr) {
		// Create a dummy node to serve as the head of the linked list
		Node dummyNode = new Node(-1);
		Node temp = dummyNode;

		// Iterate through the ArrayList and create nodes with elements
		for (Integer integer : arr) {
			// Create a new node with the element
			temp.next = new Node(integer);
			// Move the temporary pointer to the newly created node
			temp = temp.next;
		}

		// Return the linked list starting from the next of the dummy node
		return dummyNode.next;
	}


	public static Node mergeKSortedListsBruteForce(List<Node> list) {

		ArrayList<Integer> data = new ArrayList<>();
		for (Node node : list) {

			Node tmp = node;
			while (tmp != null) {
				data.add(tmp.data);
				tmp = tmp.next;
			}
		}
		Collections.sort(data);
		return convertArrToLinkedList(data);
	}

	public static Node mergeKSortedLists(List<Node> list) {

		Node head = list.get(0);

		for (int i = 1; i < list.size(); i++) {
			Node tmp = list.get(i);
			head = mergerTwoLSorts(head, tmp);

		}
		return head;
	}

	public static Node mergeKSortedListsUsingPQ(List<Node> list) {
		// PriorityQueue to keep track of the smallest element at the top
		PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.first, b.first));

		// Add the head of each linked list to the priority queue
		for (Node node : list) {
			if (node != null) {
				pq.add(new Pair(node.data, node));  // Ensure only non-null nodes are added
			}
		}

		Node dummyNode = new Node(-1);
		Node temp = dummyNode;

		while (!pq.isEmpty()) {
			// Poll the smallest element from the queue
			Pair p = pq.poll();

			// Add the smallest node to the merged list
			temp.next = p.node;
			temp = temp.next;  // Move the temp pointer to the last node

			// If there are more nodes in the current list, add the next one to the queue
			if (p.node.next != null) {
				pq.add(new Pair(p.node.next.data, p.node.next));
			}
		}

		// Return the head of the merged linked list
		return dummyNode.next;
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
		List<Node> list = new ArrayList<>();
		list.add(head);
		list.add(hea2);
		printList(mergeKSortedListsBruteForce(list));
		System.out.println();
		printList(mergeKSortedLists(list));
		System.out.println();
		printList(mergeKSortedListsUsingPQ(list));
	}
}
