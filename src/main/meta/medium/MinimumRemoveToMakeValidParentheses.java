package main.meta.medium;

import java.util.Stack;

/**
 * 1249. Minimum Remove to Make Valid Parentheses Medium Topics Companies Hint Given a string s of
 * '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that
 * the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or It can be written as AB (A
 * concatenated with B), where A and B are valid strings, or It can be written as (A), where A is a
 * valid string.
 *
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)" Output: "lee(t(c)o)de" Explanation: "lee(t(co)de)" , "lee(t(c)ode)"
 * would also be accepted. Example 2:
 *
 * Input: s = "a)b(c)d" Output: "ab(c)d" Example 3:
 *
 * Input: s = "))((" Output: "" Explanation: An empty string is also valid.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105 s[i] is either '(' , ')', or lowercase English letter.
 */
public class MinimumRemoveToMakeValidParentheses {

	/**
	 * Time complexity: O(n) Space complexity: O(n) Where n is the number of characters in the
	 * string.
	 */
	static class Solution {
		public String minRemoveToMakeValid(String s) {

			StringBuilder sb = new StringBuilder();

			int open = 0;
			for (char c : s.toCharArray()) {
				if (c == '(') {
					open++;
				} else if (c == ')') {
					if (open == 0)
						continue;
					open--;
				}
				sb.append(c);
			}
			StringBuilder result = new StringBuilder();

			for (int i = sb.length() - 1; i >= 0; i--) {
				if (sb.charAt(i) == '(' && open-- > 0)
					continue;
				result.append(sb.charAt(i));
			}

			return result.reverse().toString();

		}
	}

	/**
	 * Time complexity: O(n) Space complexity: O(1) Where n is the number of characters in the
	 * string.
	 */
	static class SolutionOptimal {
		public String minRemoveToMakeValid(String s) {
			char[] chars = s.toCharArray();
			int open = 0;

			// First pass: remove invalid ')'
			for (int i = 0; i < chars.length; i++) {
				if (chars[i] == '(') {
					open++;
				} else if (chars[i] == ')') {
					if (open == 0) {
						chars[i] = '\0'; // Mark invalid ')'
					} else {
						open--;
					}
				}
			}

			// Second pass: remove invalid '('
			for (int i = chars.length - 1; i >= 0; i--) {
				if (chars[i] == '(' && open-- > 0) {
					chars[i] = '\0'; // Mark invalid '('
				}
			}

			// Build the result string
			StringBuilder result = new StringBuilder();
			for (char c : chars) {
				if (c != '\0') {
					result.append(c);
				}
			}

			return result.toString();
		}
	}

	/**
	 * Solution to remove minimum number of parentheses to make a valid string.
	 *
	 * Problem: Given a string of parentheses, remove the minimum number of
	 * parentheses to make the string valid.
	 *
	 * Approach: Stack-based solution to track and remove invalid parentheses
	 *
	 * @author Akash
	 * @version 1.0
	 */
	static class SolutionStack {
		/**
		 * Removes minimum number of parentheses to make the string valid.
		 *
		 * Algorithm Steps:
		 * 1. Iterate through the string
		 * 2. Use a stack to track indices of open parentheses
		 * 3. Mark invalid parentheses for removal
		 * 4. Build a new string without marked characters
		 *
		 * @param s Input string containing parentheses
		 * @return String with minimum invalid parentheses removed
		 *
		 * Time Complexity: O(n)
		 * - Single pass through the input string
		 * - Stack operations are O(1)
		 * - Building result string is O(n)
		 *
		 * Space Complexity: O(n)
		 * - Stack can store up to n/2 indices in worst case
		 * - Char array and StringBuilder use additional O(n) space
		 *
		 * Examples:
		 * - "lee(t(c)o)de)" → "lee(t(c)o)de"
		 * - "a)b(c)d" → "ab(c)d"
		 * - "))((" → ""
		 */
		public String minRemoveToMakeValid(String s) {
			// Use a stack to track indices of open parentheses
			Stack<Integer> stack = new Stack<>();

			// Convert string to char array for easy manipulation
			char[] chars = s.toCharArray();

			// First pass: mark invalid parentheses
			for (int i = 0; i < chars.length; i++) {
				if (chars[i] == '(') {
					// Push index of open parenthesis
					stack.push(i);
				} else if (chars[i] == ')') {
					// If there's a matching open parenthesis, pop it
					if (!stack.isEmpty()) {
						stack.pop();
					} else {
						// Mark invalid closing parenthesis
						chars[i] = '*';
					}
				}
			}

			// Mark remaining unmatched open parentheses
			while (!stack.isEmpty()) {
				chars[stack.pop()] = '*';
			}

			// Build result string, skipping marked characters
			StringBuilder result = new StringBuilder();
			for (char c : chars) {
				if (c != '*') {
					result.append(c);
				}
			}

			return result.toString();
		}
	}


	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.minRemoveToMakeValid("lee(t(c)o)de)")); // Output: "lee(t(c)o)de"
		System.out.println(solution.minRemoveToMakeValid("a)b(c)d")); // Output: "ab(c)d"
		System.out.println(solution.minRemoveToMakeValid("))((")); // Output: ""
	}
}
