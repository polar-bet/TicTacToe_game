package org.example;

import java.util.Arrays;

public class CheckWinner {
    static public String check(String[] board) {
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> board[0] + board[1] + board[2];
                case 1 -> board[3] + board[4] + board[5];
                case 2 -> board[6] + board[7] + board[8];
                case 3 -> board[0] + board[3] + board[6];
                case 4 -> board[1] + board[4] + board[7];
                case 5 -> board[2] + board[5] + board[8];
                case 6 -> board[0] + board[4] + board[8];
                case 7 -> board[2] + board[4] + board[6];
                default -> null;
            };

            if (line.equals(Game.X_SIGN.repeat(3))) {
                return Game.X_SIGN;
            }

            else if (line.equals(Game.O_SIGN.repeat(3))) {
                return Game.O_SIGN;
            }
        }

        for (int a = 0; a < board.length; a++) {
            if (Arrays.asList(board).contains(
                    String.valueOf(a + 1))) {
                break;
            } else if (a == 8) {
                return Game.DRAW;
            }
        }

        return null;
    }
}
