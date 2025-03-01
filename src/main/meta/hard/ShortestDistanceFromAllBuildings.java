package main.meta.hard;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <pre>
 * 317 - Shortest Distance from All Buildings
 * Posted on October 12, 2016 · 7 minute read
 * Welcome to Subscribe On Youtube
 *
 * 317. Shortest Distance from All Buildings
 * Description
 * You are given an m x n grid grid of values 0, 1, or 2, where:
 *
 * each 0 marks an empty land that you can pass by freely,
 * each 1 marks a building that you cannot pass through, and
 * each 2 marks an obstacle that you cannot pass through.
 * You want to build a house on an empty land that reaches all buildings in the shortest total travel distance. You can only move up, down, left, and right.
 *
 * Return the shortest travel distance for such a house. If it is not possible to build such a house according to the above rules, return -1.
 *
 * The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
 *
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 * Example 1:
 *
 *
 * Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 *
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 *
 * Output: 7
 *
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
 *              the point (1,2) is an ideal empty land to build a house, as the total
 *              travel distance of 3+3+1=7 is minimal. So return 7.
 * Example 2:
 *
 * Input: grid = [[1,0]]
 * Output: 1
 * Example 3:
 *
 * Input: grid = [[1]]
 * Output: -1
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] is either 0, 1, or 2.
 * There will be at least one building in the grid.
 * </pre>
 */
public class ShortestDistanceFromAllBuildings {

	/**
	 * Solution for finding the shortest distance from an empty land to all buildings.
	 *
	 * Problem: Given a 2D grid where:
	 * - 1 represents a building
	 * - 0 represents empty land
	 * - 2 represents obstacle Find the empty land with the shortest total distance to all buildings.
	 *
	 * Approach:
	 * - Run BFS from each building to find distances to all empty lands
	 * - Keep track of how many buildings each empty land can reach
	 * - Find the empty land that can reach all buildings with minimum total distance
	 *
	 * Time Complexity: O(k * m * n) where:
	 * - m, n are the dimensions of the grid
	 * - k is the number of buildings
	 *
	 * Space Complexity: O(m * n)
	 */
	static class SolutionUsingBFS {
		// Directions: up, right, down, left
		private static final int[][] DIRECTIONS = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

		public int shortestDistance(int[][] grid) {
			if (grid == null || grid.length == 0 || grid[0].length == 0) {
				return -1;
			}

			int rows = grid.length;
			int cols = grid[0].length;

			// Count how many buildings each empty land can reach
			int[][] reachCount = new int[rows][cols];

			// Total distance from all buildings to each empty land
			int[][] totalDistance = new int[rows][cols];

			// Count total buildings
			int totalBuildings = 0;

			// Start BFS from each building
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (grid[i][j] == 1) {
						totalBuildings++;
						bfs(grid, i, j, reachCount, totalDistance);
					}
				}
			}

			// Find the empty land with minimum total distance that can reach all buildings
			int minDistance = Integer.MAX_VALUE;
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (grid[i][j] == 0 && reachCount[i][j] == totalBuildings) {
						minDistance = Math.min(minDistance, totalDistance[i][j]);
					}
				}
			}

			return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
		}

		private void bfs(int[][] grid, int startRow, int startCol, int[][] reachCount, int[][] totalDistance) {
			int rows = grid.length;
			int cols = grid[0].length;

			boolean[][] visited = new boolean[rows][cols];
			Queue<int[]> queue = new LinkedList<>();

			queue.offer(new int[] { startRow, startCol });
			visited[startRow][startCol] = true;

			int distance = 0;

			while (!queue.isEmpty()) {
				distance++;
				int size = queue.size();

				for (int i = 0; i < size; i++) {
					int[] current = queue.poll();

					for (int[] dir : DIRECTIONS) {
						int newRow = current[0] + dir[0];
						int newCol = current[1] + dir[1];

						if (isValid(grid, newRow, newCol, visited)) {
							// Mark as visited
							visited[newRow][newCol] = true;

							// Increment reach count
							reachCount[newRow][newCol]++;

							// Add distance
							totalDistance[newRow][newCol] += distance;

							// Add to queue
							queue.offer(new int[] { newRow, newCol });
						}
					}
				}
			}
		}

		private boolean isValid(int[][] grid, int row, int col, boolean[][] visited) {
			int rows = grid.length;
			int cols = grid[0].length;

			return row >= 0 && row < rows && col >= 0 && col < cols && grid[row][col] == 0 && !visited[row][col];
		}
	}
}
