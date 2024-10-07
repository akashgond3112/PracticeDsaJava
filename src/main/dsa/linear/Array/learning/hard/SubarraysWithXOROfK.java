package main.dsa.linear.Array.learning.hard;

import java.util.HashMap;
import java.util.Map;

/*
Problem statement
Given an array ‘A’ consisting of ‘N’ integers and an integer ‘B’, find the number of subarrays of array ‘A’ whose bitwise XOR( ⊕ ) of all elements is equal to ‘B’.

A subarray of an array is obtained by removing some(zero or more) elements from the front and back of the array.

Example:
Input: ‘N’ = 4 ‘B’ = 2
‘A’ = [1, 2, 3, 2]

Output: 3

Explanation: Subarrays have bitwise xor equal to ‘2’ are: [1, 2, 3, 2], [2], [2].
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
4 2
1 2 3 2
Sample Output 1 :
3
Explanation Of Sample Input 1:
Input: ‘N’ = 4 ‘B’ = 2
‘A’ = [1, 2, 3, 2]

Output: 3

Explanation: Subarrays have bitwise xor equal to ‘2’ are: [1, 2, 3, 2], [2], [2].
Sample Input 2:
4 3
1 2 3 3
Sample Output 2:
4
Sample Input 3:
5 6
1 3 3 3 5
Sample Output 3:
2
Constraints:
1 <= N <= 10^3
1 <= A[i], B <= 10^9

Time Limit: 1-sec
*/
public class SubarraysWithXOROfK {

	public static int subarraysWithXorK(int []a, int k) {
		//size of the given array.
		int xr = 0;
		Map<Integer, Integer> mpp = new HashMap<>(); //declaring the map.
		mpp.put(xr, 1); //setting the value of 0.
		int cnt = 0;

		for (int j : a) {
			// prefix XOR till index i:
			xr = xr ^ j;

			//By formula: x = xr^k:
			int x = xr ^ k;

			// add the occurrence of xr^k
			// to the count:
			if (mpp.containsKey(x)) {
				cnt += mpp.get(x);
			}

			// Insert the prefix xor till index i
			// into the map:
			if (mpp.containsKey(xr)) {
				mpp.put(xr, mpp.get(xr) + 1);
			} else {
				mpp.put(xr, 1);
			}
		}
		return cnt;
	}

	public static int subarraysWithSumKBetter(int []a, int b) {
		// Write your code here ^
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			int xor =0;
			for (int j = i ; j < a.length; j++) {
				xor ^= a[j];

				if (xor == b) count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] arr = new int[]{1, 2, 3, 2};
		System.out.println(subarraysWithSumKBetter(arr, 2));
		System.out.println(subarraysWithXorK(arr, 2));
	}
}
