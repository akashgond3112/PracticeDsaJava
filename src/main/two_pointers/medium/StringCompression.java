package main.two_pointers.medium;

/*
443. String Compression
Medium
Topics
Companies
Hint
Given an array of characters chars, compress it using the following algorithm:

Begin with an empty string s. For each group of consecutive repeating characters in chars:

If the group's length is 1, append the character to s.
Otherwise, append the character followed by the group's length.
The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.

After you are done modifying the input array, return the new length of the array.

You must write an algorithm that uses only constant extra space.



Example 1:

Input: chars = ["a","a","b","b","c","c","c"]
Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
Example 2:

Input: chars = ["a"]
Output: Return 1, and the first character of the input array should be: ["a"]
Explanation: The only group is "a", which remains uncompressed since it's a single character.
Example 3:

Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".


Constraints:

1 <= chars.length <= 2000
chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.
*/

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StringCompression {

	public static int compress(char[] chars) {

		StringBuilder sb = new StringBuilder();

		Map<Character, Integer> map = new HashMap<>();

		for (char aChar : chars) {
			map.put(aChar, map.getOrDefault(aChar, 0) + 1);
		}

		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				sb.append(entry.getKey());
			} else if (entry.getValue() < 10) {
				sb.append(entry.getKey()).append(entry.getValue());
			} else {
				sb.append(entry.getKey());

				String val = String.valueOf(entry.getValue());

				for (int i = 0; i < val.length(); i++) {
					sb.append(val.charAt(i));
				}

			}
		}

		String compressedStr = sb.toString();
		char[] compressedChars = compressedStr.toCharArray();

		System.arraycopy(compressedChars, 0, chars, 0, compressedChars.length);

		return compressedChars.length;

	}

	public static void main(String[] args) {
		char[] arr;
		int result;

		arr = new char[] { 'a','b','b','b','b','b','b','b','b','b','b','b','b' };
		result = compress(arr);
		assertEquals(6, result);

	}
}
