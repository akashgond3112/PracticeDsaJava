package main.dsa.linear.stack;

import main.dsa.linear.LinkedList.DoublyLinkedList.DoublyLinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU Cache Medium Topics Companies Design a data structure that follows the constraints of a Least Recently Used
 * (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity. int get(int key) Return the value of the
 * key if the key exists, otherwise return -1. void put(int key, int value) Update the value of the key if the key
 * exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this
 * operation, evict the least recently used key. The functions get and put must each run in O(1) average time
 * complexity.
 *
 *
 *
 * Example 1:
 *
 * Input ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"] [[2], [1, 1], [2, 2], [1], [3, 3],
 * [2], [4, 4], [1], [3], [4]] Output [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation LRUCache = new LRUCache(2); lRUCache.put(1, 1); // cache is {1=1} lRUCache.put(2, 2); // cache is {1=1,
 * 2=2} lRUCache.get(1);    // return 1 lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found) lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found) lRUCache.get(3);    // return 3 lRUCache.get(4);    // return 4
 *
 *
 * Constraints:
 *
 * 1 <= capacity <= 3000 0 <= key <= 104 0 <= value <= 105 At most 2 * 105 calls will be made to get and put.
 */
public class LRUCache {

	static class Node {
		int key;
		int value;
		Node next;
		Node prev;

		public Node(int key, int value) {
			this.key = key;
			this.value = value;
			this.next = null;
			this.prev = null;
		}
	}

	private final Map<Integer, Node> map;
	private final int capacity;
	private final Node head;
	private final Node tail;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.map = new HashMap<>();

		// Initialize dummy head and tail nodes
		head = new Node(0, 0);
		tail = new Node(0, 0);
		head.next = tail;
		tail.prev = head;
	}

	// Remove a node from the list
	private void removeNode(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}

	// Add a node right after the head
	private void addNodeAfterHead(Node node) {
		node.next = head.next;
		node.prev = head;
		head.next.prev = node;
		head.next = node;
	}

	public int get(int key) {
		if (!map.containsKey(key)) {
			return -1;
		}
		Node node = map.get(key);
		// Move the accessed node to the head (most recently used)
		removeNode(node);
		addNodeAfterHead(node);
		return node.value;
	}

	public void put(int key, int value) {
		if (map.containsKey(key)) {
			// Update the value of the existing node and move it to the head
			Node node = map.get(key);
			node.value = value;
			removeNode(node);
			addNodeAfterHead(node);
		} else {
			if (map.size() == capacity) {
				// Remove the least recently used node (node before tail)
				Node lru = tail.prev;
				map.remove(lru.key);
				removeNode(lru);
			}
			// Insert the new node and add it to the head
			Node newNode = new Node(key, value);
			map.put(key, newNode);
			addNodeAfterHead(newNode);
		}
	}

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(4);
		cache.put(1, 1);
		cache.put(2, 2);
		cache.get(1);    // Returns 1, moves (1,1) to the front
		cache.put(3, 3); // Adds (3,3), (2,2) becomes LRU
		cache.get(2);    // Returns 2, moves (2,2) to the front
		cache.put(4, 4); // Adds (4,4), (3,3) becomes LRU
		cache.get(1);    // Returns 1, moves (1,1) to the front
		cache.get(3);    // Returns -1, as (3,3) was evicted
		cache.get(4);    // Returns 4, moves (4,4) to the front

		System.out.println(cache.get(1));  // Prints 1
	}
}
