package main.dsa.linear.Array.learning;

import java.util.ArrayList;

/*
Problem statement
You have been given an array/list 'arr' consisting of 'n' elements.



Each element in the array is either 0, 1 or 2.



Sort this array/list in increasing order.



Do not make a new array/list. Make changes in the given array/list.



Example :
Input: 'arr' = [2, 2, 2, 2, 0, 0, 1, 0]

Output: Final 'arr' = [0, 0, 0, 1, 2, 2, 2, 2]

Explanation: The array is sorted in increasing order.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
8
2 2 2 2 0 0 1 0


Sample Output 1:
0 0 0 1 2 2 2 2


Explanation of sample input 1 :
The initial array 'arr' is [2, 2, 2, 2, 0, 0, 1, 0].

After sorting the array in increasing order, 'arr' is equal to:
[0, 0, 0, 1, 2, 2, 2, 2]


Sample Input 2:
5
1 1 1 1 1


Sample Output 2:
1 1 1 1 1


Expected time complexity :
The expected time complexity is O(n).


Constraints:
1 <= 'n' <= 10 ^ 4
0 <= 'arr[i]' <= 2

Time limit: 1 second
*/
public class SortAnArrayOf0s1sAnd2s {

	public static void sortArray(ArrayList<Integer> arr, int n) {
		// Write your code here.
		int low = 0;
		int mid = 0;
		int high = arr.size() - 1;

		while (mid <= high) {
			if (arr.get(mid) == 0) {
				int temp = arr.get(low);
				arr.set(low, arr.get(mid));
				arr.set(mid, temp);
				low++;
				mid++;
			} else if (arr.get(mid) == 1) {
				mid++;
			} else {
				int temp = arr.get(high);
				arr.set(high, arr.get(mid));
				arr.set(mid, temp);
				high--;
			}
		}
	}

	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>();
		arr.add(1);
		arr.add(2);
		arr.add(0);
		arr.add(0);
		arr.add(1);
		arr.add(2);
		arr.add(0);
		arr.add(0);

		SortAnArrayOf0s1sAnd2s.sortArray(arr, arr.size());
		System.out.println(arr);
	}
}
