package main.dsa.linear.stack;

import java.util.Stack;

/**
 * Prefix to Infix Conversion Difficulty: MediumAccuracy: 69.51%Submissions: 16K+Points: 4 You are given a string S of
 * size N that represents the prefix form of a valid mathematical expression. The string S contains only lowercase and
 * uppercase alphabets as operands and the operators are +, -, *, /, %, and ^.Convert it to its infix form.
 *
 * Example 1:
 *
 * Input: *-A/BC-/AKL Output: ((A-(B/C))*((A/K)-L)) Explanation: The above output is its valid infix form. Your Task:
 *
 * Your task is to complete the function string preToInfix(string pre_exp), which takes a prefix string as input and
 * return its infix form.
 *
 * Expected Time Complexity: O(N).
 *
 * Expected Auxiliary Space: O(N).
 *
 * Constraints:
 *
 * 3<=|S|<=104
 */
public class PrefixToInfixConversion {

	// Utility function to check if character is operator
	static boolean isOperator(char x) {
		return (x == '+' || x == '-' || x == '*' || x == '/' || x == '^');
	}

	// Convert prefix to infix
	static String preToInfix(String pre_exp) {
		Stack<String> stack = new Stack<>();

		// Reading from right to left in the prefix expression
		for (int i = pre_exp.length() - 1; i >= 0; i--) {
			char ch = pre_exp.charAt(i);

			// If the character is an operator
			if (isOperator(ch)) {
				// Pop two operands from stack
				String operand1 = stack.pop();
				String operand2 = stack.pop();

				// Concatenate them in infix form (operand1 operator operand2)
				String expr = "(" + operand1 + ch + operand2 + ")";

				// Push the resulting string back to stack
				stack.push(expr);
			} else {
				// If the character is an operand, push it to the stack
				stack.push(ch + "");
			}
		}

		// Stack will contain the final infix expression
		return stack.pop();
	}

	// Driver method
	public static void main(String[] args) {
		String exp = "*+pq-mn";  // Prefix expression example
		System.out.println("Prefix expression: " + exp);
		System.out.println("Infix expression: " + preToInfix(exp));
	}

}
