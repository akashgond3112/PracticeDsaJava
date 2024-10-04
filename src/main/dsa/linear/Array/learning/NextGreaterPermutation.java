package main.dsa.linear.Array.learning;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Problem statement
You are given an array ‘a’ of ‘n’ integers.



You have to return the lexicographically next to greater permutation.



Note:

If such a sequence is impossible, it must be rearranged in the lowest possible order.


Example:

Input: 'a' = [1, 3, 2]

Output: 2 1 3

Explanation: All the permutations of [1, 2, 3] are [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1], ]. Hence the next greater permutation of [1, 3, 2] is [2, 1, 3].


Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
3
3 1 2


Sample Output 1:
3 2 1


Explanation Of Sample Input 1:
Input:
A = [3, 1, 2]
Output:
3 2 1

Explanation: All the permutations of [1, 2, 3] are [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1], ]. Hence the next greater permutation of [3, 1, 2] is [3, 2, 1].


Sample Input 2:
3
3 2 1


Sample Output 2:
1 2 3


Constraints:
1 <= n <= 100
1 <= a[ i ] <= 100
Time Limit: 1 sec
*/
public class NextGreaterPermutation {

	public static List<Integer> nextGreaterPermutation(List<Integer> A) {
		// Write your code here.
		int index = -1;
		int n = A.size();
		for (int i = n - 2; i >= 0; i--) {
			if (A.get(i) < A.get(i + 1)) {
				index = i;
				break;
			}
		}

		if (index == -1) {
			Collections.reverse(A);
			return A;
		}

		for (int i = n - 1; i >= 0; i--) {
			if (A.get(i) > A.get(index)) {
				int temp = A.get(i);
				A.set(i, A.get(index));
				A.set(index, temp);
				break;
			}
		}

		Collections.reverse(A.subList(index + 1, A.size()));

		return A;
	}

	public static void main(String[] args) {
		Integer[] a = new Integer[] { 1, 3, 2 };

		System.out.println(nextGreaterPermutation(Arrays.asList(a)));
	}
}
