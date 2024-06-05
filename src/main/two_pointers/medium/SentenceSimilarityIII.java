package main.two_pointers.medium;

/*
1813. Sentence Similarity III
Solved
Medium
Topics
Companies
Hint
A sentence is a list of words that are separated by a single space with no leading or trailing spaces. For example, "Hello World", "HELLO", "hello world hello world" are all sentences. Words consist of only uppercase and lowercase English letters.

Two sentences sentence1 and sentence2 are similar if it is possible to insert an arbitrary sentence (possibly empty) inside one of these sentences such that the two sentences become equal. For example, sentence1 = "Hello my name is Jane" and sentence2 = "Hello Jane" can be made equal by inserting "my name is" between "Hello" and "Jane" in sentence2.

Given two sentences sentence1 and sentence2, return true if sentence1 and sentence2 are similar. Otherwise, return false.

Example 1:

Input: sentence1 = "My name is Haley", sentence2 = "My Haley"
Output: true
Explanation: sentence2 can be turned to sentence1 by inserting "name is" between "My" and "Haley".
Example 2:

Input: sentence1 = "of", sentence2 = "A lot of words"
Output: false
Explanation: No single sentence can be inserted inside one of the sentences to make it equal to the other.
Example 3:

Input: sentence1 = "Eating right now", sentence2 = "Eating"
Output: true
Explanation: sentence2 can be turned to sentence1 by inserting "right now" at the end of the sentence.

Constraints:

1 <= sentence1.length, sentence2.length <= 100
sentence1 and sentence2 consist of lowercase and uppercase English letters and spaces.
The words in sentence1 and sentence2 are separated by a single space.
*/
public class SentenceSimilarityIII {

	public boolean areSentencesSimilar(String sentence1, String sentence2) {

		if(sentence1.isEmpty() || sentence2.isEmpty()) return true;

		String[] s1 = sentence1.split(" ");
		String[] s2 = sentence2.split(" ");

		if (s1.length == 1 && (s1[0].equals(s2[0]) || s1[0].equals(s2[s2.length - 1]))) return true;
		if (s2.length == 1 && (s2[0].equals(s1[0]) || s2[0].equals(s1[s1.length - 1]))) return true;

		// Find the longest common prefix
		int prefixLen = 0;
		while (prefixLen < Math.min(s1.length, s2.length) && s1[prefixLen].equals(s2[prefixLen])) {
			prefixLen++;
		}

		// Find the longest common suffix
		int suffixLen = 0;
		while (suffixLen < Math.min(s1.length, s2.length) && s1[s1.length - 1 - suffixLen].equals(s2[s2.length - 1 - suffixLen])) {
			suffixLen++;
		}

		// Check if the middle segment can make sentences similar
		return prefixLen + suffixLen >= Math.min(s1.length, s2.length);

	}
}
