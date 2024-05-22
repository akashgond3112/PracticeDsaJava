package main.two_pointers.easy;

/*
925. Long Pressed Name
Easy
Topics
Companies
Your friend is typing his name into a keyboard. Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.

You examine the typed characters of the keyboard. Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.



Example 1:

Input: name = "alex", typed = "aaleex"
Output: true
Explanation: 'a' and 'e' in 'alex' were long pressed.
Example 2:

Input: name = "saeed", typed = "ssaaedd"
Output: false
Explanation: 'e' must have been pressed twice, but it was not in the typed output.


Constraints:

1 <= name.length, typed.length <= 1000
name and typed consist of only lowercase English letters.*/

import static org.junit.Assert.*;

public class LongPressedName {

	public static boolean isLongPressedName(String name, String typed) {

		int j=0;
		int i=0;

		while(j<typed.length()) {

			if(i < name.length() && name.charAt(i) == typed.charAt(j)) {
				i++;
			}else if (j ==0 || typed.charAt(j) != typed.charAt(j-1)){
				return false;
			}
			j++;
		}

		return i == name.length();
	}


	public static void main(String[] args) {

		String name = "alex";
		String typed = "aaleex";
		boolean result = isLongPressedName(name, typed);
		assertEquals(true, result);

		name = "abcd";
		typed = "aaabbbcccddd";
		result = isLongPressedName(name, typed);
		assertEquals(true, result);

	}
}
