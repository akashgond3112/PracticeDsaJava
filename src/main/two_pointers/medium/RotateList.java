package main.two_pointers.medium;

/*
61. Rotate List

Medium
Topics
Companies
Given the head of a linked list, rotate the list to the right by k places.

Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]

*/

class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}

public class RotateList {

	static ListNode head;

	public static void push(int new_data) {

		ListNode new_node = new ListNode(new_data);

		new_node.next = head;
		head = new_node;
	}

	public ListNode rotateRight(ListNode head, int k) {

		if (head == null || head.next == null || k == 0) {
			return head;
		}

		int length = 1;
		ListNode temp = head;

		while (temp.next != null) {
			temp = temp.next;
			length++;
		}

		temp.next = head;
		k = k % length;
		k = length - k;

		while (k-- > 0) {
			temp = temp.next;
		}

		head = temp.next;
		temp.next = null;

		return head;
	}

	static void printList(ListNode listNode)
	{
		while (listNode != null) {
			System.out.print(listNode.val + " ");
			listNode = listNode.next;
		}
	}

	public static void main(String[] args) {


		push(1);
		push(2);
		push(3);
		push(4);
		push(5);

		RotateList rotateList = new RotateList();
		printList(head);
		System.out.println("======================");
		ListNode listNode = rotateList.rotateRight(head, 2);
		printList(listNode);
	}
}
