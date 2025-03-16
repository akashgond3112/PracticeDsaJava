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
  /** Possible movement directions: right, left, down, up */
  private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  /** Represents infinity or an unreachable state */
  private static final int INF = 214783647;

  /** Tracks visited cells during DFS to avoid cycles */
  private static boolean[][] visited;

  /** Grid dimensions */
  private static int row, col;

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

  /**
   * Solution class that implements a breadth-first search algorithm to solve the island treasure
   * problem.
   *
   * <p>Time Complexity: O(row * col * row * col) - For each cell in the grid, we potentially need
   * to explore the entire grid - BFS ensures we find the shortest path to treasure
   *
   * <p>Space Complexity: O(row * col) - We use a visited array of size row*col - The queue can
   * contain at most row*col elements
   */
  public static class SolutionUsingBFS {
    /**
     * Breadth-first search to find the shortest path to treasure.
     *
     * @param grid The island grid
     * @param r Starting row position
     * @param c Starting column position
     * @return The minimum distance to treasure or INF if unreachable
     *     <p>Time Complexity: O(row * col) Space Complexity: O(row * col) for the queue
     */
    private static int bfs(int[][] grid, int r, int c) {
      Queue<int[]> q = new LinkedList<>();
      q.add(new int[] {r, c});
      visited[r][c] = true;
      int steps = 0;
      while (!q.isEmpty()) {
        int size = q.size();
        for (int i = 0; i < size; i++) {
          int[] cur = q.poll();
          assert cur != null;
          int curRow = cur[0];
          int curCol = cur[1];
          if (grid[curRow][curCol] == 0) return steps;
          for (int[] dir : directions) {
            int newRow = curRow + dir[0];
            int newCol = curCol + dir[1];
            if (newRow >= 0
                && newRow < row
                && newCol >= 0
                && newCol < col
                && !visited[newRow][newCol]
                && grid[newRow][newCol] != -1) {
              visited[newRow][newCol] = true;
              q.add(new int[] {newRow, newCol});
            }
          }
        }
        steps++;
      }
      return INF;
    }

    /**
     * Processes the entire island grid to find treasure distances using BFS.
     *
     * @param grid The island grid where: - 0 represents treasure - -1 represents obstacles - Other
     *     values represent land cells - INF represents unreachable cells
     *     <p>After execution, each cell in the grid will contain the minimum distance to the
     *     nearest treasure, or INF if unreachable.
     *     <p>Time Complexity: O(row * col * row * col) Space Complexity: O(row * col)
     */
    public static void islandTreasure(int[][] grid) {
      row = grid.length;
      col = grid[0].length;
      visited = new boolean[row][col];
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
          if (grid[i][j] != INF) {
            grid[i][j] = bfs(grid, i, j);
          }
        }
      }
    }
  }

  /**
   * Solution class that implements a multi-source breadth-first search algorithm to solve the
   * island treasure problem.
   *
   * <p>Time Complexity: O(m * n) - We process each cell in the grid exactly once - For each cell,
   * we check its four adjacent neighbors
   *
   * <p>Space Complexity: O(m * n) - In worst case, the queue could contain all treasure cells (up
   * to m*n)
   */
  public static class SolutionUsingBFSMultiSource {
    /**
     * Processes the entire island grid to find treasure distances using multi-source BFS. This
     * approach starts BFS from all treasure cells simultaneously, which is more efficient than
     * starting from each cell separately.
     *
     * @param grid The island grid where: - 0 represents treasure - -1 represents obstacles - Other
     *     positive values will be overwritten with distances
     *     <p>After execution, each cell in the grid will contain the minimum distance to the
     *     nearest treasure, or Integer.MAX_VALUE if unreachable.
     *     <p>Time Complexity: O(m * n) Space Complexity: O(m * n)
     */
    public void islandTreasure(int[][] grid) {
      Queue<int[]> q = new LinkedList<>();
      int m = grid.length;
      int n = grid[0].length; // Fixed: Use grid[0].length for columns

      // Add all treasure cells to the queue as starting points
      // and mark all non-treasure, non-obstacle cells as unvisited
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) { // Fixed: Use j < n instead of i < n
          if (grid[i][j] == 0) {
            q.add(new int[] {i, j});
          } else if (grid[i][j] != -1) {
            grid[i][j] = Integer.MAX_VALUE; // Mark unvisited cells
          }
        }
      }

      // Return if no treasure exists
      if (q.isEmpty()) {
        return;
      }

      // Process the queue in BFS manner
      while (!q.isEmpty()) {
        int[] node = q.poll();
        int row = node[0];
        int col = node[1];

        // Check all four directions
        for (int[] dir : directions) {
          int curRow = row + dir[0]; // Fixed: Add to current position
          int curCol = col + dir[1]; // Fixed: Add to current position

          // Skip invalid cells, obstacles, or already visited cells with shorter paths
          if (curRow < 0
              || curRow >= m
              || curCol < 0
              || curCol >= n
              || grid[curRow][curCol] == -1
              || grid[curRow][curCol] != Integer.MAX_VALUE) {
            continue;
          }

          // Update distance and add to queue
          grid[curRow][curCol] = grid[row][col] + 1; // Fixed: Update with parent's distance + 1
          q.add(new int[] {curRow, curCol});
        }
      }
    }
  }

  public static void main(String[] args) {}
}
