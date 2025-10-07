package org.example.tictactoe.exceptions;

/**
 * Thrown when a game operation is invoked in an invalid state
 * (e.g., making a move before the game starts or after it ends).
 */
public class FalseStateException extends RuntimeException {
    /**
     * Creates a new exception with an explanatory message.
     *
     * @param message detail message explaining the invalid state
     */
    public FalseStateException(String message) {
        super(message);
    }
}
