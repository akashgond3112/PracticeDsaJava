package main.dsa.linear.Array.learning;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem statement
There is an array ‘A’ of size ‘N’ with an equal number of positive and negative elements.

Without altering the relative order of positive and negative numbers, you must return an array of alternative positive and negative values.

Note:

Start the array with a positive number.
For example

Input:
A = [1, 2, -4, -5], N = 4
Output:
1 -4  2 -5
Explanation:
Positive elements = 1, 2
Negative elements = -4, -5
To maintain relative ordering, 1 must occur before 2, and -4 must occur before -5.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
2 <= N <= 10^5
-10^9 <= A[i] <= 10^9, A[i] != 0
N%2==0

Time Limit: 1 sec
Sample Input 1:
6
1 2 -3 -1 -2 3
Sample Output 1:
1 -3 2 -1 3 -2
Explanation Of Sample Input 1:
Testcase 1:
Input:
A = [1, 2, -3, -1, -2, 3], N = 6
Output:
1 -3 2 -1 3 -2
Explanation:
Positive elements = 1, 2, 3
Negative elements = -3, -1, -2
To maintain relative ordering, 1 should come before 2, and 2 must come before 3.
Also, -3 should come before -1, and -1 must come before -2.
Sample Input 2:
4
-2 -3 4 5
Sample Output 2:
4 -2 5 -3
*/
public class AlternateNumbers {

	public static int[] alternateNumbersOptimal(int[] a) {

		// Create a new array to store the result
		int[] result = new int[a.length];
		int posIndex = 0, negIndex = 1;

		// Fill the result array alternately with positive and negative numbers
		for (int j : a) {
			if (j >= 0) {
				result[posIndex] = j;
				posIndex += 2;
			} else {
				result[negIndex] = j;
				negIndex += 2;
			}
		}

		return result;
	}


	public static int[] alternateNumbersForOdds(int[] a) {
		// Create two lists to store positive and negative numbers
		ArrayList<Integer> positive = new ArrayList<>();
		ArrayList<Integer> negative = new ArrayList<>();

		// Separate positive and negative numbers
		for (int j : a) {
			if (j >= 0) {
				positive.add(j);
			} else {
				negative.add(j);
			}
		}

		if (positive.size() > negative.size()) {
			// Fill the result array alternately with positive and negative numbers
			for (int i = 0; i < negative.size(); i++) {
				a[2 * i] = positive.get(i);
				a[2 * i + 1] = negative.get(i);
			}

			int index = negative.size() * 2;
			for (int i = negative.size(); i < positive.size(); i++) {
				a[index] = positive.get(i);
				index++;
			}
		} else {
			// Fill the result array alternately with positive and negative numbers
			for (int i = 0; i < positive.size(); i++) {
				a[2 * i] = positive.get(i);
				a[2 * i + 1] = negative.get(i);
			}

			int index = positive.size() * 2;
			for (int i = positive.size(); i < negative.size(); i++) {
				a[index] = negative.get(i);
				index++;
			}
		}


		return a;
	}

	public static int[] alternateNumbers(int[] a) {
		// Create two lists to store positive and negative numbers
		ArrayList<Integer> positive = new ArrayList<>();
		ArrayList<Integer> negative = new ArrayList<>();

		// Separate positive and negative numbers
		for (int j : a) {
			if (j >= 0) {
				positive.add(j);
			} else {
				negative.add(j);
			}
		}

		// Create a new array to store the result
		int[] result = new int[a.length];
		int posIndex = 0, negIndex = 0;

		// Fill the result array alternately with positive and negative numbers
		for (int i = 0; i < a.length; i++) {
			if (i % 2 == 0 && posIndex < positive.size()) {
				result[i] = positive.get(posIndex++);
			} else if (negIndex < negative.size()) {
				result[i] = negative.get(negIndex++);
			}
		}

		return result;
	}

	public static void main(String[] args) {
		int[] arr = { 1, -1, 2, -2, 3, -3, 4, -4 };
		int[] result = alternateNumbers(arr);

		System.out.println(Arrays.toString(result));
		System.out.println(Arrays.toString(alternateNumbersOptimal(arr)));
		System.out.println(Arrays.toString(alternateNumbersForOdds(arr)));
	}

}
