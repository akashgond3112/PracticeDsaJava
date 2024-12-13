package main.blind75.sliding.window;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 424. Longest Repeating Character Replacement You are given a string s and an integer k. You can choose any character
 * of the string and change it to any other uppercase English character. You can perform this operation at most k
 * times.
 *
 * Return the length of the longest substring containing the same letter you can get after performing the above
 * operations.
 *
 * Example 1:
 *
 * Input: s = "ABAB", k = 2 Output: 4 Explanation: Replace the two 'A's with two 'B's or vice versa. Example 2:
 *
 * Input: s = "AABABBA", k = 1 Output: 4 Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA". The
 * substring "BBBB" has the longest repeating letters, which is 4. There may exists other ways to achieve this answer
 * too.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105 s consists of only uppercase English letters. 0 <= k <= s.length
 */
public class LongestRepeatingCharacterReplacement {

	/**
	 * Time & Space Complexity Time complexity: O(n^2 ) Space complexity: O(m) Where n is the length of the string and m
	 * is the total number of unique characters in the string.
	 */
	public static class BruteForce {
		public int characterReplacement(String s, int k) {
			int res = 0;
			for (int i = 0; i < s.length(); i++) {
				HashMap<Character, Integer> count = new HashMap<>();
				int maxf = 0;
				for (int j = i; j < s.length(); j++) {
					count.put(s.charAt(j), count.getOrDefault(s.charAt(j), 0) + 1);
					maxf = Math.max(maxf, count.get(s.charAt(j)));
					if ((j - i + 1) - maxf <= k) {
						res = Math.max(res, j - i + 1);
					}
				}
			}
			return res;
		}
	}

	/**
	 * Time complexity: O ( m ∗ n ) O(m∗n) Space complexity: O ( m ) O(m) Where n is the length of the string and m is
	 * the total number of unique characters in the string.
	 */
	public static class SlidingWindow {
		public int characterReplacement(String s, int k) {
			int res = 0;
			HashSet<Character> charSet = new HashSet<>();
			for (char c : s.toCharArray()) {
				charSet.add(c);
			}

			for (char c : charSet) {
				int count = 0, l = 0;
				for (int r = 0; r < s.length(); r++) {
					if (s.charAt(r) == c) {
						count++;
					}

					while ((r - l + 1) - count > k) {
						if (s.charAt(l) == c) {
							count--;
						}
						l++;
					}

					res = Math.max(res, r - l + 1);
				}
			}
			return res;
		}
	}

	/**
	 * Time complexity: O(n) Space complexity: O(m) Where n is the length of the string and m is the
	 * total number of unique characters in the string.
	 */
	public static class SlidingWindowOptimal {
		public int characterReplacement(String s, int k) {
			HashMap<Character, Integer> count = new HashMap<>();
			int res = 0;

			int l = 0, maxf = 0;
			for (int r = 0; r < s.length(); r++) {
				count.put(s.charAt(r), count.getOrDefault(s.charAt(r), 0) + 1);
				maxf = Math.max(maxf, count.get(s.charAt(r)));

				while ((r - l + 1) - maxf > k) {
					count.put(s.charAt(l), count.get(s.charAt(l)) - 1);
					l++;
				}
				res = Math.max(res, r - l + 1);
			}

			return res;
		}
	}
}
