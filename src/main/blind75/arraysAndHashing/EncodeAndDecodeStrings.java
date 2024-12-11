package main.blind75.arraysAndHashing;

import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecodeStrings {

	/**
	 * Time complexity: O(m) for encode() and decode().
	 * Space complexity: O(n) for encode() and decode().
	 * Where m is the sum of lengths of all the strings and n is the number of strings.
	 */
	public class SolutionOne {

		public String encode(List<String> strs) {
			if (strs.isEmpty())
				return "";
			StringBuilder res = new StringBuilder();
			List<Integer> sizes = new ArrayList<>();
			for (String str : strs) {
				sizes.add(str.length());
			}
			for (int size : sizes) {
				res.append(size).append(',');
			}
			res.append('#');
			for (String str : strs) {
				res.append(str);
			}
			return res.toString();
		}

		public List<String> decode(String str) {
			if (str.isEmpty()) {
				return new ArrayList<>();
			}
			List<String> res = new ArrayList<>();
			List<Integer> sizes = new ArrayList<>();
			int i = 0;
			while (str.charAt(i) != '#') {
				StringBuilder cur = new StringBuilder();
				while (str.charAt(i) != ',') {
					cur.append(str.charAt(i));
					i++;
				}
				sizes.add(Integer.parseInt(cur.toString()));
				i++;
			}
			i++;
			for (int sz : sizes) {
				res.add(str.substring(i, i + sz));
				i += sz;
			}
			return res;
		}
	}

	/**
	 * Time complexity: O(m) for encode() and decode().
	 * Space complexity: O(1) for encode() and decode().
	 * Where m is the sum of lengths of all the strings and n is the number of strings.
	 */
	public class SolutionTwo {

		public String encode(List<String> strs) {
			StringBuilder res = new StringBuilder();
			for (String s : strs) {
				res.append(s.length()).append('#').append(s);
			}
			return res.toString();
		}

		public List<String> decode(String str) {
			List<String> res = new ArrayList<>();
			int i = 0;
			while (i < str.length()) {
				int j = i;
				while (str.charAt(j) != '#') {
					j++;
				}
				int length = Integer.parseInt(str.substring(i, j));
				i = j + 1;
				j = i + length;
				res.add(str.substring(i, j));
				i = j;
			}
			return res;
		}
	}

}
