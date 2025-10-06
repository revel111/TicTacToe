package org.example.tictactoe.enums;

/**
 * The two possible player marks in Tic-Tac-Toe.
 */
public enum PlayerType {
    /** Player X. */
    X("X"),
    /** Player O. */
    O("O");

    private final String symbol;

    PlayerType(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the opposing player type.
     *
     * @return {@link #O} if this is {@link #X}, otherwise {@link #X}
     */
    public PlayerType getOpponent() {
        return this == X ? O : X;
    }

    /**
     * Returns the winning game state associated with this player type.
     *
     * @return {@link GameState#X_WINS} for X, {@link GameState#O_WINS} for O
     */
    public GameState getAnalog() {
        return this == X ? GameState.X_WINS : GameState.O_WINS;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
