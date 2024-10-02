package main.dsa.linear.Array.learning;

/*
Example 1:

Input: prices = {1, 1, 0, 1, 1, 1}

Output: 3

Explanation: There are two consecutive 1’s and three consecutive 1’s in the array out of which maximum is 3.

Input: prices = {1, 0, 1, 1, 0, 1}

Output: 2

Explanation: There are two consecutive 1's in the array.
*/
public class CountMaximumConsecutiveOnesInTheArray {

	static int findMaxConsecutiveOnes(int[] nums) {
		int max = Integer.MIN_VALUE;
		int count = 0;
		for (int num : nums) {
			if (num == 1) {
				count++;
				max = Math.max(max, count);

			} else {
				count = 0;
			}

		}

		return max;
	}

	public static void main(String[] args) {
		System.out.println(findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1}));
	}
}
