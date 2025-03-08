package main.meta.medium;

/**
 *
 *
 * <pre>
 * 695. Max Area of Island
 * Medium
 * Topics
 * Companies
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded by water.
 *
 * The area of an island is the number of cells with a value 1 in the island.
 *
 * Return the maximum area of an island in grid. If there is no island, return 0.
 *
 * Example 1:
 * Input: grid = [
 * [0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Output: 6
 * Explanation: The answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 *
 * Input: grid = [[0,0,0,0,0,0,0,0]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] is either 0 or 1.
 * Topics
 * Array
 * Depth-First Search
 * Breadth-First Search
 * Union Find
 * Matrix
 * </pre>
 */
/**
 * Solution to the "Max Area of Island" problem. This class finds the maximum area of an island in a
 * given 2D grid where: - 1 represents land - 0 represents water An island is a group of connected
 * 1's (land) where connections can only be made horizontally or vertically (not diagonally).
 */
public class MaxAreaOfIsland {
  /**
   * Finds the maximum area of an island in the given grid.
   *
   * <p>Algorithm: 1. Iterate through each cell in the grid. 2. If the cell is land (1) and not
   * visited, perform DFS to find the area of the island. 3. Keep track of the maximum area found.
   *
   * <p>Time Complexity: O(R * C) where R is the number of rows and C is the number of columns in
   * the grid. Each cell is visited at most once.
   *
   * <p>Space Complexity: O(R * C) for the visited array and recursion stack in the worst case where
   * the entire grid is one island.
   *
   * @param grid A 2D grid representing land (1) and water (0)
   * @return The maximum area of an island in the grid
   */
  public int maxAreaOfIsland(int[][] grid) {
    int result = 0;
    if (grid == null || grid.length == 0) {
      return result;
    }

    int row = grid.length;
    int col = grid[0].length;

    // Initialize visited array with false values (not null)
    boolean[][] visited = new boolean[row][col];

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        result = Math.max(result, dfs(i, j, visited, grid));
      }
    }
    return result;
  }

  /**
   * Performs Depth-First Search to find the area of an island starting from position (r, c).
   *
   * <p>Time Complexity: O(1) per call, but collectively O(R * C) for the entire grid Space
   * Complexity: O(R * C) in the worst case due to recursion stack
   *
   * @param r Row index of the current cell
   * @param c Column index of the current cell
   * @param visited 2D array to keep track of visited cells
   * @param grid The original grid
   * @return The area of the island connected to the current cell
   */
  private int dfs(int r, int c, boolean[][] visited, int[][] grid) {
    // Check boundary conditions and if the cell is water or already visited
    if (r < 0
        || r >= grid.length
        || c < 0
        || c >= grid[0].length
        || grid[r][c] == 0
        || visited[r][c]) {
      return 0;
    }

    // Mark current cell as visited
    visited[r][c] = true;

    // Calculate area by adding 1 (current cell) and the areas in four directions
    return (1
        + dfs(r + 1, c, visited, grid)
        + dfs(r - 1, c, visited, grid)
        + dfs(r, c + 1, visited, grid)
        + dfs(r, c - 1, visited, grid));
  }

  /**
   * Main method for testing the solution.
   *
   * @param args Command line arguments (not used)
   */
  public static void main(String[] args) {
    // Test code can be added here
  }
}
