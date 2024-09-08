package main.recursion.backtracking.hard;

import java.util.ArrayList;
import java.util.List;

/*
60. Permutation Sequence
Hard
Topics
Companies
The set [1, 2, 3, ..., n] contains a total of n! unique permutations.

By listing and labeling all the permutations in order, we get the following sequence for n = 3:
"123"
"132"
"213"
"231"
"312"
"321"

Given n and k, return the kth permutation sequence.

Example 1:
Input: n = 3, k = 3
Output: "213"

Example 2:
Input: n = 4, k = 9
Output: "2314"

Example 3:
Input: n = 3, k = 1
Output: "123"

Constraints:
1 <= n <= 9
1 <= k <= n!
*/
public class PermutationSequence {

	public static String getKthPermutation(int n, int k) {
		// Factorial calculation and list initialization
		int fact = 1;
		List<Integer> list = new ArrayList<>();

		// Populate the list with numbers from 1 to n and calculate (n-1)!
		for (int i = 1; i < n; i++) {
			fact *= i;
			list.add(i);
		}
		list.add(n); // Add 'n' to the list at the end

		// StringBuilder to store the k-th permutation
		StringBuilder sb = new StringBuilder();

		// Adjust k to 0-based index
		k = k - 1;

		// Generate the k-th permutation
		while (true) {
			// Find the index of the current digit to be placed in the permutation
			int index = k / fact;
			sb.append(list.get(index)); // Append the digit to the result
			list.remove(index); // Remove the used digit from the list

			// Break if all digits have been used
			if (list.isEmpty()) {
				break;
			}

			// Recalculate k and factorial for the remaining digits
			k = k % fact;
			fact = fact / list.size();
		}

		// Return the k-th permutation as a string
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(getKthPermutation(3, 3));
	}

}
