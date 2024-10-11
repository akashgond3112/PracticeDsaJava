package main.dsa.linear.stack;

import java.util.Stack;

/*
20. Valid Parentheses
Easy
Topics
Companies
Hint
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.


Example 1:

Input: s = "()"

Output: true

Example 2:

Input: s = "()[]{}"

Output: true

Example 3:

Input: s = "(]"

Output: false

Example 4:

Input: s = "([])"

Output: true

Constraints:

1 <= s.length <= 104
s consists of parentheses only '()[]{}'.
*/
public class ValidParentheses {

	public boolean isValid(String s) {

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
				if((value == ')' && ch == '(') ||  (value == ']' && ch == '[') || (value == '}' && ch == '{')) continue;
				else return false;

			}


		}
		return stack.isEmpty();
	}

	public static void main(String[] args) {
		ValidParentheses validParentheses = new ValidParentheses();
		System.out.println(validParentheses.isValid("()[]{}"));
	}
}
