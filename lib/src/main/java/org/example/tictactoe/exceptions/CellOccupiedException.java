package org.example.tictactoe.exceptions;

/**
 * Thrown to indicate that an attempted move targets a cell that is already occupied.
 */
public class CellOccupiedException extends RuntimeException {
    /**
     * Creates a new exception with a message describing the occupied cell coordinates.
     *
     * @param row zero-based row index of the occupied cell
     * @param col zero-based column index of the occupied cell
     */
    public CellOccupiedException(int  row, int col) {
        super(String.format("Cell %d %d Occupied", row, col));
    }
}
