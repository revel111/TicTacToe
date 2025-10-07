package org.example.tictactoe.boards;

import org.example.tictactoe.enums.PlayerType;
import org.example.tictactoe.exceptions.CellOccupiedException;
import org.example.tictactoe.exceptions.CellOutOfBoundsException;

import java.util.Arrays;

/**
 * Represents a classic 3x3 Tic-Tac-Toe board that stores player moves.
 * <p>
 * The board is a fixed-size square matrix of size {@link #SIZE} x {@link #SIZE}.
 * Each cell can contain a {@link PlayerType} value or be {@code null} if empty.
 */
public class TicTacToeBoard {

    /** The underlying storage of players' marks for each cell. */
    private PlayerType[][] players = new PlayerType[SIZE][SIZE];

    /** The side length of the board (3 for a standard Tic-Tac-Toe). */
    public static final int SIZE = 3;

    /** Creates an empty 3x3 board. */
    public TicTacToeBoard() {
    }

    private TicTacToeBoard(PlayerType[][] players) {
        this.players = players;
    }

    /**
     * Returns a deep-copied 2D array representing the current board state.
     *
     * @return 2D array of {@link PlayerType} values; {@code null} entries indicate empty cells
     */
    public PlayerType[][] getPlayers() {
        return Arrays.stream(players).map(PlayerType[]::clone).toArray(PlayerType[][]::new);
    }

    /**
     * Returns a deep copy of this board.
     * Useful for AI search that manually reverts moves on the shared state.
     *
     * @return a new TicTacToeBoard instance
     */
    public TicTacToeBoard copy() {
        return new TicTacToeBoard(this.getPlayers());
    }

    /**
     * Returns the player occupying the specified cell.
     *
     * @param row zero-based row index
     * @param col zero-based column index
     * @return the {@link PlayerType} at the cell, or {@code null} if empty
     * @throws CellOutOfBoundsException if the coordinates are outside the board bounds
     */
    public PlayerType getPlayer(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new CellOutOfBoundsException(row, col);
        }
        return players[row][col];
    }

    /**
     * Places a player's mark into the specified cell if the move is valid.
     *
     * @param row    zero-based row index
     * @param col    zero-based column index
     * @param player the {@link PlayerType} making the move; may be {@code null} to clear a cell
     * @throws CellOutOfBoundsException if the coordinates are outside the board bounds
     * @throws CellOccupiedException  if the target cell is already occupied by a non-null value
     */
    public void move(int row, int col, PlayerType player) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new CellOutOfBoundsException(row, col);
        }
        if (players[row][col] != null && player != null) {
            throw new CellOccupiedException(row, col);
        }
        players[row][col] = player;
    }

    /**
     * Checks whether all cells are filled without considering a winner.
     *
     * @return true if there are no empty cells; false otherwise
     */
    public boolean checkDraw() {
        for (int i = 0; i < TicTacToeBoard.SIZE; i++) {
            for (int j = 0; j < TicTacToeBoard.SIZE; j++) {
                if (this.getPlayer(i, j) == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks whether the given player has a winning line given the last move coordinates.
     * This verifies the row, column, and both diagonals as needed.
     *
     * @param row last move row
     * @param col last move column
     * @param playerType the player to check for a win
     * @return true if the player has won; false otherwise
     */
    public boolean checkWin(int row, int col, PlayerType playerType) {
        if (this.checkEntry(row, true, playerType) || this.checkEntry(col, false, playerType)) {
            return true;
        }
        return this.checkDiagonalsWin(playerType);
    }

    /**
     * Checks if the player has any diagonal winning line.
     *
     * @param playerType player to check
     * @return true if any of the diagonals is fully occupied by the player
     */
    public boolean checkDiagonalsWin(PlayerType playerType) {
        boolean leftToRight = true;
        boolean rightToLeft = true;
        for (int i = 0; i < TicTacToeBoard.SIZE; i++) {
            if (this.getPlayer(i, i) != playerType) {
                leftToRight = false;
            }
            if (this.getPlayer(i, TicTacToeBoard.SIZE - 1 - i) != playerType) {
                rightToLeft = false;
            }
        }
        return leftToRight || rightToLeft;
    }

    private boolean checkEntry(int entry, boolean isRow, PlayerType playerType) {
        for (int i = 0; i < TicTacToeBoard.SIZE; i++) {
            if ((isRow ? playerType != this.getPlayer(entry, i) : playerType != this.getPlayer(i, entry))) {
                return false;
            }
        }
        return true;
    }
}
