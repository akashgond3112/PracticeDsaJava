package main.dsa.linear.LinkedList.SinglyLinkedList;

/*
Problem statement
You have been given a singly Linked List of 'N' nodes with integer data and an integer 'K'.

Your task is to remove the 'K'th node from the end of the given Linked List and return the head of the modified linked list.

Example:
Input : 1 -> 2 -> 3 -> 4 -> 'NULL'  and  'K' = 2
Output: 1 -> 2 -> 4 -> 'NULL'
Explanation:
After removing the second node from the end, the linked list become 1 -> 2 -> 4 -> 'NULL'.

Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
6 3
1 2 3 4 5 6
Sample Output 1 :
1 2 3 5 6
Explanation for Sample Input 1:
In the given linked list the node with data ‘4’ will be removed as this is the 3rd node from the end of the Linked List.
Sample Input 2 :
3 3
1 2 3
Sample Output 2 :
2 3
Constraints :
1 <= 'N' <= 10^3
1 <= 'K' <= 'N'
1 <= 'data' <= 10^3

Time Limit: 1 sec.
*/
public class DeleteKthNodeFromEnd extends SinglyLinkedList {

	public static Node removeKthNodeBruteForce(Node head, int K) {
		// Write your code here.
		if (head == null || head.next == null)
			return head;

		Node tempNode = head;
		int count = 0;

		while (tempNode != null) {
			count++;
			tempNode = tempNode.next;
		}

		if (count == K) {
			Node newHead = head.next;
			return head;
		}

		int resultant = count - K;
		tempNode = head;

		while (tempNode != null) {
			resultant--;
			if (resultant == 0) {
				break;
			}
			tempNode = tempNode.next;
		}

		assert tempNode != null;
		tempNode.next = tempNode.next.next;

		return head;

	}

	public static Node removeKthNodeOptimalApproach(Node head, int K) {

		Node fast = head;
		for (int i = 0; i < K; i++) {
			fast = fast.next;
		}
		Node slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}

		if (fast == null) {
			return head.next;
		}

		assert slow != null;
		Node deleteNode = slow.next;
		slow.next = deleteNode.next;
		return head;

	}

	public static void main(String[] args) {
		Node head = new Node(0);
		head.next = new Node(1);
		head.next.next = new Node(2);
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(0);
		head.next.next.next.next.next = new Node(1);

		printList(head);
		head = removeKthNodeBruteForce(head, 2);
		System.out.println();
		printList(head);
		System.out.println();
		head = removeKthNodeOptimalApproach(head, 2);
		printList(head);
	}
}
