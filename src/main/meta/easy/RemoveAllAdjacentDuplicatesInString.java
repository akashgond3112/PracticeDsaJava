package main.meta.easy;

import java.util.Stack;

/**
 * 1047. Remove All Adjacent Duplicates In String Easy Topics Companies Hint You are given a string s consisting of
 * lowercase English letters. A duplicate removal consists of choosing two adjacent and equal letters and removing
 * them.
 * <p>
 * We repeatedly make duplicate removals on s until we no longer can.
 * <p>
 * Return the final string after all such duplicate removals have been made. It can be proven that the answer is
 * unique.
 * <p>
 *
 * Example 1: Input: s = "abbaca" Output: "ca" Explanation: For example, in "abbaca" we could remove "bb" since the
 * letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is
 * "aaca", of which only "aa" is possible, so the final string is "ca".
 *
 * Example 2: Input: s = "azxxzy" Output: "ay"
 *
 * Constraints: 1 <= s.length <= 105 s consists of lowercase English letters.
 */
public class RemoveAllAdjacentDuplicatesInString {

	/**
	 * #### **Time Complexity:**
	 * - We traverse the string **once** (`O(n)`) and for each character, we either **append** or **delete** from
	 * `StringBuilder`, which takes **O(1)** time per operation.
	 * - Thus, the overall **time complexity is O(n)**.
	 *
	 * #### **Space Complexity:**
	 * - The worst-case space complexity occurs when there are **no duplicates**, meaning we store the entire string in
	 * `StringBuilder`, which takes **O(n) space**.
	 * - In cases where duplicates exist, the space usage is **less than O(n)** but still **O(n) in the worst case**.
	 *
	 * ### **Final Complexity:**
	 * - **Time Complexity: O(n)**
	 * - **Space Complexity: O(n) (due to the StringBuilder stack usage)**
	 *
	 * This is the most optimal approach compared to recursion, which would use **O(n) extra space** for function calls.
	 * 🚀
	 */
	static class Solution {
		public String removeDuplicates(String s) {
			StringBuilder stack = new StringBuilder();
			for (char c : s.toCharArray()) {
				int len = stack.length();
				if (len > 0 && stack.charAt(len - 1) == c) {
					stack.deleteCharAt(len - 1); // Remove duplicate
				} else {
					stack.append(c); // Push to stack
				}
			}
			return stack.toString();
		}
	}


	/**
	 * Solution for removing consecutive duplicate characters from a string using recursion. For example: "aabb" ->
	 * "ab", "aabbcc" -> "abc"
	 */
	static class SolutionUsingRecursion {
		/**
		 * Removes consecutive duplicate characters from a string recursively.
		 *
		 * Algorithm:
		 * 1. Base case: if string is empty, return it
		 * 2. If first two characters are same:
		 * - Skip all consecutive occurrences of first character
		 * - Recursively process remaining string
		 * 3. If first two characters are different:
		 * - Keep first character
		 * - Recursively process remaining string
		 *
		 * Time Complexity: O(n²) due to substring operations Space Complexity: O(n) for recursion stack
		 *
		 * @param s
		 * 		Input string from which duplicates need to be removed
		 * @return String with consecutive duplicates removed
		 */
		public String removeDuplicates(String s) {
			// Base case: if the string is empty or has one character, return it as is.
			if (s.length() <= 1) {
				return s;
			}

			// Use StringBuilder to efficiently modify the string
			StringBuilder sb = new StringBuilder(s);
			int i = 0;

			while (i < sb.length() - 1) {
				if (sb.charAt(i) == sb.charAt(i + 1)) {
					int j = i;
					// Remove all consecutive occurrences of sb.charAt(i)
					while (j < sb.length() && sb.charAt(j) == sb.charAt(i)) {
						j++;
					}
					// Remove duplicates and recursively process the rest
					return removeDuplicates(sb.substring(0, i) + sb.substring(j));
				}
				i++;
			}

			return sb.toString();
		}
	}

	/**
	 * Solution for removing consecutive duplicate characters from a string using a stack.
	 *
	 * Time Complexity: O(n) - single pass through the string
	 * Space Complexity: O(n) - using a stack to store characters
	 */
	static class SolutionUsingStack {
		public String removeDuplicates(String s) {
			// Use a stack to track characters
			Stack<Character> stack = new Stack<>();

			// Iterate through each character in the string
			for (char c : s.toCharArray()) {
				// If stack is not empty and current char matches top of stack
				if (!stack.isEmpty() && stack.peek() == c) {
					// Remove the top element (duplicate)
					stack.pop();
				} else {
					// Otherwise, push the current character
					stack.push(c);
				}
			}

			// Convert stack to string
			StringBuilder result = new StringBuilder();
			for (Character c : stack) {
				result.append(c);
			}

			return result.toString();
		}
	}
}
