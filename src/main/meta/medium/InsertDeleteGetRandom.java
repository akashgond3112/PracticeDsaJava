package main.meta.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * <pre>
 * 380. Insert Delete GetRandom O(1)
 * Medium
 * Topics
 * Companies
 * Implement the RandomizedSet class:
 *
 * RandomizedSet() Initializes the RandomizedSet object.
 * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 * int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 *
 * Example 1:
 * Input
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * Output
 * [null, true, false, true, 2, true, false, 2]
 *
 * Explanation
 * RandomizedSet randomizedSet = new RandomizedSet();
 * randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
 * randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
 * randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
 * randomizedSet.insert(2); // 2 was already in the set, so return false.
 * randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
 *
 * Constraints:
 * -231 <= val <= 231 - 1
 * At most 2 * 105 calls will be made to insert, remove, and getRandom.
 * There will be at least one element in the data structure when getRandom is called.
 * Topics
 * Array
 * Hash Table
 * Math
 * Design
 * Randomized
 * </pre>*/
public class InsertDeleteGetRandom {

	/**
	 * Implementation of a RandomizedSet using only HashMap.
	 *
	 * Approach:
	 * - Uses a HashMap to store values with a placeholder value of 1
	 * - Maintains a separate size counter
	 * - For random selection, uses an iterator with random index
	 *
	 * Time Complexity:
	 * - insert: O(1) average case
	 * - remove: O(1) average case
	 * - getRandom: O(n) - iterating to a random position is linear time
	 *
	 * Space Complexity: O(n) where n is the number of unique elements inserted
	 *
	 * Note: This implementation has a significant inefficiency in the getRandom method
	 * as it requires O(n) time to iterate to a random position.
	 */
	public static class RandomizedSet {
		private final HashMap<Integer, Integer> numMap;
		private int size;

		/**
		 * Initializes an empty RandomizedSet data structure.
		 */
		public RandomizedSet() {
			numMap = new HashMap<>();
			size = 0;
		}

		/**
		 * Inserts an item val into the set if not present.
		 * Returns true if the item was not present, false otherwise.
		 *
		 * @param val The value to insert
		 * @return true if the item was not already present, false otherwise
		 */
		public boolean insert(int val) {
			if (numMap.containsKey(val)) {
				return false;
			}
			numMap.put(val, 1);
			size++;
			return true;
		}

		/**
		 * Removes an item val from the set if present.
		 * Returns true if the item was present, false otherwise.
		 *
		 * @param val The value to remove
		 * @return true if the item was present, false otherwise
		 */
		public boolean remove(int val) {
			if (!numMap.containsKey(val)) {
				return false;
			}
			numMap.remove(val);
			size--;
			return true;
		}

		/**
		 * Returns a random element from the set.
		 * Each element must have the same probability of being returned.
		 *
		 * @return A random element from the set
		 * @throws IllegalStateException if the set is empty
		 */
		public int getRandom() {
			if (size == 0) {
				throw new IllegalStateException("RandomizedSet is empty");
			}

			int idx = new Random().nextInt(size);
			Iterator<Integer> it = numMap.keySet().iterator();
			while (idx-- > 0) {
				it.next();
			}
			return it.next();
		}
	}

	/**
	 * Optimized implementation of RandomizedSet using both HashMap and ArrayList.
	 *
	 * Approach:
	 * - Uses a HashMap to store value-to-index mappings for O(1) lookups
	 * - Uses an ArrayList to store values for O(1) random access
	 * - For efficient removal, swaps the element to remove with the last element
	 * - Reuses a single Random object for better performance
	 *
	 * Time Complexity:
	 * - insert: O(1) average case
	 * - remove: O(1) average case
	 * - getRandom: O(1)
	 *
	 * Space Complexity: O(n) where n is the number of unique elements inserted
	 *
	 * Note: This implementation achieves optimal O(1) time complexity for all operations.
	 */
	public static class RandomizedSetUsingHashMapAndList {
		private final Map<Integer, Integer> numMap; // Maps value to its index in the list
		private final List<Integer> nums;           // Stores the actual values
		private final Random rand;                  // Reusable Random object

		/**
		 * Initializes an empty RandomizedSet data structure.
		 */
		public RandomizedSetUsingHashMapAndList() {
			numMap = new HashMap<>();
			nums = new ArrayList<>();
			rand = new Random();
		}

		/**
		 * Inserts an item val into the set if not present.
		 * Returns true if the item was not present, false otherwise.
		 *
		 * @param val The value to insert
		 * @return true if the item was not already present, false otherwise
		 */
		public boolean insert(int val) {
			if (numMap.containsKey(val))
				return false;

			numMap.put(val, nums.size());  // Store the index where the value will be inserted
			nums.add(val);                 // Add the value to the end of the list
			return true;
		}

		/**
		 * Removes an item val from the set if present.
		 * Returns true if the item was present, false otherwise.
		 *
		 * @param val The value to remove
		 * @return true if the item was present, false otherwise
		 */
		public boolean remove(int val) {
			if (!numMap.containsKey(val))
				return false;

			int idx = numMap.get(val);         // Get index of the element to remove
			int lastElement = nums.getLast();  // Get the last element

			nums.set(idx, lastElement);        // Replace element at idx with last element
			numMap.put(lastElement, idx);      // Update the index of the last element

			nums.removeLast();      // Remove the last element
			numMap.remove(val);                // Remove the mapping for the original value
			return true;
		}

		/**
		 * Returns a random element from the set.
		 * Each element must have the same probability of being returned.
		 *
		 * @return A random element from the set
		 * @throws IllegalStateException if the set is empty
		 */
		public int getRandom() {
			if (nums.isEmpty()) {
				throw new IllegalStateException("RandomizedSet is empty");
			}
			return nums.get(rand.nextInt(nums.size()));
		}
	}

	/**
	 * Your RandomizedSet object will be instantiated and called as such:
	 * RandomizedSet obj = new RandomizedSet();
	 * boolean param_1 = obj.insert(val);
	 * boolean param_2 = obj.remove(val);
	 * int param_3 = obj.getRandom();
	 */
}
