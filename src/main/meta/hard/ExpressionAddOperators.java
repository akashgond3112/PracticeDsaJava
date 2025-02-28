package main.meta.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 282. Expression Add Operators
 * Hard
 * Topics
 * Companies
 * Hint
 * Given a string num that contains only digits and an integer target, return all possibilities to insert the binary operators '+', '-', and/or '*'
 * between the digits of num so that the resultant expression evaluates to the target value.
 *
 * Note that operands in the returned expressions should not contain leading zeros.
 *
 * Example 1:
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 * Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
 *
 * Example 2:
 * Input: num = "232", target = 8
 * Output: ["2*3+2","2+3*2"]
 * Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
 * Example 3:
 *
 * Input: num = "3456237490", target = 9191
 * Output: []
 * Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.
 *
 * Constraints:
 * 1 <= num.length <= 10
 * num consists of only digits.
 * -231 <= target <= 231 - 1
 * Topics
 * Math
 * String
 * Backtracking
 * </pre>
 */
public class ExpressionAddOperators {

	/**
	 * Solution for the "Expression Add Operators" problem.
	 *
	 * Problem: Given a string of digits and a target value, add operators +, -, and * between the digits to form
	 * expressions that evaluate to the target.
	 *
	 * Approach:
	 * - Use backtracking (DFS) to try all possible combinations of operators
	 * - Handle multiplication with special care due to its precedence
	 * - Keep track of the current result and the last operand for multiplication precedence
	 *
	 * Time Complexity: O(4^n) where n is the length of the input string
	 * - At each position, we have 4 choices: no operator, +, -, *
	 *
	 * Space Complexity: O(n) for the recursion stack and to store the current expression
	 */
	static class Solution {
		/**
		 * Main method to find all expressions that evaluate to the target.
		 *
		 * @param num
		 * 		The string of digits
		 * @param target
		 * 		The target value
		 * @return List of valid expressions that evaluate to the target
		 */
		public List<String> addOperators(String num, int target) {
			List<String> result = new ArrayList<>();
			if (num == null || num.isEmpty()) {
				return result;
			}

			backtrack(result, num, target, 0, "", 0, 0);
			return result;
		}

		/**
		 * Backtracking helper method to build expressions.
		 *
		 * @param result
		 * 		List to store valid expressions
		 * @param num
		 * 		Input string of digits
		 * @param target
		 * 		Target value to reach
		 * @param index
		 * 		Current index in the num string
		 * @param expr
		 * 		Current expression being built
		 * @param evaluated
		 * 		Current evaluated value of the expression
		 * @param lastNumber
		 * 		Last number added (for multiplication precedence)
		 */
		private void backtrack(List<String> result, String num, int target, int index, String expr, long evaluated,
				long lastNumber) {
			// Base case: reached the end of the num string
			if (index == num.length()) {
				if (evaluated == target) {
					result.add(expr);
				}
				return;
			}

			// Try different lengths of substrings starting at current index
			for (int i = index; i < num.length(); i++) {
				// Skip leading zeros for numbers with more than 1 digit
				if (i > index && num.charAt(index) == '0') {
					break;
				}

				// Extract the current number
				String current = num.substring(index, i + 1);
				long currentNum = Long.parseLong(current);

				// If this is the first number (no operators yet)
				if (index == 0) {
					backtrack(result, num, target, i + 1, current, currentNum, currentNum);
				} else {
					// Add with +
					backtrack(result, num, target, i + 1, expr + "+" + current, evaluated + currentNum, currentNum);

					// Subtract with -
					backtrack(result, num, target, i + 1, expr + "-" + current, evaluated - currentNum, -currentNum);

					// Multiply with *
					// For multiplication, we need to subtract the last number,
					// then add (last number * current number) to handle precedence
					backtrack(result, num, target, i + 1, expr + "*" + current,
							evaluated - lastNumber + (lastNumber * currentNum), lastNumber * currentNum);
				}
			}
		}
	}
}
