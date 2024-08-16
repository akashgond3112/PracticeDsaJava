package main.dsa.linear.LinkedList.DoublyLinkedList;

import main.dsa.nonlinear.tree.binary.Pair;

import java.util.ArrayList;
import java.util.List;

/*
Problem statement
A doubly-linked list is a data structure that consists of sequentially linked nodes,
and the nodes have reference to both the previous and the next nodes in the sequence of nodes.

You are given a sorted doubly linked list of size 'n', consisting of distinct positive integers, and a number 'k'.

Find out all the pairs in the doubly linked list with sum equal to 'k'.

Example :
Input: Linked List: 1 <-> 2 <-> 3 <-> 4 <-> 9 and 'k' = 5

Output: (1, 4) and (2, 3)

Explanation: There are 2 pairs in the linked list having sum 'k' = 5.
Detailed explanation ( Input/output format, Notes, Images )
Sample input 1:
5
1 2 3 4 9
5

Sample output 1
2
1 4
2 3

Explanation for sample input 1 :
There are 2 pairs in the linked list having sum equal to 'k' (= 5).

Sample input 2:
5
1 10 11 12 27
7

Sample output 2:
0

Explanation for sample output 2
There is no pair in the linked list with sum equal to 'k' (= 7).

Expected time complexity :
The expected time complexity is O('n').

Constraints:
2 <= 'n' <= 10 ^ 4
1 <= 'data' in any node <= 10 ^ 4
1 <= 'k' <= 10 ^ 4

'data' in every node is distinct.

Time limit: 1 second
*/
public class FindPairsWithGivenSumInDoublyLinkedList extends DoublyLinkedList {

	public static List<Pair<Integer, Integer>> findPair(Node head, int k) {
		// Write your code here.
		Node left = head;

		Node right = DoublyLinkedList.getTail(head);

		List<Pair<Integer, Integer>> list = new ArrayList<>();

		while (left.data < right.data) {

			if (left.data + right.data == k) {
				list.add(new Pair<>(left.data, right.data));
				left = left.next;
				right = right.prev;
			} else if (left.data + right.data < k) {
				left = left.next;
			} else {
				right = right.prev;
			}
		}

		return list;

	}

	public static void main(String[] args) {
		int[] data = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Node head = push(data);
		printList(head);

		System.out.print("\n Delete Head \n");
		List<Pair<Integer, Integer>> pair = findPair(head, 5);
		pair.forEach(System.out::println);
	}
}
