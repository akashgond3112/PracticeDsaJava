package main.dsa.two_pointers.medium;

import java.util.Stack;

/*
151. Reverse Words in a String
Medium
Topics
Companies
Given an input string s, reverse the order of the words.

A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

Example 1:
Input: s = "the sky is blue"
Output: "blue is sky the"

Example 2:
Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

Example 3:
Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.

Constraints:
1 <= s.length <= 104
s contains English letters (upper-case and lower-case), digits, and spaces ' '.
There is at least one word in s.

Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
*/
public class ReverseWordsInAString {

	// 12 ms
	public String reverseWords(String s) {

		StringBuilder sb = new StringBuilder();

		String[] words = s.split(" ");

		Stack<String> stack = new Stack<>();

		for (String word : words) {
			stack.push(word);
		}
		while (!stack.isEmpty()) {
			if (!stack.peek().equals(" ") && !stack.peek().isEmpty()) {
				sb.append(stack.pop()).append(" ");
			} else {
				stack.pop();
			}
		}

		return sb.toString().trim();
	}

	// 5ms
	public String reverseWordss(String s) {
		String[] words = s.trim().split(" ");

		StringBuilder result = new StringBuilder();

		for (int i = words.length - 1; i >= 0; i--) {
			if (!words[i].isEmpty()) {
				result.append(words[i]).append(" ");
			}
		}

		return result.toString().trim();
	}


	public static void main(String[] args) {

		ReverseWordsInAString reverseWordsInAString = new ReverseWordsInAString();

		String s = "a good   example";
		System.out.println(reverseWordsInAString.reverseWordss(s));
	}
}
