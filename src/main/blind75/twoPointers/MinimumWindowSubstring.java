package main.blind75.twoPointers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 76. Minimum Window Substring Solved Hard Topics Companies Hint Given two strings s and t of lengths m and n
 * respectively, return the minimum window substring of s such that every character in t (including duplicates) is
 * included in the window. If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 *
 * Example 1:
 *
 * Input: s = "ADOBECODEBANC", t = "ABC" Output: "BANC" Explanation: The minimum window substring "BANC" includes 'A',
 * 'B', and 'C' from string t. Example 2:
 *
 * Input: s = "a", t = "a" Output: "a" Explanation: The entire string s is the minimum window. Example 3:
 *
 * Input: s = "a", t = "aa" Output: "" Explanation: Both 'a's from t must be included in the window. Since the largest
 * window of s only has one 'a', return empty string.
 *
 *
 * Constraints:
 *
 * m == s.length n == t.length 1 <= m, n <= 105 s and t consist of uppercase and lowercase English letters.
 *
 *
 * Follow up: Could you find an algorithm that runs in O(m + n) time?
 */
public class MinimumWindowSubstring {

	static class BruteForceI {
		public String minWindow(String s, String t) {

			int min = Integer.MAX_VALUE;
			int sIndex = -1;
			int n = s.length();
			int m = t.length();

			// Outer loop: Start of the window
			for (int i = 0; i < n; i++) {
				Map<Character, Integer> map = new HashMap<>();

				// Populate map with characters from `t`
				for (int j = 0; j < m; j++) {
					map.put(t.charAt(j), map.getOrDefault(t.charAt(j), 0) + 1);
				}

				int cnt = 0; // Tracks number of matched characters

				// Inner loop: Expand the window
				for (int j = i; j < n; j++) {
					char ch = s.charAt(j);
					if (map.getOrDefault(ch, 0) > 0) {
						cnt++;
					}
					map.put(ch, map.getOrDefault(ch, 0) - 1);

					// Check if all characters of `t` are matched
					if (cnt == m) {
						if (j - i + 1 < min) {
							min = j - i + 1;
							sIndex = i;
						}
						break; // Exit as soon as we find a valid window
					}
				}
			}

			// Return the substring or empty string if no valid window is found
			return sIndex == -1 ? "" : s.substring(sIndex, sIndex + min);
		}
	}

	static class OptimalI {
		public String minWindow(String s, String t) {
			if (s == null || t == null || s.length() < t.length()) {
				return "";
			}

			int n = s.length();
			int m = t.length();
			Map<Character, Integer> map = new HashMap<>();

			// Populate frequency map for characters in `t`
			for (int i = 0; i < m; i++) {
				char ch = t.charAt(i);
				map.put(ch, map.getOrDefault(ch, 0) + 1);
			}

			int left = 0, right = 0;
			int required = map.size(); // Number of unique characters in `t`
			int formed = 0;            // Number of unique characters matched
			Map<Character, Integer> windowCounts = new HashMap<>();

			int minLen = Integer.MAX_VALUE;
			int start = 0;

			while (right < n) {
				// Expand the window by adding `s[right]`
				char ch = s.charAt(right);
				windowCounts.put(ch, windowCounts.getOrDefault(ch, 0) + 1);

				// If the current character's count matches the desired count in `t`
				if (map.containsKey(ch) && windowCounts.get(ch).intValue() == map.get(ch).intValue()) {
					formed++;
				}

				// Contract the window until it's no longer valid
				while (left <= right && formed == required) {
					char leftChar = s.charAt(left);

					// Update minimum window length and start index
					if (right - left + 1 < minLen) {
						minLen = right - left + 1;
						start = left;
					}

					// Remove `s[left]` from the window
					windowCounts.put(leftChar, windowCounts.get(leftChar) - 1);
					if (map.containsKey(leftChar) && windowCounts.get(leftChar) < map.get(leftChar)) {
						formed--;
					}

					left++;
				}

				// Expand the window
				right++;
			}

			return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
		}
	}

	public static void main(String[] args) {
		String s = "ADOBECODEBANC";
		String t = "ABC";

		BruteForceI bruteForceI = new BruteForceI();
		System.out.println("Minimum Window Substring: " + bruteForceI.minWindow(s, t));
	}
}
