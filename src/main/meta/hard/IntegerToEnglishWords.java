package main.meta.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 273. Integer to English Words
 * Hard
 * Convert a non-negative integer num to its English words representation.
 *
 * Example 1:
 * Input: num = 123
 * Output: "One Hundred Twenty Three"
 * Example 2:
 * Input: num = 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * Example 3:
 * Input: num = 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 * Constraints:
 *
 * 0 <= num <= 231 - 1
 * Topics
 * Math
 * String
 * Recursion
 * </pre>
 */
public class IntegerToEnglishWords {

	/**
	 * Solution for converting integers to their English word representation.
	 * Time Complexity: O(log₁₀(n)) - We process each digit of the number once
	 * Space Complexity: O(log₁₀(n)) - The output string size is proportional to the number of digits
	 */
	static class Solution {
		/**
		 * Converts a given integer to its English word representation.
		 * For example, 123 -> "One Hundred Twenty Three"
		 *
		 * @param num The integer to convert (can be 0 or positive)
		 * @return The English word representation of the number
		 *
		 * Time Complexity: O(log₁₀(n)) - Processing each digit takes constant time
		 * Space Complexity: O(log₁₀(n)) - Output string length is proportional to digits in input
		 */
		public String numberToWords(int num) {
			// Special case handling for zero
			if (num == 0)
				return "Zero";

			// Define word representations for different number parts
			String[] units = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
			String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
			String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
			String[] thousands = {"", "Thousand", "Million", "Billion"};

			StringBuilder result = new StringBuilder();
			int i = 0;  // Tracks which 'thousands' group we're processing (0=ones, 1=thousands, 2=millions, etc.)

			// Process the number in groups of three digits
			while (num > 0) {
				// Only add words for non-zero groups
				if (num % 1000 != 0) {
					StringBuilder temp = new StringBuilder();
					helper(num % 1000, temp, units, teens, tens);
					temp.append(thousands[i]).append(" ");
					result.insert(0, temp);  // Insert at beginning since we're processing right-to-left
				}
				num /= 1000;  // Move to next group of three digits
				i++;
			}

			return result.toString().trim();  // Remove trailing space
		}

		/**
		 * Helper method to convert a number less than 1000 to words.
		 * Time Complexity: O(1) - Processing a fixed number of digits (1-3)
		 * Space Complexity: O(1) - Fixed size output for numbers <1000
		 *
		 * @param num The number to convert (must be less than 1000)
		 * @param sb The StringBuilder to append the result to
		 * @param units Array of unit words (one, two, etc.)
		 * @param teens Array of teen words (eleven, twelve, etc.)
		 * @param tens Array of tens words (twenty, thirty, etc.)
		 */
		private void helper(int num, StringBuilder sb, String[] units, String[] teens, String[] tens) {
			// Handle hundreds place
			if (num >= 100) {
				sb.append(units[num / 100]).append(" Hundred ");
				num %= 100;  // Get remaining digits
			}

			// Handle tens place with special case for teens (11-19)
			if (num >= 10 && num <= 19) {
				sb.append(teens[num - 10]).append(" ");
			} else {
				if (num >= 20) {
					sb.append(tens[num / 10]).append(" ");
					num %= 10;  // Get remaining digit
				}

				// Handle units place
				if (num > 0) {
					sb.append(units[num]).append(" ");
				}
			}
		}
	}
}
