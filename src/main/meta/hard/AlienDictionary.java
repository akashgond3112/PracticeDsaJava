package main.meta.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *Alien Dictionary
 * Difficulty: HardAccuracy: 47.81%Submissions: 143K+Points: 8
 * Given a sorted dictionary of an alien language having some words dict and k starting alphabets of a standard dictionary. Find the order of characters in the alien language. If no valid ordering of letters is possible, then return an empty string.
 *
 * Note: Many orders may be possible for a particular test case, thus you may return any valid order and output will be "true" if the order of string returned by the function is correct else "false" denotes incorrect string returned.
 *
 * Examples:
 *
 * Input: dict[] = ["baa","abcd","abca","cab","cad"], k = 4
 * Output: true
 * Explanation: Here order of characters is 'b', 'd', 'a', 'c' Note that words are sorted and in the given language "baa" comes before "abcd", therefore 'b' is before 'a' in output.
 * Similarly, we can find other orders.
 * Input: dict[] = ["caa","aaa","aab"], k = 3
 * Output: true
 * Explanation: Here order of characters is 'c', 'a', 'b' Note that words are sorted and in the given language "caa" comes before "aaa", therefore 'c' is before 'a' in output.
 * Similarly, we can find other orders.
 * Input: dict[] = ["dhhid","dahi","cedg","fg","gdah","i","gbdei","hbgf","e","ddde"], k = 9
 * Output: false
 * Constraints:
 * 1 ≤ dict.size()≤ 104
 * 1 ≤ k ≤ 26
 * 1 ≤ Length of words ≤ 50 */
public class AlienDictionary {

	/**
	 * Time complexity: O ( N + V + E ) O(N+V+E) Space complexity: O ( V + E ) O(V+E) Where V is the number of unique
	 * characters, E is the number of edges and N is the sum of lengths of all the strings.
	 */
	public static class SolutionUsingDfs {
		private Map<Character, Set<Character>> adj;
		private Map<Character, Boolean> visited;
		private List<Character> result;

		public String foreignDictionary(String[] words) {
			adj = new HashMap<>();
			for (String word : words) {
				for (char c : word.toCharArray()) {
					adj.putIfAbsent(c, new HashSet<>());
				}
			}

			for (int i = 0; i < words.length - 1; i++) {
				String w1 = words[i], w2 = words[i + 1];
				int minLen = Math.min(w1.length(), w2.length());
				if (w1.length() > w2.length() &&
						w1.substring(0, minLen).equals(w2.substring(0, minLen))) {
					return "";
				}
				for (int j = 0; j < minLen; j++) {
					if (w1.charAt(j) != w2.charAt(j)) {
						adj.get(w1.charAt(j)).add(w2.charAt(j));
						break;
					}
				}
			}

			visited = new HashMap<>();
			result = new ArrayList<>();
			for (char c : adj.keySet()) {
				if (dfs(c)) {
					return "";
				}
			}

			Collections.reverse(result);
			StringBuilder sb = new StringBuilder();
			for (char c : result) {
				sb.append(c);
			}
			return sb.toString();
		}

		private boolean dfs(char ch) {
			if (visited.containsKey(ch)) {
				return visited.get(ch);
			}

			visited.put(ch, true);
			for (char next : adj.get(ch)) {
				if (dfs(next)) {
					return true;
				}
			}
			visited.put(ch, false);
			result.add(ch);
			return false;
		}
	}

	public static class SolutionUsingTopologicalSortKahnsAlgorithm {
		public String foreignDictionary(String[] words) {
			Map<Character, Set<Character>> adj = new HashMap<>();
			Map<Character, Integer> indegree = new HashMap<>();

			for (String word : words) {
				for (char c : word.toCharArray()) {
					adj.putIfAbsent(c, new HashSet<>());
					indegree.putIfAbsent(c, 0);
				}
			}

			for (int i = 0; i < words.length - 1; i++) {
				String w1 = words[i];
				String w2 = words[i + 1];
				int minLen = Math.min(w1.length(), w2.length());
				if (w1.length() > w2.length() &&
						w1.substring(0, minLen).equals(w2.substring(0, minLen))) {
					return "";
				}
				for (int j = 0; j < minLen; j++) {
					if (w1.charAt(j) != w2.charAt(j)) {
						if (!adj.get(w1.charAt(j)).contains(w2.charAt(j))) {
							adj.get(w1.charAt(j)).add(w2.charAt(j));
							indegree.put(w2.charAt(j),
									indegree.get(w2.charAt(j)) + 1);
						}
						break;
					}
				}
			}

			Queue<Character> q = new LinkedList<>();
			for (char c : indegree.keySet()) {
				if (indegree.get(c) == 0) {
					q.offer(c);
				}
			}

			StringBuilder res = new StringBuilder();
			while (!q.isEmpty()) {
				char char_ = q.poll();
				res.append(char_);
				for (char neighbor : adj.get(char_)) {
					indegree.put(neighbor, indegree.get(neighbor) - 1);
					if (indegree.get(neighbor) == 0) {
						q.offer(neighbor);
					}
				}
			}

			if (res.length() != indegree.size()) {
				return "";
			}

			return res.toString();
		}
	}
}
