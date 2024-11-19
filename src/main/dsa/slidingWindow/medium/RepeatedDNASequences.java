package main.dsa.slidingWindow.medium;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author agond
 * @package main.dsa.slidingWindow.medium
 * @Date 05/12/2023
 * @Project PracticeDSA
 * <p>
 * 187. Repeated DNA Sequences
 * Medium
 * Topics
 * Companies
 * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
 * <p>
 * For example, "ACGAATTCCG" is a DNA sequence.
 * When studying DNA, it is useful to identify repeated sequences within the DNA.
 * <p>
 * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * You may return the answer in any order.
 * <p>
 * Example 1:
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC","CCCCCAAAAA"]
 * <p>
 * Example 2:
 * Input: s = "AAAAAAAAAAAAA"
 * Output: ["AAAAAAAAAA"]
 * <p>
 * Constraints:
 * 1 <= s.length <= 105
 * s[i] is either 'A', 'C', 'G', or 'T'.
 */
public class RepeatedDNASequences {

    public static List<String> findRepeatedDnaSequences(String s) {

        HashSet<String> seenSubStrings = new HashSet<>();
        HashSet<String> repeatedSequences = new HashSet<>();
        int endIndex = 0;

        while (endIndex <= s.length() - 10) {
            String subs = s.substring(endIndex, endIndex + 10);
            if (seenSubStrings.contains(subs)) {
                repeatedSequences.add(subs);
            }
            seenSubStrings.add(subs);
            endIndex++;
        }

        return new ArrayList<>(repeatedSequences);

    }

    public static void main(String[] args) {
        List<String> result;
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("AAAAACCCCC");
        expectedResult.add("CCCCCAAAAA");
        String s;

        s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        result = findRepeatedDnaSequences(s);
        Assert.assertEquals(expectedResult, result);

        expectedResult.clear();
        expectedResult.add("AAAAAAAAAA");

        s = "AAAAAAAAAAAAA";
        result = findRepeatedDnaSequences(s);
        Assert.assertEquals(expectedResult, result);


    }

}
