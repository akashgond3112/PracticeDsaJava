package main.dsa.linear.LinkedList.SinglyLinkedList;

import static main.dsa.linear.LinkedList.SinglyLinkedList.MergeTwoSortedLinkedLists.mergerTwoLSorts;

/*
Problem statement
You are given a Singly Linked List of integers which is sorted based on absolute value.

You have to sort the Linked List based on actual values.

The absolute value of a real number x, denoted |x|, is the non-negative value of x without regard to its sign.

Example:
If the given list is {1 -> -2 -> 3} (which is sorted on absolute value), the returned list should be {-2 -> 1 -> 3}.
Detailed explanation ( Input/output format, Notes, Images )
Constraints :
1 <= T <= 10
1 <= 'N' <= 5 * 10^4
-10^9 <= 'data' <= 10^9 and 'data' != -1

Where 'N' denotes the number of elements in the Singly Linked List and 'data' represents the value of those elements.

Time Limit : 1 sec
Sample Input 1:
3
1 -2 3 -1
9 9 -1
4 -1
Sample Output 1:
-2 1 3 -1
9 9 -1
4 -1
Explanation for Sample Input 1:
For the first test case:
On arranging element from lowest to highest we get ‘-2 1 3 -1’ so we return it as the answer.


For the second test case:
On arranging element from lowest to highest we get ‘9 9 -1’ so we return it as the answer.


For the third test case:
On arranging element from lowest to highest we get ‘4 -1’ so we return it as the answer.
Sample Input 2:
2
1 1 1 -1
3 -3 -1
Sample Output 2:
1 1 1 -1
-3 3 -1
*/
public class SortLinkedListII extends SinglyLinkedList {

	public static Node findMiddle(Node head) {
		// Write your code here.
		if (head == null || head.next == null) {
			return head;
		}

		Node fast = head.next;
		Node slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

	public static Node sortLL(Node head) {
		// Write your code here.
		if (head == null || head.next == null) {
			return head;
		}

		Node middle = findMiddle(head);

		Node rightHead = middle.next;
		middle.next = null;

		Node leftHead = head;


		leftHead = sortLL(leftHead);
		rightHead = sortLL(rightHead);

		return mergerTwoLSorts(leftHead, rightHead);
	}

	public static void main(String[] args) {
		Node head = new Node(0);
		head.next = new Node(7);
		head.next.next = new Node(9);
		head.next.next.next = new Node(1);
		head.next.next.next.next = new Node(3);
		head.next.next.next.next.next = new Node(1);

		printList(head);
		head = sortLL(head);
		System.out.println();
		printList(head);
	}
}
