package main.dsa.nonlinear.graph.learning.topologicalsort;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
Word Ladder I
Difficulty: HardAccuracy: 37.65%Submissions: 43K+Points: 8
Given two distinct words startWord and targetWord, and a list denoting wordList of unique words of equal lengths. Find the length of the shortest transformation sequence from startWord to targetWord.
Keep the following conditions in mind:

A word can only consist of lowercase characters.
Only one letter can be changed in each transformation.
Each transformed word must exist in the wordList including the targetWord.
startWord may or may not be part of the wordList
The second part of this problem can be found here.

Note: If no possible way to transform sequence from startWord to targetWord return 0


Example 1:

Input:
wordList = {"des","der","dfr","dgt","dfs"}
startWord = "der", targetWord= "dfs",
Output:
3
Explanation:
The length of the smallest transformation
sequence from "der" to "dfs" is 3
i,e "der" -> "dfr" -> "dfs".
Example 2:

Input:
wordList = {"geek", "gefk"}
startWord = "gedk", targetWord= "geek",
Output:
2
Explanation:
gedk -> geek
Example 3:

Input:
wordList = {"poon", "plee", "same", "poie","plea","plie","poin"}
startWord = "toon", targetWord= "plea",
Output: 7
Explanation:
toon -> poon -> poin -> poie -> plie -> plee -> plea


Your Task:
You don't need to read or print anything, Your task is to complete the function wordLadderLength() which takes startWord, targetWord and wordList as input parameter and returns the length of the shortest transformation sequence from startWord to targetWord. If not possible return 0.


Expected Time Compelxity: O(N2 * M)
Expected Auxiliary Space: O(N * M) where N = length of wordList and M = |wordListi|


Constraints:
1 ≤ N ≤ 100
1 ≤ M ≤ 10
*/
public class WordLadderI {

	static class Pair {
		String word;
		int dist;

		public Pair(String word, int dist) {
			this.word = word;
			this.dist = dist;
		}
	}

	public int wordLadderLength(String startWord, String targetWord, String[] wordList) {

		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(startWord, 1));
		Set<String> visited = new HashSet<>();
		Collections.addAll(visited, wordList);

		visited.remove(startWord);
		while (!queue.isEmpty()) {
			Pair pair = queue.peek();
			String word = pair.word;
			int dist = pair.dist;
			queue.remove();
			if (word.equals(targetWord)) {
				return dist;
			}

			for (int i = 0; i < word.length(); i++) {
				for (char c = 'a'; c <= 'z'; c++) {
					char[] replace = word.toCharArray();
					replace[i] = c;
					String newWord = new String(replace);
					if (visited.contains(newWord)) {
						visited.remove(newWord);
						queue.add(new Pair(newWord, dist + 1));
					}
				}
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		WordLadderI wordLadderI = new WordLadderI();

		String[] wordList = { "des", "der", "dfr", "dgt", "dfs" };
		String targetWord = "dfs";
		String startWord = "der";
		System.out.println(wordLadderI.wordLadderLength(startWord, targetWord, wordList));

	}
}
