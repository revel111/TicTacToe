package org.example.tictactoe;

import org.example.tictactoe.enums.GameState;
import org.example.tictactoe.enums.PlayerType;
import org.example.tictactoe.games.TicTacToeGame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TicTacToeTest {
    @Test
    void testGameStates() {
        var game = new TicTacToeGame();
        assertEquals(GameState.NOT_STARTED, game.getGameState());
        game.start();
        assertEquals(GameState.IN_PROGRESS, game.getGameState());
    }

    @Test
    void testPlayerSwitch() {
        var game = new TicTacToeGame();
        game.start();
        assertEquals("Player X", game.getCurrentPlayerName());
        assertEquals(PlayerType.X, game.getCurrentPlayerType());
        game.move(0,0);
        assertEquals("Player O", game.getCurrentPlayerName());
        assertEquals(PlayerType.O, game.getCurrentPlayerType());
    }

    @Test
    void testPlayerInabilityToMoveOnOccupiedCell() {
        var game = new TicTacToeGame();
        game.start();
        game.move(0,0);
        assertThrows(IllegalArgumentException.class,() -> game.move(0, 0));
    }

    @Test
    void testPlayerInabilityToMoveOutOfBounds() {
        var game = new TicTacToeGame();
        game.start();
        assertThrows(IndexOutOfBoundsException.class,() -> game.move(0, 4));
    }

    @Test
    void testFalseStart() {
        var game = new TicTacToeGame();
        assertThrows(IllegalStateException.class,() -> game.move(0,0));
    }

    @Test
    void testVerticalWin() {
        var game = new TicTacToeGame();
        game.start();
        game.move(0, 0); // X
        game.move(0, 1); // O
        game.move(1, 0); // X
        game.move(0, 2); // O
        game.move(2, 0); // X
        assertEquals(GameState.X_WINS, game.getGameState());
    }

    @Test
    void testHorizontalWin() {
        var game = new TicTacToeGame();
        game.start();
        game.move(0, 0); // X
        game.move(1, 0); // O
        game.move(0, 1); // X
        game.move(2, 0); // O
        game.move(0, 2); // X
        assertEquals(GameState.X_WINS, game.getGameState());
    }

    @Test
    void testDiagonalWinAndInabilityToMove() {
        var game = new TicTacToeGame();
        game.start();
        game.move(0, 0); // X
        game.move(1, 0); // O
        game.move(1, 1); // X
        game.move(2, 0); // O
        game.move(2, 2); // X
        assertEquals(GameState.X_WINS, game.getGameState());
        assertEquals("Player X", game.getResult());
        assertThrows(IllegalStateException.class, () -> game.move(0, 0));
    }

    @Test
    void testDraw() {
        var game = new TicTacToeGame();
        game.start();
        game.move(0, 0); // X
        game.move(0, 1); // O
        game.move(0, 2); // X
        game.move(1, 0); // X
        game.move(1, 2); // O
        game.move(1, 1); // X
        game.move(2, 0); // O
        game.move(2, 2); // X
        game.move(2, 1); // O
        assertEquals(GameState.DRAW, game.getGameState());
    }
}
