package main.meta.hard;

import java.util.UUID;

public class TicTacToeGame {

	public enum Result {
		ERROR, SUCCESS, WIN
	}


	public static class SolutionBruteForce {
		private final int boardSize;
		private final int minWinningCount;
		private final UUID playerOne;
		private final UUID playerTwo;
		private final UUID[][] board;
		private UUID currentPlayer;
		private boolean gameOver;

		/**
		 * Initialize the game with parameters
		 *
		 * @param player1
		 * 		First player UUID
		 * @param player2
		 * 		Second player UUID
		 * @param minWinningCount
		 * 		Minimum consecutive marks needed to win
		 * @param boardSize
		 * 		Size of the board (n x n)
		 */
		public SolutionBruteForce(UUID player1, UUID player2, int minWinningCount, int boardSize) {
			if (boardSize < minWinningCount) {
				throw new IllegalArgumentException("Board size must be at least as large as minWinningCount");
			}

			this.playerOne = player1;
			this.playerTwo = player2;
			this.minWinningCount = minWinningCount;
			this.boardSize = boardSize;
			this.board = new UUID[boardSize][boardSize];

			// Initialize board with null values (empty spaces)
			// No need to fill with anything as arrays are initialized with null by default

			this.currentPlayer = playerOne; // Player one starts
			this.gameOver = false;
		}

		/**
		 * Make a move on the board
		 *
		 * @param x
		 * 		Row index (0-based)
		 * @param y
		 * 		Column index (0-based)
		 * @param player
		 * 		UUID of the player making the move
		 * @return Result of the move
		 */
		public Result move(int x, int y, UUID player) {
			// Check if game is already over
			if (gameOver) {
				return Result.ERROR;
			}

			// Check if coordinates are valid
			if (x < 0 || x >= boardSize || y < 0 || y >= boardSize) {
				return Result.ERROR;
			}

			// Check if the cell is already occupied
			if (board[x][y] != null) {
				return Result.ERROR;
			}

			// Check if it's this player's turn
			if (!player.equals(currentPlayer)) {
				return Result.ERROR;
			}

			// Make the move by storing the player's UUID
			board[x][y] = player;

			// Check for win
			if (checkWin(x, y, player)) {
				gameOver = true;
				return Result.WIN;
			}

			// Switch turns
			currentPlayer = currentPlayer.equals(playerOne) ? playerTwo : playerOne;

			return Result.SUCCESS;
		}

		/**
		 * Check if the last move resulted in a win
		 *
		 * @param x
		 * 		Row of the last move
		 * @param y
		 * 		Column of the last move
		 * @param player
		 * 		UUID of the player who made the move
		 * @return true if the player won, false otherwise
		 */
		private boolean checkWin(int x, int y, UUID player) {
			// Check horizontal
			if (checkDirection(x, y, 0, 1, player) + checkDirection(x, y, 0, -1, player) - 1 >= minWinningCount) {
				return true;
			}

			// Check vertical
			if (checkDirection(x, y, 1, 0, player) + checkDirection(x, y, -1, 0, player) - 1 >= minWinningCount) {
				return true;
			}

			// Check diagonal (top-left to bottom-right)
			if (checkDirection(x, y, 1, 1, player) + checkDirection(x, y, -1, -1, player) - 1 >= minWinningCount) {
				return true;
			}

			// Check diagonal (top-right to bottom-left)
			if (checkDirection(x, y, 1, -1, player) + checkDirection(x, y, -1, 1, player) - 1 >= minWinningCount) {
				return true;
			}

			return false;
		}

		/**
		 * Count consecutive marks in a particular direction
		 *
		 * @param x
		 * 		Starting row
		 * @param y
		 * 		Starting column
		 * @param dx
		 * 		Row direction
		 * @param dy
		 * 		Column direction
		 * @param player
		 * 		UUID of the player
		 * @return Count of consecutive marks
		 */
		private int checkDirection(int x, int y, int dx, int dy, UUID player) {
			int count = 0;
			int i = x;
			int j = y;

			while (i >= 0 && i < boardSize && j >= 0 && j < boardSize && player.equals(board[i][j])) {
				count++;
				i += dx;
				j += dy;
			}

			return count;
		}

		/**
		 * Get the current state of the board
		 *
		 * @return 2D array representing the board
		 */
		public UUID[][] getBoard() {
			// Return a copy to prevent modification
			UUID[][] copy = new UUID[boardSize][boardSize];
			for (int i = 0; i < boardSize; i++) {
				System.arraycopy(board[i], 0, copy[i], 0, boardSize);
			}
			return copy;
		}

		/**
		 * Get the current player's UUID
		 *
		 * @return UUID of the current player
		 */
		public UUID getCurrentPlayer() {
			return currentPlayer;
		}

		/**
		 * Check if the game is over
		 *
		 * @return true if the game is over, false otherwise
		 */
		public boolean isGameOver() {
			return gameOver;
		}

		/**
		 * Get player one's UUID
		 *
		 * @return UUID of player one
		 */
		public UUID getPlayerOne() {
			return playerOne;
		}

		/**
		 * Get player two's UUID
		 *
		 * @return UUID of player two
		 */
		public UUID getPlayerTwo() {
			return playerTwo;
		}
	}

	public static class OptimalTicTacToeGame {

		public enum Result {
			ERROR, SUCCESS, WIN, DRAW
		}

