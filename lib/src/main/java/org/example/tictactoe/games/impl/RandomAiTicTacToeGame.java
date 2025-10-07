package org.example.tictactoe.games.impl;

import org.example.tictactoe.boards.TicTacToeBoard;
import org.example.tictactoe.enums.GameState;
import org.example.tictactoe.games.AiMove;
import org.example.tictactoe.games.TicTacToeGame;
import org.example.tictactoe.utils.Point;

import java.util.Random;

/**
 * Simple AI that selects a random empty cell for its next move.
 */
public class RandomAiTicTacToeGame extends TicTacToeGame implements AiMove {

    private final Random random = new Random();

    /** Creates a random AI game with default player names. */
    public RandomAiTicTacToeGame() {
        super();
    }

    /**
     * Creates a random AI game with custom player names.
     *
     * @param playerX name of the X player
     * @param playerO name of the O player
     */
    public RandomAiTicTacToeGame( String playerX, String playerO) {
        super(playerX, playerO);
    }

    /**
     * Picks a uniformly random empty cell on the board.
     *
     * @return a {@link Point} representing the chosen cell coordinates
     */
    @Override
    public Point makeMove() {
        int row;
        int col;

        do {
            row = random.nextInt(TicTacToeBoard.SIZE);
            col = random.nextInt(TicTacToeBoard.SIZE);
        } while (board.getPlayer(row, col) != null && gameState == GameState.IN_PROGRESS);

        return new Point(row, col);
    }
}
