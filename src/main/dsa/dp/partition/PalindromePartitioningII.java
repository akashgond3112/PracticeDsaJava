package main.dsa.dp.partition;

import java.util.Arrays;

import static main.dsa.two_pointers.easy.ValidPalindrome.isPalindrome;

/*
Problem statement
You are given a string 'str' of length 'n'.



Find the minimum number of partitions in the string so that no partition is empty and every partitioned substring is a palindrome.



Example :
Input: 'str' = "aaccb"

Output: 2

Explanation: We can make a valid partition like aa | cc | b.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
aaccb


Sample Output 1 :
2


Explanation of sample input 1 :
We can make a valid partition like aa | cc | b.


Sample Input 2 :
ababa


Sample Output 2 :
0


Explanation of sample input 2 :
The string is already a palindrome, so we need not make any partition.


Sample Input 3:
aababa


Sample Output 3:
1


Expected time complexity :
You can submit a solution of time complexity O(n ^ 3) but also try to write the solution having complexity O(n ^ 2).


Constraints :
1 <= 'n' <= 100

Time limit: 1 second
*/
public class PalindromePartitioningII {

	public static int palindromePartitioningTabular(String str) {
		int n = str.length();
		int[] dp = new int[n + 1];

		// Base case: no partitions are needed beyond the end of the string
		dp[n] = 0;

		// Fill the dp array in reverse order
		for (int i = n - 1; i >= 0; i--) {
			StringBuilder sb = new StringBuilder();

			int minCost = Integer.MAX_VALUE;

			// Try all partitions starting from index i
			for (int j = i; j < n; j++) {
				sb.append(str.charAt(j));

				// Check if substring str[i...j] is a palindrome
				if (isPalindrome(sb.toString())) {
					// 1 + dp[j + 1] because we add 1 partition after str[i...j]
					int cost = 1 + dp[j + 1];
					minCost = Math.min(minCost, cost);
				}
			} dp[i] = minCost;
		}

		// The answer is dp[0] because we are counting cuts, not partitions
		return dp[0];
	}

	public static int palindromePartitioningMemo(String str, int i, int[] dp) {
		// If we reach the end of the string, no more partitions are needed
		if (i == str.length()) {
			return 0;
		}

		if (dp[i] != -1) {
			return dp[i];
		}

		StringBuilder sb = new StringBuilder();
		int minCost = Integer.MAX_VALUE;

		// Try all partitions starting from index i
		for (int j = i; j < str.length(); j++) {
			sb.append(str.charAt(j));

			// If the current substring is a palindrome
			if (isPalindrome(sb.toString())) {
				// Calculate the cost of this partition and recurse for the remaining part of the string
				int cost = 1 + palindromePartitioningMemo(str, j + 1, dp);
				minCost = Math.min(minCost, cost);
			}
		}

		return dp[i] = minCost;
	}

	public static int palindromePartitioning(String str, int i) {
		// If we reach the end of the string, no more partitions are needed
		if (i == str.length()) {
			return 0;
		}

		StringBuilder sb = new StringBuilder();
		int minCost = Integer.MAX_VALUE;

		// Try all partitions starting from index i
		for (int j = i; j < str.length(); j++) {
			sb.append(str.charAt(j));

			// If the current substring is a palindrome
			if (isPalindrome(sb.toString())) {
				// Calculate the cost of this partition and recurse for the remaining part of the string
				int cost = 1 + palindromePartitioning(str, j + 1);
				minCost = Math.min(minCost, cost);
			}
		}

		return minCost;
	}

	public static void main(String[] args) {
		String input = "aaccb";
		System.out.println("Minimum number of partitions: " + (palindromePartitioning(input, 0) - 1));

		int[] dp = new int[input.length() + 1];
		Arrays.fill(dp, -1);
		System.out.println("Minimum number of partitions: " + (palindromePartitioningMemo(input, 0, dp) - 1));
		System.out.println("Minimum number of partitions: " + (palindromePartitioningTabular(input) - 1));

	}
}
