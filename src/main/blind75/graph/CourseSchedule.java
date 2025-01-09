package main.blind75.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 207. Course Schedule
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 *
 * Constraints:
 *
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.*/
public class CourseSchedule {

	/**
	 * Time complexity: O(V+E) Space complexity: O(V+E) Where V is the number of courses and E is the number of
	 * prerequisites.
	 */
	public static class SolutionUsingDfs {
		// Map each course to its prerequisites
		private final Map<Integer, List<Integer>> preMap = new HashMap<>();
		// Store all courses along the current DFS path
		private final Set<Integer> visiting = new HashSet<>();

		public boolean canFinish(int numCourses, int[][] prerequisites) {
			for (int i = 0; i < numCourses; i++) {
				preMap.put(i, new ArrayList<>());
			}
			for (int[] prereq : prerequisites) {
				preMap.get(prereq[0]).add(prereq[1]);
			}

			for (int c = 0; c < numCourses; c++) {
				if (dfs(c)) {
					return false;
				}
			}
			return true;
		}

		private boolean dfs(int crs) {
			if (visiting.contains(crs)) {
				// Cycle detected
				return true;
			}
			if (preMap.get(crs).isEmpty()) {
				return false;
			}

			visiting.add(crs);
			for (int pre : preMap.get(crs)) {
				if (dfs(pre)) {
					return true;
				}
			}
			visiting.remove(crs);
			preMap.put(crs, new ArrayList<>());
			return false;
		}
	}

	/**
	 * Time complexity: O(V+E) Space complexity: O(V+E) Where V is the number of courses and E is the number of
	 * prerequisites.
	 */
	public static class SolutionUsingTopologicalSort {
		public boolean canFinish(int numCourses, int[][] prerequisites) {
			int[] inDegree = new int[numCourses];
			List<List<Integer>> adj = new ArrayList<>();
			for (int i = 0; i < numCourses; i++) {
				adj.add(new ArrayList<>());
			}
			for (int[] pre : prerequisites) {
				inDegree[pre[1]]++;
				adj.get(pre[0]).add(pre[1]);
			}

			Queue<Integer> q = new LinkedList<>();
			for (int i = 0; i < numCourses; i++) {
				if (inDegree[i] == 0) {
					q.add(i);
				}
			}

			int finish = 0;
			while (!q.isEmpty()) {
				int node = q.poll();
				finish++;
				for (int nei : adj.get(node)) {
					inDegree[nei]--;
					if (inDegree[nei] == 0) {
						q.add(nei);
					}
				}
			}

			return finish == numCourses;
		}
	}
}
