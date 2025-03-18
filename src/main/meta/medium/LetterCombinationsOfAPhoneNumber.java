package main.meta.medium;

import java.util.*;

/**
 *
 *
 * <pre>
 * 17. Letter Combinations of a Phone Number
 * Medium
 * Topics
 * Companies
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 *
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 * Example 1:
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 * Input: digits = ""
 * Output: []
 * Example 3:
 * Input: digits = "2"
 * Output: ["a","b","c"]
 *
 * Constraints:
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 * Topics
 * Hash Table
 * String
 * Backtracking
 * </pre>
 */
public class LetterCombinationsOfAPhoneNumber {

  /**
   * Time Complexity: O(4^n * n) - 4^n represents the maximum number of combinations (4 is the
   * maximum number of letters per digit, e.g., for 7 and 9) - Additional n factor for string
   * building operations in each recursive call (copying characters to create the result)
   *
   * <p>Space Complexity: O(n) - Recursion stack can go n levels deep (where n is the number of
   * digits) - StringBuilder takes O(n) space to store the current combination - The result list is
   * not counted in the auxiliary space complexity
   */
  static class Solution {
    public List<String> letterCombinations(String digits) {
      List<String> result = new ArrayList<>();

      // Handle empty input
      if (digits == null || digits.isEmpty()) {
        return result;
      }

      Map<Character, String> map = new HashMap<>();
      map.put('0', "");
      map.put('1', "");
      map.put('2', "abc");
      map.put('3', "def");
      map.put('4', "ghi");
      map.put('5', "jkl");
      map.put('6', "mno");
      map.put('7', "pqrs");
      map.put('8', "tuv");
      map.put('9', "wxyz");

      StringBuilder sb = new StringBuilder();
      dfs(digits, 0, sb, map, result);

      return result;
    }

    private static void dfs(
        String digits,
        int index,
        StringBuilder sb,
        Map<Character, String> map,
        List<String> result) {
      // Base case: when we've processed all digits
      if (index == digits.length()) {
        result.add(sb.toString());
        return;
      }

      // Get the letters corresponding to the current digit
      String letters = map.get(digits.charAt(index));

      // Try each letter for the current digit
      for (int i = 0; i < letters.length(); i++) {
        // Add current letter to combination
        sb.append(letters.charAt(i));

        // Recurse to next digit
        dfs(digits, index + 1, sb, map, result);

        // Backtrack - remove the last character
        sb.deleteCharAt(sb.length() - 1);
      }
    }
  }
}
