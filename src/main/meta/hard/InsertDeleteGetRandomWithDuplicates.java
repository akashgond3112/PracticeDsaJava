package main.meta.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class InsertDeleteGetRandomWithDuplicates {

	/**
	 * Implementation of RandomizedMultiSet that supports duplicate values.
	 *
	 * Approach:
	 * - Uses a HashMap to store values with their frequency counts
	 * - Uses an ArrayList to store all values (including duplicates) for random access
	 * - For efficient removal, swaps the element to remove with the last element
	 *
	 * Time Complexity:
	 * - insert: O(1) average case
	 * - remove: O(1) average case
	 * - getRandom: O(1)
	 *
	 * Space Complexity: O(n) where n is the total number of elements inserted (including duplicates)
	 */
	public static class RandomizedMultiSet {
		private final Map<Integer, List<Integer>> valueToIndices; // Maps value to list of its indices in the array
		private final List<Integer> values;                      // Stores all values including duplicates
		private final Random rand;

		/**
		 * Initializes an empty RandomizedMultiSet data structure.
		 */
		public RandomizedMultiSet() {
			valueToIndices = new HashMap<>();
			values = new ArrayList<>();
			rand = new Random();
		}

		/**
		 * Inserts a value into the multiset. Returns true always as duplicate values are allowed.
		 *
		 * @param val
		 * 		The value to insert
		 * @return true (always successful)
		 */
		public boolean insert(int val) {
			// Add value to the end of the values list
			values.add(val);
			int insertedIndex = values.size() - 1;

			// Update the indices map
			if (!valueToIndices.containsKey(val)) {
				valueToIndices.put(val, new ArrayList<>());
			}
			valueToIndices.get(val).add(insertedIndex);

			return true;
		}

		/**
		 * Removes one occurrence of the value from the multiset if present. Returns true if the value was present,
		 * false otherwise.
		 *
		 * @param val
		 * 		The value to remove
		 * @return true if the value was present, false otherwise
		 */
		public boolean remove(int val) {
			// Check if the value exists in the multiset
			if (!valueToIndices.containsKey(val) || valueToIndices.get(val).isEmpty()) {
				return false;
			}

			// Get the last index of this value
			List<Integer> indices = valueToIndices.get(val);
			int indexToRemove = indices.getLast();
			indices.removeLast();

			// If this was the last occurrence of the value, remove its entry from the map
			if (indices.isEmpty()) {
				valueToIndices.remove(val);
			}

			// If this is not the last element in the values list, swap with the last element
			int lastIndex = values.size() - 1;
			if (indexToRemove != lastIndex) {
				// Get the last value
				int lastValue = values.get(lastIndex);

				// Update the values list (swap with last element)
				values.set(indexToRemove, lastValue);

				// Update the indices list for the swapped element
				List<Integer> lastValueIndices = valueToIndices.get(lastValue);
				lastValueIndices.remove(Integer.valueOf(lastIndex)); // Remove the last index
				lastValueIndices.add(indexToRemove);                // Add the new index
			}

			// Remove the last element from the values list
			values.remove(lastIndex);

			return true;
		}

		/**
		 * Returns a random element from the multiset. Each element must have the same probability of being returned.
		 *
		 * @return A random element from the multiset
		 * @throws IllegalStateException
		 * 		if the multiset is empty
		 */
		public int getRandom() {
			if (values.isEmpty()) {
				throw new IllegalStateException("RandomizedMultiSet is empty");
			}

			return values.get(rand.nextInt(values.size()));
		}

		/**
		 * Returns the count of a specific value in the multiset.
		 *
		 * @param val
		 * 		The value to count
		 * @return The number of occurrences of the value
		 */
		public int count(int val) {
			if (!valueToIndices.containsKey(val)) {
				return 0;
			}
			return valueToIndices.get(val).size();
		}

		/**
		 * Returns the total number of elements in the multiset.
		 *
		 * @return The size of the multiset
		 */
		public int size() {
			return values.size();
		}
	}
}
