package main.two_pointers.easy;

/*
28. Find the Index of the First Occurrence in a String
Easy
Topics
Companies
Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.



Example 1:

Input: haystack = "sadbutsad", needle = "sad"
Output: 0
Explanation: "sad" occurs at index 0 and 6.
The first occurrence is at index 0, so we return 0.
Example 2:

Input: haystack = "leetcode", needle = "leeto"
Output: -1
Explanation: "leeto" did not occur in "leetcode", so we return -1.


Constraints:

1 <= haystack.length, needle.length <= 104
haystack and needle consist of only lowercase English characters.
*/
public class FindtheIndexOfTheFirstOccurrenceInAString {

	public int strStr(String haystack, String needle) {

		// Edge case: if needle is empty, return 0
		if (needle.length() == 0) {
			return 0;
		}

		// Iterate through the haystack up to the point where the remaining
		// substring could still match the needle
		for (int i = 0; i <= haystack.length() - needle.length(); i++) {
			// Get the substring from the current index of length equal to needle
			String substring = haystack.substring(i, i + needle.length());

			// Check if the substring matches the needle
			if (substring.equals(needle)) {
				return i; // Return the current index if it matches
			}
		}

		// If no match is found, return -1
		return -1;

	}
}
