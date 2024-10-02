package main.dsa.linear.Array.learning;

import java.util.HashMap;
import java.util.Iterator;

/*
Example 1:
Input Format:
 arr[] = {2,2,1}
Result:
 1
Explanation:
 In this array, only the element 1 appear once and so it is the answer.

Example 2:
Input Format:
 arr[] = {4,1,2,1,2}
Result:
 4
Explanation:
 In this array, only element 4 appear once and the other elements appear twice. So, 4 is the answer.
*/
public class FindTheNumberThatAppearsOnceAndTheOtherNumbersTwice {


	public static int getSingleElementOptimal(int[] arr) {
		int xor1 = 0;
		for (int j : arr) {
			xor1 = xor1 ^ j; // XOR of array elements
		}
		return (xor1);
	}

	public static int getSingleElement(int[] arr) {

		HashMap<Integer, Integer> map = new HashMap<>();
		for (int j : arr) {
			map.put(j, map.getOrDefault(j, 0) + 1);
		}

		for (int key : map.keySet()) {
			if (map.get(key) == 1) {
				return key;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		System.out.println(getSingleElement(new int[] { 1, 1, 2, 3, 3, 4, 4, 5, 5 }));
		System.out.println(getSingleElementOptimal(new int[] { 1, 1, 2, 3, 3, 4, 4, 5, 5 }));
	}
}
