package main.dsa.two_pointers.easy;

/*
125. Valid Palindrome
Easy
Topics
Companies
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.



Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
Example 3:

Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.


Constraints:

1 <= s.length <= 2 * 105
s consists only of printable ASCII characters.
*/
public class ValidPalindrome {

	public static boolean isPalindromeBruteForce(String s) {
		// Step 1: Clean the string
		StringBuilder cleaned = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (Character.isLetterOrDigit(c)) {
				cleaned.append(Character.toLowerCase(c));
			}
		}

		// Edge case: If the cleaned string is empty
		if (cleaned.isEmpty()) {
			return true;
		}

		// Step 2: Reverse the cleaned string
		int n = cleaned.length();
		char[] reversedArray = new char[n];
		for (int i = 0; i < n; i++) {
			reversedArray[i] = cleaned.charAt(n - 1 - i);
		}
		String reversed = new String(reversedArray);

		// Step 3: Compare the cleaned string with the reversed string
		return cleaned.toString().equals(reversed);
	}

	public static boolean isPalindrome(String s) {
		// Initialize pointers
		int left = 0;
		int right = s.length() - 1;

		while (left < right) {
			// Move left pointer to the right until an alphanumeric character is found
			while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
				left++;
			}

			// Move right pointer to the left until an alphanumeric character is found
			while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
				right--;
			}

			// Compare characters at left and right pointers
			if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
				return false;
			}

			// Move pointers
			left++;
			right--;
		}

		return true;
	}
}
