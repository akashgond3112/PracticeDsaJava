package main.meta.hard;

/**
 *
 *
 * <pre>
 * 10. Regular Expression Matching
 * Hard
 * Topics
 * Companies
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 *
 * '.' Matches any single character.​​​​
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 * Example 1:
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 *
 * Example 2:
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 *
 * Example 3:
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 *
 *
 * Constraints:
 * 1 <= s.length <= 20
 * 1 <= p.length <= 20
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 * Topics
 * String
 * Dynamic Programming
 * Recursion
 * </pre>
 */
/**
 * Solution for Regular Expression Pattern Matching with Memoization
 *
 * <p>Pattern supports: '.' - Matches any single character '*' - Matches zero or more of the
 * preceding element
 *
 * <p>Time Complexity: O(m*n), where m is the length of string s and n is the length of pattern p
 * With memoization, each state (i,j) is computed at most once.
 *
 * <p>Space Complexity: O(m*n) for storing the memoization table
 */
class RegularExpressionMatching {

  /** Original recursive solution without memoization */
  static class Solution {
    /**
     * Determines if the input string s matches the given pattern p
     *
     * @param s the input string
     * @param p the pattern to match against
     * @return true if the string matches the pattern, false otherwise
     *     <p>Time Complexity: O(m*n) where m = s.length() and n = p.length() Space Complexity:
     *     O(m*n) due to recursion stack depth
     */
    public boolean isMatch(String s, String p) {
      return dfs(0, 0, s, p);
    }

    /**
     * Recursive depth-first search to check if substring s[i...] matches pattern p[j...]
     *
     * @param i current index in string s
     * @param j current index in pattern p
     * @param s input string
     * @param p pattern string
     * @return true if s[i...] matches p[j...], false otherwise
     */
    private boolean dfs(int i, int j, String s, String p) {
      // Base case: if we've reached the end of both strings
      if (j >= p.length()) {
        return i >= s.length(); // True if both strings are exhausted
      }

      // If we've reached the end of s but not p, pattern might still match if remaining
      // characters in p can all match empty strings (like a*)
      if (i >= s.length()) {
        // Pattern char followed by * can match empty string
        return j + 1 < p.length() && p.charAt(j + 1) == '*' && dfs(i, j + 2, s, p);
      }

      // Check if current characters match
      boolean isMatch = s.charAt(i) == p.charAt(j) || p.charAt(j) == '.';

      // If next character is *, we have two choices:
      // 1. Skip the current pattern character and its following *
      // 2. Use current pattern character to match current string character and try again at same
      // pattern
      if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
        return dfs(i, j + 2, s, p) || (isMatch && dfs(i + 1, j, s, p));
      }

      // If current characters match, continue matching next characters
      if (isMatch) {
        return dfs(i + 1, j + 1, s, p);
      }

