package org.example.tictactoe;

import org.example.tictactoe.boards.TicTacToeBoard;
import org.example.tictactoe.enums.GameState;
import org.example.tictactoe.games.AiMove;
import org.example.tictactoe.games.TicTacToeGame;
import org.example.tictactoe.games.impl.RandomAiTicTacToeGame;
import org.example.tictactoe.utils.Point;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameWrapper {

    private final Scanner scanner = new Scanner(System.in);
    private TicTacToeGame game;

    public void run() {
        this.game = this.chooseGameMode();
        game.start();
        this.poll();
    }

    private TicTacToeGame chooseGameMode() {
        System.out.println("Choose game mode:\n1 - Human vs Human\n2 - Human vs Random AI");
        int choice = readIntSafe();
        switch (choice) {
            case 1 -> {
                return new TicTacToeGame(askName("Player X"), askName("Player O"));
            }
            case 2 -> {
                return new RandomAiTicTacToeGame(askName("Player X"), "AI O");
            }
            default -> {
                System.out.println("Unknown option. Defaulting to Human vs Human.");
                return new TicTacToeGame(askName("Player X"), askName("Player O"));
            }
        }
    }

    private String askName(String defaultValue) {
        System.out.printf("Enter name for %s: ", defaultValue);
        String name = scanner.nextLine();
        return name.trim().isBlank() ? defaultValue : name;
    }

    private void poll() {
        do {
            System.out.println(printBoard(game.getBoard()));
            System.out.printf("Current player: %s%n", game.getCurrentPlayerName());
            Point point = askPoint();
            try {
                game.move(point.row(), point.col());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            if (game.getGameState() == GameState.IN_PROGRESS && game instanceof AiMove aiMove) {
                System.out.println(printBoard(game.getBoard()));
                System.out.printf("Current player: %s%n", game.getCurrentPlayerName());
                Point aiPoint = aiMove.makeMove();
                try {
                    game.move(aiPoint.row(), aiPoint.col());
                } catch (Exception e) {
                    System.out.println("AI failed to move: " + e.getMessage());
                }
            }
        } while (game.getGameState() == GameState.IN_PROGRESS);
        System.out.println(printBoard(game.getBoard()));
        System.out.printf(game.getResult());
    }

    private Point askPoint() {
        try {
            System.out.print("Enter row: ");
            int row = readIntSafe();
            System.out.print("Enter column: ");
            int col = readIntSafe();
            return new Point(row, col);
        } catch (InputMismatchException _) {
            System.out.println("Invalid input.");
            return askPoint();
        }
    }

    private int readIntSafe() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException _) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    private String printBoard(TicTacToeBoard board) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < TicTacToeBoard.SIZE; i++) {
            for (int j = 0; j < TicTacToeBoard.SIZE; j++) {
                var player = board.getPlayer(i, j);
                sb.append(player == null ? "." : player);
                if (j < TicTacToeBoard.SIZE - 1) {
                    sb.append(" | ");
                }
            }
            sb.append("\n");
            if (i < TicTacToeBoard.SIZE - 1) {
                sb.append("-".repeat(TicTacToeBoard.SIZE * 4 - 3)).append("\n");
            }
        }

        return sb.toString();
    }
}
