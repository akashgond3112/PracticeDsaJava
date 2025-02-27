package main.meta.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * Problem statement
 * You are given an array ‘ARR’ containing ‘N’ integers.
 *
 * Return all the unique triplets [ARR[i], ARR[j], ARR[k]] such that i != j, j != k and k != i and their sum is equal to zero.
 *
 * Example:
 * Input: ‘N’ = 5
 * 'ARR' =  [-1, -1, 2, 0, 1]
 *
 * Output:
 * -1 -1 2
 * -1 0 1
 *
 * Explanation:
 * (-1 -1 +2) = (-1 +0 +1) = 0.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1:
 * 5
 * -1 -1 2 0 1
 * Sample Output 1 :
 * -1 -1 2
 * -1 0 1
 * Explanation Of Sample Input 1:
 * (-1 -1 +2) = (-1 +0 +1) = 0.
 * Sample Input 2:
 * 4
 * 0 0 0 0
 * Sample Output 2 :
 * 0 0 0
 * Constraints:
 * 1  <= N <= 1000
 * 1 <= ARR[i] <= 1000
 * Time Limit: 1 sec
 * </pre>*/

public class ThreeSum {

	/**
	 * Time complexity: O(n^2 ) Space complexity: O(1) or O(n) depending on the sorting
	 * algorithm.
	 */
	public static List<List<Integer>> tripletOptimal(int n, int[] arr) {
		// Write your code here.
		Arrays.sort(arr);
		Set<List<Integer>> triplets = new HashSet<>();
		for (int i = 0; i < n; i++) {
			if (i > 0 && arr[i] == arr[i - 1])
				continue;
			int j = i + 1;
			int k = n - 1;
			while (j < k) {
				int sum = arr[i] + arr[j] + arr[k];
				if (sum < 0) {
					j++;
				} else if (sum > 0) {
					k--;
				} else {
					List<Integer> temp = Arrays.asList(arr[i], arr[j], arr[k]);
					triplets.add(temp);
					j++;
					k--;
					while (j < k && arr[j] == arr[j - 1]) {
						j++;
					}
					while (j < k && arr[k] == arr[k + 1]) {
						k--;
					}
				}
			}
		}
		return new ArrayList<>(triplets);
	}

	/**
	 * Time complexity: O(n^2 ) Space complexity: O(m)
	 * Where m is the number of triplets and n is
	 * the length of the given array.
	 */
	public static List<List<Integer>> tripletBetter(int n, int[] arr) {
		// Write your code here.
		Set<List<Integer>> triplets = new HashSet<>();
		for (int i = 0; i < n; i++) {
			Set<Integer> set = new HashSet<>();
			for (int j = i + 1; j < n; j++) {
				int third = -(arr[i] + arr[j]);
				if (set.contains(third)) {
					List<Integer> temp = Arrays.asList(arr[i], arr[j], third);
					Collections.sort(temp);
					triplets.add(temp);
				}
				set.add(arr[j]);
			}
		}
		return new ArrayList<>(triplets);
	}

	/**
	 * Time complexity: O(n^3 ) Space complexity: O(m)
	 * Where m is the number of triplets and n is
	 * the length of the given array.
	 */
	public static List<List<Integer>> tripletBruteForce(int n, int[] arr) {
		// Write your code here.
		Set<List<Integer>> triplets = new HashSet<>();
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				for (int k = j + 1; k < n; k++) {
					if (arr[i] + arr[j] + arr[k] == 0) {
						List<Integer> triplet = new ArrayList<>();
						triplet.add(arr[i]);
						triplet.add(arr[j]);
						triplet.add(arr[k]);
						Collections.sort(triplet);
						triplets.add(triplet);
					}
				}
			}
		}
		return new ArrayList<>(triplets);
	}

	public static void main(String[] args) {
		int[] arr = new int[] { -1, -1, 2, 0, 1 };
		System.out.println(tripletBruteForce(arr.length, arr));
		System.out.println(tripletBetter(arr.length, arr));
		System.out.println(tripletOptimal(arr.length, arr));

	}
}
