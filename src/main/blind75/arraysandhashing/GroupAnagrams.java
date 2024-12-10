package main.blind75.arraysandhashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 49. Group Anagrams Medium Topics Companies Given an array of strings strs, group the anagrams together. You can
 * return the answer in any order.
 *
 * Example 1:
 *
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 *
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Explanation:
 *
 * There is no string in strs that can be rearranged to form "bat". The strings "nat" and "tan" are anagrams as they can
 * be rearranged to form each other. The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form
 * each other. Example 2:
 *
 * Input: strs = [""]
 *
 * Output: [[""]]
 *
 * Example 3:
 *
 * Input: strs = ["a"]
 *
 * Output: [["a"]]
 *
 * Constraints:
 *
 * 1 <= strs.length <= 104 0 <= strs[i].length <= 100 strs[i] consists of lowercase English letters.
 */
public class GroupAnagrams {

	/**
	 * Time complexity: O(m∗nlogn) Space complexity: O(m∗n)
	 * Where m is the number of strings and n is the length of the longest string.
	 */
	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> res = new HashMap<>();
		for (String s : strs) {
			char[] charArray = s.toCharArray();
			Arrays.sort(charArray);
			String sortedS = new String(charArray);
			res.putIfAbsent(sortedS, new ArrayList<>());
			res.get(sortedS).add(s);
		}
		return new ArrayList<>(res.values());
	}

	/**
	 * Time complexity: O(m∗n)
	 * Space complexity: O(m)
	 * Where m is the number of strings and n is the length of the
	 * longest string.
	 */
	public List<List<String>> groupAnagramsOptimal(String[] strs) {
		Map<String, List<String>> res = new HashMap<>();
		for (String s : strs) {
			int[] count = new int[26];
			for (char c : s.toCharArray()) {
				count[c - 'a']++;
			}
			String key = Arrays.toString(count);
			res.putIfAbsent(key, new ArrayList<>());
			res.get(key).add(s);
		}
		return new ArrayList<>(res.values());
	}
}
