package org.example;

import java.io.FileWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private String turn;

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    final static String X_SIGN = "x";
    final static String O_SIGN = "○";

    final static String DRAW = "Нічия";

    public void game() {
        Menu menu = new Menu();
        int status = menu.menu();
        while (status != 0) {
            switch (status) {
                case 1 -> start();
                case 2 -> Stats.getInstance().printStats();
            }
            status = menu.menu();
        }
    }

    public void start() {
        Player player = Player.getInstance();
        if (player.getName().isEmpty()) {
            player.inputName();
        }
        String[] board = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        Board.setBoard(board);
        turn = X_SIGN;
        String winner = null;
        Board.printBoard();
        System.out.println(player.getName() + " (" + X_SIGN + ") ходить першим. Введіть значення клітинки щоб поставити (" + X_SIGN + "):");
        while (winner == null) {
            winner = turn();
            turn = turn.equals(X_SIGN) ? O_SIGN : X_SIGN;
        }
        switch (winner) {
            case X_SIGN -> {
                System.out.println(Game.ANSI_GREEN + "Ви перемогли!" + Game.ANSI_RESET);
                player.setScore(2);
            }
            case O_SIGN -> {
                System.out.println(Game.ANSI_RED + "ШІ переміг \uD83E\uDD16" + Game.ANSI_RESET);
                player.setScore(0);
            }
            case DRAW -> {
                System.out.println(Game.ANSI_BLUE + DRAW + Game.ANSI_RESET);
                player.setScore(1);
            }
        }

        Stats.getInstance().addStats(player.getName(), player.getScore());
    }

    public String turn() {
        String[] cells = Board.getBoard();
        Scanner in = new Scanner(System.in);
        int numInput = 0;
        String winner = null;
        if (turn.equals(X_SIGN)) {
            boolean validInput = false;
            while (!validInput) {
                try {
                    numInput = in.nextInt();
                    if (numInput > 0 && numInput <= 9) {
                        validInput = true;
                    } else {
                        System.out.println("Некоректне значення, введіть номер клітинки:");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Некоректне значення, введіть номер клітинки:");
                    in.nextLine();
                }
            }
            if (cells[numInput - 1].equals(String.valueOf(numInput))) {
                cells[numInput - 1] = turn;
                Board.setBoard(cells);
                Board.printBoard();
                winner = CheckWinner.check(cells);
            } else {
                System.out.println("Клітинка вже зайнята, оберіть іншу:");
                turn();
            }
        } else {
            AI AI = new AI();
            int aiMove = AI.getAIMove();
            System.out.println("ШІ (" + O_SIGN + ") обирає клітинку під номером " + aiMove);
            cells[aiMove - 1] = turn;
            Board.setBoard(cells);
            Board.printBoard();
            winner = CheckWinner.check(Board.getBoard());
        }
        return winner;
    }
}
