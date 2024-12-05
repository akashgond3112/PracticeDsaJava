package main.revision.recursion;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

	public static List<List<String>> partition(String s) {
		return solvePalindromePartitioning(0, s, new ArrayList<>(), new ArrayList<>());
	}

	private static List<List<String>> solvePalindromePartitioning(int i, String s, List<String> path, List<List<String>> result) {
		if (i == s.length()) {
			result.add(new ArrayList<>(path));
			return result;
		}

		for (int j = i; j < s.length(); j++) {
			if (isPalindrome(s, i, j)) {
				path.add(s.substring(i, j + 1));
				solvePalindromePartitioning(j + 1, s, path, result);
				path.removeLast();
			}
		}

		return result;
	}


	private static boolean isPalindrome(String s, int i, int j) {
		while (i < j) {
			if (s.charAt(i++) != s.charAt(j--)) {
				return false;
			}
		}
		return true;
	}
}
