package main.meta.medium;

import java.util.Stack;

/**
 * 227. Basic Calculator II Medium Topics Companies Given a string s which represents an expression, evaluate this
 * expression and return its value.
 *
 * The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231,
 * 231
 * - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as
 * eval().
 *
 * Example 1:
 *
 * Input: s = "3+2*2" Output: 7 Example 2:
 *
 * Input: s = " 3/2 " Output: 1 Example 3:
 *
 * Input: s = " 3+5 / 2 " Output: 5
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 105 s consists of integers and operators ('+', '-', '*', '/') separated by some number of
 * spaces. s represents a valid expression. All the integers in the expression are non-negative integers in the range
 * [0, 231 - 1]. The answer is guaranteed to fit in a 32-bit integer.
 */
public class BasicCalculatorII {


	static class SolutionBruteForce {
		/**
		 * Evaluates a basic arithmetic expression given as a string. The expression may contain non-negative integers,
		 * '+', '-', '*', and '/' operators, and spaces. The operators '*' and '/' have higher precedence than '+' and
		 * '-'.
		 *
		 * The function processes the expression in a single pass using a stack:
		 * - Numbers are extracted and processed sequentially.
		 * - Multiplication and division are performed immediately using the stack.
		 * - Addition and subtraction are deferred by pushing values onto the stack.
		 * - Spaces are ignored.
		 *
		 * @param s
		 * 		The arithmetic expression as a string. Assumes valid input without parentheses.
		 * @return The evaluated result of the expression.
		 * @throws ArithmeticException
		 * 		If division by zero occurs.
		 *
		 * 		Time Complexity: O(N), where N is the length of the input string. Space Complexity: O(N) in the worst case
		 * 		when all numbers are pushed onto the stack.
		 */
		public int calculate(String s) {
			int currentNumber = 0;
			char currentCharacter;
			char operator = '+';
			Stack<Integer> stack = new Stack<>();

			for (int i = 0; i < s.length(); i++) {
				currentCharacter = s.charAt(i);

				if (Character.isDigit(currentCharacter)) {
					currentNumber = (currentNumber * 10) + (currentCharacter - '0');
				}

				if (!Character.isDigit(currentCharacter) && currentCharacter != ' ' || i == s.length() - 1) {
					if (operator == '-') {
						stack.push(-currentNumber);
					} else if (operator == '+') {
						stack.push(currentNumber);
					} else if (operator == '*') {
						int num1 = stack.peek();
						stack.pop();
						stack.push(num1 * currentNumber);
					} else if (operator == '/') {
						int num1 = stack.peek();
						stack.pop();
						stack.push(num1 / currentNumber);
					}
					operator = currentCharacter;
					currentNumber = 0;
				}
			}

			int result = 0;
			while (!stack.isEmpty()) {
				result += stack.pop();
			}
			return result;

		}
	}

	static class SolutionOptimized {
		/**
		 * Evaluates a basic mathematical expression represented as a string.
		 * The expression contains non-negative integers and the operators `+`, `-`, `*`, and `/`.
		 * It is assumed that the input is always valid and contains no parentheses.
		 *
		 * <p>Approach:
		 * This method iterates through the string, processing digits to form numbers and applying
		 * arithmetic operations as they appear. It uses a running total (`result`) and a variable
		 * (`lastNumber`) to handle multiplication and division correctly.
		 *
		 * @param s The mathematical expression as a string.
		 * @return The computed result of the expression.
		 *
		 * <p>Time Complexity: O(N), where N is the length of the string.
		 * <p>Space Complexity: O(1), as only a few integer variables are used.
		 */
		public int calculate(String s) {
			int currentNumber = 0;
			char currentCharacter;
			char operator = '+';
			int result = 0;
			int lastNumber = 0;

			for (int i = 0; i < s.length(); i++) {
				currentCharacter = s.charAt(i);

				if (Character.isDigit(currentCharacter)) {
					currentNumber = (currentNumber * 10) + (currentCharacter - '0');
				}

				if (!Character.isDigit(currentCharacter) && currentCharacter != ' ' || i == s.length() - 1) {
					if (operator == '+' || operator == '-') {
						result += lastNumber;
						lastNumber = operator == '+' ? currentNumber : -currentNumber;
					} else if (operator == '*') {
						lastNumber *= currentNumber;
					} else if (operator == '/') {
						lastNumber /= currentNumber;
					}
					operator = currentCharacter;
					currentNumber = 0;
				}
			}

			return result + lastNumber;
		}

	}
}
