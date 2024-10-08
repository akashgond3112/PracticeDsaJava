package main.dsa.linear.Array.learning.hard;

import java.util.Arrays;

/*
Problem statement
Given two non-decreasing sorted arrays, ‘A’ and ‘B’, having ‘N’ and ‘M’ elements, respectively.

You must merge these arrays, ‘A’ and ‘B’, into a sorted array without using extra space. Of all the 'N + M' sorted elements, array 'A' should contain the first 'N' elements, and array 'B' should have the last 'M' elements.

Note:
You must perform the merge operation in place and must not allocate any extra space to merge the two arrays.
For example:
When ‘N’ = 4, ‘A’ = {1, 4, 5, 7} and ‘M’ = 3, ‘B’ = {2, 3, 6}.
We can merge these two arrays into {1, 2, 3, 4, 5, 6, 7} (The elements of ‘A’ are {1, 2, 3, 4} ).
Hence, the answer is {1, 2, 3, 4, 5, 6, 7}.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
3 4
1 8 8
2 3 4 5
Sample Output 1:
1 2 3 4 5 8 8
Explanation Of Sample Input 1:
We have ‘A’ = {1, 8, 8} and ‘B’ = {2, 3, 4, 5}.
Merging the two arrays results in {1, 2, 3, 4, 5, 8, 8}.
Hence the answer is {1, 2, 3, 4, 5, 8, 8}, where ‘A’ contains {1, 2, 3} and ‘B’ contains {4, 5, 8, 8}.
Sample Input 2:
4 5
1 1 1 1
2 2 3 3 5
Sample Output 2:
1 1 1 1 2 2 3 3 5
Constraints:
1 <= N <= 10^5
1 <= M <= 10^5
0 <= A[i] <= 10^9
0 <= B[i] <= 10^9

The sum of ‘N + M’ over all test cases does not exceed 2 * 10^5.
Time Limit: 1-sec
*/
public class MergeTwoSortedArraysWithoutExtraSpace {

	private static void swap(long[] a, long[] b, int i, int j) {
		if (a[i] > a[j]) {
			long temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}

	public static void mergeTwoSortedArraysWithoutExtraSpaceOptimal(long[] a, long[] b) {
		// Write your code here.
		int n = a.length;
		int m = b.length;
		int length = n + m;
		int gap = (length / 2) + (length % 2);

		while (gap > 0) {

			int left = 0, right = left + gap;

			while (right < length) {

				if (left < n && right >= n) {
					swap(a, b, left, right - n);
				} else if (left >= n) {
					swap(b, b, left - n, right - n);
				} else {
					swap(a, a, left, right);
				}
				left++;
				right++;
			}
			if (gap == 1)
				break;
			gap = (gap / 2) + (gap % 2);
		}
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(b));
	}

	public static void mergeTwoSortedArraysWithoutExtraSpaceBetter(long[] a, long[] b) {
		// Write your code here.
		int n = a.length;
		int m = b.length;
		int left = n - 1, right = 0;

		while (left >= 0 && right < m) {

			if (a[left] > b[right]) {
				long tmp = a[left];
				a[left] = b[right];
				b[right] = tmp;
				left--;
				right++;
			} else {
				break;
			}
		}
		Arrays.sort(a);
		Arrays.sort(b);
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(b));
	}

	public static void mergeTwoSortedArraysWithoutExtraSpaceBrute(long[] a, long[] b) {
		// Write your code here.
		int n = a.length;
		int m = b.length;
		int left = 0, right = 0;
		int index = 0;
		long[] res = new long[n + m];
		while (left < n && right < m) {
			if (a[left] <= b[right]) {
				res[index] = a[left];
				left++;
			} else {
				res[index] = b[right];
				right++;
			}
			index++;
		}

		while (left < n) {
			res[index] = a[left];
			left++;
		}
		while (right < m) {
			res[index] = b[right];
			right++;
		}

		for (int i = 0; i < n + m; i++) {
			if (i < n) {
				a[i] = res[i];
			} else {
				b[i - n] = res[i];
			}
		}

		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(b));
	}

	public static void main(String[] args) {
		long[] a = new long[] { 1, 4, 5, 7 };
		long[] b = new long[] { 2, 3, 6 };

		mergeTwoSortedArraysWithoutExtraSpaceBrute(a, b);
		mergeTwoSortedArraysWithoutExtraSpaceBetter(a, b);
		mergeTwoSortedArraysWithoutExtraSpaceOptimal(a, b);
	}
}
