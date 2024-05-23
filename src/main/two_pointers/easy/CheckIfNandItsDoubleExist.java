package main.two_pointers.easy;

/*
1346. Check If N and Its Double Exist
Easy
Given an array arr of integers, check if there exist two indices i and j such that :

i != j
0 <= i, j < arr.length
arr[i] == 2 * arr[j]


Example 1:

Input: arr = [10,2,5,3]
Output: true
Explanation: For i = 0 and j = 2, arr[i] == 10 == 2 * 5 == 2 * arr[j]
Example 2:

Input: arr = [3,1,7,11]
Output: false
Explanation: There is no i and j that satisfy the conditions.


Constraints:

2 <= arr.length <= 500
-10^3 <= arr[i] <= 10^3
*/

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CheckIfNandItsDoubleExist {

	public static boolean checkIfExist(int[] arr) {

		Set<Integer> set = new HashSet<>();

		int i;

		for (i = 0; i < arr.length; i++) {
			if (set.contains(arr[i] * 2) || (arr[i] % 2 == 0 && set.contains(arr[i] / 2))) {
				return true;
			}
			set.add(arr[i]);
		}

		return false;
	}

	public static void main(String[] args) {
		int[] arr;
		boolean result;

		arr = new int[] { 10, 2, 5, 3 };
		result = checkIfExist(arr);
		assertEquals(true, result);

	}
}
