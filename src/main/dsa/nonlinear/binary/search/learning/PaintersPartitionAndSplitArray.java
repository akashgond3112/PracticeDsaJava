package main.dsa.nonlinear.binary.search.learning;

import java.text.MessageFormat;

import static java.lang.System.out;
import static main.dsa.nonlinear.binary.search.learning.AllocateBooks.findPagesBinarySearch;
import static main.dsa.nonlinear.binary.search.learning.AllocateBooks.findPagesBruteForce;

/*
Problem statement
Given an array/list of length ‘n,’ where the array/list represents the boards and each element of the given array/list represents the length of each board. Some ‘k’ numbers of painters are available to paint these boards.
Consider that each unit of a board takes 1 unit of time to paint.

You are supposed to return the area of the minimum time to get this job done of painting all the ‘n’ boards under a constraint that any painter will only paint the continuous sections of boards.

Example :
Input: arr = [2, 1, 5, 6, 2, 3], k = 2

Output: 11

Explanation:
First painter can paint boards 1 to 3 in 8 units of time and the second painter can paint boards 4-6 in 11 units of time.
Thus, both painters will paint all the boards in max(8,11) = 11 units of time. It can be shown that all the boards can't be painted in less than 11 units of time.

Sample Input 1:
4 2
10 20 30 40

Sample Output 1:
60

Explanation For Sample Input 1:
In this test case, we can divide the first 3 boards for one painter and the last board for the second painter.
*/

public class PaintersPartitionAndSplitArray {

	public static void main(String[] args) {

		int[] v = { 2, 1, 5, 6, 2, 3 };
		int h = 2;
		out.println(MessageFormat.format("Thus both painters will paint all the boards in max :  {0} .",
				findPagesBruteForce(v, h)));
		out.println(MessageFormat.format("Thus both painters will paint all the boards in max :  {0} .",
				findPagesBinarySearch(v, h)));
		v = new int[] { 10, 20, 30, 40 };
		out.println("Thus both painters will paint all the boards in max :  " + findPagesBruteForce(v, h) + " .");
		out.println("Thus both painters will paint all the boards in max :  " + findPagesBinarySearch(v, h) + " .");
	}
}
