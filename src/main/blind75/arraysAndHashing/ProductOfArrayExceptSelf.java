package main.blind75.arraysAndHashing;

/**
 * Product of Array Except Self Medium Topics Companies Hint Given an integer array nums, return an array answer such
 * that answer[i] is equal to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4] Output: [24,12,8,6] Example 2:
 *
 * Input: nums = [-1,1,0,-3,3] Output: [0,0,9,0,0]
 *
 * Constraints:
 *
 * 2 <= nums.length <= 105 -30 <= nums[i] <= 30 The product of any prefix or suffix of nums is guaranteed to fit in a
 * 32-bit integer.
 *
 * Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space
 * for space complexity analysis.)
 */
public class ProductOfArrayExceptSelf {

	/**
	 * Time complexity: O(n^2 ) Space complexity: O(1) since the output array is excluded from space analysis.
	 */
	public int[] productExceptSelfBruteForce(int[] nums) {
		int n = nums.length;
		int[] res = new int[n];

		for (int i = 0; i < n; i++) {
			int product = 1;
			for (int j = i + 1; j < n; j++) {
				product *= nums[j];
			}
			res[i] = product;
		}
		return res;
	}

	/**
	 * Time complexity: O(n) Space complexity: O(1) since the output array is excluded from space analysis.
	 */
	public int[] productExceptSelfUsingDivision(int[] nums) {
		int product = 1;
		int zeros = 0;
		for (int num : nums) {
			if (num == 0) {
				zeros++;
			} else {
				product *= num;
			}
		}

		if (zeros > 1) {
			return new int[] { nums.length };
		}
		int[] res = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			if (zeros > 0) {
				res[i] = (nums[i] == 0) ? product : 0;
			} else {
				res[i] = product / nums[i];
			}
		}
		return res;
	}

	/**
	 * Time complexity: O(n) Space complexity: O(n)
	 */
	public int[] productExceptSelfUsingPrefixSuffix(int[] nums) {
		int n = nums.length;
		int[] res = new int[n];
		int[] prefixSum = new int[n];
		int[] suffixSum = new int[n];

		prefixSum[0] = 1;
		suffixSum[n - 1] = 1;
		for (int i = 1; i < n; i++) {
			prefixSum[i] = nums[i - 1] * prefixSum[i - 1];
		}

		for (int i = n - 2; i >= 0; i--) {
			suffixSum[i] = nums[i + 1] * suffixSum[i + 1];
		}
		for (int i = 0; i < n; i++) {
			res[i] = prefixSum[i] * suffixSum[i];
		}
		return res;
	}

	/**
	 * Time complexity: O(n) Space complexity: O(1) since the output array is excluded from space analysis.
	 */
	public int[] productExceptSelfUsingPrefixSuffixOptimal(int[] nums) {
		int n = nums.length;
		int[] res = new int[n];

		res[0] = 1;
		for (int i = 1; i < n; i++) {
			res[i] = res[i - 1] * nums[i - 1];
		}

		int postFix = 1;
		for (int i = n - 1; i >= 0; i--) {
			res[i] = res[i] * postFix;
			postFix *= nums[i];
		}
		return res;
	}

}
