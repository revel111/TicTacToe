package org.example.tictactoe.games.impl;

import org.example.tictactoe.games.AiMove;
import org.example.tictactoe.games.TicTacToeGame;
import org.example.tictactoe.utils.Point;

/**
 * Placeholder for a smarter AI implementation. Currently, returns no move.
 * Intended for future expansion (e.g., heuristic or minimax AI).
 */
public class SmartAiTicTacToeGame extends TicTacToeGame implements AiMove {

    /** Creates a smart AI game with default player names. */
    public SmartAiTicTacToeGame() {
        super();
    }

    /**
     * Creates a smart AI game with custom player names.
     *
     * @param playerX name of the X player
     * @param playerO name of the O player
     */
    public SmartAiTicTacToeGame(String playerX, String playerO) {
        super(playerX, playerO);
    }

    /**
     * Currently unimplemented.
     *
     * @return {@code null} until a strategy is implemented
     */
    @Override
    public Point makeMove() {
        return null;
    }
}
