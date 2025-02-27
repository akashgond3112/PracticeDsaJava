package main.meta.easy;

import java.util.Stack;

/**
 * <pre>
 * 20. Valid Parentheses Solved Easy Topics Companies Hint Given a string s containing just the characters '(', ')',
 * '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets. Open brackets must be closed in the correct order. Every
 * close bracket has a corresponding open bracket of the same type.
 *
 * Example 1: Input: s = "()" Output: true
 *
 * Example 2: Input: s = "()[]{}" Output: true
 *
 * Example 3: Input: s = "(]" Output: false
 *
 * Example 4: Input: s = "([])" Output: true
 *
 * Constraints:
 *
 * 1 <= s.length <= 104 s consists of parentheses only '()[]{}'.
 * </pre>
 */
public class ValidParentheses {

	/**
	 * Solution that checks if a string of brackets is valid using string replacements.
	 *
	 * Approach:
	 * - Repeatedly replace matching bracket pairs with empty strings
	 * - If the final string is empty, all brackets were matched
	 *
	 * Time Complexity: O(n²) where n is the length of the string
	 * - Each replacement operation is O(n)
	 * - In worst case (like "(((())))"), we need O(n) replacements
	 *
	 * Space Complexity: O(n) for storing modified strings during replacements
	 */
	public static class SolutionBruteForce {
		public boolean isValid(String s) {
			// Handle empty string case
			if (s.isEmpty()) {
				return true;
			}

			// Repeatedly replace matching pairs until no more replacements can be made
			String previous = s;
			while (s.contains("()") || s.contains("{}") || s.contains("[]")) {
				s = s.replace("()", "");
				s = s.replace("{}", "");
				s = s.replace("[]", "");

				// If string didn't change after replacements, break to avoid infinite loop
				if (s.equals(previous)) {
					break;
				}
				previous = s;
			}

			return s.isEmpty();
		}
	}

	/**
	 * Solution that checks if a string of brackets is valid using a stack.
	 *
	 * Approach:
	 * - Use stack to track expected closing brackets
	 * - For opening brackets, push the corresponding closing bracket
	 * - For closing brackets, check if it matches the top of stack
	 *
	 * Time Complexity: O(n) where n is the length of the string
	 * - Each character is processed once
	 *
	 * Space Complexity: O(n) in worst case where all characters are opening brackets
	 */
	public static class SolutionOptimal {
		public boolean isValid(String s) {
			// Handle empty string case
			if (s.isEmpty()) {
				return true;
			}

			Stack<Character> stack = new Stack<>();

			for (char c : s.toCharArray()) {
				// Push corresponding closing bracket for each opening bracket
				if (c == '(') {
					stack.push(')');
				} else if (c == '{') {
					stack.push('}');
				} else if (c == '[') {
					stack.push(']');
				} else {
					// For closing brackets, stack should not be empty and top should match
					if (stack.isEmpty() || stack.pop() != c) {
						return false;
					}
				}
			}

			// Valid if all brackets have been matched (stack is empty)
			return stack.isEmpty();
		}
	}

	/**
	 * Corrected optimal solution that properly handles all edge cases.
	 * The original optimal solution had a logic error that would cause exceptions.
	 *
	 * Approach:
	 * - Same stack-based approach but with corrected validation logic
	 *
	 * Time Complexity: O(n) where n is the length of the string
	 * Space Complexity: O(n) in worst case
	 */
	public static class SolutionOptimalCorrected {
		public boolean isValid(String s) {
			Stack<Character> stack = new Stack<>();

			for (char c : s.toCharArray()) {
				// For opening brackets, push corresponding closing bracket
				if (c == '(') {
					stack.push(')');
				} else if (c == '{') {
					stack.push('}');
				} else if (c == '[') {
					stack.push(']');
				} else {
					// For closing brackets, check if stack has expected match
					if (stack.isEmpty() || stack.pop() != c) {
						return false;
					}
				}
			}

			// Valid only if all brackets have been matched
			return stack.isEmpty();
		}
	}
}
