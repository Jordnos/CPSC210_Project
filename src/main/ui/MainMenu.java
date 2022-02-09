package ui;

import java.util.Scanner;

public class MainMenu extends Menu {

    public static final String COMMAND_GAME = "G";
    public static final String COMMAND_ACCOUNT = "A";

    protected void processCommand(String command) {
        if (command.equals(COMMAND_GAME)) {
            gameMenu();
        } else if (command.equals(COMMAND_ACCOUNT)) {
            accountMenu();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    protected void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tG -> game selection");
        System.out.println("\tA -> account manager");
        System.out.println("\tQ -> quit");
    }

    private void gameMenu() {
        new GameMenu();
    }

    private void accountMenu() {
        new AccountMenu();
    }

}
