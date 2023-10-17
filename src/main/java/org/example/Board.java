package org.example;

public class Board {

    private static String[] board = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};


    public static void printBoard() {
        String[] boardOutput = new String[9];
        for (int i = 0; i < board.length; i++) {
            switch (board[i]) {
                case Game.X_SIGN -> boardOutput[i] = Game.ANSI_BLUE + board[i] + Game.ANSI_RESET;
                case Game.O_SIGN -> boardOutput[i] = Game.ANSI_RED + board[i] + Game.ANSI_RESET;
                default -> boardOutput[i] = board[i];
            }
        }
        System.out.println("╔═══╦═══╦═══╗");
        System.out.println("║ " + boardOutput[0] + " ║ "
                + boardOutput[1] + " ║ " + boardOutput[2]
                + " ║");
        System.out.println("╠═══╬═══╬═══╣");
        System.out.println("║ " + boardOutput[3] + " ║ "
                + boardOutput[4] + " ║ " + boardOutput[5]
                + " ║");
        System.out.println("╠═══╬═══╬═══╣");
        System.out.println("║ " + boardOutput[6] + " ║ "
                + boardOutput[7] + " ║ " + boardOutput[8]
                + " ║");
        System.out.println("╚═══╩═══╩═══╝");
    }

    public static String[] getBoard() {
        return board;
    }

    public static void setBoard(String[] board) {
        Board.board = board;
    }
}
