package main.meta.medium;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author agond
 * @package main.dsa.slidingWindow.medium
 * @Date 17/10/2023
 * @Project PracticeDSA
 *
 * 438. Find All Anagrams in a String
 * Medium
 * Topics
 * Companies
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 * Example 1:
 *
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 * Constraints:
 *
 * 1 <= s.length, p.length <= 3 * 104
 * s and p consist of lowercase English letters.
 */
public class FindAllAnagramsInAString {

    public static List<Integer> findAnagrams(String str, String pattern) {
        List<Integer> ans = new ArrayList<>();

        // Early return for empty inputs
        if (str == null || str.isEmpty() || pattern == null || pattern.isEmpty())
            return ans;

        // Input validation
        if (pattern.length() > str.length())
            return ans;

        // Create frequency map for pattern
        Map<Character, Integer> patternFrequency = new HashMap<>();
        for (char c : pattern.toCharArray()) {
            patternFrequency.put(c, patternFrequency.getOrDefault(c, 0) + 1);
        }

        // Sliding window approach
        int matched = 0;
        int windowStart = 0;

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            // Expand window and process current character
            char rightChar = str.charAt(windowEnd);
            if (patternFrequency.containsKey(rightChar)) {
                patternFrequency.put(rightChar, patternFrequency.get(rightChar) - 1);
                if (patternFrequency.get(rightChar) == 0) {
                    matched++;
                }
            }

            // Check if we have a valid anagram window
            if (matched == patternFrequency.size()) {
                ans.add(windowStart);
            }

            // Shrink window if it reaches pattern length
            if (windowEnd >= pattern.length() - 1) {
                char leftChar = str.charAt(windowStart);
                if (patternFrequency.containsKey(leftChar)) {
                    // Restore frequency if the count becomes positive
                    if (patternFrequency.get(leftChar) == 0) {
                        matched--;
                    }
                    patternFrequency.put(leftChar, patternFrequency.get(leftChar) + 1);
                }
                windowStart++;
            }
        }

        return ans;
    }
}
