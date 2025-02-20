package main.meta.easy;

/**
 * 415. Add Strings
 * Easy
 * Topics
 * Companies
 * Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.
 *
 * You must solve the problem without using any built-in library for handling large integers (such as BigInteger). You must also not convert the inputs to integers directly.
 *
 * Example 1:
 * Input: num1 = "11", num2 = "123"
 * Output: "134"
 *
 * Example 2:
 * Input: num1 = "456", num2 = "77"
 * Output: "533"
 *
 * Example 3:
 * Input: num1 = "0", num2 = "0"
 * Output: "0"
 *
 * Constraints:
 * 1 <= num1.length, num2.length <= 104
 * num1 and num2 consist of only digits.
 * num1 and num2 don't have any leading zeros except for the zero itself.*/
public class AddStrings {

	static class Solution {
		/**
		 * Adds two non-negative numbers represented as strings.
		 * <p>
		 * Time Complexity: O(max(N, M)) where N, M are lengths of num1 and num2
		 * - Input validation: O(N + M)
		 * - Main loop processing: O(max(N, M))
		 * - StringBuilder operations: O(1) amortized per operation
		 * - Final reverse: O(max(N, M))
		 * <p>
		 * Space Complexity: O(max(N, M))
		 * - StringBuilder for result: O(max(N, M))
		 * - Auxiliary variables: O(1)
		 * - Result can be at most max(N,M) + 1 digits
		 *
		 * @param num1 first number as string of length N
		 * @param num2 second number as string of length M
		 * @return sum of num1 and num2 as string
		 */
		public String addStrings(String num1, String num2) {
			// Input validation
			if (!num1.matches("\\d+") || !num2.matches("\\d+")) {
				throw new IllegalArgumentException("Input strings must contain only digits");
			}

			int len1 = num1.length() - 1;
			int len2 = num2.length() - 1;
			int carry = 0;

			StringBuilder sb = new StringBuilder();

			// Process digits from right to left
			while (len1 >= 0 || len2 >= 0 || carry > 0) {
				// Get current digits, use 0 if we've run out of digits
				int digit1 = len1 >= 0 ? num1.charAt(len1) - '0' : 0;
				int digit2 = len2 >= 0 ? num2.charAt(len2) - '0' : 0;

				// Calculate sum and new carry
				int sum = digit1 + digit2 + carry;
				carry = sum / 10;

				// Append current digit
				sb.append(sum % 10);

				// Move pointers
				len1--;
				len2--;
			}

			// Reverse and return result
			return sb.reverse().toString();
		}
	}
}
