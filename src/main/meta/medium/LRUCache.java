package main.meta.medium;

import java.util.HashMap;
import java.util.Map;

/**
 *146. LRU Cache
 * Medium
 * Topics
 * Companies
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 *
 * Example 1:
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 *
 *
 * Constraints:
 *
 * 1 <= capacity <= 3000
 * 0 <= key <= 104
 * 0 <= value <= 105
 * At most 2 * 105 calls will be made to get and put. */

/**
 * A Least Recently Used (LRU) Cache implementation that provides O(1) time complexity
 * for both get and put operations. This cache maintains items in order of their usage,
 * automatically removing the least recently used item when the cache reaches its capacity.
 *
 * Implementation Details:
 * - Uses a HashMap for O(1) lookups
 * - Uses a doubly-linked list to maintain usage order
 * - Maintains dummy head and tail nodes to simplify list operations
 *
 * Space Complexity: O(capacity) where capacity is the maximum number of items in the cache
 */
public class LRUCache {
	/**
	 * Node class for the doubly-linked list. Each node contains a key-value pair
	 * and references to the next and previous nodes.
	 *
	 * Space Complexity: O(1) per node
	 */
	static class Node {
		int key;
		int value;
		Node next;
		Node prev;

		/**
		 * Constructs a new Node with the given key and value.
		 *
		 * @param key   the key for the cache entry
		 * @param value the value associated with the key
		 */
		public Node(int key, int value) {
			this.key = key;
			this.value = value;
			this.next = null;
			this.prev = null;
		}
	}

	private final Map<Integer, Node> map;     // For O(1) lookups
	private final int capacity;               // Maximum cache size
	private final Node head;                  // Dummy head node
	private final Node tail;                  // Dummy tail node

	/**
	 * Initializes an empty LRU cache with the specified capacity.
	 * Creates dummy head and tail nodes to simplify list operations.
	 *
	 * @param capacity the maximum number of key-value pairs the cache can hold
	 * @throws IllegalArgumentException if capacity is negative
	 * Time Complexity: O(1)
	 * Space Complexity: O(1)
	 */
	public LRUCache(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Capacity must be non-negative");
		}
		this.capacity = capacity;
		this.map = new HashMap<>();

		// Initialize dummy head and tail nodes
		head = new Node(0, 0);
		tail = new Node(0, 0);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Removes a node from the doubly-linked list.
	 * Does not modify the HashMap.
	 *
	 * @param node the node to be removed
	 * Time Complexity: O(1)
	 */
	private void removeNode(Node node) {
		Node prevNode = node.prev;
		Node afterNode = node.next;

		prevNode.next = afterNode;
		afterNode.prev = prevNode;
	}

	/**
	 * Adds a node right after the head (marks it as most recently used).
	 * Does not modify the HashMap.
	 *
	 * @param node the node to be added after head
	 * Time Complexity: O(1)
	 */
	private void addNodeAfterHead(Node node) {
		node.next = head.next;
		node.prev = head;
		head.next.prev = node;
		head.next = node;
	}

	/**
	 * Retrieves the value associated with the given key if it exists in the cache.
	 * Also marks the accessed item as most recently used.
	 *
	 * @param key the key whose associated value is to be returned
	 * @return the value associated with the key, or -1 if the key doesn't exist
	 * Time Complexity: O(1)
	 */
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

	/**
	 * Inserts or updates a key-value pair in the cache.
	 * If the key exists, updates its value and marks it as most recently used.
	 * If the key doesn't exist:
	 *   - If the cache is at capacity, removes the least recently used item
	 *   - Adds the new key-value pair as the most recently used item
	 *
	 * @param key   the key to be inserted or updated
	 * @param value the value to be associated with the key
	 * Time Complexity: O(1)
	 */
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
}