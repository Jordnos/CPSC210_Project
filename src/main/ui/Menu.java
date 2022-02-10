package ui;

import java.util.Scanner;

public abstract class Menu {

    public static final String COMMAND_QUIT = "Q";

    protected Scanner input;
    protected boolean loggedIn;
    protected String accLoggedIn;

    public Menu() {
        runMenu();
    }

    private void initialize() {
        loggedIn = false;
        accLoggedIn = null;
        input = new Scanner(System.in);
    }

    private void runMenu() {
        boolean keepGoing = true;
        String command;
        initialize();
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
    }

    protected abstract void processCommand(String command);

    protected abstract void displayMenu();

}
