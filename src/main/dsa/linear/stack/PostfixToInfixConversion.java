package main.dsa.linear.stack;

import java.util.Stack;

/**
 * Postfix to Infix Conversion Difficulty: MediumAccuracy: 49.41%Submissions: 30K+Points: 4 You are given a string that
 * represents the postfix form of a valid mathematical expression. Convert it to its infix form.
 *
 * Example:
 *
 * Input: ab*c+ Output: ((a*b)+c) Explanation: The above output is its valid infix form. Your Task:
 *
 * Complete the function string postToInfix(string post_exp), which takes a postfix string as input and returns its
 * infix form.
 *
 *
 *
 * Expected Time Complexity: O(N).
 *
 * Expected Auxiliary Space: O(N).
 *
 * Constraints:
 *
 * 3<=post_exp.length()<=104
 */
public class PostfixToInfixConversion {

	static String postToInfix(String exp) {
		// code here
		int i = 0;
		Stack<String> stack = new Stack<>();
		while (i < exp.length()) {
			char c = exp.charAt(i);

			// If the scanned character is an
			// operand, add it to output.
			if (Character.isLetterOrDigit(c))
				stack.push(String.valueOf(c));
			else {
				String peek = stack.peek();
				stack.pop();
				String peek1 = stack.peek();
				stack.pop();

				stack.push("(" + peek + c + peek1 + ")");
			}
			i++;
		}
		return stack.peek();
	}

	public static void main(String[] args) {

		PostfixToInfixConversion p = new PostfixToInfixConversion();
		System.out.println(postToInfix("ab*c+"));

	}
}
