package org.example;

import java.util.Scanner;

public class Player {
    private long score;
    private String name = "";

    private static Player INSTANCE;

    private Player() {

    }

    public static Player getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Player();
        }

        return INSTANCE;
    }

    public void inputName() {
        Scanner in = new Scanner(System.in);

        System.out.println(Game.ANSI_BLUE + "Введіть ваше ім'я (3 - 8 символів)" + Game.ANSI_RESET);

        try {
            String input = in.next().trim();
            if (input.length() <= 3) {
                throw new Exception("Ім'я повинно містити хоча б 3 символи");
            }
            if (input.length() > 8) {
                throw new Exception("Ім'я не повинно перевищувати 8 символів");
            }
            setName(input);
        } catch (Exception e) {
            System.out.println(Game.ANSI_RED + e.getMessage() + Game.ANSI_RESET);
            inputName();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public long getScore() {
        return this.score;
    }
}
