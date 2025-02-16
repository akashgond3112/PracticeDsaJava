package main.meta.medium;

import java.util.Stack;

/**
 * 921. Minimum Add to Make Parentheses Valid
 * Medium
 * Topics
 * Companies
 * A parentheses string is valid if and only if:
 * <p>
 * It is the empty string,
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.
 * <p>
 * For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
 * Return the minimum number of moves required to make s valid.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "())"
 * Output: 1
 * Example 2:
 * <p>
 * Input: s = "((("
 * Output: 3
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 1000
 * s[i] is either '(' or ')'.*/
public class MinimumAddToMakeParenthesesValid {

	/**
	 * Solutions for finding minimum number of parentheses needed to make a string valid.
	 * Problem: Given a string s of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')',
	 * and in any positions ) so that the resulting parentheses string is valid.
	 */
	static class SolutionBruteForce {
		/**
		 * Finds the minimum number of parentheses that need to be added to make the string valid using a stack-based approach.
		 * <p>
		 * Algorithm:
		 * 1. Initialize a stack to keep track of opening parentheses and a counter for invalid closing parentheses
		 * 2. For each character in the string:
		 *    - If it's an opening parenthesis '(', push it onto the stack
		 *    - If it's a closing parenthesis ')', check if there's a matching opening parenthesis:
		 *      * If stack is empty (no opening parenthesis), increment counter
		 *      * If stack has an opening parenthesis, pop it from stack
		 * 3. Return the sum of counter (invalid closing) and remaining stack size (unmatched opening)
		 * <p>
		 * Example:
		 * Input: "())"
		 * Process:
		 * - '(' : push to stack
		 * - ')' : pop from stack
		 * - ')' : stack empty, increment counter
		 * Result: 1 (need to add one opening parenthesis)
		 *
		 * @param s the input string containing parentheses
		 * @return the minimum number of parentheses needed to make the string valid
		 * <p>
		 * Time Complexity: O(n) where n is the length of string
		 * - We iterate through each character once
		 * - Stack operations (push/pop) are O(1)
		 * <p>
		 * Space Complexity: O(n)
		 * - In worst case (all opening parentheses), we store all characters in stack
		 */
		public int minAddToMakeValid(String s) {
			Stack<Character> stack = new Stack<>();
			int count = 0;
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c == '(') {
					stack.push(c);
				} else if (c == ')') {
					if (stack.isEmpty()) {
						count++;
					} else {
						stack.pop();
					}
				}
			}
			return count + stack.size();
		}
	}

	static class SolutionOptimal {
		/**
		 * Finds the minimum number of parentheses needed using a constant space approach.
		 * <p>
		 * Algorithm:
		 * 1. Use two counters:
		 *    - opening: tracks unmatched opening parentheses
		 *    - closingImbalance: tracks invalid closing parentheses
		 * 2. For each character:
		 *    - If '(': increment opening
		 *    - If ')':
		 *      * If there's an unmatched opening, decrement opening
		 *      * Otherwise, increment closingImbalance
		 * 3. Return sum of both counters
		 * <p>
		 * Example:
		 * Input: "())"
		 * Process:
		 * - '(' : opening = 1
		 * - ')' : opening = 0
		 * - ')' : closingImbalance = 1
		 * Result: 1
		 * <p>
		 * Key Improvements over Brute Force:
		 * 1. Eliminates need for stack data structure
		 * 2. Reduces space complexity to O(1)
		 * 3. More readable and maintainable code
		 * 4. Slightly faster due to fewer object operations
		 *
		 * @param s the input string containing parentheses
		 * @return the minimum number of parentheses needed to make the string valid
		 * <p>
		 * Time Complexity: O(n) where n is the length of string
		 * - Single pass through the string
		 * - All operations within loop are O(1)
		 * <p>
		 * Space Complexity: O(1)
		 * - Only uses two integer variables regardless of input size
		 */
		public int minAddToMakeValid(String s) {
			int opening = 0;
			int closingImbalance = 0;
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c == '(') {
					opening++;
				} else if (opening > 0) {
					opening--;
				} else {
					closingImbalance++;
				}
			}
			return closingImbalance + opening;
		}
	}
}
