package main.meta.hard;

import java.util.HashMap;
import java.util.Map;

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

	/**
	 * Brute Force solution for finding the minimum window substring.
	 *
	 * Problem: Given two strings s and t, find the minimum window substring of s that contains all characters of t.
	 *
	 * Time Complexity: O(n * m * n) where:
	 *   - n is the length of string s
	 *   - m is the length of string t
	 *   - First n: we try each possible starting position in s
	 *   - m: we count each character in t
	 *   - Second n: in worst case, we might examine the entire remaining string for each starting position
	 *
	 * Space Complexity: O(k) where k is the size of the character set (constant if only ASCII, but up to m in worst case)
	 */
	static class BruteForce {
		public String minWindow(String s, String t) {
			// Track minimum window length and starting index
			int min = Integer.MAX_VALUE;
			int sIndex = -1;  // Will remain -1 if no valid window is found
			int n = s.length();
			int m = t.length();

			// Outer loop: Consider each position in s as potential window start
			// Time Complexity: O(n)
			for (int i = 0; i < n; i++) {
				Map<Character, Integer> map = new HashMap<>();

				// Populate map with character frequencies from t
				// Time Complexity: O(m)
				for (int j = 0; j < m; j++) {
					map.put(t.charAt(j), map.getOrDefault(t.charAt(j), 0) + 1);
				}

				int cnt = 0; // Tracks number of matched characters from t

				// Inner loop: Expand the window to the right
				// Time Complexity: O(n) in worst case
				for (int j = i; j < n; j++) {
					char ch = s.charAt(j);

					// If current character is needed for a match
					if (map.getOrDefault(ch, 0) > 0) {
						cnt++;
					}
					// Decrement frequency (may go negative for characters not in t)
					map.put(ch, map.getOrDefault(ch, 0) - 1);

					// Check if all characters of t are matched
					if (cnt == m) {
						// Update minimum window information if better than previous
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

	/**
	 * Optimal sliding window solution for finding the minimum window substring.
	 * Uses a more efficient approach with a single pass through the string.
	 *
	 * Time Complexity: O(n + m) where:
	 *   - n is the length of string s (processed once in the sliding window)
	 *   - m is the length of string t (processed once to build the frequency map)
	 *
	 * Space Complexity: O(k) where k is the size of the character set
	 *   (constant if only ASCII, but up to min(m,n) unique characters in worst case)
	 */
	static class Optimal {
		public String minWindow(String s, String t) {
			// Handle edge cases
			if (s == null || t == null || s.length() < t.length()) {
				return "";
			}

			int n = s.length();
			int m = t.length();
			Map<Character, Integer> map = new HashMap<>();

			// Populate frequency map for characters in t
			// Time Complexity: O(m)
			for (int i = 0; i < m; i++) {
				char ch = t.charAt(i);
				map.put(ch, map.getOrDefault(ch, 0) + 1);
			}

			int left = 0, right = 0;
			int required = map.size(); // Number of unique characters in t
			int formed = 0;            // Number of unique characters matched in current window
			Map<Character, Integer> windowCounts = new HashMap<>();

			int minLen = Integer.MAX_VALUE;
			int start = 0;

			// Sliding window approach
			// Time Complexity: O(n)
			// Note: While there are nested loops, each element of s is processed at most twice
			// (once when expanding the window with right pointer, once when contracting with left pointer)
			while (right < n) {
				// Expand the window by adding s[right]
				char ch = s.charAt(right);
				windowCounts.put(ch, windowCounts.getOrDefault(ch, 0) + 1);

				// If the current character's count matches the desired count in t
				// Note: intValue() used for exact object comparison rather than value
				if (map.containsKey(ch) && windowCounts.get(ch).intValue() == map.get(ch).intValue()) {
					formed++;
				}

				// Contract the window from the left until it's no longer valid
				while (left <= right && formed == required) {
					char leftChar = s.charAt(left);

					// Update minimum window length and start index if better
					if (right - left + 1 < minLen) {
						minLen = right - left + 1;
						start = left;
					}

					// Remove s[left] from the window
					windowCounts.put(leftChar, windowCounts.get(leftChar) - 1);
					// Check if removing this character breaks the required count
					if (map.containsKey(leftChar) && windowCounts.get(leftChar) < map.get(leftChar)) {
						formed--;
					}

					// Contract window from left
					left++;
				}

				// Expand window from right
				right++;
			}

			return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
		}
	}
}
