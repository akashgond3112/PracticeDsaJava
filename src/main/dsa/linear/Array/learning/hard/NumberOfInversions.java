package main.dsa.linear.Array.learning.hard;

import java.util.ArrayList;

/*
Problem statement
There is an integer array ‘A’ of size ‘N’.



Number of inversions in an array can be defined as the number of pairs of ‘i’, ‘j’ such that ‘i’ < ‘j’ and ‘A[i]’ > ‘A[j]’.

You must return the number of inversions in the array.



For example,
Input:
A = [5, 3, 2, 1, 4], N = 5
Output:
7
Explanation:
The pairs satisfying the condition for inversion are (1, 2), (1, 3), (1, 4), (1, 5), (2, 3), (2, 4), and (3, 4).
The number of inversions in the array is 7.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
4
4 3 2 1
Sample Output 1:
6
Explanation Of Sample Input 1:
Input:
A = [4, 3, 2, 1], N = 4
Output:
6
Explanation:
The pairs satisfying the condition for inversion are (1, 2), (1, 3), (1, 4), (2, 3), (2, 4), and (3, 4).
The number of inversions in the array is 6.
Sample Input 2:
5
1 20 6 4 5
Sample Output 2:
5
Constraints:
1 <= N <= 10^5
1 <= A[i] <= 10^9
Time Limit: 1 sec
*/
public class NumberOfInversions {

	private static int merge(int[] arr, int low, int mid, int high) {
		ArrayList<Integer> temp = new ArrayList<>(); // temporary array
		int left = low;      // starting index of left half of arr
		int right = mid + 1;   // starting index of right half of arr

		//Modification 1: cnt variable to count the pairs:
		int cnt = 0;

		//storing elements in the temporary array in a sorted manner//

		while (left <= mid && right <= high) {
			if (arr[left] <= arr[right]) {
				temp.add(arr[left]);
				left++;
			} else {
				temp.add(arr[right]);
				cnt += (mid - left + 1); //Modification 2
				right++;
			}
		}

		// if elements on the left half are still left //

		while (left <= mid) {
			temp.add(arr[left]);
			left++;
		}

		//  if elements on the right half are still left //
		while (right <= high) {
			temp.add(arr[right]);
			right++;
		}

		// transfering all elements from temporary to arr //
		for (int i = low; i <= high; i++) {
			arr[i] = temp.get(i - low);
		}
		return cnt; // Modification 3
	}

	public static int mergeSort(int[] arr, int low, int high) {
		int cnt = 0;
		if (low >= high) return cnt;
		int mid = (low + high) / 2 ;
		cnt += mergeSort(arr, low, mid);  // left half
		cnt += mergeSort(arr, mid + 1, high); // right half
		cnt += merge(arr, low, mid, high);  // merging sorted halves
		return cnt;
	}

	public static int numberOfInversions(int[] a, int n) {
		// Count the number of pairs:
		return mergeSort(a, 0, n - 1);
	}


	public static void main(String[] args) {
		int[] a = {5, 4, 3, 2, 1};
		int n = 5;
		int cnt = numberOfInversions(a, n);
		System.out.println("The number of inversions are: " + cnt);
	}

}
