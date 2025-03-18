package main.meta.medium;

import java.util.*;

/**
 *
 *
 * <pre>
 * 616. Add Bold Tag in String
 * Description
 * You are given a string s and an array of strings words.
 *
 * You should add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in words.
 *
 * If two such substrings overlap, you should wrap them together with only one pair of closed bold-tag.
 * If two substrings wrapped by bold tags are consecutive, you should combine them.
 * Return s after adding the bold tags.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcxyz123", words = ["abc","123"]
 * Output: "<b>abc</b>xyz<b>123</b>"
 * Explanation: The two strings of words are substrings of s as following: "abcxyz123".
 * We add <b> before each substring and </b> after each substring.
 * Example 2:
 *
 * Input: s = "aaabbb", words = ["aa","b"]
 * Output: "<b>aaabbb</b>"
 * Explanation:
 * "aa" appears as a substring two times: "aaabbb" and "aaabbb".
 * "b" appears as a substring three times: "aaabbb", "aaabbb", and "aaabbb".
 * We add <b> before each substring and </b> after each substring: "<b>a<b>a</b>a</b><b>b</b><b>b</b><b>b</b>".
 * Since the first two <b>'s overlap, we merge them: "<b>aaa</b><b>b</b><b>b</b><b>b</b>".
 * Since now the four <b>'s are consecutive, we merge them: "<b>aaabbb</b>".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * 0 <= words.length <= 100
 * 1 <= words[i].length <= 1000
 * s and words[i] consist of English letters and digits.
 * All the values of words are unique.
 * </pre>
 */
public class AddBoldTagInString {
  /**
   *
   *
   * <pre>
   * Trie data structure to efficiently find words in a string.
   * This implementation uses an array of size 128 to support ASCII characters.
   *
   * Time Complexity:
   * - insert: O(L) where L is the length of the word being inserted
   *
   * Space Complexity:
   * - O(N * K) where N is the total number of nodes in the trie and K is
   *   the character set size (128 in this case)
   * </pre>
   */
  class Trie {
    Trie[] children = new Trie[128];
    boolean isEnd;

    /**
     * Inserts a word into the trie.
     *
     * @param word The word to insert
     */
    public void insert(String word) {
      Trie node = this;
      for (char c : word.toCharArray()) {
        if (node.children[c] == null) {
          node.children[c] = new Trie();
        }
        node = node.children[c];
      }
      node.isEnd = true;
    }
  }

  /**
   *
   *
   * <pre>
   * Solution for adding bold tags to substrings that match any word from an array.
   *
   * Algorithm:
   * 1. Build a trie with all words.
   * 2. For each starting position in the input string, find all matching words using the trie
   *    and add their [start, end] positions to a list.
   * 3. Merge overlapping intervals to get the final bold regions.
   * 4. Construct the result string by adding bold tags around the merged regions.
   *
   * Time Complexity: O(N * M + S + W) where:
   * - N is the length of the input string
   * - M is the maximum length of a word in the dictionary
   * - S is the sum of lengths of all words in the dictionary (for building the trie)
   * - W is the number of matching word occurrences found
   *
   * Space Complexity: O(S + W) where:
   * - S is the space required for the trie
   * - W is the space for storing the matching intervals
   * </pre>
   */
  class Solution {
    /**
     * Adds HTML bold tags to substrings of s that match any word from the words array.
     *
     * @param s The input string
     * @param words Array of words to be marked as bold in the input string
     * @return The input string with bold tags added
     */
    public String addBoldTag(String s, String[] words) {
      // Build trie with all words
      Trie trie = new Trie();
      for (String w : words) {
        trie.insert(w);
      }

      // Find all matching words and store their [start, end] positions
      List<int[]> pairs = new ArrayList<>();
      int n = s.length();
      for (int i = 0; i < n; ++i) {
        Trie node = trie;
        for (int j = i; j < n; ++j) {
          int idx = s.charAt(j);
          if (node.children[idx] == null) {
            break;
          }
          node = node.children[idx];
          if (node.isEnd) {
            pairs.add(new int[] {i, j});
          }
        }
      }

      // If no matches found, return the original string
      if (pairs.isEmpty()) {
        return s;
      }

      // Merge overlapping intervals
      List<int[]> mergedIntervals = new ArrayList<>();
      int start = pairs.get(0)[0], end = pairs.get(0)[1];
      for (int j = 1; j < pairs.size(); ++j) {
        int nextStart = pairs.get(j)[0], nextEnd = pairs.get(j)[1];
        if (end + 1 < nextStart) {
          // No overlap, add current interval and start a new one
          mergedIntervals.add(new int[] {start, end});
          start = nextStart;
          end = nextEnd;
        } else {
          // Overlap found, extend current interval if needed
          end = Math.max(end, nextEnd);
        }
      }
      mergedIntervals.add(new int[] {start, end});

      // Build the final string with bold tags
      int i = 0, j = 0;
      StringBuilder result = new StringBuilder();
      while (i < n) {
        if (j == mergedIntervals.size()) {
          // Append remaining part of the string
          result.append(s.substring(i));
          break;
        }

        start = mergedIntervals.get(j)[0];
        end = mergedIntervals.get(j)[1];

        // Append non-bold text before the current interval
        if (i < start) {
          result.append(s.substring(i, start));
        }

        // Append bold text
        ++j;
        result.append("<b>");
        result.append(s.substring(start, end + 1));
        result.append("</b>");

        // Update pointer to after the current interval
        i = end + 1;
      }

      return result.toString();
    }
  }
}
