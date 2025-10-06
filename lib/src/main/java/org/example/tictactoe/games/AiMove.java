package org.example.tictactoe.games;

import org.example.tictactoe.utils.Point;

/**
 * Contract for a Tic-Tac-Toe AI that can select the next move
 * given the current game state managed externally.
 */
public interface AiMove {

    /**
     * Computes the next move coordinates.
     *
     * @return a {@link Point} containing zero-based row and column indices
     */
    Point makeMove();

}