		private final int boardSize;
		private final int minWinningCount;
		private final UUID playerOne;
		private final UUID playerTwo;
		private final UUID[][] board;
		private UUID currentPlayer;
		private boolean gameOver;
		private int movesCount;
		private final int totalCells;

		/**
		 * Initialize the game with parameters
		 *
		 * @param player1
		 * 		First player UUID
		 * @param player2
		 * 		Second player UUID
		 * @param minWinningCount
		 * 		Minimum consecutive marks needed to win
		 * @param boardSize
		 * 		Size of the board (n x n)
		 */
		public OptimalTicTacToeGame(UUID player1, UUID player2, int minWinningCount, int boardSize) {
			if (boardSize < minWinningCount) {
				throw new IllegalArgumentException("Board size must be at least as large as minWinningCount");
			}

			this.playerOne = player1;
			this.playerTwo = player2;
			this.minWinningCount = minWinningCount;
			this.boardSize = boardSize;
			this.board = new UUID[boardSize][boardSize];
			this.currentPlayer = playerOne;
			this.gameOver = false;
			this.movesCount = 0;
			this.totalCells = boardSize * boardSize;
		}

		/**
		 * Make a move on the board with optimized win checking
		 *
		 * @param x
		 * 		Row index (0-based)
		 * @param y
		 * 		Column index (0-based)
		 * @param player
		 * 		UUID of the player making the move
		 * @return Result of the move
		 */
		public Result move(int x, int y, UUID player) {
			// Check if game is already over
			if (gameOver) {
				return Result.ERROR;
			}

			// Check if coordinates are valid
			if (x < 0 || x >= boardSize || y < 0 || y >= boardSize) {
				return Result.ERROR;
			}

			// Check if the cell is already occupied
			if (board[x][y] != null) {
				return Result.ERROR;
			}

			// Check if it's this player's turn
			if (!player.equals(currentPlayer)) {
				return Result.ERROR;
			}

			// Make the move by storing the player's UUID
			board[x][y] = player;
			movesCount++;

			// Check for win using optimized approach
			if (checkWinOptimized(x, y, player)) {
				gameOver = true;
				return Result.WIN;
			}

			// Check for draw
			if (movesCount == totalCells) {
				gameOver = true;
				return Result.DRAW;
			}

			// Switch turns
			currentPlayer = currentPlayer.equals(playerOne) ? playerTwo : playerOne;

			return Result.SUCCESS;
		}

		/**
		 * Optimized win check that only examines lines passing through the last move
		 *
		 * @param row
		 * 		Row of the last move
		 * @param col
		 * 		Column of the last move
		 * @param player
		 * 		Player who made the move
		 * @return true if the player won, false otherwise
		 */
		private boolean checkWinOptimized(int row, int col, UUID player) {
			// Direction pairs: horizontal, vertical, diagonal, anti-diagonal
			int[][] directions = { { 0, 1 }, { 1, 0 }, { 1, 1 }, { 1, -1 } };

			for (int[] direction : directions) {
				int dx = direction[0];
				int dy = direction[1];

				if (countConsecutive(row, col, dx, dy, player) >= minWinningCount) {
					return true;
				}
			}

			return false;
		}

		/**
		 * Count consecutive marks in both directions along a line
		 *
		 * @param row
		 * 		Starting row
		 * @param col
		 * 		Starting column
		 * @param dx
		 * 		Row direction
		 * @param dy
		 * 		Column direction
		 * @param player
		 * 		Player to check
		 * @return Total count of consecutive marks
		 */
		private int countConsecutive(int row, int col, int dx, int dy, UUID player) {
			// Count in positive direction
			int count = 1; // Start with 1 for the current position

			// Check in positive direction
			int i = row + dx;
			int j = col + dy;
			while (isValidPosition(i, j) && player.equals(board[i][j])) {
				count++;
				i += dx;
				j += dy;
			}

			// Check in negative direction
			i = row - dx;
			j = col - dy;
			while (isValidPosition(i, j) && player.equals(board[i][j])) {
				count++;
				i -= dx;
				j -= dy;
			}

			return count;
		}

		/**
		 * Check if a position is valid on the board
		 *
		 * @param row
		 * 		Row index
		 * @param col
		 * 		Column index
		 * @return true if valid, false otherwise
		 */
		private boolean isValidPosition(int row, int col) {
			return row >= 0 && row < boardSize && col >= 0 && col < boardSize;
		}

		/**
		 * Get the current state of the board
		 *
		 * @return 2D array representing the board
		 */
		public UUID[][] getBoard() {
			// Return a copy to prevent modification
			UUID[][] copy = new UUID[boardSize][boardSize];
			for (int i = 0; i < boardSize; i++) {
				System.arraycopy(board[i], 0, copy[i], 0, boardSize);
			}
			return copy;
		}

		/**
		 * Get the current player's UUID
		 *
		 * @return UUID of the current player
		 */
		public UUID getCurrentPlayer() {
			return currentPlayer;
		}

		/**
		 * Check if the game is over
		 *
		 * @return true if the game is over, false otherwise
		 */
		public boolean isGameOver() {
			return gameOver;
		}

		/**
		 * Get player one's UUID
		 *
		 * @return UUID of player one
		 */
		public UUID getPlayerOne() {
			return playerOne;
		}

		/**
		 * Get player two's UUID
		 *
		 * @return UUID of player two
		 */
		public UUID getPlayerTwo() {
			return playerTwo;
		}

		/**
		 * Get number of moves made so far
		 *
		 * @return count of moves
		 */
		public int getMovesCount() {
			return movesCount;
		}
	}


}