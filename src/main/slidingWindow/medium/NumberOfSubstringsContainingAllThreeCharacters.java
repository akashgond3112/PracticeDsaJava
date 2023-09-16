package main.slidingWindow.medium;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author agond
 * @package main.slidingWindow.medium
 * @Date 15/09/2023
 * @Project PracticeDSA
 */

/*
    1358. Number of Substrings Containing All Three Characters
    Medium
    Topics
    Companies
    Hint
    Given a string s consisting only of characters a, b and c.

    Return the number of substrings containing at least one occurrence of all these characters a, b and c.

    Example 1:

    Input: s = "abcabc"
    Output: 10
    Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
    Example 2:

    Input: s = "aaacb"
    Output: 3
    Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".
    Example 3:

    Input: s = "abc"
    Output: 1


    Constraints:

    3 <= s.length <= 5 x 10^4
    s only consists of a, b or c characters
*/
public class NumberOfSubstringsContainingAllThreeCharacters {
    public static int numberOfSubstrings(String s) {
        int startIndex = 0;
        int endIndex = 0;
        int max = 0;
        Map<Character, Integer> characterHashMap = new HashMap<>();

        while (endIndex < s.length()) {
            char ch = s.charAt(endIndex);
            characterHashMap.put(ch, characterHashMap.getOrDefault(ch, 0) + 1);

            while (characterHashMap.size() == 3) {
                max += s.length() - endIndex;
                ch = s.charAt(startIndex);
                characterHashMap.put(ch, characterHashMap.getOrDefault(ch, 0) - 1);
                if (characterHashMap.get(ch) == 0) {
                    characterHashMap.remove(ch);
                }
                startIndex++;
            }
            endIndex++;
        }
        return max;
    }

    public static void main(String[] args) {
        String txt1 = "abcabc";
        int result = numberOfSubstrings(txt1);
        Assert.assertEquals(10, result);

        String txt2 = "aaacb";
        result = numberOfSubstrings(txt2);
        Assert.assertEquals(3, result);


        String txt3 = "abc";
        result = numberOfSubstrings(txt3);
        Assert.assertEquals(1, result);


    }
}
