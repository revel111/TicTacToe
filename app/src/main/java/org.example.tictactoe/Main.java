package org.example.tictactoe;

import org.example.tictactoe.boards.TicTacToeBoard;
import org.example.tictactoe.enums.GameState;
import org.example.tictactoe.games.AiMove;
import org.example.tictactoe.games.TicTacToeGame;
import org.example.tictactoe.utils.Point;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var ticTacToeGame = new TicTacToeGame();
//        var ticTacToeGame = new RandomAiTicTacToeGame();
        ticTacToeGame.start();
        poll(ticTacToeGame);
    }

    public static void poll(TicTacToeGame game) {
        do {
            System.out.println(printBoard(game.getBoard()));
            System.out.printf("Current player: %s\n", game.getCurrentPlayerName());
            Point point = askPoint();
            try {
                game.move(point.row(), point.col());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
//            if (game.getGameState() == GameState.IN_PROGRESS) {
//                var aiPoint = aiMove(game);
//                game.move(aiPoint.row(), aiPoint.col());
//            }
        } while (game.getGameState() == GameState.IN_PROGRESS);
        System.out.println(printBoard(game.getBoard()));
        System.out.println(game.getResult());
    }

    public static Point aiMove(TicTacToeGame game) {
        var aiGame = (AiMove) game;
        return aiGame.makeMove();
    }

    public static Point askPoint() {
        Point point;
        try {
            System.out.println("Enter row: ");
            int row = scanner.nextInt();
            System.out.println("Enter column: ");
            int col = scanner.nextInt();
            point = new Point(row, col);
        } catch (InputMismatchException _) {
            scanner.nextLine();
            System.out.println("Invalid input.");
            return askPoint();
        }
        return point;
    }

    public static String printBoard(TicTacToeBoard board) {
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
