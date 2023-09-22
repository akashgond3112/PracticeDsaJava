package main.slidingWindow.medium;

import org.junit.Assert;

/**
 * @author agond
 * @package main.slidingWindow.medium
 * @Date 22/09/2023
 * @Project PracticeDSA
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

        /*string = "aeiou";
        k = 2;
        result = maxVowels(string, k);
        Assert.assertEquals(2, result);


        string = "leetcode";
        k = 3;
        result = maxVowels(string, k);
        Assert.assertEquals(2, result);*/


    }

}
