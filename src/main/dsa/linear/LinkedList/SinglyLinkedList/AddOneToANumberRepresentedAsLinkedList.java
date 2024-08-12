package main.dsa.linear.LinkedList.SinglyLinkedList;

import static main.dsa.linear.LinkedList.SinglyLinkedList.ReverseLinkedList.reverseLinkedListUsingRecursion;

/*
Problem statement
You're given a positive integer represented in the form of a singly linked-list of digits. The length of the number is 'n'.
Add 1 to the number, i.e., increment the given number by one.
The digits are stored such that the most significant digit is at the head of the linked list and the least significant digit is at the tail of the linked list.
Example:
Input: Initial Linked List: 1 -> 5 -> 2

Output: Modified Linked List: 1 -> 5 -> 3

Explanation: Initially the number is 152. After incrementing it by 1, the number becomes 153.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
3
1 5 2

Sample Output 1:
1 5 3

Explanation For Sample Input 1:
Initially the number is 152. After incrementing it by 1, the number becomes 153.

Sample Input 2:
2
9 9

Sample Output 2:
1 0 0

Explanation For Sample Input 2:
Initially the number is 9. After incrementing it by 1, the number becomes 100. Please note that there were 2 nodes in the initial linked list, but there are three nodes in the sum linked list.

Expected time complexity :
The expected time complexity is O('n').

Constraints:
1 <= 'n' <=  10^5
0 <= Node in linked list <= 9
There are no leading zeroes in the number.
Time Limit: 1 sec
*/
public class AddOneToANumberRepresentedAsLinkedList extends SinglyLinkedList {

	public static Node addOneUsingRecursion(Node head) {
		// Base case: If the head is null, return null (nothing to add to)

		int carry = helper(head);

		if (carry == 0)
			return head;
		Node newHead = new Node(1);
		newHead.next = head;
		return newHead;
	}

	private static int helper(Node head) {
		if (head == null)
			return 1;
		else {
			int carry = helper(head.next);
			head.data = carry + head.data;

			if (head.data < 10) {
				return 0;
			}
			head.data = 0;
			return 1;
		}

	}

	public static Node addOne(Node head) {
		// Base case: If the head is null, return null (nothing to add to)
		if (head == null) {
			return null;
		}

		// Step 1: Reverse the linked list
		Node reversed = reverseLinkedListUsingRecursion(head);

		// Step 2: Add 1 to the reversed list
		Node temp = reversed;
		int carry = 1; // Initial carry is 1, as we are adding 1 to the number

		while (temp != null) {
			temp.data = temp.data + carry;
			if (temp.data < 10) {
				carry = 0; // No more carry needed, break out of the loop
				break;
			} else {
				temp.data = 0; // Reset current node's data to 0 and carry over to the next node
				carry = 1;
			}
			if (temp.next == null && carry > 0) {
				// If we've reached the end of the list and still have a carry, we need to add a new node
				temp.next = new Node(1);
				carry = 0; // Carry handled, so reset to 0
				break;
			}
			temp = temp.next;
		}

		// Step 3: Reverse the list back to its original order
		// Return the head of the modified list
		return reverseLinkedListUsingRecursion(reversed);
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.next = new Node(5);
		head.next.next = new Node(9);
		//		head.next.next.next = new Node(2);
		//		head.next.next.next.next = new Node(0);
		//		head.next.next.next.next.next = new Node(1);

		printList(head);
		System.out.println();
		Node addOne = addOne(head);
		printList(addOne);
		System.out.println();
		Node addTwo = addOneUsingRecursion(head);
		printList(addTwo);
	}
}
