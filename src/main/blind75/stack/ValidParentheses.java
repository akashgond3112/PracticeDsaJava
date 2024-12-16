package main.blind75.stack;

import java.util.Stack;

/**
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
 */
public class ValidParentheses {

	static class BruteForce {
		public boolean isValidI(String s) {
			while (s.contains("()") || s.contains("{}") || s.contains("[]")) {
				s = s.replace("()", "");
				s = s.replace("{}", "");
				s = s.replace("[]", "");
			}
			return s.isEmpty();
		}

		private boolean recursive(String s, int index, int round, int curly, int square) {
			// If any count becomes negative, parentheses are unbalanced
			if (round < 0 || curly < 0 || square < 0) {
				return false;
			}

			// If we've reached the end, check if all counts are zero (balanced)
			if (index == s.length()) {
				return round == 0 && curly == 0 && square == 0;
			}

			// Get the current character
			char current = s.charAt(index);

			// Increment/decrement respective counters based on the character
			if (current == '(') {
				return recursive(s, index + 1, round + 1, curly, square);
			} else if (current == ')') {
				return recursive(s, index + 1, round - 1, curly, square);
			} else if (current == '{') {
				return recursive(s, index + 1, round, curly + 1, square);
			} else if (current == '}') {
				return recursive(s, index + 1, round, curly - 1, square);
			} else if (current == '[') {
				return recursive(s, index + 1, round, curly, square + 1);
			} else if (current == ']') {
				return recursive(s, index + 1, round, curly, square - 1);
			}

			// Ignore other characters
			return recursive(s, index + 1, round, curly, square);
		}

		public boolean isValidII(String s) {
			return recursive(s, 0, 0, 0, 0);
		}
	}

	static class OptimalI {

		public boolean isValidI(String s) {
			int min = 0;
			int max = 0;

			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '(') {
					min++;
					max++;
				} else if (s.charAt(i) == ')') {
					min--;
					max++;
				} else if (s.charAt(i) == '*') {
					min = min - 1;
					max = max + 1;
				}
			}
			if (min < 0) {
				min = 0;
			}
			if (max < 0) {
				return false;
			}

			return min == 0;
		}

		public boolean isValidII(String s) {
			char[] charArray = s.toCharArray();

			Stack<Character> stack = new Stack<>();
			for (char value : charArray) {
				if (value == '(' || value == '{' || value == '[') {
					stack.push(value);
				} else {
					if (stack.isEmpty()) {
						return false;
					}
					char ch = stack.pop();
					if ((value == ')' && ch == '(') || (value == ']' && ch == '[') || (value == '}' && ch == '{'))
						continue;
					else
						return false;
				}
			}
			return stack.isEmpty();
		}
	}
}
