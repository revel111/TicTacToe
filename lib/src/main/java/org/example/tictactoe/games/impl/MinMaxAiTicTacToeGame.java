package org.example.tictactoe.games.impl;

import org.example.tictactoe.boards.TicTacToeBoard;
import org.example.tictactoe.enums.PlayerType;
import org.example.tictactoe.games.AiMove;
import org.example.tictactoe.games.TicTacToeGame;
import org.example.tictactoe.utils.Point;

/**
 * Minimax-based AI that evaluates all possible moves to pick the optimal one
 * assuming the opponent also plays optimally.
 */
public class MinMaxAiTicTacToeGame extends TicTacToeGame implements AiMove {

    /** Creates a Min Max AI game with default player names. */
    public MinMaxAiTicTacToeGame() {
        super();
    }

    /**
     * Creates a Min Max AI game with custom player names.
     *
     * @param playerX name of the X player
     * @param playerO name of the O player
     */
    public MinMaxAiTicTacToeGame(String playerX, String playerO) {
        super(playerX, playerO);
    }

    /**
     * Computes the best next move for the AI using minimax search.
     * The AI is assumed to play as O in this implementation.
     *
     * @return coordinates of the best move as a {@link Point}
     */
    @Override
    public Point makeMove() {
        int bestScore = Integer.MIN_VALUE;
        Point bestMove = null;

        var copyBoard = this.board.copy();
        for (int row = 0; row < TicTacToeBoard.SIZE; row++) {
            for (int column = 0; column < TicTacToeBoard.SIZE; column++) {
                if (copyBoard.getPlayer(row, column) == null) {
                    copyBoard.move(row, column, PlayerType.O);
                    int currentScore = this.miniMax(copyBoard, PlayerType.X, new Point(row, column));
                    copyBoard.move(row, column, null);

                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestMove = new Point(row, column);
                    }
                }
            }
        }

        return bestMove;
    }

    /**
     * Minimax recursive evaluation.
     *
     * @param board working board (mutated and reverted during search)
     * @param isMax which player's turn is evaluating: {@link PlayerType#O} for maximizing, {@link PlayerType#X} for minimizing
     * @param point last played point to evaluate terminal states quickly
     * @return score in range {-1, 0, 1} where 1 is O win, -1 is X win, 0 is draw
     */
    private int miniMax(TicTacToeBoard board, PlayerType isMax, Point point) {
        if (board.checkWin(point.row(), point.col(), PlayerType.O)) {
            return 1;
        } else if (board.checkWin(point.row(), point.col(), PlayerType.X)) {
            return -1;
        } else if (board.checkDraw()) {
            return 0;
        }

        int bestScore = Integer.MIN_VALUE;
        if (isMax == PlayerType.O) {
            for (int row = 0; row < TicTacToeBoard.SIZE; row++) {
                for (int column = 0; column < TicTacToeBoard.SIZE; column++) {
                    if (board.getPlayer(row, column) == null) {
                        board.move(row, column, PlayerType.O);
                        int currentScore = this.miniMax(board, PlayerType.X, new Point(row, column));
                        board.move(row, column, null);

                       bestScore = Math.max(bestScore, currentScore);
                    }
                }
            }
        } else {
            bestScore =  Integer.MAX_VALUE;
            for (int row = 0; row < TicTacToeBoard.SIZE; row++) {
                for (int column = 0; column < TicTacToeBoard.SIZE; column++) {
                    if (board.getPlayer(row, column) == null) {
                        board.move(row, column, PlayerType.X);
                        int currentScore = this.miniMax(board, PlayerType.O, new Point(row, column));
                        board.move(row, column, null);

                        bestScore = Math.min(bestScore, currentScore);
                    }
                }
            }
        }

        return bestScore;
    }
}
