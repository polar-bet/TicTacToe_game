package org.example;

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

    public Game() {
    }

    public void game(){

    }

    public void start() {
        turn = X_SIGN;
        String winner = null;
        System.out.println("Ласкаво просимо до хрестиків ноликів (3 х 3).");
        Board.printBoard();
        System.out.println(X_SIGN + " ходить першим. Введіть значення клітинки щоб поставити " + X_SIGN + ":");
        while (winner == null) {
            winner = turn();
            turn = turn.equals(X_SIGN) ? O_SIGN : X_SIGN;
        }
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

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public String getTurn() {
        return turn;
    }
}
