package main.meta.medium;

/**
 * 138. Copy List with Random Pointer
 * Medium
 * Topics
 * Companies
 * Hint
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
 * <p>
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
 * <p>
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
 * <p>
 * Return the head of the copied linked list.
 * <p>
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 * <p>
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 * <p>
 * Example 1:
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * <p>
 * Example 2:
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 * <p>
 * Example 3:
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 * <p>
 * Constraints:
 * <p>
 * 0 <= n <= 1000
 * -104 <= Node.val <= 104
 * Node.random is null or is pointing to some node in the linked list.*/
public class CopyListWithRandomPointer {

	static class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}

	static class Solution {
		/**
		 * Clones a linked list with random pointers without using extra space.
		 * Each node has a next pointer and a random pointer that can point to any node in the list.
		 * <p>
		 * Algorithm works in 3 phases:
		 * 1. Interweaving Phase:
		 *    - Create copy nodes and insert them between original nodes
		 *    - Original: A -> B -> C becomes A -> A' -> B -> B' -> C -> C'
		 *    where A', B', C' are copies
		 * <p>
		 * 2. Random Pointer Phase:
		 *    - Set random pointers for copy nodes using the interweaved structure
		 *    - If original.random = X, then copy.random = X.next (copy of X)
		 * <p>
		 * 3. Separation Phase:
		 *    - Separate the interweaved list into original and copy lists
		 *    - Restore original list's structure
		 *    - Extract the copy list
		 * <p>
		 * Time Complexity: O(N) where N is number of nodes
		 *   - Phase 1: O(N) for creating and interweaving copies
		 *   - Phase 2: O(N) for setting random pointers
		 *   - Phase 3: O(N) for separating lists
		 * <p>
		 * Space Complexity: O(1)
		 *   - No extra space used except for the copy nodes which are part of output
		 *   - No hash map or additional data structures used
		 * <p>
		 * Example:
		 * Original: 1 -> 2 -> 3, where 1.random = 3, 2.random = 1, 3.random = 2
		 * Result: Creates a deep copy with same structure but separate memory
		 *
		 * @param head Head of the original linked list
		 * @return Head of the cloned linked list
		 */
		public static Node cloneLL(Node head) {
			// Base case: empty list
			if (head == null)
				return null;

			// Phase 1: Create and interweave copy nodes
			Node temp = head;
			while (temp != null) {
				Node next = temp.next;
				Node copyNode = new Node(temp.val);

				// Insert copy node between current and next
				copyNode.next = next;
				temp.next = copyNode;
				temp = next;
			}

			// Phase 2: Set random pointers for copy nodes
			temp = head;
			while (temp != null) {
				Node copyNode = temp.next;
				if (temp.random != null) {
					copyNode.random = temp.random.next;  // Point to copy of random node
				} else {
					copyNode.random = null;
				}
				temp = temp.next.next;
			}

			// Phase 3: Separate original and copy lists
			Node dummy = new Node(-1);  // Dummy node to build copy list
			Node result = dummy;
			temp = head;

			while (temp != null) {
				// Extract copy node
				result.next = temp.next;
				result = result.next;

				// Restore original list connections
				temp.next = temp.next.next;
				temp = temp.next;
			}

			return dummy.next;  // Return head of copy list
		}
	}
}
