package main.meta.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * 301. Remove Invalid Parentheses
 * Hard
 * Topics
 * Companies
 * Hint
 * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
 *
 * Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in any order.
 *
 * Example 1:
 * Input: s = "()())()"
 * Output: ["(())()","()()()"]
 *
 * Example 2:
 * Input: s = "(a)())()"
 * Output: ["(a())()","(a)()()"]
 *
 * Example 3:
 * Input: s = ")("
 * Output: [""]
 *
 * Constraints:
 * 1 <= s.length <= 25
 * s consists of lowercase English letters and parentheses '(' and ')'.
 * There will be at most 20 parentheses in s.
 * Topics
 * String
 * Backtracking
 * Breadth-First Search*/

public class RemoveInvalidParentheses {


	static class SolutionUsingRecursion {

		/**
		 * Removes the minimum number of invalid parentheses to make the string valid using recursion (backtracking).
		 *
		 * @param s Input string containing parentheses.
		 * @return List of all possible valid strings after removing the minimum parentheses.
		 *
		 * Time Complexity: O(2ⁿ)
		 * - In the worst case, we may remove each character, leading to O(2ⁿ) recursive calls.
		 * - Pruning helps in practical cases, but worst-case complexity remains exponential.
		 *
		 * Space Complexity: O(2ⁿ)
		 * - The recursive call stack takes O(n) space in the worst case.
		 * - The HashSet stores up to O(2ⁿ) unique strings.
		 */

		public List<String> removeInvalidParentheses(String s) {
			Set<String> result = new HashSet<>();  // Use HashSet to avoid duplicates
			int minRemoval = getInvalidParenthesisCount(s);
			solve(s, minRemoval, result, new HashSet<>());
			return new ArrayList<>(result);
		}

		/**
		 * Counts the number of invalid parentheses.
		 */
		private int getInvalidParenthesisCount(String s) {
			Stack<Character> stack = new Stack<>();
			for (char c : s.toCharArray()) {
				if (c == '(') {
					stack.push(c);
				} else if (c == ')') {
					if (!stack.isEmpty() && stack.peek() == '(') {
						stack.pop(); // Valid pair found
					} else {
						stack.push(c); // Unmatched closing parenthesis
					}
				}
			}
			return stack.size();  // Total number of invalid parentheses
		}

		/**
		 * Recursive function to generate valid parentheses by removing minimum characters.
		 */
		private void solve(String s, int minInvalid, Set<String> result, Set<String> visited) {
			// Base case: If we have removed required invalid parentheses
			if (minInvalid == 0) {
				if (isValid(s)) {
					result.add(s);  // Add only valid strings
				}
				return;
			}

			// Try removing each parenthesis
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;  // Skip non-parentheses

				String next = s.substring(0, i) + s.substring(i + 1);  // Remove character at i
				if (!visited.contains(next)) {  // Avoid redundant recursive calls
					visited.add(next);
					solve(next, minInvalid - 1, result, visited);
				}
			}
		}

		/**
		 * Validates whether a string has balanced parentheses.
		 */
		private boolean isValid(String s) {
			int count = 0;
			for (char c : s.toCharArray()) {
				if (c == '(') count++;
				else if (c == ')') {
					if (count == 0) return false;
					count--;
				}
			}
			return count == 0;
		}
	}

	static class SolutionUsingBfs {

		/**
		 * Removes the minimum number of invalid parentheses using BFS.
		 *
		 * @param s Input string containing parentheses.
		 * @return List of all possible valid strings after removing the minimum parentheses.
		 *
		 * Time Complexity: O(2ⁿ)
		 * - Each level of BFS explores all possible removals.
		 * - Worst case: O(2ⁿ) unique strings.
		 *
		 * Space Complexity: O(2ⁿ)
		 * - The queue can store up to O(2ⁿ) unique strings in the worst case.
		 * - The HashSet ensures no duplicate processing.
		 */

		public List<String> removeInvalidParentheses(String s) {
			List<String> result = new ArrayList<>();
			if (s == null)
				return result;

			Set<String> visited = new HashSet<>();  // Avoid duplicate processing
			Queue<String> queue = new LinkedList<>();
			queue.add(s);
			visited.add(s);
			boolean found = false;

			while (!queue.isEmpty()) {
				String curr = queue.poll();
				if (isValid(curr)) {
					result.add(curr);
					found = true;  // Stop further removals once valid string is found
				}

				if (found)
					continue;  // Skip further processing after finding valid strings

				for (int i = 0; i < curr.length(); i++) {
					if (curr.charAt(i) != '(' && curr.charAt(i) != ')')
						continue;

					String next = curr.substring(0, i) + curr.substring(i + 1);
					if (!visited.contains(next)) {
						queue.add(next);
						visited.add(next);
					}
				}
			}
			return result;
		}

		/**
		 * Checks if a given string has valid parentheses.
		 */
		private boolean isValid(String s) {
			int count = 0;
			for (char c : s.toCharArray()) {
				if (c == '(')
					count++;
				else if (c == ')') {
					if (count == 0)
						return false;
					count--;
				}
			}
			return count == 0;
		}
	}

}
