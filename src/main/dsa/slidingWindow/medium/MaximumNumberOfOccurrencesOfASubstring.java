package main.dsa.slidingWindow.medium;

import org.junit.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author agond
 * @package main.dsa.slidingWindow.medium
 * @Date 28/09/2023
 * @Project PracticeDSA
 * 1297. Maximum Number of Occurrences of a Substring
 * Medium
 * Topics
 * Companies
 * Hint
 * Given a string s, return the maximum number of occurrences of any substring under the following rules:
 * <p>
 * The number of unique characters in the substring must be less than or equal to maxLetters.
 * The substring size must be between minSize and maxSize inclusive.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * Output: 2
 * Explanation: Substring "aab" has 2 occurrences in the original string.
 * It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
 * Example 2:
 * <p>
 * Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 * Output: 2
 * Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105
 * 1 <= maxLetters <= 26
 * 1 <= minSize <= maxSize <= min(26, s.length)
 * s consists of only lowercase English letters.
 */
public class MaximumNumberOfOccurrencesOfASubstring {

    public static int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int startIndex = 0;
        int endIndex = 0;
        int ans = 0;

        Map<Character, Integer> characterHashMap = new HashMap<>();
        Map<String, Integer> stringIntegerHashMap = new HashMap<>();

        while (endIndex < s.length()) {

            char ch = s.charAt(endIndex);
            characterHashMap.put(ch, characterHashMap.getOrDefault(ch, 0) + 1);

            while (endIndex - startIndex + 1 > (maxSize + minSize) / 2) {
                ch = s.charAt(startIndex);
                characterHashMap.put(ch, characterHashMap.getOrDefault(ch, 0) - 1);
                if (characterHashMap.get(ch) == 0) {
                    characterHashMap.remove(ch);
                }
                startIndex++;
            }

            if (characterHashMap.size() <= maxLetters && endIndex - startIndex + 1 >= (maxSize + minSize) / 2)
                stringIntegerHashMap.put(s.substring(startIndex, endIndex + 1), stringIntegerHashMap.getOrDefault(s.substring(startIndex, endIndex + 1), 0) + 1);

            endIndex++;
        }

        for (int it : stringIntegerHashMap.values())
            ans = Math.max(ans, it);

        return ans;
    }

    public static int maxFreqOptimizeSolution(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();
        Map<String, Integer> substringFrequency = new HashMap<>();
        int maxFreq = 0;

        for (int i = 0; i <= n - minSize; i++) {
            String substring = s.substring(i, i + minSize);
            Set<Character> uniqueChars = new HashSet<>();
            int distinctChars = 0;

            for (char c : substring.toCharArray()) {
                if (!uniqueChars.contains(c)) {
                    distinctChars++;
                    uniqueChars.add(c);
                }
            }

            if (distinctChars <= maxLetters) {
                int frequency = substringFrequency.getOrDefault(substring, 0) + 1;
                substringFrequency.put(substring, frequency);
                maxFreq = Math.max(maxFreq, frequency);
            }
        }

        return maxFreq;
    }


    public static void main(String[] args) {
        String string = "";
        int result, maxLetters, minSize, maxSize = 0;

        string = "aababcaab";
        maxLetters = 2;
        minSize = 3;
        maxSize = 4;
        result = maxFreq(string, maxLetters, minSize, maxSize);
        Assert.assertEquals("Test 1", 2, result);

        /*string = "aaaa";
        maxLetters = 1;
        maxSize = 3;
        result = maxFreq(string, maxLetters, minSize, maxSize);
        Assert.assertEquals("Test 2", 2, result);*/

    }
}
