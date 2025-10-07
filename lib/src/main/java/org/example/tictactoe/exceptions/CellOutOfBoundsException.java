package org.example.tictactoe.exceptions;

/**
 * Thrown to indicate that specified board coordinates are outside the valid range.
 */
public class CellOutOfBoundsException extends RuntimeException {
    /**
     * Creates a new exception with a message describing the out-of-bounds coordinates.
     *
     * @param row zero-based row index
     * @param col zero-based column index
     */
    public CellOutOfBoundsException(int row, int col) {
        super(String.format("Cell %d %d Out Of Bounds", row, col));
    }
}
