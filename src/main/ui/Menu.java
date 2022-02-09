package ui;

import java.util.Scanner;

public abstract class Menu {

    public static final String COMMAND_QUIT = "Q";

    protected Scanner input;
    protected boolean loggedIn;
    protected String accLoggedIn;

    public Menu() {
        loggedIn = false;
        accLoggedIn = null;
        runMenu();
    }

    private void runMenu() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toUpperCase();

            if (command.equals(COMMAND_QUIT)) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    protected abstract void processCommand(String command);

    protected abstract void displayMenu();

}
