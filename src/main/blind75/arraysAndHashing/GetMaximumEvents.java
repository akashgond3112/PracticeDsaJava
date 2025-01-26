package main.blind75.arraysAndHashing;

import java.util.HashSet;
import java.util.Set;

public class GetMaximumEvents {

	public static class Solution {
		public static int getMaximumEvents(int[] payload) {
			Set<Integer> distinct = new HashSet<>();
			for (int p : payload) {
				distinct.add(p);
			}
			return distinct.size();
		}
	}
}
