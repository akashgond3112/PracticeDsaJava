package main.dsa.slidingWindow.medium;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author agond
 * @package main.dsa.slidingWindow.medium
 * @Date 26/09/2023
 * @Project PracticeDSA
 * <p>
 * 424. Longest Repeating Character Replacement
 * Medium
 * Topics
 * Companies
 * You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
 * <p>
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * Example 2:
 * <p>
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * There may exists other ways to achive this answer too.
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105
 * s consists of only uppercase English letters.
 * 0 <= k <= s.length
 */
public class LongestRepeatingCharacterReplacement {

    public static int characterReplacement(String s, int k) {

        int maxRepeatChar = 0;
        int startIndex = 0;
        int endIndex = 0;
        int max = 0;

        Map<Character, Integer> characterHashMap = new HashMap<>();

        while (endIndex < s.length()) {
            char ch = s.charAt(endIndex);
            characterHashMap.put(ch, characterHashMap.getOrDefault(ch, 0) + 1);

            maxRepeatChar = Math.max(maxRepeatChar, characterHashMap.get(ch));

            while (endIndex - startIndex + 1 - maxRepeatChar > k) {
                ch = s.charAt(startIndex);
                characterHashMap.put(ch, characterHashMap.getOrDefault(ch, 0) - 1);
                startIndex++;
            }
            max = Math.max(max, endIndex - startIndex + 1);
            endIndex++;
        }

        return max;
    }

    public static void main(String[] args) {
        String string = "";
        int k = 0;
        int result = 0;

        string = "ABAB";
        k = 2;
        result = characterReplacement(string, k);
        Assert.assertEquals(4, result);

        string = "AABABBA";
        k = 1;
        result = characterReplacement(string, k);
        Assert.assertEquals(4, result);


    }

}
