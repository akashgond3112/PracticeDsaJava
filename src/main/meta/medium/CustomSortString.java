package main.meta.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 791. Custom Sort String Medium Topics Companies You are given two strings order and s. All the characters of order
 * are unique and were sorted in some custom order previously.
 *
 * Permute the characters of s so that they match the order that order was sorted. More specifically, if a character x
 * occurs before a character y in order, then x should occur before y in the permuted string.
 *
 * Return any permutation of s that satisfies this property.
 *
 * Example 1: Input: order = "cba", s = "abcd" Output: "cbad" Explanation: "a", "b", "c" appear in order, so the order
 * of "a", "b", "c" should be "c", "b", and "a". Since "d" does not appear in order, it can be at any position in the
 * returned string. "dcba", "cdba", "cbda" are also valid outputs.
 *
 * Example 2: Input: order = "bcafg", s = "abcd" Output: "bcad" Explanation: The characters "b", "c", and "a" from order
 * dictate the order for the characters in s. The character "d" in s does not appear in order, so its position is
 * flexible. Following the order of appearance in order, "b", "c", and "a" from s should be arranged as "b", "c", "a".
 * "d" can be placed at any position since it's not in order. The output "bcad" correctly follows this rule. Other
 * arrangements like "dbca" or "bcda" would also be valid, as long as "b", "c", "a" maintain their order.
 *
 * Constraints:
 *
 * 1 <= order.length <= 26 1 <= s.length <= 200 order and s consist of lowercase English letters. All the characters of
 * order are unique.
 */
public class CustomSortString {

	static class Solution {
		/**
		 * Sorts a string based on a custom ordering defined by another string.
		 * Characters in the input string that don't appear in the order string are appended at the end.
		 *
		 * Algorithm:
		 * 1. Create frequency map of all characters in input string 's'
		 * 2. Process characters from 'order' string first, adding them based on their frequency
		 * 3. Add remaining characters from 's' that weren't in 'order'
		 *
		 * Example:
		 * order = "cba", s = "abcd"
		 * Result: "cbad"
		 * Explanation:
		 * - 'c', 'b', 'a' are ordered according to 'order' string
		 * - 'd' appears at the end as it's not in 'order'
		 *
		 * Time Complexity: O(N + M) where:
		 * - N is length of string s (for creating frequency map)
		 * - M is length of order string (for processing ordered characters)
		 *
		 * Space Complexity: O(K) where:
		 * - K is number of unique characters in string s (for HashMap)
		 * - StringBuilder space is not counted as it's used for output
		 *
		 * @param order The string that defines the custom ordering
		 * @param s The string to be sorted
		 * @return Sorted string according to the custom order
		 */
		public String customSortString(String order, String s) {
			// Create frequency map of characters in s
			Map<Character, Integer> map = new HashMap<>();
			for (int i = 0; i < s.length(); i++) {
				map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
			}

			// Process characters in order of 'order' string
			StringBuilder sb = new StringBuilder();
			for(char c : order.toCharArray()) {
				// Add character c based on its frequency in s
				while (map.containsKey(c) && map.get(c) > 0) {
					sb.append(c);
					map.put(c, map.get(c) - 1);
				}
			}

			// Add remaining characters that weren't in 'order'
			for (Map.Entry<Character, Integer> entry : map.entrySet()) {
				char c = entry.getKey();
				int count = entry.getValue();
				while (count > 0) {
					sb.append(c);
					count--;
				}
			}

			return sb.toString();
		}
	}
}
