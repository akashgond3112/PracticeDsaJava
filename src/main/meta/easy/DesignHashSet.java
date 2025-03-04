package main.meta.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 705. Design HashSet
 * Easy
 * Topics
 * Companies
 * Design a HashSet without using any built-in hash table libraries.
 *
 * Implement MyHashSetUsingArray class:
 *
 * void add(key) Inserts the value key into the HashSet.
 * bool contains(key) Returns whether the value key exists in the HashSet or not.
 * void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
 *
 * Example 1:
 *
 * Input
 * ["MyHashSetUsingArray", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 * Output
 * [null, null, null, true, false, null, true, null, false]
 *
 * Explanation
 * MyHashSetUsingArray myHashSet = new MyHashSetUsingArray();
 * myHashSet.add(1);      // set = [1]
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(1); // return True
 * myHashSet.contains(3); // return False, (not found)
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(2); // return True
 * myHashSet.remove(2);   // set = [1]
 * myHashSet.contains(2); // return False, (already removed)
 *
 * Constraints:
 * 0 <= key <= 106
 * At most 104 calls will be made to add, remove, and contains.
 * Topics
 * Array
 * Hash Table
 * Linked List
 * Design
 * Hash Function
 * </pre>
 */
public class DesignHashSet {

	/**
	 * <pre>
	 * Time & Space Complexity
	 * Time complexity: O(n) for each function call.
	 * Space complexity: O(n)
	 * </pre>
	 */
	static public class MyHashSetUsingList {
		private final List<Integer> data;

		public MyHashSetUsingList() {
			data = new ArrayList<>();
		}

		public void add(int key) {
			if (!data.contains(key)) {
				data.add(key);
			}
		}

		public void remove(int key) {
			data.remove(Integer.valueOf(key));
		}

		public boolean contains(int key) {
			return data.contains(key);
		}
	}

	/**
	 * <pre>
	 * Time complexity: O(1) for each function call.
	 * Space complexity: O(1000000)
	 * since the key is in the range [0,1000000].
	 * </pre>
	 */
	public static class MyHashSetUsingArray {
		private final boolean[] data;

		public MyHashSetUsingArray() {
			data = new boolean[1000001];
		}

		public void add(int key) {
			data[key] = true;
		}

		public void remove(int key) {
			data[key] = false;
		}

		public boolean contains(int key) {
			return data[key];
		}
	}

	/**
	 * <pre>
	 * Time complexity: O(n/k) for each function call.
	 * Space complexity: O(k+m)
	 * Where n is the number of keys, k is the size of the set (10000) and m is the number of unique keys.
	 * </pre>
	 */
	public static class MyHashSetUsingLinkedList {

		private static class ListNode {
			int key;
			ListNode next;

			ListNode(int key) {
				this.key = key;
			}
		}

		private final ListNode[] set;

		public MyHashSetUsingLinkedList() {
			set = new ListNode[10000];
			for (int i = 0; i < set.length; i++) {
				set[i] = new ListNode(0);
			}
		}

		// Common method to find the previous node of the target key
		private ListNode findPrevNode(int key) {
			ListNode cur = set[key % set.length];
			while (cur.next != null) {
				if (cur.next.key == key) {
					return cur;
				}
				cur = cur.next;
			}
			return null;
		}

		public void add(int key) {
			// If key already exists, do nothing
			if (contains(key)) {
				return;
			}

			// Add at the end of the list
			ListNode cur = set[key % set.length];
			while (cur.next != null) {
				cur = cur.next;
			}
			cur.next = new ListNode(key);
		}

		public void remove(int key) {
			ListNode prevNode = findPrevNode(key);
			if (prevNode != null) {
				prevNode.next = prevNode.next.next;
			}
		}

		public boolean contains(int key) {
			return findPrevNode(key) != null;
		}
	}
}
