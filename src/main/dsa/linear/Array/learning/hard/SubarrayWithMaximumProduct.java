package main.dsa.linear.Array.learning.hard;

/*
Problem statement
Given an array ‘Arr’ that has ‘N’ elements. You have to find the subarray of ‘Arr’ that has the largest product. You must return the product of the subarray with the maximum product.



For example:
When ‘N’ = 5, and ‘Arr’ = {-2, 3, -4, 0}
The subarrays of ‘Arr’ are:
{-2}, {-2, 3}, {-2, 3, -4}, {-2, 3, -4, 0}, {3}, {3, -4}, {3, -4, 0}, {-4}, {-4, 0}, {0}.
Among these, {-2, 3, -4} is the subarray having the maximum product equal to 24.
Hence, the answer is 24.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
4
1 -2 3 -4
Sample Output 1:
24
Explanation Of Sample Input 1:
Given, ‘Arr’ = {1, -2, 3, -4}. The subarrays of ‘Arr’ are:
{{1}, {1, -2}, {1, -2, 3}, {1, -2, 3, -4}, {-2}, {-2, 3}, {-2, 3, -4}, {3}, {3, -4}, {-4}}.
Among these subarrays, {1, -2, 3, -4} and {-2, 3, -4} have the maximum product, equal to 24.
Hence, the answer is 24.
Sample Input 2:
5
-1 3 0 -4 3
Sample Output 2:
3
Constraints:
1 <= N <= 10^5
-100 <= Arr[i] <= 100

-10^9 <= The product of elements of any subarray <= 10^9.
The sum of ‘N’ over all test cases <= 10^5.
Time Limit: 1-sec
*/
public class SubarrayWithMaximumProduct {

	public static int maxProductSubArrayOptimal(int[] arr) {
		int n = arr.length; //size of array.

		int pre = 1, suff = 1;
		int ans = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			if (pre == 0)
				pre = 1;
			if (suff == 0)
				suff = 1;
			pre *= arr[i];
			suff *= arr[n - i - 1];
			ans = Math.max(ans, Math.max(pre, suff));
		}
		return ans;
	}


	static int subarrayWithMaxProduct(int[] arr) {
		int result = arr[0];
		for (int i = 0; i < arr.length - 1; i++) {
			int p = arr[i];
			for (int j = i + 1; j < arr.length; j++) {
				result = Math.max(result, p);
				p *= arr[j];
			}
			result = Math.max(result, p);
		}
		return result;
	}

	public static void main(String[] args) {
		int[] arr = { -2, 3, -4, 0 };
		System.out.println(subarrayWithMaxProduct(arr));
		System.out.println(maxProductSubArrayOptimal(arr));
	}
}
