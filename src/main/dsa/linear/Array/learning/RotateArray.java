package main.dsa.linear.Array.learning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
Problem statement
Given an array 'arr' with 'n' elements, the task is to rotate the array to the left by 'k' steps, where 'k' is non-negative.

Example:
'arr '= [1,2,3,4,5]
'k' = 1  rotated array = [2,3,4,5,1]
'k' = 2  rotated array = [3,4,5,1,2]
'k' = 3  rotated array = [4,5,1,2,3] and so on.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
8
7 5 2 11 2 43 1 1
2
Sample Output 1:
2 11 2 43 1 1 7 5
Explanation of Sample Input 1:
Rotate 1 steps to the left: 5 2 11 2 43 1 1 7
Rotate 2 steps to the left: 2 11 2 43 1 1 7 5
Sample Input 2:
4
5 6 7 8
3
Sample Output 2:
8 5 6 7
Explanation of Sample Input 2:
Rotate 1 steps to the left: 6 7 8 5
Rotate 2 steps to the left: 7 8 5 6
Rotate 2 steps to the left: 8 5 6 7
Expected Time Complexity:
O(n), where ‘n’ is the size of the array ‘arr’ and ‘k’ is the number of rotations.
Constraints:
1 <= 'n' <= 10^3
1 <= 'arr'[i] <= 10^9
1 <= 'k' < 'n'


Hints:
1. For an index ‘i’, find where it lands after k swaps.
2. When performing rotation once observe how the positions of all elements change.
*/
public class RotateArray {

	// Function to reverse a portion of the array
	private static void reverse(ArrayList<Integer> arr, int start, int end) {
		while (start < end) {
			Collections.swap(arr, start, end);
			start++;
			end--;
		}
	}

	// Rotate array using the reverse method
	public static ArrayList<Integer> rotateArrayReverseMethod(ArrayList<Integer> arr, int k) {
		int n = arr.size();
		k = k % n; // Normalize k in case it's greater than n

		// Step 1: Reverse the entire array
		reverse(arr, 0, n - 1);

		// Step 2: Reverse the first k elements
		reverse(arr, 0, k - 1);

		// Step 3: Reverse the remaining n - k elements
		reverse(arr, k, n - 1);

		return arr;
	}

	public static ArrayList<Integer> rotateArrayBruteForce(ArrayList<Integer> arr, int k) {
		int n = arr.size();
		// Normalize k in case it is larger than the array size
		k = k % n;

		// Create a temporary array to store rotated result
		ArrayList<Integer> tmp = new ArrayList<>(arr);

		// Rotate the array by placing elements in their new positions
		for (int i = 0; i < n; i++) {
			tmp.set((i + k) % n, arr.get(i));
		}

		// Copy the result back to the original array
		for (int i = 0; i < n; i++) {
			arr.set(i, tmp.get(i));
		}

		return arr;
	}

	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>();
		arr.add(1);
		arr.add(2);
		arr.add(3);
		arr.add(4);

		System.out.println("Original array: " + arr);
		System.out.println("Rotated array: " + rotateArrayBruteForce(arr, 3));
		System.out.println("Rotated array: " + rotateArrayReverseMethod(arr, 3));
	}
}
