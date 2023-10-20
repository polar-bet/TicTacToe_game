package org.example;

import java.util.Objects;

public class AI {

    public int getAIMove() {
        String[] cells = Board.getBoard();
        int bestMove = -1;
        int bestScore = Integer.MIN_VALUE;

        for (int i = 0; i < cells.length; i++) {
            if (cells[i].equals(String.valueOf(i + 1))) {
                // Try making a move
                cells[i] = Game.O_SIGN;
                int score = minimax(cells, 0, false);
                cells[i] = String.valueOf(i + 1);

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i + 1;
                }
            }
        }

        return bestMove;
    }

    private int minimax(String[] cells, int depth, boolean isMaximizing) {
        if (CheckWinner.check(cells) != null) {
            switch (Objects.requireNonNull(CheckWinner.check(cells))) {
                case Game.X_SIGN -> {
                    return -1;
                }
                case Game.O_SIGN -> {
                    return 1;
                }
                default -> {
                    return 0;
                }
            }
        }

        int bestScore;
        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (cells[i].equals(String.valueOf(i + 1))) {
                    cells[i] = Game.O_SIGN;
                    int score = minimax(cells, depth + 1, false);
                    cells[i] = String.valueOf(i + 1);
                    bestScore = Math.max(score, bestScore);
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (cells[i].equals(String.valueOf(i + 1))) {
                    cells[i] = Game.X_SIGN;
                    int score = minimax(cells, depth + 1, true);
                    cells[i] = String.valueOf(i + 1);
                    bestScore = Math.min(score, bestScore);
                }
            }
        }
        return bestScore;
    }

}
