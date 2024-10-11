package main.dsa.linear.stack;

import java.util.Stack;

/**
 * Infix to Postfix Difficulty: MediumAccuracy: 52.94%Submissions: 99K+Points: 4 Given an infix expression in the form
 * of string str. Convert this infix expression to postfix expression.
 *
 * Infix expression: The expression of the form a op b. When an operator is in-between every pair of operands. Postfix
 * expression: The expression of the form a b op. When an operator is followed for every pair of operands. Note: The
 * order of precedence is: ^ greater than * equals to / greater than + equals to -. Ignore the right associativity of ^.
 * Example 1:
 *
 * Input: str = "a+b*(c^d-e)^(f+g*h)-i" Output: abcd^e-fgh*+^*+i- Explanation: After converting the infix expression
 * into postfix expression, the resultant expression will be abcd^e-fgh*+^*+i- Example 2:
 *
 * Input: str = "A*(B+C)/D" Output: ABC+*D/ Explanation: After converting the infix expression into postfix expression,
 * the resultant expression will be ABC+*D/
 */
public class InfixToPostfixConversion {

	// A utility function to return
	// precedence of a given operator
	// Higher returned value means
	// higher precedence
	static int Prec(char ch) {
		return switch (ch) {
			case '+', '-' -> 1;
			case '*', '/' -> 2;
			case '^' -> 3;
			default -> -1;
		};
	}

	// The main method that converts
	// given infix expression
	// to postfix expression.
	static String infixToPostfix(String exp) {
		// initializing empty String for result
		StringBuilder result = new StringBuilder();

		// initializing empty stack
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < exp.length(); ++i) {
			char c = exp.charAt(i);

			// If the scanned character is an
			// operand, add it to output.
			if (Character.isLetterOrDigit(c))
				result.append(c);

				// If the scanned character is an '(',
				// push it to the stack.
			else if (c == '(')
				stack.push(c);

				// If the scanned character is an ')',
				// pop and output from the stack
				// until an '(' is encountered.
			else if (c == ')') {
				while (!stack.isEmpty() && stack.peek() != '(')
					result.append(stack.pop());

				stack.pop();
			} else // an operator is encountered
			{
				while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())) {

					result.append(stack.pop());
				}
				stack.push(c);
			}

		}

		// pop all the operators from the stack
		while (!stack.isEmpty()) {
			if (stack.peek() == '(')
				return "Invalid Expression";
			result.append(stack.pop());
		}
		return result.toString();
	}

	// Driver method
	public static void main(String[] args) {
		String exp = "(p+q)*(m-n)";
		System.out.println("Infix expression: " + exp);
		System.out.println("Prefix expression: " + infixToPostfix(exp));
	}
}
