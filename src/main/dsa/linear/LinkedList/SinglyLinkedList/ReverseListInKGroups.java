package main.dsa.linear.LinkedList.SinglyLinkedList;

import static main.dsa.linear.LinkedList.SinglyLinkedList.ReverseLinkedList.reverseLinkedListUsingRecursion;

/*
Problem statement
You are given a linked list of 'n' nodes and an integer 'k', where 'k' is less than or equal to 'n'.

Your task is to reverse the order of each group of 'k' consecutive nodes, if 'n' is not divisible by 'k', then the last group of nodes should remain unchanged.

For example, if the linked list is 1->2->3->4->5, and 'k' is 3, we have to reverse the first three elements, and leave the last two elements unchanged.
Thus, the final linked list being 3->2->1->4->5.

Implement a function that performs this reversal, and returns the head of the modified linked list.

Example:
Input: 'list' = [1, 2, 3, 4], 'k' = 2

Output: 2 1 4 3


Explanation:
We have to reverse the given list 'k' at a time, which is 2 in this case. So we reverse the first 2 elements then the next 2 elements, giving us 2->1->4->3.

Note:
All the node values will be distinct.

Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
6
5 4 3 7 9 2
4

Sample Output 1:
7 3 4 5 9 2

Explanation of the Sample Input 1:
For the given test case, we reverse the nodes in groups of four. But for the last 2 elements, we cannot form a group of four, so leave them as they are. The linked list becomes 7->3->4->5->9->2. Hence the output is 7 3 4 5 9 2

Sample Input 2:
4
4 3 2 8
4

Sample Output 2:
8 2 3 4

Expected Time Complexity:
Try to solve this in O(n).

Expected Space Complexity:
Try to solve this using O(1) extra space.

Constraints:
1 <= n <= 10^4
1 <= k <= n

Time Limit: 1 sec
*/
public class ReverseListInKGroups extends SinglyLinkedList {

	private static Node getNthNode(int index, Node head) {
		index -= 1;
		while (head != null && index > 0) {
			index--;
			head = head.next;
		}
		return head;
	}

	public static Node kReverse(Node head, int k) {
		// Write your code here.

		Node temp = head;
		Node prevHead = null;
		while (temp != null) {
			Node kThNode = getNthNode(k, temp);

			if (kThNode == null) {
				if (prevHead != null)
					prevHead.next = temp;
				break;
			}

			Node nextHead = kThNode.next; // remember the next node
			kThNode.next = null; // separate the kth node

			reverseLinkedListUsingRecursion(temp);

			if (temp == head) {
				head = kThNode;
			} else {
				prevHead.next = kThNode;
			}
			prevHead = temp;
			temp = nextHead;
		}
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
		Node reversed = kReverse(head, 3);
		printList(reversed);
	}
}
