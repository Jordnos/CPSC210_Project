package ui.menus;

import model.AccountList;

import java.io.IOException;

public class MainMenu extends Menu {

    public static final String COMMAND_GAME = "G";
    public static final String COMMAND_ACCOUNT = "A";

    public MainMenu() throws IOException, InterruptedException {
        super();
    }

    // EFFECTS:  initializes variables
    @Override
    protected void initialize() {
        super.initialize();
        loggedIn = false;
        accLoggedIn = null;
        list = new AccountList();
    }

    // EFFECTS:  runs the command and calls the correlating method
    protected void processCommand(String command) throws IOException, InterruptedException {
        switch (command) {
            case COMMAND_GAME:
                gameMenu();
                break;
            case COMMAND_ACCOUNT:
                accountMenu();
                break;
            default:
                System.out.println("Selection not valid...");
        }
    }

    // EFFECTS:  displays the menu
    protected void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tG -> game selection");
        System.out.println("\tA -> account manager");
        System.out.println("\tQ -> quit");
    }

    // EFFECTS:  runs the game menu
    private void gameMenu() throws IOException, InterruptedException {
        new GameMenu();
    }

    // EFFECTS:  runs the account menu
    private void accountMenu() throws IOException, InterruptedException {
        new AccountMenu();
    }

}
