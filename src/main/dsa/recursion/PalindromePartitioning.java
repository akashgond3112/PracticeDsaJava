package main.dsa.recursion;

import java.util.ArrayList;
import java.util.List;


public class PalindromePartitioning {


	public static List<List<String>> getPalindromePartitioning(String s) {

		List<List<String>> result = new ArrayList<>();
		List<String> path = new ArrayList<>();

		solvePalindromePartitioning(0, s, path, result);
		return result;
	}

	private static void solvePalindromePartitioning(int i, String s, List<String> path, List<List<String>> result) {

		if (i == s.length()) {
			result.add(new ArrayList<>(path));
			return;
		}

		for (int j = i; j < s.length(); j++) {
			if (isPalindrome(s, i, j)) {
				path.add(s.substring(i, j + 1));
				solvePalindromePartitioning(j + 1, s, path, result);
				path.remove(path.size() - 1);
			}
		}

	}

	private static boolean isPalindrome(String s, int i, int j) {
		while (i < j) {
			if (s.charAt(i++) != s.charAt(j--)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(getPalindromePartitioning("ababab"));
	}
}
