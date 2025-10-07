package org.example.tictactoe.utils;

/**
 * Immutable pair of zero-based board coordinates (row, col).
 *
 * @param row zero-based row index
 * @param col zero-based column index
 */
public record Point(int row, int col) {
}
