package org.example;

import java.util.Scanner;

public class Menu {
    Player player = Player.getInstance();

    public int menu() {
        printMenu();
        return choice();
    }

    public void printMenu() {
        final String X_COLOR = Game.ANSI_BLUE;
        final String O_COLOR = Game.ANSI_RED;
        final String BORDER_COLOR = Game.ANSI_GREEN;
        final String RESET = Game.ANSI_RESET;

        System.out.println(BORDER_COLOR + "█".repeat(31) + "▒" + RESET);
        System.out.println(X_COLOR + "██▒         ██▒  " + RESET + O_COLOR + "   █████████▒" + RESET);
        System.out.println(X_COLOR + " ██▒       ██▒   " + RESET + O_COLOR + " ██▒        ██▒" + RESET);
        System.out.println(X_COLOR + "   ██▒   ██▒     " + RESET + O_COLOR + " ██▒        ██▒" + RESET);
        System.out.println(X_COLOR + "     ████▒       " + RESET + O_COLOR + " ██▒        ██▒" + RESET);
        System.out.println(X_COLOR + "   ██▒   ██▒     " + RESET + O_COLOR + " ██▒        ██▒" + RESET);
        System.out.println(X_COLOR + " ██▒       ██▒   " + RESET + O_COLOR + " ██▒        ██▒" + RESET);
        System.out.println(X_COLOR + "██▒         ██▒  " + RESET + O_COLOR + "   █████████▒" + RESET);
        System.out.println(BORDER_COLOR + "█".repeat(31) + "▒");
        System.out.println("▙ 1 - Грати");
        System.out.println("▙ 2 - Рейтинг гравців");
        if (!player.getName().isEmpty())
            System.out.println("▙ 3 - Змінити гравця");
        System.out.println("▙ 0 - Вихід");
        System.out.println("█".repeat(31) + "▒" + RESET);
    }

    public int choice() {
        Scanner in = new Scanner(System.in);
        String choice = in.next();
        switch (choice) {
            case "0", "1", "2" -> {
                return Integer.parseInt(choice);
            }
            case "3" -> {
                if (player.getName().isEmpty()) {
                    System.out.println("Введено некоректне значення, спробуйте знову");
                    return choice();
                } else {
                    player.inputName();
                    return menu();
                }

            }
            default -> {
                System.out.println("Введено некоректне значення, спробуйте знову");
                return choice();
            }
        }
    }


}
