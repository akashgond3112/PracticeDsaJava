package main.dsa.linear.Array.learning;

import java.util.HashMap;

/*
Example 1:
Input Format:
 N = 5, array[] = {1,2,4,5}
Result:
 3
Explanation:
In the given array, number 3 is missing. So, 3 is the answer.

Example 2:
Input Format:
 N = 3, array[] = {1,3}
Result:
 2
Explanation:
In the given array, number 2 is missing. So, 2 is the answer.
*/
public class FindTheMissingNumberInAnArray {

	public static int missingNumberOptimalII(int[] a, int n) {

		int xor1 = 0, xor2 = 0;

		for (int i = 0; i < n - 1; i++) {
			xor2 = xor2 ^ a[i]; // XOR of array elements
			xor1 = xor1 ^ (i + 1); //XOR up to [1...N-1]
		}
		xor1 = xor1 ^ n; //XOR up to [1...N]

		return (xor1 ^ xor2); // the missing number
	}


	public static int missingNumberOptimal(int[] a, int n) {

		int totalSum = n * (n + 1) / 2;

		int sum = 0;
		for (int i : a) {
			sum += i;
		}

		return totalSum - sum;
	}

	public static int missingNumberBetter(int[] a, int n) {

		int[] hash = new int[n + 1];

		// storing the frequencies:
		for (int i = 0; i < n - 1; i++)
			hash[a[i]]++;

		//checking the frequencies for numbers 1 to N:
		for (int i = 1; i <= n; i++) {
			if (hash[i] == 0) {
				return i;
			}
		}

		// The following line will never execute.
		// It is just to avoid warnings.
		return -1;
	}

	public static int missingNumberBrute(int[] a, int n) {

		for (int i = 1; i <= n; i++) {
			boolean found = false;
			for (int j = 0; j < n - 1; j++) {
				if (a[j] == i) {
					found = true;
					break;
				}
			}
			if (!found) {
				return i;
			}
		}

		return -1;
	}

	public static void main(String[] args) {

		int[] a = { 1, 2, 4, 5 };
		System.out.println(missingNumberBrute(a, 5));
		System.out.println(missingNumberBetter(a, 5));
		System.out.println(missingNumberOptimal(a, 5));
		System.out.println(missingNumberOptimalII(a, 5));

	}
}
