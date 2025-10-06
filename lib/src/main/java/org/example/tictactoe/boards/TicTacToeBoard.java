package org.example.tictactoe.boards;

import org.example.tictactoe.enums.PlayerType;

/**
 * Represents a classic 3x3 Tic-Tac-Toe board that stores player moves.
 * <p>
 * The board is a fixed-size square matrix of size {@link #SIZE} x {@link #SIZE}.
 * Each cell can contain a {@link PlayerType} value or be {@code null} if empty.
 */
public class TicTacToeBoard {

    /** The underlying storage of players' marks for each cell. */
    private final PlayerType[][] players = new PlayerType[SIZE][SIZE];

    /** The side length of the board (3 for a standard Tic-Tac-Toe). */
    public static final int SIZE = 3;

    /**
     * Returns the internal 2D array representing the board state.
     * Note: This is the live reference, not a defensive copy.
     *
     * @return 2D array of {@link PlayerType} values; {@code null} entries indicate empty cells
     */
    public PlayerType[][] getPlayers() {
        return players;
    }

    /**
     * Returns the player occupying the specified cell.
     *
     * @param row zero-based row index
     * @param col zero-based column index
     * @return the {@link PlayerType} at the cell, or {@code null} if empty
     * @throws IndexOutOfBoundsException if the coordinates are outside the board bounds
     */
    public PlayerType getPlayer(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IndexOutOfBoundsException("Cell out of board bounds");
        }
        return players[row][col];
    }

    /**
     * Places a player's mark into the specified cell if the move is valid.
     *
     * @param row    zero-based row index
     * @param col    zero-based column index
     * @param player the {@link PlayerType} making the move
     * @throws IndexOutOfBoundsException if the coordinates are outside the board bounds
     * @throws IllegalArgumentException  if the target cell is already occupied
     */
    public void move(int row, int col, PlayerType player) {
        if (row < 0 || row >= SIZE || col < 0 || col >= org.example.tictactoe.boards.TicTacToeBoard.SIZE) {
            throw new IndexOutOfBoundsException("Cell is out of bounds");
        }
        if (players[row][col] != null) {
            throw new IllegalArgumentException("Cell already occupied");
        }
        players[row][col] = player;
    }
}
