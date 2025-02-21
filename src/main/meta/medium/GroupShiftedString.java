package main.meta.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Group Shifted String
 * Difficulty: MediumAccuracy: 68.62%Submissions: 1K+Points: 4
 * Given an array of strings (all lowercase letters), the task is to group them in such a way that all strings in a group are shifted versions of each other.
 *
 * Two strings s1 and s2 are called shifted if the following conditions are satisfied:
 *
 * s1.length = s2.length
 * s1[i] = s2[i] + m for 1 <= i <= s1.length  for a constant integer m
 * Examples :
 *
 * Input: arr = ["acd", "dfg", "wyz", "yab", "mop", "bdfh", "a", "x", "moqs"]
 * Output: [["acd", "dfg", "wyz", "yab", "mop"], ["bdfh", "moqs"], ["a", "x"]]
 * Explanation: All shifted strings are grouped together.
 * Input: arr = ["geek", "for", "geeks"]
 * Output: [["for"], ["geek"], ["geeks"]]
 * Input: arr = ["aaa", "adb", "bbd", "dbc", "bca"]
 * Output: [["aaa"], ["adb"], ["bbd"], ["bca"], ["dbc"]]
 * Constraints:
 * 1 ≤ arr.size() ≤ 105
 * 1 ≤ arr[i].size() ≤ 5
 *
 * Company Tags
 * Google
 * Topic Tags
 * Strings Hash Algorithms
 * */
public class GroupShiftedString {

	/**
	 * Solution for grouping shifted strings. A string s2 is called a shifted string of s1 if s2 can be obtained by
	 * circular shifts on s1. For example: "def" is a shifted string of "abc" as each character is shifted by 3.
	 */
	static class Solution {
		/**
		 * Generates a unique key for a string based on the difference between consecutive characters. Each difference
		 * is normalized to handle circular shifts (wrapping from 'z' to 'a').
		 *
		 * For example:
		 * - "abc" generates key "1#1#" because b-a=1, c-b=1
		 * - "def" generates key "1#1#" because e-d=1, f-e=1
		 *
		 * Time Complexity: O(k) where k is the length of the input string
		 * Space Complexity: O(k) for storing the key
		 * string
		 *
		 * @param s
		 * 		The input string to generate key for
		 * @return A string key representing the pattern of character differences
		 */
		private static String getKey(String s) {
			StringBuilder key = new StringBuilder();
			for (int i = 1; i < s.length(); i++) {
				char c = s.charAt(i);
				char prev = s.charAt(i - 1);

				// Calculate difference and normalize it to handle circular shifts
				int diff = c - prev;
				if (diff < 0) {
					diff += 26;  // Handle wrapping from 'z' to 'a'
				}
				key.append(diff).append("#");
			}
			return key.toString();
		}

		/**
		 * Groups strings that are shifted versions of each other. Two strings are in the same group if one can be
		 * converted to another by shifting all characters by the same number of positions in circular manner.
		 *
		 * For example: ["abc", "bcd", "def"] will be grouped together as each string can be obtained by shifting
		 * characters of another string.
		 *
		 * Time Complexity: O(N * K) where:
		 * - N is the number of strings in input array
		 * - K is the average length of strings
		 *
		 * Space Complexity: O(N * K) where:
		 * - N is the number of strings in input array (for storing results)
		 * - K is the average length of strings (for storing keys)
		 *
		 * @param arr
		 * 		Array of strings to be grouped
		 * @return ArrayList of ArrayList containing groups of shifted strings
		 */
		public ArrayList<ArrayList<String>> groupShiftedString(String[] arr) {
			HashMap<String, ArrayList<String>> map = new HashMap<>();

			// Group strings by their key pattern
			for (String s : arr) {
				String key = getKey(s);
				if (!map.containsKey(key)) {
					ArrayList<String> list = new ArrayList<>();
					list.add(s);
					map.put(key, list);
				} else {
					map.get(key).add(s);
				}
			}

			// Convert map values to result format
			ArrayList<ArrayList<String>> result = new ArrayList<>();
			for (String key : map.keySet()) {
				ArrayList<String> list = map.get(key);
				result.add(list);
			}
			return result;
		}
	}

	/**
	 * Optimized solutions for grouping shifted strings problem
	 */
	static class SolutionOptimized {
		/**
		 * Solution 1: Using character array for key generation
		 * This is faster than StringBuilder as it avoids string concatenation
		 */
		public List<List<String>> groupStrings1(String[] strings) {
			Map<String, List<String>> map = new HashMap<>();

			for (String s : strings) {
				char[] key = new char[s.length() - 1];
				// Generate key using relative differences
				for (int i = 1; i < s.length(); i++) {
					key[i-1] = (char)((s.charAt(i) - s.charAt(i-1) + 26) % 26);
				}
				String keyStr = String.valueOf(key);
				map.computeIfAbsent(keyStr, k -> new ArrayList<>()).add(s);
			}

			return new ArrayList<>(map.values());
		}

		/**
		 * Solution 2: Using normalized first character
		 * This approach normalizes the string by shifting first character to 'a'
		 */
		public List<List<String>> groupStrings2(String[] strings) {
			Map<String, List<String>> map = new HashMap<>();

			for (String s : strings) {
				// Get shift amount to make first char 'a'
				int shift = s.charAt(0) - 'a';
				char[] normalized = new char[s.length()];

				// Normalize the string
				for (int i = 0; i < s.length(); i++) {
					char c = s.charAt(i);
					normalized[i] = (char)((c - shift - 'a' + 26) % 26 + 'a');
				}

				String key = String.valueOf(normalized);
				map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
			}

			return new ArrayList<>(map.values());
		}

		/**
		 * Solution 3: Using integer key
		 * This is the most space-efficient for shorter strings as it uses
		 * a single integer as key instead of a string
		 */
		public List<List<String>> groupStrings3(String[] strings) {
			Map<Integer, List<String>> map = new HashMap<>();

			for (String s : strings) {
				int key = 0;
				for (int i = 1; i < s.length(); i++) {
					// Pack differences into a single integer
					key = key * 26 + ((s.charAt(i) - s.charAt(i-1) + 26) % 26);
				}
				map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
			}

			return new ArrayList<>(map.values());
		}
	}
}
