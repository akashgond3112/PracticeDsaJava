package main.blind75.arraysandhashing;

import java.util.HashMap;

/**
 * 242. Valid Anagram Easy Topics Companies Given two strings s and t, return true if t is an anagram of s, and false
 * otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 *
 * Output: true
 *
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 *
 * Output: false
 *
 *
 *
 * Constraints:
 *
 * 1 <= s.length, t.length <= 5 * 104 s and t consist of lowercase English letters.
 *
 *
 * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 */
public class ValidAnagram {

	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}

		HashMap<Character, Integer> countS = new HashMap<>();
		HashMap<Character, Integer> countT = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			countS.put(s.charAt(i), countS.getOrDefault(s.charAt(i), 0) + 1);
			countT.put(t.charAt(i), countT.getOrDefault(t.charAt(i), 0) + 1);
		}
		return countS.equals(countT);
	}

		public boolean isAnagramOptimal(String s, String t) {
			if (s.length() != t.length()) {
				return false;
			}

			int[] count = new int[26];
			for (int i = 0; i < s.length(); i++) {
				count[s.charAt(i) - 'a']++;
				count[t.charAt(i) - 'a']--;
			}

			for (int val : count) {
				if (val != 0) {
					return false;
				}
			}
			return true;
		}
}
