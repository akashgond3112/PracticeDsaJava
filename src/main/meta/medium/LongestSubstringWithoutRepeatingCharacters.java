package main.meta.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 3. Longest Substring Without Repeating Characters Hint Given a string s, find the length of the longest substring
 * without repeating characters.
 *
 * Example 1:
 *
 * Input: s = "abcabcbb" Output: 3 Explanation: The answer is "abc", with the length of 3. Example 2:
 *
 * Input: s = "bbbbb" Output: 1 Explanation: The answer is "b", with the length of 1. Example 3:
 *
 * Input: s = "pwwkew" Output: 3 Explanation: The answer is "wke", with the length of 3. Notice that the answer must be
 * a substring, "pwke" is a subsequence and not a substring.
 *
 * Constraints:
 *
 * 0 <= s.length <= 5 * 104 s consists of English letters, digits, symbols and spaces.
 */
public class LongestSubstringWithoutRepeatingCharacters {

	/**
	 * Time complexity: O(n∗m) Space complexity: O(m) Where n is the length of the string and m is the total number of
	 * unique characters in the string.
	 */
	public static class BruteForce {
		public int lengthOfLongestSubstring(String s) {
			int res = 0;
			for (int i = 0; i < s.length(); i++) {
				Set<Character> charSet = new HashSet<>();
				for (int j = i; j < s.length(); j++) {
					if (charSet.contains(s.charAt(j))) {
						break;
					}
					charSet.add(s.charAt(j));
				}
				res = Math.max(res, charSet.size());
			}
			return res;
		}
	}

	/**
	 * Time complexity: O(n) Space complexity: O(m) Where n is the length of the string and m is the total number of
	 * unique characters in the string.
	 */
	public static class SlidingWindow {
		public int lengthOfLongestSubstring(String s) {
			HashSet<Character> charSet = new HashSet<>();
			int l = 0;
			int res = 0;

			for (int r = 0; r < s.length(); r++) {
				while (charSet.contains(s.charAt(r))) {
					charSet.remove(s.charAt(l));
					l++;
				}
				charSet.add(s.charAt(r));
				res = Math.max(res, r - l + 1);
			}
			return res;
		}
	}

	/**
	 * Time complexity: O(n) Space complexity: O(m) Where n is the length of the string and m is the total number of
	 * unique characters in the string.
	 */
	public static class SlidingWindowOptimal {
		public int lengthOfLongestSubstring(String s) {
			HashMap<Character, Integer> mp = new HashMap<>();
			int l = 0, res = 0;

			for (int r = 0; r < s.length(); r++) {
				if (mp.containsKey(s.charAt(r))) {
					l = Math.max(mp.get(s.charAt(r)) + 1, l);
				}
				mp.put(s.charAt(r), r);
				res = Math.max(res, r - l + 1);
			}
			return res;
		}
	}
}
