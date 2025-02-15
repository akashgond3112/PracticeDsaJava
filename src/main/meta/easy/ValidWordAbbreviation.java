package main.meta.easy;

/**
 * 408. Valid Word Abbreviation Description A string can be abbreviated by replacing any number of non-adjacent,
 * non-empty substrings with their lengths. The lengths should not have leading zeros.
 *
 * For example, a string such as "substitution" could be abbreviated as (but not limited to):
 *
 * "s10n" ("s ubstitutio n") "sub4u4" ("sub stit u tion") "12" ("substitution") "su3i1u2on" ("su bst i t u ti on")
 * "substitution" (no substrings replaced) The following are not valid abbreviations:
 *
 * "s55n" ("s ubsti tutio n", the replaced substrings are adjacent) "s010n" (has leading zeros) "s0ubstitution"
 * (replaces an empty substring) Given a string word and an abbreviation abbr, return whether the string matches the
 * given abbreviation.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "internationalization", abbr = "i12iz4n" Output: true Explanation: The word "internationalization" can
 * be abbreviated as "i12iz4n" ("i nternational iz atio n"). Example 2:
 *
 * Input: word = "apple", abbr = "a2e" Output: false Explanation: The word "apple" cannot be abbreviated as "a2e".
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 20 word consists of only lowercase English letters. 1 <= abbr.length <= 10 abbr consists of
 * lowercase English letters and digits. All the integers in abbr will fit in a 32-bit integer.
 */
public class ValidWordAbbreviation {
	public static class Solution {

		/**
		 * Validates whether a given abbreviation correctly represents a word.
		 *
		 * <p>The abbreviation may contain numerical values that represent a number of characters
		 * to skip in the word. The abbreviation is considered valid if following these rules results in matching the
		 * entire word.
		 *
		 * <p>Rules:
		 * - A numeric value in `abbr` represents how many characters to skip in `word`.
		 * - Leading zeros in numbers are not allowed.
		 * - If `abbr` contains non-numeric characters, they must match the corresponding character in `word`.
		 *
		 * @param word
		 * 		The original word to validate against.
		 * @param abbr
		 * 		The abbreviation to check.
		 * @return {@code true} if `abbr` correctly represents `word`, otherwise {@code false}.
		 *
		 * 		<p>Time Complexity: O(N), where N is the length of `abbr` (since we traverse it once).
		 * 		<p>Space Complexity: O(1), since we only use a few integer variables.
		 */
		public boolean isValid(String word, String abbr) {
			int wordPointer = 0, abbrPointer = 0;

			while (wordPointer < word.length() && abbrPointer < abbr.length()) {
				char currentAbbrChar = abbr.charAt(abbrPointer);

				if (Character.isDigit(currentAbbrChar)) {
					int steps = 0;
					if (currentAbbrChar == '0') {
						return false; // Leading zeros are not allowed
					}

					while (abbrPointer < abbr.length() && Character.isDigit(abbr.charAt(abbrPointer))) {
						int currentNumber = abbr.charAt(abbrPointer) - '0';
						steps = steps * 10 + currentNumber;
						abbrPointer++;
					}

					wordPointer += steps;
				} else {
					if (word.charAt(wordPointer) != currentAbbrChar) {
						return false;
					}
					wordPointer++;
					abbrPointer++;
				}
			}

			return wordPointer == word.length() && abbrPointer == abbr.length();
		}

	}
}
