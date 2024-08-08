package main.dsa.linear.LinkedList.SinglyLinkedList;

/*
Problem statement
You are given two non-negative numbers 'num1' and 'num2' represented in the form of linked lists.



The digits in the linked lists are stored in reverse order, i.e. starting from least significant digit (LSD) to the most significant digit (MSD), and each of their nodes contains a single digit.



Calculate the sum of the two numbers and return the head of the sum list.



Example :
Input:
'num1' : 1 -> 2 -> 3 -> NULL
'num2' : 4 -> 5 -> 6 -> NULL

Output: 5 -> 7 -> 9 -> NULL

Explanation: 'num1' represents the number 321 and 'num2' represents 654. Their sum is 975.


Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
3
1 2 3
3
4 5 6


Sample Output 1 :
5 7 9


Explanation for Sample Input 1 :
'num1' represents the number 321 and 'num2' represents 654. Their sum is 975.


Sample Input 2 :
2
0 1
1
0


Sample Output 2 :
0 1


Explanation for Sample Input 2 :
'num1' represents 10 and 'num2' represents 0. Their sum is 10.


Sample Input 3 :
1
2
2
9 9


Sample Output 3 :
1 0 1


Explanation for Sample Input 3 :
'num1' represents 2 and 'num2' represents 99. Their sum is 101.


Expected Time Complexity :
The expected time complexity is O('m' + 'n').


Constraints :
1 <= 'm', 'n' <= 5 * 10^4
0 <= 'data' in any Linked List node <= 9

The numbers do not contain any leading zeros.
If the number is zero, then there is one node having 'data' = 0.

Time Limit: 1 sec
*/

public class AddTwoNumbers extends SinglyLinkedList {

	static Node addTwoNumbers(Node head1, Node head2) {
		Node t1 = head1;
		Node t2 = head2;
		Node dummy = new Node(-1); // Dummy node to simplify the result list construction
		Node curr = dummy;
		int carry = 0;

		while (t1 != null || t2 != null) {
			int sum = carry;

			if (t1 != null) {
				sum += t1.data;
				t1 = t1.next;
			}
			if (t2 != null) {
				sum += t2.data;
				t2 = t2.next;
			}

			// Create a new node with the digit value and move to the next
			curr.next = new Node(sum % 10);
			carry = sum / 10;
			curr = curr.next;
		}

		// If there's a remaining carry, add a new node for it
		if (carry > 0) {
			curr.next = new Node(carry);
		}

		return dummy.next; // Return the next node after dummy which is the actual result list's head
	}


	public static void main(String[] args) {
		Node head1 = null;

		// Push elements onto the list
		head1 = push(head1, 3);
		head1 = push(head1, 2);
		head1 = push(head1, 1);

		Node head2 = null;

		// Push elements onto the list
		head2 = push(head2, 5);
		head2 = push(head2, 6);
		head2 = push(head2, 8);
		head2 = push(head2, 9);

		// Print the list
		printList(head1);
		System.out.println();
		printList(head2);
		System.out.println();
		Node addTwoNumbers = addTwoNumbers(head1, head2);
		printList(addTwoNumbers);
	}
}
