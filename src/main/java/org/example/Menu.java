package org.example;

public class Menu {
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
        System.out.println("1 - Зіграти знову");
        System.out.println("2 - Історія матчів");
        System.out.println("0 - Вихід");
        System.out.println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀" + RESET);
    }

    public void menu(){

    }
}
