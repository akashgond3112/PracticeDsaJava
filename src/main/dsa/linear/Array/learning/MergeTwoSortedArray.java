package main.dsa.linear.Array.learning;

import java.util.ArrayList;

/*
Problem statement
Given two sorted arrays, ‘a’ and ‘b’, of size ‘n’ and ‘m’, respectively, return the union of the arrays.



The union of two sorted arrays can be defined as an array consisting of the common and the distinct elements of the two arrays. The final array should be sorted in ascending order.



Note: 'a' and 'b' may contain duplicate elements, but the union array must contain unique elements.



Example:
Input: ‘n’ = 5 ‘m’ = 3
‘a’ = [1, 2, 3, 4, 6]
‘b’ = [2, 3, 5]

Output: [1, 2, 3, 4, 5, 6]

Explanation: Common elements in ‘a’ and ‘b’ are: [2, 3]
Distinct elements in ‘a’ are: [1, 4, 6]
Distinct elements in ‘b’ are: [5]
Union of ‘a’ and ‘b’ is: [1, 2, 3, 4, 5, 6]
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
5 3
1 2 3 4 6
2 3 5
Sample Output 1 :
1 2 3 4 5 6
Explanation Of Sample Input 1 :
Input: ‘n’ = 5 ‘m’ = 3
‘a’ = [1, 2, 3, 4, 6]
‘b’ = [2, 3, 5]

Output: [1, 2, 3, 4, 5, 6]

Explanation: Common elements in ‘a’ and ‘b’ are: [2, 3]
Distinct elements in ‘a’ are: [1, 4, 6]
Distinct elements in ‘b’ are: [5]
Union of ‘a’ and ‘b’ is: [1, 2, 3, 4, 5, 6]
Sample Input 2:
4 3
1 2 3 3
2 2 4
Sample Output 2:
1 2 3 4
Explanation Of Sample Input 2 :
Input: ‘n’ = 5 ‘m’ = 3
‘a’ = [1, 2, 3, 3]
‘b’ = [2, 2, 4]

Output: [1, 2, 3, 4]

Explanation: Common elements in ‘a’ and ‘b’ are: [2]
Distinct elements in ‘a’ are: [1, 3]
Distinct elements in ‘b’ are: [4]
Union of ‘a’ and ‘b’ is: [1, 2, 3, 4]
Expected Time Complexity:
O(( N + M )), where 'N' and ‘M’ are the sizes of Array ‘A’ and ‘B’.
Constraints :
1 <= 'n', 'm' <= 10^5
-10^9 <= 'a'[i], 'b'[i] <= 10^9

Time Limit: 1 sec
* */
public class MergeTwoSortedArray {

	public static ArrayList<Integer> mergeTwoSortedArray(ArrayList<Integer> arr1, int n, ArrayList<Integer> arr2,
			int m) {
		// Write Your Code Here.
		int i = 0;
		int j = 0;

		ArrayList<Integer> intersection = new ArrayList<>();

		while (i < n && j < m) {
			if (arr1.get(i) <= arr2.get(j)) {
				if (intersection.isEmpty() || !intersection.contains(arr1.get(i))) {
					intersection.add(arr1.get(i));
				}
				i++;
			} else {
				if (intersection.isEmpty() || !intersection.contains(arr2.get(j))) {
					intersection.add(arr2.get(j));
				}
				j++;
			}
		}

		while (i < n) {
			if (intersection.isEmpty() || !intersection.contains(arr1.get(i))) {
				intersection.add(arr1.get(i));
			}
			i++;
		}
		while (j < m) {
			if (intersection.isEmpty() || !intersection.contains(arr2.get(j))) {
				intersection.add(arr2.get(j));
			}
			j++;
		}
		return intersection;
	}

	public static void main(String[] args) {
		ArrayList<Integer> arr1 = new ArrayList<>();
		arr1.add(1);
		arr1.add(2);
		arr1.add(3);
		arr1.add(9);

		ArrayList<Integer> arr2 = new ArrayList<>();
		arr2.add(1);
		arr2.add(2);
		arr2.add(7);
		arr2.add(8);
		ArrayList<Integer> intersection = mergeTwoSortedArray(arr1, arr1.size(), arr2, arr2.size());
		System.out.println(intersection);
	}
}

