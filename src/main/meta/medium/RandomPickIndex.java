package main.meta.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * <pre>
 * 398. Random Pick Index
 * Medium
 * Given an integer array nums with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
 *
 * Implement the Solution class:
 * Solution(int[] nums) Initializes the object with the array nums.
 * int pick(int target) Picks a random index i from nums where nums[i] == target. If there are multiple valid i's, then each index should have an equal probability of returning.
 *
 * Example 1:
 * Input
 * ["Solution", "pick", "pick", "pick"]
 * [[[1, 2, 3, 3, 3]], [3], [1], [3]]
 * Output
 * [null, 4, 0, 2]
 *
 * Explanation
 * Solution solution = new Solution([1, 2, 3, 3, 3]);
 * solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 * solution.pick(1); // It should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 *
 * Constraints:
 * 1 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * target is an integer from nums.
 * At most 104 calls will be made to pick.
 * Topics
 * Hash Table, Math, Reservoir Sampling, Randomized
 * </pre>
 */
public class RandomPickIndex {

	/**
	 * Brute Force Solution
	 *
	 * This approach doesn't use any preprocessing and simply scans the array each time we need to pick a random
	 * element.
	 */
	static class BruteForceSolution {
		private final int[] nums;
		private final Random rand;

		/**
		 * Initializes the solution with the input array.
		 *
		 * Time Complexity: O(1) - simple assignment Space Complexity: O(n) where n is the length of nums array
		 *
		 * @param nums
		 * 		array of integers
		 * @throws IllegalArgumentException
		 * 		if nums is null or empty
		 */
		public BruteForceSolution(int[] nums) {
			if (nums == null || nums.length == 0) {
				throw new IllegalArgumentException("Input array cannot be null or empty");
			}
			this.nums = nums;
			this.rand = new Random();
		}

		/**
		 * Picks a random index of target value in the array.
		 *
		 * Time Complexity: O(n) where n is the length of nums array Space Complexity: O(k) where k is the count of
		 * occurrences of target
		 *
		 * @param target
		 * 		the value to randomly pick
		 * @return random index of target, or -1 if target is not found
		 */
		public int pick(int target) {
			List<Integer> indices = new ArrayList<>();

			// Find all indices where nums[i] == target
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] == target) {
					indices.add(i);
				}
			}

			// If target is not found, return -1
			if (indices.isEmpty()) {
				return -1;
			}

			// Pick a random index
			return indices.get(rand.nextInt(indices.size()));
		}
	}

	/**
	 * Optimal Solution
	 *
	 * This approach uses a HashMap for O(1) lookup time when picking a random occurrence. It addresses the inefficiency
	 * in the original code by storing indices instead of duplicating values.
	 */
	static class OptimalSolution {
		// Map of value -> list of indices where this value appears
		private final HashMap<Integer, List<Integer>> valueToIndices;
		private final Random rand;

		/**
		 * Initializes the solution with preprocessed data for efficient lookups.
		 *
		 * Time Complexity: O(n) where n is the length of nums array Space Complexity: O(n) to store all indices in the
		 * HashMap
		 *
		 * @param nums
		 * 		array of integers
		 * @throws IllegalArgumentException
		 * 		if nums is null or empty
		 */
		public OptimalSolution(int[] nums) {
			if (nums == null || nums.length == 0) {
				throw new IllegalArgumentException("Input array cannot be null or empty");
			}

			this.valueToIndices = new HashMap<>();
			this.rand = new Random();

			// Preprocess the array to build the value -> indices mapping
			for (int i = 0; i < nums.length; i++) {
				valueToIndices.putIfAbsent(nums[i], new ArrayList<>());
				valueToIndices.get(nums[i]).add(i);
			}
		}

		/**
		 * Picks a random index of target value in the array.
		 *
		 * Time Complexity: O(1) for HashMap lookup and random selection Space Complexity: O(1) as no additional space
		 * is used during the operation
		 *
		 * @param target
		 * 		the value to randomly pick
		 * @return random index of target, or -1 if target is not found
		 */
		public int pick(int target) {
			if (!valueToIndices.containsKey(target)) {
				return -1;  // Target value not found
			}

			List<Integer> indices = valueToIndices.get(target);
			return indices.get(rand.nextInt(indices.size()));
		}
	}

	/**
	 * Reservoir Sampling Solution
	 *
	 * This approach is memory-efficient when we don't want to preprocess the array. It's especially useful for
	 * streaming data or very large arrays.
	 */
	static class ReservoirSolution {
		private final int[] nums;
		private final Random rand;

		/**
		 * Initializes the solution with the input array.
		 *
		 * Time Complexity: O(1) - simple assignment Space Complexity: O(n) where n is the length of nums array (just
		 * storing the reference)
		 *
		 * @param nums
		 * 		array of integers
		 * @throws IllegalArgumentException
		 * 		if nums is null or empty
		 */
		public ReservoirSolution(int[] nums) {
			if (nums == null || nums.length == 0) {
				throw new IllegalArgumentException("Input array cannot be null or empty");
			}
			this.nums = nums;
			this.rand = new Random();
		}

		/**
		 * Picks a random index of target value using reservoir sampling.
		 *
		 * Time Complexity: O(n) where n is the length of nums array Space Complexity: O(1) as we only store a single
		 * result
		 *
		 * @param target
		 * 		the value to randomly pick
		 * @return random index of target, or -1 if target is not found
		 */
		public int pick(int target) {
			int result = -1;
			int count = 0;

			// Reservoir sampling
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] == target) {
					count++; // Increment count of target values seen

					// With probability 1/count, replace the result
					if (rand.nextInt(count) == 0) {
						result = i;
					}
				}
			}

			return result;
		}
	}
}
