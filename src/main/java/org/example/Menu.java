package org.example;

import java.util.Scanner;

public class Menu {

    public int menu(){
        printMenu();
        return choice();
    }
    public void printMenu(){
        final String X_COLOR = Game.ANSI_BLUE;
        final String O_COLOR = Game.ANSI_RED;
        final String BORDER_COLOR = Game.ANSI_GREEN;
        final String RESET = Game.ANSI_RESET;

        System.out.println(BORDER_COLOR + "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
        System.out.println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀" + RESET);
        System.out.println(X_COLOR + "▀▀          ▀▀  " + RESET + O_COLOR + "     ▀▀▀▀▀▀▀▀▀" + RESET);
        System.out.println(X_COLOR + " ▀▀        ▀▀   " + RESET + O_COLOR + "   ▀▀         ▀▀" + RESET);
        System.out.println(X_COLOR + "   ▀▀    ▀▀     " + RESET + O_COLOR + "   ▀▀         ▀▀" + RESET);
        System.out.println(X_COLOR + "     ▀▀▀▀       " + RESET + O_COLOR + "   ▀▀         ▀▀" + RESET);
        System.out.println(X_COLOR + "   ▀▀    ▀▀     " + RESET + O_COLOR + "   ▀▀         ▀▀" + RESET);
        System.out.println(X_COLOR + " ▀▀        ▀▀   " + RESET + O_COLOR + "   ▀▀         ▀▀" + RESET);
        System.out.println(X_COLOR + "▀▀          ▀▀  " + RESET + O_COLOR + "     ▀▀▀▀▀▀▀▀▀" + RESET);
        System.out.println(BORDER_COLOR + "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
        System.out.println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
        System.out.println("1 - Грати");
        System.out.println("2 - Історія матчів");
        System.out.println("0 - Вихід");
        System.out.println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀" + RESET);
    }

    public int choice(){
        Scanner in = new Scanner(System.in);
        String choice = in.next();
        switch (choice){
            case "0", "1", "2" -> {
                return Integer.parseInt(choice);
            }
            default -> {
                System.out.println("Введено некоректне значення, спробуйте знову");
                return choice();
            }
        }
    }


}
