package main.dsa.linear.Array.learning.hard;

import java.util.Arrays;

/*
Problem statement
You are given an array of ‘N’ integers where each integer value is between ‘1’ and ‘N’. Each integer appears exactly once except for ‘P’, which appears exactly twice, and ‘Q’, which is missing.

Your task is to find ‘P’ and ‘Q’ and return them respectively.

Detailed explanation ( Input/output format, Notes, Images )
Constraints:
2 <= N <= 5 * 10^4
1 <= data <= N

Where ‘N’ is the size of the array and ‘data’ denotes the value of the elements of the array.
Sample Input 1:
4
1 2 3 2
Sample Output 1:
2 4
Explanation Of Sample Input 1:
Input: ‘N’ = 4
‘A’ = [1, 2, 3, 2]
Output: {2, 4} - The integer appearing twice is ‘2’, and the integer missing is ‘4’.
Sample Input 2:
3
1 2 1
Sample Output 2:
1 3
*/
public class MissingAndRepeatingNumbers {

	public static int[] findMissingRepeatingNumbersOptimal(int[] a) {
		// Write your code here
		int n = a.length;
		int xor = 0;
		for (int i = 0; i < n; i++) {
			xor ^= a[i];
			xor ^= (i + 1);
		}

		int bitNo = 0;
		while ((xor & (1 << bitNo)) == 0) {
			bitNo++;
		}

		int zeros = 0;
		int ones = 0;
		for (int j : a) {
			if ((j & (1 << bitNo)) != 0) {
				ones ^= j;
			} else {
				zeros ^= j;
			}
		}

		for (int i = 1; i <= n; i++) {
			if ((i & (1 << bitNo)) != 0) {
				ones ^= i;
			} else {
				zeros ^= i;
			}
		}

		int count = 0;
		for (int j : a) {
			if (j == zeros)
				count++;
		}
		if (count == 2)
			return new int[] { zeros, ones };
		else
			return new int[] { zeros, ones };
	}


	public static int[] findMissingRepeatingNumbersBetter(int[] a) {
		// Write your code here
		int n = a.length;
		int sum = Arrays.stream(a).sum();
		int sumWithSquare = Arrays.stream(a).map(x -> x * x) // Square each element
				.sum();          // Sum the squared elements

		int totalSum = (n * (n + 1)) / 2;
		int totalSumWithSquare = (n * (n + 1) * ((2 * n) + 1)) / 6;

		int val1 = sum - totalSum;
		int val2 = sumWithSquare - totalSumWithSquare;

		val2 = val2 / val1;

		int x = (val1 + val2) / 2;
		int y = x - val1;
		return new int[] { x, y };
	}

	public static int[] findMissingRepeatingNumbers(int[] a) {
		int n = a.length;
		int[] hash = new int[n + 1];

		// Fill the hash array with frequency counts
		for (int j : a) {
			hash[j]++;
		}

		int repeating = -1;
		int missing = -1;

		// Find the repeating and missing numbers
		for (int i = 1; i <= n; i++) {
			if (hash[i] == 2) {
				repeating = i; // The number appears twice
			} else if (hash[i] == 0) {
				missing = i; // The number is missing
			}

			// If both repeating and missing numbers are found, break early
			if (repeating != -1 && missing != -1) {
				break;
			}
		}

		return new int[] { repeating, missing };
	}


	public static void main(String[] args) {
		System.out.println(Arrays.toString(findMissingRepeatingNumbers(new int[] { 1, 2, 3, 1 })));
		System.out.println(Arrays.toString(findMissingRepeatingNumbersBetter(new int[] { 1, 2, 3, 1 })));
		System.out.println(Arrays.toString(findMissingRepeatingNumbersOptimal(new int[] { 1, 2, 3, 1 })));
	}

}
