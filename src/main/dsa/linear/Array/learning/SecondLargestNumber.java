package main.dsa.linear.Array.learning;

import java.util.Arrays;

/*
Problem statement
You have been given an array ‘a’ of ‘n’ unique non-negative integers.

Find the second largest and second smallest element from the array.

Return the two elements (second largest and second smallest) as another array of size 2.

Example :
Input: ‘n’ = 5, ‘a’ = [1, 2, 3, 4, 5]
Output: [4, 2]

The second largest element after 5 is 4, and the second smallest element after 1 is 2.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
4
3 4 5 2
Sample Output 1 :
4 3
Explanation For Sample Input 1 :
The second largest element after 5 is 4 only, and the second smallest element after 2 is 3.
Sample Input 2 :
5
4 5 3 6 7
Sample Output 2 :
6 4
Expected Time Complexity:
O(n), Where ‘n’ is the size of an input array ‘a’.
Constraints:
2 ≤ 'n' ≤ 10^5
0 ≤ 'a'[i] ≤ 10^9

Time limit: 1 sec


Hints:
1. Sort the array.
2. More efficiently, can you use the largest and smallest elements to find the required elements?
*/
public class SecondLargestNumber {

	public static int[] getSecondOrderElementsOptimal(int n, int[] a) {

		int largest = a[0];
		int secondLargest = -1;

		for (int i = 1; i < n; i++) {
			if (a[i] > largest) {
				secondLargest = largest;
				largest = a[i];
			} else if (a[i] < largest && a[i] > secondLargest) {
				secondLargest = a[i];
			}
		}
		return new int[] { secondLargest, largest };

	}

	public static int[] getSecondOrderElementsBetter(int n, int[] a) {

		int largest = LargestElementInTheArray.largestElementOptimal(a, 0);
		int secondLargest = -1;
		for (int i = 0; i < n; i++) {
			if (a[i] != largest && a[i] > secondLargest) {
				secondLargest = a[i];
			}
		}
		return new int[] { secondLargest, largest };

	}

	public static int[] getSecondOrderElements(int n, int[] a) {
		// Write your code here.
		Arrays.sort(a);
		int larger = a[n - 1];
		int secondLarger = -1;

		for (int i = n - 2; i >= 0; i--) {
			if (a[i] != larger) {
				secondLarger = a[i];
				break;
			}
		}
		return new int[] { secondLarger, larger };
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 2, 3, 4, 5, 8, 9, 9 };
		System.out.println(Arrays.toString(getSecondOrderElements(arr.length, arr)));
		System.out.println(Arrays.toString(getSecondOrderElementsBetter(arr.length, arr)));
		System.out.println(Arrays.toString(getSecondOrderElementsOptimal(arr.length, arr)));
	}
}
