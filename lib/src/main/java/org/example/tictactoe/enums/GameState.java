package org.example.tictactoe.enums;

/**
 * Represents the possible states of a Tic-Tac-Toe game lifecycle and outcome.
 */
public enum GameState {
    /** Game has not been started yet. */
    NOT_STARTED("Not Started"),
    /** Game is currently in progress. */
    IN_PROGRESS("In Progress"),
    /** Game ended with no winner. */
    DRAW("Draw"),
    /** Player X has won the game. */
    X_WINS("X Wins"),
    /** Player O has won the game. */
    O_WINS("O Wins");

    private final String label;

    GameState(String label) {
        this.label = label;
    }

    /**
     * Maps a {@link PlayerType} to the corresponding winning {@link GameState} value.
     *
     * @param player the player type
     * @return {@link #X_WINS} if player is X, otherwise {@link #O_WINS}
     */
    public static GameState checkState(PlayerType player) {
        return player == PlayerType.X ? X_WINS : O_WINS;
    }

    @Override
    public String toString() {
        return label;
    }
}
