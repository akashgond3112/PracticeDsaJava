package main.dsa.linear.Array.learning;

import java.util.HashMap;

/*
Problem statement
You are given an array 'a' of 'n' integers.

A majority element in the array ‘a’ is an element that appears more than 'n' / 2 times.

Find the majority element of the array.

It is guaranteed that the array 'a' always has a majority element.

Example:
Input: ‘n’ = 9, ‘a’ = [2, 2, 1, 3, 1, 1, 3, 1, 1]

Output: 1

Explanation: The frequency of ‘1’ is 5, which is greater than 9 / 2.
Hence ‘1’ is the majority element.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
9
2 2 1 3 1 1 3 1 1

Sample Output 1:
1

Explanation Of Sample Input 1:
Input: ‘n’ = 9, ‘a’ = [2, 2, 1, 3, 1, 1, 3, 1, 1]

Output: 1

Explanation: The frequency of ‘1’ is 5, which is greater than 9 / 2.
Hence ‘1’ is the majority element.

Sample Input 2:
1
4

Sample Output 2:
4

Sample Input 3:
5
-53 75 56 56 56

Sample Output 3:
56

Expected time complexity :
The expected time complexity is O(n).

Constraints :
1 <= 'n' <= 10000
0 <= 'arr[i]' <= 10^9

Time limit: 1 second
*/
public class MajorityElement {

	// Moore's Voting Algorithm
	// TC : O(N) + O(N) (If array might have majority elements)
	// SC : O(1) -> No extra space
	public static int majorityElementOptimal(int[] arr) {
		// Write your code here
		int elem = 0;
		int count = 0;
		for (int j : arr) {
			if (count == 0) {
				count = 1;
				elem = j;
			} else if (j == elem) {
				count++;
			} else {
				count--;
			}
		}
		int result = 0;
		for (int j : arr) {
			if (j == elem) {
				result++;
			}
		}
		if (result > arr.length / 2) {
			return elem;
		}
		return -1;
	}


	// TC : O(N Log N) + O(N)
	// SC : O(N) -> When all elements are unique
	public static int majorityElement(int[] arr) {
		// Write your code here
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int j : arr) {
			map.put(j, map.getOrDefault(j, 0) + 1);
		}

		for (Integer key : map.keySet()) {
			if (map.get(key) > arr.length / 2) {
				return key;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 2, 2, 1, 3, 1, 1, 3, 1, 1 };
		int n = arr.length;
		System.out.println(majorityElement(arr));
		System.out.println(majorityElementOptimal(arr));
	}
}
