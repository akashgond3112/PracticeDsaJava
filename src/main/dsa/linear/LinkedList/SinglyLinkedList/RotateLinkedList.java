package main.dsa.linear.LinkedList.SinglyLinkedList;

import java.util.LinkedList;

/*
Problem statement
You are given a linked list having ‘n’ nodes and an integer ‘k’.

You have to rotate the linked list to the right by ‘k’ positions .

Example :
Input: linked list = [1 2 3 4] , k = 2

Output: 3 4 1 2

Explanation:
We have to rotate the given linked list to the right 2 times. After rotating it to the right once it becomes 4->1->2->3. After rotating it to the right again, it becomes 3->4->1->2.

Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
6
1 2 3 4 5 6
2

Sample Output 1 :
5 6 1 2 3 4

Explanation For Sample Input 1 :
For the first test case, after 1st clockwise rotation the modified linked list will be : 6->1->2->3->4->5
After, 2nd clockwise rotated the modified linked list will be : 5->6->1->2->3->4.
Thus the output is: 5 6 1 2 3 4.

Sample Input 2 :
3
3 6 9
0

Sample Output 2:
3 6 9

Explanation For Sample Input 2 :
In this test case, ‘k’ is 0 therefore there will be no rotation, so the linked list remains unchanged.

Expected Time Complexity:
Try to do this in O(n).

Constraints :
1 <= n <= 10^5
0 <= Node.data <= 10^9
0 <= k <= 10^5

Time Limit: 1 sec
*/
public class RotateLinkedList extends SinglyLinkedList {

	private static Node getNthNode(int index, Node head) {
		int count = 0;
		while (head != null) {
			if (count == index) {
				return head;
			}
			head = head.next;
			count++;
		}
		return head;
	}

	public static Node rotate(Node head, int k) {
		// Write your code here.
		if (head == null || head.next == null) {
			return head;
		}

		int length = 1;

		Node tail = head;

		while (tail.next != null) {
			tail = tail.next;
			length++;
		}

		if (k % length == 0) {
			return head;
		}

		k = k % length;
		tail.next = head;

		Node newLastNode = getNthNode(length - k, head);
		head = newLastNode.next;
		newLastNode.next = null;
		return head;
	}

	public static void main(String[] args) {
		Node head = new Node(0);
		head.next = new Node(1);
		head.next.next = new Node(2);
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(0);
		head.next.next.next.next.next = new Node(1);
		head.next.next.next.next.next.next = new Node(9);

		printList(head);
		System.out.println();
		Node reversed = rotate(head, 3);
		printList(reversed);
	}

}
