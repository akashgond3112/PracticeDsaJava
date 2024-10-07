package main.dsa.linear.Array.learning.hard;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Problem statement
You are given an array ‘NUMS’ of length ‘N’. You are also given an integer ‘TARGET’.
Return an array of all the unique quadruplets [ ‘NUMS[ a ]’, ‘NUMS[ b ]’, ‘NUMS[ c ]’, ‘NUMS[ d ]’ ] with the following conditions:

i. 0 <= a, b, c, d < ‘N’ and a, b, c, and d are distinct.

ii. NUMS[ a ] + NUMS[ b ] + NUMS[ c ] +NUMS[ d ] = TARGET

Return the array in any order.
Note:
(NUMS[ a ], NUMS[ b ], NUMS[ c ], NUMS[ d ]), (NUMS[ a ], NUMS[ d ], NUMS[ c ], NUMS[ b ]), (NUMS[ a ], NUMS[ c ], NUMS[ b ], NUMS[ d ])... all of them are treated or considered the same quadruplets.

Two quadruplets are different if there is any integer in one quadruplet which is not in the other.

The solution will be verified by the number of valid quadruplets returned. In the output, only the number of valid quadruplets will be printed.

Example:
Input: ‘N’ = 5,  ‘TARGET’ = 5, ‘NUMS’ = [ 1, 2, 1, 0, 1 ]

Output: 1.

There is only one unique quadruplet with sum = 5 and that is [1, 2, 1, 1].
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
6 8
2 2 2 2 1 3
Sample Output 1 :
2
Explanation Of Sample Input 1:
(2+2+2+2) = (2+2+1+3) = 4.
Sample Input 2:
4 4
1 1 1 0
Sample Output 2 :
0
Constraints :
4 <= N <= 100
-10^6 <= NUMS[ i ] <= 10^6
-10^6 <= TARGET <= 10^6


Time Limit: 1 sec
*/
public class FourSum {

	public static List<List<Integer>> fourSumOptimal(int[] arr, int target) {
		List<List<Integer>> result = new ArrayList<>();
		int n = arr.length;

		// Sort the array to handle duplicates and use two-pointer technique
		Arrays.sort(arr);

		// Loop through each element and use two-pointer for the remaining part
		for (int i = 0; i < n - 3; i++) {
			// Avoid duplicate solutions for the first element
			if (i > 0 && arr[i] == arr[i - 1]) continue;

			for (int j = i + 1; j < n - 2; j++) {
				// Avoid duplicate solutions for the second element
				if (j > i + 1 && arr[j] == arr[j - 1]) continue;

				int k = j + 1;
				int l = n - 1;

				// Use two-pointer approach for the remaining elements
				while (k < l) {
					int sum = arr[i] + arr[j] + arr[k] + arr[l];

					if (sum == target) {
						result.add(Arrays.asList(arr[i], arr[j], arr[k], arr[l]));
						k++;
						l--;

						// Skip duplicates for the third element
						while (k < l && arr[k] == arr[k - 1]) k++;

						// Skip duplicates for the fourth element
						while (k < l && arr[l] == arr[l + 1]) l--;

					} else if (sum < target) {
						k++;
					} else {
						l--;
					}
				}
			}
		}

		return result;
	}


	public static List<List<Integer>> fourSumBetter(int[] arr, int target) {
		// Write your code here.
		int n = arr.length;
		Set<List<Integer>> triplets = new HashSet<>();
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				Set<Integer> set = new HashSet<>();
				for (int k = j + 1; k < n; k++) {

					int fourth = target - (arr[i] + arr[j] + arr[k]);
					if (set.contains(fourth)) {
						List<Integer> temp = Arrays.asList(arr[i], arr[j], arr[k], fourth);
						temp.sort(null);
						triplets.add(temp);
					}
					set.add(arr[k]);
				}
			}
		}
		return new ArrayList<>(triplets);
	}

	public static List<List<Integer>> fourSumBruteForce(int[] arr, int target) {
		// Write your code here.
		int n = arr.length;
		Set<List<Integer>> triplets = new HashSet<>();
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				for (int k = j + 1; k < n; k++) {
					for (int l = k + 1; l < n; l++) {
						if (arr[i] + arr[j] + arr[k] + arr[l] == target) {
							List<Integer> triplet = new ArrayList<>();
							triplet.add(arr[i]);
							triplet.add(arr[j]);
							triplet.add(arr[k]);
							triplet.add(arr[l]);
							Collections.sort(triplet);
							triplets.add(triplet);
						}
					}

				}
			}
		}
		return new ArrayList<>(triplets);
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 2, 1, 0, 1 };
		System.out.println(fourSumBruteForce(arr, 5));
		System.out.println(fourSumBetter(arr, 5));
		System.out.println(fourSumOptimal(arr, 5));

	}
}
