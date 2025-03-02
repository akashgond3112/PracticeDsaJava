package main.dsa.nonlinear.graph.topologicalsort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
Word Ladder II
Difficulty: HardAccuracy: 50.0%Submissions: 29K+Points: 8
Given two distinct words startWord and targetWord, and a list denoting wordList of unique words of equal lengths. Find all shortest transformation sequence(s) from startWord to targetWord. You can return them in any order possible.
Keep the following conditions in mind:

A word can only consist of lowercase characters.
Only one letter can be changed in each transformation.
Each transformed word must exist in the wordList including the targetWord.
startWord may or may not be part of the wordList.
Return an empty list if there is no such transformation sequence.
The first part of this problem can be found here.


Example 1:

Input:
startWord = "der", targetWord = "dfs",
wordList = {"des","der","dfr","dgt","dfs"}
Output:
der dfr dfs
der des dfs
Explanation:
The length of the smallest transformation is 3.
And the following are the only two ways to get
to targetWord:-
"der" -> "des" -> "dfs".
"der" -> "dfr" -> "dfs".
Example 2:

Input:
startWord = "gedk", targetWord = "geek",
wordList = {"geek", "gefk"}
Output:
"gedk" -> "geek"

Your Task:
You don't need to read or print anything, Your task is to complete the function findSequences() which takes startWord, targetWord and wordList as input parameter and returns a list of list of strings of the shortest transformation sequence from startWord to targetWord.
Note: You don't have to return -1 in case of no possible sequence. Just return the Empty List.


Expected Time Compelxity: O(N*(logN * M * 26))
Expected Auxiliary Space: O(N * M) where N = length of wordList and M = |wordListi|


Constraints:
1 ≤ N ≤ 100
1 ≤ M ≤ 10
*/
public class WordLadderII {

	static class comp implements Comparator<ArrayList<String>> {

		public int compare(ArrayList<String> a, ArrayList<String> b) {
			StringBuilder x = new StringBuilder();
			StringBuilder y = new StringBuilder();
			for (String s : a)
				x.append(s);
			for (String s : b)
				y.append(s);
			return x.toString().compareTo(y.toString());
		}
	}

	public List<ArrayList<String>> findSequences(String startWord, String targetWord, String[] wordList) {
		Set<String> set = new HashSet<>();
		Collections.addAll(set, wordList);

		Queue<ArrayList<String>> queue = new LinkedList<>();
		ArrayList<String> list = new ArrayList<>();
		list.add(startWord);
		queue.add(list);

		ArrayList<String> usedOnLevel = new ArrayList<>();
		usedOnLevel.add(startWord);
		int level = 0;
		ArrayList<ArrayList<String>> result = new ArrayList<>();

		while (!queue.isEmpty()) {
			ArrayList<String> current = queue.peek();
			queue.remove();

			// erase all words that had been used in the previous levels to transform
			if (current.size() > level) {
				level++;
				for (String word : usedOnLevel) {
					set.remove(word);
				}
			}
			// Get the last word from the list of the queue.
			String word = current.get(current.size() - 1);
			if (word.equals(targetWord)) {
				if (result.isEmpty())
					result.add(current);
				else if (result.get(0).size() == current.size()) {
					result.add(current);
				}
			}

			for (int i = 0; i < word.length(); i++) {

				for (char c = 'a'; c <= 'z'; c++) {
					char[] replacementChar = word.toCharArray();
					replacementChar[i] = c;
					String newWord = new String(replacementChar);
					if (set.contains(newWord)) {
						current.add(newWord);

						ArrayList<String> temp = new ArrayList<>(current);
						queue.add(temp);

						usedOnLevel.add(newWord);
						current.remove(current.size() - 1);
					}
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String startWord = "der";
		String targetWord = "dfs";
		String[] wordList = { "des", "der", "dfr", "dgt", "dfs" };

		WordLadderII obj = new WordLadderII();
		List<ArrayList<String>> ans = obj.findSequences(startWord, targetWord, wordList);

		// If no transformation sequence is possible.
		if (ans.isEmpty())
			System.out.println(-1);
		else {

			ans.sort(new comp());
			for (ArrayList<String> an : ans) {
				for (String s : an) {
					System.out.print(s + " ");
				}
				System.out.println();
			}
		}
	}
}
