package main.meta.medium;

import java.util.Random;

/**
 * 528. Random Pick with Weight Medium Topics Companies You are given a 0-indexed array of positive integers w where
 * w[i] describes the weight of the ith index.
 *
 * You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1]
 * (inclusive) and returns it. The probability of picking an index i is w[i] / sum(w).
 *
 * For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), and the probability
 * of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
 *
 *
 * Example 1:
 *
 * Input ["Solution","pickIndex"] [[[1]],[]] Output [null,0]
 *
 * Explanation Solution solution = new Solution([1]); solution.pickIndex(); // return 0. The only option is to return 0
 * since there is only one element in w. Example 2:
 *
 * Input ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"] [[[1,3]],[],[],[],[],[]] Output
 * [null,1,1,1,1,0]
 *
 * Explanation Solution solution = new Solution([1, 3]); solution.pickIndex(); // return 1. It is returning the second
 * element (index = 1) that has a probability of 3/4. solution.pickIndex(); // return 1 solution.pickIndex(); // return
 * 1 solution.pickIndex(); // return 1 solution.pickIndex(); // return 0. It is returning the first element (index = 0)
 * that has a probability of 1/4.
 *
 * Since this is a randomization problem, multiple answers are allowed. All of the following outputs can be considered
 * correct: [null,1,1,1,1,0] [null,1,1,1,1,1] [null,1,1,1,0,0] [null,1,1,1,0,1] [null,1,0,1,0,0] ...... and so on.
 *
 *
 * Constraints:
 *
 * 1 <= w.length <= 104 1 <= w[i] <= 105 pickIndex will be called at most 104 times.
 */
public class RandomPickWithWeight {

	/**
	 * Approach: Prefix Sum + Binary Search
	 *
	 * 1. Calculate the prefix sum of the input array
	 * 2. Generate a random number in the range [0, totalWeight)
	 * 3. Perform binary search to find the index
	 *
	 * Time Complexity: O(n) for initialization, O(log n) for pickIndex()
	 * Space Complexity: O(n) for prefix sum array
	 */
	static class Solution {
		private final int[] prefixSum;
		private final int totalWeight;
		private final Random rand;

		public Solution(int[] w) {
			if (w == null || w.length == 0) {
				throw new IllegalArgumentException("Input array cannot be null or empty");
			}

			// Initialize prefix sum array
			prefixSum = new int[w.length];
			prefixSum[0] = w[0];

			// Calculate prefix sums
			for (int i = 1; i < w.length; i++) {
				prefixSum[i] = prefixSum[i - 1] + w[i];
			}

			totalWeight = prefixSum[w.length - 1];
			rand = new Random();
		}

		public int pickIndex() {
			// Generate random number in range [0, totalWeight)
			int target = rand.nextInt(totalWeight);

			// Binary search to find the index
			int left = 0;
			int right = prefixSum.length - 1;

			while (left < right) {
				int mid = left + (right - left) / 2;

				if (prefixSum[mid] <= target) {
					left = mid + 1;
				} else {
					right = mid;
				}
			}

			return left;
		}

		// Optional: Method to validate input weights
		private void validateWeights(int[] w) {
			if (w == null || w.length == 0) {
				throw new IllegalArgumentException("Weights array cannot be null or empty");
			}

			for (int weight : w) {
				if (weight < 0) {
					throw new IllegalArgumentException("Weights cannot be negative");
				}
			}
		}
	}

	/**
	 * Your Solution object will be instantiated and called as such:
	 * Solution obj = new Solution(w);
	 * int param_1 = obj.pickIndex();
	 */
}
