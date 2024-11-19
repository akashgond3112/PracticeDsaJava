package main.dsa.slidingWindow.medium;

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
public class FindAllAnagramsinAString {

    public static List<Integer> findAnagrams(String txt, String pat) {
        int startIndex = 0;
        int endIndex = 0;
        List<Integer> ans = new ArrayList<>();

        if (txt.length() <= 0 || pat.length() <= 0) return ans;

        Map<Character, Integer> characterHashMap = new HashMap<>();

        for (int z = 0; z < pat.length(); z++) {
            characterHashMap.put(pat.charAt(z), characterHashMap.getOrDefault(pat.charAt(z), 0) + 1);
        }
        int cnt = characterHashMap.size();

        while (endIndex < txt.length()) {
            char currentChar = txt.charAt(endIndex);
            int count = characterHashMap.getOrDefault(currentChar, 0) - 1;
            characterHashMap.put(currentChar, count);
            if (count == 0) cnt--;


            if (endIndex - startIndex + 1 < pat.length()) {
                endIndex++;
            } else if (endIndex - startIndex + 1 == pat.length()) {
                if (cnt == 0)
                    ans.add(startIndex);

                /*Reverse the changes*/
                currentChar = txt.charAt(startIndex);
                count = characterHashMap.getOrDefault(currentChar, 0) + 1;
                characterHashMap.put(currentChar, count);
                if (count == 1) cnt++;

                startIndex++;
                endIndex++;

            }


        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "";
        String p = "";
        List<Integer> result;
        List<Integer> expectedResult = new ArrayList<>();

        s = "cbaebabacd";
        p = "bac";
        expectedResult.add(0);
        expectedResult.add(6);
        result = findAnagrams(s, p);
        Assert.assertEquals(expectedResult, result);

        expectedResult.clear();

        s = "abab";
        p = "ab";
        result = findAnagrams(s, p);
        expectedResult.add(0);
        expectedResult.add(1);
        expectedResult.add(2);
        Assert.assertEquals(expectedResult, result);

    }
}
