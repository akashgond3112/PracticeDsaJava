package main.dsa.slidingWindow.medium;

import org.junit.Assert;

/**
 * @author agond
 * @package main.dsa.slidingWindow.medium
 * @Date 22/09/2023
 * @Project PracticeDSA
 */

/*
1456. Maximum Number of Vowels in a Substring of Given Length
Hint
Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.

Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.

Example 1:

Input: s = "abciiidef", k = 3
Output: 3
Explanation: The substring "iii" contains 3 vowel letters.
Example 2:

Input: s = "aeiou", k = 2
Output: 2
Explanation: Any substring of length 2 contains 2 vowels.
Example 3:

Input: s = "leetcode", k = 3
Output: 2
Explanation: "lee", "eet" and "ode" contain 2 vowels.

Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
1 <= k <= s.length
*/
public class MaximumNumberOfVowelsInASubstringOfGivenLength {

    public static int maxVowels(String s, int k) {
        int startIndex = 0;
        int endIndex = 0;
        int max = 0;

        String vowels = "aeiou";

        int currentCount = 0; // Keep track of the current count of consecutive vowels within the window

        while (endIndex < s.length()) {
            char currentChar = s.charAt(endIndex);

            if (vowels.contains(String.valueOf(currentChar))) {
                currentCount++; // Increment the count if the current character is a vowel
            }

            endIndex++; // Move the window's end to the right

            if (endIndex - startIndex > k) {
                char startChar = s.charAt(startIndex);

                if (vowels.contains(String.valueOf(startChar))) {
                    currentCount--; // Decrement the count if the character leaving the window was a vowel
                }

                startIndex++; // Move the window's start to the right to maintain the window size
            }

            // Update the maximum count if the current count is greater
                max = Math.max(max, currentCount);
        }

        return max;
    }



    public static void main(String[] args) {
        String string = "";
        int k = 0;

        string = "abciiidef";
        k = 3;
        int result = maxVowels(string, k);
        Assert.assertEquals(3, result);

        string = "aeiou";
        k = 2;
        result = maxVowels(string, k);
        Assert.assertEquals(2, result);


        string = "leetcode";
        k = 3;
        result = maxVowels(string, k);
        Assert.assertEquals(2, result);


    }

}
