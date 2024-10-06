package main.dsa.linear.Array.learning.medium;

import java.util.HashMap;

/*
Problem statement
You are given an integer array 'arr' of size 'N' and an integer 'K'.

Your task is to find the total number of subarrays of the given array whose sum of elements is equal to k.

A subarray is defined as a contiguous block of elements in the array.

Example:
Input: ‘N’ = 4, ‘arr’ = [3, 1, 2, 4], 'K' = 6

Output: 2

Explanation: The subarrays that sum up to '6' are: [3, 1, 2], and [2, 4].
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
2
4 6
3 1 2 4

3 3
1 2 3
Sample output 1:
2
2
Explanation:
Test Case 1:

Input: ‘N’ = 4, ‘arr’ = [3, 1, 2, 4], 'K' = 6

Output: 2

Explanation: The subarrays that sum up to '6' are: [3, 1, 2], and [2, 4].

Test Case 2:

Input: ‘N’ = 3, ‘arr’ = [1, 2, 3], 'K' = 3

Output: 2

Explanation: The subarrays that sum up to '7' are: [1, 2], and [3].
Sample Input 2:
2
3 7
1 2 3

4 9
6 3 5 2
Sample output 2:
0
1

HINT : Prefix sum
*/
public class CountAllSubarraysWithGivenSum {

	/*
	 * Time Complexity : O(N) X log N
	 * Space Complexity : O(N)
	 * */
	public static int findAllSubarraysWithGivenSumOptimal(int[] arr, int s) {
		// Write your code here.
		int count = 0;
		int prefixSum = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);

		for (int j : arr) {
			prefixSum += j;
			int remove = prefixSum - s;
			count += map.getOrDefault(remove, 0);
			map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
		}

		return count;
	}

	public static int findAllSubarraysWithGivenSum(int[] arr, int s) {
		// Write your code here.
		int count = 0;

		for (int i = 0; i < arr.length; i++) {
			int sum = 0;

			for (int j = i; j < arr.length; j++) {
				sum += arr[j];

				// Check if the sum equals the target value
				if (sum == s) {
					count++;
				}
			}
		}

		return count;
	}


	public static void main(String[] args) {
		int[] arr = { 3, 1, 2, 4 };
		System.out.println(findAllSubarraysWithGivenSum(arr, 6));
		System.out.println(findAllSubarraysWithGivenSumOptimal(arr, 6));
	}
}