      // If we get here, characters don't match and there's no special handling
      return false;
    }
  }

  /** Optimized solution using memoization to avoid redundant calculations */
  static class SolutionUsingMemoization {
    /**
     * Determines if the input string s matches the given pattern p using memoization
     *
     * @param s the input string
     * @param p the pattern to match against
     * @return true if the string matches the pattern, false otherwise
     *     <p>Time Complexity: O(m*n) where m = s.length() and n = p.length() Space Complexity:
     *     O(m*n) for memoization table
     */
    public boolean isMatch(String s, String p) {
      // Create memoization table initialized with null values
      // null = not computed, true/false = result
      Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
      return dfs(0, 0, s, p, memo);
    }

    /**
     * Recursive depth-first search with memoization to check if substring s[i...] matches pattern
     * p[j...]
     *
     * @param i current index in string s
     * @param j current index in pattern p
     * @param s input string
     * @param p pattern string
     * @param memo memoization table to store already computed results
     * @return true if s[i...] matches p[j...], false otherwise
     */
    private boolean dfs(int i, int j, String s, String p, Boolean[][] memo) {
      // Check if this state has already been computed
      if (i <= s.length() && j <= p.length() && memo[i][j] != null) {
        return memo[i][j];
      }

      // Base case: if we've reached the end of both strings
      if (j >= p.length()) {
        return i >= s.length(); // True if both strings are exhausted
      }

      // If we've reached the end of s but not p, pattern might still match if remaining
      // characters in p can all match empty strings (like a*)
      if (i >= s.length()) {
        // Pattern char followed by * can match empty string
        boolean result = j + 1 < p.length() && p.charAt(j + 1) == '*' && dfs(i, j + 2, s, p, memo);
        if (i <= s.length() && j <= p.length()) {
          memo[i][j] = result;
        }
        return result;
      }

      // Check if current characters match
      boolean isMatch = s.charAt(i) == p.charAt(j) || p.charAt(j) == '.';

      boolean result;
      // If next character is *, we have two choices
      if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
        result = dfs(i, j + 2, s, p, memo) || (isMatch && dfs(i + 1, j, s, p, memo));
      }
      // If current characters match, continue matching next characters
      else if (isMatch) {
        result = dfs(i + 1, j + 1, s, p, memo);
      }
      // If we get here, characters don't match and there's no special handling
      else {
        result = false;
      }

      // Store result in memoization table
      if (i <= s.length() && j <= p.length()) {
        memo[i][j] = result;
      }
      return result;
    }
  }

  /**
   * Main method with test cases for the regex matcher
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    // Test original solution
    System.out.println("Testing original solution:");
    Solution solution = new Solution();
    testSolution(solution);

    // Test memoized solution
    System.out.println("\nTesting memoized solution:");
    SolutionUsingMemoization memoSolution = new SolutionUsingMemoization();
    testSolution(memoSolution);

    // Test with large input to demonstrate memoization efficiency
    System.out.println("\nTesting with large input (performance comparison):");
    testPerformance();
  }

  /**
   * Helper method to test a solution implementation with standard test cases
   *
   * @param solution the solution instance to test
   */
  private static void testSolution(Object solution) {
    if (solution instanceof Solution) {
      Solution s = (Solution) solution;
      System.out.println("aa matches a: " + s.isMatch("aa", "a")); // Should print false
      System.out.println("aa matches a*: " + s.isMatch("aa", "a*")); // Should print true
      System.out.println("ab matches .*: " + s.isMatch("ab", ".*")); // Should print true
      System.out.println("aab matches c*a*b: " + s.isMatch("aab", "c*a*b")); // Should print true
    } else if (solution instanceof SolutionUsingMemoization) {
      SolutionUsingMemoization s = (SolutionUsingMemoization) solution;
      System.out.println("aa matches a: " + s.isMatch("aa", "a")); // Should print false
      System.out.println("aa matches a*: " + s.isMatch("aa", "a*")); // Should print true
      System.out.println("ab matches .*: " + s.isMatch("ab", ".*")); // Should print true
      System.out.println("aab matches c*a*b: " + s.isMatch("aab", "c*a*b")); // Should print true
    }
  }

  /** Helper method to compare performance of original and memoized solutions */
  private static void testPerformance() {
    String s = "aaaaaaaaaaaaaaaaaaab";
    String p = "a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*b";

    // Test original solution
    Solution solution = new Solution();
    long startTime = System.nanoTime();
    boolean result1 = solution.isMatch(s, p);
    long endTime = System.nanoTime();
    System.out.println(
        "Original solution: " + result1 + " (Time: " + (endTime - startTime) / 1000000.0 + " ms)");

    // Test memoized solution
    SolutionUsingMemoization memoSolution = new SolutionUsingMemoization();
    startTime = System.nanoTime();
    boolean result2 = memoSolution.isMatch(s, p);
    endTime = System.nanoTime();
    System.out.println(
        "Memoized solution: " + result2 + " (Time: " + (endTime - startTime) / 1000000.0 + " ms)");
  }
}
