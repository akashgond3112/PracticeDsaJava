package main.meta.hard;

/**
 * 65. Valid Number
 * Hard
 * Topics
 * Companies
 * Given a string s, return whether s is a valid number.
 * <p>
 * For example, all the following are valid numbers: "2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789",
 * while the following are not valid numbers: "abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53".
 * <p>
 * Formally, a valid number is defined using one of the following definitions:
 * <p>
 * An integer number followed by an optional exponent.
 * A decimal number followed by an optional exponent.
 * An integer number is defined with an optional sign '-' or '+' followed by digits.
 * <p>
 * A decimal number is defined with an optional sign '-' or '+' followed by one of the following definitions:
 * <p>
 * Digits followed by a dot '.'.
 * Digits followed by a dot '.' followed by digits.
 * A dot '.' followed by digits.
 * An exponent is defined with an exponent notation 'e' or 'E' followed by an integer number.
 * <p>
 * The digits are defined as one or more digits.
 * <p>
 * Example 1:
 * Input: s = "0"
 * Output: true
 * <p>
 * Example 2:
 * Input: s = "e"
 * Output: false
 * <p>
 * Example 3:
 * Input: s = "."
 * Output: false
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 20
 * s consists of only English letters (both uppercase and lowercase), digits (0-9), plus '+', minus '-', or dot '.'.*/
public class ValidNumber {

	/**
	 * Solution for validating if a string represents a valid number.
	 * Handles decimal numbers and scientific notation (e.g., "123", "-123.456", "1.2e-3").
	 */
	static class Solution {
		/**
		 * Determines if a string represents a valid number.
		 * Valid number formats:
		 * 1. Integer: "123", "-123"
		 * 2. Decimal: "123.456", "-123.456"
		 * 3. Scientific: "1e10", "1.2E-3"
		 *
		 * @param s input string to validate
		 * @return true if string represents a valid number
		 */
		public boolean isNumber(String s) {
			if (s == null || s.isEmpty()) {
				return false;
			}

			boolean decimalSeen = false;
			boolean numberSeen = false;
			boolean eSeen = false;
			boolean numberAfterE = true;  // Will be set to false when 'e' is found

			char[] chars = s.trim().toCharArray();

			for (int i = 0; i < chars.length; i++) {
				char c = chars[i];

				if (Character.isDigit(c)) {
					numberSeen = true;
					numberAfterE = true;
				} else if (c == '.') {
					// Decimal can't appear after 'e' or appear twice
					if (eSeen || decimalSeen) {
						return false;
					}
					decimalSeen = true;
				} else if (c == 'e' || c == 'E') {
					// 'e' can't appear twice and must have numbers before it
					if (eSeen || !numberSeen) {
						return false;
					}
					eSeen = true;
					numberAfterE = false;  // Reset for checking numbers after 'e'
				} else if (c == '-' || c == '+') {
					// Signs can only appear at start or after 'e'
					if (i != 0 && chars[i - 1] != 'e' && chars[i - 1] != 'E') {
						return false;
					}
				} else {
					return false;  // Invalid character
				}
			}

			return numberSeen && numberAfterE;
		}
	}
}
