package main.dsa.linear.Array.learning;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/*
Problem statement
There is an integer array ‘A’ of size ‘N’.

A sequence is successive when the adjacent elements of the sequence have a difference of 1.

You must return the length of the longest successive sequence.

Note:

You can reorder the array to form a sequence.
For example,

Input:
A = [5, 8, 3, 2, 1, 4], N = 6
Output:
5
Explanation:
The resultant sequence can be 1, 2, 3, 4, 5.
The length of the sequence is 5.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= N <= 10^5
1 <= A[i] <= 10^9
Time Limit: 1 sec
*/
public class LongestSuccessiveElements {

	public static int longestSuccessiveElementsOptimal(int[] a) {
		// Write your code here.
		HashSet<Integer> set = new HashSet<>();
		int longest = 1;

		for (int j : a) {
			set.add(j);
		}

		// Find the longest sequence
		for (int it : set) {
			// if 'it' is a starting number
			if (!set.contains(it - 1)) {
				// find consecutive numbers
				int cnt = 1;
				int x = it;
				while (set.contains(x + 1)) {
					x = x + 1;
					cnt = cnt + 1;
				}
				longest = Math.max(longest, cnt);
			}
		}
		return longest;
	}

	public static int longestSuccessiveElements(int[] a) {
		// Write your code here.
		Arrays.sort(a);
		int longest = 1;
		int currenSmaller = Integer.MIN_VALUE;
		int counter = 0;
		for (int j : a) {
			if (j - 1 == currenSmaller) {
				counter++;
				currenSmaller = j;
			} else if (j != currenSmaller) {
				counter = 1;
				currenSmaller = j;
			}
			longest = Math.max(longest, counter);
		}
		return longest;
	}

	public static void main(String[] args) {

		int[] a = new int[] { 5, 8, 3, 2, 1, 4 };
		System.out.println(longestSuccessiveElements(a));
		System.out.println(longestSuccessiveElementsOptimal(a));
	}
}
