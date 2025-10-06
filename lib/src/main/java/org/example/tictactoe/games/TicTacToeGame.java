package org.example.tictactoe.games;

import org.example.tictactoe.enums.GameState;
import org.example.tictactoe.enums.PlayerType;
import org.example.tictactoe.boards.TicTacToeBoard;

import java.util.Map;

/**
 * Core Tic-Tac-Toe game engine that manages turns, validates moves,
 * and calculates game outcomes (win/draw/in-progress).
 */
public class TicTacToeGame {

    /** Game board used to store player moves. */
    protected final TicTacToeBoard board;
    /** Mapping from winning {@link GameState} to player names. */
    protected final Map<GameState, String> players;
    /** Current state of the game. */
    protected GameState gameState = GameState.NOT_STARTED;
    /** Player to move next. */
    protected PlayerType currentPlayer = PlayerType.X;

    /**
     * Creates a game with default player names ("Player X" and "Player O").
     */
    public TicTacToeGame() {
        this("Player X", "Player O");
    }

    /**
     * Creates a game with custom player names.
     *
     * @param playerX name of the X player
     * @param playerO name of the O player
     */
    public TicTacToeGame(String playerX, String playerO) {
        this.board = new TicTacToeBoard();
        this.players = Map.of(
                GameState.O_WINS, playerO,
                GameState.X_WINS, playerX
        );
    }

    /**
     * Returns the underlying game board.
     *
     * @return the board instance
     */
    public final TicTacToeBoard getBoard() {
        return board;
    }

    /**
     * Returns the current game state.
     *
     * @return the {@link GameState}
     */
    public final GameState getGameState() {
        return gameState;
    }

    /**
     * Returns the type of the player who is to move next.
     *
     * @return {@link PlayerType#X} or {@link PlayerType#O}
     */
    public final PlayerType getCurrentPlayerType() {
        return currentPlayer;
    }

    /**
     * Returns the display name of the player whose turn it is.
     *
     * @return player name if the game is in progress; {@code null} otherwise
     */
    public final String getCurrentPlayerName() {
        return gameState == GameState.IN_PROGRESS ? players.get(currentPlayer.getAnalog()) : null;
    }

    /**
     * Returns the result label if the game is finished.
     *
     * @return winner name for a win, "Draw" for a draw, or {@code null} if the game is not finished
     */
    public String getResult() {
        if (gameState == GameState.O_WINS || gameState == GameState.X_WINS) {
            return players.get(gameState);
        } else if (gameState == GameState.DRAW){
            return gameState.toString();
        }
        return null;
    }

    /**
     * Starts the game, transitioning the state to {@link GameState#IN_PROGRESS}.
     *
     * @throws IllegalStateException if the game has already started or finished
     */
    public void start() {
        if (gameState != GameState.NOT_STARTED) {
            throw new IllegalStateException("Game has already started or finished");
        }
        gameState = GameState.IN_PROGRESS;
    }

    /**
     * Performs a move for the current player at the given coordinates and advances the game state.
     *
     * @param row zero-based row index
     * @param col zero-based column index
     * @return resulting {@link GameState} after the move
     * @throws IllegalStateException if the game is not started or already over
     * @throws IndexOutOfBoundsException if the coordinates are outside the board bounds
     * @throws IllegalArgumentException if the target cell is already occupied
     */
    public GameState move(int row, int col) {
        if (gameState == GameState.NOT_STARTED) {
            throw new IllegalStateException("Game is not started");
        } else if (gameState != GameState.IN_PROGRESS) {
            throw new IllegalStateException("Game is already over");
        }

        board.move(row, col, currentPlayer);
        if (this.checkWin(row, col)) {
            gameState = GameState.checkState(currentPlayer);
            return gameState;
        } else if (this.checkDraw()) {
            gameState = GameState.DRAW;
            return gameState;
        }
        currentPlayer = currentPlayer.getOpponent();
        return GameState.IN_PROGRESS;
    }

    private boolean checkWin(int row, int col) {
        if (this.checkEntry(row, true) || this.checkEntry(col, false)) {
            return true;
        }
        return this.checkDiagonals();
    }

    private boolean checkEntry(int entry, boolean isRow) {
        for (int i = 0; i < TicTacToeBoard.SIZE; i++) {
            if ((isRow ? currentPlayer != board.getPlayer(entry, i) : currentPlayer != board.getPlayer(i, entry))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonals() {
        boolean leftToRight = true;
        boolean rightToLeft = true;
        for (int i = 0; i < TicTacToeBoard.SIZE; i++) {
            if (board.getPlayer(i, i) != currentPlayer) {
                leftToRight = false;
            }
            if (board.getPlayer(i, TicTacToeBoard.SIZE - 1 - i) != currentPlayer) {
                rightToLeft = false;
            }
        }
        return leftToRight || rightToLeft;
    }

    private boolean checkDraw() {
        for (int i = 0; i < TicTacToeBoard.SIZE; i++) {
            for (int j = 0; j < TicTacToeBoard.SIZE; j++) {
                if (board.getPlayer(i, j) == null) {
                    return false;
                }
            }
        }
        return true;
    }
}
