package main.meta.hard;

import main.dsa.linear.LinkedList.SinglyLinkedList.SinglyLinkedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

	public static Object convertArrToLinkedList(ArrayList<Integer> arr) {
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
		return (Node) convertArrToLinkedList(data);
	}

	public static Node mergeKSortedLists(List<Node> list) {

		Node head = list.get(0);

		for (int i = 1; i < list.size(); i++) {
			Node tmp = list.get(i);
			head = mergerTwoLSorts(head, tmp);

		}
		return head;
	}

	/**
	 * Merges K sorted linked lists into a single sorted linked list using a Priority Queue.
	 * <p>
	 * Algorithm:
	 * 1. Initialize a min priority queue to store first nodes from each list, ordered by node value
	 * 2. Add first node from each list into the priority queue (if non-null)
	 * 3. Create a dummy node and temp pointer for building merged list
	 * 4. While priority queue is not empty:
	 *    - Poll smallest node from queue
	 *    - Add it to merged list
	 *    - Add next node from same list into queue (if exists)
	 * 5. Return head of merged list (next of dummy node)
	 * <p>
	 * Time Complexity: O(N * log(K)) where:
	 * - N is total number of nodes across all input lists
	 * - K is number of linked lists
	 * - log(K) comes from priority queue operations (poll and add)
	 * - Initial population of queue takes O(K * log(K))
	 * - Main merge loop does O(log(K)) work N times
	 * <p>
	 * Space Complexity: O(K) where:
	 * - Priority queue stores at most K nodes at any time (one from each list)
	 * - Output merged list reuses existing nodes so not counted as extra space
	 * - Temporary pointers and dummy node use O(1) extra space
	 *
	 * @param list List containing heads of K sorted linked lists to be merged
	 * @return Head node of the merged sorted linked list
	 */
	public static Node mergeKSortedListsUsingPQ(List<Node> list) {
		// Initialize min priority queue comparing node values
		PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.first));

		// Add first node from each list, O(K * log(K)) time
		for (Node node : list) {
			if (node != null) {
				pq.add(new Pair(node.data, node));
			}
		}

		// Dummy node to build result list
		Node dummyNode = new Node(-1);
		Node temp = dummyNode;

		// Process all nodes, O(N * log(K)) time
		while (!pq.isEmpty()) {
			// Get smallest remaining node, O(log(K))
			Pair p = pq.poll();

			// Add to merged list, O(1)
			temp.next = p.node;
			temp = temp.next;

			// Add next node from same list if exists, O(log(K))
			if (p.node.next != null) {
				pq.add(new Pair(p.node.next.data, p.node.next));
			}
		}

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
