package main.dsa.nonlinear.graph.learning.topologicalsort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
126. Word Ladder II
Hard
Topics
Companies
A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation: There are 2 shortest transformation sequences:
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"


Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: []
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.

Constraints:

1 <= beginWord.length <= 5
endWord.length == beginWord.length
1 <= wordList.length <= 500
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
The sum of all shortest transformation sequences does not exceed 105.
*/

public class WordLadderIII {

	String b;
	Map<String, Integer> map = new HashMap<>();
	List<ArrayList<String>> ans = new ArrayList<>();

	public List<ArrayList<String>> findSequences(String startWord, String targetWord, String[] wordList) {
		Set<String> wordSet = new HashSet<>();
		Collections.addAll(wordSet, wordList);

		Queue<String> queue = new LinkedList<>();
		b = startWord;
		queue.add(startWord);
		map.put(startWord, 1);
		int size = startWord.length();
		wordSet.remove(startWord);

		while (!queue.isEmpty()) {
			String word = queue.peek();
			int steps = map.get(word);
			queue.remove();
			if (word.equals(targetWord))
				break;

			for (int i = 0; i < size; i++) {

				for (char c = 'a'; c <= 'z'; c++) {
					char[] replaceChars = word.toCharArray();
					replaceChars[i] = c;
					String newWord = new String(replaceChars);
					if (wordSet.contains(newWord)) {
						queue.add(newWord);
						wordSet.remove(newWord);
						map.put(newWord, steps + 1);
					}
				}
			}
		}

		if (map.containsKey(targetWord)) {
			List<String> list = new ArrayList<>();
			list.add(targetWord);
			dfsCheck(targetWord, list);
		}

		return ans;
	}

	private void dfsCheck(String targetWord, List<String> list) {

		if (targetWord.equals(b)) {
			ArrayList<String> duplicate = new ArrayList<>(list);
			Collections.reverse(list);
			ans.add(duplicate);
			return;
		}

		int steps = map.get(targetWord);
		int size = targetWord.length();
		for (int i = 0; i < size; i++) {
			for (char c = 'a'; c <= 'z'; c++) {
				char[] replaceChars = targetWord.toCharArray();
				replaceChars[i] = c;
				String newWord = new String(replaceChars);
				if (map.containsKey(newWord) && map.get(newWord) + 1 == steps) {

					list.add(newWord);
					dfsCheck(newWord, list);
					list.remove(list.size() - 1);
				}
			}
		}
	}


	public static void main(String[] args) {
		String startWord = "der";
		String targetWord = "dfs";
		String[] wordList = { "des", "der", "dfr", "dgt", "dfs" };

		WordLadderIII obj = new WordLadderIII();
		List<ArrayList<String>> ans = obj.findSequences(startWord, targetWord, wordList);

		// If no transformation sequence is possible.
		if (ans.isEmpty())
			System.out.println(-1);
		else {

			ans.sort(new WordLadderII.comp());
			for (ArrayList<String> an : ans) {
				for (String s : an) {
					System.out.print(s + " ");
				}
				System.out.println();
			}
		}
	}
}
