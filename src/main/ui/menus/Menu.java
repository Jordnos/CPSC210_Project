package ui.menus;

import model.AccountList;
import model.Leaderboard;

import java.io.IOException;
import java.util.Scanner;

// tellerApp code is used as reference
// abstract class with things to do with menus
public abstract class Menu {
    public static final String COMMAND_QUIT = "Q";

    protected Scanner input;
    protected static Leaderboard leaderboardMemorizeSequence;
    protected static boolean loggedIn;
    protected static String accLoggedIn;
    protected static AccountList list;

    // MODIFIES: this
    // EFFECTS:  starts the runMenu
    public Menu() throws IOException, InterruptedException {
        runMenu();
    }

    // MODIFIES: this
    // EFFECTS:  initializes variables
    protected void initialize() {
        input = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS:  runs the menu and keeps it up until the quit command is done
    private void runMenu() throws IOException, InterruptedException {
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

    // EFFECTS:  runs the command and calls the correlating method
    protected abstract void processCommand(String command) throws IOException, InterruptedException;

    // EFFECTS:  displays the menu
    protected void displayMenu() {
        if (loggedIn) {
            System.out.println("\nLogged in: " + accLoggedIn);
        } else {
            System.out.println("\nNot logged in ");
        }
    }

}
