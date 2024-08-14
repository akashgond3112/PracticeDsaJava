package main.dsa.linear.LinkedList.SinglyLinkedList;

/*
Problem statement
Given a singly linked list of 'N' nodes. Your task is to delete the middle node of this list and return the head of the modified list.

However, if the list has an even number of nodes, we delete the second middle node

Example:
If given linked list is 1->2->3->4 then it should be modified to 1->2->4.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
5
1 2 3 4 5
Sample Output 1 :
1 2 4 5
Explanation to Sample Input 1 :
We can clearly see that there are 5 nodes in the linked list therefore the middle node is the node with value '3'.
Sample Input 2 :
1
2
Sample Output 2 :
-1
Explanation to Sample Input 1 :
We can clearly see that there is only one node in the linked list.
Therefore, after deleting that one node, the linked list becomes empty, resulting in an output of -1.
Constraints :
1 <= N <= 10^3
0 <= data <= 10^3

Where 'N' is the length of the linked list.

Time Limit: 1 sec
*/
public class DeleteMiddleNodeFromLinkedList extends SinglyLinkedList {

	public static Node deleteMiddleBruteForce(Node head) {
		// Write your code here.
		if (head == null || head.next == null) {
			return head;
		}
		int n = 0;
		Node cur = head;
		while (cur != null) {
			n++;
			cur = cur.next;
		}

		cur = head;
		int mid = n / 2;

		while (cur != null) {
			mid--;

			if (mid == 0) {
				cur.next = cur.next.next;
				break;
			}
			cur = cur.next;
		}
		return head;
	}

	public static Node deleteMiddleHareAndTortoiseApproach(Node head) {
		// Write your code here.
		if (head == null || head.next == null) {
			return head;
		}

		Node slow = head;
		Node fast = head;
		fast = fast.next.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		slow.next = slow.next.next;
		return head;
	}

	public static void main(String[] args) {
		Node head = new Node(0);
		head.next = new Node(1);
		head.next.next = new Node(2);
		head.next.next.next = new Node(3);
		head.next.next.next.next = new Node(4);
		head.next.next.next.next.next = new Node(5);

		printList(head);
		System.out.println();
		Node middle = deleteMiddleBruteForce(head);
		printList(middle);
		System.out.println();
		Node middle2 = deleteMiddleHareAndTortoiseApproach(head);
		printList(middle2);
	}
}
