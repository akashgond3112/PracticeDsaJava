package main.meta.medium;

import java.util.*;

/**
 *
 *
 * <pre>
 * 286. Walls And Gates - Explanation
 * Problem Link
 *
 * Description
 * You are given a
 * m×n
 * m×n 2D grid initialized with these three possible values:
 *
 * -1 - A water cell that can not be traversed.
 * 0 - A treasure chest.
 * INF - A land cell that can be traversed. We use the integer 2^31 - 1 = 2147483647 to represent INF.
 * Fill each land cell with the distance to its nearest treasure chest. If a land cell cannot reach a treasure chest than the value should remain INF.
 *
 * Assume the grid can only be traversed up, down, left, or right.
 *
 * Modify the grid in-place.
 *
 * Example 1:
 *
 * Input: [
 *   [2147483647,-1,0,2147483647],
 *   [2147483647,2147483647,2147483647,-1],
 *   [2147483647,-1,2147483647,-1],
 *   [0,-1,2147483647,2147483647]
 * ]
 *
 * Output: [
 *   [3,-1,0,1],
 *   [2,2,1,-1],
 *   [1,-1,2,-1],
 *   [0,-1,3,4]
 * ]
 * Example 2:
 *
 * Input: [
 *   [0,-1],
 *   [2147483647,2147483647]
 * ]
 *
 * Output: [
 *   [0,-1],
 *   [1,2]
 * ]
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * grid[i][j] is one of {-1, 0, 2147483647}
 * </pre>
 */
public class WallsAndGates {

  /**
   * Solution class that implements a backtracking algorithm to solve the island treasure problem.
   *
   * <p>Time Complexity: O(row * col * 4^(row*col)) - For each cell in the grid, we potentially
   * explore all paths through the grid - Each cell has up to 4 directions to explore - In worst
   * case, we visit nearly all cells before backtracking
   *
   * <p>Space Complexity: O(row * col) - We use a visited array of size row*col - The recursion
   * stack can go as deep as row*col in the worst case
   */
  public static class SolutionUsingBackTracking {
    /** Possible movement directions: right, left, down, up */
    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /** Represents infinity or an unreachable state */
    private static final int INF = 214783647;

    /** Tracks visited cells during DFS to avoid cycles */
    private static boolean[][] visited;

    /** Grid dimensions */
    private static int row, col;

    /**
     * Depth-first search to find the shortest path to treasure.
     *
     * @param grid The island grid
     * @param r Current row position
     * @param c Current column position
     * @return The minimum distance to treasure or INF if unreachable
     *     <p>Time Complexity: O(4^(row*col)) worst case Space Complexity: O(row*col) for recursion
     *     stack
     */
    private static int dfs(int[][] grid, int r, int c) {
      if (r < 0 || c < 0 || r >= row || c >= col || grid[r][c] == -1 || visited[r][c]) {
        return INF;
      }
      if (grid[r][c] == 0) {
        return 0;
      }
      visited[r][c] = true;
      int res = INF;
      for (int[] dir : directions) {
        int cur = dfs(grid, r + dir[0], c + dir[1]);
        if (cur != INF) {
          res = Math.min(res, cur);
        }
      }
      visited[r][c] = false;
      return res;
    }

    /**
     * Processes the entire island grid to find treasure distances.
     *
     * @param grid The island grid where: - 0 represents treasure - -1 represents obstacles - Other
     *     values represent land cells - INF represents unreachable cells
     *     <p>After execution, each cell in the grid will contain the minimum distance to the
     *     nearest treasure, or INF if unreachable.
     *     <p>Time Complexity: O(row * col * 4^(row*col)) Space Complexity: O(row * col)
     */
    public static void islandTreasure(int[][] grid) {
      row = grid.length;
      col = grid[0].length;
      visited = new boolean[row][col];
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
          if (grid[i][j] != INF) {
            grid[i][j] = dfs(grid, i, j);
          }
        }
      }
    }
  }

  public static void main(String[] args) {}
}
